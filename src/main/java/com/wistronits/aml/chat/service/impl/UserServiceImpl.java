package com.wistronits.aml.chat.service.impl;

import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wistronits.aml.chat.entity.User;
import com.wistronits.aml.chat.mapper.UserMapper;
import com.wistronits.aml.chat.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wistronits.aml.commons.Constant;
import com.wistronits.aml.commons.redis.RedisUtil;
import com.wistronits.aml.commons.util.*;
import com.wistronits.aml.commons.websocket.Messages;
import com.wistronits.aml.commons.websocket.SessionUser;
import com.wistronits.aml.commons.websocket.WebSocketProduct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.Wrapper;
import java.util.*;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author gzp
 * @since 2018-09-19
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private RedisUtil redisUtil;
	@Autowired
	private WebSocketProduct product;

	private static String isTuling = "-1";

	@Override
	public Boolean checkByName(String userName, String userId) {
		// userId==null代表此时是新增时的校验
		List<User> ids = userMapper.checkByName(userName, userId);
		return ids == null || ids.size() == 0;
	}

	@Override
	public Result login(Map map, HttpServletRequest request) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		String userName = (String) map.get("userName");
		String password = (String) map.get("password");

		// 根据用户名查询用户
		User user = userMapper.getUser(userName);
		// 用户不存在的话
		if (user == null) {
			logger.info("用户名不存在");
			return ResultUtils.warn(ResultCode.LOGIN_ERROR);
		}
		// 用户存在，密码不正确，返回登录失败用户名或者密码错误。
		if (!Md5.encode(password).equals(user.getPassword())) {
			logger.info("密码错误");
			return ResultUtils.warn(ResultCode.LOGIN_ERROR);
		}

		// 获取登录用户的ip
		String loginIp;
		// 用户真实ip的XFF头
		String xffHeader = "x-forwaeded-for";
		if (request.getHeader(xffHeader) == null) {
			loginIp = request.getRemoteAddr();
			logger.info("用户登录ip：" + loginIp);
		} else {
			loginIp = request.getHeader(xffHeader);
			logger.info("用户登录2ip：" + loginIp);
		}

		Map<String, Object> data = new HashMap<>();
		// 修改登陆时间
		String time = user.getLastTime();
		String latestLoginTime = "";
		if (time != null && "".equals(time)) {
			latestLoginTime = time;
		}

		// TODO
		// 登录前判断用户是否已经登录，是的话将前一个用户下线。
		List<Object> tokenList = redisUtil.lGet(Constant.USER, 0, -1);
		if (tokenList != null && !tokenList.isEmpty()) {
			for (Object obj : tokenList) {
				String str = obj.toString();
				if (ObjectUtils.isNotEmpty(redisUtil.get(str))) {
					SessionUser sessionUser = mapper.readValue((String) redisUtil.get(str),
							SessionUser.class);
					if (sessionUser.getUserId().equals(user.getId())) {
						product.offLine(str);
						continue;
					}
				}
			}
		}

		// SessionUser
		SessionUser sessionUser = new SessionUser();
		sessionUser.setUserId(user.getId());
		sessionUser.setLoginDate(new Date());
		sessionUser.setLoginIp(loginIp);
		sessionUser.setUsername(user.getUserName());
		// 生成token
		String token = Md5.encode(user.getUserName() + UUID.randomUUID());
		logger.info(token);

		// token对应的是对象，并将token存入缓存
		redisUtil.set(token, mapper.writeValueAsString(sessionUser), -1);
		redisUtil.lSet(Constant.USER, token);

		data.put("user", user);
		data.put("token", token);

		user.setLastIp(loginIp);
		user.setLastTime(latestLoginTime);
		userMapper.updateById(user);
		return ResultUtils.success(data);
	}

	@Override
	public Messages chatAll(Messages msg, HttpServletRequest request) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		msg.getMessage().setIsTuling(isTuling);
		if (msg.getType().equals("notice")) {
			String token = request.getHeader("token");
			SessionUser sessionUser = mapper.readValue(redisUtil.get(token).toString(),
					SessionUser.class);
			if (msg.getMessage().getContent().equals("join")) {
				redisUtil.sSet(Constant.ONLINE, token);
				String message = "[" + msg.getMessage().getFrom() + "]" + "进入聊天室,当前在线人数";
				msg.getMessage().setContent(message);
			}
			if (msg.getMessage().getContent().equals("leave")) {
				String message = "[" + msg.getMessage().getFrom() + "]" + "离开聊天室,当前在线人数";
				msg.getMessage().setContent(message);
				redisUtil.setRemove(Constant.ONLINE, token);
			}
			if (msg.getMessage().getContent().equals("offline")) {
				String message = "[" + msg.getMessage().getFrom() + "]" + "下线,当前在线人数";
				msg.getMessage().setContent(message);
				redisUtil.del(token);
				redisUtil.lRemove(Constant.USER, 1, token);
				redisUtil.setRemove(Constant.ONLINE, token);
			}
			Set<Object> set = redisUtil.sGet(Constant.ONLINE);
			List list = new ArrayList();
			for (Object obj : set) {
				SessionUser user = mapper.readValue(redisUtil.get(obj.toString()).toString(),
						SessionUser.class);
				list.add(user);
			}
			String content = msg.getMessage().getContent();
			if (CollectionUtils.isNotEmpty(list)) {
				msg.setList(list);
				msg.getMessage().setContent(msg.getMessage().getContent() + list.size() + "人");
			}
			if (content.equals("tulingjoin")) {
				String message = sessionUser.getUsername()+"启用图灵机器人";
				msg.getMessage().setContent(message);
				isTuling = "1";
				msg.getMessage().setIsTuling(isTuling);
			}
			if (content.equals("tulingleave")) {
				String message = sessionUser.getUsername()+"关闭图灵机器人";
				msg.getMessage().setContent(message);
				isTuling = "-1";
				msg.getMessage().setIsTuling(isTuling);
			}
		}
		User user = userMapper.getUser(msg.getMessage().getFrom());
		msg.getMessage().setUser(user);
		product.chatAll(BeanUtils.objectToJson(msg));
		return msg;
	}


	@Override
	public User getByUserId(String userId) {
		User user = userMapper.getByUserId(userId);
		return user;
	}

	@Override
	public void updatePwd(User user) {
		userMapper.updatePwd(user);
	}



}

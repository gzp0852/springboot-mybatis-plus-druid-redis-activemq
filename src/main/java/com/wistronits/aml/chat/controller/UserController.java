package com.wistronits.aml.chat.controller;


import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wistronits.aml.chat.entity.User;
import com.wistronits.aml.chat.service.IUserService;
import com.wistronits.aml.commons.util.*;
import com.wistronits.aml.commons.websocket.Message;
import com.wistronits.aml.commons.websocket.Messages;
import com.wistronits.aml.configuration.Access;
import org.apache.logging.log4j.core.util.UuidUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author gzp
 * @since 2018-09-19
 */
@RestController
@RequestMapping("/chat/user")
public class UserController {
	private static final ObjectMapper mapper = new ObjectMapper();
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private IUserService iUserService;
	static {
		// 视空字符串为null
		mapper.configure(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT, true);
		//
		mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}


	@PostMapping("/add")
	public Result addUser(@RequestBody User user) {
		String userName = user.getUserName();
		String password = user.getPassword();

		if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(password)) {
			return ResultUtils.warn(ResultCode.PARAMETER_ERROR,  "用户名或密码不能为空");
		}
		boolean isExist = iUserService.checkByName(userName, user.getUserId());
		if (!isExist) {
			return ResultUtils.warn(ResultCode.PARAMETER_ERROR,  "用户名重复");
		}
		user.setUserCode(RandomUtils.random6());
		user.setUserId(UuidUtil.getTimeBasedUuid().toString());
		user.setPassword(Md5.encode(password));
		iUserService.save(user);
		return ResultUtils.success(user);
	}

	/**
	 * 用户登录
	 *
	 * @param params 用户名、密码
	 * @param request HttpServletRequest
	 * @return User对象
	 * @throws IOException IO异常
	 */
	@PostMapping("/login")
	@Access(operation = "用户登录")
	public Result login(@RequestBody String params, HttpServletRequest request) throws IOException {
		Map map = mapper.readValue(params, Map.class);
		Result result = iUserService.login(map, request);
		return result;
	}


	@PostMapping("/chatAll")
	public Result chatAll(@RequestBody Messages msg, HttpServletRequest request) throws IOException {
		return ResultUtils.success(iUserService.chatAll(msg, request));
	}

}

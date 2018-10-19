package com.wistronits.aml.chat.controller;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wistronits.aml.chat.entity.User;
import com.wistronits.aml.chat.service.IUserService;
import com.wistronits.aml.commons.util.*;
import com.wistronits.aml.commons.websocket.Messages;
import com.wistronits.aml.configuration.Access;
import org.apache.logging.log4j.core.util.UuidUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.ObjectUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * 前端控制器
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
			return ResultUtils.warn(ResultCode.PARAMETER_ERROR, "用户名或密码不能为空");
		}
		boolean isExist = iUserService.checkByName(userName, user.getUserId());
		if (!isExist) {
			return ResultUtils.warn(ResultCode.PARAMETER_ERROR, "用户名重复");
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

	/**
	 * 发送上线、聊天消息
	 *
	 * @param msg 消息
	 * @param request request
	 * @return result
	 * @throws IOException ioexception
	 */
	@PostMapping("/chatAll")
	public Result chatAll(@RequestBody Messages msg, HttpServletRequest request) throws IOException {
		return ResultUtils.success(iUserService.chatAll(msg, request));
	}

	/**
	 * 根据id查询用户信息
	 * 
	 * @param userId 用户id
	 * @return result
	 */
	@PostMapping("/info")
	public Result info(@RequestBody String userId) {
		User user = iUserService.getByUserId(userId);
		if (Objects.nonNull(user)) {
			return ResultUtils.success(user);
		}
		return ResultUtils.warn(ResultCode.PARAMETER_ERROR, "查无此人");
	}

	/**
	 * 更新用户信息
	 *
	 * @param user user
	 * @return result
	 */
	@PostMapping("/update")
	public Result update(@RequestBody User user) {
		if (Objects.nonNull(user)) {
			iUserService.updateByUserId(user);
			return ResultUtils.success(user);
		} else {
			return ResultUtils.warn(ResultCode.PARAMETER_ERROR, "修改失败");
		}
	}

	/**
	 * 更新用户密码
	 *
	 * @param params String
	 * @return result
	 */
	@PostMapping("/updatePwd")
	public Result updatePwd(@RequestBody String params) throws Exception {
		Map map = mapper.readValue(params, Map.class);
		String oldpwd = (String) map.get("oldPassword");
		String password = (String) map.get("password");

		if (StringUtils.isNotEmpty(oldpwd) && StringUtils.isNotEmpty(password)) {
			String id = (String) map.get("userId");
			if (StringUtils.isNotEmpty(id)) {
				User user = iUserService.getByUserId(id);
				if (user.getPassword().equals(oldpwd)) {
					user.setPassword(password);
					iUserService.updatePwd(user);
					return ResultUtils.success("修改成功");
				} else {
					return ResultUtils.warn(ResultCode.PASSWORD_ERR_OLD);
				}
			} else {
				return ResultUtils.warn(ResultCode.PARAMETER_ERROR);
			}
		} else {
			return ResultUtils.warn(ResultCode.PASSWORD_NULL);
		}

	}

	@Value("${img.location}")
	private String location;
	@Value("${hostAddress}")
	private String hostAddress;

	@PostMapping("/upload/{userid}")
	public Result upload(@PathVariable("userid") String userid, MultipartFile file,
			HttpServletRequest request) throws Exception {
		if (file.isEmpty() || StringUtils.isBlank(file.getOriginalFilename())) {
			// throw new BusinessException(ResultEnum.IMG_NOT_EMPTY);
			return ResultUtils.warn(ResultCode.IMG_NOT_EMPTY);
		}
		String contentType = file.getContentType();
		if (!contentType.contains("")) {
			return ResultUtils.warn(ResultCode.IMG_FORMAT_ERROR);
		}
		String root_fileName = file.getOriginalFilename();
		logger.info("上传图片:name={},type={}", root_fileName, contentType);
		// 处理图片
		User currentUser = iUserService.getByUserId(userid);
		logger.info("图片保存路径={}", location);
		String file_name = null;
		try {
			file_name = UploadUtil.saveImg(file, location, currentUser.getUserCode());
			if (StringUtils.isNotBlank(file_name)) {
				String hostAddress1 = file_name;
				currentUser.setProfilehead(hostAddress1);
				iUserService.updateByUserId(currentUser);
			}
			logger.info("返回值：{}", currentUser);
		} catch (IOException e) {
			return ResultUtils.warn(ResultCode.SAVE_IMG_ERROE);
		}
		return ResultUtils.success(currentUser);
	}


	/**
	 * 获取指定目录下的头像
	 * 
	 * @param path String
	 * @param response HttpServletResponse
	 * @throws Exception Exception
	 */
	@GetMapping("/img/{userCode}")
	public void getImg(@PathVariable("userCode") String path, HttpServletResponse response) throws Exception {
		FileInputStream inputStream = new FileInputStream(new File(hostAddress + path));
		byte[] str = new byte[1024];
		while (inputStream.read(str) != -1) {
			response.getOutputStream().write(str);
		}
	}
}

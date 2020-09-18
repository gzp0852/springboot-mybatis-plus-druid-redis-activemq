package com.wistronits.aml.chat.service;

import com.wistronits.aml.chat.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wistronits.aml.commons.util.Result;
import com.wistronits.aml.commons.websocket.Messages;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author gzp
 * @since 2018-09-19
 */
public interface IUserService extends IService<User> {

	Boolean checkByName(String name, String userId);

	Result login(Map map, HttpServletRequest request) throws IOException;

	Messages chatAll(Messages msg, HttpServletRequest request) throws IOException;

	User getByUserId(String userId);

	void updatePwd(User user);

}

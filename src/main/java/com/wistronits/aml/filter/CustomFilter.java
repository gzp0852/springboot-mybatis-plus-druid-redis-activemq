package com.wistronits.aml.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wistronits.aml.commons.Constant;
import com.wistronits.aml.commons.redis.RedisUtil;
import com.wistronits.aml.commons.websocket.SessionUser;
//import com.wistronits.aml.commons.websocket.WebSocketSendBean;
import com.wistronits.aml.commons.websocket.WebSocketSendBean;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.util.ObjectUtils;
//import org.springframework.messaging.simp.SimpMessagingTemplate;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * @author gzp
 * @date 2018/8/24 17:33
 */
@WebFilter(filterName = "customFilter", urlPatterns = "/token/*")
public class CustomFilter implements Filter {
	private final Logger logger = LogManager.getLogger(getClass());

	@Value("${userOutTime}")
	private int userOutTime;
	@Autowired
	private RedisUtil redisUtil;
	@Autowired
	private SimpMessagingTemplate template;

	private FilterConfig filterConfig;
	private FilterChain chain;
	private HttpServletRequest request;
	private HttpServletResponse response;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = null;
		System.out.println("过滤器初始化");
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
			FilterChain filterChain) throws IOException, ServletException {
		System.out.println("CustomFilter指定过滤器操作......");
		ObjectMapper objectMapper = new ObjectMapper();
		// todo
		chain = filterChain;
		request = (HttpServletRequest) servletRequest;
		response = (HttpServletResponse) servletResponse;

		// this.chain.doFilter(request, response);
		// 获取当前页面文件名处url
		if (isLoginOperation(this.request)) {
			logger.info("登录状态");
			// 执行操作后必须doFilter
			this.chain.doFilter(request, response);
		} else {
			setHeader(request, response);
			String token = request.getHeader("token");
			logger.info("------------------------" + token);
			SessionUser user = null;
			if (token != null && !ObjectUtils.isEmpty(redisUtil.get(token))) {
				String str = (String) redisUtil.get(token);
				user = objectMapper.readValue(str, SessionUser.class);
			}
			if (user == null) {
				if (request.getRequestURL().toString().contains("/chat/user/img")) {
					this.chain.doFilter(request, response);
				}
				try {
					// 前缀
					String pre = "/chatTopic/user/";
					String path = pre + token;
					String message = "您未登录！";
					WebSocketSendBean bean = new WebSocketSendBean(path, message);
					template.convertAndSend(bean.getPath(), bean.getMsg());
				} catch (Exception e) {
					logger.error("拦截失败！");
				}
			} else {
				logger.info("-----------------------------------------登陆后的操作");
				user.setLoginDate(new Date());
				redisUtil.set(token, objectMapper.writeValueAsString(user));
				this.chain.doFilter(request, response);
			}
		}

	}

	@Override
	public void destroy() {
		this.filterConfig = filterConfig;
		System.out.println("过滤器销毁");
	}

	/**
	 * 检查是否为登陆操作
	 *
	 * @param request HttpServletRequest
	 * @return boolean
	 */
	public boolean isLoginOperation(HttpServletRequest request) {
		String url = request.getRequestURL().toString();
		logger.info(url);
		// 登录路径
		String login = "/chat/user/login";
		String websocket = "/chat/chatEndpointWisely";
		String img = "/chat/user/img";
		String source = "/source";
		String plugins = "/plugins";
		return url.contains(login) || url.contains(websocket) || url.contains(img) || url.contains(source)
				|| url.contains(plugins);
	}

	/**
	 * 设置头信息
	 *
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 */
	public void setHeader(HttpServletRequest request, HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Allow-Headers", "x-requested-with,Content-Type,Authorization");
		response.setContentType("application/json;charset=utf-8");
		logger.info(
				String.format("%s request to %s", request.getMethod(), request.getRequestURL().toString()));
	}

}

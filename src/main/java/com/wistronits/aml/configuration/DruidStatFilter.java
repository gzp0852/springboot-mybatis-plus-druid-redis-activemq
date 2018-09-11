package com.wistronits.aml.configuration;

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

/**
 * @author gzp
 * @date 2018/8/28 10:53
 */
@WebFilter(
		filterName = "druidWebStatFilter",
		urlPatterns = "/*",
		initParams = {
				@WebInitParam(name="exclusions",value="*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*"),// 忽略资源
		}
)
public class DruidStatFilter {

}

package com.wistronits.aml.schedule;

import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wistronits.aml.commons.Constant;
import com.wistronits.aml.commons.redis.RedisUtil;
import com.wistronits.aml.commons.websocket.SessionUser;
import com.wistronits.aml.commons.websocket.WebSocketProduct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ScheduledFuture;

/**
 * @author 10376 2018/3/19
 */
@Component
public class TimeOutOfflineTask {
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Value("${userOutTime}")
	private String userOutTime;
	@Autowired
	private ThreadPoolTaskScheduler threadPoolTaskScheduler;
	@Autowired
	private RedisUtil redisUtil;
	@Autowired
	private WebSocketProduct product;

	@Bean
	public ThreadPoolTaskScheduler threadPoolTaskScheduler() {
		return new ThreadPoolTaskScheduler();
	}

	private ScheduledFuture<?> future;

	/**
	 * 第一位，表示秒，取值0-59 第二位，表示分，取值0-59 第三位，表示小时，取值0-23 第四位，日期天/日，取值1-31
	 * 第五位，日期月份，取值1-12 第六位，星期，取值1-7，星期一，星期二...，注：不是第1周，第二周的意思 另外：1表示星期天，2表示星期一。
	 * 第7为，年份，可以留空，取值1970-2099
	 */
	private String cron = "";

	public void start(final String cron) {
		this.cron = cron;
		stop();
		future = threadPoolTaskScheduler.schedule(() -> {
			logger.info("检查是否存在超时未操作用户************");
			long timeOut = Long.parseLong(userOutTime) * 60 * 1000;
		List<Object> list = redisUtil.lGet(Constant.USER, 0, -1);
			ObjectMapper mapper = new ObjectMapper();
			if (list != null && !list.isEmpty()) {
				for (Object obj : list) {
					String str = obj.toString();
					if (ObjectUtils.isNotEmpty(redisUtil.get(str))) {
						try {
							SessionUser sessionUser = mapper.readValue((String) redisUtil.get(str),
									SessionUser.class);
							long now = System.currentTimeMillis();
							long loginDate = sessionUser.getLoginDate() == null ? -1
									: sessionUser.getLoginDate().getTime();
							if (now - loginDate > timeOut) {
								logger.info("用户被下线***" + "***" + sessionUser.getUsername());
								product.userTimeOut(str);
								continue;
							}
						} catch (IOException e) {
							e.printStackTrace();
						}
					}

				}
			}
		}, triggerContext -> {
			if ("".equals(cron) || cron == null) {
				return null;
			}
			return new CronTrigger(cron).nextExecutionTime(triggerContext);
		});
	}

	public void stop() {
		// 取消任务调度
		if (future != null) {
			future.cancel(true);
			logger.info("定时任务被关闭");
		}
	}

	public String getCron() {
		return cron;
	}
}

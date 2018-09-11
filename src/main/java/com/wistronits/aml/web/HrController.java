package com.wistronits.aml.web;


import com.baomidou.mybatisplus.plugins.Page;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wistronits.aml.commons.redis.RedisUtil;
import com.wistronits.aml.commons.util.Result;
import com.wistronits.aml.commons.util.ResultUtils;
import com.wistronits.aml.entity.Hr;
import com.wistronits.aml.service.IHrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author gzp
 * @since 2018-08-27
 */
@RestController
@RequestMapping("/hr")
public class HrController {
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	@Autowired
	private IHrService hrService;

	@Autowired
	private RedisUtil redisUtil;

	@GetMapping("/{id}")
	public Result getHr(@PathVariable("id") int id) {
		return ResultUtils.success(hrService.getHr(id));
	}

	@GetMapping
	public Result getHrs() {
		return ResultUtils.success(hrService.selectPage(new Page<>(1, 10)));
	}

	@PostMapping("/getHrList")
	public Result getHrList(@RequestBody String params) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		Map map = mapper.readValue(params, Map.class);

		int current = (int) map.get("pageSize");
		int size = (int) map.get("pageNum");
		int enabled= (int) map.get("enabled");

		return ResultUtils.success(hrService.selectHrPage(new Page<Hr>(current, size), enabled));
	}

	@GetMapping("/redisTest/{key}")
	public Result redisTest(@PathVariable("key") String key) {
		redisUtil.set("zzz", "zzz");
//		return ResultUtils.success(redisUtil.lGet(key, 0, -1));
		return ResultUtils.success(redisUtil.get(key));
	}
}

package com.wistronits.aml.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.wistronits.aml.entity.Hr;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author gzp
 * @since 2018-08-27
 */
public interface IHrService extends IService<Hr> {
	Hr getHr(int id);

	Page<Hr> selectHrPage(Page<Hr> page, int enabled);
}

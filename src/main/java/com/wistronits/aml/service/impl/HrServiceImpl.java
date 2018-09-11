package com.wistronits.aml.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.wistronits.aml.entity.Hr;
import com.wistronits.aml.mapper.HrMapper;
import com.wistronits.aml.service.IHrService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author gzp
 * @since 2018-08-27
 */
@Service
public class HrServiceImpl extends ServiceImpl<HrMapper, Hr> implements IHrService {

	@Autowired
	private HrMapper mapper;

	@Override public Hr getHr(int id) {
		return mapper.getHr(id);
	}

	@Override public Page<Hr> selectHrPage(Page<Hr> page, int enabled) {
		return page.setRecords(mapper.getHrList(page, enabled));
	}
}

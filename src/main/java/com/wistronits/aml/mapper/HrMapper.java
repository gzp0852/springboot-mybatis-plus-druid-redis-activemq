package com.wistronits.aml.mapper;

import com.baomidou.mybatisplus.plugins.Page;
import com.wistronits.aml.entity.Hr;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author gzp
 * @since 2018-08-27
 */
public interface HrMapper extends BaseMapper<Hr> {
	Hr getHr(@Param("id") int id);

	List<Hr> getHrList(@Param("page")Page<Hr> page, @Param("enabled") int enabled);
}

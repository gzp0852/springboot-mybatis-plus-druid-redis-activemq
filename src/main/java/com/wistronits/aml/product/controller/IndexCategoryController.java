package com.wistronits.aml.product.controller;


import com.wistronits.aml.commons.util.Result;
import com.wistronits.aml.commons.util.ResultUtils;
import com.wistronits.aml.product.entity.IndexCategory;
import com.wistronits.aml.product.service.IIndexCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author gzp
 * @since 2018-09-12
 */
@RestController
@RequestMapping("/product/index-category")
public class IndexCategoryController {
	@Autowired
	private IIndexCategoryService iIndexCategoryService;

	@GetMapping("query")
	public Result queryIndexCategory() {
		return ResultUtils.success(iIndexCategoryService.getById("1"));
	}
}

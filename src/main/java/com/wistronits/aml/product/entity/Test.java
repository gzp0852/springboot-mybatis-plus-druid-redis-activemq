package com.wistronits.aml.product.entity;

import lombok.Data;

/**
 * @author gzp
 * @date 2018/9/12 16:25
 */
@Data
public class Test {
	private String id;
	private String name;
	private String desc;
	private Long timeStamp;

	public Test(String id, String name, String desc, Long timeStamp) {
		this.id = id;
		this.name = name;
		this.desc = desc;
		this.timeStamp = timeStamp;
	}
}

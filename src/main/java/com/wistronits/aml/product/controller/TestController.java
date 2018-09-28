/*
package com.wistronits.aml.product.controller;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.wistronits.aml.commons.util.Result;
import com.wistronits.aml.commons.util.ResultUtils;
import com.wistronits.aml.product.entity.Test;
import lombok.experimental.var;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

*/
/**
 * @author gzp
 * @date 2018/9/12 16:22
 *//*

@RestController
@RequestMapping("/test")
public class TestController {

	@Autowired
	private MongoTemplate mongoTemplate;

	@GetMapping("/{index}")
	public void saveTest(@PathVariable("index") int index) {
		List list = new ArrayList();

		for (int i = index; i <= 40000000; i++) {
			Test test = new Test(i + "", "aaa" + i, "bbb" + i, (long) i);
			list.add(test);

			if (i != 0 && i % 1000000 == 0)// 每100万次批量插入一次
			{
				mongoTemplate.insertAll(list);
				list.clear();
			}
		}
	}

	@GetMapping("/get")
	public Result getTest() {
		Query query = new Query();
		query.addCriteria(Criteria.where("name").is("aaa101"));
		List<Test> test = mongoTemplate.find(query, Test.class);
		return ResultUtils.success(test);
	}

	@PostMapping("/query")
	public Result queryTest(@RequestBody Test test) {
		Query query = new Query();
		query.addCriteria(Criteria.where("name").is(test.getName()));
		query.addCriteria(Criteria.where("id").is(test.getId()));
		query.addCriteria(Criteria.where("desc").is(test.getDesc()));
		List<Test> tests = mongoTemplate.find(query, Test.class);

//		Bson query = new BasicDBObject();
//		query.put("name", new BasicDBObject("$is", test.getName()));
//		DBCursor dbCursor = mongoTemplate.getCollection("test").find;
//		List<Test> list = new ArrayList<>();
//		while (dbCursor.hasNext()){
//			DBObject object=dbCursor.next();
//			Test test1=new Test(object.get("_id").toString(), (String)object.get("name"), (String)object.get("desc"), (Long)object.get("timeStamp"));
//			list.add(test1);
//		}
		return ResultUtils.success(tests);
	}

}
*/

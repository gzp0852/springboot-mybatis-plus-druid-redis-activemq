package com.wistronits.aml.web;

import com.wistronits.aml.AmlApplication;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.*;

/**
 * @author gzp
 * @date 2018/8/28 16:31
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AmlApplication.class)
@WebAppConfiguration
@Rollback(value = true)
@Transactional(transactionManager = "transactionManager")
public class HrControllerTest {
	@Autowired
	private WebApplicationContext context;

	private MockMvc mvc;

	@Before
	public void setUp() throws Exception{
		//mvc = MockMvcBuilders.standaloneSetup(new LoginController()).build();
		mvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	/**
	 * 测试用户登录
	 * @throws Exception
	 */
	@Test
	public void loginTest() throws Exception {


	}
}
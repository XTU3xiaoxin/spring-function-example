package com.tja;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.tja.config.ApplicationConfig;
import com.tja.domain.Customer;
import com.tja.domain.EmailAddress;
import com.tja.repository.CustomerRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes =ApplicationConfig.class)
@WebAppConfiguration    //如果项目里引入了spring mvc， 必须加上这个注解，表示测试环境使用的applicationContext是web类型的
public class CustomerControllerTests {

	@Autowired
	private WebApplicationContext wac;
	
	@Autowired
	private CustomerRepository repositiory;
	
	private MockMvc  mockMvc;
	
	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	//mvc controller测试
	@Test
	public void testCustomerList() throws Exception {
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/customer/list"))
							       .andExpect(MockMvcResultMatchers.view().name("index"))
							       .andExpect(MockMvcResultMatchers.model().attributeExists("customerList"))
							       .andReturn();
		//还可以对返回结果result作进一步的断言处理.
	}
	
	@Test
	public void testCustomerAdd() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/customer/add", "firstName","xiaoxin","lastName",""))
		       .andExpect(MockMvcResultMatchers.model().hasErrors())
		       .andExpect(MockMvcResultMatchers.view().name("addCustomer"));
	}
	
	@Test
	public void testCustomerAdd2() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/customer/add")
				                              .param("firstName", "")
				                              .param("lastName", "xixi")
				                              .param("emailAddress.email", "xiaoxin"))
		       .andExpect(MockMvcResultMatchers.model().attributeHasFieldErrors("customer", "firstName","emailAddress.email"))
		       .andExpect(MockMvcResultMatchers.view().name("addCustomer"));
	}
	
	@Test
	public void testCustomerAdd3() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/customer/add")
				                              .param("firstName", "xiaoxin")
				                              .param("lastName", "xixi")
				                              .param("emailAddress.email", "xiaoxin@1890.com"))
		       .andExpect(MockMvcResultMatchers.model().hasNoErrors())
		       .andExpect(MockMvcResultMatchers.view().name("redirect:/customer/list"));
		//clear data
		
		//谁要好的办法来清理数据啊   这代码好恶心哦
		Customer c = repositiory.findByEmailAddress(new EmailAddress("xiaoxin@1890.com"));
		repositiory.delete(c);
	}
	
}

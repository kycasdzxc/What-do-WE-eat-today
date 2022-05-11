package com.inadang.controller;

import static org.junit.Assert.assertNotNull;

import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration ({
	"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
	"file:src/main/webapp/WEB-INF/spring/root-context.xml"
})
@Log4j
@WebAppConfiguration
public
class PaymentControllerTests {
	@Setter @Autowired
	private WebApplicationContext ctx;
	private MockMvc mockMvc;
	
	@Before
	public void setUp(){
		this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
	}
	
	@Test
	public void exist(){
		log.info(mockMvc);
		assertNotNull(mockMvc);
	}
	
	@Test
	public void testList() throws Exception {
		ModelAndView mav = mockMvc.perform(MockMvcRequestBuilders
				.get("order/history")
				)
				.andReturn()
				.getModelAndView();
		
		Map<String, Object> map = mav.getModel();
		@SuppressWarnings("unchecked")
		List<Object> list = (List<Object>) map.get("payment");
		list.forEach(log::info);
	}
	
	@Test
	public void testGet() throws Exception{
		ModelAndView mav = mockMvc.perform(MockMvcRequestBuilders
				.get("/order/history")
				.param("pno", "6"))
				.andReturn()
				.getModelAndView();
		
		Map<String, Object> map = mav.getModel();
		Object payment = map.get("payment");
		log.info(payment);
	}
}

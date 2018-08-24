package com.example.demo.controller;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import com.example.demo.entity.*;
import com.example.demo.service.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
/*@Ignore*/
@RunWith(SpringRunner.class)
@WebMvcTest(value =  ControllerClass.class, secure = false)
public class ControllerClassTest {
	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private IService iservice;
	List<Employee> emp= Arrays.asList( new Employee(1,"Manohar","Kumar","Noida"));
	String exampleCourseJson = "[{\"id\":\"1\",\"firstName\":\"Manohar\",\"lastName\":\"Kumar\",\"location\":\"Noida\"}]";
	Employee emp1=null;
	@Test
	public void testAllEmployee() throws Exception {
		Mockito.when(
				iservice.getAllEmployee()
						).thenReturn(emp);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"allemployee").accept(
				MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse());
		String expected = "{id:1,firstName:Manohar,lastName:Kumar,location:Noida}";

		// {"id":"Course1","name":"Spring","description":"10 Steps, 25 Examples and 10K Students","steps":["Learn Maven","Import Project","First Example","Second Example"]}

		/*JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);*/


	}
	
	
	@Test
	public void testAdd() throws Exception {
		Employee mockCourse = new Employee(1,"Manohar","Kumar","Noida");
          String inputInJson= this.mapToJson(mockCourse);
		// studentService.addCourse to respond back with mockCourse
		Mockito.when(iservice.addEmployee(Mockito.any(Employee.class))).thenReturn(true);

		// Send course as body to /students/Student1/courses
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("add")
				.accept(MediaType.APPLICATION_JSON).content(inputInJson)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();
          String outputInJson =response.getContentAsString();
		//assertThat(outputInJson).isEqualTo(inputInJson);
		//assertEquals(HttpStatus.CREATED.value(), response.getStatus());
		/*assertEquals("http://localhost/students/Student1/courses/1",
			response.getHeader(HttpHeaders.LOCATION));*/

	}
	@Test
	public void testUpdate() throws Exception {
		Employee mockCourse = new Employee(1,"Manohar","Kumar","Noida");
		  String inputInJson= this.mapToJson(mockCourse);
		// studentService.addCourse to respond back with mockCourse
		//Mockito.when(iservice.updateEmployee(Mockito.any(Employee.class))).thenReturn(emp1);

		// Send course as body to /students/Student1/courses
		  RequestBuilder requestBuilder = MockMvcRequestBuilders.put("update")
				 .accept(MediaType.APPLICATION_JSON);
				  mockMvc.perform(requestBuilder)
				  .andExpect(status().is(404));

		

	}
	@Test
	public void testDelete() throws Exception {
		Employee mockCourse = new Employee(1,"Manohar","Kumar","Noida");

		// studentService.addCourse to respond back with mockCourse
		//Mockito.when(iservice.deleteEmployee(Mockito.anyInt())).thenReturn(null);

		// Send course as body to /students/Student1/courses
		  RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("employee/1")
					 .accept(MediaType.APPLICATION_JSON);
					  mockMvc.perform(requestBuilder)
					  .andExpect(status().is(404));
		/*assertEquals(HttpStatus.CREATED.value(), response.getStatus());

		assertEquals("http://localhost/students/Student1/courses/1",
			response.getHeader(HttpHeaders.LOCATION));
*/
	}
	@Test
	public void getByID() throws Exception
	{
	try
	{
	RequestBuilder requestBuilder = MockMvcRequestBuilders.get("employee/{id}")
	.requestAttr("id", emp1.getId())
	.accept(MediaType.APPLICATION_JSON);
	MvcResult result = mockMvc.perform(requestBuilder).andReturn();
	 
	String expected="{\"id\":1,\"firstName\":\"Manohar\",\"lastName\":Kumar,\"location\":Noida\"}";
	assertEquals(expected, result.getResponse().getContentAsString());
	System.out.println("getMedicineById successfully executed...");
	}
	catch(Exception e)
	{
	System.out.println(e);
	}
	}
	private String mapToJson(Object object) throws JsonProcessingException{
		ObjectMapper objectmapper= new ObjectMapper();
		return objectmapper.writeValueAsString(object);
	}

	
}

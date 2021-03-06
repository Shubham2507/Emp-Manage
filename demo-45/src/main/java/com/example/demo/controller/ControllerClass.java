package com.example.demo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.demo.entity.*;
import com.example.demo.security.config.AccountCredentials;
import com.example.demo.security.config.TokenAuthenticationService;
//import com.example.demo.security.UserDetails;
import com.example.demo.service.*;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
@Controller


public class ControllerClass {
	@Autowired
	private IService empService;
	private AuthenticationManager authenticationManager;



	@GetMapping("employee/{id}")
	@ApiImplicitParams({
	    @ApiImplicitParam(name = "Authorization", value = "Authorization token", 
	                      required = true, dataType = "string", paramType = "header") })
	public ResponseEntity<Employee> getemployeeById(@PathVariable("id") Integer id) {
		Employee med =empService.getemployeeById(id);
		return new ResponseEntity<Employee>(med, HttpStatus.OK);
	}

	@GetMapping("allemployee")
	@ApiImplicitParams({
	    @ApiImplicitParam(name = "Authorization", value = "Authorization token", 
	                      required = true, dataType = "string", paramType = "header") })
	public ResponseEntity<List<Employee>> getAllemployee(){
		List<Employee> l =empService.getAllEmployee();
		return new ResponseEntity<List<Employee>>(l, HttpStatus.OK);

	}

	@PostMapping("add")
	@ApiImplicitParams({
	    @ApiImplicitParam(name = "Authorization", value = "Authorization token", 
	                      required = true, dataType = "string", paramType = "header") })
	public ResponseEntity<String> addemployee(@RequestBody Employee employee, UriComponentsBuilder builder) {
		boolean flag = empService.addEmployee(employee);
		if (flag == false) {
			return new ResponseEntity<String>("employee already exists",HttpStatus.CONFLICT);
		}

		return new ResponseEntity<String>("employee Inserted",HttpStatus.CREATED);
	}

	@PutMapping("update")
	@ApiImplicitParams({
	    @ApiImplicitParam(name = "Authorization", value = "Authorization token", 
	                      required = true, dataType = "string", paramType = "header") })
	public ResponseEntity<Employee> updateemployee(@RequestBody Employee employee) {
		empService.updateEmployee(employee);
		return new ResponseEntity<Employee>(employee, HttpStatus.OK);
	}
	@DeleteMapping("employee/{id}")
	@ApiImplicitParams({
	    @ApiImplicitParam(name = "Authorization", value = "Authorization token", 
	                      required = true, dataType = "string", paramType = "header") })
	public ResponseEntity<String> deleteemployee(@PathVariable("id") Integer id) {
		empService.deleteEmployee(id);
		return new ResponseEntity<String>("employee Deleted",HttpStatus.NO_CONTENT);
	}	
	

	

	
	 @RequestMapping("/users")
	  public @ResponseBody String getUsers() {
	    return "{\"users\":[{\"firstname\":\"Richard\", \"lastname\":\"Feynman\"}," +
	           "{\"firstname\":\"Marie\",\"lastname\":\"Curie\"}]}";
	  }
	 @RequestMapping(value = "/login", method = RequestMethod.POST)
	    public void register(@RequestBody AccountCredentials loginUser,HttpServletResponse response) throws AuthenticationException {

	        final Authentication authentication = authenticationManager.authenticate(
	                new UsernamePasswordAuthenticationToken(
	                        loginUser.getUsername(),
	                        loginUser.getPassword()
	                )
	        );
	        SecurityContextHolder.getContext().setAuthentication(authentication);
	        String token=TokenAuthenticationService.addAuthentication(response,loginUser.getUsername());
	        System.out.println(token);
	        response.setHeader("token", token);
			
	
	}
}



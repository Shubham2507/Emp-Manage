package com.example.demo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.demo.entity.*;

import com.example.demo.security.UserDetails;
import com.example.demo.service.*;
@Controller


public class ControllerClass {
	@Autowired
	private IService empService;
	private AuthenticationManager authenticationManager;



	@GetMapping("employee/{id}")
	public ResponseEntity<Employee> getemployeeById(@PathVariable("id") Integer id) {
		Employee med =empService.getemployeeById(id);
		return new ResponseEntity<Employee>(med, HttpStatus.OK);
	}

	@GetMapping("allemployee")
	public ResponseEntity<List<Employee>> getAllemployee(){
		List<Employee> l =empService.getAllEmployee();
		return new ResponseEntity<List<Employee>>(l, HttpStatus.OK);

	}

	@PostMapping("add")
	public ResponseEntity<String> addemployee(@RequestBody Employee employee, UriComponentsBuilder builder) {
		boolean flag = empService.addEmployee(employee);
		if (flag == false) {
			return new ResponseEntity<String>("employee already exists",HttpStatus.CONFLICT);
		}

		return new ResponseEntity<String>("employee Inserted",HttpStatus.CREATED);
	}

	@PutMapping("update")
	public ResponseEntity<Employee> updateemployee(@RequestBody Employee employee) {
		empService.updateEmployee(employee);
		return new ResponseEntity<Employee>(employee, HttpStatus.OK);
	}
	@DeleteMapping("employee/{id}")
	public ResponseEntity<String> deleteemployee(@PathVariable("id") Integer id) {
		empService.deleteEmployee(id);
		return new ResponseEntity<String>("employee Deleted",HttpStatus.NO_CONTENT);
	}	
	

	
/*	@Get
	@Secured
	@Path("/id")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	
public UserAuh*/
	
	 @RequestMapping("/users")
	  public @ResponseBody String getUsers() {
	    return "{\"users\":[{\"firstname\":\"Richard\", \"lastname\":\"Feynman\"}," +
	           "{\"firstname\":\"Marie\",\"lastname\":\"Curie\"}]}";
	  }
	 
	 /*ControllerClass(AuthenticationManager authenticationManager) {
	        this.authenticationManager = authenticationManager;
	    }
	 
	 @GetMapping
	    ResponseEntity index(HttpServletRequest request, HttpSession session) {
	        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	        return new ResponseEntity<>(authentication.getPrincipal(), HttpStatus.OK);
	    }
	 
	 @PostMapping("/loginuser")
	 ResponseEntity login(@RequestBody UserDetails loginRequest) {
	     String username = loginRequest.getUsername();
	     String password = loginRequest.getPassword();
	     UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
	     Authentication authentication = this.authenticationManager.authenticate(token);
	     // vvv THIS vvv
	     SecurityContextHolder
	         .getContext()
	         .setAuthentication(authentication);
	     return new ResponseEntity<>(authentication.getPrincipal(), HttpStatus.OK);
	 }*/

	}
 



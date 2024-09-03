package com.suvasish.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.suvasish.Exception.OrderException;
import com.suvasish.Service.StudentService;
import com.suvasish.model.StudentOrder;

@Controller
public class StudentController {
	private StudentService studentService;
	@Autowired
	public StudentController(StudentService studentService) {
		this.studentService=studentService;
	}
	@GetMapping("/")
	public String init() {
		return "index";
	}
	@PostMapping(value = "/create-order" , produces = "application/json")
	@ResponseBody
	public ResponseEntity<StudentOrder> createOrder(@RequestBody StudentOrder studentOrder)
	throws OrderException,Exception{
		StudentOrder studentOrder2=studentService.createOrder(studentOrder);
		return new ResponseEntity<StudentOrder>(studentOrder2,HttpStatus.CREATED);
	}
}

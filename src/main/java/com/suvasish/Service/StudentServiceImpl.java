package com.suvasish.Service;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.suvasish.Exception.OrderException;
import com.suvasish.Repository.StudentOrderRepo;
import com.suvasish.model.StudentOrder;

@Service
public class StudentServiceImpl implements StudentService{
	//In Order to communicate with db,  we have to inject StudentOrderRepo object here.
	private StudentOrderRepo studentOrderRepo;
	@Autowired
	public StudentServiceImpl(StudentOrderRepo studentOrderRepo) {
		this.studentOrderRepo=studentOrderRepo;
	}
	@Value("${razorpay.api.key}")
	private String razorpayKey;
	@Value("${razorpay.api.secret}")
	private String razorpaySecret;
	private RazorpayClient razorpayClient;
	//whenever user will submit the form , then this method will be executed and 
	//one order will be created 
	@Override
	public StudentOrder createOrder(StudentOrder order) throws OrderException,Exception {
		JSONObject orderReq=new JSONObject();
		orderReq.put("amount", order.getAmount()*100);
		orderReq.put("currency", "INR");
		orderReq.put("receipt", order.getEmail());
		this.razorpayClient=new RazorpayClient(razorpayKey,razorpaySecret);
		//creating order in Razorpay
		Order razorpayOrder=razorpayClient.orders.create(orderReq);
		order.setRazorpayOrderId(razorpayOrder.get("id"));
		order.setOrderStatus(razorpayOrder.get("status"));
		studentOrderRepo.save(order);
		return order;
	}

}

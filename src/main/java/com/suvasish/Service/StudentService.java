package com.suvasish.Service;

import com.suvasish.Exception.OrderException;
import com.suvasish.model.StudentOrder;

public interface StudentService {
	public StudentOrder createOrder(StudentOrder order)throws OrderException,Exception;
}

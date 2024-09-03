package com.suvasish.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.suvasish.model.StudentOrder;

public interface StudentOrderRepo extends JpaRepository<StudentOrder, Integer>{

}

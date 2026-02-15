package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.Dept;

public interface DepartamentosRepository extends JpaRepository<Dept, Integer> {

	@Query("SELECT MAX(deptno) FROM Dept")
	Integer findMaxDeptNo();
	
}

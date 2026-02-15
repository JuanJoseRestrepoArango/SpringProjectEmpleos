package com.example.demo.service;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.demo.model.Emp;


public interface IEmpleados {
	List<Emp> buscarTodas();
	Emp buscarPorId(int empno);
	void guardar(Emp empleado);
	void eliminar(Integer empno);
	List<Emp> buscarByExample(Example<Emp> example);
	Page<Emp> bucarTodas(Pageable page);
	List<Emp> buscarPorFiltros(String nombre, Integer deptno);
}

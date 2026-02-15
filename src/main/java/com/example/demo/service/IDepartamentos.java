package com.example.demo.service;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.demo.model.Dept;

public interface IDepartamentos {
	 List<Dept> buscarTodas();
	 Dept buscarPorId(int deptno);
	 void guardar(Dept departamento);
	 void eliminar(Integer deptno);
	 List<Dept> buscarByExample(Example<Dept> example);
	 Page<Dept> bucarTodas(Pageable page);
}

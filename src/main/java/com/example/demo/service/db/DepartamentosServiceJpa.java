package com.example.demo.service.db;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.model.Dept;
import com.example.demo.repository.DepartamentosRepository;
import com.example.demo.service.IDepartamentos;

@Service
@Primary
public class DepartamentosServiceJpa implements IDepartamentos {
	
	@Autowired
	private DepartamentosRepository repo;

	@Override
	public List<Dept> buscarTodas() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	@Override
	public Dept buscarPorId(int deptno) {
		// TODO Auto-generated method stub
		Optional<Dept> opcional = repo.findById(deptno);
		if(opcional.isPresent()) return opcional.get();
		return null;
	}

	@Override
	public void guardar(Dept departamento) {
		// TODO Auto-generated method stub
		
		if(departamento.getDeptno() == null || departamento.getDeptno() == 0) {
			Integer maxDeptno = repo.findMaxDeptNo();
			
			if(maxDeptno == null) {
				departamento.setDeptno(10);
			}else {
				departamento.setDeptno(maxDeptno + 10);
			}
		}
		repo.save(departamento);
	}

	@Override
	public void eliminar(Integer deptno) {
		// TODO Auto-generated method stub
		repo.deleteById(deptno);
	}

	@Override
	public List<Dept> buscarByExample(Example<Dept> example) {
		// TODO Auto-generated method stub
		return repo.findAll(example);
	}

	@Override
	public Page<Dept> bucarTodas(Pageable page) {
		// TODO Auto-generated method stub
		return repo.findAll(page);
	}

}

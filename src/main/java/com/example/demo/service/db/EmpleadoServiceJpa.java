package com.example.demo.service.db;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.model.Emp;
import com.example.demo.repository.EmpleadoRepository;
import com.example.demo.service.IEmpleados;
@Service
@Primary
public class EmpleadoServiceJpa implements IEmpleados {
	@Autowired
	private EmpleadoRepository repo;
	@Override
	public List<Emp> buscarTodas() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	@Override
	public Emp buscarPorId(int empno) {
		// TODO Auto-generated method stub
		Optional<Emp> opcional=repo.findById(empno);
		if (opcional.isPresent())return opcional.get();
		return null;
	}

	@Override
	public void guardar(Emp empleado) {
		// TODO Auto-generated method stub
		repo.save(empleado);
	}

	@Override
	public void eliminar(Integer empno) {
		// TODO Auto-generated method stub
		repo.deleteById(empno);
	}

	@Override
	public List<Emp> buscarByExample(Example<Emp> example) {
		// TODO Auto-generated method stub
		return repo.findAll(example);
	}

	@Override
	public Page<Emp> bucarTodas(Pageable page) {
		// TODO Auto-generated method stub
		return repo.findAll(page);
	}

	@Override
	public List<Emp> buscarPorFiltros(String nombre, Integer deptno) {
		// TODO Auto-generated method stub
		return repo.buscarPorNombreYDepartamento(nombre, deptno);
	}

}

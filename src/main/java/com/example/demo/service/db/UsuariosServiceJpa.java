package com.example.demo.service.db;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Usuario;
import com.example.demo.repository.UsuariosRepository;
import com.example.demo.service.IUsuariosService;

@Service
public class UsuariosServiceJpa implements IUsuariosService {
	
	@Autowired
	private UsuariosRepository repo;
	@Override
	public void guardar(Usuario usuario) {
		// TODO Auto-generated method stub
		repo.save(usuario);
	}

	@Override
	public void eliminar(Integer idUsuario) {
		// TODO Auto-generated method stub
		repo.deleteById(idUsuario);
	}

	@Override
	public List<Usuario> buscarTodos() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	@Override
	public List<Usuario> buscarRegistrados() {
		// TODO Auto-generated method stub
		return repo.findByFechaRegistroNotNull();
	}

	@Override
	public Usuario buscarPorId(Integer idUsuario) {
		// TODO Auto-generated method stub
		Optional<Usuario> optional = repo.findById(idUsuario);
		if(optional.isPresent())return optional.get();
		return null;
	}

	@Override
	public Usuario buscarPorUsername(String username) {
		// TODO Auto-generated method stub
		return repo.findByUsername(username);
	}

	@Override
	@Transactional
	public int bloquear(int idUsuario) {
		// TODO Auto-generated method stub
		int rows = repo.lock(idUsuario);
		return rows;
	}

	@Override
	@Transactional
	public int activar(int idUsuario) {
		int rows = repo.unlock(idUsuario);
		return rows;
	}

}

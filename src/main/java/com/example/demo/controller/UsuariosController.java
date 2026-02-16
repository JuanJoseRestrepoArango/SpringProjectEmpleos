package com.example.demo.controller;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.service.IUsuariosService;

import org.springframework.ui.Model;

@Controller
@RequestMapping("/usuarios")
public class UsuariosController {
	
	@Autowired
	private IUsuariosService serviceUsuarios;
	
	@GetMapping("/index")
	public String mostrarIndex(Model model) {
		model.addAttribute("usuarios",serviceUsuarios.buscarRegistrados());
		return "usuarios/listUsuarios";
	}
	
	 @GetMapping("/delete/{id}")
	public String eliminar(@PathVariable("id") int idUsuario, RedirectAttributes attributes) {
		    	
		// Eliminamos el usuario
    	serviceUsuarios.eliminar(idUsuario);
			
		attributes.addFlashAttribute("msg", "El usuario fue eliminado!.");
		return "redirect:/usuarios/index";
	}
	    
    @GetMapping("/unlock/{id}")
	public String activar(@PathVariable("id") int idUsuario, RedirectAttributes attributes) {		
    	serviceUsuarios.activar(idUsuario);
		attributes.addFlashAttribute("msg", "El usuario fue activado y ahora tiene acceso al sistema.");		
		return "redirect:/usuarios/index";
	}
	    
	@GetMapping("/lock/{id}")
	public String bloquear(@PathVariable("id") int idUsuario, RedirectAttributes attributes) {		
		serviceUsuarios.bloquear(idUsuario);
		attributes.addFlashAttribute("msg", "El usuario fue bloqueado y no tendra acceso al sistema.");		
		return "redirect:/usuarios/index";
	}
}

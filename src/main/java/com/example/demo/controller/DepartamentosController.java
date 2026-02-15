package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.model.Dept;
import com.example.demo.service.IDepartamentos;
 

@Controller
@RequestMapping("/departamentos")
public class DepartamentosController {
	@Autowired
	private IDepartamentos service;
	 
	@GetMapping(value="/index")
	public String mostrarIndex(Model model) {
		List<Dept> lista=service.buscarTodas(); 
		model.addAttribute("departamentos",lista);
		return "departamentos/listDepartamentos";
	} 
	
	@RequestMapping(value="/create",method=RequestMethod.GET)
	public String crear(Dept dept,Model model) {
		return "departamentos/formDepartamento";
	}	 
	
	
	 
	@PostMapping("/save")
	public String guardar(Dept departamento,BindingResult result,RedirectAttributes attributes) {
		if (result.hasErrors()) {
			for(ObjectError error :result.getAllErrors()) {
				System.out.println("Ocurrio un error: "+error.getDefaultMessage());
			}
			return "departamentos/formDepartamento";
		}
		  
		service.guardar(departamento);
		attributes.addFlashAttribute("msg","Registro Guardado");
		System.out.println("Departamento: "+departamento);
		return "redirect:/departamentos/index";
	}
	
	@GetMapping("/view/{deptno}")
	public String verDetalle(@PathVariable("deptno") int deptno,Model model) {
		Dept d=service.buscarPorId(deptno);
		System.out.println("Departamento: "+d);
		model.addAttribute("dept",d);
		return "detalle";
	}
	@GetMapping(value="/edit/{deptno}")
	public String editar(@PathVariable("deptno") int deptno,Model model) {
		Dept d=service.buscarPorId(deptno);
		model.addAttribute("dept",d);
		
		return "departamentos/formDepartamento";
	}
	
	@GetMapping(value="/delete/{deptno}")
	public String eliminar(@PathVariable("deptno") int deptno,RedirectAttributes attributes) {
		System.out.println("Borrando vacante con deptno: "+deptno);
		service.eliminar(deptno); 
		attributes.addFlashAttribute("msg","El Departamento fue eliminada.");
		return "redirect:/departamentos/index";
	}
}

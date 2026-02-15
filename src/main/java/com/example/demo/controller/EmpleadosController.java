package com.example.demo.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.model.Emp;
import com.example.demo.service.IDepartamentos;
import com.example.demo.service.IEmpleados;


import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

@Controller
@RequestMapping("/empleados")
public class EmpleadosController {
	@Autowired 
	private IEmpleados serviceEmpleados;
	@Autowired 
	private IDepartamentos serviceDepartamentos; 
	
	@GetMapping(value="/index")
	public String mostrarIndex(Model model) {
		List<Emp> empleados = serviceEmpleados.buscarTodas();
		model.addAttribute("empleados",empleados); 
		return "empleados/listEmpleados";
	} 
	
	@RequestMapping(value="/create",method=RequestMethod.GET)
	public String crear(Emp emp, Model model) {
		model.addAttribute("departamentos",serviceDepartamentos.buscarTodas());
		return "empleados/formEmpleado";
	}
	
	@PostMapping("/save")
	public String guardar(Emp emp,
	BindingResult result,RedirectAttributes attributes) {
		if (result.hasErrors()) {
			for(ObjectError error :result.getAllErrors()) {
				System.out.println("Ocurrio un error: "+error.getDefaultMessage());
			}
			return "empleados/formEmpleado";
		}
		
		serviceEmpleados.guardar(emp);
		attributes.addFlashAttribute("msg","Registro Guardado");
		System.out.println("Empleado: "+emp);
		return "redirect:/empleados/index";
	}
	
	@GetMapping("/view/{empno}")
	public String verDetalle(@PathVariable("empno") int empno,Model model) {
		Emp e=serviceEmpleados.buscarPorId(empno);
		System.out.println("Empleado: "+e);
		model.addAttribute("emp",e);
		return "detalle";
	}
	@GetMapping(value="/delete/{empno}")
	public String eliminar(@PathVariable("empno") int empno,RedirectAttributes attributes) {
		System.out.println("Borrando empleado con empno: "+empno);
		serviceEmpleados.eliminar(empno);
		attributes.addFlashAttribute("msg","El Empleado fue eliminado.");
		return "redirect:/empleados/index";
	}
	@GetMapping(value="/edit/{empno}")
	public String editar(@PathVariable("empno") int empno,Model model) {
		Emp e=serviceEmpleados.buscarPorId(empno);
		model.addAttribute("emp",e);
		model.addAttribute("departamentos",serviceDepartamentos.buscarTodas());
		return "empleados/formEmpleado";
	}
	@GetMapping(value="/indexPaginate")
	public String mostrarIndexPaginado(Model model,Pageable page) {
		Page<Emp> lista=serviceEmpleados.bucarTodas(page);
		model.addAttribute("empleados",lista); 
		return "empleados/listEmpleados";
		
	}
	 
	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		SimpleDateFormat dateFormat=new SimpleDateFormat("dd-MM-yyyy");
		webDataBinder.registerCustomEditor(
				Date.class,new CustomDateEditor(dateFormat,false));
	}  
}

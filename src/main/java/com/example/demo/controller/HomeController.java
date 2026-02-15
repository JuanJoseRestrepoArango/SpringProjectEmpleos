package com.example.demo.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Emp;
import com.example.demo.service.IDepartamentos;
import com.example.demo.service.IEmpleados;

//import com.example.demo.model.Vacante;
//import com.example.demo.service.IVacantes;

@Controller
public class HomeController {

	@Autowired	
	private IEmpleados serviceEmpleados;
	
	@Autowired	
	private IDepartamentos serviceDepartamentos;
	  
	@GetMapping("/tabla")
	public String mostrarTabla(Model model){
		List<Emp> lista=serviceEmpleados.buscarTodas();
		model.addAttribute("empleados",lista); 
		
		return "tabla";
	}
 
	@GetMapping("/")
	public String mostrarHome(@RequestParam(required = false) String nombre,@RequestParam(required = false) Integer deptno,Model model) {
	    List<Emp> empleados = serviceEmpleados.buscarPorFiltros(nombre, deptno);

	    model.addAttribute("departamentos", serviceDepartamentos.buscarTodas());
	    model.addAttribute("empleados", empleados);

	    return "home";
	}
	
	
	@GetMapping("/view/{empno}")
	public String verDetalle(@PathVariable("empno") int empno,Model model) {
		Emp e=serviceEmpleados.buscarPorId(empno);
		System.out.println("Empleado: "+e);
		model.addAttribute("emp",e);
		return "detalle";
	}

	
		
}

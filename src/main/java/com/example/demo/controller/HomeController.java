package com.example.demo.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.model.Emp;
import com.example.demo.model.Rol;
import com.example.demo.model.Usuario; 
import com.example.demo.service.IDepartamentos;
import com.example.demo.service.IEmpleados;
import com.example.demo.service.IUsuariosService;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

//import com.example.demo.model.Vacante;
//import com.example.demo.service.IVacantes;

@Controller
public class HomeController {

	@Autowired	 
	private IEmpleados serviceEmpleados;
	 
	@Autowired	
	private IDepartamentos serviceDepartamentos;
	
	@Autowired	
	private IUsuariosService serviceUsuarios;
	  
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	
  
	@GetMapping("/")
	public String mostrarHome(@RequestParam(required = false) String nombre,@RequestParam(required = false) Integer deptno,Model model) {
		List<Emp> empleados = serviceEmpleados.buscarPorFiltros(nombre, deptno);
 
	    model.addAttribute("departamentos", serviceDepartamentos.buscarTodas());
	    model.addAttribute("empleados", empleados);
	    return "home";
	}
	  
	@GetMapping("/index")   
	public String mostrarIndex(Authentication authentication, HttpSession session) {		
		
		// Como el usuario ya ingreso, ya podemos agregar a la session el objeto usuario.
		String username = authentication.getName();		
		
		for(GrantedAuthority rol: authentication.getAuthorities()) {
			System.out.println("ROL: " + rol.getAuthority());
		}
		
		if (session.getAttribute("usuario") == null){
			Usuario usuario = serviceUsuarios.buscarPorUsername(username);
			usuario.setPassword(null);
			session.setAttribute("usuario", usuario);
		}
		
		return "redirect:/";
	}
	@GetMapping("/tabla")
	public String mostrarTabla(Model model){
		List<Emp> lista=serviceEmpleados.buscarTodas();
		model.addAttribute("empleados",lista); 
		
		return "tabla"; 
	}
	@GetMapping("/signup") 
	public String registrarse(Usuario usuario) {
		return "formRegistro";
	}  

     
	@PostMapping("/signup")
	public String guardarRegistro(Usuario usuario, RedirectAttributes attributes) {
		
		String pwdPlano = usuario.getPassword();
		String pwdEncriptado = passwordEncoder.encode(pwdPlano); 
		usuario.setPassword(pwdEncriptado);	
		usuario.setEstatus(1); 
		usuario.setFechaRegistro(new Date()); 

	
		Rol rol = new Rol();	
		rol.setId(3);
		usuario.agregar(rol);
		
		/**
		 * Guardamos el usuario en la base de datos. El Perfil se guarda automaticamente
		 */
		serviceUsuarios.guardar(usuario);
				
		attributes.addFlashAttribute("msg", "Has sido registrado. ¡Ahora puedes ingresar al sistema!");
		
		return "redirect:/login";
	}
	
	@GetMapping("/about")
	public String mostrarAcerca() {			
		return "acerca";
	}
	  
	@GetMapping("/login")
	public String mostrarLogin() {
		return "formLogin";
	}
	
	@GetMapping("/logout")
	public String logout(HttpServletRequest request) {
		SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
		logoutHandler.logout(request, null, null);
		return "redirect:/";
	}
	
	@GetMapping("/bcrypt/{texto}")
    @ResponseBody
   	public String encriptar(@PathVariable("texto") String texto) {    	
   		return texto + " Encriptado en Bcrypt: " + passwordEncoder.encode(texto);
   	}
	 /**
	 * InitBinder para Strings si los detecta vacios en el Data Binding los settea a NULL
	 * @param binder
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
	    binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}
    
	/**
	 * Metodo que agrega al modelo datos genéricos para todo el controlador
	 * @param model
	 
	@ModelAttribute
	public void setGenericos(Model model){
		Emp empSearch = new Emp();
		
		model.addAttribute("search", empSearch);
		model.addAttribute("empleados", serviceEmpleados.buscarDestacadas());	
		model.addAttribute("categorias", serviceCategorias.buscarTodas());	
	}
	
	*/
		

	
		
}

package com.example.demo.security;

import javax.sql.DataSource;

import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.*;
import org.springframework.security.web.*;

@Configuration
@EnableWebSecurity
public class DatabaseWebSecurity {
	
	@Bean
	public UserDetailsManager usersCustom(DataSource ds) {
		JdbcUserDetailsManager users = new JdbcUserDetailsManager(ds);
		users.setUsersByUsernameQuery("select username, password, estatus from Usuarios where username=?");
		users.setAuthoritiesByUsernameQuery("select u.username,r.rol from Usuario_rol ur " +
											"inner join Usuarios u on u.id = ur.idUsuario " +
											"inner join Roles r on r.id = ur.idRol " + "where u.username = ?");
		return users;
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http.authorizeHttpRequests(authorize -> authorize
				
				// Los recursos estáticos no requieren autenticación
				.requestMatchers("/bootstrap/**", "/images/**", "/tinymce/**", "/logos/**").permitAll()		
				
				// Las vistas públicas no requieren autenticación
				.requestMatchers("/", "/login", "/signup", "/search", "/bcrypt/**", "/about", "/vacantes/view/**").permitAll()
				 
				// Asignar permisos a URLs por ROLES
				
				.requestMatchers("/empleados/view/**").hasAnyAuthority("SUPERVISOR", "ADMINISTRADOR","USUARIO")
				.requestMatchers("/departamentos/**").hasAnyAuthority("SUPERVISOR", "ADMINISTRADOR")
				.requestMatchers("/empleados/**").hasAnyAuthority("SUPERVISOR", "ADMINISTRADOR")
				.requestMatchers("/usuarios/**").hasAnyAuthority("ADMINISTRADOR")
				// Todas las demás URLs de la Aplicación requieren autenticación
				.anyRequest().authenticated());
		
		// El formulario de Login no requiere autenticacion
		http.formLogin(form -> form.loginPage("/login").permitAll());
		
		return http.build();
				
	}
	
	/**
	 * Implementación de Spring Security que encripta passwords con el algoritmo
	 * Bcrypt
	 * 
	 * @return
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
}

package com.example.demo.model;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name="dept")
@NamedQuery(name="Dept.findAll", query="SELECT d FROM Dept d")
public class Dept implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	private Integer deptno;
	
	private String dname;
	
	private String loc;
	
	@OneToMany(mappedBy="deptno")
	private List<Emp> empleados;
	
	public Dept() {}

	public Integer getDeptno() {
		return deptno;
	}

	public void setDeptno(Integer deptno) {
		this.deptno = deptno;
	}

	public String getDname() {
		return dname;
	}

	public void setDname(String dname) {
		this.dname = dname;
	}

	public String getLoc() {
		return loc;
	}

	public void setLoc(String loc) {
		this.loc = loc;
	}

	public List<Emp> getEmpleados() {
		return empleados;
	}

	public void setEmpleados(List<Emp> empleados) {
		this.empleados = empleados;
	}
	
	
	
	
}

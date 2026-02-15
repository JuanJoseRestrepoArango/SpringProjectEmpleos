package com.example.demo.model;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.*;

@Entity
@Table(name="emp")
@NamedQuery(name="Emp.findAll",query="SELECT e FROM Emp e")
public class Emp implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer empno;
	
	private String ename;
	
	private String job;
	
	private Integer mgr;
	
	@Temporal(TemporalType.DATE)
	private Date hiredate;
	
	private Integer sal;
	
	private Integer comm;
	
	@ManyToOne
	@JoinColumn(name="deptno")
	private Dept deptno;
	
	public Emp() {
		
	}

	public Integer getEmpno() {
		return empno;
	}

	public void setEmpno(Integer empno) {
		this.empno = empno;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public Integer getMgr() {
		return mgr;
	}

	public void setMgr(Integer mgr) {
		this.mgr = mgr;
	}

	public Date getHiredate() {
		return hiredate;
	}

	public void setHiredate(Date hiredate) {
		this.hiredate = hiredate;
	}

	public Integer getSal() {
		return sal;
	}

	public void setSal(Integer sal) {
		this.sal = sal;
	}

	public Integer getComm() {
		return comm;
	}

	public void setComm(Integer comm) {
		this.comm = comm;
	}

	public Dept getDeptno() {
		return deptno;
	}

	public void setDeptno(Dept deptno) {
		this.deptno = deptno;
	}
	
	
	
	
	
	
	
	
}

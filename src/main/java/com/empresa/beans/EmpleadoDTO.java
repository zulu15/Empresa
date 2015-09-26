package com.empresa.beans;

import java.sql.Date;

public class EmpleadoDTO {

	private int empno;
	private String ename;
	private Date hiredate;
	private int deptno;



	// Constructores
	public EmpleadoDTO(int empno, String ename, Date hiredate, int deptno) {
		this.empno = empno;
		this.ename = ename;
		this.hiredate = hiredate;
		this.deptno=deptno;
	}

	public EmpleadoDTO() {
	}

	// Accesores
	public int getEmpno() {
		return empno;
	}

	public void setEmpno(int empno) {
		this.empno = empno;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public Date getHiredate() {
		return hiredate;
	}

	public void setHiredate(Date hiredate) {
		this.hiredate = hiredate;
	}
	
	public int getDeptno() {
		return deptno;
	}

	public void setDeptno(int deptno) {
		this.deptno = deptno;
	}
	// ToString
	@Override
	public String toString() {
		return "Empleado [empno=" + empno + ", ename=" + ename + ", hiredate=" + hiredate +", deptno ="+deptno+"]";
	}

}

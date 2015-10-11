package com.empresa.beans;

public class UsuarioDTO {

	private long id;
	private String nombre;
	private String password;
	private int acceso;

	@Override
	public String toString() {
		return "UsuarioDTO [id=" + id + ", nombre=" + nombre + ", password=" + password + ", acceso=" + acceso + "]";
	}

	// Constructores
	public UsuarioDTO() {
	}

	public UsuarioDTO(long id, String nombre, String password, int acceso) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.password = password;
		this.acceso = acceso;
	}

	// Accesores
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getAcceso() {
		return acceso;
	}

	public void setAcceso(int acceso) {
		this.acceso = acceso;
	}

}

package datos;

import java.util.HashSet;
import java.util.Set;

public class Rol {
	private int idRol;
	private String descripcion;
	private Set<PermisoAcceso> permisos;
	
	public Rol() {
		
	}

	public Rol(String descripcion) {
		super();
		this.idRol = 0;
		this.descripcion = descripcion;
		this.permisos = new HashSet<PermisoAcceso>();
	}

	public Rol(String descripcion, Set<PermisoAcceso> permisos) {
		super();
		this.idRol = 0;
		this.descripcion = descripcion;
		this.permisos = permisos;
	}
	
	public int getIdRol() {
		return idRol;
	}

	protected void setIdRol(int idRol) {
		this.idRol = idRol;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Set<PermisoAcceso> getPermisos() {
		return permisos;
	}

	public void setPermisos(Set<PermisoAcceso> permisos) {
		this.permisos = permisos;
	}
	
	public void agregarPermiso(PermisoAcceso pa) {
		this.permisos.add(pa);
	}
}

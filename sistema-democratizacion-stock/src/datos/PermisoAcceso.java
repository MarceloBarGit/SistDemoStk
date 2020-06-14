package datos;

public class PermisoAcceso {
	private int idPermisoAcceso; // PK 1
	private String descripcion;  //    Control Total - Gestión Total -  Autorización - Autenticación - Creación - Búsqueda - Lectura - Escritura - Eliminar 
	
//-------------------------------------
//Constructores	
	public PermisoAcceso() {}

	public PermisoAcceso(int idPermisoAcceso, String descripcion) {
		super();
		this.idPermisoAcceso = idPermisoAcceso;
		this.descripcion = descripcion;
	}
	
//-------------------------------------
//Getter y Setter
	public int getIdPermisoAcceso() {
		return idPermisoAcceso;
	}
	protected void setIdPermisoAcceso(int idPermisoAcceso) {
		this.idPermisoAcceso = idPermisoAcceso;
	}
	//-------------
	
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

//-------------------------------------
//tostring
	@Override
	public String toString() {
		return "Permisos de Acceso: ID [ " + idPermisoAcceso + " ] Descripcion: " + descripcion;
	}
	

	
//--------------
}//Fin PermisoAcceso

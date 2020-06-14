package datos;

public class Contacto {
	private int idContacto;
	private String telefono;
	private String domicilio;
	
	public Contacto() {
		
	}

	public Contacto(String telefono, String domicilio) {
	
		this.telefono = telefono;
		this.domicilio = domicilio;
	}

	public int getIdContacto() {
		return idContacto;
	}

	protected void setIdContacto(int idContacto) {
		this.idContacto = idContacto;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}
	
	
}

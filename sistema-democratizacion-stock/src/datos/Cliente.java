package datos;

import java.time.LocalDate;

public class Cliente extends Persona {
	private int idCliente;
	private String mailCliente;
	private String cuitCliente;
	
	public Cliente() {
		
	}

	
	public Cliente(String dni, String nombre, String apellido, LocalDate fechaNacimiento, Contacto contacto, String mailCliente, String cuitCliente) {
		super(dni, nombre, apellido, fechaNacimiento, contacto);
		this.mailCliente = mailCliente;
		this.cuitCliente = cuitCliente;
	}
	

	public int getIdCliente() {
		return idCliente;
	}

	protected void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public String getMailCliente() {
		return mailCliente;
	}

	public void setMailCliente(String mailCliente) {
		this.mailCliente = mailCliente;
	}

	public String getCuitCliente() {
		return cuitCliente;
	}

	public void setCuitCliente(String cuitCliente) {
		this.cuitCliente = cuitCliente;
	}
}

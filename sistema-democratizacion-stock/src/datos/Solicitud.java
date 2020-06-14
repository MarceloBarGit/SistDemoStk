package datos;

import java.time.LocalDate;
import java.util.Set;

public class Solicitud {
	private int idSolicitud;
	private String estado; // TODO esto deberia ser un select field o enum o como se llame en java. (abierta, completa, cerrada)
	private Local localSolicitante;
	private Local localSolicitado;
	private Empleado empleadoSolicitante;
	private Empleado empleadoSolicitado;
	private Set<LineaSolicitud> lineasSolicitud;
	private LocalDate fechaSolicitud;
	
	public Solicitud() {
		
	}

	public Solicitud(int idSolicitud, String estado, Local localSolicitante, Local localSolicitado,
			Empleado empleadoSolicitante, Empleado empleadoSolicitado, Set<LineaSolicitud> lineasSolicitud,
			LocalDate fechaSolicitud) {
		super();
		this.idSolicitud = idSolicitud;
		this.estado = estado;
		this.localSolicitante = localSolicitante;
		this.localSolicitado = localSolicitado;
		this.empleadoSolicitante = empleadoSolicitante;
		this.empleadoSolicitado = empleadoSolicitado;
		this.lineasSolicitud = lineasSolicitud;
		this.fechaSolicitud = fechaSolicitud;
	}

	public int getIdSolicitud() {
		return idSolicitud;
	}

	protected void setIdSolicitud(int idSolicitud) {
		this.idSolicitud = idSolicitud;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Local getLocalSolicitante() {
		return localSolicitante;
	}

	public void setLocalSolicitante(Local localSolicitante) {
		this.localSolicitante = localSolicitante;
	}

	public Local getLocalSolicitado() {
		return localSolicitado;
	}

	public void setLocalSolicitado(Local localSolicitado) {
		this.localSolicitado = localSolicitado;
	}

	public Empleado getEmpleadoSolicitante() {
		return empleadoSolicitante;
	}

	public void setEmpleadoSolicitante(Empleado empleadoSolicitante) {
		this.empleadoSolicitante = empleadoSolicitante;
	}

	public Empleado getEmpleadoSolicitado() {
		return empleadoSolicitado;
	}

	public void setEmpleadoSolicitado(Empleado empleadoSolicitado) {
		this.empleadoSolicitado = empleadoSolicitado;
	}

	public Set<LineaSolicitud> getLineasSolicitud() {
		return lineasSolicitud;
	}

	public void setLineasSolicitud(Set<LineaSolicitud> lineasSolicitud) {
		this.lineasSolicitud = lineasSolicitud;
	}

	public LocalDate getFechaSolicitud() {
		return fechaSolicitud;
	}

	public void setFechaSolicitud(LocalDate fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
	}
}

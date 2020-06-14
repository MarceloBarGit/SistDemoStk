package datos;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Empleado extends Persona {
	private int idEmpleado;
	private String username;
	private String password;
	private String legajo;
	private FranjaHoraria franjaHoraria;
	private float sueldoBase;
	private Rol rol;
	private Set<Solicitud> solicitudesRealizadas; // estas son las solicitudes que el empleado realizo a otro local.
	private Set<Solicitud> solicitudesAprobadas; // estas son las solicitudes que el empleado aprobo.
	private Set<Factura> facturas;
	
	public Empleado() {
		super();
	}

	public Empleado(String dni, String nombre, String apellido, LocalDate fechaNacimiento, Contacto contacto,
			String username, String password, String legajo, FranjaHoraria franjaHoraria,
			float sueldoBase, Rol rol, Set<Solicitud> solicitudesRealizadas, Set<Solicitud> solicitudesAprobadas, Set<Factura> facturas) {
		super(dni, nombre, apellido, fechaNacimiento, contacto);
		this.username = username;
		this.password = password;
		this.legajo = legajo;
		this.franjaHoraria = franjaHoraria;
		this.sueldoBase = sueldoBase;
		this.rol = rol;
		this.solicitudesRealizadas = solicitudesRealizadas;
		this.solicitudesAprobadas = solicitudesAprobadas;
		this.facturas = facturas;
	}

	public int getIdEmpleado() {
		return idEmpleado;
	}

	protected void setIdEmpleado(int idEmpleado) {
		this.idEmpleado = idEmpleado;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLegajo() {
		return legajo;
	}

	public void setLegajo(String legajo) {
		this.legajo = legajo;
	}

	public FranjaHoraria getFranjaHoraria() {
		return franjaHoraria;
	}

	public void setFranjaHoraria(FranjaHoraria franjaHoraria) {
		this.franjaHoraria = franjaHoraria;
	}

	public float getSueldoBase() {
		return sueldoBase;
	}

	public void setSueldoBase(float sueldoBase) {
		this.sueldoBase = sueldoBase;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	public Set<Solicitud> getSolicitudesRealizadas() {
		return solicitudesRealizadas;
	}

	public void setSolicitudesRealizadas(Set<Solicitud> solicitudesRealizadas) {
		this.solicitudesRealizadas = solicitudesRealizadas;
	}
	
	public Set<Solicitud> getSolicitudesAprobadas() {
		return solicitudesAprobadas;
	}

	public void setSolicitudesAprobadas(Set<Solicitud> solicitudesAprobadas) {
		this.solicitudesAprobadas = solicitudesAprobadas;
	}

	public Set<Factura> getFacturas() {
		return facturas;
	}

	public void setFacturas(Set<Factura> facturas) {
		this.facturas = facturas;
	}
	
	public float calcularSueldoMes(int mes) {
		// El mes llega en formato 1 -12
		List<Factura> facturasMes = this.facturas.stream().filter(s -> s.getFechaFactura().getMonthValue() == mes).collect(Collectors.toList()); // Filtro las facturas dejando solo las del mes
		float sueldo = this.sueldoBase;
		for (Factura f : facturasMes) {
			for (LineaFactura lf: f.getLineasFactura()) {
				sueldo += lf.getTotal() * 0.05; // la linea ya tiene su total = cantidad * precioUnitario
			}
		}
		// FIXME en los filtros de solicitud deberia filtrar por estado = completado
		List<Solicitud> solicitudesRealizadas = this.solicitudesRealizadas.stream().filter(s -> s.getFechaSolicitud().getMonthValue() == mes).collect(Collectors.toList());
		List<Solicitud> solicitudesAprobadas = this.solicitudesAprobadas.stream().filter(s -> s.getFechaSolicitud().getMonthValue() == mes).collect(Collectors.toList());
		for (Solicitud s : solicitudesRealizadas) for (LineaSolicitud ls : s.getLineasSolicitud()) sueldo += ls.getTotal() * 0.03;
		for (Solicitud s : solicitudesAprobadas) for (LineaSolicitud ls : s.getLineasSolicitud()) sueldo += ls.getTotal() * 0.02;
		return sueldo;
	}
}

package negocio;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import dao.PersonaDao;
import datos.Cliente;
import datos.Contacto;
import datos.Empleado;
import datos.Factura;
import datos.FranjaHoraria;
//import datos.Persona;
import datos.Rol;
import datos.Solicitud;

public class PersonaABM {

	private static PersonaABM instancia = null; // Patrón Singleton

	protected PersonaABM() {
	}

	public static PersonaABM getInstance() {
		if (instancia == null)
			instancia = new PersonaABM();
		return instancia;
	}

	/////////////////// CLIENTES ////////////////////////
	public Cliente traerCliente(int idCliente) {
		return PersonaDao.getInstance().traerCliente(idCliente);
	}
	
	public Cliente traerCliente(String dni) {
		return PersonaDao.getInstance().traerCliente(dni);
	}

	public List<Cliente> traeClientes() {
		return PersonaDao.getInstance().traerClientes();
	}
	
	public int agregarCliente(String dni, String nombre, String apellido, LocalDate fechaNacimiento, Contacto contacto,
			String mailCliente, String cuitCliente) {
		int salida = 0;
		Cliente cliente = traerCliente(dni);
		if (cliente == null) {
			salida = PersonaDao.getInstance().agregarCliente(
					new Cliente(dni, nombre, apellido, fechaNacimiento, contacto, mailCliente, cuitCliente));
		} else {
// Depende de lo que pida, si tirar excepción o dar mensaje de error.
		}
		return salida;
	}
	
	public void actualizarCliente(Cliente cliente) {
		Cliente cli = traerCliente(cliente.getIdCliente());
		if (cli != null) {
			PersonaDao.getInstance().actualizarCliente(cliente);
		} else {
// Depende de lo que pida, si tirar excepción o dar mensaje de error.
		}
		return;
	}

	public void eliminarCliente(int idCliente) {
		Cliente cli = traerCliente(idCliente);
		if (cli != null) {
			PersonaDao.getInstance().eliminarCliente(cli);
		} else {
// Depende de lo que pida, si tirar excepción o dar mensaje de error.
		}
		return;
	}

	

/////////////////// EMPLEADOS ////////////////////////
	public Empleado traerEmpleado(int idEmpleado) {
		return PersonaDao.getInstance().traerEmpleado(idEmpleado);
	}

	public Empleado traerEmpleado(String legajo) {
		return PersonaDao.getInstance().traerEmpleado(legajo);
	}

	public List<Empleado> traeEmpleados() {
		return PersonaDao.getInstance().traerEmpleados();
	}
	
	
	
	public int agregarEmpleado(String dni, String nombre, String apellido, LocalDate fechaNacimiento, Contacto contacto,
			String username, String password, String legajo, FranjaHoraria franjaHoraria,
			float sueldoBase, Rol rol, Set<Solicitud> solicitudesRealizadas, Set<Solicitud> solicitudesAprobadas, Set<Factura> facturas) {
		int salida = 0;
		Empleado empleado = traerEmpleado(legajo);
		if (empleado == null) {
			salida = PersonaDao.getInstance().agregarEmpleado(
					new Empleado(dni, nombre, apellido, fechaNacimiento, contacto,	username, password, legajo, franjaHoraria,	sueldoBase, rol, solicitudesRealizadas, solicitudesAprobadas, facturas));
		} else {
			// Depende de lo que pida, si tirar excepción o dar mensaje de error.
		}
		return salida;
	}

	public void actualizarEmpleado(Empleado empleado) {
		Empleado emp = traerEmpleado(empleado.getIdEmpleado());
		if (emp != null) {
			PersonaDao.getInstance().actualizarEmpleado(empleado);
		} else {
			// Depende de lo que pida, si tirar excepción o dar mensaje de error.
		}
		return;
	}

	public void eliminarEmpleado(int idEmpleado) {
		Empleado emp = traerEmpleado(idEmpleado);
		if (emp != null) {
			PersonaDao.getInstance().eliminarEmpleado(emp);
		} else {
			// Depende de lo que pida, si tirar excepción o dar mensaje de error.
		}
		return;
	}

}
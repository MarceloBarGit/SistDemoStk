package negocio;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import dao.FacturaDao;
import dao.LocalDao;
import dao.LoteDao;
import datos.Empleado;
import datos.Factura;
import datos.Local;
import datos.Lote;
import datos.Producto;
import datos.Solicitud;
import datos.Ubicacion;

public class LocalABM {
	
	private LocalDao daoLocal = new LocalDao();       // para el ABM
	private LoteDao daoLote = new LoteDao();           // para el eliminar
	private FacturaDao daoFactura = new FacturaDao();  // para el eliminar

//------
	public int agregarLocal(String nombre, Ubicacion ubicacion, 
			Set<Empleado> empleados, 
			Set<Factura> facturas, 
			Set<Solicitud> solicitudesEntrantes,
			Set<Solicitud> solicitudesSalientes, 
			Set<Lote> lotes) {
		int salida = 0;
		Local l = daoLocal.traer(nombre);
		if (l == null) {
			//salida = daoLocal.agregar(new Local(idLocal,nombre, Ubicacion, empleados, 
			//	facturas, solicitudesEntrantes,
			//	solicitudesSalientes, lotes));
		} else {
			// Depende de lo que pida, si tirar excepción 
			// "El Local con el ID: " + idLocal + " YA existe."
		}
		return salida;
	}
//--------------------------------------------------------
	public void modificarLocal(Local l) { // throws HibernateException {
		// boolean salida = false;
		Local lo = daoLocal.traer(l.getIdLocal());
		if (lo != null) {
			daoLocal.actualizar(l);
			// salida = true;
		} else {
			// Depende de lo que pida, si tirar excepción 
			// "El Local buscado: " + l + " No existe."		
		}
		//return salida;
		return;
	}

//--------------------------------------------------------
	public boolean eliminarLocal(Local l) {
		boolean salida = false;
		Local lo = daoLocal.traer(l.getIdLocal());
		if(lo != null) {
			
			List<Lote> listaLotes = daoLote.traerLotesDelProducto(l.getIdLocal());
			List<Factura> listaLineaFactura= (List<Factura>) daoFactura.traerFactura(l.getIdLocal());
			// Si TODAS las Listas estan Vacias, se puede ELIMNAR el LOCAL sin problema.
			if(listaLotes.isEmpty() && listaLineaFactura.isEmpty()) { //&& listaLineaSolicitud.isEmpty()) {
				daoLocal.eliminar(lo);
				salida = true;
				// "El Local" + l + " ha sido Eliminado correctamente."
			}else {
				// "El Local" + l + " NO ha sido Eliminado por tener sus dependencias."
			}
		}else {
			// "No existe el Local " + l + "a Eliminar"
		}
		return salida;
	}


//--------------------------------------------------------	
	public int traerStockProducto(Local l, Producto p) {
		return 0;
	}
	
	public boolean reducirStock(Local l, Producto p, int cantidad) {
		// TODO la cantidad siempre sera positiva ya que es lo q resta.
		return false;
	}
	
	public boolean aumentarStock(Local l, Lote lote) {
		return false;
	}
	
	public void reporteProductosVendidos(Local l, LocalDate fechaInicio, LocalDate fechaFin) {
	}
	
	public Producto traerProductoMasVendido(Local l) {
		return null;
	}
	
	public Local traerLocalConStock(Producto p, int cantidad) {
		return null;
	}
	
	public Local inicializarUbicacion(Local l) {
		return l;
	}
	
	public Local inicializarEmpleados(Local l) {
		return l;
	}
	
	public Local inicializarFacturas(Local l) {
		return l;
	}
	
	public Local inicializarSolicitudesEntrantes(Local l) {
		return l;
	}
	
	public Local inicializarSolicitudesSalientes(Local l) {
		return l;
	}
	
	public Local inicializarLotes(Local l) {
		return l;
	}
}
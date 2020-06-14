package negocio;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import dao.FacturaDao;
import datos.Cliente;
import datos.Empleado;
import datos.Factura;
import datos.Local;
import datos.Producto;
//--------
import datos.Solicitud;


public class FacturaABM {
	
	// INSTANCIAS de la capa DAO
	private FacturaDao daoFact = new FacturaDao();


	// AGREGAR   ---> como trabajo cuando hay campos del tipo Empleado empleado ?
	//--------------------
	// public boolean agregarFactura(Factura f) {
	// 	return false;
	// }
	public int agregarFactura(LocalDate fechaFactura, 
			String nroDocumento, float total, 
			Empleado vendedor, 
			Cliente cliente, 
			Local local, 
			Solicitud solicitudAsociada) {
		int salida = 0;
		int idFactura = 0;
		Factura f = traerFactura(idFactura);
		if (f == null) {
			salida = daoFact.agregar(new Factura(idFactura, fechaFactura, nroDocumento, 
				total, vendedor, cliente, local, solicitudAsociada));
		} else {
			// Depende de lo que pida, si tirar excepción
			// "La Factura con con la descripción: " + nombre + " YA existe."
		}
		return salida;
	}

	// MODIFICAR
	//--------------------	
	// public boolean modificarFactura(Factura f) {
	// 	return false;
	// }
	public void modificarFactura(Factura f) {
		Factura fac = daoFact.traerFactura(f.getIdFactura());
		if(fac != null) {
			daoFact.actualizar(f);
			// "La Factura con Nro.: " + f + " ha sido modificado correctamente."
		}
		else {
			// Depende de lo que pida, si tirar excepción 
			// "La Factura: " + f + " No existe."	
		}
		return;
		
	}


	// ELIMINAR (Fisica con Dependencia)
	//--------------------
	public boolean eliminarFactura(Factura f) {
		boolean salida = false;
		Factura fa = daoFact.traerFactura(f.getIdFactura());
		if(fa != null) {
			
			// List<Lote> listaLotes = daoLote.traerLotesDelProducto(p.getIdProducto());
			// List<LineaFactura> listaLineaFactura= daoLineaFactura.traerLineaFacturaDelProducto(p.getIdProducto());
			// List<LineaSolicitud> listaLineaSolicitud= daoLineaSolicitud.traerLineaSolicitudDelProducto(p.getIdProducto());
			
				// if(listaLotes.isEmpty() && listaLineaFactura.isEmpty() && listaLineaSolicitud.isEmpty()) {
				daoFact.eliminar(f);
				salida = true;
				// "La Factura " + f + " ha sido Eliminada correctamente."
			// }else {
				// "La Factura " + f + " NO ha sido Eliminada por tener sus dependencias."
			// }
			
		}else {
			// "No existe la Factura " + f + " a Eliminar"
		}
		return salida;
	}

	// TRAER
	//------------------
	public Factura traerFactura(int idFactura) {
		return daoFact.traerFactura(idFactura);
	}
	
	// LISTADO general
	//-------------------
	public List<Factura> traerFactura() {
		return daoFact.traerFactura();
	}



	//------------------------------------------
	public void generarFactura(Factura f) {
	}
	
	public Factura inicializarLineasFactura(Factura f) {
		return f;
	}
	
	public Factura inicializarVendedor(Factura f) {
		return f;
	}
	
	public Factura inicializarCliente(Factura f) {
		return f;
	}
	
	public Factura inicializarLocal(Factura f) {
		return f;
	}
	
	public Factura inicializarSolicitudAsociada(Factura f) {
		return f;
	}
}
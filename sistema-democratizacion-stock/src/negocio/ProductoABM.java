package negocio;

import java.util.List;

import dao.LineaFacturaDao;
import dao.LineaSolicitudDao;
import dao.LoteDao;
import dao.ProductoDao;
import datos.LineaFactura;
import datos.LineaSolicitud;
import datos.Lote;
import datos.Producto;

public class ProductoABM {

	private ProductoDao dao = new ProductoDao();
	private LoteDao daoLote = new LoteDao();
	private LineaFacturaDao daoLineaFactura = new LineaFacturaDao();
	private LineaSolicitudDao daoLineaSolicitud = new LineaSolicitudDao();
	
	public int agregarProducto(String codProducto, String nombre, String descripcion, 
			boolean habilitado, float precioUnitario) throws Exception {
		int salida = 0;
		// si existe arrojar la Excepcion
		if(dao.traerProducto(nombre) != null) throw new Exception(" El Producto:  [" + nombre + "]  ya existe.");
		
			salida = dao.agregar(new Producto(codProducto, nombre, descripcion, habilitado, precioUnitario));

		return salida;
	}

//	public boolean agregarProducto(Producto p) {
//		return false;
//	}

	public void modificarProducto(Producto p) throws Exception {
		 //implementar que no exista un Prestamo
		if(dao.traerProducto(p.getIdProducto())==null) throw new Exception("ERROR: No se encuentra ese Producto");

			dao.actualizar(p);
		return;		
	}

	public boolean eliminarProducto(Producto p) throws Exception {
		boolean salida = false;

		if(dao.traerProducto(p.getIdProducto()) !=null) throw new Exception("ERROR: No se encuentra ese Producto");
			// Busco las dependencias
			List<Lote> listaLotes = daoLote.traerLotesDelProducto(p.getIdProducto());
			List<LineaFactura> listaLineaFactura= daoLineaFactura.traerLineaFacturaDelProducto(p.getIdProducto());
			List<LineaSolicitud> listaLineaSolicitud= daoLineaSolicitud.traerLineaSolicitudDelProducto(p.getIdProducto());
			if(listaLotes.isEmpty() && listaLineaFactura.isEmpty() && listaLineaSolicitud.isEmpty()) {
				dao.eliminar(p);
				salida = true;
				// me faltaria un msj: "No se puede eliminar porque tiene dependencias" 
			}
		return salida;
	}

	public boolean bajaProducto(Producto p) {
		
		boolean salida = false;
		Producto prod = dao.traerProducto(p.getIdProducto());
		if(prod!=null) {
			prod.setHabilitado(false);
			dao.actualizar(prod);
			salida = true;
		}
		else {
			// Depende de lo que pida, si tirar excepción o dar mensaje de error.
		}
		return salida;
	}

	public Producto traerProducto(int idProducto) {

		return dao.traerProducto(idProducto);
	}

	public Producto traerProducto(String nombre) {
		return dao.traerProducto(nombre);
	}

	public List<Producto> traerProductos() {
		return dao.traerProductos();
	}
}

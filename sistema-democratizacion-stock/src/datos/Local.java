package datos;

import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

public class Local {
	private int idLocal;
	private String nombre;
	private Ubicacion ubicacion;
	private Set<Empleado> empleados;
	private Set<Factura> facturas;
	private Set<Solicitud> solicitudesEntrantes;
	private Set<Solicitud> solicitudesSalientes;
	private Set<Lote> lotes;
	
	public Local() {
		
	}
	
	public Local(int idLocal, String nombre, Ubicacion ubicacion, Set<Empleado> empleados, Set<Factura> facturas, Set<Solicitud> solicitudesEntrantes,
			Set<Solicitud> solicitudesSalientes, Set<Lote> lotes) {
		super();
		this.idLocal = idLocal;
		this.nombre = nombre;
		this.ubicacion = ubicacion;
		this.empleados = empleados;
		this.facturas = facturas;
		this.solicitudesEntrantes = solicitudesEntrantes;
		this.solicitudesSalientes = solicitudesSalientes;
		this.lotes = lotes;
	}

	public int getIdLocal() {
		return idLocal;
	}

	protected void setIdLocal(int idLocal) {
		this.idLocal = idLocal;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Ubicacion getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(Ubicacion ubicacion) {
		this.ubicacion = ubicacion;
	}

	public Set<Empleado> getEmpleados() {
		return empleados;
	}

	public void setEmpleados(Set<Empleado> empleados) {
		this.empleados = empleados;
	}

	public Set<Factura> getFacturas() {
		return facturas;
	}

	public void setFacturas(Set<Factura> facturas) {
		this.facturas = facturas;
	}

	public Set<Solicitud> getSolicitudesEntrantes() {
		return solicitudesEntrantes;
	}

	public void setSolicitudesEntrantes(Set<Solicitud> solicitudesEntrantes) {
		this.solicitudesEntrantes = solicitudesEntrantes;
	}

	public Set<Solicitud> getSolicitudesSalientes() {
		return solicitudesSalientes;
	}

	public void setSolicitudesSalientes(Set<Solicitud> solicitudesSalientes) {
		this.solicitudesSalientes = solicitudesSalientes;
	}
	
	public Set<Lote> getLotes() {
		return lotes;
	}

	public void setLotes(Set<Lote> lotes) {
		this.lotes = lotes;
	}
	
	public int traerStockProducto(Producto p) {
		int acumulador = 0;
		List<Lote> lotesPosibles = this.lotes.stream().filter(s -> s.getProducto() == p).collect(Collectors.toList()); // Filtro la lista dejando solo los lotes del producto
		for (Lote l : lotesPosibles) acumulador += l.getCantidadDisponible();
		return acumulador;
	}
	
	public boolean reducirStock(Producto p, int cantidad) {
		// Retorna true si se puede completar totalmente false si no alcanza el stock. Por ahi es mejor retornar cuanto falta satisfacer (0 si alcanzo o n si faltaron n unidades)
		// FIXME ver de agregar excepciones para esto
		boolean retorno = false;
		if (this.traerStockProducto(p) >= cantidad) {
			List<Lote> lotesPosibles = this.lotes.stream().filter(s -> s.getProducto() == p).collect(Collectors.toList()); // Filtro la lista dejando solo los lotes del producto
			Collections.sort(lotesPosibles, 
					new Comparator<Lote>() {
						public int compare(Lote l1, Lote l2) {
							return l1.getFechaIngreso().compareTo(l2.getFechaIngreso());
						}
			});
			if (!lotesPosibles.isEmpty()) { 				// La lista quedo ordenada por fecha, por lo que agarro el primero
				int faltante = cantidad;
				for (Lote l : lotesPosibles) {
					if (faltante > 0) {
						this.lotes.remove(l);				// Lo saco del set pq no se como actualizarlo
						faltante = (l.getCantidadDisponible() > faltante) ? 0 : faltante - l.getCantidadDisponible();
						l.setCantidadDisponible(l.getCantidadDisponible() - faltante);
						this.lotes.add(l); 					// Lo vuelvo a poner en el set
						retorno = faltante == 0;
					}
				}
			}
		}
		else {
			// TODO ver que hacemos si el stock no alcanza para satisfacerlo todo, sino solo una parte
		}
		return retorno;
	}
	
	public boolean aumentarStock(Lote lote) {
		return this.lotes.add(lote);
	}
	
	public Producto traerProductoMasVendido() {
		// Retorna el producto mas vendido o null si no se vendio ningun producto
		Hashtable<Producto, Integer> productosVendidos = new Hashtable<Producto, Integer>();
		for (Factura f : this.facturas) {
			for (LineaFactura lf : f.getLineasFactura()) {
				if (productosVendidos.containsKey(lf.getProducto())) {
					productosVendidos.put(lf.getProducto(), productosVendidos.get(lf.getProducto()) + lf.getCantidad());
				}
				else {
					productosVendidos.put(lf.getProducto(), lf.getCantidad());
				}
			}
		}
		Producto prodMasVendido = null;
		int cantidad = 0;
		for (Entry<Producto, Integer> p : productosVendidos.entrySet()) {
			if (prodMasVendido == null) {
				prodMasVendido = p.getKey();
				cantidad = p.getValue();
			}
			if (p.getValue() > cantidad) {
				prodMasVendido = p.getKey();
				cantidad = p.getValue();
			}
		}
		return prodMasVendido;
	}
} 

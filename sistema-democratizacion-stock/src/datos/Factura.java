package datos;

import java.time.LocalDate;
import java.util.Set;

public class Factura {
	private int idFactura;
	private LocalDate fechaFactura;
	private String nroDocumento;
	private float total;
	private Set<LineaFactura> lineasFactura;
	private Empleado vendedor;
	private Cliente cliente;
	private Local local;
	private Solicitud solicitudAsociada;
	
	public Factura() {}

	public Factura(int idFactura, LocalDate fechaFactura, String nroDocumento, float total, Empleado vendedor, Cliente cliente, Local local,
			Solicitud solicitudAsociada) {
		super();
		this.idFactura = idFactura;
		this.fechaFactura = fechaFactura;
		this.nroDocumento = nroDocumento;
		this.total = total;
		this.vendedor = vendedor;
		this.cliente = cliente;
		this.local = local;
		this.solicitudAsociada = solicitudAsociada;
	}

	public int getIdFactura() {
		return idFactura;
	}

	protected void setIdFactura(int idFactura) {
		this.idFactura = idFactura;
	}

	public String getNroDocumento() {
		return nroDocumento;
	}

	public void setNroDocumento(String nroDocumento) {
		this.nroDocumento = nroDocumento;
	}

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	public Set<LineaFactura> getLineasFactura() {
		return lineasFactura;
	}

	public void setLineasFactura(Set<LineaFactura> lineasFactura) {
		this.lineasFactura = lineasFactura;
	}

	public Empleado getVendedor() {
		return vendedor;
	}

	public void setVendedor(Empleado vendedor) {
		this.vendedor = vendedor;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Local getLocal() {
		return local;
	}

	public void setLocal(Local local) {
		this.local = local;
	}

	public Solicitud getSolicitudAsociada() {
		return solicitudAsociada;
	}

	public void setSolicitudAsociada(Solicitud solicitudAsociada) {
		this.solicitudAsociada = solicitudAsociada;
	}

	public LocalDate getFechaFactura() {
		return fechaFactura;
	}

	public void setFechaFactura(LocalDate fechaFactura) {
		this.fechaFactura = fechaFactura;
	}
}

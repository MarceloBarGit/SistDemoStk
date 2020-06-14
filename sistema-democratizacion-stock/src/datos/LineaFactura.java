package datos;

public class LineaFactura {
	private int idLineaFactura;
	private int cantidad;
	private float total;
	private Producto producto;
	
	public LineaFactura() {
		
	}

	public LineaFactura(int idLineaFactura, int cantidad, float total, Producto producto) {
		super();
		this.idLineaFactura = idLineaFactura;
		this.cantidad = cantidad;
		this.total = total;
		this.producto = producto;
	}

	public int getIdLineaFactura() {
		return idLineaFactura;
	}

	protected void setIdLineaFactura(int idLineaFactura) {
		this.idLineaFactura = idLineaFactura;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
		this.setTotal();
	}

	public float getTotal() {
		this.setTotal();
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}
	
	public void setTotal() {
		this.total = this.producto.getPrecioUnitario() * this.cantidad;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
		this.setTotal();
	}
	
	
}

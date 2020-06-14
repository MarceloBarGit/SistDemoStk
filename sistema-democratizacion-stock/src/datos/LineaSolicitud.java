package datos;

public class LineaSolicitud {
	private int idSolicitud;
	private int cantidad;
	private float total;
	private Producto producto;
	
	public LineaSolicitud() {
		
	}

	public LineaSolicitud(int idSolicitud, int cantidad, Producto producto) {
		super();
		this.idSolicitud = idSolicitud;
		this.cantidad = cantidad;
		this.producto = producto;
		this.total = this.getTotal();
	}

	public int getIdSolicitud() {
		return idSolicitud;
	}

	protected void setIdSolicitud(int idSolicitud) {
		this.idSolicitud = idSolicitud;
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

	public void setTotal() {
		this.total = this.cantidad * this.producto.getPrecioUnitario();
	}

	public void setTotal(float total) {
		this.total = total;
	}
	
	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
		this.setTotal();
	}
	
	
}

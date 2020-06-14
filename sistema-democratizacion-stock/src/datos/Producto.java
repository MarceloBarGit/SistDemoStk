package datos;

public class Producto {
	private int idProducto;
	private String codProducto;
	private String nombre;
	private String descripcion;
	private boolean habilitado;
	private float precioUnitario;
	
	public Producto() {}

	public Producto(String codProducto, String nombre, String descripcion, boolean habilitado, float precioUnitario) {
		super();
		this.codProducto = codProducto;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.habilitado = habilitado;
		this.precioUnitario = precioUnitario;
	}

	public int getIdProducto() {
		return idProducto;
	}

	protected void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}
	
	public String getCodProducto() {
		return codProducto;
	}

	public void setCodProducto(String codProducto) {
		this.codProducto = codProducto;
	}
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public boolean isHabilitado() {
		return habilitado;
	}

	public void setHabilitado(boolean habilitado) {
		this.habilitado = habilitado;
	}

	public float getPrecioUnitario() {
		return precioUnitario;
	}

	public void setPrecioUnitario(float precioUnitario) {
		this.precioUnitario = precioUnitario;
	}
	
	@Override
	public boolean equals(Object o) {
		// Comparo los productos por id
		return this.idProducto == ((Producto) o).getIdProducto();
	}
	
}

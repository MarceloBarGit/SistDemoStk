package datos;

import java.time.LocalDate;

public class Lote {
	private int idLote;
	private LocalDate fechaIngreso;
	private int cantidadDisponible;
	private int cantidadInicial;
	private Producto producto;
	
	public Lote() {
		
	}

	public Lote(int idLote, LocalDate fechaIngreso, int cantidadDisponible, int cantidadInicial, Producto producto) {
		super();
		this.idLote = idLote;
		this.fechaIngreso = fechaIngreso;
		this.cantidadDisponible = cantidadDisponible;
		this.cantidadInicial = cantidadInicial;
		this.producto = producto;
	}

	public int getIdLote() {
		return idLote;
	}

	protected void setIdLote(int idLote) {
		this.idLote = idLote;
	}

	public LocalDate getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(LocalDate fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public int getCantidadDisponible() {
		return cantidadDisponible;
	}

	public void setCantidadDisponible(int cantidadDisponible) {
		this.cantidadDisponible = cantidadDisponible;
	}

	public int getCantidadInicial() {
		return cantidadInicial;
	}

	public void setCantidadInicial(int cantidadInicial) {
		this.cantidadInicial = cantidadInicial;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}
}

package datos;

public class Ubicacion {
	private int idUbicacion;
	private double latitud;
	private double longitud;
	
	public Ubicacion() {
		
	}

	public Ubicacion(double latitud, double longitud) {
		super();
		this.latitud = latitud;
		this.longitud = longitud;
	}

	public int getIdUbicacion() {
		return idUbicacion;
	}

	protected void setIdUbicacion(int idUbicacion) {
		this.idUbicacion = idUbicacion;
	}

	public double getLatitud() {
		return latitud;
	}

	public void setLatitud(double latitud) {
		this.latitud = latitud;
	}

	public double getLongitud() {
		return longitud;
	}

	public void setLongitud(double longitud) {
		this.longitud = longitud;
	}
	
	public static double distanciaCoord(double lat1 , double lng1 , double lat2 , double lng2) {
		double radioTierra = 6371; // en kilómetros
		double dLat = Math. toRadians ( lat2 - lat1 );
		double dLng = Math. toRadians ( lng2 - lng1 );
		double sindLat = Math. sin ( dLat / 2);
		double sindLng = Math. sin ( dLng / 2);
		double va1 = Math. pow ( sindLat , 2) + Math. pow ( sindLng , 2) * Math. cos (Math. toRadians ( lat1 )) * Math. cos (Math. toRadians ( lat2 ));
		double va2 = 2 * Math. atan2 (Math. sqrt ( va1 ), Math. sqrt (1 - va1 ));
		return radioTierra * va2 ;
	}
	
	public double distanciaCoord(Ubicacion u) {
		double radioTierra = 6371; // en kilómetros
		double dLat = Math. toRadians ( u.latitud - this.latitud );
		double dLng = Math. toRadians ( u.longitud - this.longitud );
		double sindLat = Math. sin ( dLat / 2);
		double sindLng = Math. sin ( dLng / 2);
		double va1 = Math. pow ( sindLat , 2) + Math. pow ( sindLng , 2) * Math. cos (Math. toRadians ( this.latitud )) * Math. cos (Math. toRadians ( u.latitud ));
		double va2 = 2 * Math. atan2 (Math. sqrt ( va1 ), Math. sqrt (1 - va1 ));
		return radioTierra * va2 ;
	}
}

package datos;

public class FranjaHoraria {
	private int idFranjaHoraria;
	private int horaInicio;
	private int horaFin;
	
	public FranjaHoraria() {
		
	}

	public FranjaHoraria(int idFranjaHoraria, int horaInicio, int horaFin) {
		super();
		this.idFranjaHoraria = idFranjaHoraria;
		this.horaInicio = horaInicio;
		this.horaFin = horaFin;
	}

	public int getIdFranjaHoraria() {
		return idFranjaHoraria;
	}

	protected void setIdFranjaHoraria(int idFranjaHoraria) {
		this.idFranjaHoraria = idFranjaHoraria;
	}

	public int getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(int horaInicio) {
		this.horaInicio = horaInicio;
	}

	public int getHoraFin() {
		return horaFin;
	}

	public void setHoraFin(int horaFin) {
		this.horaFin = horaFin;
	}
}

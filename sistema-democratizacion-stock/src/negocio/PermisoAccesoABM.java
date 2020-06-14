package negocio;

import dao.PermisoAccesoDao;
import datos.PermisoAcceso;

public class PermisoAccesoABM {
	// TODO esta clase creo q no requiere ABM ya que no la maneja el usuario
	public int agregarPermisoAcceso(PermisoAcceso pa) {
		PermisoAccesoDao padao = new PermisoAccesoDao();
		return padao.agregar(pa);
	}
	
	public void modificarPermisoAcceso(PermisoAcceso pa) {
		PermisoAccesoDao padao = new PermisoAccesoDao();
		padao.actualizar(pa);
	}
	
	public void eliminarPermisoAcceso(PermisoAcceso pa) {
		PermisoAccesoDao padao = new PermisoAccesoDao();
		padao.eliminar(pa);
	}
}

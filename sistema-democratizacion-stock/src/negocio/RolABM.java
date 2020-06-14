package negocio;

import java.util.List;
import datos.Rol;
import dao.RolDao;

public class RolABM {
	public int agregarRol(Rol r) {
		RolDao rdao = new RolDao();
		return rdao.agregar(r);
	}
	
	public void modificarRol(Rol r) {
		RolDao rdao = new RolDao();
		rdao.actualizar(r);
	}
	
	public void eliminarRol(Rol r) {
		RolDao rdao = new RolDao();
		rdao.eliminar(r);
	}
	
	public List<Rol> traerTodos(boolean inicializado){
		RolDao rdao = new RolDao();
		return rdao.traer();
	}
}

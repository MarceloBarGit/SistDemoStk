package negocio;

import dao.ContactoDao;
import datos.Contacto;

public class ContactoABM {

	ContactoDao cDao = new ContactoDao();
		
		
		public boolean agregarContacto(String telefono, String direccion) {
			
			return cDao.agregar(new Contacto(telefono,direccion))>0;
			
		}
		
		public boolean modificarCliente(Contacto c) {
			Contacto con = cDao.traer(c.getIdContacto());
			boolean salida = false;
			if(con != null) {
				cDao.actualizar(c);
				salida = true;		
			}
			else {
				// No sé si hay que tirar excepcion o no, depende de lo que pida.
			}
			return salida;
		}
		
		public boolean eliminarContacto(Contacto c) {
			
			Contacto con = cDao.traer(c.getIdContacto());
			boolean salida = false;
			if(con != null) {
				cDao.eliminar(con);
				salida = true;		
			}
			else {
				// No sé si hay que tirar excepcion o no, depende de lo que pida.
			}
			return salida;
		}
		
		public Contacto traer(int idContacto) {
			
			return cDao.traer(idContacto);
		}
		
		

}

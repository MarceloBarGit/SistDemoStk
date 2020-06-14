package dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

//import datos.LineaFactura;
import datos.LineaSolicitud;

public class LineaSolicitudDao {

	private static Session session;
	private Transaction tx;
	
	//---------------------
	// Conexión a BD y creación de la Session
	protected void iniciaOperacion() throws HibernateException {
		session = HibernateUtil.getSessionFactory().openSession();
		tx = session.beginTransaction();
	}
	protected void manejaExcepcion(HibernateException he) throws HibernateException {
		tx.rollback();
		throw new HibernateException("ERROR en la capa de acceso a datos", he);
	}
	
//-------------------------------------------------------
//   Agregar-Eliminar-Modificar-Traer - List de LineaFactura
//-------------------------------------------------------	
	
	
//-----------------	
	@SuppressWarnings("unchecked")
	public List<LineaSolicitud> traer() throws HibernateException {
		List<LineaSolicitud> lista = null;
		try {
			iniciaOperacion();
			lista = session.createQuery("from ineaSolicitud l").list();
		} finally {
			session.close();
		}
		return lista;
		}
	
@SuppressWarnings("unchecked")
public List<LineaSolicitud> traerLineaSolicitudDelProducto(int idProducto) {
	List<LineaSolicitud> lista = null;
	try {
		iniciaOperacion();
		String consulta = "from LineaSolicitud l inner join fetch l.idProducto where l.idProducto = "	+ String.valueOf(idProducto);
		lista = session.createQuery(consulta).list();
	} finally {
		session.close();
	}
	return lista;
}
		

//------------------
}// Fin LineaFactura

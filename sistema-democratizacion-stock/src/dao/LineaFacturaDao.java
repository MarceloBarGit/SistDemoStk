package dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import datos.LineaFactura;

public class LineaFacturaDao {

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
	public List<LineaFactura> traer() throws HibernateException {
		List<LineaFactura> lista = null;
		try {
			iniciaOperacion();
			lista = session.createQuery("from LineaFactura l").list();
		} finally {
			session.close();
		}
		return lista;
		}
	
@SuppressWarnings("unchecked")
public List<LineaFactura> traerLineaFacturaDelProducto(int idProducto) {
	List<LineaFactura> lista = null;
	try {
		iniciaOperacion();
		String consulta = "from LineaFactura l inner join fetch l.idProducto where l.idProducto = "	+ String.valueOf(idProducto);
		lista = session.createQuery(consulta).list();
	} finally {
		session.close();
	}
	return lista;
}
		

//------------------
}// Fin LineaFactura

package dao;

import java.util.List;

//import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
//-----------
import datos.Lote;
//-----------

public class LoteDao {
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
//   Agregar-Eliminar-Modificar-Traer - List de Lote
//-------------------------------------------------------	
	public int agregar(Lote objeto) {
		int id = 0;
		try {
			iniciaOperacion();  // Se abre la conexión
			id = Integer.parseInt(session.save(objeto).toString());
			tx.commit();
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			session.close();   // Se cierra la conexión 
		}
		return id;
	}
	public void actualizar(Lote objeto) throws HibernateException {
		try {
			iniciaOperacion();
			session.update(objeto);
			tx.commit();
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			session.close();
		}
	}

	public void eliminar(Lote objeto) throws HibernateException {
		try {
			iniciaOperacion();
			session.delete(objeto);
			tx.commit();
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			session.close();
		}
	}

//---------------
	public Lote traer(int idLote) throws HibernateException {
		Lote objeto = null;
		try {
			iniciaOperacion();   // abro la conexion a la session
			//Casteo del tipo Lote
			objeto = (Lote) session.get(Lote.class, idLote);
		} finally {
			session.close();
		}
		return objeto;
	}
	
//-----------------	
	@SuppressWarnings("unchecked")
	public List<Lote> traer() throws HibernateException {
		List<Lote> lista = null;
		try {
			iniciaOperacion();
			lista = session.createQuery("from Lote c").list();
		} finally {
			session.close();
		}
		return lista;
		}
	
@SuppressWarnings("unchecked")
public List<Lote> traerLotesDelProducto(int idProducto) {
	List<Lote> lotes = null;
	try {
		iniciaOperacion();
		String consulta = "from Lote l inner join fetch l.idProducto where l.idProducto = "	+ String.valueOf(idProducto);
		lotes = session.createQuery(consulta).list();
	} finally {
		session.close();
	}
	return lotes;
}
		

//------------------
}// Fin LoteDao

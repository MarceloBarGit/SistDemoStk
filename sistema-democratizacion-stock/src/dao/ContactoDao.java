package dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import datos.Contacto;

public class ContactoDao {
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
//   Agregar-Eliminar-Modificar-Traer - List de Contacto
//-------------------------------------------------------	
	public int agregar(Contacto objeto) {
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
	public void actualizar(Contacto objeto) throws HibernateException {
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

	public void eliminar(Contacto objeto) throws HibernateException {
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

//------------------------------------
	public Contacto traer(int idContacto) throws HibernateException {
		Contacto objeto = null;
		try {
			iniciaOperacion();   // abro la conexion a la session
			//Casteo del tipo Contacto
			objeto = (Contacto) session.get(Contacto.class, idContacto);
		} finally {
			session.close();
		}
		return objeto;
	}
	
	
//---------------------
	@SuppressWarnings("unchecked")
	public List<Contacto> traer() throws HibernateException {
		List<Contacto> lista = null;
		try {
			iniciaOperacion();
			lista = session.createQuery("from Contacto c").list();
		} finally {
			session.close();
		}
		return lista;
	}	
	
	


	
	
	
//------------------
}// Fin ContactoDao

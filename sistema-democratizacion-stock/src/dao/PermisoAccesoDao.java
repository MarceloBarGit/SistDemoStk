package dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import datos.PermisoAcceso;

public class PermisoAccesoDao {
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
	public int agregar(PermisoAcceso objeto) {
		int id = 0;
		try {
			iniciaOperacion();
			id = Integer.parseInt(session.save(objeto).toString());
			tx.commit();
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			session.close();
		}
		return id;
	}

	public void actualizar(PermisoAcceso objeto) throws HibernateException {
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

	public void eliminar(PermisoAcceso objeto) throws HibernateException {
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
//--------------
	public PermisoAcceso traer(int idPermisoAcceso) throws HibernateException {
		PermisoAcceso objeto = null;
		try {
			iniciaOperacion();
			objeto = (PermisoAcceso) session.get(PermisoAcceso.class, idPermisoAcceso);
		} finally {
			session.close();
		}
		return objeto;
	}

	public PermisoAcceso traer(String descripcion) throws HibernateException {
		PermisoAcceso objeto = null;
		try {
			iniciaOperacion();
			objeto = (PermisoAcceso) session.createQuery("from PermisoAcceso c where c.descripcion like '%" + descripcion + "%'").uniqueResult();
		} finally {
			session.close();
		}
		return objeto;
	}
	
//--------------
	@SuppressWarnings("unchecked")
	public List<PermisoAcceso> traer() throws HibernateException {
		List<PermisoAcceso> lista = null;
		try {
			iniciaOperacion();
			lista = session.createQuery("from PermisoAcceso c").list();
		} finally {
			session.close();
		}
		return lista;
	}
	
	
//------------------	
}// Fin PermisoDao

package dao;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import datos.Rol;

public class RolDao {
	private static Session session;
	private Transaction tx;

	private void iniciaOperacion() throws HibernateException {
		session = HibernateUtil.getSessionFactory().openSession();
		tx = session.beginTransaction();
	}

	private void manejaExcepcion(HibernateException he) throws HibernateException {
		tx.rollback();
		throw new HibernateException("ERROR en la capa de acceso a datos", he);
	}

	public int agregar(Rol objeto) {
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

	public void actualizar(Rol objeto) throws HibernateException {
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

	public void eliminar(Rol objeto) throws HibernateException {
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
//-------------
	public Rol traer(int idRol) throws HibernateException {
		Rol objeto = null;
		try {
			iniciaOperacion();
			objeto = (Rol) session.get(Rol.class, idRol);
		} finally {
			session.close();
		}
		return objeto;
	}

	public Rol traer(String descripcion) throws HibernateException {
		Rol objeto = null;
		try {
			iniciaOperacion();
			objeto = (Rol) session.createQuery("from Rol c where c.descripcion like '%" + descripcion + "%'").uniqueResult();
		} finally {
			session.close();
		}
		return objeto;
	}
	
//------------------------
	@SuppressWarnings("unchecked")
	public List<Rol> traer() throws HibernateException {
		List<Rol> lista = null;
		try {
			iniciaOperacion();
			lista = session.createQuery("from Rol c").list();
		} finally {
			session.close();
		}
		return lista;
	}
//-----------------------
	public Rol inicializarPermisos(Rol r) {
		Hibernate.initialize(r.getPermisos());
		return r;
	}
}



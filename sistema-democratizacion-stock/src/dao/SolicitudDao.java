package dao;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import datos.Solicitud;

public class SolicitudDao {
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
//   Agregar-Eliminar-Modificar-Traer - List de Producto
//-------------------------------------------------------
	public int agregar(Solicitud objeto) {
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

	public void actualizar(Solicitud objeto) throws HibernateException {
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

	public void eliminar(Solicitud objeto) throws HibernateException {
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
	
//------------------------
	public Solicitud traer(int idSolicitud) throws HibernateException {
		Solicitud objeto = null;
		try {
			iniciaOperacion();
			objeto = (Solicitud) session.get(Solicitud.class, idSolicitud);
		} finally {
			session.close();
		}
		return objeto;
	}

	public Solicitud traer(String estado) throws HibernateException {
		Solicitud objeto = null;
		try {
			iniciaOperacion();
			objeto = (Solicitud) session.createQuery("from Solicitud S where c.estado like '%" + estado + "%'").uniqueResult();
		} finally {
			session.close();
		}
		return objeto;
	}
//-------------------
	@SuppressWarnings("unchecked")
	public List<Solicitud> traer() throws HibernateException {
		List<Solicitud> lista = null;
		try {
			iniciaOperacion();
			lista = session.createQuery("from Solicitud S").list();
		} finally {
			session.close();
		}
		return lista;
	}
//-----------------------
	public Solicitud inicializarLocalSolicitante(Solicitud s) {
		Hibernate.initialize(s.getLocalSolicitante());
		return s;
	}
	
	public Solicitud inicializarLocalSolicitado(Solicitud s) {
		Hibernate.initialize(s.getLocalSolicitado());
		return s;
	}
	
	public Solicitud inicializarEmpleadoSolicitante(Solicitud s) {
		Hibernate.initialize(s.getEmpleadoSolicitante());
		return s;
	}
	
	public Solicitud inicializarEmpleadoSolicitado(Solicitud s) {
		Hibernate.initialize(s.getEmpleadoSolicitado());
		return s;
	}
	
	public Solicitud inicializarLineasSolicitud(Solicitud s) {
		Hibernate.initialize(s.getLineasSolicitud());
		return s;
	}	
	
//-----------------------
}// fIN SolicitudDao

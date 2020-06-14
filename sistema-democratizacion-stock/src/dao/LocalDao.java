package dao;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
//-----------
import datos.Local;
//-----------

public class LocalDao {
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
//   Agregar-Eliminar-Modificar-Traer - List de Local
//-------------------------------------------------------	
	public int agregar(Local objeto) {
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
	public void actualizar(Local objeto) throws HibernateException {
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

	public void eliminar(Local objeto) throws HibernateException {
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

//-------------------
	public Local traer(int idLocal) throws HibernateException {
		Local objeto = null;
		try {
			iniciaOperacion();   // abro la conexion a la session
			//Casteo del tipo Local
			objeto = (Local) session.get(Local.class, idLocal);
		} finally {
			session.close();
		}
		return objeto;
	}
	public Local traer(String nombre) throws HibernateException {
		Local objeto = null;
		try {
			iniciaOperacion();
			objeto = (Local) session.createQuery("from Local c where c.nombre like '%" + nombre + "'%").uniqueResult();
		} finally {
			session.close();
		}
		return objeto;
	}
//-----------------------
	@SuppressWarnings("unchecked")
	public List<Local> traer() throws HibernateException {
		List<Local> lista = null;
		try {
			iniciaOperacion();
			lista = session.createQuery("from Local c").list();
		} finally {
			session.close();
		}
		return lista;
	}	
	
//-----------------------
	public Local inicializarUbicacion(Local l) {
		Hibernate.initialize(l.getUbicacion());
		return l;
	}
	
	public Local inicializarEmpleados(Local l) {
		Hibernate.initialize(l.getEmpleados());
		return l;
	}
	
	public Local inicializarFacturas(Local l) {
		Hibernate.initialize(l.getFacturas());
		return l;
	}
	
	public Local inicializarSolicitudesEntrantes(Local l) {
		Hibernate.initialize(l.getSolicitudesEntrantes());
		return l;
	}
	
	public Local inicializarSolicitudesSalientes(Local l) {
		Hibernate.initialize(l.getSolicitudesSalientes());
		return l;
	}
	
	public Local inicializarLotes(Local l) {
		Hibernate.initialize(l.getLotes());
		return l;
	}
	
//--------------
}// Fin LocalDao

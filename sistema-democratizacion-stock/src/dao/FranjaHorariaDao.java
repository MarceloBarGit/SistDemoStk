package dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

//--------
import datos.FranjaHoraria;
//--------

public class FranjaHorariaDao {
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
//   Agregar-Eliminar-Modificar-Traer - List de FranjaHoraria
//-------------------------------------------------------	
	public int agregar(FranjaHoraria objeto) {
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
	public void actualizar(FranjaHoraria objeto) throws HibernateException {
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

	public void eliminar(FranjaHoraria objeto) throws HibernateException {
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

//---------------------
	public FranjaHoraria traer(int idFranjaHoraria) throws HibernateException {
		FranjaHoraria objeto = null;
		try {
			iniciaOperacion();   // abro la conexion a la session
			//Casteo del tipo FranjaHoraria
			objeto = (FranjaHoraria) session.get(FranjaHoraria.class, idFranjaHoraria);
		} finally {
			session.close();
		}
		return objeto;
	}
//---------------------
	@SuppressWarnings("unchecked")
	public List<FranjaHoraria> traer() throws HibernateException {
		List<FranjaHoraria> lista = null;
		try {
			iniciaOperacion();
			lista = session.createQuery("from FranjaHoraria fh").list();
		} finally {
			session.close();
		}
		return lista;
	}	

	
	
	
//------------------
}// Fin FranjaHorariaDao

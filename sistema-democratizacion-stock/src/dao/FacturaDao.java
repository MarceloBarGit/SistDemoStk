package dao;

import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
//--------
import datos.Factura;
import datos.Lote;

public class FacturaDao {
	
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
//   Agregar-Eliminar-Modificar-Traer - List de Empleado
//-------------------------------------------------------
	public int agregar(Factura objeto) {
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

	public void actualizar(Factura objeto) throws HibernateException {
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

	public void eliminar(Factura objeto) throws HibernateException {
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
//-----------------
	public Factura traer(int idFactura) throws HibernateException {
		Factura objeto = null;
		try {
			iniciaOperacion();
			objeto = (Factura) session.get(Factura.class, idFactura);
		} finally {
			session.close();
		}
		return objeto;
	}

	public Factura traer(String nroDocumento) throws HibernateException {
		Factura objeto = null;
		try {
			iniciaOperacion();
			objeto = (Factura) session.createQuery("from Factura c where c.nroDocumento=" + nroDocumento).uniqueResult();
		} finally {
			session.close();
		}
		return objeto;
	}
//----------------
	@SuppressWarnings("unchecked")
	public List<Factura> traer() throws HibernateException {
		List<Factura> lista = null;
		try {
			iniciaOperacion();
			lista = session.createQuery("from Factura c").list();
		} finally {
			session.close();
		}
		return lista;
	}	
	
//---------------
	public Factura inicializarLineasFactura(Factura f) {
		Hibernate.initialize(f.getLineasFactura());
		return f;
	}
	
	public Factura inicializarVendedor(Factura f) {
		Hibernate.initialize(f.getVendedor());
		return f;
	}
	
	public Factura inicializarCliente(Factura f) {
		Hibernate.initialize(f.getCliente());
		return f;
	}
	
	public Factura inicializarLocal(Factura f) {
		Hibernate.initialize(f.getLocal());
		return f;
	}
	
	public Factura inicializarSolicitudAsociada(Factura f) throws Exception {
		if (f.getSolicitudAsociada() != null) {
			Hibernate.initialize(f.getSolicitudAsociada());
		} 
		else {
			throw new Exception("No existe una solicitud para esta factura");
		}
		return f;
	}
//-----------
	//-----------------	
		@SuppressWarnings("unchecked")
		public List<Factura> traerFactura(){
			List<Factura> lista = null;
			try {
				iniciaOperacion();
				lista = session.createQuery("from Factura c").list();
			} finally {
				session.close();
			}
			return lista;
			}
	
	public Factura traerFactura(int idFactura) {
		// TODO Auto-generated method stub
		return null;
	}
}//Fin FacturaDao

package dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import datos.Cliente;
import datos.Producto;

public class ProductoDao{
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
	public int agregar(Producto objeto) {
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
	public void actualizar(Producto objeto) throws HibernateException {
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

	public void eliminar(Producto objeto) throws HibernateException {
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
	public Producto traerProducto(int idProducto) throws HibernateException {
		Producto objeto = null;
		try {
			iniciaOperacion(); 
			objeto = (Producto) session.get(Producto.class, idProducto);
		} finally {
			session.close();
		}
		return objeto;
	}
	
	public Producto traerProducto(String nombre) throws HibernateException {
		Producto objeto = null;
		try {
			iniciaOperacion();   // abro la conexion a la session
			String consulta = "from Producto p where p.nombre = "+ nombre;
					
			objeto = (Producto) session.createQuery(consulta).uniqueResult();
			
		} finally {
			session.close();
		}
		return objeto;
	}	
	
	@SuppressWarnings("unchecked")
	public List<Producto> traerProductos() throws HibernateException {
		List<Producto> lista = null;
		try {
			iniciaOperacion();
			lista = session.createQuery("from Producto p").list();
		} finally {
			session.close();
		}
		return lista;
	}	

	
// DESHABILITARLO PARA USARLO CON LOS CODIGOS DE VERIFICACION DE PRODUCTOS
//-----------	
//	public Producto traer(String codProducto) throws HibernateException {
//		Producto objeto = null;
//		try {
//			iniciaOperacion(); 
//			objeto = (Producto) session.get(Producto.class, codProducto);
//		} finally {
//			session.close();
//		}
//		return objeto;
//	}
	
	@SuppressWarnings("unchecked")
	public List<Producto> traerPorNombre(String nombre) throws HibernateException {
		List<Producto> objeto = null;
		try {
			iniciaOperacion();
			objeto = session.createQuery("from Producto c where c.nombre like '%" + nombre + "%'").list();
		} finally {
			session.close();
		}
		return objeto;
	}
//------------------------------
	@SuppressWarnings("unchecked")
	public List<Producto> traer() throws HibernateException {
		List<Producto> lista = null;
		try {
			iniciaOperacion();
			lista = session.createQuery("from Producto p").list();
		} finally {
			session.close();
		}
		return lista;
	}	
	
//------------------
}// Fin ProductoDao

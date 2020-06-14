package dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import datos.Cliente;
import datos.Empleado;
//import datos.Factura;
//--------
//import datos.Persona;
//--------

public class PersonaDao {
		private static Session session;
		private Transaction tx;
	//----------------------------------	
	// LO NUEVO POR SER UNA HERENCIA
	// ESTA ES LA CLSE PADRE
		
		// Patrón Singleton
		private static PersonaDao instancia = null; 

		//---------------------
		// Constructor
		public PersonaDao() {
		}

		//---------------------
		// Instancia DAO
		public static PersonaDao getInstance() {
			if (instancia == null)
				instancia = new PersonaDao();
			return instancia;
		}

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
//   Agregar-Eliminar-Modificar-Traer - List de Persona
//-------------------------------------------------------	
			public int agregarCliente(Cliente objeto) {
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
			
			public int agregarEmpleado(Empleado objeto) {
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
			
			public void actualizarCliente(Cliente objeto) throws HibernateException {
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
			
			public void actualizarEmpleado(Empleado objeto) throws HibernateException {
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


			public void eliminarCliente(Cliente objeto) throws HibernateException {
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
			
			public void eliminarEmpleado(Empleado objeto) throws HibernateException {
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

			//Cuando se debe traer el ID de cualquier Tabla...
			//      SE DE DEBE USAR UN session.Get(...) ---> SIEMPRE 
			public Cliente traerCliente(int idCliente) throws HibernateException {
				Cliente objeto = null;
				try {
					iniciaOperacion();   // abro la conexion a la session
					//Casteo del tipo Cliente
					objeto = (Cliente) session.get(Cliente.class, idCliente);
				} finally {
					session.close();
				}
				return objeto;
			}	
			
			public Cliente traerCliente(String dni) throws HibernateException {
				Cliente objeto = null;
				try {
					iniciaOperacion();   // abro la conexion a la session
					String consulta = "from Cliente c where c.dni = "+ dni;
							
					objeto = (Cliente) session.createQuery(consulta).uniqueResult();
					
				} finally {
					session.close();
				}
				return objeto;
			}	
			
			public Empleado traerEmpleado(int idEmpleado) throws HibernateException {
				Empleado objeto = null;
				try {
					iniciaOperacion();   // abro la conexion a la session
					//Casteo del tipo Empleado
					objeto = (Empleado) session.get(Empleado.class, idEmpleado);
				} finally {
					session.close();
				}
				return objeto;
			}	
			public Empleado traerEmpleado(String legajo) throws HibernateException {
				Empleado objeto = null;
				try {
					iniciaOperacion();   // abro la conexion a la session
					String consulta = "from Empleado e where e.legajo = "+ legajo;
							
					objeto = (Empleado) session.createQuery(consulta).uniqueResult();
					
				} finally {
					session.close();
				}
				return objeto;
			}	
			
			
			
			@SuppressWarnings("unchecked")
			public List<Empleado> traerEmpleados() throws HibernateException {
				List<Empleado> lista = null;
				try {
					iniciaOperacion();
					lista = session.createQuery("from Empleado c").list();
				} finally {
					session.close();
				}
				return lista;
			}
			
			@SuppressWarnings("unchecked")
			public List<Cliente> traerClientes() throws HibernateException {
				List<Cliente> lista = null;
				try {
					iniciaOperacion();
					lista = session.createQuery("from Cliente c").list();
				} finally {
					session.close();
				}
				return lista;
			}
//----------------
}//Fin Persona
	

/*
 * Tipo: configuración del Registro de la SessionFactory y configuración del hibernate.
 * Extensión: .JAVA
 * Nombre archivo: hibernate.util.java
 * Descripción: Establece las Referencia (Sesiones de trabajo) a las Conexiones de la Base de datos, 
 * 			       esta referencia al objeto SessionFactory sirve para que cualquier clase (capa datos)
 *  			   pueda tener acceso al objeto Session y por lo tanto a todas las funcionalidades de Hibernate
 *                 
 * Fecha: 26/03/2020
 * Version:  1.1.2603.0001    
 * Autor: Barbieri Marcelo E.
 * Modificación: __/__/2020. 
 * Tipo de Modificación:
 * ******************************************************************************* */

package dao;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {
	private static SessionFactory sessionFactory;

	public static SessionFactory getSessionFactory() {
		try {
			if (sessionFactory == null) {
				StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
						.configure("hibernate.cfg.xml").build();                                        //  SE VA A LEER el ARCHIVO de configuración
				Metadata metaData = new MetadataSources(standardRegistry).getMetadataBuilder().build();
				sessionFactory = metaData.getSessionFactoryBuilder().build();
			}
		} catch (HibernateException he) {
			System.err.println("ERROR en la inicialización de la SessionFactory (Configuración): " + he);
			throw new ExceptionInInitializerError(he);
		}
		return sessionFactory;
	}

}
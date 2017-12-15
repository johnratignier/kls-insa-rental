package com.kls.configuration;

import java.io.IOException;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.logging.Level;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.sql.DataSource;

import org.xml.sax.SAXException;

public class DAOFactoryInitialisation implements ServletContextListener{
	private static final String ATT_DAO_FACTORY = "daoFactory";
	public DAOFactory daoFactory;

	public void contextInitialized(ServletContextEvent event) {
		/* Récupération du ServletContext lors du chargement de l'application */
		ServletContext servletContext = event.getServletContext();
		/* Instanciation de notre DAOFactory */
		try {
			this.daoFactory = DAOFactory.getInstance();
		} catch (SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/* Enregistrement dans un attribut ayant pour portée toute l'application */
		servletContext.setAttribute( ATT_DAO_FACTORY, this.daoFactory );
	}

	public void contextDestroyed(ServletContextEvent event) {

	}

}

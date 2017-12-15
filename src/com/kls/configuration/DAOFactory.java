package com.kls.configuration;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import javax.servlet.ServletContext;

import org.apache.fop.apps.FopFactory;
import org.xml.sax.SAXException;

import com.kls.dao.*;
import com.kls.javabean.EtatMateriel;

public class DAOFactory {

	private static final String FICHIER_PROPERTIES= "/com/kls/configuration/mysql_properties";
	private static final String PROPERTY_URL= "url";
	private static final String PROPERTY_DRIVER= "driver";
	private static final String PROPERTY_NOM_UTILISATEUR = "nomutilisateur";
	private static final String PROPERTY_MOT_DE_PASSE    = "motdepasse";

	private String  url;
	private String  username;
	private String  password;

	DAOFactory( String url, String username, String password ) throws SAXException, IOException {
		this.url = url;
		this.username = username;
		this.password = password;
	}

	/*
	 * Méthode chargée de récupérer les informations de connexion à la base de
	 * données, charger le driver JDBC et retourner une instance de la Factory
	 */
	public static DAOFactory getInstance() throws SAXException, IOException {
		Properties properties = new Properties();
		String url;
		String driver;
		String nomUtilisateur;
		String motDePasse;

		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream fichierProperties = classLoader.getResourceAsStream( FICHIER_PROPERTIES );

		if ( fichierProperties == null ) {
			System.out.print(classLoader.toString());
			throw new RuntimeException( "Le fichier properties " + FICHIER_PROPERTIES + " est introuvable." );
		}

		try {
			properties.load( fichierProperties );
			url = properties.getProperty( PROPERTY_URL );
			driver = properties.getProperty( PROPERTY_DRIVER );
			nomUtilisateur = properties.getProperty( PROPERTY_NOM_UTILISATEUR );
			motDePasse = properties.getProperty( PROPERTY_MOT_DE_PASSE );
		} catch ( IOException e ) {
			throw new RuntimeException( "Impossible de charger le fichier properties " + FICHIER_PROPERTIES, e );
		}

		try {
			Class.forName( driver );
		} catch ( ClassNotFoundException e ) {
			throw new RuntimeException( "Le driver est introuvable dans le classpath.", e );
		}

		DAOFactory instance = new DAOFactory( url, nomUtilisateur, motDePasse );
		return instance;
	}

	/* Méthode chargée de fournir une connexion à la base de données */
	/* package */ 
	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection( url, username, password );
	}

	/*
	 * Méthodes de récupération de l'implémentation des différents DAO (un seul
	 * pour le moment)
	 */
	public MembreDAO getMembreDAO() {
		return new MembreDAOImpl(this);
	}

	public TypeMaterielDAO getTypeMaterielDAO(){
		return new TypeMaterielDAOImpl(this);
	}

	public EtatMaterielDAO getEtatMaterielDAO(){
		return new EtatMaterielDAOImpl(this);
	}
	
	public MaterielDAO getMaterielDAO(){
		return new MaterielDAOImpl(this);
	}
	
	public ClientDAO getClientDAO(){
		return new ClientDAOImpl(this);
	}
	
	public DroitsMembreDAO getDroitsMembreDAO(){
		return new DroitsMembreDAOImpl(this);
	}
	
	public StatutDAO getStatutDAO(){
		return new StatutDAOImpl(this);
	}
	
	public ReservationDAO getReservationDAO(){
		return new ReservationDAOImpl(this);
	}
	
	public FactureDAO getFactureDAO(){
		return new FactureDAOImpl(this);
	}


}

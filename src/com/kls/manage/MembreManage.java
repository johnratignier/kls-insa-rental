package com.kls.manage;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.kls.dao.MembreDAO;
import com.kls.javabean.DroitsMembre;
import com.kls.javabean.Membre;
import com.kls.javabean.Statut;

public  class MembreManage {

	private MembreDAO membreDAO;

	public MembreManage(MembreDAO membreDAO){
		this.membreDAO = membreDAO;
	}

	public  boolean  verifierLogin(String mail, String motDePasse, HttpServletRequest req, HttpServletResponse res){
		Membre mb = this.membreDAO.find(mail);
		if(BCrypt.checkpw(motDePasse, mb.getMot_de_passe())){
			req.getSession().setAttribute("membre", mb);
			return true;
		}else{
			System.out.print("Erreur mdp "+motDePasse+" "+mb.getMot_de_passe());
			req.setAttribute("erreurMDP", "Mauvais mot de passe");
			return false;
		}
	}



	public void nouveauMembre(HttpServletRequest req, String nomFichier){
		Membre mb = new Membre();
		mb.setAdresse_mail(req.getParameter("mail"));
		mb.setNumero_tel(req.getParameter("telephone"));
		mb.setNom(req.getParameter("nom").toUpperCase());
		mb.setPrenom(req.getParameter("prenom"));
		mb.setMot_de_passe(BCrypt.hashpw(req.getParameter("mot_de_passe"), BCrypt.gensalt()));
		mb.setDate_naissance(Date.valueOf(req.getParameter("date_de_naissance")));
		mb.setDepartement_annee(req.getParameter("departement_annee"));
		mb.setDepartement_etude(req.getParameter("departement_etude"));
		mb.setPseudo_artiste(req.getParameter("pseudo_artiste"));
		if(nomFichier!=null || !nomFichier.isEmpty()){
			mb.setPhoto_profil("img/photoMembre/"+nomFichier);
		}else{
			mb.setPhoto_profil("img/photoMembre/photo_profil_default.jpg");
		}
		this.membreDAO.create(mb);
	}

	public void updateMembre(HttpServletRequest req){
		Membre mb = new Membre();
		mb.setId_user(Integer.valueOf(req.getParameter("id")));
		mb.setAdresse_mail(req.getParameter("mail"));
		mb.setNumero_tel(req.getParameter("telephone"));
		mb.setNom(req.getParameter("nom").toUpperCase());
		mb.setPrenom(req.getParameter("prenom"));
		mb.setDate_naissance(Date.valueOf(req.getParameter("date_de_naissance")));
		mb.setDepartement_annee(req.getParameter("departement_annee"));
		mb.setDepartement_etude(req.getParameter("departement_etude"));
		mb.setPseudo_artiste(req.getParameter("pseudo_artiste"));
		mb.setStatut(new Statut(Integer.parseInt(req.getParameter("statut"))));
		mb.setDroits(new DroitsMembre(Integer.parseInt(req.getParameter("droits"))));
		this.membreDAO.updateEx(mb);
	}


	public void uploadPhotoProfil(HttpServletRequest req, Part part, String nom, String chemin) throws IOException{
		BufferedInputStream entree  = new BufferedInputStream(part.getInputStream(), 10240);
		BufferedOutputStream sortie  = new BufferedOutputStream(new FileOutputStream(new File(chemin +"/"+ nom)),10240);
		byte[] tampon = new byte[10240];
		int longeur;
		while((longeur=entree.read(tampon))>0){
			sortie.write(tampon, 0, longeur);
		}
		System.out.print(entree +" "+ sortie);
		try {
			sortie.close();
		} catch ( IOException ignore ) {
		}
		try {
			entree.close();
		} catch ( IOException ignore ) {
		}
	}

}

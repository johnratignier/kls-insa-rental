package com.kls.manage;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import com.kls.dao.MaterielDAO;
import com.kls.javabean.EtatMateriel;
import com.kls.javabean.Materiel;
import com.kls.javabean.Membre;
import com.kls.javabean.TypeMateriel;

public class MaterielManage {
	
	private MaterielDAO materielDAO;
	
	public MaterielManage(MaterielDAO m){
		this.materielDAO = m;
	}
	
	public void nouveauMateriel(HttpServletRequest req, String nomFichier){
		Materiel mat = new Materiel();
		mat.setNom_materiel(req.getParameter("label"));
		mat.setDescription_materiel(req.getParameter("description"));
		mat.setPrix_materiel(Double.valueOf(req.getParameter("prix")));
		mat.setCaution_materiel(Double.valueOf(req.getParameter("caution")));
		mat.setEtat_materiel(new EtatMateriel(Integer.valueOf(req.getParameter("etat"))));
		mat.setType_materiel(new TypeMateriel(Integer.valueOf(req.getParameter("type"))));
		mat.setPhoto_materiel("img/materielImage/"+nomFichier);
		mat.setLocation(Integer.parseInt(req.getParameter("location")));
		mat.setExoneration(Integer.parseInt(req.getParameter("exoneration")));
		this.materielDAO.create(mat);
	}
	
	public void updateMateriel(HttpServletRequest req){
		Materiel mat = new Materiel();
		mat.setId_materiel(Integer.parseInt(req.getParameter("id_materiel")));
		mat.setNom_materiel(req.getParameter("label"));
		mat.setDescription_materiel(req.getParameter("description"));
		mat.setPrix_materiel(Double.valueOf(req.getParameter("prix")));
		mat.setCaution_materiel(Double.valueOf(req.getParameter("caution")));
		mat.setEtat_materiel(new EtatMateriel(Integer.valueOf(req.getParameter("etat"))));
		mat.setType_materiel(new TypeMateriel(Integer.valueOf(req.getParameter("type"))));
		mat.setExoneration(Integer.parseInt(req.getParameter("exoneration")));
		mat.setLocation(Integer.parseInt(req.getParameter("location")));
		this.materielDAO.update(mat);
	}
	
	public void uploadPhotoMateriel(HttpServletRequest req, Part part, String nom, String chemin) throws IOException{
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

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

import com.kls.dao.ClientDAO;
import com.kls.dao.MembreDAO;
import com.kls.javabean.Client;
import com.kls.javabean.DroitsMembre;
import com.kls.javabean.Membre;
import com.kls.javabean.TypeClient;

public  class ClientManage {

	private ClientDAO clientDAO;

	public ClientManage(ClientDAO clientDAO){
		this.clientDAO = clientDAO;
	}

	public void nouveauClient(HttpServletRequest req, String nomFichier){
		Client cli = new Client();
		cli.setDescription_client(req.getParameter("description"));
		cli.setNom_client(req.getParameter("nom"));
		cli.setNumero_telephone_client(req.getParameter("telephone"));
		cli.setType_client(new TypeClient(Integer.valueOf(req.getParameter("type_client"))));
		cli.setMail_client(req.getParameter("mail"));
		cli.setFacturation_adresse(req.getParameter("facturation_adresse"));
		cli.setFacturation_code_postal(req.getParameter("facturation_code_postal"));
		cli.setFacturation_ville(req.getParameter("facturation_ville"));
		if(nomFichier!=null && !nomFichier.isEmpty()){
			System.out.print(nomFichier);
			cli.setPhoto_client("img/photoClient/"+nomFichier);
		}else{
			cli.setPhoto_client("img/photoClient/photo_client_default.jpg");
		}
		this.clientDAO.create(cli);
	}
	
	public void updateClient(HttpServletRequest req){
		Client cli = new Client();
		cli.setId_client(Integer.parseInt(req.getParameter("id")));
		cli.setDescription_client(req.getParameter("description"));
		cli.setNom_client(req.getParameter("nom"));
		cli.setNumero_telephone_client(req.getParameter("telephone"));
		cli.setType_client(new TypeClient(Integer.valueOf(req.getParameter("type_client"))));
		cli.setMail_client(req.getParameter("mail"));
		cli.setFacturation_adresse(req.getParameter("facturation_adresse"));
		cli.setFacturation_code_postal(req.getParameter("facturation_code_postal"));
		cli.setFacturation_ville(req.getParameter("facturation_ville"));
		this.clientDAO.update(cli);
	}

	public void uploadPhotoClient(HttpServletRequest req, Part part, String nom, String chemin) throws IOException{
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

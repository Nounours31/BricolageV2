package sfa.bricolage.v2.db.object;

import sfa.bricolage.v2.db.api.DBUser;

public class User {
	private int id;
	private int matricule;
	private String nom;
	private String prenom;
	private String Entreprise;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getMatricule() {
		return matricule;
	}
	public void setMatricule(int matricule) {
		this.matricule = matricule;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getEntreprise() {
		return Entreprise;
	}
	public void setEntreprise(String entreprise) {
		Entreprise = entreprise;
	}
	
	public static User buildFromDB(int id) {
		User u = DBUser.getUserFromId(id);
		return u;
	}
}

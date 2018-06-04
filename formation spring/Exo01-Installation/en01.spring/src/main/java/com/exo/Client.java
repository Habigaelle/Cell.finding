package com.exo;

public class Client {

	private String nom;
	private String prenom;
	private int age;
	private java.util.List<Adresse> adresses;

	public Client() {

	}

	public Client(String nom, String prenom, int age, Adresse adresse) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.age = age;
		this.adresses = adresses;
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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Client [nom=");
		builder.append(nom);
		builder.append(", prenom=");
		builder.append(prenom);
		builder.append(", age=");
		builder.append(age);
		builder.append(", adresses=");
		builder.append(adresses);
		builder.append("]");
		return builder.toString();
	}

	public java.util.List<Adresse> getAdresses() {
		return adresses;
	}

	public void setAdresses(java.util.List<Adresse> adresses) {
		this.adresses = adresses;
	}
}

package com.gestion.customerservice.security;

public class TokenObject {

	private String login;
	private Integer idUtilisateur;
	private String role;
	private String username;
	private Integer idAgence;
	private Integer idZone;

	private Integer idRegion;

	public Integer getIdZone() {
		return idZone;
	}

	public void setIdZone(Integer idZone) {
		this.idZone = idZone;
	}

	public Integer getIdRegion() {
		return idRegion;
	}

	public void setIdRegion(Integer idRegion) {
		this.idRegion = idRegion;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public Integer getIdUtilisateur() {
		return idUtilisateur;
	}

	public void setIdUtilisateur(Integer idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getIdAgence() {
		return idAgence;
	}

	public void setIdAgence(Integer idAgence) {
		this.idAgence = idAgence;
	}


	public TokenObject(String login, Integer idUtilisateur, String role, String username, Integer idAgence, Integer idZone, Integer idRegion) {
		this.login = login;
		this.idUtilisateur = idUtilisateur;
		this.role = role;
		this.username = username;
		this.idAgence = idAgence;
		this.idZone = idZone;
		this.idRegion = idRegion;
	}

	public TokenObject() {

	}
	
	
	
}

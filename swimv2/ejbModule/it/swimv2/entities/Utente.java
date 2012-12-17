package it.swimv2.entities;

import java.util.HashSet;
import java.util.Set;

public class Utente {

	private String id;
	private String nome;
	private String cognome;
	private String username;
	private String password;
	private String email;
	private Set<Abilita> abilita;
	
	/**
	 * 
	 * @param nome
	 * @param cognome
	 * @param username
	 * @param password
	 * @param email
	 */
	public Utente(String nome, String cognome, String username, String password, String email)
	{
		// TODO
		// implementare l'id
		this.nome = nome;
		this.cognome = cognome;
		this.username = username;
		this.password = password;
		this.email = email;
		this.abilita = new HashSet<Abilita>();
	}
	
	/**
	 * restituisce true se e solo se l'abilità non appartiene già
	 * all'utente e se viene aggiunta correttamente.
	 * @param abi
	 * @return
	 */
	public boolean AggiungiAbilità(Abilita abi)
	{
		return this.abilita.add(abi);
	}
	
	/**
	 * restituisce true
	 * @param nuovaMail
	 * @return
	 */
	public boolean ModificaMail(String nuovaMail)
	{
		if(this.email.equals(nuovaMail) ||
				nuovaMail.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$;"))
			return false;
		this.email = nuovaMail;
		return true;
	}
	
	/**
	 * 
	 */
	public void RicercaDomande()
	{
		
	}
}
/*
sig Utente{
nome: Testo,
cognome: Testo,
username: Testo,
password: Testo,
email: Testo,
abilita: set Abilita
}
*/
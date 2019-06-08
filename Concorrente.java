package polichef;

import java.util.*;

public class Concorrente implements Comparable<Concorrente>{
	
	private String nome;
	private String cognome;
	private String professione;
	private String id;
	
	private List<Piatto> piatti = new LinkedList<Piatto>();
	
	public Concorrente(String nome, String cognome, String professione,
			String id) {
		this.nome = nome;
		this.cognome = cognome;
		this.professione = professione;
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public String getCognome() {
		return cognome;
	}

	public String getProfessione() {
		return professione;
	}

	public String getId() {
		return id;
	}
	
	public void aggiungiPiatto(Piatto p){
		piatti.add(p);
	}
	
	public List<Piatto> getPiatti(){
		return piatti;
	}

	@Override
	public int compareTo(Concorrente altro) {
		if(this.getNome().compareTo(altro.getNome())!=0){
			return this.getNome().compareTo(altro.getNome());
		}
		return this.getCognome().compareTo(altro.getCognome());
	}
	
}

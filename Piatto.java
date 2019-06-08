package polichef;

import java.util.*;

public class Piatto implements Comparable<Piatto>{
	
	private String nome;
	private String idConcorrente;
	private int idPiatto;
	
	private List<String> ingredienti = new LinkedList<String>();

	public Piatto(String nome, String idConcorrente, int idPiatto) {
		this.nome = nome;
		this.idConcorrente = idConcorrente;
		this.idPiatto = idPiatto;
	}

	public String getNome() {
		return nome;
	}

	public String getIdConcorrente() {
		return idConcorrente;
	}

	public int getIdPiatto() {
		return idPiatto;
	}

	public List<String> getIngredienti() {
		return ingredienti;
	}

	public void aggiungiIngrediente(String ingrediente) {
		ingredienti.add(ingrediente);
		
	}

	@Override
	public int compareTo(Piatto altro) {
		return this.getNome().compareTo(altro.getNome());
	}
	
}

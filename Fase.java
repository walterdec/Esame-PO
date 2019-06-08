package polichef;

import java.util.*;

public class Fase implements Comparable<Fase>{
	
	private int numeroFase;
	private String nome;
	private int numeroMassimoConcorrenti;
	
	private List<Concorrente> concorrenti = new LinkedList<Concorrente>();
	private List<Sfida> sfide = new LinkedList<Sfida>();

	public Fase(int numeroFase, String nome, int numeroMassimoConcorrenti) {
		this.numeroFase = numeroFase;
		this.nome = nome;
		this.numeroMassimoConcorrenti = numeroMassimoConcorrenti;
	}

	public int getNumeroFase() {
		return numeroFase;
	}

	public String getNome() {
		return nome;
	}

	public int getNumeroMassimoConcorrenti() {
		return numeroMassimoConcorrenti;
	}
	
	public List<Concorrente> getConcorrenti(){
		return concorrenti;
	}

	public void aggiungiConcorrente(Concorrente c) {
		concorrenti.add(c);
		
	}
	
	public List<Sfida> getSfide(){
		return sfide;
	}

	public void aggiungiSfida(Sfida s) {
		sfide.add(s);
		
	}

	@Override
	public int compareTo(Fase altra) {
		return this.getNumeroFase()-altra.getNumeroFase();
	}
}

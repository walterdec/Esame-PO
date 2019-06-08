package polichef;

import java.io.*;
import java.util.*;

public class Trasmissione {
	
	private List<Concorrente> concorrenti = new LinkedList<Concorrente>();
	private List<Piatto> piatti = new LinkedList<Piatto>();
	private Map<Integer, Fase> fasi = new HashMap<Integer, Fase>();
	
	private int codicePiatto=99;

	public Concorrente iscriviConcorrente(String nome, String cognome, String professione) {
		for(Concorrente c : concorrenti){
			if(c.getNome().compareTo(nome)==0 && c.getCognome().substring(0,1).compareTo(cognome.substring(0,1))==0){
				return null;
			}
		}
		String codice = nome+" "+cognome.substring(0, 1)+".";
		Concorrente c = new Concorrente(nome, cognome, professione, codice);
		concorrenti.add(c);
		return c;
	}

	public Concorrente cercaConcorrente(String idConcorrente) {
		for(Concorrente c : concorrenti){
			if(c.getId().compareTo(idConcorrente)==0){
				return c;
			}
		}
		return null;
	}

	public Collection<Concorrente> elencoConcorrenti() {
		List<Concorrente> listaConcorrenti = new LinkedList<Concorrente>();
		listaConcorrenti.addAll(concorrenti);
		Collections.sort(listaConcorrenti);
		return listaConcorrenti;
	}

	public Collection<Concorrente> elencoConcorrenti(String professione) {
		List<Concorrente> concorrentiPerProfessione = new LinkedList<Concorrente>();
		for(Concorrente c : concorrenti){
			if(c.getProfessione().compareTo(professione)==0){
				concorrentiPerProfessione.add(c);
			}
		}
		return concorrentiPerProfessione;
	}

	public int registraPiattoConcorrente(String nomePiatto, String idConcorrente) {
		for(Concorrente c : concorrenti){
			if(c.getId().compareTo(idConcorrente)==0){
				codicePiatto++;
				Piatto p = new Piatto(nomePiatto, idConcorrente, codicePiatto);
				piatti.add(p);
				c.aggiungiPiatto(p);
				return codicePiatto;
			}
		}
		return -1;
	}
	
	public Piatto cercaPiatto(int idPiatto) {
		for(Piatto p : piatti){
			if(p.getIdPiatto()==idPiatto){
				return p;
			}
		}
		return null;
	}
	
	public void aggiungiIngredientePiatto(int idPiatto, String ingrediente) throws EccezioneIngredienteDuplicato {
		for(Piatto p : piatti){
			if(p.getIdPiatto()==idPiatto){
				for(String ing : p.getIngredienti()){
					if(ing.compareTo(ingrediente)==0){
						throw new EccezioneIngredienteDuplicato();
					}
				}
				p.aggiungiIngrediente(ingrediente);
			}
		}
	}
	
	public Collection<Piatto> elencoPiattiPerNome() {
		Collections.sort(piatti);
		return piatti;
	}
	
	public Collection<Piatto> elencoPiattiPerNumeroIngredienti() {
		Collections.sort(piatti, new ComparatorePerIngredienti());
		return piatti;
	}

	public Fase definisciFase(int numero, String nome, int numeroMassimoConcorrenti) {
		if(fasi.containsKey(numero)){
			return null;
		}
		Fase f = new Fase(numero, nome, numeroMassimoConcorrenti);
		fasi.put(numero, f);
		return f;
	}
	
	public void assegnaConcorrenteFase(int numeroFase, String idConcorrente) {
		if(fasi.containsKey(numeroFase)){
			Fase ftemp = fasi.get(numeroFase);
			if(ftemp.getConcorrenti().size()==ftemp.getNumeroMassimoConcorrenti()){
				return;
			}
			for(Concorrente c : concorrenti){
				if(c.getId().compareTo(idConcorrente)==0){
					ftemp.aggiungiConcorrente(c);
				}
			}
		}
	}
	
	public void definisciSfidaFase(int numeroFase, String idConcorrente1, int idPiatto1, String idConcorrente2, int idPiatto2, String esito) {
		Concorrente c1=null;
		Concorrente c2=null;
		Piatto p1=null;
		Piatto p2=null;
		if(fasi.containsKey(numeroFase)){
			Fase ftemp = fasi.get(numeroFase);
			for(Concorrente c : concorrenti){
				if(c.getId().compareTo(idConcorrente1)==0){
					c1=c;
					for(Piatto p : c1.getPiatti()){
						if(p.getIdPiatto()==idPiatto1){
							p1=p;
						}
					}
				}
				if(c.getId().compareTo(idConcorrente2)==0){
					c2=c;
					for(Piatto p : c2.getPiatti()){
						if(p.getIdPiatto()==idPiatto2){
							p2=p;
						}
					}
				}
			}
			if(c1==null || c2==null || p1==null || p2==null){
				return;
			}
			Sfida s = new Sfida(ftemp, c1, c2, p1, p2, esito);
			ftemp.aggiungiSfida(s);
			
		}
	}
	
	public String descriviSfideFase(int numeroFase) {
		String descrizioni="";
		if(fasi.containsKey(numeroFase)){
			for(Sfida s : fasi.get(numeroFase).getSfide()){
				descrizioni+=s.descriviSfida()+"\n";
			}
		}
		return descrizioni;
	}

	public String descriviSfide() {
		String descrizioni="";
		List<Fase> listaFasi = new LinkedList<Fase>();
		listaFasi.addAll(fasi.values());
		Collections.sort(listaFasi);
		for(Fase f : listaFasi){
			for(Sfida s : f.getSfide()){
				descrizioni += s.descriviSfida()+"\n";
			}
		}
		return descrizioni;
	}

	public String determinaVincitoreSfida(String idConcorrente1, String idConcorrente2) {
		List<Fase> listaFasi = new LinkedList<Fase>();
		listaFasi.addAll(fasi.values());
		for(Fase f : listaFasi){
			for(Sfida s : f.getSfide()){
				if(s.getConcorrente1().getId().compareTo(idConcorrente1)==0 && s.getConcorrente2().getId().compareTo(idConcorrente2)==0){
					return s.getVincitore();
				}
				if(s.getConcorrente1().getId().compareTo(idConcorrente2)==0 && s.getConcorrente2().getId().compareTo(idConcorrente1)==0){
					return s.getVincitore();
				}
			}
		}
		return null;
	}

	public void leggiDaFile(String nomeFile) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(nomeFile));
		String line;
		
		while((line=br.readLine())!=null){
			String [] riga = line.split(";");
			if(riga[0].compareTo("C")==0 && riga.length==4){
				this.iscriviConcorrente(riga[1], riga[2], riga[3]);
			}
			if(riga[0].compareTo("P")==0 && riga.length==3){
				this.registraPiattoConcorrente(riga[1], riga[2]);
			}
		}
		br.close();
	}
	
}









package polichef;

public class Sfida {
	
	private Fase fase;
	private Concorrente concorrente1;
	private Concorrente concorrente2;
	private Piatto piatto1;
	private Piatto piatto2;
	private String esito;

	public Sfida(Fase fase, Concorrente concorrente1, Concorrente concorrente2,
			Piatto piatto1, Piatto piatto2, String esito) {
		this.fase = fase;
		this.concorrente1 = concorrente1;
		this.concorrente2 = concorrente2;
		this.piatto1 = piatto1;
		this.piatto2 = piatto2;
		this.esito = esito;
	}
	
	public String descriviSfida(){
		String idC1= concorrente1.getId();
		String idC2= concorrente2.getId();
		int idP1 = piatto1.getIdPiatto();
		int idP2 = piatto2.getIdPiatto();
		
		return idC1+", "+idP1+", "+idC2+", "+idP2+", "+esito;
	}
	
	
	public Fase getFase() {
		return fase;
	}

	public Concorrente getConcorrente1() {
		return concorrente1;
	}

	public Concorrente getConcorrente2() {
		return concorrente2;
	}

	public Piatto getPiatto1() {
		return piatto1;
	}

	public Piatto getPiatto2() {
		return piatto2;
	}

	public String getEsito() {
		return esito;
	}
	
	public String getVincitore(){
		String [] risultato = esito.split("-");
		if(Integer.parseInt(risultato[0])>Integer.parseInt(risultato[1])){
			return concorrente1.getId();
		}
		if(Integer.parseInt(risultato[0])<Integer.parseInt(risultato[1])){
			return concorrente2.getId();
		}
		return null;
	}
	
	


}

package Model;

import java.time.LocalDate;
import java.time.Period;

public class Tilmelding {

	private LocalDate startDato;
	private LocalDate slutDato;
	private Deltager deltager;
	private Indkvartering indkvartering;

	/**
	 * Constructor
	 * @param deltager
	 * @param startDato
	 * @param slutDato
	 */
	public Tilmelding(Deltager deltager, LocalDate startDato, LocalDate slutDato, Indkvartering indkvartering) {
		this.deltager = deltager;
		this.startDato = startDato;
		this.slutDato = slutDato;
		this.indkvartering = indkvartering;
	}

	/**
	 * Metode der returnerer hvor mange dage en tilmelding gælder for
	 * @return
	 */
	public long getPeriode() {
		Period periode = Period.between(getStartDato(), getSlutDato());		
		long periodeIDage = periode.getDays() + 1;	//Vi plusser med 1 for at inkludere slutdatoen.
		return periodeIDage;
	}


	/**
	 * Metode der returnerer hvilke deltager som tilmelding er tilknyttet
	 * @return
	 */
	public Deltager getDeltager() {
		return this.deltager;
	}

	/**
	 * 
	 * @param deltager
	 */
	public void setDeltager(Deltager deltager) {
		this.deltager = deltager;
	}

	/**
	 * @return the startDato
	 */
	public LocalDate getStartDato()
	{
		return startDato;
	}

	/**
	 * @param startDato the startDato to set
	 */
	public void setStartDato(LocalDate startDato)
	{
		this.startDato = startDato;
	}

	/**
	 * @return the slutDato
	 */
	public LocalDate getSlutDato()
	{
		return slutDato;
	}

	/**
	 * @param slutDato the slutDato to set
	 */
	public void setSlutDato(LocalDate slutDato)
	{
		this.slutDato = slutDato;
	}
	
	public Indkvartering getIndkvartering() {
		return this.indkvartering;
	}
	
	/**
	 * Metode til at udregne den samlede pris for deltageren.
	 * @return samletPris
	 */
	public double udregnSamletPris() {
		double samletPris = 0.0;
		if(this.getIndkvartering().getHotelbooking() != null) {
			samletPris = deltager.getPrisgruppe().getPris() * this.getPeriode() + this.getIndkvartering().getHotelbooking().udregnHotelPris(); 
		}
		else {
			samletPris = deltager.getPrisgruppe().getPris() * this.getPeriode();
		}
		
		if(this.getDeltager().getLedsager() != null) {
			for (Udflugt udflugt : getDeltager().getLedsager().getUdflugter()) {
				samletPris += udflugt.getPris();
			}
		}
		
		return samletPris;
	}

}
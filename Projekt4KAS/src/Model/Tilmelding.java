package Model;

import java.time.LocalDate;

public class Tilmelding {

	private LocalDate startDato;
	private LocalDate slutDato;
	private Deltager deltager;

	/**
	 * 
	 * @param deltager
	 * @param startDato
	 * @param slutDato
	 */
	public Tilmelding(Deltager deltager, LocalDate startDato, LocalDate slutDato) {

	}

	public LocalDate getDatoer() {
		return startDato;
	}

	public LocalDate getPeriode() {
		return slutDato;
	}

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
	
	

}
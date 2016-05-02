package Model;

import java.time.LocalDate;
import java.time.Period;

public class Indkvartering
{

	private Adresse adresse;
	private LocalDate startDato;
	private LocalDate slutDato;
	private Hotelbooking hotelbooking;	



	public Indkvartering(LocalDate startDato, LocalDate slutDato, String vej, int nr, String etage, int postNr, String by, String land)
	{
		this.startDato = startDato;
		this.slutDato = slutDato;
		this.adresse = new Adresse(vej, nr, etage, postNr, by, land);
	}
	
	public Indkvartering(LocalDate startDato, LocalDate slutDato) {
		this.startDato = startDato;
		this.slutDato = slutDato;
		this.hotelbooking = null;
		this.adresse = null;
	}


	/**
	 * 
	 * @return hotelbooking
	 */
	public Hotelbooking getHotelbooking() {
		return hotelbooking;
	}

	/**
	 * 
	 * @param hotelbooking
	 */
	public void setHotelbooking(Hotelbooking hotelbooking) {
		this.hotelbooking = hotelbooking;
	}
	
	/**
	 * @return adresse
	 */
	public Adresse getAdresse()
	{
		return adresse;
	}

	/**
	 * @return startDato
	 */
	public LocalDate getStartDato()
	{
		return startDato;
	}

	/**
	 * @param startDato
	 *        
	 */
	public void setStartDato(LocalDate startDato)
	{
		this.startDato = startDato;
	}

	/**
	 * @return slutDato
	 */
	public LocalDate getSlutDato()
	{
		return slutDato;
	}

	/**
	 * @param slutDato
	 *            
	 */
	public void setSlutDato(LocalDate slutDato)
	{
		this.slutDato = slutDato;
	}
	
	/**
	 * Beregner perioden imellem start og slutdato
	 * @return
	 */
	public long getPeriode() {
		Period periode = Period.between(this.getStartDato(), this.getSlutDato());		
		long nætter = periode.getDays();
		return nætter;
}
}
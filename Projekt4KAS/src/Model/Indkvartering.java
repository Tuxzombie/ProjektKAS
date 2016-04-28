package Model;

import java.time.LocalDate;

public class Indkvartering
{

	private Adresse adresse;
	private LocalDate startDato;
	private LocalDate slutDato;
	private Hotelbooking hotelbooking;	



	public Indkvartering(LocalDate startDato, LocalDate slutDato, String vej, int nr, String etage, int postNr, String by, String land, Hotelbooking hotelbooking)
	{
		this.startDato = startDato;
		this.slutDato = slutDato;
		this.adresse = new Adresse(vej, nr, etage, postNr, by, land);
		this.hotelbooking = hotelbooking;
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
	 * @param adresse
	 *  
	 */
	public void setAdresse(Adresse adresse)
	{
		this.adresse = adresse;
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

}
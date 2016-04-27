package Model;

import java.time.LocalDate;

public class Indkvartering
{

	private Adresse adresse;
	private LocalDate startDato;
	private LocalDate slutDato;

	public Indkvartering()
	{
		// TODO - implement Indkvartering.Indkvartering

	}

	/**
	 * @return the adresse
	 */
	public Adresse getAdresse()
	{
		return adresse;
	}

	/**
	 * @param adresse
	 *            the adresse to set
	 */
	public void setAdresse(Adresse adresse)
	{
		this.adresse = adresse;
	}

	/**
	 * @return the startDato
	 */
	public LocalDate getStartDato()
	{
		return startDato;
	}

	/**
	 * @param startDato
	 *            the startDato to set
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
	 * @param slutDato
	 *            the slutDato to set
	 */
	public void setSlutDato(LocalDate slutDato)
	{
		this.slutDato = slutDato;
	}

}
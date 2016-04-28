package Model;

import java.time.LocalDate;

public class Foredrag {

	private String titel;
	private String lokalitet;
	private LocalDate startDato;
	private LocalDate slutDato;

	/** Constructor
	 * @param titel
	 * @param lokalitet
	 * @param startDato
	 * @param slutDato
	 */
	public Foredrag(String titel, String lokalitet, LocalDate startDato, LocalDate slutDato)
	{
		this.titel = titel;
		this.lokalitet = lokalitet;
		this.startDato = startDato;
		this.slutDato = slutDato;
	}

	/**
	 * @return titel
	 */
	public String getTitel()
	{
		return titel;
	}

	/**
	 * @param titel
	 */
	public void setTitel(String titel)
	{
		this.titel = titel;
	}

	/**
	 * @return lokalitet
	 */
	public String getLokalitet()
	{
		return lokalitet;
	}

	/**
	 * @param lokalitet
	 */
	public void setLokalitet(String lokalitet)
	{
		this.lokalitet = lokalitet;
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
	 */
	public void setSlutDato(LocalDate slutDato)
	{
		this.slutDato = slutDato;
	}
	
	

}
package Model;

import java.time.LocalDate;

public class Foredrag {

	private String titel;
	private String lokalitet;
	private LocalDate startDato;
	private LocalDate slutDato;

	/**
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
	 * @return the titel
	 */
	public String getTitel()
	{
		return titel;
	}

	/**
	 * @param titel the titel to set
	 */
	public void setTitel(String titel)
	{
		this.titel = titel;
	}

	/**
	 * @return the lokalitet
	 */
	public String getLokalitet()
	{
		return lokalitet;
	}

	/**
	 * @param lokalitet the lokalitet to set
	 */
	public void setLokalitet(String lokalitet)
	{
		this.lokalitet = lokalitet;
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
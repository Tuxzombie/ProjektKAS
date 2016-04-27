package Model;

import java.time.LocalDate;

public class Udflugt {

	private String lokalitet;
	private double pris;
	private LocalDate startDato;
	private LocalDate slutDato;
	private boolean hasFrokost;

	/**
	 * 
	 * @param lokalitet
	 * @param pris
	 * @param startDato
	 * @param slutDato
	 * @param hasFrokost
	 */
	public Udflugt(String lokalitet, double pris, LocalDate startDato, LocalDate slutDato, boolean hasFrokost) {

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
	 * @return the pris
	 */
	public double getPris()
	{
		return pris;
	}

	/**
	 * @param pris the pris to set
	 */
	public void setPris(double pris)
	{
		this.pris = pris;
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

	/**
	 * @return the hasFrokost
	 */
	public boolean isHasFrokost()
	{
		return hasFrokost;
	}

	/**
	 * @param hasFrokost the hasFrokost to set
	 */
	public void setHasFrokost(boolean hasFrokost)
	{
		this.hasFrokost = hasFrokost;
	}
	
	

}
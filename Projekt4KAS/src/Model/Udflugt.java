package Model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Udflugt {

	private String lokalitet;
	private String beskrivelse;
	private double pris;
	private LocalDate startDato;
	private LocalDate slutDato;
	private boolean hasFrokost;
	private ArrayList<Ledsager> ledsagere;

	/**
	 * 
	 * @param lokalitet
	 * @param pris
	 * @param startDato
	 * @param slutDato
	 * @param hasFrokost
	 */
	public Udflugt(String lokalitet, String beskrivelse, double pris, LocalDate startDato, LocalDate slutDato, boolean hasFrokost) {
		this.lokalitet = lokalitet;
		this.pris = pris;
		this.beskrivelse = beskrivelse;
		this.pris = pris;
		this.startDato = startDato;
		this.slutDato = slutDato;
		this.hasFrokost = hasFrokost;
		ledsagere = new ArrayList<>();
	}
	
	/**
	 * 
	 * @return
	 */
	public ArrayList<Ledsager> getLedsagere() {
		return new ArrayList<>(ledsagere);
	}
	
	/**
	 * 
	 * @param ledsager
	 */
	public void addLedsager(Ledsager ledsager) {
		ledsagere.add(ledsager);
	}
	
	/**
	 * 
	 * @param ledsager
	 */
	public void removeLedsager(Ledsager ledsager) {
		ledsagere.remove(ledsager);
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
		
	/**
	 * @return the beskrivelse
	 */
	public String getBeskrivelse()
	{
		return beskrivelse;
	}

	/**
	 * @param beskrivelse the beskrivelse to set
	 */
	public void setBeskrivelse(String beskrivelse)
	{
		this.beskrivelse = beskrivelse;
	}

	@Override
	public String toString() {
		String s = "";
		if(hasFrokost) {
			s = this.beskrivelse + ", " + this.lokalitet + ", inkl. frokost, " + this.pris + " kr."; 
		}
		else s = this.beskrivelse + ", " + this.lokalitet + ", excl. frokost, " + this.pris + " kr."; 
		return s;
	}

}
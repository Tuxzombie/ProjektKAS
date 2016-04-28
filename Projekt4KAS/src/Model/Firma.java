package Model;

import java.util.ArrayList;

public class Firma
{

	private String navn;
	private int cvrNr;
	private Adresse adresse;
	private ArrayList<Deltager> medarbejdere;

	/**
	 * @param navn
	 * @param cvrNr
	 * @param adresse
	 */
	public Firma(String navn, int cvrNr, Adresse adresse, String vej, int nr, String etage, int postNr, String land, String by)
	{
		this.navn = navn;
		this.cvrNr = cvrNr;
		this.adresse = new Adresse(vej, nr, etage, postNr, land, by);
		medarbejdere = new ArrayList<Deltager>();
	}

	/**
	 * Metode til at hente medarbejderlisten
	 * 
	 * @return
	 */

	public ArrayList<Deltager> getMedarbejdere()
	{
		return new ArrayList<>(medarbejdere);
	}

	/**
	 * 
	 * @param medarbejder
	 */
	public void addMedarbejder(Deltager medarbejder)
	{
		medarbejdere.add(medarbejder);
	}

	/**
	 * 
	 * @param medarbejder
	 */
	public void removeMedarbejder(Deltager medarbejder)
	{
		medarbejdere.remove(medarbejder);
	}

	/**
	 * @return navn
	 */
	public String getNavn()
	{
		return navn;
	}

	/**
	 * @param navn
	 *            the navn to set
	 */
	public void setNavn(String navn)
	{
		this.navn = navn;
	}

	/**
	 * @return the cvrNr
	 */
	public int getCvrNr()
	{
		return cvrNr;
	}

	/**
	 * @param cvrNr
	 *            the cvrNr to set
	 */
	public void setCvrNr(int cvrNr)
	{
		this.cvrNr = cvrNr;
	}

	/**
	 * @param adresse
	 *            the adresse to set
	 */
	public void setAdresse(Adresse adresse)
	{
		this.adresse = adresse;
	}

	public Adresse getAdresse()
	{
		return this.adresse;
	}

}
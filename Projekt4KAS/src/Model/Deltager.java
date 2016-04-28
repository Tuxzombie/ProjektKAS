package Model;

import java.util.ArrayList;

public class Deltager
{

	private String navn;
	private Adresse adresse;
	private int telefonNr;
	private Ledsager ledsager;
	private ArrayList<Tilmelding> tilmeldinger;
	private Prisgruppe prisgruppe;
	private Firma firma;

	/**
	 * @param navn
	 * @param adresse
	 * @param telefonNr
	 * @param ledsager
	 * @param prisgruppe
	 */
	public Deltager(String navn, int telefonNr, Prisgruppe prisgruppe, String vej, int nr, String etage, int postNr, String by, String land)
	{
		this.navn = navn;
		tilmeldinger = new ArrayList<>();
		this.adresse = new Adresse(vej, nr, etage, postNr, land, by);
		this.telefonNr = telefonNr;
		this.prisgruppe = prisgruppe;
	}

	public Firma getFirma() {
		return firma;
	}

	public void setFirma(Firma firma) {
		this.firma = firma;
	}

	/**
	 * Metode til at hente liste over deltagerens tilmeldinger
	 * 
	 * @return
	 */
	public ArrayList<Tilmelding> getTilmeldinger()
	{
		return new ArrayList<>(tilmeldinger);
	}

	/**
	 *  Metode til at tilføje tilmeldinger til listen over deltagerens tilmeldinger
	 * 
	 * @param tilmeldinger
	 */
	public void addTilmelding(Tilmelding tilmelding)
	{
		tilmeldinger.add(tilmelding);
	}

	/**
	 * Metode til at fjerne en tilmelding fra deltagerens liste over tilmeldinger
	 * 
	 * @param tilmelding
	 */
	public void removeTilmelding(Tilmelding tilmelding)
	{
		tilmeldinger.remove(tilmelding);
	}

	/**
	 * 
	 * @param prisgruppe
	 */
	public void setPrisgruppe(Prisgruppe prisgruppe)
	{
		this.prisgruppe = prisgruppe;
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
	 * @return adresse
	 */
	public Adresse getAdresse()
	{
		return adresse;
	}

	/**
	 * @return telefonNr
	 */
	public int getTelefonNr()
	{
		return telefonNr;
	}

	/**
	 * @param telefonNr
	 *            the telefonNr to set
	 */
	public void setTelefonNr(int telefonNr)
	{
		this.telefonNr = telefonNr;
	}

	/**
	 * @return ledsager
	 */
	public Ledsager getLedsager()
	{
		return ledsager;
	}

	/**
	 * @param ledsager
	 *            the ledsager to set
	 */
	public void setLedsager(Ledsager ledsager)
	{
		this.ledsager = ledsager;
	}
	
	/**
	 * @return prisgruppe
	 */
	public Prisgruppe getPrisgruppe()
	{
		return prisgruppe;
	}


}
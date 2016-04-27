package Model;

import java.util.ArrayList;

public class Deltager
{

	private String navn;
	private Adresse adresse;
	private int telefonNr;
	private Ledsager ledsager;
	private Indkvartering indkvartering;
	private Tilmelding tilmelding;
	private ArrayList<Miljøkonference> miljøkonferencer;
	private Prisgruppe prisgruppe;

	/**
	 * @param navn
	 * @param adresse
	 * @param telefonNr
	 * @param ledsager
	 * @param indkvartering
	 * @param tilmelding
	 * @param prisgruppe
	 */
	public Deltager(String navn, Adresse adresse, int telefonNr, Ledsager ledsager, Indkvartering indkvartering, Tilmelding tilmelding, Prisgruppe prisgruppe)
	{
		this.navn = navn;
		//her kunne det være smartere at oprette aredess i denne constructor istedet for at få den genereret ind
		this.adresse = adresse;
		this.telefonNr = telefonNr;
		this.ledsager = ledsager;
		this.indkvartering = indkvartering;
		this.tilmelding = tilmelding;
		this.prisgruppe = prisgruppe;
	}

	/**
	 * Metode til at hente liste over miljøkonferencer
	 * 
	 * @return
	 */
	public ArrayList<Miljøkonference> getMiljøkonferencer()
	{
		return new ArrayList<>(miljøkonferencer);
	}

	/**
	 *  metode til at tilføje miljøkonference til listen
	 * 
	 * @param miljøkonference
	 */
	public void addMiljøkonference(Miljøkonference miljøkonference)
	{
		miljøkonferencer.add(miljøkonference);

	}

	/**
	 * metode til at fjerne en miljøkonference fra listen
	 * 
	 * @param miljøkonference
	 */
	public void removeMiljøkonference(Miljøkonference miljøkonference)
	{
		miljøkonferencer.remove(miljøkonference);

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
	 * @return the navn
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
	 * @return the telefonNr
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
	 * @return the ledsager
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
	 * @return the indkvartering
	 */
	public Indkvartering getIndkvartering()
	{
		return indkvartering;
	}

	/**
	 * @param indkvartering
	 *            the indkvartering to set
	 */
	public void setIndkvartering(Indkvartering indkvartering)
	{
		this.indkvartering = indkvartering;
	}

	/**
	 * @return the tilmelding
	 */
	public Tilmelding getTilmelding()
	{
		return tilmelding;
	}

	/**
	 * @param tilmelding
	 *            the tilmelding to set
	 */
	public void setTilmelding(Tilmelding tilmelding)
	{
		this.tilmelding = tilmelding;
	}

	/**
	 * @return the prisgruppe
	 */
	public Prisgruppe getPrisgruppe()
	{
		return prisgruppe;
	}


}
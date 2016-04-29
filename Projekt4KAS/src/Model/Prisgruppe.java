package Model;

import java.util.ArrayList;

public class Prisgruppe
{

	private String navn;
	private double pris;
	private ArrayList<Deltager> deltagere;

	/**
	 * Constructor
	 * 
	 * @param navn
	 * @param pris
	 */
	public Prisgruppe(String navn, double pris)
	{
		this.navn = navn;
		this.pris = pris;
		deltagere = new ArrayList<>();
	}
	
	

	@Override
	public String toString()
	{
		return navn + " " + "(" + pris + ")";
	}



	/**
	 * Metode der returnerer liste over prisgruppens deltagere
	 * 
	 * @return
	 */
	public ArrayList<Deltager> getDeltagere()
	{
		return new ArrayList<>(deltagere);
	}

	/**
	 * Metode til at tilføje en deltager til listen over prisgruppens deltagere
	 * 
	 * @param deltager
	 */
	public void addDeltager(Deltager deltager)
	{
		deltagere.add(deltager);
	}

	/**
	 * Metode til at fjerne en deltager fra listen over prisgruppens deltagere
	 * 
	 * @param deltager
	 */
	public void removeDeltager(Deltager deltager)
	{
		deltagere.remove(deltager);
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
	 */
	public void setNavn(String navn)
	{
		this.navn = navn;
	}

	/**
	 * @return pris
	 */
	public double getPris()
	{
		return pris;
	}

	/**
	 * @param pris
	 */
	public void setPris(double pris)
	{
		this.pris = pris;
	}

}
package Model;

import java.util.ArrayList;

public class Hotel
{

	private String navn;
	private Adresse adresse;
	private double prisEnkeltVærelse;
	private double prisDobbeltVærelse;
	private ArrayList<Facilitet> faciliteter;
	private ArrayList<Hotelbooking> hotelbookinger;

	/** Constructor
	 * @param navn
	 * @param prisEnkeltVærelse
	 * @param prisDobbeltVærelse
	 */
	public Hotel(String navn, double prisEnkeltVærelse, double prisDobbeltVærelse, String vej, int nr, String etage, int postNr, String land, String by)
	{
		this.navn = navn;
		this.adresse = new Adresse(vej, nr, etage, postNr, land, by);
		this.prisEnkeltVærelse = prisEnkeltVærelse;
		this.prisDobbeltVærelse = prisDobbeltVærelse;
		faciliteter = new ArrayList<Facilitet>();
		hotelbookinger = new ArrayList<Hotelbooking>();
	}
	
	public Adresse getAdresse() {
		return this.adresse;
	}

	/**
	 * 
	 * @param facilitet
	 */
	public void addFacilitet(Facilitet facilitet)
	{
		faciliteter.add(facilitet);
	}

	/**
	 * 
	 * @param dacilitet
	 */
	public void removeFacilitet(Facilitet facilitet)
	{
		faciliteter.remove(facilitet);
	}

	/**
	 * 
	 * @return faciliteter
	 */
	public ArrayList<Facilitet> getFaciliteter()
	{
		return new ArrayList<>(faciliteter);
	}

	/**
	 * 
	 * @param hotelbooking
	 */
	public void addHotelbooking(Hotelbooking hotelbooking)
	{
		hotelbookinger.add(hotelbooking);
	}

	/**
	 * 
	 * @param hotelbooking
	 */
	public void removeHotelbooking(Hotelbooking hotelbooking)
	{
		hotelbookinger.remove(hotelbooking);
	}

	public ArrayList<Hotelbooking> getHotelbookinger()
	{
		return new ArrayList<>(hotelbookinger);
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
	 *         
	 */
	public void setNavn(String navn)
	{
		this.navn = navn;
	}

	/**
	 * @return prisEnkeltVærelse
	 */
	public double getPrisEnkeltVærelse()
	{
		return prisEnkeltVærelse;
	}

	/**
	 * @param prisEnkeltVærelse
	 *         
	 */
	public void setPrisEnkeltVærelse(double prisEnkeltVærelse)
	{
		this.prisEnkeltVærelse = prisEnkeltVærelse;
	}

	/**
	 * @return prisDobbeltVærelse
	 */
	public double getPrisDobbeltVærelse()
	{
		return prisDobbeltVærelse;
	}

	/**
	 * @param prisDobbeltVærelse
	 *         
	 */
	public void setPrisDobbeltVærelse(double prisDobbeltVærelse)
	{
		this.prisDobbeltVærelse = prisDobbeltVærelse;
	}


}
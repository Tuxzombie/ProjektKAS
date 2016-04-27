package Model;

import java.util.ArrayList;

public class Hotel
{

	private String navn;
	private double prisEnkeltVærelse;
	private double prisDobbeltVærelse;
	private ArrayList<Facilitet> faciliteter;
	private ArrayList<Hotelbooking> hotelbookinger;

	/**
	 * @param navn
	 * @param prisEnkeltVærelse
	 * @param prisDobbeltVærelse
	 */
	public Hotel(String navn, double prisEnkeltVærelse, double prisDobbeltVærelse)
	{
		this.navn = navn;
		this.prisEnkeltVærelse = prisEnkeltVærelse;
		this.prisDobbeltVærelse = prisDobbeltVærelse;
	}

	/**
	 * 
	 * @param facilitet
	 */
	public void addFacilitet(Facilitet facilitet)
	{
		// TODO - implement Hotel.addFacilitet

	}

	/**
	 * 
	 * @param dacilitet
	 */
	public void removeFacilitet(Facilitet dacilitet)
	{
		// TODO - implement Hotel.removeFacilitet

	}

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
		// TODO - implement Hotel.addHotelbooking

	}

	/**
	 * 
	 * @param hotelbooking
	 */
	public void removeHotelbooking(Hotelbooking hotelbooking)
	{
		// TODO - implement Hotel.removeHotelbooking

	}

	public ArrayList<Hotelbooking> getHotelbookinger()
	{
		return this.hotelbookinger;
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
	 * @return the prisEnkeltVærelse
	 */
	public double getPrisEnkeltVærelse()
	{
		return prisEnkeltVærelse;
	}

	/**
	 * @param prisEnkeltVærelse
	 *            the prisEnkeltVærelse to set
	 */
	public void setPrisEnkeltVærelse(double prisEnkeltVærelse)
	{
		this.prisEnkeltVærelse = prisEnkeltVærelse;
	}

	/**
	 * @return the prisDobbeltVærelse
	 */
	public double getPrisDobbeltVærelse()
	{
		return prisDobbeltVærelse;
	}

	/**
	 * @param prisDobbeltVærelse
	 *            the prisDobbeltVærelse to set
	 */
	public void setPrisDobbeltVærelse(double prisDobbeltVærelse)
	{
		this.prisDobbeltVærelse = prisDobbeltVærelse;
	}


}
package Model;

import java.util.ArrayList;

public class Hotelbooking {

	private Hotel hotel;
	private Indkvartering indkvartering;
	private ArrayList<Facilitet> valgteFaciliteter;
	private boolean isDobbeltVærelse;

	/**
	 * Constructor
	 * @param hotel
	 * @param indkvartering
	 * @param isDobbeltVærelse
	 */
	public Hotelbooking(Hotel hotel, Indkvartering indkvartering, boolean isDobbeltVærelse) {
		this.hotel = hotel;
		this.indkvartering = indkvartering;
		this.isDobbeltVærelse = isDobbeltVærelse;
		valgteFaciliteter = new ArrayList<>();
	}

	/**
	 * 
	 * @return valgteFaciliteter
	 */
	public ArrayList<Facilitet> getValgteFaciliteter() {
		return new ArrayList<>(valgteFaciliteter);
	}

	/**
	 * Metode til at tilføje en facilitet til de valgte faciliteter
	 * @param facilitet
	 */
	public void addFacilitet(Facilitet facilitet) {
		valgteFaciliteter.add(facilitet);
	}

	/**
	 * 
	 * @param facilitet
	 */
	public void removeFacilitet(Facilitet facilitet) {
		valgteFaciliteter.remove(facilitet);
	}

	/**
	 * 
	 * @param hotel
	 */
	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	/**
	 * 
	 * @return hotel
	 */
	public Hotel getHotel() {
		return this.hotel;
	}

	public double udregnHotelPris() {
		// TODO - implement Hotelbooking.udregnHotelPris
		return 0.0;
	}

	/**
	 * @return indkvartering
	 */
	public Indkvartering getIndkvartering()
	{
		return indkvartering;
	}

	/**
	 * @param indkvartering
	 */
	public void setIndkvartering(Indkvartering indkvartering)
	{
		this.indkvartering = indkvartering;
	}

	/**
	 * @return isDobbeltVærelse
	 */
	public boolean isDobbeltVærelse()
	{
		return isDobbeltVærelse;
	}

	/**
	 * @param isDobbeltVærelse
	 */
	public void setDobbeltVærelse(boolean isDobbeltVærelse)
	{
		this.isDobbeltVærelse = isDobbeltVærelse;
	}	
}
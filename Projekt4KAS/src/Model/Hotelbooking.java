package Model;

import java.util.ArrayList;

public class Hotelbooking {

	private Hotel hotel;
	private Indkvartering indkvartering;
	private ArrayList<Facilitet> valgteFaciliteter;
	private boolean isDobbeltVærelse;

	/**
	 * 
	 * @param hotel
	 * @param indkvartering
	 * @param isDobbeltVærelse
	 */
	public Hotelbooking(Hotel hotel, Indkvartering indkvartering, boolean isDobbeltVærelse) {
		// TODO - implement Hotelbooking.Hotelbooking
		
	}

	public ArrayList<Facilitet> getValgteFaciliteter() {
		return this.valgteFaciliteter;
	}

	/**
	 * 
	 * @param facilitet
	 */
	public void addFacilitet(Facilitet facilitet) {
		// TODO - implement Hotelbooking.addFacilitet
		
	}

	/**
	 * 
	 * @param facilitet
	 */
	public void removeFacilitet(Facilitet facilitet) {
		// TODO - implement Hotelbooking.removeFacilitet
		
	}

	/**
	 * 
	 * @param hotel
	 */
	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public Hotel getHotel() {
		return this.hotel;
	}

	public double udregnHotelPris() {
		// TODO - implement Hotelbooking.udregnHotelPris
		return 0.0;
	}

	/**
	 * @return the indkvartering
	 */
	public Indkvartering getIndkvartering()
	{
		return indkvartering;
	}

	/**
	 * @param indkvartering the indkvartering to set
	 */
	public void setIndkvartering(Indkvartering indkvartering)
	{
		this.indkvartering = indkvartering;
	}

	/**
	 * @return the isDobbeltVærelse
	 */
	public boolean isDobbeltVærelse()
	{
		return isDobbeltVærelse;
	}

	/**
	 * @param isDobbeltVærelse the isDobbeltVærelse to set
	 */
	public void setDobbeltVærelse(boolean isDobbeltVærelse)
	{
		this.isDobbeltVærelse = isDobbeltVærelse;
	}

	/**
	 * @param valgteFaciliteter the valgteFaciliteter to set
	 */
	public void setValgteFaciliteter(ArrayList<Facilitet> valgteFaciliteter)
	{
		this.valgteFaciliteter = valgteFaciliteter;
	}
	
	

}
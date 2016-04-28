package Storage;

import java.util.ArrayList;

import Model.Deltager;
import Model.Firma;
import Model.Hotel;
import Model.Miljøkonference;

public class Storage
{
	private static ArrayList<Hotel> hoteller = new ArrayList<>();
	private static ArrayList<Miljøkonference> miljøkonferencer = new ArrayList<>();
	private static ArrayList<Firma> firmaer = new ArrayList<>();
	private static ArrayList<Deltager> deltagere = new ArrayList<>();
	
	//----------------------------------------
	
	public static void addHotel(Hotel hotel){
		hoteller.add(hotel);
	}
	
	public static void removeHotel(Hotel hotel) {
		hoteller.remove(hotel);
	}
	
	public static ArrayList<Hotel> getHoteller() {
		return hoteller;
	}
	
	//-----------------------------------------
	
	public static void addMiljøkonference(Miljøkonference miljøkonference){
		miljøkonferencer.add(miljøkonference);
	}
	
	public static void removeMiljøkonference(Miljøkonference miljøkonference) {
		miljøkonferencer.remove(miljøkonference);
	}
	
	public static ArrayList<Miljøkonference> getMiljøkonferencer() {
		return new ArrayList<>(miljøkonferencer);
	}
	
	//-----------------------------------------
	
	public static void addFirma(Firma firma){
		firmaer.add(firma);
	}
	
	public static void removeFirma(Firma firma) {
		firmaer.remove(firma);
	}
	
	public static ArrayList<Firma> getFirmaer() {
		return  new ArrayList<>(firmaer);
	}
	
	//-----------------------------------------
	
	public static void addDeltagere(Deltager deltager){
		deltagere.add(deltager);
	}
	
	public static void removeDeltager(Deltager deltager) {
		deltagere.remove(deltager);
	}
	
	public static ArrayList<Deltager> getDeltagere() {
		return new ArrayList<>(deltagere);
	}
}

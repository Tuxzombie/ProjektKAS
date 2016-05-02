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
	
	/**
	 * gemmer et hotel i listen
	 * @param hotel
	 */
	public static void addHotel(Hotel hotel){
		hoteller.add(hotel);
	}
	
	/**
	 * fjerner et hotel i listen
	 * @param hotel
	 */
	public static void removeHotel(Hotel hotel) {
		hoteller.remove(hotel);
	}
	
	/**
	 * henter listen af hoteller
	 * @return
	 */
	public static ArrayList<Hotel> getHoteller() {
		return new ArrayList<>(hoteller);
	}
	
	//-----------------------------------------
	
	/*
	 * Gemmer en konference i listen
	 */
	public static void addMiljøkonference(Miljøkonference miljøkonference){
		miljøkonferencer.add(miljøkonference);
	}
	
	/**
	 * Sletter en kofernece fra listen
	 * @param miljøkonference
	 */
	public static void removeMiljøkonference(Miljøkonference miljøkonference) {
		miljøkonferencer.remove(miljøkonference);
	}
	
	/**
	 * Henter listen af konferencer
	 * @return
	 */
	public static ArrayList<Miljøkonference> getMiljøkonferencer() {
		return new ArrayList<>(miljøkonferencer);
	}
	
	//-----------------------------------------
	/**
	 * Gemmer et firma i listen
	 * @param firma
	 */
	public static void addFirma(Firma firma){
		firmaer.add(firma);
	}
	
	/**
	 * Sletter et firma fra listen
	 * @param firma
	 */
	public static void removeFirma(Firma firma) {
		firmaer.remove(firma);
	}
	
	/**
	 * Henter listen af firmaer
	 * @return
	 */
	public static ArrayList<Firma> getFirmaer() {
		return  new ArrayList<>(firmaer);
	}
	
	//-----------------------------------------
	/**
	 * Gemmer en deltager i listen
	 * @param deltager
	 */
	public static void addDeltagere(Deltager deltager){
		deltagere.add(deltager);
	}
	
	/**
	 * Sletter en deltager i listen
	 * @param deltager
	 */
	public static void removeDeltager(Deltager deltager) {
		deltagere.remove(deltager);
	}
	
	/**
	 * Henter listen af deltagere
	 * @return
	 */
	public static ArrayList<Deltager> getDeltagere() {
		return new ArrayList<>(deltagere);
	}
}

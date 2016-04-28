package Service;
import java.util.ArrayList;

import Model.*;

public class Service {

	private static ArrayList<Deltager> deltagere;
	
	public static void createHotel(String navn, double prisEnkeltVærelse, double prisDobbeltVærelse) {
		// TODO - implement Service.createHotel
		Hotel newHotel = new Hotel(navn, prisEnkeltVærelse, prisDobbeltVærelse);
		
	}

	public static void createMiljøkonference() {
		// TODO - implement Service.createMiljøkonference
		
	}

	public static void createFirma() {
		// TODO - implement Service.createFirma
		
	}

	public static void createDeltager() {
		// TODO - implement Service.createDeltager
//		Deltager newDeltager = new Deltager("ib", null, 123123, null, null, null, null);
//		deltagere.add(newDeltager);
		
	}

	public static void createTilmelding() {
		// TODO - implement Service.createTilmelding
		
	}

	public static void createHotelbooking() {
		// TODO - implement Service.createHotelbooking
		
	}

	public static void createIndkvartering() {
		// TODO - implement Service.createIndkvartering
		
	}

	public static void deleteHotel() {
		// TODO - implement Service.deleteHotel
		
	}

	public static void deleteMiljøkonference() {
		// TODO - implement Service.deleteMiljøkonference
		
	}

	public static void deleteFirma() {
		// TODO - implement Service.deleteFirma
		
	}

	public static void deleteDeltager() {
		// TODO - implement Service.deleteDeltager
		
	}

	public static void deleteTilmelding() {
		// TODO - implement Service.deleteTilmelding
		
	}

	public static void deleteHotelbooking() {
		// TODO - implement Service.deleteHotelbooking
		
	}

	public static void deleteIndkvartering() {
		// TODO - implement Service.deleteIndkvartering
		
	}

	public static void getMiljøkonference()
	{
	
	}
	
	public static ArrayList<Deltager> getDeltagere()
	{
		return deltagere;
	}
}
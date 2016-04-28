package Service;
import java.time.LocalDate;
import java.util.ArrayList;

import Model.*;
import Storage.Storage;


public class Service {

	public static Hotel createHotel(String navn, double prisEnkeltVærelse, double prisDobbeltVærelse, String vej, int nr, String etage, int postNr, String land, String by) {
		Hotel newHotel = new Hotel(navn, prisEnkeltVærelse, prisDobbeltVærelse, vej, nr, etage, postNr, land, by);
		Storage.addHotel(newHotel);
		return newHotel;
	}

	public static Miljøkonference createMiljøkonference(String titel, String tema, LocalDate startDato, LocalDate slutDato, String vej, int nr, String etage, int postNr, String by, String land) {
		Miljøkonference newMiljøkonference = new Miljøkonference(titel, tema, startDato, slutDato, vej, nr, etage, postNr, by, land);
		Storage.addMiljøkonference(newMiljøkonference);
		return newMiljøkonference;
	}

	public static Firma createFirma(String navn, int cvrNr, Adresse adresse, String vej, int nr, String etage, int postNr, String land, String by) {
		Firma newFirma = new Firma(navn, cvrNr, adresse, vej, nr, etage, postNr, land, by);
		Storage.addFirma(newFirma);
		return newFirma;
	}

	public static Deltager createDeltager(String navn, int telefonNr, Prisgruppe prisgruppe, String vej, int nr, String etage, int postNr, String by, String land) {
		Deltager newDeltager = new Deltager(navn, telefonNr, prisgruppe, vej, nr, etage, postNr, by, land);
		Storage.addDeltagere(newDeltager);
		return newDeltager;
	}

	public static Hotelbooking createHotelbooking(Hotel hotel, Indkvartering indkvartering, boolean isDobbeltVærelse) {
		Hotelbooking newHotelbooking = new Hotelbooking(hotel, indkvartering, isDobbeltVærelse);
		hotel.addHotelbooking(newHotelbooking);
		return newHotelbooking;
	}

	public static Indkvartering createIndkvartering(LocalDate startDato, LocalDate slutDato, String vej, int nr, String etage, int postNr, String by, String land, Hotelbooking hotelbooking) {
		Indkvartering newIndkvartering = new Indkvartering(startDato, slutDato, vej, nr, etage, postNr, by, land, hotelbooking);
		return newIndkvartering;
	}
	
	public static Ledsager createLedsager(String navn, Deltager deltager) {
		Ledsager newLedsager = new Ledsager(navn, deltager);
		deltager.setLedsager(newLedsager);
		return newLedsager;
	}
	
	public static Udflugt createUdflugt(Miljøkonference miljøkonference, String lokalitet, String beskrivelse, double pris, LocalDate startDato, LocalDate slutDato, boolean hasFrokost) {
		Udflugt newUdflugt = miljøkonference.createUdflugt(lokalitet, beskrivelse, pris, startDato, slutDato, hasFrokost);
		return newUdflugt;
	}
	
	public static Prisgruppe createPrisgruppe(Miljøkonference miljøkonference, String navn, double pris) {
		Prisgruppe newPrisgruppe = miljøkonference.createPrisgruppe(navn, pris);
		return newPrisgruppe;
	}

	public static void deleteHotel(Hotel hotel) {
		for (Hotelbooking hotelbooking: hotel.getHotelbookinger()) {
			hotelbooking.getIndkvartering().setHotelbooking(null);
		}
		Storage.removeHotel(hotel);
	}

	public static void deleteMiljøkonference(Miljøkonference miljøkonference) {
		Storage.removeMiljøkonference(miljøkonference);
	}

	public static void deleteFirma(Firma firma) {
		for (Deltager deltager : firma.getMedarbejdere()) {
			deltager.setFirma(null);
		}
		Storage.removeFirma(firma);
	}

	public static void deleteDeltager(Deltager deltager) {
		for (Tilmelding tilmelding : deltager.getTilmeldinger()) {
			tilmelding.setDeltager(null);
		}
		Storage.removeDeltager(deltager);
	}

	public static void deleteHotelbooking(Hotelbooking hotelbooking) {
		hotelbooking.getHotel().removeHotelbooking(hotelbooking);
		hotelbooking.getIndkvartering().setHotelbooking(null);
	}

	public static void deleteIndkvartering(Indkvartering indkvartering) {
		indkvartering.setHotelbooking(null);
	}
	
	public static ArrayList<Miljøkonference> getMiljøkonferencer() {
		return Storage.getMiljøkonferencer();
	} 
}
package Service;
import java.time.LocalDate;

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

	public static void createIndkvartering(LocalDate startDato, LocalDate slutDato, String vej, int nr, String etage, int postNr, String by, String land, Hotelbooking hotelbooking) {
		Indkvartering newIndkvartering = new Indkvartering(startDato, slutDato, vej, nr, etage, postNr, by, land, hotelbooking);
		// TODO
	}

	public static void deleteHotel(Hotel hotel) {
		Storage.removeHotel(hotel);
	}

	public static void deleteMiljøkonference(Miljøkonference miljøkonference) {
		Storage.removeMiljøkonference(miljøkonference);
	}

	public static void deleteFirma(Firma firma) {
		Storage.removeFirma(firma);
	}

	public static void deleteDeltager(Deltager deltager) {
		Storage.removeDeltager(deltager);
	}

	public static void deleteHotelbooking(Hotelbooking hotelbooking) {
		hotelbooking.getHotel().removeHotelbooking(hotelbooking);
		hotelbooking.getIndkvartering().setHotelbooking(null);
	}

	public static void deleteIndkvartering() {
		//TODO
	}
}
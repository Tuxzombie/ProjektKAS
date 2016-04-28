package Service;
import java.time.LocalDate;
import java.util.ArrayList;

import Model.*;
import Storage.Storage;

public class Service
{

	public static Hotel createHotel(String navn, double prisEnkeltVærelse, double prisDobbeltVærelse, String vej, int nr, String etage, int postNr, String land, String by)
	{
		Hotel newHotel = new Hotel(navn, prisEnkeltVærelse, prisDobbeltVærelse, vej, nr, etage, postNr, land, by);
		Storage.addHotel(newHotel);
		return newHotel;
	}

	public static Miljøkonference createMiljøkonference(String titel, String tema, LocalDate startDato, LocalDate slutDato, String vej, int nr, String etage, int postNr, String by, String land)
	{
		Miljøkonference newMiljøkonference = new Miljøkonference(titel, tema, startDato, slutDato, vej, nr, etage, postNr, by, land);
		Storage.addMiljøkonference(newMiljøkonference);
		return newMiljøkonference;
	}

	public static Firma createFirma(String navn, int cvrNr, Adresse adresse, String vej, int nr, String etage, int postNr, String land, String by)
	{
		Firma newFirma = new Firma(navn, cvrNr, adresse, vej, nr, etage, postNr, land, by);
		Storage.addFirma(newFirma);
		return newFirma;
	}

	public static Deltager createDeltager(String navn, int telefonNr, Prisgruppe prisgruppe, String vej, int nr, String etage, int postNr, String by, String land)
	{
		Deltager newDeltager = new Deltager(navn, telefonNr, prisgruppe, vej, nr, etage, postNr, by, land);
		Storage.addDeltagere(newDeltager);
		return newDeltager;
	}

	public static Hotelbooking createHotelbooking(Hotel hotel, Indkvartering indkvartering, boolean isDobbeltVærelse)
	{
		Hotelbooking newHotelbooking = new Hotelbooking(hotel, indkvartering, isDobbeltVærelse);
		hotel.addHotelbooking(newHotelbooking);
		return newHotelbooking;
	}

	public static Indkvartering createIndkvartering(LocalDate startDato, LocalDate slutDato, String vej, int nr, String etage, int postNr, String by, String land, Hotelbooking hotelbooking)
	{
		Indkvartering newIndkvartering = new Indkvartering(startDato, slutDato, vej, nr, etage, postNr, by, land, hotelbooking);
		return newIndkvartering;
	}

	public static Ledsager createLedsager(String navn, Deltager deltager)
	{
		Ledsager newLedsager = new Ledsager(navn, deltager);
		deltager.setLedsager(newLedsager);
		return newLedsager;
	}

	public static Udflugt createUdflugt(Miljøkonference miljøkonference, String lokalitet, String beskrivelse, double pris, LocalDate startDato, LocalDate slutDato, boolean hasFrokost)
	{
		Udflugt newUdflugt = miljøkonference.createUdflugt(lokalitet, beskrivelse, pris, startDato, slutDato, hasFrokost);
		return newUdflugt;
	}

	public static Prisgruppe createPrisgruppe(Miljøkonference miljøkonference, String navn, double pris)
	{
		Prisgruppe newPrisgruppe = miljøkonference.createPrisgruppe(navn, pris);
		return newPrisgruppe;
	}

	public static Tilmelding createTilmelding(Miljøkonference miljøkonference, Deltager deltager, LocalDate startDato, LocalDate slutDato, Indkvartering indkvartering)
	{
		Tilmelding newTilmelding = miljøkonference.createTilmelding(indkvartering, deltager, startDato, slutDato);
		deltager.addTilmelding(newTilmelding);
		return newTilmelding;
	}

	public static void deleteHotel(Hotel hotel)
	{
		for (Hotelbooking hotelbooking : hotel.getHotelbookinger())
		{
			hotelbooking.getIndkvartering().setHotelbooking(null);
		}
		Storage.removeHotel(hotel);
	}

	public static void deleteMiljøkonference(Miljøkonference miljøkonference)
	{
		Storage.removeMiljøkonference(miljøkonference);
	}

	public static void deleteFirma(Firma firma)
	{
		for (Deltager deltager : firma.getMedarbejdere())
		{
			deltager.setFirma(null);
		}
		Storage.removeFirma(firma);
	}

	public static void deleteDeltager(Deltager deltager)
	{
		for (Tilmelding tilmelding : deltager.getTilmeldinger())
		{
			tilmelding.setDeltager(null);
		}
		Storage.removeDeltager(deltager);
	}

	public static void deleteHotelbooking(Hotelbooking hotelbooking)
	{
		hotelbooking.getHotel().removeHotelbooking(hotelbooking);
		hotelbooking.getIndkvartering().setHotelbooking(null);
	}

	public static void deleteIndkvartering(Indkvartering indkvartering)
	{
		indkvartering.setHotelbooking(null);
	}

	public static void updateHotelbooking(Hotelbooking hotelbooking, Hotel hotel, Indkvartering indkvartering, boolean isDobbeltVærelse)
	{
		hotelbooking.setDobbeltVærelse(isDobbeltVærelse);
		hotelbooking.setHotel(hotel);
		hotelbooking.setIndkvartering(indkvartering);
	}

	public static void updateHotel(Hotel hotel, String navn, double prisEnkeltVærelse, double prisDobbeltVærelse, String vej, int nr, String etage, int postNr, String land, String by)
	{
		hotel.setNavn(navn);
		hotel.setPrisDobbeltVærelse(prisDobbeltVærelse);
		hotel.setPrisEnkeltVærelse(prisEnkeltVærelse);
		hotel.getAdresse().setBy(by);
		hotel.getAdresse().setEtage(etage);
		hotel.getAdresse().setLand(land);
		hotel.getAdresse().setNr(nr);
		hotel.getAdresse().setPostNr(postNr);
		hotel.getAdresse().setVej(vej);
	}

	public static void updateMiljøkonference(Miljøkonference miljøkonference, String titel, String tema, LocalDate startDato, LocalDate slutDato, String vej, int nr, String etage, int postNr, String by, String land)
	{
		miljøkonference.setSlutDato(slutDato);
		miljøkonference.setStartDato(startDato);
		miljøkonference.setTema(tema);
		miljøkonference.setTitel(titel);
		miljøkonference.getAdresse().setBy(by);
		miljøkonference.getAdresse().setEtage(etage);
		miljøkonference.getAdresse().setLand(land);
		miljøkonference.getAdresse().setPostNr(postNr);
		miljøkonference.getAdresse().setNr(nr);
		miljøkonference.getAdresse().setVej(vej);
	}

	public static void updateFirma(Firma firma, String navn, int cvrNr, Adresse adresse, String vej, int nr, String etage, int postNr, String land, String by)
	{
		firma.setCvrNr(cvrNr);
		firma.setNavn(navn);
		firma.getAdresse().setBy(by);
		firma.getAdresse().setEtage(etage);
		firma.getAdresse().setLand(land);
		firma.getAdresse().setNr(nr);
		firma.getAdresse().setPostNr(postNr);
		firma.getAdresse().setVej(vej);
	}

	public static void updateDeltager(Deltager deltager, Firma firma, Ledsager ledsager, String navn, int telefonNr, Prisgruppe prisgruppe, String vej, int nr, String etage, int postNr, String by, String land)
	{
		deltager.setNavn(navn);
		deltager.setPrisgruppe(prisgruppe);
		deltager.setTelefonNr(telefonNr);
		deltager.getAdresse().setBy(by);
		deltager.getAdresse().setEtage(etage);
		deltager.getAdresse().setLand(land);
		deltager.getAdresse().setNr(nr);
		deltager.getAdresse().setPostNr(postNr);
		deltager.getAdresse().setVej(vej);
		if (ledsager != null)
		{
			deltager.setLedsager(ledsager);
		}
		if (firma != null)
		{
			deltager.setFirma(firma);
		}
	}

	public static void updateIndkvartering(Indkvartering indkvartering, LocalDate startDato, LocalDate slutDato, String vej, int nr, String etage, int postNr, String by, String land, Hotelbooking hotelbooking)
	{
		indkvartering.setSlutDato(slutDato);
		indkvartering.setStartDato(startDato);
		indkvartering.setHotelbooking(hotelbooking);
		indkvartering.getAdresse().setBy(by);
		indkvartering.getAdresse().setEtage(etage);
		indkvartering.getAdresse().setLand(land);
		indkvartering.getAdresse().setNr(nr);
		indkvartering.getAdresse().setPostNr(postNr);
		indkvartering.getAdresse().setVej(vej);
	}

	public static void updateLedsager(Ledsager ledsager, String navn, Deltager deltager)
	{
		ledsager.setDeltager(deltager);
		ledsager.setNavn(navn);
	}

	public static void updateUdflugt(Udflugt udflugt, String lokalitet, String beskrivelse, double pris, LocalDate startDato, LocalDate slutDato, boolean hasFrokost)
	{
		udflugt.setHasFrokost(hasFrokost);
		udflugt.setLokalitet(lokalitet);
		udflugt.setPris(pris);
		udflugt.setSlutDato(slutDato);
		udflugt.setStartDato(startDato);
	}

	public static void updatePrisgruppe(Prisgruppe prisgruppe, String navn, double pris)
	{
		prisgruppe.setNavn(navn);
		prisgruppe.setPris(pris);
	}

	public static void updateTilmelding(Tilmelding tilmelding, Deltager deltager, LocalDate startDato, LocalDate slutDato, Indkvartering indkvartering)
	{
		tilmelding.setDeltager(deltager);
		tilmelding.setSlutDato(slutDato);
		tilmelding.setStartDato(startDato);
	}

	public static void addFacilitetToHotelbooking(Hotelbooking hotelbooking, Facilitet facilitet)
	{
		if (hotelbooking.getHotel().getFaciliteter().contains(facilitet))
		{
			hotelbooking.addFacilitet(facilitet);
		}
	}

	public static ArrayList<Miljøkonference> getMiljøkonferencer()
	{
		return Storage.getMiljøkonferencer();
	}

	public static void initStorage()
	{
		Hotel hotelDenHvideSvane = Service.createHotel("Den Hvide Svane", 100, 150, "Vestergade", 2, "", 9000, "Danmark", "Aalborg");
		Miljøkonference miljøkonference = Service.createMiljøkonference("Hav og himmel", "Havet", LocalDate.of(2016, 11, 1), LocalDate.of(2016, 11, 3), "Hovedgaden", 12, "1. th.", 8200, "Aarhus N", "Danmark");

		Prisgruppe prisgruppeVoksen = Service.createPrisgruppe(miljøkonference, "Voksen", 1500);
		Prisgruppe prisgruppeForedragsholder = Service.createPrisgruppe(miljøkonference, "Foredragsholder", 0);

		Deltager deltagerFinn = Service.createDeltager("Finn Madsen", 12345678, prisgruppeVoksen, "Hammergade", 1, "", 9000, "Aalborg", "Danmark");
		Deltager deltagerPeter = Service.createDeltager("Peter Sommer", 12345678, prisgruppeVoksen, "Østergade", 2, "", 9000, "Aalborg", "Danmark");
		Deltager deltagerLone = Service.createDeltager("Lone Jensen", 12345678, prisgruppeForedragsholder, "Vestergade", 3, "", 9000, "Aalborg", "Danmark");

		Indkvartering indkvarteringFinn = Service.createIndkvartering(LocalDate.of(2016, 11, 1), LocalDate.of(2016, 11, 3), "Øllegade", 68, "", 9280, "Aalborg Øst", "Danmark", null);
		Indkvartering indkvarteringPeter = Service.createIndkvartering(LocalDate.of(2016, 11, 1), LocalDate.of(2016, 11, 3), "Øllegade", 68, "", 9280, "Aalborg Øst", "Danmark", null);
		Indkvartering indkvarteringLone = Service.createIndkvartering(LocalDate.of(2016, 11, 1), LocalDate.of(2016, 11, 3), "Øllegade", 68, "", 9280, "Aalborg Øst", "Danmark", null);

		Tilmelding tilmeldingFinn = Service.createTilmelding(miljøkonference, deltagerFinn, LocalDate.of(2016, 11, 1), LocalDate.of(2016, 11, 3), indkvarteringFinn);
		Tilmelding tilmeldingPeter = Service.createTilmelding(miljøkonference, deltagerPeter, LocalDate.of(2016, 11, 1), LocalDate.of(2016, 11, 3), indkvarteringPeter);
		Tilmelding tilmeldingLone = Service.createTilmelding(miljøkonference, deltagerLone, LocalDate.of(2016, 11, 1), LocalDate.of(2016, 11, 3), indkvarteringLone);

	}
	// TODO Getter til alle arraylists i Storage

}
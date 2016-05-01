package Service;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import Model.*;
import Storage.Storage;

public class Service
{

	public static Hotel createHotel(String navn, double prisEnkeltVærelse, double prisDobbeltVærelse, String vej, int nr, String etage, int postNr, String by, String land)
	{
		Hotel newHotel = new Hotel(navn, prisEnkeltVærelse, prisDobbeltVærelse, vej, nr, etage, postNr, by, land);
		Storage.addHotel(newHotel);
		return newHotel;
	}

	public static Miljøkonference createMiljøkonference(String titel, String tema, LocalDate startDato, LocalDate slutDato, String vej, int nr, String etage, int postNr, String by, String land)
	{
		Miljøkonference newMiljøkonference = new Miljøkonference(titel, tema, startDato, slutDato, vej, nr, etage, postNr, by, land);
		Storage.addMiljøkonference(newMiljøkonference);
		return newMiljøkonference;
	}

	public static Firma createFirma(String navn, int cvrNr, String vej, int nr, String etage, int postNr, String by, String land)
	{
		Firma newFirma = new Firma(navn, cvrNr, vej, nr, etage, postNr, by, land);
		Storage.addFirma(newFirma);
		return newFirma;
	}

	public static Deltager createDeltager(String navn, int telefonNr, Prisgruppe prisgruppe, String vej, int nr, String etage, int postNr, String by, String land)
	{
		Deltager newDeltager = new Deltager(navn, telefonNr, prisgruppe, vej, nr, etage, postNr, by, land);
		Storage.addDeltagere(newDeltager);
		return newDeltager;
	}

	public static Hotelbooking createHotelbooking(Hotel hotel, boolean isDobbeltVærelse)
	{
		Hotelbooking newHotelbooking = new Hotelbooking(hotel, isDobbeltVærelse);
		hotel.addHotelbooking(newHotelbooking);
		return newHotelbooking;
	}

	public static Indkvartering createIndkvarteringMedHotelbooking(LocalDate startDato, LocalDate slutDato)
	{
		Indkvartering newIndkvartering = new Indkvartering(startDato, slutDato);
		return newIndkvartering;
	}
	
	public static Indkvartering createIndkvarteringUdenHotelbooking(LocalDate startDato, LocalDate slutDato, String vej, int nr, String etage, int postNr, String by, String land) {
		Indkvartering newIndkvartering = new Indkvartering(startDato, slutDato, vej, nr, etage, postNr, by, land);
		return newIndkvartering;
	}

	public static Facilitet createFacilitet(Hotel hotel, String facilitetnavn, double pris) {
		Facilitet newFacilitet = new Facilitet(facilitetnavn, pris);
		hotel.addFacilitet(newFacilitet);
		return newFacilitet;
	}
	
	public static void connectIndkvarteringOgHotelbooking (Indkvartering indkvartering, Hotelbooking hotelbooking) {
		indkvartering.setHotelbooking(hotelbooking);
		hotelbooking.setIndkvartering(indkvartering);
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
	
	public static void deleteFacilitet(Hotel hotel, Facilitet facilitet) {
		hotel.removeFacilitet(facilitet);
	}
	
	public static void deletePrisgruppe(Prisgruppe prisgruppe, Miljøkonference miljøkonference)
	{
		miljøkonference.removePrisgruppe(prisgruppe);
		for (Tilmelding liste : miljøkonference.getTilmeldingliste())
		{
			if (liste.getDeltager().getPrisgruppe() == prisgruppe)
			{
				liste.getDeltager().setPrisgruppe(null);
			}
		}
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

	public static void updateTilmelding(Tilmelding tilmelding, Deltager deltager, LocalDate startDato, LocalDate slutDato)
	{
			tilmelding.setStartDato(startDato);
			tilmelding.setSlutDato(slutDato);
	}

	public static void addFacilitetTilHotelbooking(Hotelbooking hotelbooking, Facilitet facilitet)
	{
			hotelbooking.addFacilitet(facilitet);
	}
	
	public static void addLedsagerTilUdflugt(Udflugt udflugt, Ledsager ledsager) {
		udflugt.addLedsager(ledsager);
		ledsager.addUdflugt(udflugt);
	}
	
	public static void addDeltagerTilFirma(Firma firma, Deltager deltager) {
		firma.addMedarbejder(deltager);
	}

	public static ArrayList<Miljøkonference> getMiljøkonferencer()
	{
		return Storage.getMiljøkonferencer();
	}
	
	public static ArrayList<Deltager> getDeltagere()
	{
		return Storage.getDeltagere();
	}
	
	public static ArrayList<Hotel> getHoteller()
	{
		return Storage.getHoteller();
	}
	
	public static ArrayList<Firma> getFirmaer() {
		return Storage.getFirmaer();
	}
	
	public static int randInt(int min, int max) {

	    Random rand = new Random();

	    int randomNum = rand.nextInt((max - min) + 1) + min;

	    return randomNum;
	}
	
	public static long randLong(long min, long max) {

	    long randomNum = ThreadLocalRandom.current().nextLong(min,max);

	    return randomNum;
	}
	
	public static LocalDate randDate()
	{
		return LocalDate.ofEpochDay(Service.randLong(LocalDate.now().minusYears(15).toEpochDay(),
									LocalDate.now().plusYears(15).toEpochDay()));
	}

	public static void initStorage()
	{
		String[] randomNames = {"Birger Alexandersen", "Adam Petersen", "Gregers Lange",
								"Gudmund Ludvigsen", "Jan Øster", "Anika Kristoffersen",
								"Tine Carlsen", "Theresa Clausen", "Lisa Karstensen",
								"Kathrine Andersen"};
		
		String[] randomVej = { "A.N. Hansens Alle", "Richelieus Alle", "Hambros Alle",
								"Lærkevej", "Engvej", "Møllevej", "Vibevej", "Vinkelvej",
								"Kirkevej", "Strandvejen"};
		
		String[] randomEtage = {"" , "St. Th.", "St. Tv.", "1. Th.", "1. Tv.", "2. Th.",
								"2. Tv.", "3. Th.", "3. Tv.", "4. Th.", "4. Tv."};
		
		String[] randomBy ={"København", "Århus", "Odense", "Aalborg", "Frederiksberg",
							"Esbjerg", "Gentofte", "Gladsaxe", "Randers", "Kolding"};
		
		String[] randomTitle = {"The Fallen Silence", "Ragged Dream", "The Wife's Star", 
								"The Birch of the Flying", "Predator in the Captive"};
		
		String[] randomTheme = {"Sustainable Innovation", "Climate Action",
								"The Future of Electric Vehicle Integration",
								"Improving Energy Efficiency", "Commitment" };

		int[] randomNr = new int[randomNames.length];
		int[] randomPhoneNumbers = new int[randomNames.length];
		int[] randomPostNr = new int[randomNames.length];
		for (int i = 0; i < randomNr.length; i++)
		{
			randomNr[i] = Service.randInt(1, 100);
			randomPhoneNumbers[i] = Service.randInt(10000000, 99999999);
			randomPostNr[i] = Service.randInt(1000, 9999);
		}
	
		for (int i = 0; i < randomNames.length; i++)
		{
			Service.createDeltager(randomNames[i], randomPhoneNumbers[i], null, randomVej[i],
									randomNr[i], randomEtage[Service.randInt(0, 9)], randomPostNr[i],
									randomBy[Service.randInt(0, 9)], "Danmark");
		}
		
		for (int i = 0; i < randomTitle.length; i++)
		{
			Service.createMiljøkonference(randomTitle[i], randomTheme[i], Service.randDate(), 
								Service.randDate(), randomVej[Service.randInt(0, 9)], randomNr[Service.randInt(0, 9)],
								randomEtage[Service.randInt(0, 9)], randomPostNr[Service.randInt(0, 9)],
								randomBy[Service.randInt(0, 9)], "Danmark");
		}
		
		for (Deltager delt : Service.getDeltagere())
		{
			for (int i = Service.randInt(0, 9); i < Service.randInt(0, 9); i++)
			{
				delt.addTilmelding(Service.createTilmelding(Service.getMiljøkonferencer().get(Service.randInt(0, Service.getMiljøkonferencer().size()-1)),
						delt, Service.randDate(), Service.randDate(), null));
			}

		}
		
		
		Hotel hotelDenHvideSvane = Service.createHotel("Den Hvide Svane", 1050, 1250, "Vestergade", 2, "", 9000, "Aalborg", "Danmark");
		Facilitet wifi = Service.createFacilitet(hotelDenHvideSvane, "WIFI", 50);

		
		
		Miljøkonference miljøkonference = Service.createMiljøkonference("Hav og himmel", "Havet", LocalDate.of(2016, 4, 18), LocalDate.of(2016, 4, 20), "Hovedgaden", 12, "1. th.", 8200, "Aarhus N", "Danmark");
		

		Udflugt udflugtOdense = Service.createUdflugt(miljøkonference, "Odense", "Byrundtur", 125, LocalDate.of(2016, 4, 18), LocalDate.of(2016, 4, 18), true);
		Udflugt udflugtEgeskov = Service.createUdflugt(miljøkonference, "Fyn", "Egeskov", 75, LocalDate.of(2016, 4, 19), LocalDate.of(2016, 4, 19), false);
		Udflugt udflugtTrapholt = Service.createUdflugt(miljøkonference, "Kolding", "Trapholt Museum", 200, LocalDate.of(2016, 4, 20), LocalDate.of(2016, 4, 20), true);
		
		Prisgruppe prisgruppeVoksen = Service.createPrisgruppe(miljøkonference, "Voksen", 1500);
		Prisgruppe prisgruppeForedragsholder = Service.createPrisgruppe(miljøkonference, "Foredragsholder", 0);
		Deltager deltagerFinn = Service.createDeltager("Finn Madsen", 12345678, prisgruppeVoksen, "Hammergade", 1, "", 9000, "Aalborg", "Danmark");
		Deltager deltagerNiels = Service.createDeltager("Niels Petersen", 12345678, prisgruppeVoksen, "Østergade", 2, "", 9000, "Aalborg", "Danmark");
		Deltager deltagerLone = Service.createDeltager("Lone Jensen", 12345678, prisgruppeForedragsholder, "Vestergade", 3, "", 9000, "Aalborg", "Danmark");
		Ledsager ledsagerJan = Service.createLedsager("Jan Madsen", deltagerLone);
		Indkvartering indkvarteringLone = Service.createIndkvarteringMedHotelbooking(LocalDate.of(2016, 4, 18), LocalDate.of(2016, 4, 20));
		Hotelbooking hotelbookingLone = Service.createHotelbooking(hotelDenHvideSvane, true);
		Tilmelding tilmeldingLone = Service.createTilmelding(miljøkonference, deltagerLone, LocalDate.of(2016, 4, 18), LocalDate.of(2016, 4, 20), indkvarteringLone);
		Service.connectIndkvarteringOgHotelbooking(indkvarteringLone, hotelbookingLone);
		hotelbookingLone.addFacilitet(wifi);
		ledsagerJan.addUdflugt(udflugtEgeskov);
		ledsagerJan.addUdflugt(udflugtOdense);
		
		
		Deltager deltagerPeter = Service.createDeltager("Peter Sommer", 12345678, prisgruppeVoksen, "Nordgade", 9, "", 2000, "København", "Danmark");
		Ledsager ledsagerMie = Service.createLedsager("Mie Sommer", deltagerPeter);
	
		
		Service.addLedsagerTilUdflugt(udflugtEgeskov, ledsagerMie);
		Service.addLedsagerTilUdflugt(udflugtTrapholt, ledsagerMie);
		Indkvartering indkvarteringPeter = Service.createIndkvarteringMedHotelbooking(LocalDate.of(2016, 4, 18), LocalDate.of(2016, 4, 20));
		Tilmelding tilmeldingPeter = Service.createTilmelding(miljøkonference, deltagerPeter, LocalDate.of(2016, 4, 18), LocalDate.of(2016, 4, 20), indkvarteringPeter);
		Hotelbooking hotelbookingPeter = Service.createHotelbooking(hotelDenHvideSvane, true);
		Service.connectIndkvarteringOgHotelbooking(indkvarteringPeter, hotelbookingPeter);
		Service.addFacilitetTilHotelbooking(hotelbookingPeter, wifi);

		
		Indkvartering indkvarteringFinn = Service.createIndkvarteringUdenHotelbooking(LocalDate.of(2016, 4, 18), LocalDate.of(2016, 4, 20), "Øllegade", 68, "", 9280, "Aalborg Øst", "Danmark");
		Tilmelding tilmeldingFinn = Service.createTilmelding(miljøkonference, deltagerFinn, LocalDate.of(2016, 4, 18), LocalDate.of(2016, 4, 20), indkvarteringFinn);
		
		Indkvartering indkvarteringNiels = Service.createIndkvarteringMedHotelbooking(LocalDate.of(2016, 4, 18), LocalDate.of(2016, 4, 20));
		Hotelbooking hotelbookingNiels = Service.createHotelbooking(hotelDenHvideSvane, false);
		Service.connectIndkvarteringOgHotelbooking(indkvarteringNiels, hotelbookingNiels);
		Tilmelding tilmeldingNiels = Service.createTilmelding(miljøkonference, deltagerNiels, LocalDate.of(2016, 4, 18), LocalDate.of(2016, 4, 20), indkvarteringNiels);
		
		Service.createFirma("Grundfos", 12345, "Pumpevej", 4, null, 8850, "Bjerringbro", "Danmark");
		
//		Hotel hotelDenHvideSvane = Service.createHotel("Den Hvide Svane", 100, 150, "Vestergade", 2, "", 9000, "Danmark", "Aalborg");
//		Miljøkonference miljøkonference = Service.createMiljøkonference("Hav og himmel", "Havet", LocalDate.of(2016, 11, 1), LocalDate.of(2016, 11, 3), "Hovedgaden", 12, "1. th.", 8200, "Aarhus N", "Danmark");
//		Miljøkonference miljøkonference1 = Service.createMiljøkonference("Ib's Mågehjælp", "Min fod", LocalDate.of(2016, 11, 1), LocalDate.of(2016, 11, 3), "Over Skuderen", 102, "", 8000, "Aarhus C", "Danmark");
//
//		Prisgruppe prisgruppeVoksen = Service.createPrisgruppe(miljøkonference, "Voksen", 1500);
//		Prisgruppe prisgruppeForedragsholder = Service.createPrisgruppe(miljøkonference, "Foredragsholder", 0);
//
//		
//		
//		
//		
////		Deltager deltagerFinn = Service.createDeltager("Finn Madsen", 12345678, prisgruppeVoksen, "Hammergade", 1, "", 9000, "Aalborg", "Danmark");
////		Deltager deltagerPeter = Service.createDeltager("Peter Sommer", 12345678, prisgruppeVoksen, "Østergade", 2, "", 9000, "Aalborg", "Danmark");
////		Deltager deltagerLone = Service.createDeltager("Lone Jensen", 12345678, prisgruppeForedragsholder, "Vestergade", 3, "", 9000, "Aalborg", "Danmark");
//
////		Indkvartering indkvarteringFinn = Service.createIndkvartering(LocalDate.of(2016, 11, 1), LocalDate.of(2016, 11, 3), "Øllegade", 68, "", 9280, "Aalborg Øst", "Danmark", null);
////		Indkvartering indkvarteringPeter = Service.createIndkvartering(LocalDate.of(2016, 11, 1), LocalDate.of(2016, 11, 3), "Øllegade", 68, "", 9280, "Aalborg Øst", "Danmark", null);
////		Indkvartering indkvarteringLone = Service.createIndkvartering(LocalDate.of(2016, 11, 1), LocalDate.of(2016, 11, 3), "Øllegade", 68, "", 9280, "Aalborg Øst", "Danmark", null);
//
////		Tilmelding tilmeldingFinn = Service.createTilmelding(miljøkonference, deltagerFinn, LocalDate.of(2016, 11, 1), LocalDate.of(2016, 11, 3), indkvarteringFinn);
////		Service.createTilmelding(miljøkonference1, deltagerFinn, LocalDate.of(2016, 11, 1), LocalDate.of(2016, 11, 3), indkvarteringFinn);
////		Tilmelding tilmeldingPeter = Service.createTilmelding(miljøkonference, deltagerPeter, LocalDate.of(2016, 11, 1), LocalDate.of(2016, 11, 3), indkvarteringPeter);
////		Tilmelding tilmeldingLone = Service.createTilmelding(miljøkonference, deltagerLone, LocalDate.of(2016, 11, 1), LocalDate.of(2016, 11, 3), indkvarteringLone);

	}
	// TODO Getter til alle arraylists i Storage

}
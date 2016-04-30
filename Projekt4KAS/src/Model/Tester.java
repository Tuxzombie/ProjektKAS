package Model;


import java.time.LocalDate;

import Service.Service;

public class Tester {

	public static void main(String[] args) {
		Hotel hotelDenHvideSvane = Service.createHotel("Den Hvide Svane", 1050, 1250, "Vestergade", 2, "", 9000, "Danmark", "Aalborg");
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
		


		
		
		
		System.out.println("Pris Finn Madsen (hypotese: 4500 kr.): " + tilmeldingFinn.udregnSamletPris());
		System.out.println("Pris Niels Petersen (hypotese: 6600 kr.): " + tilmeldingNiels.udregnSamletPris());
		System.out.println("Pris Peter Sommer (hypotese: 7375 kr.): " + tilmeldingPeter.udregnSamletPris());
		System.out.println("Pris Lone Jensen (hypotese 2800 kr.): " + tilmeldingLone.udregnSamletPris());
	}

}

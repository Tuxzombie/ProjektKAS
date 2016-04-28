package Model;


import java.time.LocalDate;
import Service.Service;

public class Tester {

	public static void main(String[] args) {
		Hotel hotelDenHvideSvane = Service.createHotel("Den Hvide Svane", 1050, 1250, "Vestergade", 2, "", 9000, "Danmark", "Aalborg");
		Miljøkonference miljøkonference = Service.createMiljøkonference("Hav og himmel", "Havet", LocalDate.of(2016, 4, 18), LocalDate.of(2016, 4, 20), "Hovedgaden", 12, "1. th.", 8200, "Aarhus N", "Danmark");
		Prisgruppe prisgruppeVoksen = Service.createPrisgruppe(miljøkonference, "Voksen", 1500);
		Prisgruppe prisgruppeForedragsholder = Service.createPrisgruppe(miljøkonference, "Foredragsholder", 0);
		Deltager deltagerFinn = Service.createDeltager("Finn Madsen", 12345678, prisgruppeVoksen, "Hammergade", 1, "", 9000, "Aalborg", "Danmark");
		Deltager deltagerPeter = Service.createDeltager("Peter Sommer", 12345678, prisgruppeVoksen, "Østergade", 2, "", 9000, "Aalborg", "Danmark");
		Deltager deltagerLone = Service.createDeltager("Lone Jensen", 12345678, prisgruppeForedragsholder, "Vestergade", 3, "", 9000, "Aalborg", "Danmark");
		Indkvartering indkvarteringFinn = Service.createIndkvarteringUdenHotelbooking(LocalDate.of(2016, 4, 18), LocalDate.of(2016, 4, 20), "Øllegade", 68, "", 9280, "Aalborg Øst", "Danmark");
		Tilmelding tilmeldingFinn = Service.createTilmelding(miljøkonference, deltagerFinn, LocalDate.of(2016, 4, 18), LocalDate.of(2016, 4, 20), indkvarteringFinn);
		
		Indkvartering indkvarteringPeter = Service.createIndkvarteringMedHotelbooking(LocalDate.of(2016, 4, 18), LocalDate.of(2016, 4, 20));
		Hotelbooking hotelbookingPeter = Service.createHotelbooking(hotelDenHvideSvane, false);
		Service.connectIndkvarteringOgHotelbooking(indkvarteringPeter, hotelbookingPeter);
		Tilmelding tilmeldingPeter = Service.createTilmelding(miljøkonference, deltagerPeter, LocalDate.of(2016, 4, 18), LocalDate.of(2016, 4, 20), indkvarteringPeter);
		
		//Service.createUdflugt(miljøkonference, "Odense", beskrivelse, 125, LocalDate.of(year, month, dayOfMonth), slutDato, hasFrokost)
		
		Indkvartering indkvarteringLone = Service.createIndkvarteringMedHotelbooking(LocalDate.of(2016, 4, 18), LocalDate.of(2016, 4, 20));
		Hotelbooking hotelbookingLone = Service.createHotelbooking(hotelDenHvideSvane, true);
		Service.connectIndkvarteringOgHotelbooking(indkvarteringLone, hotelbookingLone);
		Tilmelding tilmeldingLone = Service.createTilmelding(miljøkonference, deltagerLone, LocalDate.of(2016, 4, 18), LocalDate.of(2016, 4, 20), indkvarteringLone);
		Ledsager ledsagerJan = Service.createLedsager("Jan Madsen", deltagerLone);
		
		
		
		
		System.out.println("Pris Finn Madsen (hypotese: 4500 kr.): " + tilmeldingFinn.udregnSamletPris());
		System.out.println("Pris Peter Sommer (hypotese: 6600 kr.): " + tilmeldingPeter.getPeriode() + tilmeldingPeter.udregnSamletPris());
		
	}

}

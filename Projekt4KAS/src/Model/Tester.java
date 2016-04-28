package Model;


import java.time.LocalDate;
import Service.Service;

public class Tester {

	public static void main(String[] args) {
		Hotel hotelDenHvideSvane = Service.createHotel("Den Hvide Svane", 100, 150, "Vestergade", 2, "", 9000, "Danmark", "Aalborg");
		Miljøkonference miljøkonference = Service.createMiljøkonference("Hav og himmel", "Havet", LocalDate.of(2016, 11, 1), LocalDate.of(2016, 11, 3), "Hovedgaden", 12, "1. th.", 8200, "Aarhus N", "Danmark");
		Prisgruppe prisgruppeVoksen = Service.createPrisgruppe(miljøkonference, "Voksen", 1500);
		Prisgruppe prisgruppeForedragsholder = Service.createPrisgruppe(miljøkonference, "Foredragsholder", 0);
		Deltager deltagerFinn = Service.createDeltager("Finn Madsen", 12345678, prisgruppeVoksen, "Hammergade", 1, "", 9000, "Aalborg", "Danmark");
		Deltager deltagerPeter = Service.createDeltager("Peter Sommer", 12345678, prisgruppeVoksen, "Østergade", 2, "", 9000, "Aalborg", "Danmark");
		Deltager deltagerLone = Service.createDeltager("Lone Jensen", 12345678, prisgruppeForedragsholder, "Vestergade", 3, "", 9000, "Aalborg", "Danmark");
		Indkvartering indkvarteringFinn = Service.createIndkvartering(LocalDate.of(2016, 11, 1), LocalDate.of(2016, 11, 3), "Øllegade", 68, "", 9280, "Aalborg Øst", "Danmark", null);
		Tilmelding tilmeldingFinn = Service.createTilmelding(miljøkonference, deltagerFinn, LocalDate.of(2016, 11, 1), LocalDate.of(2016, 11, 3), indkvarteringFinn);
		
		System.out.println("Pris Finn Madsen (4500 kr.): " + tilmeldingFinn.udregnSamletPris());
		System.out.println("" + tilmeldingFinn.getPeriode());
	}

}

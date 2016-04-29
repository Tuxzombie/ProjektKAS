package Model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Miljøkonference
{

	private String titel;
	private ArrayList<Tilmelding> tilmeldinger;
	private LocalDate startDato;
	private LocalDate slutDato;
	private String tema;
	private Adresse adresse;
	private ArrayList<Foredrag> foredrag;
	private ArrayList<Prisgruppe> prisgrupper;
	private ArrayList<Udflugt> udflugter;

	/**
	 * Constructor
	 * 
	 * @param titel Titlen på konferencen
	 * @param tema temaet (f.eks. CO2 udslip) 
	 * @param startDato startdatoen for konferencen
	 * @param slutDato slutdatoen for konferencen
	 * @param vej 
	 * @param nr
	 * @param etage
	 * @param postNr
	 * @param by
	 * @param land
	 */
		public Miljøkonference(String titel, String tema, LocalDate startDato, LocalDate slutDato, String vej, int nr, String etage, int postNr, String by, String land)
	{
		this.titel = titel; 
		this.tema = tema;
		this.startDato = startDato;
		this.slutDato = slutDato;
		prisgrupper = new ArrayList<>();
		tilmeldinger = new ArrayList<>();
		udflugter = new ArrayList<>();
		foredrag = new ArrayList<>();
		this.adresse = new Adresse(vej, nr, etage, postNr, by, land);
	}
	
		
		
	@Override
	public String toString()
	{
		return titel;
	}

	/**
	 * Metode til at oprette foredrag og tilføje foredraget til listen over miljekonferencens foredrag
	 * 
	 * @param titel
	 * @param lokalitet
	 * @param startDato
	 * @param slutDato
	 */
	public Foredrag createForedrag(String titel, String lokalitet, LocalDate startDato, LocalDate slutDato)
	{
		Foredrag newForedrag = new Foredrag(titel, lokalitet, startDato, slutDato);
		this.foredrag.add(newForedrag);
		return newForedrag;
	}

	/**
	 * Metode til at oprette en udflugt og tilføje udflugten til listen over miljøkonferencens udflugter
	 * 
	 * @param lokalitet
	 * @param pris
	 * @param startDato
	 * @param slutDato
	 * @param hasFrokost
	 */
	public Udflugt createUdflugt(String lokalitet, String beskrivelse, double pris, LocalDate startDato, LocalDate slutDato, boolean hasFrokost)
	{
		Udflugt newUdflugt = new Udflugt(lokalitet, beskrivelse, pris, startDato, slutDato, hasFrokost);
		udflugter.add(newUdflugt);
		return newUdflugt;
	}
 
	/**
	 * Metode til at opretter en prisgruppe og tilføjer prisgruppen til miljøkonferencens prisgrupper
	 * @param navn
	 * @param pris
	 * @return
	 */
	public Prisgruppe createPrisgruppe(String navn, double pris) {
		Prisgruppe newPrisgruppe = new Prisgruppe(navn, pris);
		this.prisgrupper.add(newPrisgruppe);
		return newPrisgruppe;
	}
	
	/**
	 * Metode til at oprette en tilmelding og tilføjer tilmelding til listen over miljøkonferencens tilmeldinger
	 * @param indkvartering
	 * @param deltager
	 * @param startDato
	 * @param slutDato
	 * @return newTilmelding
	 */
	public Tilmelding createTilmelding(Indkvartering indkvartering, Deltager deltager, LocalDate startDato, LocalDate slutDato) {
		Tilmelding newTilmelding = new Tilmelding(deltager, startDato, slutDato, indkvartering);
		this.tilmeldinger.add(newTilmelding);
		return newTilmelding;
	}
	
	/**
	 * Metode til at fjerne et foredrag fra miljøkonferencens liste over foredrag
	 * 
	 * @param foredrag fordraget du vil fjerne
	 */
	public void removeForedrag(Foredrag foredrag)
	{
		this.foredrag.remove(foredrag);
	}
	
	/**
	 * Metode til at fjerne et udflugt fra miljøkonferneces liste over udflugter
	 * 
	 * @param udflugt
	 */
	public void removeUdflugt(Udflugt udflugt)
	{
		udflugter.remove(udflugt);
	}
	
	/**
	 * Metode til at fjerne en tilmelding fra miljøkonferences liste over tilmeldinger
	 * 
	 * @param tilmelding
	 */
	public void removeTilmelding(Tilmelding tilmelding)
	{
		tilmeldinger.remove(tilmelding);

	}
	
	/**
	 * Metode til at fjerne prisgruppe fra miljøkonferencens liste over prisgrupper.
	 * 
	 * @param prisgruppe
	 */
	public void removePrisgruppe(Prisgruppe prisgruppe)
	{
		prisgrupper.remove(prisgruppe);

	}
	
	/**
	 * Metode til hente alle miljøkonferencens udflugter
	 * @return
	 */
	public ArrayList<Udflugt> getUdflugter()
	{
		return new ArrayList<>(udflugter);
	}

	/**
	 * Metode til hente alle foredrag
	 * @return 
	 */
	public ArrayList<Foredrag> getForedrag()
	{
		return new ArrayList<>(foredrag);
	}
	
	/**
	 * Metode til at hente liste over tilmeldinger til konferencen
	 * @return tilmeldingslisten
	 */
	public ArrayList<Tilmelding> getTilmeldingliste()
	{
		return new ArrayList<>(tilmeldinger);
	}
	
	/**
	 * Metode til hente alle prisgrupper
	 * @return prisgrupper
	 */
	public ArrayList<Prisgruppe> getPrisgrupper()
	{
		return new ArrayList<>(prisgrupper);
	}

	/**
	 * Metode til at tilføje prisgruppe til listen
	 * 
	 * @param prisgruppe
	 */
	public void addPrisgrupper(Prisgruppe prisgruppe)
	{
		//vi skal nok lave en opret prisgruppe også enten her eller i service
		prisgrupper.add(prisgruppe);

	}

	/**
	 * @return titel
	 */
	public String getTitel()
	{
		return titel;
	}

	/**
	 * @param titel
	 *            the titel to set
	 */
	public void setTitel(String titel)
	{
		this.titel = titel;
	}

	/**
	 * @return startDato
	 */
	public LocalDate getStartDato()
	{
		return startDato;
	}

	/**
	 * @param startDato
	 *            the startDato to set
	 */
	public void setStartDato(LocalDate startDato)
	{
		this.startDato = startDato;
	}

	/**
	 * @return slutDato
	 */
	public LocalDate getSlutDato()
	{
		return slutDato;
	}

	/**
	 * @param slutDato
	 *            the slutDato to set
	 */
	public void setSlutDato(LocalDate slutDato)
	{
		this.slutDato = slutDato;
	}

	/**
	 * @return tema
	 */
	public String getTema()
	{
		return tema;
	}

	/**
	 * @param tema
	 *            the tema to set
	 */
	public void setTema(String tema)
	{
		this.tema = tema;
	}

	/**
	 * @return adresse
	 */
	public Adresse getAdresse()
	{
		return adresse;
	}

}
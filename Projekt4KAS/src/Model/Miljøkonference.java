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
	 * @param tema temaet 
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
		this.adresse = new Adresse(vej, nr, etage, postNr, by, land);
	}
	
	/**
	 * Metode til at hente tilmeldingsliste
	 * @return tilmeldingslisten
	 */
	public ArrayList<Tilmelding> getTilmeldingliste()
	{
		return new ArrayList<>(tilmeldinger);
	}
	

	public void getGæstelisteTilHotel()
	{
		// TODO - implement Miljøkonference.getG�stelisteTilHotel

	}

	public void getKonferenceOversigt()
	{
		// TODO - implement Miljøkonference.getKonferenceOversigt

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
	 * metode til at oprette foredrag og tilføje det til listen
	 * 
	 * @param titel
	 * @param lokalitet
	 * @param startDato
	 * @param slutDato
	 */
	public void createForedrag(String titel, String lokalitet, LocalDate startDato, LocalDate slutDato)
	{
		Foredrag newForedrag = new Foredrag(titel, lokalitet, startDato, slutDato);
		this.foredrag.add(newForedrag);
		//måske skal der return på
		//return newForedrag;
	}

	/**
	 * metode til at fjerne et foredrag fra listen
	 * 
	 * @param foredrag fordraget du vil fjerne
	 */
	public void removeForedrag(Foredrag foredrag)
	{
		this.foredrag.remove(foredrag);

	}
	
	/**
	 * Metode til hente alle udflugter
	 * @return
	 */
	public ArrayList<Udflugt> getUdflugter()
	{
		return new ArrayList<>(udflugter);
	}

	/**
	 * metode til at oprette Udflugt og tilføje det til listen
	 * 
	 * @param lokalitet
	 * @param pris
	 * @param startDato
	 * @param slutDato
	 * @param hasFrokost
	 */
	public void createUdflugt(String lokalitet, double pris, LocalDate startDato, LocalDate slutDato, boolean hasFrokost)
	{
		Udflugt newUdflugt = new Udflugt(lokalitet, pris, startDato, slutDato, hasFrokost);
		udflugter.add(newUdflugt);
		//måske skal der return på
		//return newUdflugt;

	}

	/**
	 * metode til at fjerne et udflugt fra listen
	 * 
	 * @param udflugt
	 */
	public void removeUdflugt(Udflugt udflugt)
	{
		udflugter.remove(udflugt);
	}
	
	/**
	 * Metode til hente alle tilmeldinger
	 * 
	 * @return the tilmeldinger
	 */
	public ArrayList<Tilmelding> getTilmeldinger()
	{
		return new ArrayList<>(tilmeldinger);
	}

	/**
	 * metode til at tilføje tilmelding til listen
	 * 
	 * @param tilmelding
	 */
	public void addTilmelding(Tilmelding tilmelding)
	{
		tilmeldinger.add(tilmelding);
		
	}

	/**
	 * metode til at fjerne et tilmelding fra listen
	 * 
	 * @param tilmelding
	 */
	public void removeTilmelding(Tilmelding tilmelding)
	{
		tilmeldinger.remove(tilmelding);

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
	 * metode til at tilføje prisgruppe til listen
	 * 
	 * @param prisgruppe
	 */
	public void addPrisgrupper(Prisgruppe prisgruppe)
	{
		//vi skal nok lave en opret prisgruppe også enten her eller i service
		prisgrupper.add(prisgruppe);

	}

	/**
	 * metode til at fjerne prisgruppe fra listen
	 * 
	 * @param prisgruppe
	 */
	public void removePrisgruppe(Prisgruppe prisgruppe)
	{
		prisgrupper.remove(prisgruppe);

	}

	/**
	 * @return the titel
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
	 * @return the startDato
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
	 * @return the slutDato
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
	 * @return the tema
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
	 * @return the adresse
	 */
	public Adresse getAdresse()
	{
		return adresse;
	}

	/**
	 * @param adresse
	 *            the adresse to set
	 */
	public void setAdresse(Adresse adresse)
	{
		this.adresse = adresse;
	}


}
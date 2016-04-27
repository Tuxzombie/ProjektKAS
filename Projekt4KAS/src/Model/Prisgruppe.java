package Model;

public class Prisgruppe {

	private String navn;
	private double pris;

	/**
	 * 
	 * @param navn
	 * @param pris
	 */
	public Prisgruppe(String navn, double pris) {
		// TODO - implement Prisgruppe.Prisgruppe
		
	}

	/**
	 * @return the navn
	 */
	public String getNavn()
	{
		return navn;
	}

	/**
	 * @param navn the navn to set
	 */
	public void setNavn(String navn)
	{
		this.navn = navn;
	}

	/**
	 * @return the pris
	 */
	public double getPris()
	{
		return pris;
	}

	/**
	 * @param pris the pris to set
	 */
	public void setPris(double pris)
	{
		this.pris = pris;
	}
	
	

}
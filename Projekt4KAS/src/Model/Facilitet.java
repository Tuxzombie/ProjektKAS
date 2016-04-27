package Model;

public class Facilitet {

	private String facilitetNavn;
	private double pris;

	/**
	 * @param facilitetNavn
	 * @param pris
	 */
	public Facilitet(String facilitetNavn, double pris)
	{
		this.facilitetNavn = facilitetNavn;
		this.pris = pris;
	}

	/**
	 * @return the facilitetNavn
	 */
	public String getFacilitetNavn()
	{
		return facilitetNavn;
	}

	/**
	 * @param facilitetNavn the facilitetNavn to set
	 */
	public void setFacilitetNavn(String facilitetNavn)
	{
		this.facilitetNavn = facilitetNavn;
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
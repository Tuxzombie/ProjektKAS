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
	 * @return facilitetNavn
	 */
	public String getFacilitetNavn()
	{
		return facilitetNavn;
	}

	/**
	 * @param facilitet
	 */
	public void setFacilitetNavn(String facilitetNavn)
	{
		this.facilitetNavn = facilitetNavn;
	}

	/**
	 * @return pris
	 */
	public double getPris()
	{
		return pris;
	}

	/**
	 * @param pris
	 */
	public void setPris(double pris)
	{
		this.pris = pris;
	}
	

}
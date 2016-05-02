package Model;

public class Adresse {

	private String vej;
	private int nr;
	private String etage;
	private int postNr;
	private String land;
	private String by;

	/**
	 * Constructor
	 * @param vej
	 * @param nr
	 * @param etage
	 * @param postNr
	 * @param land
	 * @param by
	 */
	public Adresse(String vej, int nr, String etage, int postNr, String by, String land)
	{
		this.vej = vej;
		this.nr = nr;
		this.etage = etage;
		this.postNr = postNr;
		this.land = land;
		this.by = by;
	}
	
	/**
	 * Skriver indeholdet af variabler
	 */
	@Override
	public String toString()
	{
		return  vej +" "+ nr + ", " + etage + "\n" + postNr + " " + by +"\n" + land;
	}

	/**
	 * @return vej
	 */
	public String getVej()
	{
		return vej;
	}

	/**
	 * @param vej
	 */
	public void setVej(String vej)
	{
		this.vej = vej;
	}

	/**
	 * @return nr
	 */
	public int getNr()
	{
		return nr;
	}

	/**
	 * @param nr
	 */
	public void setNr(int nr)
	{
		this.nr = nr;
	}

	/**
	 * @return etage
	 */
	public String getEtage()
	{
		return etage;
	}

	/**
	 * @param etage
	 */
	public void setEtage(String etage)
	{
		this.etage = etage;
	}

	/**
	 * @return postNr
	 */
	public int getPostNr()
	{
		return postNr;
	}

	/**
	 * @param postNr
	 */
	public void setPostNr(int postNr)
	{
		this.postNr = postNr;
	}

	/**
	 * @return land
	 */
	public String getLand()
	{
		return land;
	}

	/**
	 * @param land
	 */
	public void setLand(String land)
	{
		this.land = land;
	}

	/**
	 * @return by
	 */
	public String getBy()
	{
		return by;
	}

	/**
	 * @param by
	 */
	public void setBy(String by)
	{
		this.by = by;
	}
	
	

}
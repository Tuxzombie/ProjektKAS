package Model;

public class Adresse {

	private String vej;
	private int nr;
	private String etage;
	private int postNr;
	private String land;
	private String by;



	/**
	 * @param vej
	 * @param nr
	 * @param etage
	 * @param postNr
	 * @param land
	 * @param by
	 */
	public Adresse(String vej, int nr, String etage, int postNr, String land, String by)
	{
		this.vej = vej;
		this.nr = nr;
		this.etage = etage;
		this.postNr = postNr;
		this.land = land;
		this.by = by;
	}

	/**
	 * @return the vej
	 */
	public String getVej()
	{
		return vej;
	}

	/**
	 * @param vej the vej to set
	 */
	public void setVej(String vej)
	{
		this.vej = vej;
	}

	/**
	 * @return the nr
	 */
	public int getNr()
	{
		return nr;
	}

	/**
	 * @param nr the nr to set
	 */
	public void setNr(int nr)
	{
		this.nr = nr;
	}

	/**
	 * @return the etage
	 */
	public String getEtage()
	{
		return etage;
	}

	/**
	 * @param etage the etage to set
	 */
	public void setEtage(String etage)
	{
		this.etage = etage;
	}

	/**
	 * @return the postNr
	 */
	public int getPostNr()
	{
		return postNr;
	}

	/**
	 * @param postNr the postNr to set
	 */
	public void setPostNr(int postNr)
	{
		this.postNr = postNr;
	}

	/**
	 * @return the land
	 */
	public String getLand()
	{
		return land;
	}

	/**
	 * @param land the land to set
	 */
	public void setLand(String land)
	{
		this.land = land;
	}

	/**
	 * @return the by
	 */
	public String getBy()
	{
		return by;
	}

	/**
	 * @param by the by to set
	 */
	public void setBy(String by)
	{
		this.by = by;
	}
	
	

}
package Model;

import java.util.ArrayList;

public class Ledsager {

	private ArrayList<Udflugt> udflugt;
	private String navn;

	/**
	 * 
	 * @param navn
	 */
	public Ledsager(String navn) {

	}

	public ArrayList<Udflugt> getUdflugter() {
		// TODO - implement Ledsager.getUdflugter
		return null;
	}

	/**
	 * 
	 * @param udflugt
	 */
	public void addUdflugt(Udflugt udflugt) {
		// TODO - implement Ledsager.addUdflugt
		
	}

	/**
	 * 
	 * @param udflugt
	 */
	public void removeUdflugt(Udflugt udflugt) {
		// TODO - implement Ledsager.removeUdflugt
		
	}

	/**
	 * @return the udflugt
	 */
	public ArrayList<Udflugt> getUdflugt()
	{
		return udflugt;
	}

	/**
	 * @param udflugt the udflugt to set
	 */
	public void setUdflugt(ArrayList<Udflugt> udflugt)
	{
		this.udflugt = udflugt;
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
	
	

}
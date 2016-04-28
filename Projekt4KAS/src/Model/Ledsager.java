package Model;

import java.util.ArrayList;

public class Ledsager {

	private ArrayList<Udflugt> udflugter;
	private String navn;
	private Deltager deltager;

	/**
	 * 
	 * @param navn
	 */
	public Ledsager(String navn, Deltager deltager) {
		this.navn = navn;
		this.deltager = deltager;
		udflugter = new ArrayList<>();
	}
	
	/**
	 * 
	 * @return
	 */
	public ArrayList<Udflugt> getUdflugter() {
		return new ArrayList<>(udflugter);
	}

	/**
	 * 
	 * @param udflugt
	 */
	public void addUdflugt(Udflugt udflugt) {
		udflugter.add(udflugt);
	}

	/**
	 * 
	 * @param udflugt
	 */
	public void removeUdflugt(Udflugt udflugt) {
		udflugter.remove(udflugt);
	}

	/**
	 * @return navn
	 */
	public String getNavn()
	{
		return navn;
	}

	/**
	 * @param navn
	 */
	public void setNavn(String navn)
	{
		this.navn = navn;
	}

	public Deltager getDeltager() {
		return deltager;
	}

	public void setDeltager(Deltager deltager) {
		this.deltager = deltager;
	}
	
	
}
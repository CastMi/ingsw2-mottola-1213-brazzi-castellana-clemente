package it.swimv2.controller;
/**
 * 
 * @author Michele
 *
 */
public class Factory {

	public IDBAccess getDBAccess()
	{
		return new DBAccess();
	}
}

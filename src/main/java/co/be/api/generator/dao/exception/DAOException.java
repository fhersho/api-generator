package co.be.api.generator.dao.exception;


public class DAOException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public DAOException(String string) {  
		super(string);                              
	}                                               
	                                                

	public DAOException(Exception string) {  
		super(string);                              
	}             
}

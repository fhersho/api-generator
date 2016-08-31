package co.be.api.generator.business.exception;


public class BusinessException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public BusinessException(String string) {  
		super(string);                              
	}                                               
	                                                

	public BusinessException(Exception string) {  
		super(string);                              
	}             
}

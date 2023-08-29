package ba.unsa.etf.rpr.exceptions;

public class CompanyException extends Exception{

    public CompanyException(String msg, Exception reason){
        super(msg, reason);
    }

    public CompanyException(String msg){
        super(msg);
    }
}

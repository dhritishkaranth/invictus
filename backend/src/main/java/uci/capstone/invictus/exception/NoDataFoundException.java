package uci.capstone.invictus.exception;

public class NoDataFoundException extends RuntimeException{
    public NoDataFoundException(){
        super("No data available in the database");
    }
}

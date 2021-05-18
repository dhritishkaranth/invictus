package uci.capstone.invictus.exception;

public class GroupNotFoundException extends RuntimeException{
    public GroupNotFoundException(String queryParameter, String value){
        super("No group found for the query <" + queryParameter + ": " + value + ">");
    }
}

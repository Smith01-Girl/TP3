package web2.tp3.exceptions;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String s) {
        super(s);
    }
}

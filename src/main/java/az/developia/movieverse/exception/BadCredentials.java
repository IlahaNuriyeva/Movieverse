package az.developia.movieverse.exception;

public class BadCredentials extends BaseException{

    public static final String CODE = "exception.auth.bad-credentials";
    public BadCredentials(){
        super(CODE);
    }

}

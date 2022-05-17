package az.developia.movieverse.exception;

public class UnauthorizedException extends BaseException{

    public UnauthorizedException(String code, String message) {
        super(code, message);
    }

    public UnauthorizedException(String code) {
        super(code);
    }
}

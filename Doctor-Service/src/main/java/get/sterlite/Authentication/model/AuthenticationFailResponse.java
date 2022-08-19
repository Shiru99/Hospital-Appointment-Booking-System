package get.sterlite.Authentication.model;

import java.io.Serializable;

public class AuthenticationFailResponse implements Serializable {

    private final String message;

    public AuthenticationFailResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}

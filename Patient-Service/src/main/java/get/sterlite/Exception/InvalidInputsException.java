package get.sterlite.Exception;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

public class InvalidInputsException extends Exception {
    public InvalidInputsException(BindingResult errors) {
        super(getErrorString(errors));
    }

    private static String getErrorString(BindingResult errors) {
        StringBuilder sb = new StringBuilder();

        for (ObjectError error : errors.getAllErrors()) {
            sb.append(error.getDefaultMessage()+"\n");
        }
        return sb.toString();
    }
}


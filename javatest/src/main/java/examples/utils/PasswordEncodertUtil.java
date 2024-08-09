package examples.utils;

import java.util.function.Supplier;
import lombok.experimental.UtilityClass;

@UtilityClass
public class PasswordEncodertUtil {

    public static void checkPassword(Supplier<Boolean> checkFunction, String functionDescription) {
        String result;
        if (checkFunction.get()) {
            result = "Login OK";
        } else {
            result = "Wrong password";
        }
        System.out.printf("Checking result with function '%s':\n%s\n", functionDescription, result);
    }
}

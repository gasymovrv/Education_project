package examples;

import static examples.utils.PasswordEncodertUtil.checkPassword;
import static examples.utils.TimeMeasurementUtil.measureTimeWithIterationsAndDescription;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

/**
 * Хеширование паролей с помощью BCrypt и дальнейшая проверка
 */
public class Argon2Test extends Test {

    private static final String PASSWORD = "1234";
    private static final String PASSWORD_TO_CHECK = "wrong";

    private static final Argon2 ARGON2_ID = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);

    private static Test thisOne = new Argon2Test();

    public static void main(String[] args) {
        thisOne.go();
    }

    @Override
    public void contentGo() {
        System.out.printf("Password: '%s'\n", PASSWORD);
        String hashed = ARGON2_ID.hash(10, 65536, 1, PASSWORD.toCharArray());

        System.out.println("Hashed password: " + hashed);
        System.out.printf("Password to check: '%s'\n", PASSWORD_TO_CHECK);

        System.out.println();

        checkPassword(
                () -> ARGON2_ID.verify(hashed, PASSWORD_TO_CHECK.toCharArray()),
                "ARGON2_ID.verify(hashed, password to check)"
        );

        System.out.println();

        measureTimeWithIterationsAndDescription("Argon2 verification time:", 10,
                () -> ARGON2_ID.verify(hashed, PASSWORD.toCharArray()));
    }
}

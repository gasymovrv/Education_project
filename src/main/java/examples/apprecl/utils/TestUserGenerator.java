package examples.apprecl.utils;

import com.github.javafaker.Faker;
import com.github.javafaker.Name;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

public class TestUserGenerator {

    public static void main(String... args) throws Exception {
        int max = Integer.parseInt("10");
        if (max < 1) {
            throw new RuntimeException("Argument must be greater than 1");
        }
        generateLdapUsers(max);
    }

    private static void generateLdapUsers(int max) throws InterruptedException {

        List<String> linesForLDAP = new ArrayList<>();
        List<String> linesForSAML = new ArrayList<>();

        linesForLDAP.add("\t");
        linesForSAML.add("\t");

        System.out.println("# ------------ Started generation test users ---------------");
        for (int i = 1; i <= max; i++) {
            Faker faker = new Faker();
            Name name = faker.name();

            String cn = String.valueOf(1000 + i);
            String sn = name.lastName();
            String givenName = name.firstName();
            String mail = (username(givenName, sn) + i + "@gmail.com");
            String physicalDeliveryOfficeName = faker.address().fullAddress();

            linesForLDAP.add(String.format("# Entry %d", i));
            linesForLDAP.add(String.format("dn: uid=%d,ou=users,dc=testcompany,dc=com", i));
            linesForLDAP.add(String.format("cn: %s", cn));
            linesForLDAP.add(String.format("sn: %s", sn));
            linesForLDAP.add(String.format("givenname: %s", givenName));
            linesForLDAP.add(String.format("mail: %s", mail));
            linesForLDAP.add(String.format("physicalDeliveryOfficeName: %s", physicalDeliveryOfficeName));
            linesForLDAP.add(String.format("uid: %d", i));
            linesForLDAP.add(String.format("uidnumber: 100%d", i));
            linesForLDAP.add(String.format("gidnumber: 50%d", i));
            linesForLDAP.add(String.format("homedirectory: /home/users/%d", i));
            linesForLDAP.add("objectclass: inetOrgPerson");
            linesForLDAP.add("objectclass: posixAccount");
            linesForLDAP.add("objectclass: top");
            linesForLDAP.add("");

            linesForSAML.add(String.format("        '%s:password' => array_merge($test_user_base, array(", cn));
            linesForSAML.add(String.format("            'Cn' => '%s'", cn));
            linesForSAML.add("        )),");
        }

        linesForSAML.add("        'userWrong:password' => array_merge($test_user_base, array(");
        linesForSAML.add("            'Cn' => 'userWrong'");
        linesForSAML.add("        ))");
        linesForSAML.add("    )");
        linesForSAML.add(");");

        System.out.println("# ------------ Test users were successfully generated ---------------");

        Path ldapConfigFile = Paths.get("src/main/java/examples/apprecl/utils/ldap-testcompany.ldif");
        if (!Files.exists(ldapConfigFile)) {
            try {
                Files.createFile(ldapConfigFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Path samlConfigFile = Paths.get("src/main/java/examples/apprecl/utils/authsources.php");
        if (!Files.exists(samlConfigFile)) {
            try {
                Files.createFile(samlConfigFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.submit(() -> {
            try {
                FileUtils.writeLines(ldapConfigFile.toFile(), linesForLDAP, true);
                System.out.println("# ------------ Test users were successfully written to LDAP config file ---------------");
            } catch (IOException e) {
                System.out.println("# ------------ Error while writing test users to LDAP config file ---------------");
            }
        });

        executorService.submit(() -> {
            try {
                FileUtils.writeLines(samlConfigFile.toFile(), linesForSAML, true);
                System.out.println("# ------------ Test users were successfully written to SAML config file ---------------");
            } catch (IOException e) {
                System.out.println("# ------------ Error while writing test users to SAML config file ---------------");
            }
        });

        //Инициирует упорядоченное завершение работы,
        // при котором ранее отправленные задачи выполняются, но новые задачи не принимаются.
        executorService.shutdown();
        System.out.println("All tasks submitted");

        //Что-то типо join, ждем наступления первого из:
        // все задачи выполнились
        // истек таймаут
        // interruption текущего потока
        executorService.awaitTermination(150, TimeUnit.SECONDS);
        System.out.println("All tasks completed");
    }

    private static String username(String firstName, String lastName) {
        String username = StringUtils.join(
                firstName.replaceAll("'", "").toLowerCase(),
                ".",
                lastName.replaceAll("'", "").toLowerCase()
        );

        return StringUtils.deleteWhitespace(username);
    }
}
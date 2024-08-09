package examples.apprecl.utils;

import com.github.javafaker.Faker;
import com.github.javafaker.Name;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import examples.utils.TimeMeasurementUtil;

public class TestUserGenerator {

    public static void main(String... args) {
        int max = Integer.parseInt("12000");
        if (max < 1) {
            throw new RuntimeException("Argument must be greater than 1");
        }
        long time = TimeMeasurementUtil.measureTime(() -> {
            try {
                generateLdapUsers(max);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.printf("Generation took: %s ms", time);
    }

    private static void generateLdapUsers(int max) throws InterruptedException {

        StringBuffer ldapUsers = new StringBuffer();
        StringBuffer samlUsers = new StringBuffer();

        FakeValuesService fakeValuesService = new FakeValuesService(new Locale("en-US"), new RandomService());

        ExecutorService executorService = Executors.newFixedThreadPool(20);
        System.out.println("# ------------ Started generation test users ---------------");

        for (int i = 1; i <= max; i++) {
            final int x = i;
            executorService.execute(() -> {
                Faker faker = new Faker();
                Name name = faker.name();

                String uid;
                if (0.1 <= Math.random()) {
                    uid = String.format("s%s", StringUtils.leftPad(String.valueOf(x), 6, '0'));
                } else {
                    uid = String.format("s%sL", StringUtils.leftPad(String.valueOf(x), 6, '0'));
                }
                String cn = uid.toUpperCase() + "@snm.snecma";
                String sn = name.lastName().toUpperCase();
                String givenName = name.firstName();
                String mail = (username(givenName, sn) + x + "@safrangroup.com");
                String sfEtabTrig = fakeValuesService.regexify("[A-Z]{3}");
                String sfAffectation = fakeValuesService.regexify("[A-Z]{3,7}");
                String telephoneNumber = "33" + fakeValuesService.regexify("[0-9]{9}");

                String ldapUser = "\n" +
                        String.format("dn: uid=%s,ou=people,dc=testcompany,dc=com", uid) +
                        "\n" +
                        String.format("uid: %s", uid) +
                        "\n" +
                        String.format("cn: %s", cn) +
                        "\n" +
                        String.format("sn: %s", sn) +
                        "\n" +
                        String.format("givenName: %s", givenName) +
                        "\n" +
                        String.format("sfMail: %s", mail) +
                        "\n" +
                        String.format("mail: %s", mail) +
                        "\n" +
                        String.format("sfEtabTrig: %s", sfEtabTrig) +
                        "\n" +
                        String.format("sfAffectation: %s", sfAffectation) +
                        "\n" +
                        String.format("telephoneNumber: %s", telephoneNumber) +
                        "\n" +
                        String.format("sfIdStatus: %s", "Active") +
                        "\n" +
                        "objectClass: sfPerson" +
                        "\n";
                ldapUsers.append(ldapUser);

                String samlUser = "\n" +
                        String.format("        '%s:password' => array(", uid) + "\n" +
                        String.format("            'uid' => '%s'", uid) + "\n" +
                        "        ),";
                samlUsers.append(samlUser);
            });
        }

        executorService.shutdown();
        executorService.awaitTermination(60, TimeUnit.SECONDS);
        System.out.println("All generate-users tasks completed");

        samlUsers.append("\n        'userWrong:password' => array(\n");
        samlUsers.append("            'uid' => 'userWrong'\n");
        samlUsers.append("        )\n");
        samlUsers.append("    )\n");
        samlUsers.append(");\n");
        System.out.println("# ------------ Test users were successfully generated ---------------");

        writeToConfigFiles(
                ldapUsers.toString().getBytes(),
                samlUsers.toString().getBytes());
    }

    private static void writeToConfigFiles(byte[] ldapUsers, byte[] samlUsers) throws InterruptedException {
        Path ldapConfigFile = Paths.get("src/main/java/examples/apprecl/utils/ldap-users.ldif");
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
        executorService.execute(() -> {
            try {
                FileUtils.writeByteArrayToFile(ldapConfigFile.toFile(), ldapUsers, true);
                System.out.println("# ------------ Test users were successfully written to LDAP config file ---------------");
            } catch (IOException e) {
                System.out.println("# ------------ Error while writing test users to LDAP config file ---------------");
            }
        });

        executorService.execute(() -> {
            try {
                FileUtils.writeByteArrayToFile(samlConfigFile.toFile(), samlUsers, true);
                System.out.println("# ------------ Test users were successfully written to SAML config file ---------------");
            } catch (IOException e) {
                System.out.println("# ------------ Error while writing test users to SAML config file ---------------");
            }
        });

        executorService.shutdown();
        executorService.awaitTermination(60, TimeUnit.SECONDS);
        System.out.println("All write-to-file tasks completed");
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
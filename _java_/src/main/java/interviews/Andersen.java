package interviews;

import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Data;


public class Andersen {
    public static void main(String[] args) {
        System.out.println(getLogins(List.of(
                new User("z"),
                new User(null),
                new User("b"),
                new User("d"),
                new User(null),
                new User("a"),
                new User("c"),
                new User(null))
        ));
    }

    public static List<String> getLogins(List<User> list) {
        //вернуть отсортированный список логинов(поле  login),
        //убрать повторения и исключить со списка пустые логины
        //List<String> logins = new ArrayList<>();
        //for (User u : list) {
        //    if (u.getLogin() != null && !logins.contains(u.getLogin())) {
        //        logins.add(u.getLogin());
        //    }
        //}
        //Collections.sort(logins);
        //return logins;
        return list.stream()
                .filter(user -> user.getLogin() != null)
                .map(User::getLogin)
                .sorted()
                .distinct().collect(Collectors.toList());
    }
}

@AllArgsConstructor
@Data
class User {
    private String login;

    public String getLogin() {
        return login;
    }
}






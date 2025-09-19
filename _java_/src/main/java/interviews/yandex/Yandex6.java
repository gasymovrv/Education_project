package interviews.yandex;

/**
 * Дана строка. Произвольное количество любых символов в ней.
 * Но если мы встречаем паттерн, двоеточие, дефис, и далее скобка в любом количестве, либо открывающиеся, либо закрывающиеся, неважно, но одинаковая скобка, мы этот паттерн должны удалить.
 * И все его вхождения. И на выходе вернуть текст без этих смайликов.
 */
public class Yandex6 {
    public static String removeSmileys(String input) {
        StringBuilder result = new StringBuilder(); // Храним результат
        int n = input.length();

        for (int i = 0; i < n; ) { // Цикл по символам
            // Проверяем, что хватает места для ":-" и хотя бы одной скобки
            if (i + 2 < n && input.charAt(i) == ':' && input.charAt(i + 1) == '-') {
                char bracket = input.charAt(i + 2); // символ после ":-"
                if (bracket == '(' || bracket == ')') {
                    // Если нашли скобку, пропускаем все такие же подряд
                    int j = i + 3;
                    while (j < n && input.charAt(j) == bracket) {
                        j++;
                    }
                    // Пропускаем весь смайлик, двигаем индекс вперёд
                    i = j;
                    continue;
                }
            }
            // Если не смайлик, просто добавляем символ
            result.append(input.charAt(i));
            i++;
        }

        return result.toString();
    }

    public static void main(String[] args) {
        // Базовый тест — один смайлик
        System.out.println(removeSmileys("Привет :-)"));
        // Ожидаем: "Привет "

        // Несколько смайликов
        System.out.println(removeSmileys("Текст :-( и еще :-) и опять :-((("));
        // Ожидаем: "Текст  и еще  и опять "

        // Несколько смайликов подряд
        System.out.println(removeSmileys("Текст :-(:-) и опять :-((("));
        // Ожидаем: "Текст  и опять "

        // Без смайликов
        System.out.println(removeSmileys("Просто текст без смайлов"));
        // Ожидаем: "Просто текст без смайлов"

        // Пустая строка
        System.out.println(removeSmileys(""));
        // Ожидаем: ""

        // Только смайлик
        System.out.println(removeSmileys(":-))))"));
        // Ожидаем: ""

        // Смешанные символы
        System.out.println(removeSmileys("abc :-))) 123 :-( Тест"));
        // Ожидаем: "abc  123  Тест"

        // Пограничный случай: ":-" без скобки — не смайлик
        System.out.println(removeSmileys("тут :- только часть"));
        // Ожидаем: "тут :- только часть"
    }
}

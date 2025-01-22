package examples.algorithms.yandex;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class GenerateParentheses {
    static void generateParentheses(int n, StringBuilder current, int open, int close, PrintWriter writer) {
        if (current.length() == 2 * n) {
            writer.println(current);
            return;
        }
        if (open < n) {
            current.append('(');
            generateParentheses(n, current, open + 1, close, writer);
            current.deleteCharAt(current.length() - 1);
        }
        if (close < open) {
            current.append(')');
            generateParentheses(n, current, open, close + 1, writer);
            current.deleteCharAt(current.length() - 1);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("output.txt")));
        int n = Integer.parseInt(reader.readLine());
        StringBuilder current = new StringBuilder();
        generateParentheses(n, current, 0, 0, writer);
        writer.close();
        reader.close();
    }
}

package examples.algorithms;

public class RecursionTest {
    public static void main(String[] args) {
        System.out.println(factorial(5));
        System.out.println(fib(7));
    }

    /**
     * Вычисляет факториал числа.
     * Например, факториал числа 5 равен произведению 1 * 2 * 3 * 4 * 5 = 120
     *
     * @param n число
     * @return факториал числа n
     */
    private static int factorial(int n) {
        if (n == 1) {
            return n;
        } else {
            return n * factorial(n - 1);
        }
    }

    /**
     * Вычисляет n-е число фибоначчи
     * Последовательность чисел Фибоначчи определяется формулой Fn = Fn-1 + Fn-2.
     * То есть, следующее число получается как сумма двух предыдущих.
     * Первые два числа равны 1, затем 2(1+1), затем 3(1+2), 5(2+3) и так далее: 1, 1, 2, 3, 5, 8, 13, 21....
     *
     * @param n номер числа
     * @return n-е число фибоначчи
     */
    private static int fib(int n) {
        if (n <= 1) {
            return n;
        } else {
            return fib(n - 2) + fib(n - 1);
        }
    }
}

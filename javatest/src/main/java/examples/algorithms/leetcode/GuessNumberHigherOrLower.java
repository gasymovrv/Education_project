package examples.algorithms.leetcode;

/**
 * We are playing the Guess Game. The game is as follows:
 * <p>
 * I pick a number from 1 to n. You have to guess which number I picked.
 * <p>
 * Every time you guess wrong, I will tell you whether the number I picked is higher or lower than your guess.
 * <p>
 * You call a pre-defined API int guess(int num), which returns three possible results:
 * <p>
 * -1: Your guess is higher than the number I picked (i.e. num > pick).
 * <p>
 * 1: Your guess is lower than the number I picked (i.e. num < pick).
 * <p>
 * 0: your guess is equal to the number I picked (i.e. num == pick).
 * <p>
 * Return the number that I picked
 */
public class GuessNumberHigherOrLower extends GuessGame {

    /**
     * Используем бинарный поиск.
     * <p>
     * Сложность O(log n)
     */
    public int guessNumber(int n) {
        int left = 1;
        int right = n;

        while (left <= right) {
            int mid = left + ((right - left) / 2);
            int res = guess(mid);
            System.out.println("Try to guess: " + mid);

            if (res == -1) {
                right = mid - 1;
                continue;
            }
            if (res == 1) {
                left = mid + 1;
                continue;
            }
            if (res == 0) {
                return mid;
            }
        }
        return -1;
    }

    GuessNumberHigherOrLower(int pick) {
        super(pick);
    }

    public static void main(String[] args) {
        var guessGame = new GuessNumberHigherOrLower(10);
        System.out.println(guessGame.guessNumber(100));
    }
}


class GuessGame {
    int pick;

    GuessGame(int pick) {
        this.pick = pick;
    }

    public int guess(int num) {
        if (num == pick) return 0;
        return num > pick ? -1 : 1;
    }
}

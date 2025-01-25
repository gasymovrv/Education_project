package examples.algorithms.leetcode;

/**
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/
 * <p>
 * You are given an array prices where prices[i] is the price of a given stock on the ith day, and
 * an integer fee representing a transaction fee.
 * <p>
 * Find the maximum profit you can achieve. You may complete as many transactions as you like, but
 * you need to pay the transaction fee for each transaction.
 * <p>
 * Note:
 * <p>
 * You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before
 * you buy again). The transaction fee is only charged once for each stock purchase and sale.
 * <p>
 * <p>
 * Example 1: Input: prices = [1,3,2,8,4,9], fee = 2
 * Output: 8
 * Explanation: The maximum profit can
 * be achieved by: - Buying at prices[0] = 1 - Selling at prices[3] = 8 - Buying at prices[4] = 4 -
 * Selling at prices[5] = 9 The total profit is ((8 - 1) - 2) + ((9 - 4) - 2) = 8.
 * <p>
 * Example 2: Input: prices = [1,3,7,5,10,3], fee = 3
 * Output: 6
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= prices.length <= 5 * 10^4 1 <= prices[i] < 5 * 10^4 0 <= fee < 5 * 10^4
 */
public class BestTimeToBuyAndSellStockWithTransactionFee {

    public static void main(String[] args) {
        System.out.println(maxProfit(new int[]{1, 3, 2, 8, 4, 9}, 2));
        System.out.println(maxProfit(new int[]{1, 3, 7, 5, 10, 3}, 3));
    }

    public static int maxProfit(int[] prices, int fee) {
        int buy = Integer.MIN_VALUE;
        int sell = 0;

        for (int price : prices) {
            // Счетчик покупок, срабатывает на первом элементе или при падении цены.
            // Используется далее для расчета нового sell
            buy = Math.max(buy, sell - price);
            // Счетчик продаж, пишет если покупка была и затем цена увеличилась больше предыдущей
            // (для первого элемента всегда 0, для второго предыдущая 0) за вычетом комиссии
            sell = Math.max(sell, buy + price - fee);
        }

        return sell;
    }
}

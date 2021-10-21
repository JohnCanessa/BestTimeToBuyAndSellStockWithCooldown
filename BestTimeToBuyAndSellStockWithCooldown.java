import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


/**
 * 
 */
public class BestTimeToBuyAndSellStockWithCooldown {


    /**
     * Find the maximum profit you can achieve.
     * 
     * 210 / 210 test cases passed.
     * Status: Accepted
     * Runtime: 2 ms
     * Memory Usage: 39 MB
     * 
     * After replacing Math.max():
     * 
     * 210 / 210 test cases passed.
     * Status: Accepted
     * Runtime: 0 ms
     * Memory Usage: 36.5 MB
     * 
     * Runtime: O(n) - Space: O(1)
     */
    static public int maxProfit(int[] prices) {

        // **** sanity check(s) ****
        if (prices.length <= 1) return 0;
        
        // **** initialization (states) ****
        int maxProfit   = 0;
        int cost        = -prices[0];
        int profit      = 0;

        // **** traverse the prices array - O(n) ****
        for (int day = 1; day < prices.length; day++) {

            // **** save max profit ****
            int tmp = maxProfit;

            // **** update max profit ****
            // maxProfit = Math.max(maxProfit, profit);
            maxProfit = maxProfit > profit ? maxProfit: profit;

            // **** compute profit ****
            profit = cost + prices[day];

            // **** update cost ****
            // cost = Math.max(cost, tmp - prices[day]);
            cost = cost > tmp - prices[day] ? cost : tmp - prices[day];

            // ??? ?????
            System.out.println( "maxProfit <<< day: " + day + " cost: " + cost + 
                                " profit: " + profit + " maxProfit: " + maxProfit);
        }

        // **** return the max of these values ****
        //return Math.max(maxProfit, profit);
        return maxProfit > profit ? maxProfit : profit;
    }


    /**
     * Find the maximum profit you can achieve.
     * Building dp[][] array.
     * 
     * 210 / 210 test cases passed.
     * Status: Accepted
     * Runtime: 3 ms
     * Memory Usage: 39.2 MB
     * 
     * Runtime: O(n) - Space: O(n * 2)
     */
    static public int maxProfit0(int[] prices) {

        // **** initialization ****
        int len = prices.length;

        // **** sanity check(s) ****
        if (len <= 1)
            return 0;

        // **** base case(s) ****
        if (len == 2 && prices[1] > prices[0])
            return prices[1] - prices[0];
        else if (len == 2 && prices[0] > prices[1])
            return 0;

        // **** initialize dp[][] ****
        int[][] dp  = new int[len][2];

        dp[0][0]    = 0;
        dp[0][1]    = -prices[0];

        dp[1][0]    = Math.max(dp[0][0], dp[0][1] + prices[1]);
        dp[1][1]    = Math.max(dp[0][1], dp[0][0] - prices[1]);

        // **** loop through the remaining days - O(n) ****
        for (int day = 2; day < len; day++) {
         
            // **** have no stock ****
            dp[day][0] = Math.max(dp[day - 1][0], dp[day - 1][1] + prices[day]);

            // **** have stock ***
            dp[day][1] = Math.max(dp[day - 1][1], dp[day - 2][0] - prices[day]);

            // ???? ????
            System.out.println("maxProfit0 <<< dp:");
            for (int i = 0; i < len; i++)
                System.out.println("day: " + i + " " + Arrays.toString(dp[i]));
        }

        // ***** return max profit (not holding a stock) ****
        return dp[len - 1][0];
    }


    /**
     * Test scaffold
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        
        // **** open buffered reader ****
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // **** read int[] of prices ****
        int[] prices = Arrays.stream(br.readLine().trim().split(","))
                            .mapToInt(Integer::parseInt)
                            .toArray();

        // **** close buffered reader ****
        br.close();

        // ???? ????
        System.out.println("main <<< prices: " + Arrays.toString(prices));

        // **** call function of interest and display result ****
        System.out.println("main <<< output: " + maxProfit0(prices));

        // **** call function of interest and display result ****
        System.out.println("main <<< output: " + maxProfit(prices));
    }
}
package programmers;

public class perfectCrime {
    static final int MAX_VALUE = Integer.MAX_VALUE / 2;

    public static void main(String[] args) {
        int[][] info = {{1, 2}, {2, 3}, {2, 1}};
        int n = 4;
        int m = 4;

        System.out.println(solution(info, n, m));
    }
    static int solution(int[][] info, int n, int m) {
        int itemLength = info.length;
        int[][] dp = new int[itemLength+1][m];

        // dp 초기화
        for (int i = 0; i <= itemLength; i++) {
            for (int j = 0; j < m; j++) {
                dp[i][j] = MAX_VALUE;
            }
        }

        // 0번째 물건을 훔쳤고, a,b 모두 0
        dp[0][0] = 0;
        for (int i = 0; i < itemLength; i++) {
            for (int j = 0; j < m; j++) {
                if (dp[i][j] == MAX_VALUE) {
                    continue;
                }

                int nextA = dp[i][j] + info[i][0];
                if (nextA < n) {
                    dp[i+1][j] = Math.min(nextA, dp[i+1][j]);
                }

                int nextB = j + info[i][1];
                if (nextB < m) {
                    dp[i+1][nextB] = Math.min(dp[i][j], dp[i+1][nextB]);
                }
            }
        }

        int answer = MAX_VALUE;
        for (int j=0; j<m; j++) {
            answer = Math.min(answer, dp[itemLength][j]);
        }
        return answer == MAX_VALUE ? -1 : answer;
    }
}

package baekjoon.dynamic_programming;

import java.io.IOException;
import java.util.Scanner;

public class Baekjoon_No_2193 {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        // long으로 선언한 이유 : int의 범위를 벗어나기 때문
        long[] d = new long[91];

        d[1] = 1;
        d[2] = 1;

        // 점화식 : d[i] = d[i-1] + d[i-2];
        for(int i=3; i<=N; i++) {
            d[i] = d[i-1] + d[i-2];
        }

        System.out.println(d[N]);
    }

}
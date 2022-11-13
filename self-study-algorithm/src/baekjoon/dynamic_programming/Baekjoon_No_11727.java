package baekjoon.dynamic_programming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baekjoon_No_11727 {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] d = new int[1001];
        d[1] = 1;
        if(N>1)
            d[2] = 3;

        // 점화식 : d[i] = (d[i-2]*2) + (d[i-1]);
        for(int i=3; i<=N; i++) {
            d[i] =((d[i-2]*2) + (d[i-1]));
        }

        System.out.println(d[N] );
    }

}
package baekjoon.implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// Link : https://www.acmicpc.net/problem/14719
public class Baekjoon_No_14719{

    public static int H, W;

    public static int[] heightArr;

    public static int result = 0;

    public static void main(String[] args) throws IOException{
        // TODO Auto-generated method stub
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        heightArr = new int[W];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<W; i++) {
            heightArr[i] = Integer.parseInt(st.nextToken());
        }

        calcRain();

        System.out.println(result);
    }

    /*
     1. 첫 번째와 마지막 벽을 제외하고 확인
     2. 양 옆의 최대 벽 길이를 구하기
     3. 두 개중 작은 값 - 현재 벽 높이
    */
    public static void calcRain() {

        // 첫 번째, 마지막 벽을 제외한 벽 하나씩 확인
        for(int i=1; i<W-1; i++) {
            int now = heightArr[i]; 	// 현재 벽의 높이
            int left = 0;           	// 현재 기준 왼쪽에서 가장 큰 벽의 높이
            int right = 0;          	// 현재 기준 오른쪽에서 가장 큰 벽의 높이

            // 현재 기준 왼쪽에서 가장 큰 벽의 높이 구하기
            for(int j=i-1; j>=0; j--) {
                if(left < heightArr[j]) {
                    left = heightArr[j];
                }
            }

            // 현재 기준 오른쪽에서 가장 큰 벽의 높이 구하기
            for(int j=i+1; j<W; j++) {
                if(right < heightArr[j]) {
                    right = heightArr[j];
                }
            }

            // 작은 벽을 기준으로 계산
            int min = Math.min(left, right);
            if(min > now) {
                result += (min - now);
            }
        }
    }

}
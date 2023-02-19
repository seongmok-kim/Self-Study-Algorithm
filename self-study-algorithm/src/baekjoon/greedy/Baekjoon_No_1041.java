package baekjoon.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon_No_1041 {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        long N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] arr = new int[6];
        long min1 = Integer.MAX_VALUE;
        long min2 = Integer.MAX_VALUE;
        long min3 = Integer.MAX_VALUE;
        long max = 0;
        long diceSum = 0;

        // A=0, B=1, C=2, D=3, E=4, F=5
        for(int i=0; i<6; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            min1 = Math.min(min1, arr[i]);
            max = Math.max(max, arr[i]);
            diceSum += arr[i];
        }

        if(N == 1) {
            System.out.println(diceSum - max);
            return;
        }

        // 2면의 합 중 최소값 찾기
        for(int i=0; i<6; i++) {
            for(int j=0; j<6; j++) {
                int sum = arr[i];
                if(j == i) {
                    continue;
                }

                // 서로 붙어있을 수 없는 면들
                // 0, 5
                // 1, 4
                // 2, 3
                if((j == 0 && i == 5) || (j == 5 && i == 0)) {
                    continue;
                }
                if((j == 1 && i == 4) || (j == 4 && i == 1)) {
                    continue;
                }
                if((j == 2 && i == 3) || (j == 3 && i == 2)) {
                    continue;
                }

                sum += arr[j];
                min2 = Math.min(min2, sum);
            }
        }

        // 3면의 합 중 최소값 찾기
        min3 = Math.min(arr[0], arr[5]) + Math.min(arr[1], arr[4]) + Math.min(arr[2], arr[3]);

        long diceOutAreaCnt = (N*2) + ((N-2)*2) ;
        long topMin3Cnt = 4;					// 맨 윗층에서 3면이 보이는 주사위 4개
        long topMin2Cnt = diceOutAreaCnt - 4;	// 맨 윗층에서 3면이 보이는 주사위를 제외한 가장자리 주사위 갯수. (두 면이 보이는 주사위)
        long topMin1Cnt = (N*N) - topMin3Cnt - topMin2Cnt;	// 맨 윗층에서 윗면만 보이는 주사위 갯수(한 면이 보이는 주사위)
        long top = (min3*topMin3Cnt) + (min2*topMin2Cnt) + (min1*topMin1Cnt);

        long bottomMin2Cnt = 4;
        long bottomMin1Cnt = diceOutAreaCnt-4;
        long bottom = ((min2*bottomMin2Cnt) + (min1*bottomMin1Cnt)) * (N-1);

        System.out.println((top + bottom));

    }
}
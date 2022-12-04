package baekjoon.implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon_No_18111 {

    public static int N, M, B;

    public static int[][] map;

    public static int RESULT_TIME = Integer.MAX_VALUE;
    public static int RESULT_HEIGHT = 0;

    public static int MIN = Integer.MAX_VALUE;
    public static int MAX = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());

            for(int j=0; j<M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                // 맵의 최소 최대 높이를 저장해둠
                MIN = Math.min(MIN, map[i][j]);
                MAX = Math.max(MIN, map[i][j]);
            }
        }

        // 맵의 최소 높이부터 256 높이까지 탐색
        for(int height=MIN; height<=256; height++) {
            int needUp = 0;			// 쌓아야 하는 블럭의 수
            int needDown = 0;		// 파야 하는 블럭의 수
            int have = B;			// 인벤토리에 가지고 있는 블럭의 수

            for(int i=0; i<N; i++) {
                for(int j=0; j<M; j++) {
                    int num = height - map[i][j];

                    if(num == 0) {
                        continue;
                    }
                    else if(num < 0) {
                        needDown += Math.abs(num);
                    }
                    else {
                        needUp += Math.abs(num);
                    }
                }
            }

            have += needDown;									// 판 블럭만큼 인벤토리에 추가
            if(needUp <= have) {								// 매꾸는 데 필요한 블럭의 수을 가지고 있을 때
                int time = (needDown * 2) + (needUp);			// 블럭을 파는건 2초, 블럭을 설치하는 건 1초

                // 같은 시간일 때 높이가 더 높은 걸로 출력하라고 문제에 명시됨
                if(RESULT_TIME >= time) {
                    RESULT_TIME = time;
                    RESULT_HEIGHT = height;
                }
            }
            // 현재 높이가 MAP의 최대 높이보다 높은 상태에서
            // 쌓아야 하는 블럭의 수보다 가지고 있는 블럭의 수가 적을 때, 다음 높이에서 쌓을 수 있을리가 없기 때문에 탈출
            else if(height > MAX){
                break;
            }

        }

        System.out.println(RESULT_TIME + " " + RESULT_HEIGHT);
    }
}
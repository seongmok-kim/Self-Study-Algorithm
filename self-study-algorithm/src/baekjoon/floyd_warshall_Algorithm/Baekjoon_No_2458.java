package baekjoon.floyd_warshall_Algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Baekjoon_No_2458 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());       // 학생 수
        int M = Integer.parseInt(st.nextToken());       // 비교 수

        ArrayList<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>();
        int[][] map = new int[N+1][N+1];

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            map[a][b] = 1;
            map[b][a] = 2;

            // 1 : a보다 b가 큼. 2: b보다 a가 큼
        }

        for(int i=1; i<=N; i++){
            for(int from=1; from<=N; from++){
                for(int to=1; to<=N; to++){
                    if(map[from][i] == 1 && map[i][to] == 1){
                        map[from][to] = 1;
                        map[to][from] = 2;
                    }

                    else if(map[from][i] == 2 && map[i][to] == 2){
                        map[from][to] = 2;
                        map[to][from] = 1;
                    }
                }
            }
        }

        int num = 0;
        for(int i=1; i<=N; i++){
            boolean flag = false;
            for(int j=1; j<=N; j++){
                if(i == j) continue;

                // 모든 학생들이랑 비교를 한 적이 없으면 자기 순서를 모른다.
                if(map[i][j] == 0){
                    flag = true;
                }
            }
            if(!flag)
                num++;
        }

        System.out.println(num);
    }
}
package baekjoon.sorting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baekjoon_No_11403 {

    public static int V, E;
    public static int[][] map;
    public static final int INF = (int)1e8;				// 무한으로 취급될 변수. Integer.MAX_VALUE를 하면 스택 오버 플로우가 발생하므로 이와 같이 적당하게 설정

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        map = new int[V+1][V+1];

        // 1. map 변수에 INF(무한)으로 초기화
        for(int i=1; i<=V; i++) {
            Arrays.fill(map[i], INF);		// map은 11000(무한 취급)으로 초기화
            map[i][i] = 0;					// 자기 자신에게 가는건 0으로 초기화
        }

        // 2. map 변수에 각 거리 정보를 넣어줌
        for(int i=0; i<E; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            map[from][to] = cost;
        }

        // 3. 플로이드-와샬 알고리즘을 이용해 각 정점 별 최단 거리를 구한다.
        for(int i=1; i<=V; i++) {
            for(int from=1; from<=V; from++) {
                for(int to=1; to<=V; to++) {
                    if(from == to)
                        continue;

                    map[from][to] = Math.min(map[from][to], map[from][i] + map[i][to]);
                }
            }
        }

        int length = INF;

        // 4. 최단 거리 계산
        for( int start=1; start<=V; start++ ) {
            for(int end=1; end<=V; end++) {
                if(start == end)
                    continue;

                // map[시작점][끝점] 과 map[끝점][시작점]이 INF가 아니라는 건. 왕복할 수 있다는 뜻.
                if(map[start][end] != INF && map[end][start] != INF) {
                    length = Math.min(length, map[start][end] + map[end][start]);
                }
            }
        }

        // 사이클이 단 하나라도 없으면 -1 출력
        if(length == INF)
            System.out.println(-1);
        else
            System.out.println(length);
    }
}
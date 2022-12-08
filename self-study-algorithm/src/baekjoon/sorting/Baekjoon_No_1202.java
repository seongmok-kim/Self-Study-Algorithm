package baekjoon.sorting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 첫 번째 방법 :
 보석의 정렬 방법 : 가치 내림차순
 가방의 정렬 방법 : 오름차순

 보석을 탐색하면서 넣을 수 있는 가방을 전체 탐색.
 (가방을 찾으면 해당 가방은 가방 리스트에서 제거)

 -> 이 방법은 시간 초과 발생.
 */

// Link : https://www.acmicpc.net/problem/1202
// 아래의 블로그에서 도움받아서 해결
// 블로그 링크 : https://devowen.com/300
public class Baekjoon_No_1202 {

    public static class Jewel implements Comparable<Jewel>{
        int weight;
        int value;

        public Jewel(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }

        @Override
        public int compareTo(Jewel other) {		// 무게 오름차순 정렬
            return this.weight - other.weight;
        }
    }

    public static void main(String[] args) throws IOException{
        int N, K;
        Jewel[] jewelArr;
        int[] bagArr;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        jewelArr = new Jewel[N];
        bagArr = new int[K];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());

            int weight = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());

            Jewel jewel = new Jewel(weight, value);
            jewelArr[i] = jewel;
        }

        for(int i=0; i<K; i++) {
            int weight = Integer.parseInt(br.readLine());

            bagArr[i] = weight;
        }

        Arrays.sort(jewelArr);		// 보석 무게 오름차순 정렬 (Jewel 클래스에서 정렬 방법 정의)
        Arrays.sort(bagArr);		// 가방 무게 오름차순 정렬 (int는 기본적으로 오름차순)

        // 오름차순 정렬. (높은 숫자가 먼저 나옴)
        Queue<Integer> pq = new PriorityQueue<>((x, y) -> Integer.compare(y, x));

        long totalValue = 0;
        int idx = 0;

        // i : 가방 인덱스, idx : 보석 인덱스
        for (int i = 0; i < K; i++) {

            // 가방에 넣을 수 있는 모든 보석은 pq에 넣음.
            while (idx < N && jewelArr[idx].weight <= bagArr[i]) {
                pq.add(jewelArr[idx].value);
                idx++;
            }

            // 가장 앞에 있는 보석이 가장 가치있는 보석이다.
            if (!pq.isEmpty()) {
                totalValue += pq.poll();
            }
        }

        System.out.println(totalValue);
    }

}
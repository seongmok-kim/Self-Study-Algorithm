package baekjoon.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Baekjoon_No_2212 {

    public static class Pos implements Comparable<Pos>{
        int idx;
        int dis;		// 뒷 센서와의 거리 차이

        public Pos(int idx, int dis) {
            this.idx = idx;
            this.dis = dis;
        }

        @Override
        public int compareTo(Pos o) {
            return o.dis - this.dis;
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());
        int[] arr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 내 뒤에 있는 센서랑 거리 계산 후 우선순위 큐에 넣기
        Arrays.sort(arr);
        int totalLength = arr[arr.length-1] - arr[0];		// 센서들 간의 총 길이 구하기

        PriorityQueue<Pos> pq = new PriorityQueue<>();
        int before = arr[0];
        for(int i=1; i<N; i++) {
            if(before == arr[i])		// 같은 건 무시
                continue;

            int gap = arr[i] - before;		// 차이를 구하고
            before = arr[i];				// 이전 센서 위치 갱신
            pq.offer(new Pos(before, gap));	// 우선순위 큐에 넣기
        }

        // 우선순위 큐에서 센서간의 거리가 먼 것부터 가져오기
        int temp = 0;
        for(int i=0; i<K-1; i++) {		// 집중국 -1의 갯수만큼 확인
            if(pq.isEmpty())
                break;

            temp += pq.poll().dis;		// 제외 할 길이 계산
        }

        System.out.println(totalLength - temp);		// 총 길이 - 제외할 길이
    }
}

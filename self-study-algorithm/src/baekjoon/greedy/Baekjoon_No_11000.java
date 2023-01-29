package baekjoon.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Baekjoon_No_11000 {

    public static class Time implements Comparable<Time>{
        int start;
        int end;

        public Time(int s, int e) {
            start = s;
            end = e;
        }

        @Override
        public int compareTo(Time o) {
            if(this.start == o.start) {
                return this.end - o.end;
            }
            else {
                return this.start - o.start;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        Time[] times = new Time[N];

        for(int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            times[i] = new Time(start, end);
        }

        // 시작시간, 종료시간 오름차순으로 정렬
        Arrays.sort(times);

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.offer(times[0].end);

        for(int i=1; i<N; i++) {
            if(times[i].start >= pq.peek()) {
                pq.poll();
            }
            pq.offer(times[i].end);
        }

        System.out.println(pq.size());
    }

}
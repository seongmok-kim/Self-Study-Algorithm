package baekjoon.implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Baekjoon_No_11279 {

    // Comparable 인터페이스를 구현해서 커스텀 정렬 순서 정의
    static class CustomInt implements Comparable<CustomInt>{
        int num;

        public CustomInt(int num) {
            this.num = num;
        }

        @Override
        public int compareTo(CustomInt other) {
            if(other.num < this.num) {
                return -1;
            }
            else {
                return 1;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        // 우선순위 큐를 이용해서 풀이
        PriorityQueue<CustomInt> pq = new PriorityQueue<>();

        StringBuilder sb = new StringBuilder();

        for(int i=0; i<N; i++) {
            int num = Integer.parseInt(br.readLine());

            if(num == 0) {
                if(pq.isEmpty()) {
                    sb.append("0\n");
                }
                else {
                    sb.append(pq.poll().num+"\n");
                }
            }
            else {
                pq.offer(new CustomInt(num));
            }
        }

        System.out.println(sb);
    }

}
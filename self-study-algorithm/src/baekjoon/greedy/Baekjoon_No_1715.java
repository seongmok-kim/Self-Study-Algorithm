package baekjoon.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Baekjoon_No_1715 {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i=0; i<N; i++) {
            pq.offer(Integer.parseInt(br.readLine()));
        }

        if(N == 1) {
            System.out.println(arr[0]);
            return;
        }

        int sum = 0;
        while(!pq.isEmpty()) {
            int num1 = pq.poll();
            if(pq.isEmpty()) {
                break;
            }
            int num2 = pq.poll();
            int temp = num1 + num2;
            pq.offer(temp);
            sum += temp;
        }
        System.out.println(sum);

    }

}

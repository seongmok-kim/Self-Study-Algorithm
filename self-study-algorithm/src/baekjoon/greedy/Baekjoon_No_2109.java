package baekjoon.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Baekjoon_No_2109 {
    static class Task implements Comparable<Task> {
        int pay;
        int date;

        public Task(int pay, int date) {
            this.pay = pay;
            this.date = date;
        }

        @Override
        public int compareTo(Task o) {
            if(this.pay != o.pay){
                return o.pay - this.pay;
            }
            else{
                return o.date - this.date;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Task> pq = new PriorityQueue<>();

        int maxDate = 0;
        // 급여 > 남은일자
        for(int i=0; i<N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());

            int pay = Integer.parseInt(st.nextToken());
            int date = Integer.parseInt(st.nextToken());

            maxDate = Math.max(maxDate, date);
            pq.offer(new Task(pay, date));
        }

        boolean[] dateArr = new boolean[maxDate+1];

        int totalPay = 0;
        while(!pq.isEmpty()){
            Task task = pq.poll();
            int pay = task.pay;
            int date = task.date;

            for(int i=date; i>0; i--) {
                if (!dateArr[i]) {
                    dateArr[i] = true;
                    totalPay += pay;
                    break;
                }
            }
        }

        System.out.println(totalPay);
    }
}

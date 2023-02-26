package baekjoon.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Baekjoon_No_13904 {

    static class Homework implements Comparable<Homework>{
        int dueDate;
        int score;

        public Homework(int dueDate, int score) {
            this.dueDate = dueDate;
            this.score = score;
        }

        // 정렬 기준 : 점수 내림차순, 마감일 오름차순
        @Override
        public int compareTo(Homework other) {
            if(this.score != other.score)
                return other.score - this.score;
            else {
                return this.dueDate - other.dueDate;
            }
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Homework> pq = new PriorityQueue<>();
        for(int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int dueDate = Integer.parseInt(st.nextToken());
            int score = Integer.parseInt(st.nextToken());
            pq.offer(new Homework(dueDate, score));
        }

        // isAssigned[int] 가 true라면, 그 날 과제를 한 것임
        boolean[] isAssigned = new boolean[1001];		// 최대 1000일이라고 문제에 명시
        int totalScore = 0;

        while(!pq.isEmpty()) {
            Homework homework = pq.poll();

            int score = homework.score;
            int dueDate = homework.dueDate;

            for(int i=dueDate; i>0; i--) {
                if(isAssigned[i] == false) {
                    isAssigned[i] = true;
                    totalScore += score;

                    break;
                }
            }
        }

        System.out.println(totalScore);
    }

}
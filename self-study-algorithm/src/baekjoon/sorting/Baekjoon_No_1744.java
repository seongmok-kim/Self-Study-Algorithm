package baekjoon.sorting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Baekjoon_No_1744 {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());		// 수열의 길이
        int result = 0;		// 결과값

        // 양수를 모아둔 pq : 내림차순 정렬
        Queue<Integer> postive_pq = new PriorityQueue<>((x,y) -> (y-x));

        // 음수를 모아둔 pq : 오름차순 정렬
        Queue<Integer> negative_pq = new PriorityQueue<>((x,y) -> (x-y));

        // 0을 모아둔 q
        Queue<Integer> zero_q = new LinkedList<Integer>();

        for(int i=0; i<N; i++) {
            int num = Integer.parseInt(br.readLine());
            if(num > 0) {
                postive_pq.offer(num);
            }
            else if(num == 0) {
                zero_q.offer(num);
            }
            else {
                negative_pq.offer(num);
            }
        }

		/* 고려해야할 점
		 1. 음수가 2개 이상인 경우, 음수끼리 곱해서 양수로 만들 수 있다.
		 2. 음수가 하나이고, 0이 있는 경우, 음수와 0을 곱해서 0으로 만들 수 있다.
		 3. 양수가 2개 이상인 경우, 양수끼리 곱해서 더 큰 양수로 만들 수 있다.
		 	(단, 1이 2개 인 경우 곱셈보단 더하기가 더 크다.)
		 */

        // 세 큐 중 하나의 큐라도 남아있으면 계속 반복
        while( !postive_pq.isEmpty() || !negative_pq.isEmpty() || !zero_q.isEmpty() ) {

            // 음수가 2개 이상인 경우, 두 수를 곱해서(양수로 만들어서) result에 넣기
            if(negative_pq.size() >= 2) {
                int num1 = negative_pq.poll();
                int num2 = negative_pq.poll();

                result += (num1*num2);
            }
            // 음수가 1개 있고, 0도 있을 때 이 두 수를 곱한다고 가정함
            else if(!zero_q.isEmpty() && !negative_pq.isEmpty()) {
                zero_q.poll();		// 0을 빼주고
                negative_pq.poll();	// 음수도 빼주고

                // 곱한 결과가 0이므로, result에 굳이 더하진 않음

            } else {
                // 양수가 2개 이상인 경우
                if(postive_pq.size() >= 2) {
                    int num1 = postive_pq.poll();
                    int num2 = postive_pq.poll();

                    // 일반적으로는 양수는 덧셈보다 두 수를 곱한 값이 더 크지만
                    // 두 수 중에서 하나라도 1이면 덧셈이 더 크다.
                    if(num1 == 1 || num2 == 1)
                        result += num1+num2;
                    else
                        result += (num1*num2);
                } else {
                    // 마무리 단계
                    // 남아있는 음수와 양수를 result에 더해준다.
                    while(!negative_pq.isEmpty()) {
                        result += negative_pq.poll();
                    }

                    while(!postive_pq.isEmpty()) {
                        result += postive_pq.poll();
                    }

                    zero_q.clear();		// 0은 굳이 더하지 않고 clear만 해준다. (0은 음수 하나를 0으로 만들 때만 사용됨)
                }
            }
        }

        System.out.println(result);
    }

}
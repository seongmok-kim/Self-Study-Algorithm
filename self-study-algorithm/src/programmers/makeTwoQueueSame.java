package programmers;

import java.util.LinkedList;
import java.util.Queue;

public class makeTwoQueueSame {
    public int solution(int[] queue1, int[] queue2) {
        int length = queue1.length * 2;
        Queue<Integer> q1 = new LinkedList<Integer>();
        Queue<Integer> q2 = new LinkedList<Integer>();

        long sum1 = 0, sum2 = 0;
        for(int num : queue1){
            q1.offer(num);
            sum1 += num;
        }

        for(int num : queue2){
            q2.offer(num);
            sum2 += num;
        }

        // 두 수의 합을 2로 나눴을 때 딱 떨어지지 않으면 -1 반환(두 큐에 어떻게 해도 같은 값을 못 둠)
        if((sum1+sum2) % 2 != 0){
            return -1;
        }

        long division = (sum1+sum2) / 2;
        int cnt1 = 0, cnt2 = 0;

        // 각 2배 길이까지 할 수 탐색할 수 있게 설정
        while(cnt1 <= length && cnt2 <= length){
            if(sum1 == division){
                return cnt1+cnt2;
            }

            // sum1이 더 큰경우
            if(sum1>division){
                if(!q1.isEmpty()){
                    int num = q1.poll();
                    sum1 -= num;
                    sum2 += num;
                    q2.offer(num);
                    cnt1++;
                }
            }
            // sum2가 더 큰 경우
            else{
                if(!q2.isEmpty()){
                    int num = q2.poll();
                    sum2 -= num;
                    sum1 += num;
                    q1.offer(num);
                    cnt2++;
                }
            }
        }
        return -1;
    }
}

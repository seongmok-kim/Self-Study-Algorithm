package baekjoon.sorting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Baekjoon_No_11497 {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        // 테스트 케이스의 수 만큼 반복
        for(int i=0; i<T; i++) {
            int N = Integer.parseInt(br.readLine());
            int[] numArr = new int[N];

            // 1. numArr에 넣기
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                numArr[j] = Integer.parseInt(st.nextToken());
            }

            // 2. numArr을 오름차순으로 정렬
            Arrays.sort(numArr);

            // (중요) 3. 덱에 앞뒤로 번갈아가면서 넣어주기
            // 그래야 기둥끼리 높이 차이가 최저로 나온다.
            Deque<Integer> dq = new LinkedList();
            for(int j=0; j<N; j++) {
                int num = numArr[j];

                if(j % 2 == 0) {
                    dq.addFirst(num);
                }
                else {
                    dq.addLast(num);
                }
            }

            // 4. 맨 앞과 맨 뒤를 꺼내고
            int dqFirst = dq.peekFirst();
            int dqLast = dq.peekLast();

            // 5. 앞에서 두 개 꺼내고
            int num1 = dq.poll();
            int num2 = dq.poll();

            // 6. 맨 앞과 맨 뒤의 높이 차이 vs 앞 두 기둥의 높이 차이 중 큰 걸 diff 에 넣기
            int diff = Math.max(Math.abs(num1-num2), Math.abs(dqFirst - dqLast));

            // 7. num1을 num2에 넣기
            num1 = num2;

            // dq가 빌 때까지 반복
            while(!dq.isEmpty()) {
                num2 = dq.poll();	// 8. num2에 넣고

                // 9. num1과 num2의 차이를 구하고
                int tempDiff = Math.abs(num1 - num2);

                // 10. diff와 tempDiff중 큰 걸 diff에 넣음
                diff = Math.max(diff, tempDiff);

                // 11. num1을 num 2에 넣음
                num1 = num2;
            }

            // 결과 값 sb에 저장
            sb.append(diff+"\n");
        }

        // 결과 출력
        System.out.println(sb.toString());
    }

}
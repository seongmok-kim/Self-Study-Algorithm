package baekjoon.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baekjoon_No_1092 {
    public static void main(String[] args) throws IOException{
        int N, M;
        int[] craneArr, boxArr;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        craneArr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            craneArr[i] = Integer.parseInt(st.nextToken());

        }
        Arrays.sort(craneArr);

        M = Integer.parseInt(br.readLine());
        boxArr = new int[M];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<M; i++) {
            int num = Integer.parseInt(st.nextToken());
            boxArr[i] = num;
        }
        Arrays.sort(boxArr);

        // 가장 큰 크레인보다 무게가 큰 경우 -1 출력
        if(boxArr[M-1] > craneArr[N-1]) {
            System.out.println(-1);
            System.exit(0);;
        }

        int cnt = 0;
        ArrayList<Integer> boxList = new ArrayList<>();

        for(int num : boxArr)
            boxList.add(num);

        // 박스리스트가 빌 때까지 반복
        while(!boxList.isEmpty()) {

            // 크레인의 갯수 만큼 반복
            for(int i=N-1; i>=0; i--) {
                int nowCrane = craneArr[i];

                int boxListSize = boxList.size();
                // 가장 무거운 짐부터 옮길 수 있는 지 확인
                for(int j=boxListSize-1; j>=0; j--) {
                    if(nowCrane >= boxList.get(j)) {
                        boxList.remove(j);		// 짐을 들 수 있으면, 해당 짐 제거하고 break
                        break;
                    }
                }
            }
            cnt++;
        }

        System.out.println(cnt);

    }
}
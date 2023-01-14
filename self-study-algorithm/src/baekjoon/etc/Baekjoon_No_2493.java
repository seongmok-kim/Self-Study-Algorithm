package baekjoon.etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Baekjoon_No_2493 {

    public static class Pos{
        public int height;
        public int index;

        public Pos(int height, int index) {
            this.height = height;
            this.index = index+1;
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());	// 기둥 갯수
        int[] arr = new int[N];	// 기둥 높이 배열
        int[] ans = new int[N];	// 정답 배열

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Stack<Pos> stack = new Stack<>();

        stack.add(new Pos(arr[0], 0));

        for(int i=1; i<N; i++) {

            int now = arr[i];

            // 스택을 이용해서 풀이
            // 현재 높이보다 큰 기둥을 만날 때까지 Stack pop wlsgod
            int stackSize = stack.size();
            for(int j=0; j<stackSize; j++) {
                Pos prev = stack.peek();

                if(prev.height >= now) {
                    ans[i] = prev.index;
                    break;
                }
                stack.pop();
            }

            // 현재 기둥 Stack에 넣음
            stack.add(new Pos(arr[i], i));
        }

        StringBuilder sb = new StringBuilder();
        for(int i=0; i<N; i++) {
            sb.append(ans[i] + " ");
        }
        System.out.println(sb.toString());
    }
}

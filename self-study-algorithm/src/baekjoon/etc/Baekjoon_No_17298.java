package baekjoon.etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Baekjoon_No_17298 {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        int[] ans = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Stack<Integer> stack = new Stack<Integer>();

        /*** 배열의 우측부터 확인 ***/
        // 가장 우측은 무조건 -1임.
        stack.add(arr[N-1]);
        ans[N-1] = -1;

        for(int i=N-2; i>=0; i--) {
            int now = arr[i];
            int stackSize = stack.size();

            boolean isExist = false;		// 오큰수를 찾았는 지에 대한 여부
            for(int j=0; j<stackSize; j++) {
                int num = stack.peek();

                // 스택 안에 있는 건. 오른쪽에 있는 숫자들.
                // 오른쪽 숫자가 현재 숫자보다 크면 오큰수
                if(num > now) {
                    ans[i] = num;		// 해당 숫자 오큰수로 넣어주고
                    isExist = true;		// 오큰수 찾았다고 flag값 수정
                    break;
                }

                stack.pop();			// 오큰수가 아니라면 그 숫자 스택에서 빼고 다시 반복
            }

            // 오큰수를 결국 못찾았다면 -1로 기록
            if(!isExist) {
                ans[i] = -1;
            }

            stack.add(now);		// 현재 숫자 스택에 넣기
        }

        StringBuilder sb = new StringBuilder();
        for(int num : ans) {
            sb.append(num + " ");
        }

        System.out.println(sb.toString());
    }

}

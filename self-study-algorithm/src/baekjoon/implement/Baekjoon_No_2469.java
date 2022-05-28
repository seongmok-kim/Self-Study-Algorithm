package baekjoon.implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**

 - Link: https://www.acmicpc.net/problem/2469
 - 문제 설명
   시작지점: A B ... (알파벳 순서)
   *: 잇는 줄 X, -: 잇는 줄 O, ?: 주어지지 않음
   3번 째로 입력받은 문자열이 사다리 결과가 되도록 설정하는 것이 목표
   만약 불가능하다면 k-1만큼 x(소문자)를 출력
   -가 연속으로 이어질 수 없음(문제조건)

 */
public class Baekjoon_No_2469 {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int K = Integer.parseInt(br.readLine());
        int N = Integer.parseInt(br.readLine());

        char[] startArr = new char[K];         
        for(int i=0; i< startArr.length; i++){
            startArr[i] = (char)('A' + i);
        }
        String target = br.readLine();
        char[] targetArr = target.toCharArray();

        char[][] map = new char[N][K-1];
        int lineIdx = -1;           // ?가 나오는 줄의 인덱스

        for(int i=0; i<N; i++){
            String temp = br.readLine();

            map[i] = temp.toCharArray();
            if(map[i][0] == '?')
                lineIdx = i;
        }

        // ?전까지의 사다리 결과 구하기
        for(int i=0; i< lineIdx; i++){
            for(int j=0; j<K-1; j++){
                // -일때만 앞뒤로 스왑
                if(map[i][j] == '-'){
                    char temp = startArr[j];
                    startArr[j] = startArr[j+1];
                    startArr[j+1] = temp;
                }
            }
        }

        // 문제에서 준 문자열에서 ? 후까지의 사다리 결과 구하기
        for(int i=N-1; i > lineIdx; i--){
            for(int j=0; j<K-1; j++){
                // -일때만 앞뒤로 스왑
                if(map[i][j] == '-'){
                    char temp = targetArr[j];
                    targetArr[j] = targetArr[j+1];
                    targetArr[j+1] = temp;
                }
            }
        }

        StringBuilder sb = new StringBuilder();

        // 이제 위에서 구한 startArr과 targetArr만 비교
        for(int i=0; i<K-1; i++){
            // 같다면 *
            if(startArr[i] == targetArr[i]){
                sb.append("*");
            }
            // 하나차이로 다르다면 -
            else if(startArr[i] == targetArr[i+1] || startArr[i+1] == targetArr[i]){
                sb.append("-");

                // 스왑해주기
                char temp = startArr[i];
                startArr[i] = startArr[i+1];
                startArr[i+1] = temp;
            }
            // 위에 조건들이 아니라면 두 칸이상 차이난다는 것이기 때문에 x출력
            else{
                sb = new StringBuilder();
                for(int j=0; j<K-1; j++){
                    sb.append("x");
                }
                break;
            }
        }
        System.out.println(sb);
    }


}
package baekjoon.string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// Link : https://www.acmicpc.net/problem/5052
public class Baekjoon_No_5052 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		int caseCount;
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		caseCount = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for(int i=0; i<caseCount; i++) {
			int N = Integer.parseInt(br.readLine());
			
			String[] strArr = new String[N];
			String result = "YES";
			
			for(int j=0; j<N; j++) {
				String s = br.readLine();
				
				strArr[j] = s;
			}
			
			// String 타입으로 Arrays sort를 진행하면 문자열 형식으로 정렬이 됨.
			// ex) 가, 가나, 가나다, 나, 나가, 다 ...
			// 앞의 문자열이 현재 문자열의 접두어인 지 체크하면 됨
			Arrays.sort(strArr);

			for(int j=1; j < N; j++) {
				// startsWith() 함수로 간편하게 확인 가능
				if(strArr[j].startsWith(strArr[j - 1])) {
                    result = "NO";
                    break;
                }
			}
			
			sb.append(result+"\n");
		}
		
		System.out.println(sb.toString());
	}
}
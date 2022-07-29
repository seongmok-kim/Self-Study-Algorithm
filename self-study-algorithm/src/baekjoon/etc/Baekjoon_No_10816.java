package baekjoon.etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

// Link : https://www.acmicpc.net/problem/10816
public class Baekjoon_No_10816{
	// N: 가지고 있는 카드의 수, M: 찾을 카드의 수
	public static int N, M;
	
	// HashMap을 이용해서 풀이예정
	public static HashMap<Integer, Integer> resultMap = new HashMap<>();;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			// 숫자 하나씩 받아서
			int num = Integer.parseInt(st.nextToken());
			
			// resultMap에 해당 숫자가 없으면 1넣어주고
			if(resultMap.get(num) == null) {
				resultMap.put(num, 1);
			}
			// 있으면, 증가 시킴
			else {
				resultMap.put(num, resultMap.get(num)+1);
			}
		}
		
		M = Integer.parseInt(br.readLine());
		
		st = new StringTokenizer(br.readLine());
		StringBuilder sb =new StringBuilder();
		
		// 해당 숫자가 몇 개 들어있는 지 확인
		for(int i=0; i<M; i++) {
			int num = Integer.parseInt(st.nextToken());
			
			int cnt;
			// 해당 숫자가 아예 없으면 0
			if(resultMap.get(num) == null) {
				cnt = 0;
			// 있으면 그대로 출력
			}else {
				cnt = resultMap.get(num);
			}
			sb.append(cnt + " ");
		}
		
		System.out.print(sb.toString());
	}
}
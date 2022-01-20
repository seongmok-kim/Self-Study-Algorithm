package baekjoon.search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon_No_2805 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 나무의 수
		int N = Integer.parseInt(st.nextToken());
		
		// 필요한 나무의 길이
		int M = Integer.parseInt(st.nextToken());
		
		long[] treeArr = new long[N];
		
		st = new StringTokenizer(br.readLine());
		
		// 목재절단기의 최소 높이
		long min = 0;
		
		// 목재절단기의 최대 높이
		long max = 0;
		
		// 나무들 세팅
		for(int i=0;i<N;i++) {
			treeArr[i]=Long.parseLong(st.nextToken());
			if(max < treeArr[i]) {
				max = treeArr[i];
			}
		}
		
		br.close();
		
		// 최소 높이가 최대 길이보다 커질 때까지 반복
		while(min<=max) {
			long mid = (min+max) / 2;
			
			long rest = 0;
			for(int i=0; i<N; i++) {
				if(treeArr[i] > mid) {
					rest += (treeArr[i] - mid);
				}
			}
			
			// 잘리고 남은 나무의 길이가 M보다 많거나 같을 때, 목재절단기의 최소 높이를 높인다.
			if(rest>=M) {
				min = mid + 1;
			}
			// 잘리고 남은 나무의 길이가 M보다 적을 때, 목재절단기의 최대 높이를 줄인다
			else {
				max = mid - 1;
			}
		}
		
		// 최대 높이 출력
		System.out.println(max);
		
	}
}
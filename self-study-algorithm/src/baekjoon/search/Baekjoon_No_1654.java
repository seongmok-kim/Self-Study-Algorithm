package baekjoon.search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon_No_1654 {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String temp = br.readLine();
		StringTokenizer st = new StringTokenizer(temp);
		
		int K = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[K];
		
		// 자르는 최소 길이(자연수라고 문제에 언급되어있으므로 1로 선언)
		long min = 1;
		
		// 자르는 최대 길이
		long max = 1;
		
		// 입력받으면서 최대값 확인
		for(int i=0;i<K;i++) {
			arr[i] = Integer.parseInt(br.readLine());
			if(max < arr[i]) {
				max = arr[i];
			}
		}
		br.close();
	
		// 최소길이가 최대길이보다 커질때까지 반복
		while(min<=max) {
			long mid = (min+max) / 2;
			
			int sum = 0;
			for(int i=0;i<arr.length;i++) {
				sum+=(arr[i]/mid);
			}
			
			// 나눠진 랜선들이 필요한 개수보다 많거나 같은 경우, 최소 길이를 늘린다.
			if(sum>=N) {
				min = mid+1;
			// 나눠진 랜선들이 필요한 개수보다 적은 경우, 최대 길이를 줄인다.
			}else {
				max = mid-1;
			}
		}

		System.out.println(max);
	}
	
}

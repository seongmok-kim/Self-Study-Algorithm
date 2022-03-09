package baekjoon.search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baekjoon_No_1920 {
 
	// 수열 
	public static int[] num;
	
	// 찾을 수 배열 
	public static int[] findNum;
	
	// 결과를 저장할 배
	public static boolean[] answer;
	
	public static int N1,N2;
	
	// 이분 탐색 
	public static void binarySearch(int start, int end, int target, int idx) {
		// 중간 인덱스 
		int mid = (start + end) / 2;
		
		// 중간 인덱스의 데이터가 타겟이면 결과 배열에 저장 
		if(num[mid] == target) {
			answer[idx] = true;
			return;
		}
		
		// 끝 인덱스보다 시작 인덱스가 커지면 재귀 종료 
		if(start > end) {
			return;
		}
		
		// 중간 인덱스의 데이터가 타겟보다 클 경우 중간 인덱스 기준 왼쪽을 확인 
		if(num[mid] > target) {
			binarySearch(start, mid-1, target ,idx);
		}
		// 중간 인덱스의 데이터가 타겟보다 작을 경우 중간 인덱스 기준 오른쪽을 확인 
		else {
			binarySearch(mid+1, end, target ,idx);
		}
	}
	
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N1 = Integer.parseInt(br.readLine());
		num = new int[N1];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N1; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}
		
		
		N2 = Integer.parseInt(br.readLine());
		findNum = new int[N2];
		answer = new boolean[N2];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N2; i++) {
			findNum[i] = Integer.parseInt(st.nextToken());
		}
		
		// 이분탐색을 진행하기 위해서 미리 정렬을 진행한다 
		Arrays.sort(num);
		
		// 이분탐색 진행 
		for(int i=0; i<N2; i++) {
			binarySearch(0, num.length-1, findNum[i], i);
		}
		
		// True면 1, False면 0 출력 
		for(int i=0; i<N2; i++) {
			if(answer[i])
				System.out.println(1);
			else
				System.out.println(0);
		}
	}

}

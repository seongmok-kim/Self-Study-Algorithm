package baekjoon.search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baekjoon_No_1920 {
 
	public static int[] num;
	
	public static int[] findNum;
	
	public static boolean[] answer;
	
	public static int N1,N2;
	
	public static void binarySearch(int start, int end, int target, int idx) {
		int mid = (start + end) / 2;
		
		if(num[mid] == target) {
			answer[idx] = true;
			return;
		}
		
		if(start > end) {
			return;
		}
		
		if(num[mid] > target) {
			binarySearch(start, mid-1, target ,idx);
			
		}
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
		
		Arrays.sort(num);
		
		for(int i=0; i<N2; i++) {
			binarySearch(0, num.length-1, findNum[i], i);
		}
		
		for(int i=0; i<N2; i++) {
			if(answer[i])
				System.out.println(1);
			else
				System.out.println(0);
		}
	}

}

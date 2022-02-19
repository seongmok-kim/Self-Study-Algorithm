package baekjoon.etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baekjoon_No_1929 {

	//각 수가 소수인지 아닌지 (true : 소수, false:소수X)
	public static boolean[] arr;
	
	// 에라토스테네스의 체 이용 
	public static void checkPrimeNumber(int start, int end) {
		
		// 2부터 제곱근까지만 확인 
		for(int i=2; i<=Math.sqrt(end); i++) {
			// 소수일 경우 
			if(arr[i] == true) {
				// 그 수의 곱들은 소수가 아님 
				for(int j=2; i*j<=end; j++) {
					arr[i*j] = false;
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		
		arr = new boolean[end+1];
		
		// 모두가 소수라고 초기화 
		Arrays.fill(arr, true);
		
		// 0과 1은 소수가 아님 
		arr[0] = false;
		arr[1] = false;
		
		// 소수 확인 
		checkPrimeNumber(start, end);
		
		// 배열을 확인하며  소수만 출력 
		for(int i=start; i<=end; i++) {
			if(arr[i] == true) {
				System.out.println(i);
			}
			
		}
	}

}

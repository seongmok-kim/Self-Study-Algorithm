package baekjoon.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// Link : https://www.acmicpc.net/problem/1541
public class Baekjoon_No_1541 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String str = br.readLine();
		
		// 1. 뺄셈은 가장 마지막에 진행해야하므로, -를 기준으로 split
		String[] arr = str.split("\\-");		// (중요)특수문자의 경우 \\를 붙여야함
		int[] intArr = new int[arr.length];		// 임시 저장해둘 정수형 배열
		
		for(int i=0; i<arr.length; i++) {
			String[] tempArr = arr[i].split("\\+");		// 2. + 연산자가 있는 경우 더하기 연산 진행 후 intArr에 저장
			
			int num = 0;
			for(String s : tempArr) {
				num += Integer.parseInt(s);
			}
			
			intArr[i] = num;
		}
		
		// 3. 마지막으로 뺄셈 연산 진행
		int result = intArr[0];
		for(int i=1; i<intArr.length;i++) {
			result -= intArr[i];
		}
		
		// 4. 결과 출력
		System.out.println(result);
	}
}
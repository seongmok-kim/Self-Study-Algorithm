package baekjoon.implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baekjoon_No_18406 {
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 점수를 문자열로 받고
		String N = br.readLine();
		
		// 그 문자열을 split 함수를 이용해 각 숫자를 문자열 배열에 넣는다.
		String[] arr = N.split("");
		
		// 문자열 배열 길이에 2를 나눠 나온 몫이 기준점이다.
		int center = arr.length/2;
		
		// 기준점 왼쪽의 합계 구하기
		int left = 0;
		for(int i=0; i<center; i++) {
			left += Integer.parseInt(arr[i]);
		}
		
		// 기준점 오른쪽의 합계 구하기
		int right = 0;
		for(int i=center; i<arr.length; i++) {
			right += Integer.parseInt(arr[i]);
		}
		
		// 왼쪽과 오른쪽 합이 같다면 럭키 사용 가능 - LUCKY 출력
		if(left == right) {
			System.out.println("LUCKY");
		}
		
		// 합이 다르다면 사용 불가 - READY 출력
		else {
			System.out.println("READY");
		}
	}
}

package baekjoon.greedy;

import java.util.Scanner;

public class Baekjoon_No_1439 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc  = new Scanner(System.in);
		
		String str = sc.nextLine();
		
		// 0으로 이루어진 그룹과 1로 이루어진 그룹 중 적은 그룹의 수를 찾으면 해결 가
		// 그룹? 연속된 숫자의 모임
 
		int count0 = 0;	// 0의 그룹 카운 
		int count1 = 0;	// 1의 그룹 카운트 
		
		String[] strArr = str.split("");
		
		// 처음 원소만 num에 넣고 해당 숫자 카운트 증가 
		String num = strArr[0];
		if(num.equals("0")) {
			count0++;
		}else{
			count1++;
		}
		
		// 2번째 원소부터 반복 시작
		// 이전 숫자와 현재 숫자가 다르면 해당 숫자 카운트 증가 및 이전 숫자 변경
		for(int i=1;i<strArr.length;i++) {
			if(!num.equals(strArr[i])) {
				if(strArr[i].equals("0")) {
					count0++;
				}else {
					count1++;
				}
				num=strArr[i];
			}
		}
		
		//결과 출
		if(count0>count1) {
			System.out.println(count1);
		}else {
			System.out.println(count0);
		}
	}

}

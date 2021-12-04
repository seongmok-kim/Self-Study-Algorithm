package baekjoon.level.level_8;

import java.util.*;
import java.io.*;

public class Baekjoon_No_1712 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		// 숫자형 정수를 int가 아닌 long으로 선언하는 이유
		// A,B,C는 21억이하의 자연수가 들어오는데, 서로 연산을 진행하면 21억을 넘길 수 있다.
		// int는 -21억 ~ 21억 사이만 처리할 수 있기 때문에 long으로 선언함
		
		// 고정비용
		long A = sc.nextLong();
		
		// 가변비용
		long B = sc.nextLong();
		
		// 노트북 가격
		long C = sc.nextLong();
		
		// 손익분기점 
		long count = 1;
		
		// 노트북 가격보다 가변 비용이 많을 경우 손익분기점이 존재하지 않음
		if(B > C) {
			count = -1;
		}
		
		// 손익분기점이 없다고 판단되지 않았다면 
		if(count != -1) {
			while(true) {
				long total = A + (B*count);
				long sellPrice = C*count;
				
				if(total<sellPrice) {
					break;
				}
				count++;
			}
		}
		
		System.out.println(count);
	}

}

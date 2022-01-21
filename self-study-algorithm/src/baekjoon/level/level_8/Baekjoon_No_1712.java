package baekjoon.level.level_8;

import java.util.*;

public class Baekjoon_No_1712 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
				Scanner sc = new Scanner(System.in);

				// 고정비용
				int A = sc.nextInt();
				
				// 가변비용
				int B = sc.nextInt();
				
				// 노트북 가격
				int C = sc.nextInt();
				
				// 손익분기점 
				int count = 1;
				
				// 노트북 가격보다 가변 비용이 많을 경우 손익분기점이 존재하지 않음
				if(B >= C) {
					count = -1;
				}
				
				// 손익분기점이 없다고 판단되지 않았다면 
				if(count != -1) {
					count = (A/(C-B)) + 1;
				}
				
				System.out.println(count);
	}

}

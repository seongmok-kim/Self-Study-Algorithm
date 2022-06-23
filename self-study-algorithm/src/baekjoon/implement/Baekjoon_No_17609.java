package baekjoon.implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

// Link : www.acmicpc.net/problem/17609
public class Baekjoon_No_17609 {

	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		
		for(int i=0; i<N; i++) {
			String str = br.readLine();
			int answer = Check(str);
			
			sb.append(answer + "\n");
		}
		
		System.out.println(sb);
	}
	
	// 해당 문자열이 회문인지 유사회문인지 둘 다 아닌 지 확인하는 함수
	public static int Check(String str) {
		
		// 덱을 사용함
		Deque<Character> d = new ArrayDeque<Character>();
		
		char[] charArr = str.toCharArray();
		
		// 덱에다가 문자열을 문자로 쪼개서 하나씩 넣음
		for(char c : charArr) {
			d.add(c);
		}
		
		// 0:회문, 1:유사회문, 2:아무것도아님
		int flag = 0;
		
		while(!d.isEmpty()) {
			// peekFirst() : 덱 맨 앞에서 하나를 확인(꺼내는 것이 아님)
			// peekLast() : 덱 맨 뒤에서 하나를 확인(꺼내는 것이 아님)
			char first = d.peekFirst();
			char last = d.peekLast();
			
			// 같으면
			if(first == last) {
				// 앞뒤 하나씩 뺌
				d.pollFirst();
				d.pollLast();
			}
			// 다르면
			else {
				// 이미 유사회문이라고 판단했을 때, 또 다르면 아무것도 아닌 것.
				if(flag == 1) {
					// flag를 2로 한 후 반복문 종료
					flag = 2;
					break;
				}
				// 유사회문 flag값 기록해두고
				flag = 1;
				
				// 맨 앞에서 하나 뺌
				d.pollFirst();
			}
		}
		
		// 회문, 유사회문일 경우 그 값 반환
		if(flag == 0 || flag == 1) {
			return flag;	
		}
		// 회문, 유사회문이 아닐 경우 맨 뒤에서 하나 빼는 경우 유사회문이 될 수 있기 때문에 한 번 더 확인 (윗 로직에서는 맨 앞에서 문자를 하나를 뺌)
		else {
			flag = 0;
			d = new ArrayDeque<Character>();
			for(char c : charArr) {
				d.add(c);
			}
			
			while(!d.isEmpty()) {
				char first = d.peekFirst();
				char last = d.peekLast();
				
				if(first == last) {
					d.pollFirst();
					d.pollLast();
				}
				else {
					if(flag == 1) {
						flag = 2;
						break;
					}
					flag = 1;
					d.pollLast();
				}
			}
			
			return flag;
		}
		
	}
}
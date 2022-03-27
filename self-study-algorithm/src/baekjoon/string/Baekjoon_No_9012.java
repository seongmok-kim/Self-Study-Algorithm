package baekjoon.string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Baekjoon_No_9012 {

	// 해당 문자열 정합성 검사
	public static String isClear(String str) {
		Stack<Character> s = new Stack<>();
		
		for(int j=0; j<str.length(); j++) {
			char now = str.charAt(j);
			
			// 여는 괄호는 스택에 넣고
			if(now == '[' || now == '{' || now == '(') {
				s.add(now);
			}
			// 닫는 괄호는 스택에서 확인한다.
			else {
				// 스택이 비어있지 않다면 
				if(!s.isEmpty()) {
					char c = s.pop();
					
					// 스택에서 꺼낸 여는 괄호가 지금 닫는 괄호랑 종류가 다르다면 정합성 X
					if( !((c == '[' && now == ']') || (c == '{' && now == '}') || (c == '(' && now == ')')) ) {
						return "NO";
					}
				}
				// 스택이 비어있으면 여는 괄호가 부족하다는 의미. 정합성 X 
				else {
					return "NO";
				}
			}
		}
		
		// 스택이 비어있으면 정합성 O
		if(s.isEmpty()) {
			return "YES";
		}
		
		// 스택이 다 비지 않았으면 닫는 괄호가 부족하다는 의미. 정합성 X
		else {
			return "NO";
		}
	}
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		
		
		for(int i=0; i<N; i++) {
			String str = br.readLine();
			
			sb.append(isClear(str) + "\n");
		}
		
		System.out.println(sb.toString());
	}

}

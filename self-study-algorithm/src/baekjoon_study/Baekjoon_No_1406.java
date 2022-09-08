package baekjoon_study;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;

// Link : 
// 1. LinkedList를 이용하니 시간초과 발생함.
// 2. 두 개의 Stack을 이용하면 간단히 해결 가능(커서 기준 왼쪽을 스택1, 기준 오른쪽을 스택2로 이용). 스택 연산 시간은 O(1)임
public class Baekjoon_No_1406 {
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String str = br.readLine();
		char[] charArr = str.toCharArray();
		int N = Integer.parseInt(br.readLine());
		
		Stack<Character> left = new Stack<>();			// 커서 기준 왼쪽 
		Stack<Character> right = new Stack<>();			// 커서 기준 오른쪽
		
		// 입력받은 알파벳들을 왼쪽 스택에 넣기
		for(char c : charArr) {
			left.push(c);
		}
		
		// 연산의 수만큼 반복
		for(int i=0; i<N; i++) {
			charArr = br.readLine().toCharArray();
			
			switch(charArr[0]) {
			// 커서를 왼쪽으로 옮김(맨 왼쪽이면 무시) : 왼쪽 스택에서 하나 빼서 오른쪽 스택으로 옮김 
			case 'L':
				if(!left.isEmpty()) {
					right.push(left.pop());
				}
				break;
				
			// 커서를 오른쪽으로 옮김(맨 오른쪽이면 무시) : 오른쪽 스택에서 하나 빼서 왼쪽 스택으로 옮김
			case 'D':
				if(!right.isEmpty()) {
					left.push(right.pop());
				}
				break;
				
			// 커서 왼쪽에 있는 문자 삭제(커서가 맨 왼쪽이면 무시) : 왼쪽 스택에서 하나 빼기
			case 'B':
				if(!left.isEmpty()) {
					left.pop();
				}
				break;
				
			// 문자를 커서 왼쪽에 추가	
			case 'P':
				left.push(charArr[2]);
				break;
			}
		}
		
		// 왼쪽 스택에 있던 알파벳들을 오른쪽 스택으로 옮김 (FILO 이용)
		while(!left.isEmpty()) {
			right.push(left.pop());
		}
		
		// 오른쪽 스택에서 알파벳을 하나씩 빼면서 출력
		while(!right.isEmpty()) {
			bw.write(right.pop());
		}
		
		bw.flush();
		bw.close(); 
	}

}

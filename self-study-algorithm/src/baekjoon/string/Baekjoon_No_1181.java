package baekjoon.string;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashSet;

public class Baekjoon_No_1181 {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		// 1. String 배열에 입력받은 문자열을 넣는다.
		String[] strArr = new String[N];
		for(int i=0; i<N; i++) {
			strArr[i] = br.readLine();
		}
		
		// 2. String 배열 내에 중복이 있을 수 있으니 HashSet으로 변경한다. (HashSet은 중복을 허용하지 않음)
		HashSet<String> hashSet = new HashSet<>(Arrays.asList(strArr));
		
		// 3. 위의 hashSet 안에 있는 문자열들을 CustomString 배열로 넣어준다. 
		CustomString[] customArr = new CustomString[hashSet.size()];
		int i=0;
		for(String s : hashSet) {
			customArr[i] = new CustomString(s);
			i++;
		}
		
		// 4. 정렬한다. 
		// 참고) CustomString에서 Comparable 인터페이스를 구현함
		Arrays.sort(customArr);
		
		// 5. 출력을 빠르게 하기위해 BufferedWriter 이용 (System.out.println 사용해도 무방함)
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		for(CustomString cs : customArr) {
			bw.write(cs.str+"\n");
		}
		bw.flush();
		bw.close();
	}
	
	// Comparable인터페이스를 구현하기 위해 따로 클래스를 정의함
	public static class CustomString implements Comparable<CustomString>{
		String str;
		
		public CustomString(String s) {
			this.str = s;
		}	
			
		// compareTo 메소드를 잘 정의하면 Arrays.sort() 메소드 사용 가능
		public int compareTo(CustomString other) {
			// 1) 문자열의 길이가 짧은 것이 우선순위임.
			if(str.length() < other.str.length()) {
				return -1;
			}
			else if(str.length() > other.str.length()) {
				return 1;
			}
			// 문자열의 길이가 같다면
			else {
				// 문자열의 앞부터 문자 하나씩 확인해서 사전순으로 앞에 있는 것이 우선순위를 갖도록.
				// 만약 같다면, 다음 문자 확인하기 위해 반복문 사용
				for(int i=0; i<str.length(); i++) {
					if((int) this.str.charAt(i) < (int) other.str.charAt(i)) {
						return -1;
					}
					else if((int) this.str.charAt(i) > (int) other.str.charAt(i)) {
						return 1;
					}
				}
				return 0;
			}
		}
		
		 
	}

}
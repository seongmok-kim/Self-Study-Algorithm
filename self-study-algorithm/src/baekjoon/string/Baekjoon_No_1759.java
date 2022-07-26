package baekjoon.string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

// Link : https://www.acmicpc.net/problem/1759
public class Baekjoon_No_1759 {

	// 3 <= L <= C <= 15
	public static int L, C;
	
	public static char[] charArr;
	
	public static ArrayList<String> resultList = new ArrayList<String>();
	
	// 암호 규칙!
	// 1. 암호는 서로 다른 L개의 알파벳 소문자들로 구성되며 최소 한 개의 모음(a, e, i, o, u)과 최소 두 개의 자음으로 구성
	// 2. 암호를 이루는 알파벳이 암호에서 증가하는 순서로 배열되었을 것 ( abc는 가능성이 있는 암호이지만 bac는 그렇지 않다. )
	
	// ** 새 보안 시스템에서 조교들이 암호로 사용했을 법한 문자의 종류는 C가지가 있다고 한다. 
	// 이 알파벳을 입수한 민식, 영식 형제는 조교들의 방에 침투하기 위해 암호를 추측해 보려고 한다. C개의 문자들이 모두 주어졌을 때, 가능성 있는 암호들을 모두 구하기 ** 
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		charArr = br.readLine().replaceAll(" ","").toCharArray();
		
		// 2번 암호 규칙을 만족시키기 위해 오름차순 정렬(암호를 이루는 알파벳이 암호에서 증가하는 순서로 배열)
		Arrays.sort(charArr);
		
		// DFS를 이용해서 조합 구하기
		DFS(-1, new char[L], 0);
		
		// sb를 이용해 출력
		StringBuilder sb = new StringBuilder();
		for(String s : resultList) {
			sb.append(s+"\n");
		}
		
		System.out.println(sb);
	}
	
	// DFS
	// int num : 현재 인덱스, 배열, 현재 반복 횟수 
	public static void DFS(int num, char[] arr, int count) {
		// 현재 반복 횟수가 L보다 같거나 크면 암호 정합성 검사
		if(count >= L) {
			String str = "";
			
			int vowelsNum  = 0;			// 모음의 갯수	
			int consonantsNum  = 0;		// 자음의 갯수
			
			for(char c : arr) {
				str += c;
				
				// 1번 암호 규칙을 만족시키기 위해 확인(암호는 서로 다른 L개의 알파벳 소문자들로 구성되며 최소 한 개의 모음(a, e, i, o, u)과 최소 두 개의 자음으로 구성)
				if(c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
					vowelsNum++;
				}
				else {
					consonantsNum++;
				}
			}
			
			// 암호 규칙을 만족할 때만 resultList에 추가
			if(vowelsNum>=1 && consonantsNum >= 2) {
				resultList.add(str);
			}
			
			return;
		}
		
		// 현재 위치의 인덱스보다 뒤에 있는 문자를 배열에다가 넣고 DFS 재귀 호출
		for(int i=num+1; i<C; i++) {
			char[] tempArr = arr.clone();
			
			char c = charArr[i];
			tempArr[count] = c;
			
			DFS(i, tempArr, count+1);
		}
	}
}
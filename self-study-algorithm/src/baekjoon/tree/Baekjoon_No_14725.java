package baekjoon.tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

// Link : https://www.acmicpc.net/problem/14725
public class Baekjoon_No_14725 {

	public static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		Pos root = new Pos();	// 트리 형태에서의 루트 
		
		int N = Integer.parseInt(br.readLine());
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int depth = Integer.parseInt(st.nextToken());
			Pos now = root;
			
			for(int j=0; j<depth; j++) {
				String s = st.nextToken();
					
				// 해당 s를 자식에 가지고 있지않으면 추가함
				if(!now.children.containsKey(s)) {		
					now.children.put(s, new Pos());
				}
				
				// 자식 시점으로 이동
				now = now.children.get(s);
			}
		}
		
		print(root, "");		// 출력 준비
		
		System.out.println(sb.toString());		// 출력
	}
	
	public static void print(Pos pos, String head) {
		Object[] keys = pos.children.keySet().toArray();	// 해당 Pos에서의 자식 키를 가져옴
		Arrays.sort(keys);		// 사전 순이라고 하였으니 정렬
		
		for(Object key : keys) {
			sb.append(head+key+"\n");		// StringBuilder에 출력 정보 넣어두고
			
			print(pos.children.get(key), head+"--");		// 자식 정보들도 출력할 수 있도록 재귀호출
		}
	}
	
	public static class Pos{
		HashMap<String, Pos> children = new HashMap<>();		// HashMap을 이용해서 트리 형태 구현
	}

}
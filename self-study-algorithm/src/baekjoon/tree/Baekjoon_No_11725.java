package baekjoon.tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// Link : https://www.acmicpc.net/problem/11725
public class Baekjoon_No_11725 {

	public static int N;		// N: 노드의 갯수
	public static ArrayList<ArrayList<Integer>> list;		// 인접 리스트 이용
	public static int[] parents;		// 각 노드의 부모 노드 배열
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		list = new ArrayList<>();
		parents = new int[N+1];
		
		for(int i=0; i<N+1; i++) {
			list.add(new ArrayList<Integer>());
		}
		
		for(int i=0; i<N-1; i++) {
			StringTokenizer st= new StringTokenizer(br.readLine());
			
			int num1 = Integer.parseInt(st.nextToken());
			int num2 = Integer.parseInt(st.nextToken());
			
			list.get(num1).add(num2);
			list.get(num2).add(num1);
		}

		// BFS 방식 사용
		Queue<Integer> q = new LinkedList<>();
		q.offer(1);		// 1은 항상 루트
		parents[1] = 1;
		
		// 큐가 빌 때까지 반복
		while(!q.isEmpty()) {
			int parent = q.poll();		
			
			for(int num : list.get(parent)) {
				
				// num의 부모가 미정으로 나오는 경우
				if(parents[num] == 0) {
					parents[num] = parent;		
					q.offer(num);
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		
		for(int i=2; i<=N; i++) {
			sb.append(parents[i]+"\n");
		}
		
		System.out.println(sb.toString());
	}
}
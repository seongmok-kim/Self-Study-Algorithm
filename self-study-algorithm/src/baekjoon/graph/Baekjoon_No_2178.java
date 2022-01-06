package baekjoon.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Node{
	private int num1;
	private int num2;
	
	public Node(int num1, int num2) {
		this.num1 = num1;
		this.num2 = num2;
	}
	
	public int getNum1() {
		return this.num1;
	}
	
	public int getNum2() {
		return this.num2;
	}
}

public class Baekjoon_No_2178 {

	// 그래프 변수 
	public static int[][] graph;
	
	// 그래프 크기 변수
	public static int N, M;
	
	// 상하좌우 확인에 사용할 배열
	public static int[] Nmove = {1,-1,0,0};
	public static int[] Mmove = {0,0,1,-1};
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String temp = br.readLine();
		StringTokenizer st = new StringTokenizer(temp);
		
		// 그래프 크기 입력받기
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		// 그래프 크기 할당
		graph = new int[N][M];
		
		// 그래프 입력받아 그래프안에 정수 넣기
		for(int i=0;i<N;i++) {
			String str = br.readLine();
			String[] strArr = str.split("");
			
			for(int j=0;j<M;j++) {
				graph[i][j] = Integer.parseInt(strArr[j]);
			}
		}
		
		// BFS 함수 호출
		BFS(0,0);
		
	}
	
	public static void BFS(int num1, int num2) {
		// Node 클래스를 이용해 두 개의 정수 처리
		Queue<Node> queue = new LinkedList<>();
		
		Node first = new Node(num1, num2);
		queue.offer(first);
		
		// 큐가 빌 때까지 반복
		while(!queue.isEmpty()) {
			
			// 큐에서 하나를 꺼낸다.
			Node node = queue.poll();
			
			// 상하좌우 체크
			for(int i=0;i<4;i++) {
				int tempN = node.getNum1()+Nmove[i];
				int tempM = node.getNum2()+Mmove[i];
				
				// 그래프 범위를 벗어나는 경우 무시
				if(tempN >= N || tempN < 0 || tempM >= M || tempM < 0) {
					continue;
				}else {
					// 가보지 않은 곳 : 1
					// 이동할 수 없는 곳 : 0
					// 가본 곳 : 1과 0이 아닌 숫자
					if(graph[tempN][tempM] == 1) {
						Node newNode = new Node(tempN,tempM);
						graph[tempN][tempM] = graph[node.getNum1()][node.getNum2()]+1;
						queue.offer(newNode);
					}
				}
			}
		}
		
		// 도착 위치의 정수 반환
		System.out.println(graph[N-1][M-1]);
		
	}
	
}

package baekjoon.graph;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;


public class Baekjoon_No_14502 {

	static int[][] graph;
	static int N,M;
	static int[] dnum1 = {-1,1,0,0};
	static int[] dnum2 = {0,0,1,-1};
	static int result = 0;
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt(); // 지도의 세로 크기
		M = sc.nextInt(); // 지도의 가로 크기
		
		graph = new int[N][M]; // 지도
		
		//지도 입력
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				graph[i][j] = sc.nextInt();				
			}
		}
		
		
		makeWall(0);
		
		System.out.println(result);
	}
	
	public static void makeWall(int count) {
		if(count == 3) {
			int tempResult = BFS();
			if(tempResult>result) {
				result = tempResult;
			}
			return;
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (graph[i][j] == 0) {
					// 벽 세움
					graph[i][j] = 1;
					makeWall(count+1);
					// 원상 복구
					graph[i][j] = 0;
				}
			}
		}
	}
	
	public static int BFS() {
		boolean[][] visited = new boolean[N][M];
		int[][] tempGraph = new int[N][M];
		Queue<Node> queue = new LinkedList<>();
		
		// 임시 그래프로 옮기기. (바이러스 퍼지는 걸 체크해야 하므로 원본 손상 방지를 위해)
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				tempGraph[i][j] = graph[i][j];
			}
		}
		
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(tempGraph[i][j] == 2) {
					
					Node first = new Node(i,j);
					queue.offer(first);
				}
			}
		}
		
		while(!queue.isEmpty()) {
			Node node = queue.poll();
			
			int temp1 = node.getNum1();
			int temp2 = node.getNum2();
			
			if(temp1>=N||temp2>=M||temp1<0||temp2<0) {
				continue;
			}else {
				
				if(tempGraph[temp1][temp2] != 1 && visited[temp1][temp2] == false) {
					visited[temp1][temp2] = true;
					tempGraph[temp1][temp2]=2;
					for(int k=0;k<4;k++) {
						int tempDnum1 = temp1+dnum1[k];
						int tempDnum2 = temp2+dnum2[k];
								
						Node temp = new Node(tempDnum1, tempDnum2);
						
						queue.offer(temp);
					}
				}
			}
		}
		
		int count = 0;
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(tempGraph[i][j] == 0)
					count++;
			}
		}
		
		return count;
	}
	

	static class Node {
		private int num1;
		private int num2;
		
		public Node(int num1, int num2) {
			this.num1=num1;
			this.num2=num2;
		}
		
		public int getNum1() {
			return num1;
		}
		
		public int getNum2() {
			return num2;
		}
		
		public void setNum1(int num) {
			this.num1 = num;
		}
		
		public void setNum2(int num) {
			this.num2 = num;
		}
	}

}

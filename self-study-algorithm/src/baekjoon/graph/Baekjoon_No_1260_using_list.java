package baekjoon.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;


public class Baekjoon_No_1260_using_list {

	// 그래프 변수(인접리스트 이용)
	public static ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
	public static int N,M,V;
	
	//  방문 체크 변수
	public static boolean[] visited;
	
	// DFS 함수
	public static void DFS(int num) {
		
		// 현재 노드 번호 방문 처리
		visited[num] = true;
		System.out.print(num + " ");
		
		for(int i=0; i<graph.get(num).size();i++) {
			int temp = graph.get(num).get(i);
			
			// temp 노드가 방문처리가 안 된 경우 재귀함수 DFS 호출
			if(visited[temp] == false) {
				DFS(temp);
			}
		}
	}
	
	// BFS 함수
	public static void BFS(int num) {
		
		// queue.offer() : 삽입하기
		// queue.poll() : 꺼내서 확인하기
		Queue<Integer> queue = new LinkedList<>();
		
		// 현재 노드 queue에 삽입 후 방문 처리
		queue.offer(num);
		visited[num] = true;
		
		System.out.print(num + " ");
		
		// queue가 빌 때까지 반복
		while(!queue.isEmpty()) {
			// 큐에서 하나를 꺼낸 후
			int number = queue.poll();
			
			// 꺼낸 노드의 번호와 인접한 노드들 체크
			for(int i=0;i<graph.get(number).size();i++) {
				int temp_num = graph.get(number).get(i);
				
				// 방문하지 않은 인접한 노드가 있다면 
				if(visited[temp_num] == false) {
					// 해당 노드를 방문 체크 후
					visited[temp_num] = true;
					// 큐에 삽입
					queue.offer(temp_num);
					System.out.print(temp_num +" ");
				}
			}
		}
		
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		V = sc.nextInt();
		sc.nextLine();
		
		// 변수 크기 선언
		visited = new boolean[N+1];
		
		// 노드의 수 + 1 만큼 graph 변수에 추가
		// 왜 1만큼 더했을까? 문제에서 0은 사용하지 않고 1부터 시작하기 때문이다.
		// 총 노드가 4인 경우 +1을 해주지 않는다면 0,1,2,3 이 된다. 
		// 4번 노드의 인접한 노드를 가져올 떄 graph.get(4)를 하는데 이 때, 오류 발생
		// 이 방법 말고 다른 부분에서 -1 처리 하는 부분이 있지만, 필자는 이런 방식으로 처리했다.
		for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<Integer>());
        }
		
		for(int i=0;i<M;i++) {
			int tempN1 = sc.nextInt();
			int tempN2 = sc.nextInt();
			sc.nextLine();
			
			// 일방적인 방향이 있는 것이 아니고 간선이기 때문에 두 노드에 둘 다 추가한다.
			graph.get(tempN1).add(tempN2);
			graph.get(tempN2).add(tempN1);
		}
		
		// 같은 깊이의 노드의 경우 오름차순으로 확인하기 때문에 정렬을 해준다.
		for(int i=0;i<graph.size();i++) {
			Collections.sort(graph.get(i));
		}
			
		DFS(V);
		visited = new boolean[N+1];
		System.out.println();
		BFS(V);
		
	}

}

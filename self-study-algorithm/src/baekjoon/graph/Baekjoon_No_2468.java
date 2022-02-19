package baekjoon.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baekjoon_No_2468 {

   // 그래프의 행, 열의 높이
   public static int N;
   
   // 그래프
   public static int[][] graph;
   
   // 상하좌우 이동
   public static int[] amove = {1,-1,0,0};
   public static int[] bmove = {0,0,1,-1};
   
   // 결과값
   public static int result = 0;
   
   // BFS 탐색. height : 물의 높이
   public static int BFS(int height) {
      boolean[][] visited = new boolean[N][N];
      
      // 안전지대의 갯수
      int count = 0;
      
      for(int i=0; i<N; i++) {
         for(int j=0; j<N; j++) {
            Queue<Node> q = new LinkedList<>();
            
            // 이미 방문한 위치거나 물에 잠긴 곳은 제외
            if(visited[i][j] == true || graph[i][j]-height <= 0){
               continue;
            }
            
            // 현재 위치를 큐에 넣기
            q.offer(new Node(i,j));
            
            // 안전지대 +1
            count+=1;
            
            // 큐가 빌 때까지 반복
            while(!q.isEmpty()) {
               
               // 큐에서 하나 꺼내고
               Node node = q.poll();
               
               int na = node.a;
               int nb = node.b;
               
               // 큐에서 꺼낸 노드가 방문했던 곳이라면 무시
               if(visited[na][nb] == true) {
                  continue;
               }
               
               // 큐에서 꺼낸 노드를 방문 처리
               visited[na][nb] = true;
               
               // 상하좌우 확인
               for(int k=0; k<4; k++) {
                  int tempa = na + amove[k];
                  int tempb = nb + bmove[k];
                  
                  // 범위를 벗어나는 경우 무시
                  if(tempa >= N || tempa < 0 || tempb >= N || tempb < 0) {
                     continue;
                  }
                  
                  // 범위 이내이며, 방문한 적이 없고, 물에 잠기지 않은 곳이면 큐에 넣기
                  if(visited[tempa][tempb] == false && graph[tempa][tempb]-height > 0) {
                     q.offer(new Node(tempa,tempb));
                  }
                  
               }
            }
         }
      }
      return count;
      
   }
   
   // 노드 클래스 선언
   public static class Node{
      int a;
      int b;
      
      public Node(int a, int b) {
         this.a=a;
         this.b=b;
      }
   }
   
   public static void main(String[] args) throws IOException {
      // TODO Auto-generated method stub
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      
      N = Integer.parseInt(br.readLine());
      
      graph = new int[N][N];
      
      // 가장 높은 지역의 높이
      int max = 0;
      
      // 그래프 설정
      for(int i=0; i<N; i++) {
         StringTokenizer st = new StringTokenizer(br.readLine());
         for(int j=0; j<N; j++) {
            int height = Integer.parseInt(st.nextToken());
            graph[i][j] = height;
            
            // 가장 높은 지역의 높이 저장
            max = Math.max(max, height);
         }
      }
      
      // 물의 높이를 0부터 가장 높은 지역의 높이 -1까지만 확인
      for(int h=0; h<max; h++) {
         // 안전지대 갯수가 높은 걸로 갱신
         result = Math.max(result, BFS(h));
      }
      
      // 결과 출력
      System.out.println(result);
      
   }

}
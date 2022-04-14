package baekjoon.implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon_No_14500 {
  // N : 높이, M : 넓이
  public static int N, M;
  
  public static int[][] map;
  public static boolean[][] visited;
 
  public static int[] ymove = {-1,0,1,0};
  public static int[] xmove = {0,-1,0,1};
  
  public static int result = 0;
  
  // ㅗ모양을 제외한 나머지 모양은 DFS로 만들 수 있다.
  public static void DFS(int y, int x, int depth, int sum){
    // 4칸이면 결과 확인
    if(depth == 4){
      result = Math.max(result, sum);
      return;
    }
    
    // 상하좌우 체크
    for(int i=0; i<4; i++){
      int tempY = y + ymove[i];
      int tempX = x + xmove[i];
      
      // 맵 범위 이내이고
      if(tempY >= 0 && tempY < N && tempX >= 0 && tempX < M){
        // 방문하지 않은 곳이라면
        if(visited[tempY][tempX] == false){
          // 방문 체크
          visited[tempY][tempX] = true;
          // 방문
          DFS(tempY, tempX, depth+1, (sum+map[tempY][tempX]));
          // 방문 체크 취소(백트래킹)
          visited[tempY][tempX] = false;
        }
      }
    }
  }
  
  // ㅗ모양은 DFS로 구현이 불가능하므로 함수를 만들어줌
  public static void SpecialCase(int y, int x){
    int depth = 0;
    int sum = map[y][x];
    int min = Integer.MAX_VALUE;

    // 현재 위치가 가운데라고 생각하고 상하좌우 확인
    for(int i=0; i<4; i++){
      int tempY = y + ymove[i];
      int tempX = x + xmove[i];
      
      // 범위 이내일 때
      if(tempY >= 0 && tempY < N && tempX >= 0 && tempX < M){
        // sum에 해당 위치의 값을 추가
        sum+=map[tempY][tempX];
        // 최소값 갱신
        min = Math.min(min, map[tempY][tempX]);
        
        // depth 추가
        depth++;
      }
    }
    
    // depth 가 3보다 작다는 것은 ㅣ - 과 같은 모양들이므로 무시
    if(depth < 3){
      return;
    }
    else{
      
      // 4는 십자가모양 十이므로 여기서 제일 작은 값을 빼주면 ㅓ ㅏ ㅜ ㅗ 모양 중 하나가 된다.
      if(depth == 4){
        sum -= min;
      }
      // 결과값 갱신
      result = Math.max(result, sum);
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    StringTokenizer st = new StringTokenizer(br.readLine());
    
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    
    map = new int[N][M];
    visited = new boolean[N][M];
    
    for(int i=0; i<N; i++){
      st = new StringTokenizer(br.readLine());
      for(int j=0; j<M; j++){
        map[i][j] = Integer.parseInt(st.nextToken());
      }
    }
    
    for(int i=0; i<N; i++){
      for(int j=0; j<M; j++){
        // DFS를 이용해 테르리미노를 만들 수 있음
        DFS(i, j, 0, 0);
        
        // ㅗ 모양은 불가능하므로 따로 함수를 만들어서 호출
        SpecialCase(i,j);
        
      }
    }
    
    // 결과 출력
    System.out.println(result);
  } 
}
package baekjoon.implement;

import java.util.*;

public class Baekjoon_No_5212 {
  
  public static int R;
  public static int C;
  
  // 원본 지도
  public static char [][] map;
  
  // 50년이 지난 지도
  public static char [][] tempMap;

  // 상하좌우 설정
  public static final int[] dx = {0, 0, 1, -1};
  public static final int[] dy = {1, -1, 0, 0};
  
  public static void main(String[] args) {
    
    Scanner sc = new Scanner(System.in);
    
    int R = sc.nextInt();
    int C = sc.nextInt();
    
    map = new char[R][C];
    tempMap = new char[R][C];
    sc.nextLine();
    
    for(int i = 0 ; i < map.length ; i++) {
      String s = sc.nextLine();
      map[i] = s.toCharArray();
      tempMap[i] = s.toCharArray();
    }

    sc.close();
    
    // X주위 상하좌우에 바다가 3개 이상 있다면 녹임.
    for(int i = 0 ; i < map.length ; i++) {
      for(int j = 0 ; j < map[i].length ; j++) {
        if(map[i][j] == 'X') {
          
          int count = 0;
          
          for(int k = 0 ; k < dx.length ; k++) {
            int nx = i + dx[k];
            int ny = j + dy[k];
            
            if((nx>=0 && nx < R && ny >=0 && ny < C )){
              if(map[nx][ny] == 'X') {
                continue;
              }
            }
            count++;
          }
          
          if(count >= 3) {
            tempMap[i][j] = '.';
          }
        }
      }
    }
    
    // 출력할 범위 구하기
    int top = -1;
    int left = -1;
    int down = -1;
    int right = -1;
    
    int height = tempMap.length;
    int width = tempMap[0].length;
    
    // 위
    for(int i=0; i<height; i++){
      
      boolean check = false;
      for(int j=0; j<width; j++){
        if(tempMap[i][j] == 'X'){
          top = i;
          check=true;
          break;
        }
      }
      if(check){
        break;
      }
    }
    
    // 왼쪽
    for(int i=0; i<width; i++){
      boolean check = false;
      for(int j=0; j<height; j++){
        if(tempMap[j][i] == 'X'){
          left = i;
          check=true;
          break;
        }
      }
      if(check){
        break;
      }
    }
    
    // 아래
    for(int i=height-1; i>=0; i--){
      boolean check = false;
      for(int j=0; j<width; j++){
        if(tempMap[i][j] == 'X'){
          down = i;
          check=true;
          break;
        }
      }
      if(check){
        break;
      }
    }
    
    // 오른쪽
    for(int i=width-1; i>=0; i--){
      boolean check = false;
      for(int j=0; j<height; j++){
        if(tempMap[j][i] == 'X'){
          right = i;
          check=true;
          break;
        }
      }
      if(check){
        break;
      }
    }
    
    // 위에서 정한 범위 내에서 출력
    StringBuilder sb = new StringBuilder();
    
    int i = top;
    while(i <= down){
      int j = left;
      while(j<=right){
        sb.append(tempMap[i][j]);
        j++;
      }
      sb.append("\n");
      i++;
    }  
    
    System.out.println(sb.toString());
  }
}
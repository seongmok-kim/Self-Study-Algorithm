package programmers;

import java.io.IOException;

public class install_pillar_and_beam {
	
	// 기둥과 보를 따로 관리.
	// 기둥
    public static boolean[][] pillars;
    
    // 보
    public static boolean[][] beams;
    
    // 건설한 구조물의 수
    public static int count = 0;
    
    // 맵의 길이
    public static int length;
    
    // 해당 위치의 보 정합성 체크
    public static boolean checkBeam(int x, int y) {
    	if(y-1 >= 0 && y-1 < length) {
			// 한쪽 끝 부분이 기둥 위에 있을 경우 가능
			if(pillars[x][y-1] == true) {
				return true;
			}
			// 한쪽 끝 부분이 기둥 위에 있을 경우 가능
			if(x+1 >= 0 && x+1 < length) {
				if(pillars[x+1][y-1] == true) {
					return true;
				}
			}
			
			// 양쪽 끝 부분이 다른 보와 동시에 연결되어 있는 경우 가능
			if(x-1 >= 0 && x-1 < length && x+1 >= 0 && x+1 < length) {
				if(beams[x-1][y] == true && beams[x+1][y] == true) {
					return true;
				}
			}
		}
    	
    	// 위의 경우가 아니면 정합성 결과 FALSE
    	return false;
    }
    
    // 해당 기둥 정합성 체크
    public static boolean checkPillar(int x, int y) {
    	// 바닥에 설치 시
		if(y==0) {
			return true;
		}
		
		// 보의 한쪽 끝 부분 위에 설치할 경우 가능
		if(beams[x][y] == true) {
			return true;
		}
		
		// 보의 한쪽 끝 부분 위에 설치할 경우 가능
		if(x-1 >= 0 && x-1 < length) {
			if(beams[x-1][y] == true) {
				return true;
			}
		}
		
		// 기둥 위에 설치할 경우 가능
		if(y-1 >= 0 && y-1 < length) {
			if(pillars[x][y-1] == true) {
				return true;
			}
		}
		
		// 위의 경우가 아니면 정합성 결과 FALSE
		return false;
    }
    
    // 기둥 작업(설치 or 삭제)
    public static void workPillar(int x, int y, int a, int b) {
    	// 설치
    	if(b==1) {
    		// 해당 자리에 설치할 수 있으면 설치
    		if(checkPillar(x,y) == true) {
    			pillars[x][y] = true;
    			count++;
    		}
    	}
    	// 삭제
    	else {
    		// 우선 해당 위치의 구조물을 삭제 및 건축물 갯수 -1 처리 후
    		pillars[x][y] = false;
    		count--;
    		
    		// 남은 건축물들 정합성 테스트 해서 정합성 실패일 경우 원복.
    		for(int i=0; i<length; i++) {
    			for(int j=0; j<length; j++) {
    				
    				// 해당 위치에 기둥이 있을 때
    				if(pillars[i][j] == true) {
    					// 해당 기둥이 조건을 만족하는 지 체크. 조건 만족 안하는 경우 
    					if(checkPillar(i,j) == false) {
    						// 원복
    						pillars[x][y] = true;
    			    		count++;
    			    		return;
    					}
    				}
    				
    				// 해당 위치에 보가 있을 때
    				if(beams[i][j] == true) {
    					// 해당 보가 조건을 만족하는 지 체크. 조건 만족 안하는 경우 
    					if(checkBeam(i,j) == false) {
    						// 원복
    						pillars[x][y] = true;
    			    		count++;
    			    		return;
    					}
    				}
    			}
    		}
    	}
    }

    // 보 작업(설치 or 삭제)
    public static void workBeam(int x, int y, int a, int b) {
    	// 설치 
    	if(b == 1) {
    		// 해당 자리에 설치할 수 있으면 설치
    		if(checkBeam(x,y) == true) {
    			beams[x][y] = true;
    			count++;
    		}
    	}
    	// 삭제
    	else {
    		// 우선 해당 위치의 구조물을 삭제 및 건축물 갯수 -1 처리 후
    		beams[x][y] = false;
    		count--;
    		
    		// 남은 건축물들 정합성 테스트 해서 정합성 실패일 경우 원복.
    		for(int i=0; i<length; i++) {
    			for(int j=0; j<length; j++) {
    				
    				// 해당 위치에 기둥이 있을 때
    				if(pillars[i][j] == true) {
    					// 해당 기둥이 조건을 만족하는 지 체크. 조건 만족 안하는 경우 
    					if(checkPillar(i,j) == false) {
    						// 원복
    						beams[x][y] = true;
    			    		count++;
    			    		return;
    					}
    				}
    				
    				// 해당 위치에 보가 있을 때
    				if(beams[i][j] == true) {
    					// 해당 보가 조건을 만족하는 지 체크. 조건 만족 안하는 경우 
    					if(checkBeam(i,j) == false) {
    						// 원복
    						beams[x][y] = true;
    			    		count++;
    			    		return;
    					}
    				}
    			}
    		}
    	}
    }
    
    public static int[][] solution(int n, int[][] build_frame) {
        // build_frame :  [x, y, a, b]
        // x : 가로 좌표, y : 세로 좌표
        // a - 0 : 기둥, 1 : 보
        // b - 0 : 삭제, 1 : 설치
        
    	length = n+1;
    	
    	pillars = new boolean[length][length];
    	beams = new boolean[length][length];
    	
    	
        for(int i=0; i<build_frame.length; i++){
            int[] curPos = build_frame[i];
            
            // 가로 좌표
            int x = curPos[0];
            
            // 세로 좌표
            int y = curPos[1];
            
            // 0 : 기둥, 1 : 보
            int a = curPos[2];
            
            // 0 : 삭제, 1 : 설치
            int b = curPos[3];
            
            // 교차점 좌표를 기준으로, 보는 오른쪽 / 기둥은 위쪽 방향으로 설치 or 삭제
            
            if(a==0) {
            	workPillar(x, y, a, b);
            }
            else {
            	workBeam(x, y, a, b);
            }
        }
        
        // 건축물의 수만큼 크기 할당.
        // answer[] : [x,y,a] 형식
        int[][] answer = new int[count][3];
        
        int index = 0;
        
        // 데이터 정렬 조건 : x오름차순, y오름차순.
        // 한 좌표에 기둥과 보 둘 다 있을 경우 기둥이 먼저 나오도록 정렬
        for(int i=0; i<length; i++) {
        	for(int j=0; j<length; j++) {
        		if(pillars[i][j] == true) {
        			answer[index] = new int[] {i,j,0};
        			index++;
        		}
        		if(beams[i][j] == true) {
        			answer[index] = new int[] {i,j,1};
        			index++;
        		}
        	}
        }
        
        // answer : [x,y,a] 형식
        return answer; 
    }
	
	public static void main(String[] args) throws IOException {
		int n = 5;
		//int[][] arr = {{1,0,0,1},{1,1,1,1},{2,1,0,1},{2,2,1,1},{5,0,0,1},{5,1,0,1},{4,2,1,1},{3,2,1,1}};
		int[][] arr = {{0,0,0,1},{2,0,0,1},{4,0,0,1},{0,1,1,1},{1,1,1,1},{2,1,1,1},{3,1,1,1},{2,0,0,0},{1,1,1,0},{2,2,0,1}};
		
		solution(n,arr);
	}
}
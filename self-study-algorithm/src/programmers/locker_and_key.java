package programmers;

public class locker_and_key {

	// 2차원 배열 90도 회전
	public static int[][] rotate(int[][] arr) {
	    int n = arr.length;
	    int m = arr[0].length;
	    int[][] rotated = new int[m][n];

	    for (int i = 0; i < rotated.length; i++) {
	        for (int j = 0; j < rotated[i].length; j++) {
	            rotated[i][j] = arr[n-1-j][i];
	        }
	    }

	    return rotated;
	    
    }
    public static boolean solution(int[][] key, int[][] lock) {
        int mapSize = lock.length+(2*(key.length-1));
        
        // 열쇠가 상하좌우로 들어갈 수 있게 여유공간을 만들어주기(저장용 변수)
        int[][] map = new int[mapSize][mapSize];
        
        // map의 가운데에 자물쇠 모양 넣기
        for(int i=0; i<lock.length; i++) {
        	for(int j=0; j<lock.length; j++) {
        		map[key.length-1+i][key.length-1+j] = lock[i][j];
        	}
        }
        
        // 회전한 열쇠
        int[][] rotatedKey = key;
        
        // 임시 맵(실제로 데이터를 넣다뺴는 작업을 진행할 변수)
        int[][] tempMap = new int[mapSize][mapSize];
        
        // 열쇠를 총 4번 회전
        for(int cnt=0; cnt<4; cnt++) {
        	
        	// 열쇠 90도 회전
        	rotatedKey = rotate(rotatedKey);
        	
            // tempMap의 0,0부터 key의 0,0 맞춰보기
            for(int i=0; i<tempMap.length-key.length+1; i++) {
            	for(int j=0; j<tempMap.length-key.length+1; j++) {
            		
            		// tempMap 초기화
                    for(int a=0; a<tempMap.length; a++) {
                    	for(int b=0; b<tempMap.length; b++) {
                    		tempMap[a][b] = map[a][b];
                    	}
                    }
                    
            		boolean check = true;
            		// key를 tempMap에 합치기
            		for(int k=0; k<rotatedKey.length; k++) {
            			for(int m=0; m<rotatedKey.length; m++) {
            				int sum = tempMap[i+k][j+m] + rotatedKey[k][m];
            				tempMap[i+k][j+m] = sum;
            			}
            		} 
            		
            		// tempMap 안에 있는 자물쇠영역이 모두 1이어야함.
            		for(int k=0; k<lock.length; k++) {
            			for(int m=0; m<lock.length; m++) {
            				
            				// 자물쇠부분이 1이 아니라면 잘못된 것.
            				if(tempMap[key.length-1+k][key.length-1+m] != 1) {
            					check=false;
            					break;
            				}
            			}
            			if(check == false) {
            				break;
            			}
            		}
            		
            		// 자물쇠부분이 모두 1이면 열쇠가 작동하는 것이므로 true 리턴
            		if(check) {
            			return true;
            		}
            	}
            }
        }
        
        // 위의 모든 경우의 수를 해봤는데 안되면 false반환
        return false;
    }
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] KEY = {{0, 0, 0}, {1, 0, 0}, {0, 1, 1}};
		int[][] LOCK = {{1, 1, 1}, {1, 1, 0}, {1, 0, 1}};
		
		
		boolean result = solution(KEY, LOCK);
		
		System.out.println(result);
	}

}

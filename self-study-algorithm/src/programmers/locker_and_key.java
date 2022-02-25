package programmers;

public class locker_and_key {

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
        
        int[][] rotatedKey = key;
        
        // 임시 맵(실제로 데이터를 넣다뺴는 작업을 진행할 변수)
        int[][] tempMap = new int[mapSize][mapSize];
        
        // 열쇠를 4번 회전
        for(int cnt=0; cnt<4; cnt++) {
        	
        	// 회전
        	rotatedKey = rotate(rotatedKey);
        	
            // 자물쇠에 열쇠 꽂기
            for(int i=0; i<tempMap.length-key.length+1; i++) {
            	for(int j=0; j<tempMap.length-key.length+1; j++) {
            		
            		// tempMap초기화
                    for(int a=0; a<tempMap.length; a++) {
                    	for(int b=0; b<tempMap.length; b++) {
                    		tempMap[a][b] = map[a][b];
                    	}
                    }
                    
            		boolean check = true;
            		for(int k=0; k<key.length; k++) {
            			for(int m=0; m<key.length; m++) {
            				int sum = tempMap[i+k][j+m] + rotatedKey[k][m];
            				tempMap[i+k][j+m] = sum;
            			}
            		} 
            		
            		/*
                    for(int a=0; a<tempMap.length; a++) {
                    	System.out.println();
                    	for(int b=0; b<tempMap.length; b++) {
                    		System.out.print(tempMap[a][b] + " ");
                    	}
                    }
                    System.out.println();
                    */
            		
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
            		
            		// 자물쇠부분이 모두 1
            		if(check) {
            			return true;
            		}
            	}
            }
        }
        
        return false;
    }
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] KEY = {{0, 0, 0}, {1, 0, 0}, {0, 1, 1}};
		int[][] LOCK = {{1, 1, 1}, {1, 1, 0}, {1, 0, 1}};
		
		
		boolean result = solution(KEY, LOCK);
		
		System.out.println();
		System.out.println(result);
	}

}

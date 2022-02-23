package programmers;

public class compress_String {
	class Solution {
	    public int solution(String s) {
	        String[] strArr = s.split("");
	        
	        int min = (int)1e9;
	        
	        // 문자열을 묶을 갯수를 1부터 점차 증가(문자열의 전체 개수까지)
	        for(int num=1; num<=strArr.length; num++){
	            String str = "";
	            
	            String now = "";
	            int cnt = 0;
	            
	            // 문자열 묶을 갯수만큼 문자열을 가져옴
	            for(int k=0; k<num; k++){
	                now += strArr[k];
	            }  
	            
	            int rest = 0;
	            int j=0;
	            while(true) {
	                // 뒤에 남은 문자의 갯수가 num 보다 작으면 탈출
	                if(strArr.length - j < num) {
	                    
	                    // 남은 문자의 개수 기록해두고
	                	rest = strArr.length - j;
	                    
	                    // 기존에 기록해오던 데이터 str에 넣고
	                	if(cnt == 1){
	                        str += now;
	                    }
	                    else{
	                        str += (cnt+now);
	                    }
	                    
	                    // 탈출
	                	break;
	                }
	                
	                // num 만큼 임시 문자열에 넣기
	                String temp = ""; 
	                // 현재 위치부터 num뒤로의 문자열을 가져오기
	                for(int k=0; k<num; k++){
	                    temp += strArr[j+k];
	                }  
	                
	                // 같으면 카운트
	                if(now.equals(temp)){
	                    cnt++;
	                }
	                
	                // 다르면 str 문자열에 넣기
	                else{
	                	// 카운트가 1이면 숫자 안넣음
	                    if(cnt == 1){
	                        str += now;
	                    }
	                    
	                    // 카운트가 1이 아니면 숫자 넣음
	                    else{
	                        str += (cnt+now);
	                    }
	                    
	                    // now 문자열 갱신
	                    now = temp;
	                    
	                    // 카운트 1로 초기화
	                    cnt = 1;
	                }
	                // j 증가
	                j+=num;
	            }
	            
	            // 작은 것으로 결과값 갱신
	            min = Math.min(min, str.length()+rest);
	        }
	        
	        //결과 출력
	        return min;
	    }
	}
}

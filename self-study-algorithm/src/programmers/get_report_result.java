package programmers;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class get_report_result {

	public static int[] solution(String[] id_list, String[] report, int k) {
		
		// 각 사용자가 누구를 신고했는 지 저장해두는 리스트
        ArrayList<ArrayList<Integer>> reportList = new ArrayList<>();
        
        // 각 사용자가 받은 신고의 횟수를 모아두는 배열
        int[] reportCountArr = new int[id_list.length];
		
        // 각 사용자의 수만큼 리스트 할당
        for(int i=0; i<id_list.length; i++) {
        	reportList.add(new ArrayList<Integer>());
        }
        
        // reportList에 저장.
        for(int i=0; i<report.length; i++) {
        	StringTokenizer st = new StringTokenizer(report[i]);
        	
        	String from = st.nextToken();
        	String to = st.nextToken();
        	
        	int fromIdx = 0;
        	for(; fromIdx<id_list.length; fromIdx++) {
        		if(from.equals(id_list[fromIdx])){
        			break;
        		}
        	}
        	
        	int toIdx = 0;
    		for(; toIdx<id_list.length; toIdx++) {
    			if(to.equals(id_list[toIdx])) {
    				break;
    			}
    		}
        	
        	// 같은 사람이 같은 대상에게 이전에 신고했었는지 확인
        	boolean check = false;
        	for(int j=0; j<reportList.get(fromIdx).size(); j++) {
        		int temp = reportList.get(fromIdx).get(j);
        		
        		if(toIdx == temp) {
        			check = true;
        			break;
        		}
        	}
        	
        	// 같은 사람이 같은 대상에게 이전에 신고했었다면 무시.
        	if(check) {
        		continue;
        	}
        	else {
        		
        		reportList.get(fromIdx).add(toIdx);
        		
        		reportCountArr[toIdx]++;
        	}
        }
        
        int[] answer = new int[id_list.length];
        
        for(int i=0; i<reportCountArr.length; i++) {
        	
        	// 신고 대상자 일 경우
        	if(reportCountArr[i] >= k) {
        		
        		for(int j=0; j<reportList.size(); j++) {
        			// 자기 자신에게 신고하는 일은 없으니 해당 경우는 건너뛰기
        			if(j==i) {
        				continue;
        			}
        			
        			for(int m=0; m<reportList.get(j).size(); m++) {
        				int idx = reportList.get(j).get(m);
        				
        				if(idx == i) {
        					answer[j]++;
        					break;
        				}
        			}
        		}
        	}
        }
        
        
        return answer;
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String[] list1 = {"muzi", "frodo", "apeach", "neo"};
		String[] list2 = {"muzi frodo","apeach frodo","frodo neo","muzi neo","apeach muzi"};
		int k= 2;
		
		int[] result = solution(list1, list2, k);
		for(int i=0; i<result.length; i++) {
			System.out.println(result[i]);
		}
		
	}

}

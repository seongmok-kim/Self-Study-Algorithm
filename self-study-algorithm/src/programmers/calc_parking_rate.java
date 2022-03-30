package programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class calc_parking_rate {

	public static int[] solution(int[] fees, String[] records) {
		int defaultTime = fees[0];
		int defaultRate = fees[1];
		int extraTime = fees[2];
		int extraRate = fees[3];

		ArrayList<String> carList = new ArrayList<>();
		HashMap<String, String> inTable = new HashMap<>();
		
		HashMap<String, Integer> timeTable = new HashMap<>();
		
		// 기록을 각 테이블로 이동
		for(int i=0; i< records.length; i++) {
			StringTokenizer st = new StringTokenizer(records[i]);
			
			String time = st.nextToken();
			String car_number = st.nextToken();
			String type = st.nextToken();

			if(!carList.contains(car_number)) {
				carList.add(car_number);
			}
			
			// 입차일 경우 입차 테이블에 넣기
			if(type.equals("IN")) {
				inTable.put(car_number, time);
			}
			// 출차일 경우 출차 테이블에 넣기
			else {
				String[] inTimeArr = inTable.get(car_number).split(":");
				String[] outTimeArr = time.split(":");
				
				int useTimeMinute = ((Integer.parseInt(outTimeArr[0]) - Integer.parseInt(inTimeArr[0])) * 60) + (Integer.parseInt(outTimeArr[1]) - Integer.parseInt(inTimeArr[1]));
				
				if(timeTable.get(car_number) == null) {
					timeTable.put(car_number, useTimeMinute);
				}
				else {
					timeTable.put(car_number, timeTable.get(car_number) + useTimeMinute);
				}
				
				inTable.remove(car_number);
			}
		}
		
		String[] temp_car_list_arr = new String[carList.size()];
		for(int i=0; i<carList.size(); i++) {
			String car_number = carList.get(i);
			
			if(inTable.get(car_number) != null) {
				String[] inTimeArr = inTable.get(car_number).split(":");
				String[] outTimeArr = "23:59".split(":");
				
				int useTimeMinute = ((Integer.parseInt(outTimeArr[0]) - Integer.parseInt(inTimeArr[0])) * 60) + (Integer.parseInt(outTimeArr[1]) - Integer.parseInt(inTimeArr[1]));
				
				if(timeTable.get(car_number) == null) {
					timeTable.put(car_number, useTimeMinute);
				}
				else {
					timeTable.put(car_number, timeTable.get(car_number) + useTimeMinute);
				}
				
				inTable.remove(car_number);
			}
			
			temp_car_list_arr[i] = car_number;
		}
		
		int[] carArr = new int[temp_car_list_arr.length];
		
		// int형 배열로 변환
		for(int i=0; i<temp_car_list_arr.length; i++) {
			carArr[i] = Integer.parseInt(temp_car_list_arr[i]);
		}
		
		// 차량번호 오름차순 정렬
		Arrays.sort(carArr);
		
		int[] answer = new int[carArr.length];
		
		for(int i=0; i<temp_car_list_arr.length; i++) {
			String curCarNo = temp_car_list_arr[i];
			
			int idx=0;
			for(; idx<carArr.length; idx++) {
				if(carArr[idx] == Integer.parseInt(curCarNo)) {
					break;
				}
			}
			
			int useTimeMinute = timeTable.get(curCarNo);
			System.out.println(curCarNo + " : " + useTimeMinute);
			
			int totalRate = defaultRate;
			int restTime = ((useTimeMinute - defaultTime) / extraTime);
			if(((useTimeMinute - defaultTime) % extraTime) != 0) {
				restTime ++;
			}
			if(restTime > 0) {
				totalRate += (restTime * extraRate);
			}
			answer[idx] = totalRate;
			System.out.println(totalRate);
			
		}
		
        return answer;
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//int[] fees = {180,5000,10,600};
		//String[] records = {"05:34 5961 IN", "06:00 0000 IN", "06:34 0000 OUT", "07:59 5961 OUT", "07:59 0148 IN", "18:59 0000 IN", "19:09 0148 OUT", "22:59 5961 IN", "23:00 5961 OUT"};
		int[] fees = {1, 10, 1, 11};
		String[] records = {"00:00 1234 IN", "00:02 1234 OUT"};
		solution(fees, records);
		
	}

}
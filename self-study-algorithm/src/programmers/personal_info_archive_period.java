package programmers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class personal_info_archive_period {

    public static void main(String[] args) throws IOException{
        String today = "2022.05.19";
        String[] terms = {"A 6", "B 12", "C 3"};
        String[] privacies = {"2021.05.02 A", "2021.07.01 B", "2022.02.19 C", "2022.02.20 C"};

        solution(today, terms, privacies);
    }

    public static int[] solution(String today, String[] terms, String[] privacies) {
        HashMap<String, Integer> ruleMap = new HashMap<>();
        ArrayList<Integer> answerList = new ArrayList<>();

        for(String s : terms) {
            StringTokenizer st = new StringTokenizer(s);
            String type = st.nextToken();
            int period = Integer.parseInt(st.nextToken());

            ruleMap.put(type, period);
        }

        int todayInt = Integer.parseInt(today.replaceAll("\\.", ""));

        int N = 1;
        for(String s : privacies) {
            StringTokenizer st = new StringTokenizer(s);
            String date = st.nextToken();
            String type = st.nextToken();

            String[] dateArr = date.split("\\.");
            int period = ruleMap.get(type);

            String tempDay = (Integer.parseInt(dateArr[2]) - 1)+"";

            if(tempDay.equals("0")) {
                tempDay = "28";		// 모든 달은 28일까지 있다고 가정한다. (문제 조건에 명시)
                period -= 1;
            }

            // period는 1~100사이의 수가 입력됨
            int periodYear = (period / 12);
            int periodMonth = (period % 12);

            int tempMonth = periodMonth + Integer.parseInt(dateArr[1]);
            if(tempMonth > 12) {
                tempMonth -= 12;
                periodYear += 1;
            }

            int tempYear = (Integer.parseInt(dateArr[0]) + periodYear);

            String tempYearString = String.valueOf(tempYear);
            String tempMonthString = String.valueOf(tempMonth);
            String tempDayString = String.valueOf(tempDay);

            if(tempDayString.length() == 1)
                tempDayString = "0" + tempDayString;

            if(tempMonthString.length() == 1)
                tempMonthString = "0" + tempMonthString;

            int expiredDateInt = Integer.parseInt(tempYearString + tempMonthString + tempDayString);
            if(todayInt > expiredDateInt) {
                answerList.add(N);
            }

            N++;
        }

        int[] answer = new int[answerList.size()];
        for(int i = 0; i<answerList.size(); i++) {
            answer[i] = answerList.get(i);
        }

        return answer;
    }
}

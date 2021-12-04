package baekjoon.level.level_5;

import java.util.*;
import java.lang.*;
import java.io.*;

public class Baekjoon_No_4344 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
        
        int caseNum = Integer.parseInt(sc.nextLine());
        double result[] = new double[caseNum];
        
        String inputStr = "";
        for(int i=0; i<caseNum; i++){
            inputStr = sc.nextLine();  
            String[] strArr=inputStr.split(" ");
            
            int total = 0;
            double avg = 0.0;
            int avgUpNum = 0;
            
            for(int j=1;j<strArr.length;j++){
                if(strArr[j]=="" || strArr[j]==null){
                    continue;
                }
                total += Integer.parseInt(strArr[j]);
            }
            
            avg=(double)total/Double.parseDouble(strArr[0]);
            
            for(int j=1;j<strArr.length;j++){
                if(strArr[j]!="" &&strArr[j]!=null){
                    if(Integer.parseInt(strArr[j]) > avg){
                        avgUpNum++;
                    }
                   
                }
            }
            
            //System.out.println("출력 "+i+" 평균이상숫자:"+avgUpNum+" 합계 "+total+" 인원 :"+strArr[0]+" 평균 : "+avg);
            result[i] = (double)avgUpNum/Double.parseDouble(strArr[0])*100.0;
            //System.out.println("통계 : "+result[i]);
        }
        
        for(int i=0; i<caseNum; i++){
            //String dbResult = String.format("%.3lf", result[i]);
            System.out.println(String.format("%.3f", result[i])+"%");
        }
	}

}

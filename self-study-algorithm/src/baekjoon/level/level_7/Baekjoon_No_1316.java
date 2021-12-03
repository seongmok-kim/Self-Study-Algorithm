package baekjoon.level.level_7;

import java.util.*;
import java.io.*;

public class Baekjoon_No_1316 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 몇개 받을 지 저장하는 변수
        int count = Integer.parseInt(br.readLine());
        
        // 단어들 저장할 문자열 배열 
        String[] strArr = new String[count];
        
        // 문자열에 단어들 저장
        for(int i=0; i<count; i++){
            strArr[i] = br.readLine();
        }
        
        // 그룹 단어 갯수 
        int num = 0;
        
        for(int i = 0; i<count; i++){
            char[] wordArr = strArr[i].toCharArray();
            ArrayList<Character> chrList = new ArrayList<Character>();
            boolean result = true;
            
            for(int j=0;j<wordArr.length;j++){
                char word = wordArr[j];
                int chrListSize = chrList.size();
                boolean inCheck = false;
                for(int k=0; k<chrListSize; k++){
                    if(word == chrList.get(k)){
                        inCheck = true;
                        if(k==chrListSize-1){
                            result = true;
                            break;
                        }else{
                            result = false;
                            break;
                        }
                    }
                }
                if(inCheck == true){
                    if(result == false){
                        break;
                    }
                }else{
                    chrList.add(word);
                }
            }
            
            if(result == true){
                num++;
            }
        }
        
        System.out.println(num);
	}

}

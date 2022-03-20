package programmers;

public class get_prime_number_from_k {

	// 긴 숫자가 들어올 수 있으니 long으로 선언
	public static boolean isPrime(long num) {
		
		// 1은 소수가 아님.
		if(num == 1) {
			return false;
		}
		// num까지 안보고 제곱근까지만 봐도 됨.
		 for(int i=2; i<=Math.sqrt(num); i++) {
			 if(num%i == 0) {
				 return false;
			 }
		 }
		 
		 return true;
	}
	
	public static int solution(int n, int k) {
		int answer = 0;
		
		String changeNum = "";
		
		// k진수로 변환
		while(n > 0) {
			changeNum = (n%k) + changeNum;
			n = n/k;
		}
		
		String[] numArr = changeNum.split("");
		
		String num = "";
		for(int i=0; i<numArr.length; i++) {
			// 0 을 만난 경우, 이전에 만났던 숫자 확인
			if(numArr[i].equals("0")) {
				// 이전에 만났던 숫자가 없으면 무시
				if(num.equals("")) {
					continue;
				}
				
				// 이전에 만났던 숫자가 소수인지 체크 후
				boolean check = isPrime(Long.parseLong(num));
				
				// 소수라면 카운트
				if(check) {
					answer++;
				}
				
				num = "";
			}
			// 0 이 아니라면, 숫자 저장
			else {
				num += numArr[i];
			}
		}
		// 끝으로 저장된 숫자가 있을 수 있으니 한번 더 체크
		if(!num.equals("")) {
			boolean check = isPrime(Long.parseLong(num));
			
			if(check) {
				answer++;
			}
		}
        
        return answer;
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(solution(437674, 3));
	}

}

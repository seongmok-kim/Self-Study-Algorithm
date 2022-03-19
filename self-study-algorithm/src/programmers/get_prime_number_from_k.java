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
		
		while(n > 0) {
			changeNum = (n%k) + changeNum;
			n = n/k;
		}
		
		String[] numArr = changeNum.split("");
		
		String num = "";
		for(int i=0; i<numArr.length; i++) {
			if(numArr[i].equals("0")) {
				if(num.equals("")) {
					continue;
				}
				boolean check = isPrime(Long.parseLong(num));
				
				if(check) {
					answer++;
				}
				
				num = "";
			}
			else {
				num += numArr[i];
			}
		}
		
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

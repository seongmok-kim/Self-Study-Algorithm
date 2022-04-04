package etc.review;

public class bubbleSort {

	/***** 거품 정렬(BubbleSort) 
	 1. 배열에서 현재 인덱스의 값과 다음 인덱스의 값을 비교해서 현재 인덱스의 값이 클 경우 다음 인덱스와 스왑을 진행한다.(큰 값이 배열 뒤로 가도록)
	 2. 정렬을 완료한 인덱스를 제외하고 위의 과정을 반복한다.
	 - 가장 기초적인 정렬 알고리즘이지만, 효율성은 좋지 않다.
	 - 공간 복잡도 : O(N), 시간 복잡도 : O(N^2)
	******/ 
	public static int[] BubbleSort(int[] arr) {
		for(int i=0; i<arr.length; i++) {
			for(int j=0; j<arr.length - i - 1; j++) {
				if(arr[j] > arr[j+1]) {
					int temp = arr[j+1];
					arr[j+1] = arr[j];
					arr[j] = temp;
				}
			}
		}
		
		return arr;
	}
	
	public static void main(String[] args) {
		int[] arr = {3,4,2,5,6,1,7};
		
		System.out.print("변경 전 : ");
		for(int i=0; i<arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
		
		int[] changed = BubbleSort(arr);
		
		System.out.print("변경 후 : ");
		for(int i=0; i<changed.length; i++) {
			System.out.print(changed[i] + " ");
		}
		System.out.println();
	}	

}

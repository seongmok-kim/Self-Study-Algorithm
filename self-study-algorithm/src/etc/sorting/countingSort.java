package etc.sorting;

public class countingSort {

	/***** 계수 정렬
	 1. 별도의 리스트를 선언하고 그 안에 정렬에 대한 정보를 담는 알고리즘
	 2. 데이터의 범위가 적을 때 효과적이며, 양수일 때 사용이 가능하다.
	 ( 배열의 인덱스를 값으로 여기고 해당 값에 몇 개가 있는 지 기록하는 방식 ) 
	 - 비교 기반의 정렬 방식이 아님. 
	 - 공간 복잡도 : O(N), 시간 복잡도 : O(N+K) 
	******/ 
	public static void CountingSort(int[] arr) {
		
		int[] newArr = new int[arr.length];
		
		for(int i=0; i<arr.length; i++) {
			newArr[arr[i]]++;
		}
		
		System.out.print("변경 후 : ");
		for(int i=0; i<newArr.length; i++) {
			for(int j=0; j<newArr[i]; j++) {
				System.out.print(i + " ");
			}
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] arr = {0, 5, 8, 5, 7, 6, 1, 2, 3, 4, 4, 2, 7, 9, 3, 1, 0};
		
		System.out.print("변경 전 : ");
		for(int i=0; i<arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
		
		CountingSort(arr);
	}

}

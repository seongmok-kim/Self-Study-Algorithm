package etc.sorting;

public class quickSort {

	/***** 퀵 정렬
	 1. 피봇을 이용하는 정렬이다.
	 2. 피봇은 부분 배열 기준 첫 번째 인덱스로 설정한다.
	 3. 피봇의 데이터를 기준으로 왼쪽에는 피봇보다 작은 데이터를 두고 오른쪽에는 큰 데이터를 둔다.
	 4. 피봇의 좌우의 부분배열을 재귀호출하는 방식이다. 
	 - 정렬 알고리즘 중에서 가장 많이 사용되는 알고리즘
	 - 공간 복잡도 : O(N), 시간 복잡도 : O(NlogN) ~ O(N^2)
	******/ 
	
	// arr : 배열
	// left : 시작점, right : 끝점
	public static void QuickSort(int[] arr, int start, int end) {
		
		// 부분 배열의 길이가 1이하인 경우 종료
		if(start >= end) {
			return;
		}
		
		// pivot은 시작점으로 
		int pivot = start;
		
		// 부분 배열의 범위는 pivot을 제외한 곳.
		int left = start + 1;
		int right = end;
		
		while(left <= right) {
			
			while(arr[pivot] > arr[left] && left <= end) {
				left++;
			}
			
			while(arr[pivot] < arr[right] && right>start) {
				right--;
			}
			
			// 엇갈린 경우
			if(left > right) {
				int temp = arr[pivot];
				arr[pivot] = arr[right];
				arr[right] = temp;
			}
			else {
				int temp = arr[left];
				arr[left] = arr[right];
				arr[right] = temp;
			}
		}
		
		QuickSort(arr, start, right-1);
		QuickSort(arr, right+1, end);
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] arr = {3,4,2,5,6,1,7};
		
		System.out.print("변경 전 : ");
		for(int i=0; i<arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
		
		QuickSort(arr, 0, arr.length-1);
		
		System.out.print("변경 후 : ");
		for(int i=0; i<arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

}

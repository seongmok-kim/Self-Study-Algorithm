package etc.sorting;

public class selectionSort {

	/***** 선택 정렬 
	 1. 배열에서 가장 작은 값을 골라서 가장 앞 인덱스와 값을 변경한다. (가장 작은 값이 가장 앞으로 가도록)
	 2. 정렬을 완료한 인덱스를 제외하고 가장 작은 값을 찾은 뒤 앞으로 보내준다.
	 - BubbleSort보다 비슷하지만, 스왑하는 횟수가 적다는 것이 특징이다.
	 - 공간 복잡도 : O(N), 시간 복잡도 : O(N^2)
	******/ 
	public static int[] SelectionSort(int[] arr) {
		for(int i=0; i<arr.length; i++) {
			int min = i;
			
			for(int j=i; j<arr.length; j++) {
				if(arr[j] < arr[min]) {
					min = j;
				}
			}
			
			int temp = arr[i];
			arr[i] = arr[min];
			arr[min] = temp;
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
		
		int[] changed = SelectionSort(arr);
		
		System.out.print("변경 후 : ");
		for(int i=0; i<changed.length; i++) {
			System.out.print(changed[i] + " ");
		}
		System.out.println();
	}

}

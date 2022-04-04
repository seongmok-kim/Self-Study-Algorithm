package etc.review;

public class insertionSort {

	/***** 삽입 정렬 
	 1. 배열의 두 번째 인덱스부터 확인한다. (첫 번째 인덱스는 정렬이 되어있다고 가정한다.)
	 2. 현재 값을 확인하여 적절한 위치(정렬된 곳 좌우만 가능)에 삽입한다.
	 ( 정렬 시작 시, 배열 전체를 확인하는 것이 아닌 부분 배열을 확인하며 점점 넓혀가는 방식 ) 
	 - BubbleSort, SelectionSort 보다 성능이 좋음.
	 - 최악의 경우에는 시간복잡도가 O(N^2)
	 - 공간 복잡도 : O(N), 시간 복잡도 : O(N) ~ O(N^2)
	******/ 
	public static int[] InsertionSort(int[] arr) {
		
		for(int i=1; i<arr.length; i++) {
			
			for(int j=i; j>0; j--) {
				if(arr[j-1] > arr[j]) {
					int temp = arr[j-1];
					arr[j-1] = arr[j];
					arr[j] = temp;
				}
				else {
					break;
				}
			}
		}
		
		return arr;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] arr = {3,4,2,5,6,1,7};
		
		System.out.print("변경 전 : ");
		for(int i=0; i<arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
		
		int[] changed = InsertionSort(arr);
		
		System.out.print("변경 후 : ");
		for(int i=0; i<changed.length; i++) {
			System.out.print(changed[i] + " ");
		}
		System.out.println();
	}

}

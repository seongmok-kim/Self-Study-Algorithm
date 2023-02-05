package programmers;

import java.util.Stack;

// Link : https://school.programmers.co.kr/learn/courses/30/lessons/150369
public class delivery_minimum_length {

    public static void main(String[] args) {
		/*
		int cap = 4;
		int n= 5;
		int[] deliveries = {1, 0, 3, 1, 2};
		int[] pickups = {0, 3, 0, 4, 0};
		*/

		/*
		int cap = 2;
		int n= 7;
		int[] deliveries = {1, 0, 2, 0, 1, 0, 2};
		int[] pickups = {0, 2, 0, 1, 0, 2, 0};
		*/


        int cap = 2;
        int n= 2;
        int[] deliveries = {0, 0};
        int[] pickups = {0, 0};

        long result = solution(cap, n, deliveries, pickups);
        System.out.println(result);
    }

    public static class Pos{
        int idx;
        int amount;

        public Pos(int a, int b) {
            idx = a;
            amount = b;
        }
    }

    public static long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long result = 0;

        Stack<Pos> deliveryStack = new Stack<>();		// 배달할 Stack
        Stack<Pos> pickupStack = new Stack<>();			// 수거할 Stack

        int deliverySum = 0;		// 배달할 박스 총 갯수
        int pickupSum = 0;			// 수거할 박스 총 갯수

        // 각 스택 및 Sum에 넣어준다.
        for(int i=0; i<n; i++) {
            deliveryStack.add(new Pos(i, deliveries[i]));
            deliverySum += deliveries[i];

            pickupStack.add(new Pos(i, pickups[i]));
            pickupSum += pickups[i];
        }

        // 배달도 수거도 전혀 할 필요가 없는 경우엔 움직이지 않아도 되므로 0 반환.
        if(deliverySum + pickupSum == 0) {
            return 0;
        }

        // 두 스택이 모두 비어있지 않을 때
        while(!deliveryStack.isEmpty() && !pickupStack.isEmpty()) {
            int delivery = 0;
            int pickup = 0;

            // 두 스택 중 먼 곳 확인
            int max = Math.max(deliveryStack.peek().idx, pickupStack.peek().idx);
            result += (max + 1) * 2;

            // 배달 진행
            while(!deliveryStack.isEmpty()) {
                Pos now = deliveryStack.pop();
                delivery += now.amount;

                if(delivery > cap) {		// 최대 용량을 벗어나는 경우
                    int rest = (delivery - cap);				// 가능한 만큼만 배달하고
                    deliveryStack.add(new Pos(now.idx, rest));	// 나머지는 keep
                    break;
                }
            }

            // 수거 진행
            while(!pickupStack.isEmpty()) {
                Pos now = pickupStack.pop();
                pickup += now.amount;

                // 배달과 마찬가지
                if(pickup > cap) {
                    int rest = (pickup - cap);
                    pickupStack.add(new Pos(now.idx, rest));
                    break;
                }
            }
        }

        // 배달 or 수거 스택 중에서 남아 있을 수 있기 때문에 한 번 더 확인

        while(!deliveryStack.isEmpty()) {
            int delivery = 0;

            int max = deliveryStack.peek().idx;

            result += (max + 1) * 2;
            while(!deliveryStack.isEmpty()) {
                Pos now = deliveryStack.pop();
                delivery += now.amount;

                if(delivery > cap) {
                    int rest = (delivery - cap);
                    deliveryStack.add(new Pos(now.idx, rest));
                    break;
                }
            }
        }

        while(!pickupStack.isEmpty()) {
            int pickup = 0;

            int max = pickupStack.peek().idx;

            result += (max + 1) * 2;

            while(!pickupStack.isEmpty()) {
                Pos now = pickupStack.pop();
                pickup += now.amount;

                if(pickup > cap) {
                    int rest = (pickup - cap);
                    pickupStack.add(new Pos(now.idx, rest));
                    break;
                }
            }
        }

        return result;
    }
}

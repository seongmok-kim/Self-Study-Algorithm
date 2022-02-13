package programmers;

import java.util.*;

// 음식 클래스 선언
class Food implements Comparable<Food> {

    // 먹는 시간
    private int time;
    
    // 음식 번호
    private int index;

    public Food(int time, int index) {
        this.time = time;
        this.index = index;
    }

    public int getTime() {
        return this.time;
    }

    public int getIndex() {
        return this.index;
    }

    // 우선순위 큐에서 사용
    @Override
    public int compareTo(Food other) {
        if(this.time < other.time)
            return -1;
        else 
            return 1;
    }
}

class Solution {
    public int solution(int[] food_times, long k) {
        // 우선순위 큐 선언
        PriorityQueue<Food> pq = new PriorityQueue<>();
        
        // 모든 음식을 먹는데 필요한 시간
        long summary = 0;
        
        // 모든 음식을 먹는데 필요한 시간 구하기 & 우선순위 큐에 넣기
        for (int i = 0; i < food_times.length; i++) {
            summary += food_times[i];
            pq.offer(new Food(food_times[i], i + 1));
        }
        
        // 만약, 모든 음식을 먹는데 필요한 시간보다 k가 같거나 크다면 -1 반환(먹을 수 있는 음식이 없음)
        if (summary <= k) 
            return -1;

        // 지금까지의 음식을 먹은 시간
        summary = 0; 
        
        // 이전 음식을 먹는데 걸리는 시간
        long previous = 0; 
        
        // 현재 남아있는 음식의 갯수
        long length = food_times.length; 

        // (지금까지의 음식을 먹은 시간 + (현재 음식을 먹는데 걸리는 시간 - 이전 음식을 먹는데 걸리는 시간) * 현재 남아있는 음식의 갯수) 가 k보다 작거나 같을 때
        // peek()은 꺼내는 것이 아닌 확인만 함
        while (summary + ((pq.peek().getTime() - previous) * length) <= k) {
            // 큐에서 꺼내고
            int now = pq.poll().getTime();
            
            // 지금까지 음식을 먹는데 필요한 시간에 더해준다.
            summary += (now - previous) * length;
            
            // 하나의 음식을 다 먹었으니 -1 처리
            length -= 1; 
            
            previous = now; 
        }

        // 음식 번호대로 정렬을 하기위해 ArrayList 이용
        ArrayList<Food> result = new ArrayList<>();
        
        // 우선순위 큐에 남아있는 음식들을 ArrayList에 넣어준다.
        while (!pq.isEmpty()) {
            result.add(pq.poll());
        }
        
        // 음식 번호 순서대로 정렬
        Collections.sort(result, new Comparator<Food>() { 
            @Override
            public int compare(Food a, Food b) {
                return Integer.compare(a.getIndex(), b.getIndex());
            }
        });
        
        // 결과 출력
        return result.get((int) ((k - summary) % length)).getIndex();
    }
}
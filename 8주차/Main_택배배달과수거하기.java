package study.week8;

public class Main_택배배달과수거하기 {
	public static void main(String[] args) {
//		int cap = 2;
//		int n = 7;
		int cap = 4;
		int n = 1;
//		int[] deliveries = {1, 0, 2, 0, 1,0,2};
//		int[] pickups= {0, 2, 0, 1, 0,2,0};
		int[] deliveries = {5, 3, 0, 4, 0};
		int[] pickups  = {1, 0, 3, 1, 2};
		long a = solution(cap, n, deliveries, pickups);
		System.out.println(a);
	}
	public static long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;
        int dIdx = n-1;
        int pIdx = n-1;
        while (deliveries[dIdx] == 0)dIdx--;
        while (pickups[pIdx] == 0)pIdx--;
        int carryBox = cap;
        while (0 <= dIdx || 0 <= pIdx) {
        	answer += (Math.max(dIdx, pIdx)+1) * 2;
        	while (0 <= dIdx && carryBox != 0) {
        		deliveries[dIdx]--;
        		carryBox--;
        		while (0 <= dIdx && deliveries[dIdx] == 0)dIdx--;
        	}
        	carryBox = 0;
        	while (0 <= pIdx && carryBox != cap) {
        		pickups[pIdx]--;
        		carryBox++;
        		while (0 <= pIdx && pickups[pIdx] == 0)pIdx--;
        	}
        	carryBox = cap;
        }
        return answer;
    }
}


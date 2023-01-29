package study.Comb;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class BOJ_9375_패션왕신해빈 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int testCase = 1; testCase < T + 1; testCase++) {
			int N = sc.nextInt();
			Map<String, Integer> clothes = new HashMap<>();
			for (int i = 0; i < N; i++) {
				String name = sc.next();
				String lo = sc.next();
				if (clothes.containsKey(lo))
					clothes.put(lo, (clothes.get(lo) + 1));
				else
					clothes.put(lo,1);
			}
			int cnt = 1;
			for (String str : clothes.keySet()) {
				cnt = cnt * (clothes.get(str) + 1);
			}
			System.out.println(cnt - 1);
		}
	}
}

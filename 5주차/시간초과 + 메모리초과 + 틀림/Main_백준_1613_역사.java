package study.FiveWeeks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main_백준_1613_역사 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(st.nextToken()); // 사건 개수
		int K = Integer.parseInt(st.nextToken()); // 전후 관계 수
		Set<Integer>[] set = new HashSet[N];
		for (int i = 0; i < set.length; i++) {
			set[i] = new HashSet<>(); // i번째 사건이후에 발생한 사건들
		}
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1; // 앞선 사건
			int b = Integer.parseInt(st.nextToken()) - 1; // 이후 사건
			set[a].add(b);
			Queue<Integer> q = new ArrayDeque<>();
			q.offer(b);
			while(!q.isEmpty()) { // 모든 다음 역사의 역사의 ... 를 set[a]에 넣는다
				int next = q.poll();
				for (int nextNum : set[next]) {
					set[a].add(nextNum);
					if (!q.contains(nextNum))
						q.offer(nextNum);
				}
			}
		}
		st = new StringTokenizer(br.readLine());
		int s = Integer.parseInt(st.nextToken());
		for (int i = 0; i < s; i++) {
			st = new StringTokenizer(br.readLine());
			int s1 = Integer.parseInt(st.nextToken()) - 1;
			int s2 = Integer.parseInt(st.nextToken()) - 1;
			if (set[s2].contains(s1))
				sb.append("1\n");
			else if (set[s1].contains(s2))
				sb.append("-1\n");
			else
				sb.append("0\n");
		}
		System.out.println(sb.toString());
	}
}

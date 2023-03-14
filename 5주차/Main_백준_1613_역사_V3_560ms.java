package study.FiveWeeks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main_백준_1613_역사_V3_560ms {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(st.nextToken()); // 사건 개수
		int K = Integer.parseInt(st.nextToken()); // 전후 관계 수
		boolean [][] checker = new boolean [N][N];
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1; // 앞선 사건
			int b = Integer.parseInt(st.nextToken()) - 1; // 이후 사건
			checker[a][b] = true;
		}
		for (int k = 0; k < N; k++) {
			for (int s = 0; s < N; s++) {
				for (int e = 0; e < N; e++) {
					checker[s][e] = checker[s][e]? checker[s][e] : checker[s][k] & checker[k][e];
				}
			}
		}
		st = new StringTokenizer(br.readLine());
		int s = Integer.parseInt(st.nextToken());
		for (int i = 0; i < s; i++) {
			st = new StringTokenizer(br.readLine());
			int s1 = Integer.parseInt(st.nextToken()) - 1;
			int s2 = Integer.parseInt(st.nextToken()) - 1;
			if (checker[s2][s1])
				sb.append("1\n");
			else if (checker[s1][s2])
				sb.append("-1\n");
			else
				sb.append("0\n");
		}
		System.out.println(sb.toString());
	}
}

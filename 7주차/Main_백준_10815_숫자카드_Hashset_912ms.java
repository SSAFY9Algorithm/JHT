package study.sevenWeeks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;
/**
 * 솔직히 HashSet쓰면 빠르긴함/ 얼마 차이 안나네?
 */
public class Main_백준_10815_숫자카드_Hashset_912ms {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N= Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		Set<Integer> s = new HashSet<>();
		for (int i = 0; i < N; i++) {
			s.add(Integer.parseInt(st.nextToken()));
		}
		st = new StringTokenizer(br.readLine());
		int M= Integer.parseInt(st.nextToken());
		int [] ans = new int [M]; 
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			int a = Integer.parseInt(st.nextToken());
			if (s.contains(a))
				ans[i] = 1;
			else
				ans[i] = 0;
		}
		StringBuilder sb = new StringBuilder();
		for (int i : ans) {
			sb.append(i).append(" ");
		}
		System.out.println(sb.toString());
	}
}

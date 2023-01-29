package study.Comb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class BOJ_1269_대칭차집합 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String AB = br.readLine();
		StringTokenizer st = new StringTokenizer(AB);
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		Set<Integer> Aset = new HashSet<Integer>();
		Set<Integer> Bset = new HashSet<Integer>();
		String Aline = br.readLine();
		String Bline = br.readLine();
		st = new StringTokenizer(Aline);
		int ans = 0;
		for (int i = 0; i < A; i++) {
			Aset.add(Integer.parseInt(st.nextToken()));
		}
		st = new StringTokenizer(Bline);
		for (int i = 0; i < B; i++) {
			Bset.add(Integer.parseInt(st.nextToken()));
		}
		for (int a : Aset) {
			if (!Bset.contains(a))
				ans++;
		}
		for (int b : Bset) {
			if (!Aset.contains(b))
				ans++;
		}
		System.out.println(ans);
	}
}

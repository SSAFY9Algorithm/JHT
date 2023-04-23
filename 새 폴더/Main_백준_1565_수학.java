package study.homestudy.day0423;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main_백준_1565_수학 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int Dcnt = Integer.parseInt(st.nextToken());
		int [] D = new int [Dcnt];
		int Mcnt = Integer.parseInt(st.nextToken());
		int [] M = new int [Mcnt];
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < Dcnt; i++) {
			D[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < Mcnt; i++) {
			M[i] = Integer.parseInt(st.nextToken());
		}
		int GCD = getTotalGCD(M);
		long LCM = getTotalLCM(D);
		System.out.println(getAns (GCD, LCM));
	}
	private static int getAns(int GCD, long LCM) {
		if (GCD % LCM != 0)
			return 0;
		long temp = GCD / LCM;
		int ans = 1;
		Map<Long, Integer> map = new HashMap<>();
		ArrayList<Long> deci = new ArrayList<>();
		deci.add(2L);
		long i = 3L;
		while (0 < i * i && i * i <= temp) {
			if (!check(i, deci))
				deci.add(i);
			i++;
		}
		int idx = 0;
		while (temp != 1 && idx < deci.size()) {
			if (temp % deci.get(idx) == 0) {
				if (map.containsKey(deci.get(idx)))
					map.put(deci.get(idx), map.get(deci.get(idx)) + 1);
				else
					map.put(deci.get(idx), 1);
				temp /= deci.get(idx);
			}
			else 
				idx++;
		}
		if (idx == deci.size()) {
			if (map.containsKey(temp))
				map.put(temp, map.get(temp) + 1);
			else
				map.put(temp, 1);
		}
		for (Long key : map.keySet()) {
			ans *= map.get(key) + 1;
		}
		return ans;
	}
	private static boolean check(long i, ArrayList<Long> deci) {
		for (int j = 0; j < deci.size(); j++) {
			if (i % deci.get(j) == 0)
				return true;
		}
		return false;
	}
	private static long getTotalLCM(int[] d) {
		long ans = d[0];
		for (int i = 0; i < d.length; i++) {
			long temp = (ans < d[i]) ? getGCD(ans, d[i]) : getGCD(d[i], ans);
			ans = ans * d[i] / temp;
		}
		return ans;
	}

	private static int getTotalGCD(int[] m) {
		int ans = m[0];
		for (int i = 0; i < m.length; i++) {
			if (ans > m[i])
				ans = getGCD(ans, m[i]);
			else
				ans = getGCD(m[i], ans);
		}
		return ans;
	}

	private static int getGCD(int A, int B) {
		if (B == 0)			
			return A;
		return getGCD(B, A%B);
	}
	private static long getGCD(long A, long B) {
		if (B == 0)			
			return A;
		return getGCD(B, A%B);
	}
}
package study.Comb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_11051_이항계수2 {
	public static void main(String[] args) throws IOException {// %10007
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		if (N-K < K)
			K = N-K;
		if(K == 0) {
			System.out.print(1);
			return ;
		}
		else if(K == 1) { 
			System.out.print(N);
			return ;
		}
		int ans = 1;
		for (int i = 0; i < K; i++) {
			ans = (ans * ((N - i) % 10007)) % 10007;
			if (ans % (K-i) == 0)
				ans = ans/ (K-i);
			else {
				int a = checkNum(K-i);
				ans = (ans * a) %10007;
			}
		}
		System.out.println(ans);
	}
	public static int checkNum(int a) {
		int k = 1;
		while ((k * a) % 10007 != 1) {
			k = (k * a) % 10007;
		}
		return k;
	}
}

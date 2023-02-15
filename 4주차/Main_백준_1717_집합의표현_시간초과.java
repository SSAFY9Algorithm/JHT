package study.day0215;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_백준_1717_집합의표현_시간초과 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int []arr = new int [N + 1];
		for (int i = 0; i < N+1; i++) {
			arr[i] = i;
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int checker = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int aset = arr[a];
			int bset = arr[b];
			if (checker == 0) {
				for (int j = 0; j < N; j++) {
					if (arr[j] == bset)
						arr[j] = aset;
				}
			}
			else {
				if (aset == bset)
					System.out.println("YES");
				else
					System.out.println("NO");
			}
		}
	}
}

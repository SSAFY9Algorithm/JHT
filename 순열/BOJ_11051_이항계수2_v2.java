package study.Comb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_11051_이항계수2_v2 {
	public static void main(String[] args) throws IOException {
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
		int [][] arr = new int[N+1][N+1];
		arr[0][0] = 1;
		for (int i = 0; i < N; i++) {
			arr[i+1][0] = 1;
			arr[i+1][N] = 1;
			for (int j = 1; j < N; j++) {
				arr[i+1][j] = (arr[i][j-1] + arr[i][j]) % 10007; 
			}
		}
		System.out.println(arr[N][K]);
	}
}

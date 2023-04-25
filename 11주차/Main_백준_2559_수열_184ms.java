package study.homestudy.day0422;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/**
 *
 * 슬라이딩 윈도우 사용
 *
 */
public class Main_백준_2559_수열_184ms {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int [] arr = new int [N];
		st = new StringTokenizer(br.readLine());
		int ans = 0;
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			if (i < K)
				ans += arr[i];
		}
		int temp = ans;
		for (int i = K; i < N; i++) {
			temp = temp - arr[i - K] + arr[i];
			if (ans < temp)
				ans = temp;
		}
		System.out.println(ans);
	}
}

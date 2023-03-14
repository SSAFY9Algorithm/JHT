package study.sevenWeeks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_백준_21317_징검다리건너기_84ms {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int [][] jumps = new int [N-1][2];
		for (int i = 0; i < N-1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			jumps[i][0]= Integer.parseInt(st.nextToken());
			jumps[i][1]= Integer.parseInt(st.nextToken());
		}
		int K = Integer.parseInt(br.readLine());
		int [] weight = new int [N];
		for (int i = 2; i < N; i++) {
			weight[i] = 2147483647;
		}
		if (N == 1) { //N==1일때 생각을 못 했었음
			System.out.println(0);
			return;
		}
		weight[0] = 0;
		weight[1] = jumps[0][0];
		for (int i = 2; i < N; i++) { //큰 점프를 안 할 경우(안 하는 것이 이득 일 수 있음)
			weight[i] = Math.min(weight[i-2] + jumps[i-2][1], weight[i-1] + jumps[i-1][0]);
		}
		int ans = weight[N-1]; 
		for (int kidx = 0; kidx < N-3; kidx++) { // 큰 점프를 하게 되는 위치 정하기
			for (int i = 2; i < N; i++) {
				weight[i] = 2147483647;
			}
			weight[0] = 0;
			weight[1] = jumps[0][0];
			for (int i = 0; i < N; i++) {
				if (kidx < i && i < kidx+5)continue;
				if (i != 0 && i != 1) // 해당위치의 가중치를 먼저 계산
					weight[i] = Math.min(weight[i-2] + jumps[i-2][1], weight[i-1] + jumps[i-1][0]);
				if (i == kidx) { // 해당위치가 큰 점프 하는 곳일 경우 3칸뒤 & 4칸뒤 계산 
					weight[kidx+3] = weight[i] + K;
					if (i + 3 < N-1)
						weight[kidx+4] = weight[i+3] + jumps[i+3][0];
					continue;
				}
			}
			if (weight[N-1] < ans)
				ans = weight[N-1];
		}
		System.out.println(ans);
	}
}

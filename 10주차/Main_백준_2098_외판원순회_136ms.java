package study.day0407;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_백준_2098_외판원순회_136ms {
	public static void main(String[] args) throws IOException {
		BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int [][] map = new int [N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int [][] DP = new int [1<<(N-1)][N - 1];
		for (int i = 0; i < N-1; i++) {
			DP[1<<i][i] = map[N-1][i];
		}
		for (int k = 1; k < 1<<(N-1); k++) {
			for (int i = 0; i < N-1; i++) {
				if (DP[k][i] == 0)continue;
				for (int j = 0; j < N-1; j++) {
					if ((k & (1<<j))!= 0)continue;
					if (map[i][j] == 0)continue;
					DP[k | (1<<j)][j] = DP[k | (1<<j)][j] == 0? DP[k][i] + map[i][j]: Math.min(DP[k | (1<<j)][j], DP[k][i] + map[i][j]); 
				}
			}
		}
		int ans = 2147483647;
		for (int i = 0; i < N-1; i++) {
			if (map[i][N-1] == 0 || DP[(1<<(N-1)) - 1][i] == 0)continue;
			ans = Math.min(DP[(1<<(N-1)) - 1][i] + map[i][N-1], ans);
		}
		System.out.println(ans);
	}
}
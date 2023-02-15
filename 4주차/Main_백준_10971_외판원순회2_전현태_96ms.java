package study.day0215;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class Main_백준_10971_외판원순회2_전현태_96ms {
	static int [][] map;
	static int N, isSelected = 0;
	static int ans = 2147483647;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int [N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for (int n = 0; n < N; n++) {//시작지점
			isSelected = isSelected | (1 << n);
			check(n, n, 0, 0);
			isSelected = isSelected - (1 << n);
		}
		System.out.println(ans);
	}
	private static void check(int first, int now, int cnt, int Weight) {
		if (cnt == N-1) {
			int lastWeight = Weight + map[now][first];
			if (ans > lastWeight && map[now][first] != 0)
				ans = lastWeight;
			return;
		}
		for (int i = 0; i < N; i++) {// 순열과 똑같음
			if ((isSelected & (1 << i)) != 0 || map[now][i] == 0)continue;
			isSelected = isSelected | (1 << i);
			int temp = Weight + map[now][i];
			if (temp < ans)
				check(first, i, cnt+1, temp);
			isSelected = isSelected - (1 << i);
		}
	}
	
}

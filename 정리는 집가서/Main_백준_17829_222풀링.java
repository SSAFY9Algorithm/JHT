package study.day0213;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_백준_17829_222풀링 {
	static int[][] map;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		map = new int [N][N]; 
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		System.out.println(check(N));
	}
	private static int check(int n) {
		if (n == 1)
			return map[0][0];
		for (int i = 0; i < n/2; i++) {
			for (int j = 0; j < n/2; j++) {
				int [] arr = {map[2*i][2*j],map[2*i+1][2*j],map[2*i][2*j+1],map[2*i+1][2*j+1]};
				Arrays.sort(arr);
				map[i][j] = arr[2];
			}
		}
		return check(n/2);
	}
}

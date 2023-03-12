package study.sevenWeeks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_백준_1374_강의실_시간초과 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int[][] lesson = new int[N][2];
		boolean[] visited = new boolean [N];
		for (int i = 0; i < lesson.length; i++) {
			st = new StringTokenizer(br.readLine());
			int no = Integer.parseInt(st.nextToken());
			lesson[i][0] = Integer.parseInt(st.nextToken());
			lesson[i][1] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(lesson,(o1,o2)->((o1[0] != o2[0])? o1[0]-o2[0] : o1[1]-o2[1]));
		int cnt = 0;
		int ans = 0;
		while (cnt < N) {
			int idx = 0;
			ans++;
			int end = 0;
			while(idx < N) {
				if (visited[idx] || end > lesson[idx][0]) {idx++; continue;}
				visited[idx] = true;
				end = lesson[idx][1];
				cnt++;
			}
		}
		System.out.println(ans);
	}
}

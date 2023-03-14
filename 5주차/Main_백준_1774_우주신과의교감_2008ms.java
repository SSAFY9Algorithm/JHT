package study.FiveWeeks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_백준_1774_우주신과의교감_2008ms { // 이게 왜 맞음?
	static int [] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 우주신 수 최대1000
		int M = Integer.parseInt(st.nextToken()); // 이미 연결된 통로수 최대 1000
		visited = new int [N];
		for (int i = 0; i < N; i++) {
			visited[i] = i;
		}
		long [][] lines = new long[N*(N-1)/2][3]; // 우주신 간의 간선 정보
		int [][] dots = new int [N][2]; // 우주신들의 위치
		int idx = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			dots[i][0] = Integer.parseInt(st.nextToken()); // 좌표 0~1,000,000
			dots[i][1] = Integer.parseInt(st.nextToken()); // 좌표 0~1,000,000
			for (int j = 0; j < i; j++) {
				long x =(dots[i][0] - dots[j][0]);
				long y =(dots[i][1] - dots[j][1]);
				lines[idx][0] =  x*x + y*y; // 가중치
				lines[idx][1] =  i; // a지점
				lines[idx][2] =  j; // b지점
				idx++;
			}
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = find(Integer.parseInt(st.nextToken()) - 1);
			int b = find(Integer.parseInt(st.nextToken()) - 1);
			if (a < b)visited[b] = visited[a];
			else visited[a] = visited[b];
		}
		Arrays.sort(lines,(o1,o2) -> (o1[0] - o2[0]>0 ? 1 :-1));
		double ans = 0;
		for (int i = 0; i < lines.length; i++) {
			int a = find((int)lines[i][1]);
			int b = find((int)lines[i][2]);
			if (a == b)continue;
			if (a < b)visited[b] = visited[a];
			else visited[a] = visited[b];
			ans += Math.sqrt(lines[i][0]);
		}
		System.out.printf("%.2f\n",ans);
	}

	private static int find(int a) {
		if (a != visited[a])
			visited[a] = find(visited[a]);
		return visited[a];
	}
}

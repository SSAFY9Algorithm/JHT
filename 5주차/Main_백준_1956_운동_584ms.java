package study.February.day0224;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_백준_1956_운동_584ms {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		int [][] matrix = new int [V][V];
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken());
			matrix[a][b] = c;
		}
		/**
		 * 인접리스트로 사이클 찾아 최소 구하는 법 -> 아무리 생각해도 N! 이라 분명 시간초과 일거임
		 * 솔직히 코드 짜기도 귀찮음
		 */
		//플로이드-워셜 : a->b가는 최단경로 구하는 법 중 하나 O(n^3)걸림
		for (int k = 0; k < V; k++) {
			for (int s = 0; s < V; s++) {
				for (int e = 0; e < V; e++) {
					int sk = matrix[s][k];
					int ke = matrix[k][e];
					if (sk == 0 || ke == 0)continue; // s와 e중 k로가는 간선이 없을 경우
					if (matrix[s][e] == 0) {//s->e 간선이 없는 경우
						matrix[s][e] = sk + ke;
					}
					else { // 간선이 있을경우 더 작은 값으로 변경
						matrix[s][e] = Math.min(matrix[s][e], sk + ke);
					}
				}
			}
		}
		int ans = 2147483647;
		for (int i = 0; i < V; i++) {
			if (matrix[i][i] == 0)continue;
			if (matrix[i][i] < ans)
				ans = matrix[i][i]; 
		}
		if (ans == 2147483647)
			System.out.println(-1);
		else
			System.out.println(ans);
	}
}

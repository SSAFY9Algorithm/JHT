package study.fourWeeks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_백준_18404_현명한나이트_272ms {
	static int [][]map;
	static int [][]checker;
	static int [] dx = {-2,-2,-1,-1,1,1,2,2};
	static int [] dy = {-1,1,-2,2,-2,2,-1,1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N  = Integer.parseInt(st.nextToken());
		int M  = Integer.parseInt(st.nextToken());
		map = new int [N][N];
		checker = new int [N][N];
		st = new StringTokenizer(br.readLine());
		
		// 큐와 checker에 첫 나이트의 위치 저장
		int [] knight = new int [2];
		knight[0] = Integer.parseInt(st.nextToken())-1;
		knight[1] = Integer.parseInt(st.nextToken())-1;
		Queue<int[]> q = new ArrayDeque<>();
		q.add(knight);
		checker[knight[0]][knight[1]] = 1;
		
		int []ans = new int [M]; // M개의 상대말에대한 정답 배열
		for (int i = 0; i < M; i++) {// map에 상대말 표시
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()) - 1; 
			int y = Integer.parseInt(st.nextToken()) - 1;
			map[x][y] = i+1;
		}
		int cnt = 0;
		while (cnt < M) {// 상대말을 다 잡았을 경우 종료
			int []nowKnight = q.poll();
			for (int i = 0; i < 8; i++) {
				int cx = nowKnight[0] + dx[i];
				int cy = nowKnight[1] + dy[i];
				if (0 <= cx && cx < N && 0 <= cy && cy < N && checker[cx][cy] == 0) {
					checker[cx][cy] = checker[nowKnight[0]][nowKnight[1]] + 1;
					int[] temp = new int [2];
					temp[0] = cx;
					temp[1] = cy;
					q.add(temp);
					if (map[cx][cy] != 0) {// 상대말의 위치 일 경우
						ans[map[cx][cy] - 1] = checker[cx][cy] -1;
						cnt++;
					}
				}
			}
		}
		StringBuilder sb = new StringBuilder();
		for (int i : ans) {
			sb.append(i + " ");
		}
		System.out.println(sb.toString());
	}
}

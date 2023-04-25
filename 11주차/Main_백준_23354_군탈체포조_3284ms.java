package study.homestudy.day0424;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_백준_23354_군탈체포조_3284ms {
	static int N, pCnt = 0, ans = 2147483647;
	static int [][] map;
	static int [][] numbering;// 탈영병, 위병소 번호
	static int [][] load;
	static List<int []> list = new ArrayList<>(); // 탈영병, 위병소 위치 저장 
	static int [] dr = {-1 ,1 , 0, 0};
	static int [] dc = {0 ,0 , -1, 1};
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int [N][N];
		numbering = new int [N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 0 || map[i][j] == -1) {
					list.add(new int [] {i , j});
					numbering[i][j] = pCnt;
					pCnt++;
				}
			}
		}
		load = new int [pCnt][pCnt];
		simul();
		System.out.println(ans);
	}
	private static void simul() {
		getLoad(); // (탈영병, 위병소) 서로의 최단 거리 계산 (다익스트라)
		perm(0, 0, (1 << 0), 0); //순열
	}
	private static void perm(int cnt, int now, int visited, int sum) {
		if (ans < sum) // 도착전에 정답 보다 크면 답이 될 수 없음
			return;
		if (cnt == pCnt - 1) {
			int temp = sum + load[now][0]; // 마지막에 돌아오는 것 계산 후 비교
			if (temp < ans)
				ans = temp;
			return;
		}
		for (int i = 1; i < pCnt; i++) {
			if ((visited & (1<<i)) != 0)continue;
			perm(cnt + 1, i, visited | (1 << i), sum + load[now][i]);
		}
	}
	private static void getLoad() {
		for (int i = 0; i < pCnt; i++) { // 모든 탈영병, 위병소에대하여 계산
			int leftCnt = pCnt;
			int [] start = list.get(i);
			boolean[][] visited = new boolean [N][N];
			PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[0] - o2[0]);
			pq.offer(new int [] {0, start[0], start[1]});
			while (true) {
				int [] now = pq.poll();
				if (visited[now[1]][now[2]])continue;
				visited[now[1]][now[2]] = true;
				if (map[now[1]][now[2]] == 0 || map[now[1]][now[2]] == -1) {
					load[i][numbering[now[1]][now[2]]] = now[0];
					leftCnt--;
					if (leftCnt == 0)
						break;
				}
				for (int j = 0; j < 4; j++) {
					int cr = now[1] + dr[j];
					int cc = now[2] + dc[j];
					if(0 <= cr && cr < N && 0 <= cc && cc < N && !visited[cr][cc]) {
						if (map[cr][cc] == -1)
							pq.offer(new int [] {now[0], cr, cc});
						else
							pq.offer(new int [] {now[0] + map[cr][cc], cr, cc});
					}
				}
			}
			
		}
	}
}
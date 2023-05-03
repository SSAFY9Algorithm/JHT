package study.day0503;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_백준_22255_호석사우루스_344ms {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine(), " ");
		int Sr = Integer.parseInt(st.nextToken()) - 1;
		int Sc = Integer.parseInt(st.nextToken()) - 1;
		int Er = Integer.parseInt(st.nextToken()) - 1;
		int Ec = Integer.parseInt(st.nextToken()) - 1;
		int [][] map = new int[N][M]; 
		boolean [][][] visited = new boolean[N][M][3];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int []dr = {-1, 1, 0, 0};
		int []dc = {0, 0, -1, 1};
		PriorityQueue<int []> pq = new PriorityQueue<>((o1,o2)->(o1[0] - o2[0]));
		pq.offer(new int [] {map[Sr][Sc], Sr, Sc, 1});
		while (!pq.isEmpty()) {
			int [] now = pq.poll();
			if (visited[now[1]][now[2]][now[3]])continue;
			if (now[1] == Er && now[2] == Ec) {
				System.out.println(now[0]);
				return;
			}
			visited[now[1]][now[2]][now[3]] = true;
			for (int i = 0; i < 4; i++) {
				if(now[3] == 1 && i > 1)continue;
				if(now[3] == 2 && i < 2)continue;
				int cr = now[1] + dr[i];
				int cc = now[2] + dc[i];
				if (0 <= cr && cr < N && 0 <= cc && cc < M && map[cr][cc] != -1 && !visited[cr][cc][(now[3] + 1) % 3]) {
					pq.offer(new int [] {now[0] + map[cr][cc], cr, cc, (now[3] + 1) % 3});
				}
			}
		}
		System.out.println(-1);
	}
}
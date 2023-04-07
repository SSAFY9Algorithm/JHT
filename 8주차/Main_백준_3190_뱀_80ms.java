package study.week8;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_백준_3190_뱀_80ms {
	static int [][] map;
	static int [] head = {0, 0};
	static int [] tail = {0, 0};
	static int [] changeDir;
	static int N,K,L,ans = 0, snaDir = 2, changeDirIdx = 0;
	static int dr[] = {-1, 0, 1, 0}; // 상, 우, 하, 좌
	static int dc[] = {0, 1, 0, -1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());
		map = new int [N][N];
		for (int i = 0; i < K; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			map[x][y] = -1;
		}
		map[0][0] = 2;
		L = Integer.parseInt(br.readLine());
		changeDir = new int [L];
		for (int i = 0; i < L; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			changeDir[i] = (st.nextToken().charAt(0) == 'L')? a : -a;
		}
		solveAns();
		System.out.println(ans);
	}
	private static void solveAns() {
		while (true) {
			ans++;
			head[0] = head[0] + dr[snaDir - 1];
			head[1] = head[1] + dc[snaDir - 1];
			if (head[0]< 0 || head[0] >= N || head[1]< 0 || head[1] >= N  || map[head[0]][head[1]] > 0 )break;
			if (changeDirIdx < L && ans == Math.abs(changeDir[changeDirIdx])) {
				snaDir = (changeDir[changeDirIdx] > 0)? (snaDir + 2) % 4 : (snaDir + 4) % 4;
				snaDir++;
				changeDirIdx++;
			}
			if (map[head[0]][head[1]] != -1) {
				int temp = map[tail[0]][tail[1]] - 1;
				map[tail[0]][tail[1]] = 0;
				tail[0] = tail[0] + dr[temp];
				tail[1] = tail[1] + dc[temp];
			}
			map[head[0]][head[1]] = snaDir;
			
		}
	}
}
package study.sixWeeks;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;
/**
 * 한 정점을 정해 파고 들어가는 것을 생각
 * dfs로 했으나 틀림 (한쪽으로 내려가면 다른 쪽의 합도 생각을 해야 했음)
 * 단, dfs로 가면서 합을 부분집합의 형식으로 풀어야함
 * 따라서, 풀이방법 변경
 */
public class Main_백준_17471_게리맨더링 {
	static boolean []visited;
	static int [][] lines;
	static int [] po;
	static int N, sum, ans = 2147483647;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		po = new int[N];
		visited = new boolean[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		sum = 0;
		for (int i = 0; i < N; i++) {
			po[i] = Integer.parseInt(st.nextToken());
			sum += po[i];
		}
		lines = new int [N][];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			int [] oneLine = new int [num]; 
			for (int j = 0; j < num; j++) {
				oneLine[j] = Integer.parseInt(st.nextToken()) - 1;
			}
			lines[i] = oneLine;
		}
		visited[0] = true;
		check (0, 1, po[0]);
		if (ans == 2147483647)
			System.out.println(-1);
		else
			System.out.println(ans);
	}
	private static void check(int now, int cnt, int temp) {
		if (cnt == N)return;
		int tester = 0;
		for (; tester < N; tester++) {
			if (!visited[tester])break;
		}
		boolean [] tempVisited = new boolean[N];
		for (int i = 0; i < N; i++) {
			tempVisited[i] = visited[i]; 
		}
		if (canLine(tester, cnt, tempVisited)) {
			if (Math.abs(temp - (sum-temp)) < ans)
				ans = Math.abs(temp - (sum-temp));
		}
		for (int i = 0; i < lines[now].length; i++) {
			if (visited[lines[now][i]])continue;
			visited[lines[now][i]] = true;
			check(lines[now][i], cnt + 1, temp + po[lines[now][i]]);
			visited[lines[now][i]] = false;
		}
	}
	private static boolean canLine(int tester, int cnt, boolean[] tempVisited) {
		int V = N - cnt;
		Queue<Integer> q = new ArrayDeque<>();
		q.offer(tester);
		int checker = 1;
		while (!q.isEmpty()) {
			int now = q.poll();
			for (int i = 0; i < lines[now].length; i++) {
				if (tempVisited[lines[now][i]])continue;
				tempVisited[lines[now][i]] = true;
				q.offer(lines[now][i]);
				checker++;
			}
		}
		if (checker != V)
			return false;
		else
			return true;
	}

}

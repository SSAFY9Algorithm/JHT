package study.sixWeeks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_백준_17471_게리멘더링_92ms {
	static boolean []visited;
	static int [][] lines;
	static int [] po;
	static int N, sum, ans = 2147483647;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		po = new int[N];
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
		for (int i = 1; i < (1 << N) -1; i++) {  // 부분 집합 활용
			visited = new boolean[N];
			check(i);
		}
		if (ans == 2147483647)
			System.out.println(-1);
		else
			System.out.println(ans);
	}
//	사전에 미리 값을 보고 ans와 비교하여 return 하는 것을 생각했으나 굳이(?) 라고 판단 & 시간이 더 걸릴거 같음
	private static void check(int flag) {//firstSet : flag에서 1인 인덱스 , SecondSet : 0 인 인덱스 
		int firstSetNum = 0; 
		for (; firstSetNum < N; firstSetNum++) {
			if((flag & (1 << firstSetNum)) != 0)
				break;
		}
		Queue<Integer> q = new ArrayDeque<>();
		q.offer(firstSetNum);
		visited[firstSetNum] = true;
		int firstSetsum = po[firstSetNum];
		while(!q.isEmpty()) { // 1번 set BFS순회 하면서 합, 방문 처리 
			int now = q.poll();
			for (int i = 0; i < lines[now].length; i++) {
				if ((flag & (1 << lines[now][i])) == 0 || visited[lines[now][i]])continue;
				visited[lines[now][i]] = true;
				firstSetsum +=po[lines[now][i]];
				q.offer(lines[now][i]);
			}
		}
		int secondSetNum = 0;
		for (; secondSetNum < N; secondSetNum++) {
			if((flag & (1 << secondSetNum)) == 0)
				break;
		}
		q.offer(secondSetNum);
		visited[secondSetNum] = true;
		int secondSetsum = po[secondSetNum];
		while(!q.isEmpty()) { // 2번 set BFS순회 하면서 합, 방문 처리
			int now = q.poll();
			for (int i = 0; i < lines[now].length; i++) {
				if ((flag & (1 << lines[now][i])) != 0 || visited[lines[now][i]])continue;
				visited[lines[now][i]] = true;
				secondSetsum +=po[lines[now][i]];
				q.offer(lines[now][i]);
			}
		}
		for (int i = 0; i < N; i++) { // 모든 정점을 방문 했는지 확인
			if (!visited[i])return;
		}
		if (Math.abs(firstSetsum -secondSetsum) < ans) { // 차이가 작으면 ans변경
			ans = Math.abs(firstSetsum -secondSetsum);
		}
	}
}

package study.week8;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_백준_23290_마법사상어와복제_408ms {
	static Queue<Integer>[][] fishes;
	static int [][] sharSmell;
	static int M,S, maxFishCnt = -1;
	static int [] dr = {0, -1, -1, -1, 0, 1, 1, 1};
	static int [] dc = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int [] shark = new int[2];
	static int [] catchRoad = new int[3];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		fishes = new ArrayDeque[4][4];
		sharSmell = new int [4][4];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				fishes[i][j] = new ArrayDeque();
			}
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int d = Integer.parseInt(st.nextToken()) - 1;
			fishes[r][c].offer(d);
		}
		st = new StringTokenizer(br.readLine());
		shark[0] = Integer.parseInt(st.nextToken()) - 1;
		shark[1] = Integer.parseInt(st.nextToken()) - 1;
		
		sharSmell[shark[0]][shark[1]] = -1;
		Simule(S);
//		System.out.println("================FINAL==========");
//		for (int i = 0; i < 4; i++) {
//			System.out.println(Arrays.toString(fishes[i]));
//		}
		System.out.println(ans());
	}
	private static int ans() {
		int ans = 0;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				ans += fishes[i][j].size();
			}
		}
		return ans;
	}
	private static void Simule(int time) {
//		System.out.println(Arrays.toString(shark));
//		System.out.println("==================f========");
//		for (int i = 0; i < 4; i++) {
//			System.out.println(Arrays.toString(fishes[i]));
//		}
//		System.out.println("================ss==========");
//		for (int i = 0; i < 4; i++) {
//			System.out.println(Arrays.toString(sharSmell[i]));
//		}
		if(time == 0)return;
		Queue<Integer>[][] temp = new ArrayDeque[4][4];
		Queue<Integer>[][] sum = new ArrayDeque[4][4];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				temp[i][j] = new ArrayDeque();
				sum[i][j] = new ArrayDeque();
			}
		}
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				int size = fishes[i][j].size();
				for (int fish = 0; fish < size; fish++) {
					int sight = fishes[i][j].poll();
					sum[i][j].offer(sight);
					for (int k = 0; k < 8; k++) {
						int cr = i + dr[(8 + sight-k)%8];
						int cc = j + dc[(8 + sight-k)%8];
						if (0 <= cr && cr < 4 && 0 <= cc && cc < 4 && sharSmell[cr][cc] == 0) {
							temp[cr][cc].offer((8 + sight-k)%8);
							break;
						}
						if (k == 7)
							temp[i][j].offer(sight);
					}
				}
			}
		}
		smell();
		maxFishCnt = -1;
//		System.out.println("=============temp(before)=============");
//		for (int i = 0; i < 4; i++) {
//			System.out.println(Arrays.toString(temp[i]));
//		}
		CatchFish(0, shark[0], shark[1], new int [3], 0, temp, new boolean [4][4]);
		fishes = Plus(temp, sum);
		Simule(time - 1);
	}
	private static void CatchFish(int cnt, int sR, int sC, int[] temp, int fishCnt, Queue<Integer>[][] map, boolean [][] visited) {
		if (cnt == 3) {
			if (maxFishCnt < fishCnt) {
				maxFishCnt = fishCnt;
				for (int i = 0; i < 3; i++) {
					catchRoad[i] = temp[i];
				}
			}
			return;
		}
		//0:좌, 2:상, 4:우, 6:하
		//상
		if (sR - 1 > -1) { // && (cnt == 0 || temp[cnt - 1] != 6)
			temp[cnt] = 2;
			if (visited[sR - 1][sC] == false) {
				visited[sR - 1][sC] = true;
				CatchFish(cnt + 1, sR - 1, sC, temp, fishCnt + map[sR-1][sC].size(), map, visited);
				visited[sR - 1][sC] = false;
			}else
				CatchFish(cnt + 1, sR - 1, sC, temp, fishCnt, map, visited);
		}
		//좌
		if (sC - 1 > -1) { // && (cnt == 0 || temp[cnt - 1] != 4)
			temp[cnt] = 0;
			if (visited[sR][sC - 1] == false) {
				visited[sR][sC - 1] = true;
				CatchFish(cnt + 1, sR, sC - 1, temp, fishCnt + map[sR][sC - 1].size(), map, visited);
				visited[sR][sC - 1] = false;
			}else
				CatchFish(cnt + 1, sR, sC - 1, temp, fishCnt, map, visited);
		}
		//하
		if (sR + 1 < 4) { // && (cnt == 0 || temp[cnt - 1] != 2)
			temp[cnt] = 6;
			if (visited[sR + 1][sC] == false) {
				visited[sR + 1][sC] = true;
				CatchFish(cnt + 1, sR + 1, sC, temp, fishCnt + map[sR + 1][sC].size(), map, visited);
				visited[sR + 1][sC] = false;
			}else
				CatchFish(cnt + 1, sR + 1, sC, temp, fishCnt, map, visited);
		}
		//우
		if (sC + 1 < 4) { // && (cnt == 0 || temp[cnt - 1] != 0)
			temp[cnt] = 4;
			if (visited[sR][sC + 1] == false) {
				visited[sR][sC + 1] = true;
				CatchFish(cnt + 1, sR, sC + 1, temp, fishCnt + map[sR][sC + 1].size(), map, visited);
				visited[sR][sC + 1] = false;
			}else
				CatchFish(cnt + 1, sR, sC + 1, temp, fishCnt, map, visited);
		}
		if (cnt == 0)
			MoveShark(map);
	}
	private static void MoveShark(Queue<Integer>[][] map) {
		int r = shark[0];
		int c = shark[1];
		if (sharSmell[r][c] == -1) {
			sharSmell[r][c] = 0;
		}else {
			sharSmell[r][c] = 1;
		}
		for (int i = 0; i < 3; i++) {
			r += dr[catchRoad[i]];
			c += dc[catchRoad[i]];
			if (map[r][c].size()==0)continue;
			map[r][c] = new ArrayDeque();
			sharSmell[r][c] = 2;
		}
		if (sharSmell[r][c] == 2)
			sharSmell[r][c] = -2;
		else
			sharSmell[r][c] = -1;
		shark[0] = r;
		shark[1] = c;
	}
	private static Queue<Integer>[][] Plus(Queue<Integer>[][] temp, Queue<Integer>[][] sum) {
//		System.out.println("=============temp=============");
//		for (int i = 0; i < 4; i++) {
//			System.out.println(Arrays.toString(temp[i]));
//		}
//		System.out.println("============sum==============");
//		for (int i = 0; i < 4; i++) {
//			System.out.println(Arrays.toString(sum[i]));
//		}
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				while(!temp[i][j].isEmpty()) {
					sum[i][j].offer(temp[i][j].poll());
				}
			}
		}
		return sum;
	}
	private static void smell() {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (sharSmell[i][j] > 0)
					sharSmell[i][j]--;
			}
		}
	}
}
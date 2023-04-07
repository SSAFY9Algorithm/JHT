package study.week8;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_백준_21611_마법사상어와블리자드_144ms {
	static int N,M;
	static int [][] map;
	static int [] line;
	static int [] dr = {0, 1, 0, -1};
	static int [] dc = {1, 0, -1, 0};
	static int [] ans = new int [3];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int [N][N];
		line = new int [N*N-1];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		MakeMapToLine();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int d = Integer.parseInt(st.nextToken()); // 1상,2하,3좌,4우
			int s = Integer.parseInt(st.nextToken());
			destroy(d, s);
			DeleteAndMove();
			MakeNewLine();
		}
		System.out.println((ans[0] + 2* ans[1] + 3 * ans[2]));
	}
	private static void MakeNewLine() {
		int []temp = new int [N*N-1];
		int tempIdx = 0;
		for (int i = 0; i < line.length && tempIdx < line.length;) {
			if (line[i] == 0)break;
			int j = i;
			while (line[i] == line[j])j++;
			temp[tempIdx++] = j - i;
			temp[tempIdx++] = line[i];
			i = j;
		}
		line = temp;
	}
	private static void DeleteAndMove() {
		boolean checker = true;
		while(checker) {
			int frontIdx = 0;
			int backIdx = 0;
			checker = false;
			while (backIdx < line.length) {
				int temp = 0;
				while (frontIdx < line.length && (line[frontIdx] == 0 || line[backIdx] == line[frontIdx])) {
					if (line[frontIdx] != 0)
						temp++;
					frontIdx++;
				}
				if (temp >= 4) {
					checker = true;
					while (backIdx < frontIdx) {
						if (line[backIdx]!=0)
							ans[line[backIdx]-1]++;
						line[backIdx] = 0;
						backIdx++;
					}
				}
				backIdx = frontIdx;
			}
		}
		int cnt = 0;
		for (int i = 0; i < line.length; i++) {
			if (line[i] == 0) continue;
			line[cnt++] = line[i];
			if (i + 1!=cnt)
				line[i] = 0;
		}
	}
	private static void destroy(int d, int s) {
		int startPoint = (d==1)? 6: (d==2)? 2: (d==3)? 0: 4;
		int temp = (d==1)? 15: (d==2)? 11: (d==3)? 9: 13;
		for (int i = 0; i < s; i++) {
			line[startPoint] = 0;
			startPoint += temp;
			temp+=8;
		}
	}
	private static void MakeMapToLine() {
		boolean [][] visited = new boolean [N][N];
		int site = 0;
		int r = 0;
		int c = 0;
		int index = line.length -1;
		while(index >= 0) {
			visited[r][c] = true;
			line[index--] = map[r][c];
			int cr = r + dr[site];
			int cc = c + dc[site];
			if (cr < 0 || N <= cr || cc < 0 || N <= cc || visited[cr][cc])
				site = (site+1)%4;
			r += dr[site];
			c += dc[site];
		}
	}
}
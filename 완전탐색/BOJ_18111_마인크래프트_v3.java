package study.BF;
/**
 * 인터넷에 다른 사람들 풀이 확인후 결과 계산에서는 별다른 점 없었음
 * 입출력 문제라 판단
 * scanner에서 bufferedReader로 변경 후 내부에서 데이터 가공
 * (정답)
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_18111_마인크래프트_v3 {
	public static void main(String[] args) throws IOException  {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String NMB = br.readLine();
		StringTokenizer st = new StringTokenizer(NMB);
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		int [] map = new int [257];
		int min = 256;
		int max = 0;
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			StringTokenizer lineSt = new StringTokenizer(line);
			for (int j = 0; j < M; j++) {
				int a = Integer.parseInt(lineSt.nextToken());
				map[a]++;
				if (a > max)
					max = a; 
				if (a < min)
					min = a; 
			}
		}
		br.close();
		int ans = 2147283647;
		int check = 0;
		for (int i = min; i <= max; i++) {
			int time = 0;
			int B_check = B;
			for (int j = min; j <= max; j++) {
				if (map[j] == 0 || i == j)
					continue;
				if (i - j < 0) {
					time = time - 2 *(map[j] * (i - j));
					B_check = B_check - (map[j] * (i - j));
				}
				else {
					time = time + (map[j] * (i - j));
					B_check = B_check - (map[j] * (i - j));
				}
				if (time > ans)
					break;
			}
			if (B_check >= 0 && time <= ans) {
				ans = time;
				check = i;
			}
			if (time > ans)
				break;
		}
		System.out.println(ans + " " + check);
	}
}

package study.twoWeeks;
/**
 * 시간 4초 나옴. 왜 시간초과 안나온지 모르겠음
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_백준_11509_풍선맞추기 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int [] total = new int [N];
		total[0] = Integer.parseInt(st.nextToken());
		int idx = 1;
		for (int i = 1; i < N; i++) {
			int H = Integer.parseInt(st.nextToken());
			int checkPoint = -1;
			for (int j = 0; j < total.length; j++) {
				if (total[j] > H) {
					if (total[j] - H == 1) {
						checkPoint = j;
						break;// 이거 없으면 시간초과남 - 백트래킹 문제인가?
					}
				}
			}
			if (checkPoint == -1)
				total[idx++] = H;
			else {
				total[checkPoint] = H;
			}
		}
		System.out.println(idx);
	}
}

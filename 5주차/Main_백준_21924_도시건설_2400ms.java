package study.February.day0223;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_백준_21924_도시건설_2400ms { // int -> long 으로 바꿈
	static int [] setNum;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 건물수
		int M = Integer.parseInt(st.nextToken()); // 간선수 (도로수)
		setNum = new int [N];
		for (int i = 0; i < N; i++) {
			setNum[i] = i;
		}
		int [][] streets =  new int [M][3]; // 0:비용 1:건물번호1 2:건물번호2
		long total = 0;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			streets[i][1] = Integer.parseInt(st.nextToken()) - 1;
			streets[i][2] = Integer.parseInt(st.nextToken()) - 1;
			streets[i][0] = Integer.parseInt(st.nextToken());
			total += (long)streets[i][0];
		}
		Arrays.sort(streets, (o1, o2) -> (o1[0] - o2[0])); // 비용이 작은 것 부터 건설
		long money = 0; 
		for (int i = 0; i < M; i++) {
			int a = find(streets[i][1]);
			int b = find(streets[i][2]);
			if (a == b)continue;// 2개가 같은 집합에 있으면 넘어감 (union find 사용)
			if (a < b)
				setNum[b] = a;
			else
				setNum[a] = b;
			money += (long)streets[i][0]; // total에서 그냥 빼는 게 더 좋아보이긴함
		}
		for (int i = 1; i < N; i++) {//모든 건물이 연결되어있는지 판별 (union find)
			if (find(0) != find(i)) {
				System.out.println(-1);
				return;
			}
		}
		System.out.println(total - money);
	}

	private static int find(int i) {
		if (setNum[i] != i)
			setNum[i] = find(setNum[i]);
		return setNum[i];
	}
}

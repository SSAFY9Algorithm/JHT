package study.day0215;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/**
 * stringbuilder 안 썼다가 시간초과 났음
 * */
public class Main_백준_1717_집합의표현_4308ms {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int []arr = new int [N + 1];
		for (int i = 0; i < N+1; i++) {
			arr[i] = i;
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int checker = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int aset = arr[a];
			while (aset != arr[aset]) {
				aset = arr[aset];
			}
			int bset = arr[b];
			while (bset != arr[bset]) {
				bset = arr[bset];
			}
			if (checker == 0) {// 굳이 다 바꿀 필요가 없음 느낌 최종 부모만 건들면 됨
				arr[bset] = aset;
			}
			else {// 최종으로 가르키는 부모의 주소가 같으면 yes
				if (aset == bset)
					sb.append("YES\n");
				else
					sb.append("NO\n");
			}
		}
		System.out.println(sb.toString());
	}
}

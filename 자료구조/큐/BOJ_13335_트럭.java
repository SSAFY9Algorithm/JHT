package study.DataStructure.Queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_13335_트럭 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); //트럭수
		int W = Integer.parseInt(st.nextToken()); //다리길이
		int L = Integer.parseInt(st.nextToken()); //최대하중
		int [][] tru = new int [2][N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			tru[0][i] = Integer.parseInt(st.nextToken());
		}
		/** 모든 트럭이 다 지나갈 때(tru[1]의 모든 요소가 W+1) 까지 time++ */
		int time = 0;
		int front = 0; //다리위 제일 앞
		int back = 1; // 다리위 제일 뒤 + 1
		int sum = tru[0][0];
		while (tru[1][N-1] != W+1) {
			for (int i = front; i < back; i++) {
				tru[1][i]++;
			}
			if (tru[1][front] == W+1) {
				sum = sum - tru[0][front];
				front++;
				if (back < N && sum + tru[0][back] <= L) {
					sum = sum + tru[0][back];
					tru[1][back]++;
					back++;
				}
			}
			else if (tru[1][0] != 1){ //시작하자마자 2대가 출발 하는 경우가 생겨서 if 추가함
				if (back < N && sum + tru[0][back] <= L) {
					sum = sum + tru[0][back];
					tru[1][back]++;
					back++;
				}
			}
			time++;
		}
		System.out.println(time);
	}
}

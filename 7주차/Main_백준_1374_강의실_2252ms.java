package study.sevenWeeks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
/**
 *	시간 줄이는 법 좀 알려주셈
 */
public class Main_백준_1374_강의실_2252ms {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		PriorityQueue<int[]> lesson = new PriorityQueue<>((o1,o2)->((o1[0] != o2[0])? o1[0]-o2[0] : o1[1]-o2[1]));
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int no = Integer.parseInt(st.nextToken());
			lesson.add(new int [] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())});
		}
		int [] room = new int [N];
		int roomIdx = 1;
		while (!lesson.isEmpty()) {
			int [] lessInfo = lesson.poll();
			int i = 0;
			for (; i < roomIdx; i++) { // 이부분이 오래 걸리는 것 같은데 최적화를 모르겟음 
				if (room[i] <= lessInfo[0]) {
					room[i] = lessInfo[1];
					break;
				}
			}
			if (i == roomIdx) {
				room[roomIdx] = lessInfo[1];
				roomIdx++;
			}
		}
		System.out.println(roomIdx);
	}
}

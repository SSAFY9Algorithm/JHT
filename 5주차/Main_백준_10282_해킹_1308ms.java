package study.FiveWeeks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_백준_10282_해킹_1308ms {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int testCase = 0; testCase < T; testCase++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken()); // 컴퓨터 수
			int d = Integer.parseInt(st.nextToken()); // 의존성 수
			int c = Integer.parseInt(st.nextToken()) - 1; // 첫 바이러스 컴퓨터 번호
			Node[] nodes = new Node[n];
			for (int i = 0; i < nodes.length; i++) {
				nodes[i] = new Node();
			}
			for (int i = 0; i < d; i++) {
				st = new StringTokenizer(br.readLine());
				int b = Integer.parseInt(st.nextToken()) - 1; // a컴 
				int a = Integer.parseInt(st.nextToken()) - 1; // b컴
				int s = Integer.parseInt(st.nextToken()); // a컴 감염시 b감염시간
				int[] insert = {b,s};
				nodes[a].li.add(insert);
			}
			Queue<Integer> q = new ArrayDeque<Integer>();
			q.offer(c);
			nodes[c].virus = true;
			nodes[c].time = 0;
			while (!q.isEmpty()) {
				Node now = nodes[q.poll()];
				for (int i = 0; i < now.li.size(); i++) {
					int next = now.li.get(i)[0]; // 다음으로 감염될 컴퓨터 인덱스
					nodes[next].virus = true;
					int temp = nodes[next].time;
					if (nodes[next].time != -1)
						nodes[next].time = Math.min(nodes[next].time, now.time + now.li.get(i)[1]);
					else
						nodes[next].time = now.time + now.li.get(i)[1];
					if (temp != nodes[next].time) //컴퓨터가 감염된 시간이 변경 되었을 경우 큐에 넣음
						q.offer(next);
				}
			}
			int cnt = 0; // 감염된 수
			int time = 0; // 총 감염시간
			for (int i = 0; i < nodes.length; i++) {
				if(nodes[i].virus)
					cnt++;
				if(time < nodes[i].time)
					time = nodes[i].time;
			}
			sb.append(cnt).append(" ").append(time).append("\n");
		}
		System.out.println(sb.toString());
	}
	static class Node{
		boolean virus = false; // 감염 되었는 지
		int time = -1; // 감염 최소 시간
		List<int[]> li = new ArrayList<int[]>(); //  0: 감염될컴, 1:감염시간
	}
}

package study.fourWeeks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_백준_1005_ACMCraft_848ms {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int testCase = 0; testCase < T; testCase++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			Node[] arr = new Node[N+1];
			int K = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N+1; i++) {
				arr[i] = new Node();
			}
			for (int i = 1; i < N+1; i++) {
				arr[i].Time = Integer.parseInt(st.nextToken());
			}
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				arr[x].li.add(y);
				arr[y].get++;
			}
			Queue<Integer> q = new ArrayDeque<>();
			for (int i = 1; i < N+1; i++) {
				if (arr[i].get == 0) { // 앞선 건물이 없을 경우 최종건설 시간은 본인의 시간
					arr[i].EndTime = arr[i].Time;
					q.add(i);
				}
			}
			while (!q.isEmpty()) {
				int idx = q.poll();
				for (int i = 0; i < arr[idx].li.size(); i++) {
					arr[arr[idx].li.get(i)].get--;
					int temp = arr[arr[idx].li.get(i)].Time + arr[idx].EndTime; //앞선 건물 건설후 다음 건설 시간의 기대값
					arr[arr[idx].li.get(i)].EndTime = Math.max(arr[arr[idx].li.get(i)].EndTime, temp); // 기대값들중 최대값을 저장
					if (arr[arr[idx].li.get(i)].get == 0) {
						q.offer(arr[idx].li.get(i));
					}
				}
			}
			int W = Integer.parseInt(br.readLine());
			System.out.println(arr[W].EndTime);
		}
	}
}
class Node{
	int get = 0; // 앞선 노드의 개수
	int Time; // 해당 노드 건설 걸리는 시간
	int EndTime; // 해당 노드가 건설 되었을 때 시간
	List<Integer> li = new ArrayList<Integer>();// 노드 건설시 건설 할 수 있는 건물 목록
}

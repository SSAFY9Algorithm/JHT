package study.day0215;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_백준_14567_선수과목_564ms
{
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		Queue<Integer> q = new ArrayDeque<>();
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		Node [] lessons = new Node [N+1];
		for (int i = 0; i < lessons.length; i++) {
			lessons[i] = new Node();
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			lessons[A].li.add(B);
			lessons[B].get++;
		}
		for (int i = 1; i < lessons.length; i++) {
			if (lessons[i].get == 0) {// 전부 확인했을때 0 이면 큐에 넣는다
				q.offer(i);
				lessons[i].can = 1;
			}
		}
		while (!q.isEmpty()) {
			int now = q.poll();
			for (int i = 0; i < lessons[now].li.size(); i++) { // 수업 들었을 때 열리는 수업의 get--
				lessons[lessons[now].li.get(i)].get--;
				if (lessons[lessons[now].li.get(i)].get == 0) { // get이 0이 되었을 때 큐에 넣어준다. 이때 can 계산
					lessons[lessons[now].li.get(i)].can =lessons[now].can + 1; 
					q.offer(lessons[now].li.get(i));
				}
			}
		}
		StringBuilder ans = new StringBuilder();
		for (int i = 1; i < lessons.length; i++) {
			ans.append(lessons[i].can + " ");
		}
		System.out.println(ans.toString());
	}
}
class Node{
	int get = 0; // node수업을 듣기위해 꼭 들어야하는 수업수
	int can; // 빨리 들을 수 있는 학기(정답저장)
	List <Integer> li = new ArrayList<>(); // 이 노드 수업을 들었을 때 들을 수 있는 다음 과목 
}

package study.DataStructure.LinkedList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/**
 * L = 커서 왼쪽이동
 * D = 커서 오른쪽 이동
 * B = 커서 왼쪽 삭제
 * P $ = 커서 왼쪽에 $문자 추가
 */
public class BOJ_1406_에디터 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder str = new StringBuilder(br.readLine());
		Node curser = new Node();
		Node first = curser;
		for (int i = 0; i < str.length(); i++) {
			Node make = new Node();
			make.ch = str.charAt(i);
			make.front = curser;
			curser.back = make;
			curser = make;
		}
		int M = Integer.parseInt(br.readLine());
		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			char move = st.nextToken().charAt(0);
			if (move == 'L') {
				if (curser.front != null)
					curser = curser.front;
			}
			else if (move == 'D') {
				if (curser.back != null)
					curser = curser.back;
			}
			else if (move == 'B') {
				if (curser.front != null) {
					Node b = curser.back;
					Node f = curser.front;
					curser = curser.front;
					f.back = b;
					if (b!=null)
						b.front = f;
				}
			}
			else if (move == 'P') {
				char a = st.nextToken().charAt(0);
				Node make = new Node();
				make.ch = a;
				make.front = curser;
				make.back = curser.back;
				if (curser.back != null)
					curser.back.front = make;
				curser.back = make;
				curser = make;
			}
			
		}
		StringBuilder ans = new StringBuilder();
		first = first.back;
		while (first != null) {
			ans.append(first.ch);
			first = first.back;
		}
		System.out.println(ans);
	}
}
class Node {
	char ch;
	Node front;
	Node back;
}

package study.homestudy.day0424;
import java.util.*;

public class Main_프로그래머스_표편집 {
	public static void main(String[] args) {
	}
}
class Solution1 {
    public String solution(int n, int k, String[] cmd) {
       StringBuilder answer = new StringBuilder();
        Stack<Node> s = new Stack<>();
        Node start = new Node();
		start.num = 0;
		Node now = make(start, n, k);
        for(int i = 0 ; i < cmd.length;i++){
            StringTokenizer st = new StringTokenizer(cmd[i], " ");
            String com = st.nextToken();
            if (com.equals("U")){
                int X = Integer.parseInt(st.nextToken());
                while (X-- > 0)
                	now = now.front;
            }else if (com.equals("D")){
                int X = Integer.parseInt(st.nextToken());
                while (X-- > 0)
                	now = now.back;
            }else if (com.equals("C")){
            	s.add(now);
            	if (now.back == null) { // 가장뒤에 삭제
            		Node front = now.front;
            		front.back = now.back;
            		now = front;
            	}else {
            		Node del = now;
            		now = now.back;
            		now.front = del.front;
            		if (del.front != null)
            			del.front.back = now;
                    else // 가장 앞에 삭제시 시작점 변경
                        start = now;
            	}
            }else if (com.equals("Z")){
            	Node restore = s.pop();
            	Node front = restore.front;
            	Node back = restore.back;
                if (front != null)
            	    front.back = restore;
            	if (back != null)
            		back.front = restore;
                if (front == null)
                    start = restore;
            }
        }
        for (int i = 0; i < n; i++) {
			if (start != null && start.num == i) {
				answer.append("O");
				start = start.back;
			}else {
				answer.append("X");
			}
		}
        return answer.toString();
    }
    private static Node make(Node start, int n, int k) {
		Node res =null;
		Node maker = start;
		for (int i = 1; i < n; i++) {
			Node next = new Node();
			next.num = i;
			maker.back = next;
			next.front = maker;
			maker = next;
			if (i == k)
				res = maker;
		}
		return res;
	}
}
class Node{
	Node front;
	int num;
	Node back;
}
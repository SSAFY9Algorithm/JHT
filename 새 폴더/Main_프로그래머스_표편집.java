package study.homestudy.day0422;
import java.util.*;
public class Main_프로그래머스_표편집 {
	public static void main(String[] args) {
		System.out.println(Edit.solution(8, 2, new String [] {"D 2","C","U 3","C","D 4","C","U 2","Z","Z"}));
		System.out.println(Edit.solution(8, 2, new String [] {"D 2","C","U 3","C","D 4","C","U 2","Z","Z","U 1","C"}));
		System.out.println(Edit.solution(8, 7, new String [] {"C", "C", "C", "C"}));
	}
}
class Edit {
    public static String solution(int n, int k, String[] cmd) {
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
            	if (now.back == null) {
            		Node front = now.front;
            		front.back = now.back;
            		now = front;
            	}else {
            		Node del = now;
            		now = now.back;
            		now.front = del.front;
            		if (del.front != null)
            			del.front.back = now;
            	}
            }else if (com.equals("Z")){
            	Node restore = s.pop();
            	Node front = restore.front;
            	Node back = restore.back;
                if (front != null)
            	    front.back = restore;
            	if (back != null)
            		back.front = restore;
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
package study.day0404;

import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class Main_프로그래머스_등산코스정하기_메모리초과{
	public static void main(String[] args) {
//		int n = 7;
//		int [][] paths = {{1,2,5},{1,4,1},{2,3,1},{2,6,7},{4,5,1},{5,6,1},{6,7,1}};
//		int [] gates = {3,7};
//		int [] summits = {1,5 };
		
//		int n = 7;
//		int [][] paths = {{1,4,4},{1,6,1},{1,7,3},{2,5,2},{3,7,4},{5,6,6}};
//		int [] gates = {1};
//		int [] summits = {2,3 ,4};
		
		int n = 6;
		int [][] paths = {{1,2,3},{2,3,5},{2,4,2},{2,5,4},{3,4,4},{4,5,3},{4,6,1},{5,6,1}};
		int [] gates = {1, 3};
		int [] summits = {5};
		int [] a = Solution2.solution(n, paths, gates, summits);
		System.out.println(Arrays.toString(a));
	}
}
class Solution2 {
    static public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
    	 int[] answer = {2147483647, 2147483647};
         boolean [] visited = new boolean[n + 1];
         PriorityQueue<int []> pq = new PriorityQueue<>((o1, o2)->(o1[0] - o2[0]));
         Set<Integer> set = new HashSet<>();
         int [][] map = new int [n+1][n+1];
         for (int i = 0; i < paths.length; i++) {
 			int x = paths[i][0];
 			int y = paths[i][1];
 			int w = paths[i][2];
 			map[x][y] = w;
 			map[y][x] = w;
 		}
         for (int i = 0; i < summits.length; i++) {
 			set.add(summits[i]);
 		}
         for (int i = 0; i < gates.length; i++) {
 			pq.offer(new int [] {0, gates[i]});
 		}
         while (!pq.isEmpty()) {
         	int [] now = pq.poll();
         	if (answer[1] < now[0])break;
         	if (visited[now[1]])continue;
         	visited[now[1]] = true;
         	if (set.contains(now[1])) {
         		if (now[1] < answer[0]) {
         			answer[0] = now[1];
         			answer[1] = now[0];
     			}
         		continue;
         	}
         	for (int i = 1; i < n+1; i++) {
 				if (visited[i] || map[now[1]][i] == 0)continue;
 				pq.offer(new int [] {Math.max(now[0], map[now[1]][i]), i});
 			}
         }
         return answer;
    }
}
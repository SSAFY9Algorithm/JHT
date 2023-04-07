package study.week8;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Main_파괴되지않은건물_60점 {
	public static void main(String[] args) {
		int[][] board = {{5,5,5,5,5},{5,5,5,5,5},{5,5,5,5,5},{5,5,5,5,5}};
		int[][] skill = {{1,0,0,3,4,4}, {1,2,0,2,3,2}, {2,1,0,3,1,2}, {1,0,1,3,3,1}};
		int a = solution(board, skill);
		System.out.println(a);
	}
	public static int solution(int[][] board, int[][] skill) {
        int answer = board.length * (board[0].length);
        Arrays.sort(skill,(o1, o2) ->(o1[0]!=o2[0]? o1[0]-o2[0] : o1[1]!=o2[1]? o1[1]-o2[1]: o1[2]-o2[2]));
        int idx = 0;
        Set<int[]> s = new HashSet<>(); 
        for (; idx < skill.length; idx++) {
        	if (skill[idx][0] == 2)break;
			for (int i = skill[idx][1]; i <= skill[idx][3]; i++) {
				for (int j = skill[idx][2]; j <= skill[idx][4]; j++) {
					int check = board[i][j];
					board[i][j] -= skill[idx][5];
					if (check > 0 && board[i][j] <= 0) {
						s.add(new int [] {i, j});
					}
				}
			}
		}
        for (int[] now : s) {
			for (int k = idx; k < skill.length; k++) {
				if (skill[k][1] <= now[0] && now[0] <= skill[k][3] 
						&& skill[k][2] <= now[1] && now[1] <= skill[k][4]) {
					board[now[0]][now[1]] += skill[k][5];
				}
				else if ((now[0] < skill[k][1])||(now[0] == skill[k][1] && now[1] < skill[k][2]))break;
				if (board[now[0]][now[1]] > 0)break;
			}
			if (board[now[0]][now[1]]<= 0)
				answer--;
		}
        return answer;
    }
}

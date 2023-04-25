package study.day0425;

public class Main_프로그래머스_자물쇠와열쇠_첫시도 {
	public static void main(String[] args) {
		System.out.println(Solution.solution(new int [][] {{0,0,0},{1,0,0},{0,1,1}}, new int [][] {{1,1,1},{1,1,0},{1,0,1}}));
		System.out.println(Solution.solution(new int [][] {{0,0,0},{0,1,0},{0,0,0}}, new int [][] {{1,1,1},{1,1,1},{1,1,1}}));
		System.out.println(Solution.solution(new int [][] {{0,0,0},{0,1,1},{0,0,1}}, new int [][] {{1,1,1},{1,0,0},{1,1,0}}));
		System.out.println(Solution.solution(new int [][] {{0,1,0},{1,1,0},{0,0,1}}, new int [][] {{1,0,1},{0,0,1},{1,1,1}}));
	}
}

class Solution {
    public static boolean solution(int[][] key, int[][] lock) {
        boolean answer = false;
        int M = key.length;
        int N = lock.length;
        int minR = 2147483647;
        int minC = 2147483647;
        int maxR = 0;
        int maxC = 0;
        for(int i = 0 ; i < N ; i++){
            for(int j = 0 ; j < N ; j++){
                if (lock[i][j] == 0){
                    if (i < minR) minR = i;
                    if (j < minC) minC = j;
                    if (i > maxR) maxR = i;
                    if (i > maxC) maxC = j;
                }
            }
        }
        if (maxR < minR || maxC < minC)
        	return true;
        int R = maxR - minR + 1;
        int C = maxC - minC + 1;
        int [][] checker = new int [R][C];
        for(int i = 0 ; i < R ; i++){
            for(int j = 0 ; j < C ; j++){
                checker[i][j] = lock[minR + i][minC + j];
            }
        }
        if (key.length < checker.length || key.length < checker[0].length)
            return false;
        for(int time = 0; time < 4;time++){
            R = checker.length;
            C = checker[0].length;
            for(int i = 0 ; i < M - R + 1 ; i++){
                for(int j = 0 ; j < M - C + 1 ; j++){
                    boolean check = true;
                    for(int r = 0 ; r < R; r++){
                        for(int c = 0 ; c < C; c++){
                            if ((checker[r][c] == 0 && key[i + r][j + c] != 1)||(checker[r][c] == 1 && key[i + r][j + c] != 0)){
                                check = false;
                                break;
                            }
                        }
                        if (!check)break;
                    }
                    if (check) {
                    	if (Lastcheck(key, i, j, time, minR, minC, maxR, maxC))
                    		return check;
                    }
                }
            }
            checker = spin(checker);
        }
        return answer;
    }
    private static boolean Lastcheck(int[][] key, int r, int c, int time, int minR, int minC, int maxR, int maxC) {
    	
		return false;
	}
	public static int [][] spin(int [][] checker){
        int R = checker.length;
        int C = checker[0].length;
        int [][] temp = new int [C][R];
        for(int i = 0 ; i < R ; i++){
            for(int j = 0 ; j < C ; j++){
                temp[j][R - 1 - i] = checker[i][j];
            }
        }
        return temp;
    }
}
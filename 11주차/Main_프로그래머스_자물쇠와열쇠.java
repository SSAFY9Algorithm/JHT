package study.day0425;
/**
 *
 * 첫 시도
 * 	lock의 비어있는 부분만 따로 뽑아서 계산
 * 	 => 그 외 부분에서 겹치는 1이 겹치는 경우 틀림(테케 23 번)
 * 		=> 함수작성이 복잡하다고 판단 포기!!
 * 2차 시도
 *  2*(M - 1) + N 전부 확인 솔직히 너무 비효율 적인 것 같음
 *
 */
public class Main_프로그래머스_자물쇠와열쇠 {
	public static void main(String[] args) {
		System.out.println(Solution1.solution(new int [][] {{0,0,0},{1,0,0},{0,1,1}}, new int [][] {{1,1,1},{1,1,0},{1,0,1}}));
		System.out.println(Solution1.solution(new int [][] {{0,0,0},{0,1,0},{0,0,0}}, new int [][] {{1,1,1},{1,1,1},{1,1,1}}));
		System.out.println(Solution1.solution(new int [][] {{0,0,0},{0,1,1},{0,0,1}}, new int [][] {{1,1,1},{1,0,0},{1,1,0}}));
		System.out.println(Solution1.solution(new int [][] {{0,1,0},{1,1,0},{0,0,1}}, new int [][] {{1,0,1},{0,0,1},{1,1,1}}));
		System.out.println(Solution1.solution(new int [][] {{1,1,1},{1,1,1},{1,1,1}}, new int [][] {{0,0,0},{0,0,0},{0,0,0}}));
		System.out.println(Solution1.solution(new int [][] {{1,1,1},{1,0,0},{1,0,1}}, new int [][] {{1,1,1},{1,0,1},{1,1,1}}));
	}
}

class Solution1 {
    public static boolean solution(int[][] key, int[][] lock) {
        int M = key.length;
        int N = lock.length;
        for (int time = 0; time < 4; time++) {
        	for (int i = 0; i < 2*(M - 1) + N; i++) {
				for (int j = 0; j < 2*(M - 1) + N; j++) {
					boolean check = true;
					a : for (int r = 0; r < N; r++) {
						for (int c = 0; c < N; c++) {
							int kr = M - i + r;
							int kc = M - j + c;
							if (0 <= kr && kr < M && 0 <= kc && kc < M) {
								if ((key[kr][kc] == 1 && lock[r][c] != 0) || (key[kr][kc] == 0 && lock[r][c] != 1)) {
									check = false;
									break a;
								}
							}else {
								if (lock[r][c] != 1) {
									check = false;
									break a;
								}
							}
						}
					}
					if (check)return true;
				}
			}
        	lock = spinMap(lock);
		}
        for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (lock[i][j] == 0)
					return false;
			}
		}
        return true;
    }
	public static int [][] spinMap(int [][] map){
        int R = map.length;
        int C = map[0].length;
        int [][] temp = new int [C][R];
        for(int i = 0 ; i < R ; i++){
            for(int j = 0 ; j < C ; j++){
                temp[j][R - 1 - i] = map[i][j];
            }
        }
        return temp;
    }
}
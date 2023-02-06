package study.DataStructure.Queue;
/**
 * 처음에 앵무새가 가진 단어 수와 받은 단어수 비교 안해도 되는 줄 알았음
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_14713_앵무새 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		String[][]birds = new String[N][];
		int wordNum = 0; //앵무새의 모든 단어의 수
		for (int i = 0; i < N; i++) {
			birds[i] = br.readLine().split(" ");
			wordNum += birds[i].length;
		}
		String[] L = br.readLine().split(" ");
		int [] birdsIdx = new int [N]; // 각 앵무새가 말할단어의 위치
		int words = 0;
		for (int i = 0; i < L.length; i++) {
			//모든 앵무새에대해 지금 말하는 단어가 필요단어와 같은지 판별
			for (int j = 0; j < N; j++) {
				//문장 다 읽었으면 비교 할 필요없음
				if (birdsIdx[j] < birds[j].length && L[i].equals(birds[j][birdsIdx[j]])) {
					birdsIdx[j]++;
					words++;
					break;
				}
			}
		}
		if (L.length == words && L.length == wordNum)
			System.out.println("Possible");
		else
			System.out.println("Impossible");
	}	
}

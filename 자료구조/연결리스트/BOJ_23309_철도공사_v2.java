package study.DataStructure.LinkedList;

/**
 *	ArrayList<> & LsinkedList<> 사용했을 때 시간초과 나왔음
 *	[2][1500000] 배열 사용
 *	0번 인덱스 - 이전역
 *	1번 인덱스 - 다음역
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_23309_철도공사_v2 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int [][] list = new int [2][1500000];
		st = new StringTokenizer(br.readLine());
		int first = Integer.parseInt(st.nextToken());
		int front = first;
		int a = -1;
		for (int i = 1; i < N; i++) {
			a = Integer.parseInt(st.nextToken());
			list[0][a] = front;
			list[1][front] = a;
			front = a;
		}
		list[0][first] = a;
		list[1][a] = first; 
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			String str = st.nextToken();
			if (str.equals("BN")){//i 다음역 출력후 사이에 j 설립
				int idx = Integer.parseInt(st.nextToken());
				int num = Integer.parseInt(st.nextToken());
				int next= list[1][idx];
				bw.write(next + "\n");
				list[0][num] = idx;
				list[1][num] = next;
				list[0][next] = num;
				list[1][idx] = num;
			}
			else if (str.equals("BP")){//i 이전역 출력후 사이에 j 설립
				int idx = Integer.parseInt(st.nextToken());
				int num = Integer.parseInt(st.nextToken());
				int pre = list[0][idx];
				bw.write(pre + "\n");
				list[0][num] = pre;
				list[1][num] = idx;
				list[1][pre] = num;
				list[0][idx] = num;
			}
			else if (str.equals("CN")){//i 다음역 폐쇄후 고유번호 출력
				int idx = Integer.parseInt(st.nextToken());
				int next= list[1][idx];
				int nextNext = list[1][next];
				list[1][idx] = nextNext;
				list[0][nextNext] = idx;
				bw.write(next + "\n");
			}
			else if (str.equals("CP")){//i 이전역 폐쇄후 고유번호 출력
				int idx = Integer.parseInt(st.nextToken());
				int pre = list[0][idx];
				int prePre = list[0][pre];
				list[1][prePre] = idx;
				list[0][idx] = prePre;
				bw.write(pre + "\n");
			}
		}
		bw.close();
	}
}

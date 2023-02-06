package study.DataStructure.LinkedList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_23309_철도공사 {
	static List<Integer> list = new ArrayList<Integer>();
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			list.add(Integer.parseInt(st.nextToken()));
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			String str = st.nextToken(); 
			if (str.equals("BN"))
				BN(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
			else if (str.equals("BP"))
				BP(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
			else if (str.equals("CN"))
				CN(Integer.parseInt(st.nextToken()));
			else if (str.equals("CP"))
				CP(Integer.parseInt(st.nextToken()));
		}
		bw.close();
	}

	private static void CP(int i) throws IOException {//i 이전역 폐쇄후 고유번호 출력
		int idx = list.indexOf(i) -1;
		if (idx == -1)
			idx = list.size() -1;
		bw.write(list.get(idx)+"\n");
		list.remove(idx);
	}

	private static void CN(int i) throws IOException {//i 다음역 폐쇄후 고유번호 출력
		int idx = list.indexOf(i) +1;
		if (idx == list.size())
			idx = 0;
		bw.write(list.get(idx)+"\n");
		list.remove(idx);
	}

	private static void BP(int i, int j) throws IOException {//i 이전역 출력후 사이에 j 설립
		int idx = list.indexOf(i) -1;
		if (idx == -1)
			idx = list.size() -1;
		bw.write(list.get(idx)+"\n");
		list.add(idx+1, j);
	}

	private static void BN(int i, int j) throws IOException {//i 다음역 출력후 사이에 j 설립
		int idx = list.indexOf(i) +1;
		if (idx == list.size())
			idx = 0;
		bw.write(list.get(idx)+"\n");
		list.add(idx, j);
	}
}
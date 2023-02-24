package study.day0213;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main_백준_2668_숫자고르기 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int [][] arr = new int [2][N];
		Node[] li = new Node[N];
		for (int i = 0; i < N; i++) {
			arr[0][i] = Integer.parseInt(br.readLine());
		}
		for (int i = 0; i < N; i++) {
			int start = i + 1;
			li[i] = new Node();
			li[i].cycle.add(start);
			arr[1][i] = 1;
			int check = arr[0][i];
			while (true) {
				if (arr[1][check -1] == 1)
					break;
				li[i].cycle.add(check);
				arr[1][check -1] = 1;
				check = arr[0][check -1];
			}
			if (check == start) {
				li[i].len = li[i].cycle.size();
			}
			else {
				li[i].len= 0;
				for (int j = 0; j < li[i].cycle.size(); j++) {
					arr[1][li[i].cycle.get(j)-1] = 0;
				}
			}
		}
		int ans = 0;
		List<Integer> nums = new ArrayList<>(); 
		for (int i = 0; i < N; i++) {
			if (li[i].len == 0) continue;
			ans += li[i].len;
			for (int j = 0; j < li[i].cycle.size(); j++) {
				nums.add(li[i].cycle.get(j));
			}
		}
		Collections.sort(nums);
		System.out.println(ans);
		for (Integer i : nums) {
			System.out.println(i);
		}
	}
}
class Node {
	int len = 0;
	List<Integer> cycle = new ArrayList<>();
}
package study.day0404;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class Main_프로그래머스_두큐합같게만들기 {
	public static void main(String[] args) {
		int [] queue1 = {1, 2, 1, 2};
		int [] queue2 = {1, 10, 1, 2};
//		int [] queue1 = {1,1};
//		int [] queue2 = {1,5};
//		int [] queue1 = {3,2,7,2};
//		int [] queue2 = {4,6,5,1};
		int a = Solution.solution(queue1, queue2);
		System.out.println(a);
	}
}
class Solution {
    public static int solution(int[] queue1, int[] queue2) {
    	long [] arr = new long [queue1.length + queue2.length];
    	int idx = 0;
    	int q1 = 0;
    	long q1Sum = 0;
    	long half = 0;
    	for (int i = 0; i < queue1.length; i++, idx++) {
			arr[idx] = queue1[i];
			q1Sum += queue1[i];
			half += queue1[i];
		}
    	int q2 = idx;
    	for (int i = 0; i < queue2.length; i++,idx++) {
    		arr[idx] = queue2[i];
    		half += queue2[i];
		}
    	if (half %2 != 0)
    		return -1;
    	half = half /2;
    	int time = 0;
    	while (half != q1Sum) {
    		if (half < q1Sum) {
    			q1Sum = q1Sum - arr[q1];
    			q1 = (q1+1)% arr.length;
    			if (q1 == 0) {
    				return -1;
    			}
			}
    		else {
    			q1Sum = q1Sum + arr[q2];
    			q2 = (q2+1)% arr.length;
			}
    		time++;
    	}
        return time;
    }
}
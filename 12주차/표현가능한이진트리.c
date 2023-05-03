#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>

int check(long long num, long long size){
	if (size == 1)
		return 1;
    long long h = size/2;
    long long L = num >> (h + 1);
    long long now = (num & (1 << h)); 
    long long R = num & ((1 << h) - 1);
    if (L == 0 && R == 0)
        return 1;
    else if (L == 0){
		if (now == 0)
			return 0;
        return check(R, h);
    }else if (R == 0){
		if (now == 0)
			return 0;
        return check(L, h);
    }else{
        if (now == 0)
            return 0;
        if (check(L, h) == 0 || check(R, h) == 0)
            return 0;
        return 1;
    }
}
long long getSize(long long num){
    long long ans = 0;
	long long temp = 1;
    while (ans != 63 && ((long long)1<<ans) <= num){
        ans += temp;
		temp *= 2;
    }
    return ans;
}
// numbers_len은 배열 numbers의 길이입니다.
int* solution(long long numbers[], size_t numbers_len) {
    // return 값은 malloc 등 동적 할당을 사용해주세요. 할당 길이는 상황에 맞게 변경해주세요.
    int* answer = (int*)malloc(numbers_len * sizeof(int));
    for(int i = 0 ; i < numbers_len ; i++){
        answer[i] = check(numbers[i], getSize(numbers[i]));
    }
    return answer;
}
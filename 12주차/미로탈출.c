#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>

int getMoveCnt(int x, int y, int r, int c){
    int a = x - r;
    int b = y - c;
    if (a < 0)
        a = -a;
    if (b < 0)
        b = -b;
    return a + b;
}
char* solution(int n, int m, int x, int y, int r, int c, int k) {
    // return 값은 malloc 등 동적 할당을 사용해주세요. 할당 길이는 상황에 맞게 변경해주세요.
    char* answer = (char*)malloc(k + 1);
    answer[k] = '\0';
    int dr = x - r;
    int dc = y - c;
    if (dr < 0)
        dr = -dr;
    if (dc < 0)
        dc = -dc;
    if ((k % 2 != (dr + dc) % 2) || k < (dr + dc))
        return "impossible";
    int idx = 0;
    while (getMoveCnt(x,y,r,c) != k){
        if (x < n){
            x +=  1;
            k -=1;
            answer[idx++] = 'd';
        }
        else if (1 < y){
            y -= 1;
            k -=1;
            answer[idx++] = 'l';
        }
        else{
            y += 1;
            k -=1;
            answer[idx++] = 'r';
        }
    }
    int a = x - r;
    int b = y - c;
    if (a < 0){
        for(int i = 0 ; i < -a ; i++)
            answer[idx++] = 'd';
    }
    if (b > 0){
        for(int i = 0 ; i < b ; i++)
            answer[idx++] = 'l';
    }
    if (b < 0){
        for(int i = 0 ; i < -b ; i++)
            answer[idx++] = 'r';
    }
    if (a > 0){
        for(int i = 0 ; i < a ; i++)
            answer[idx++] = 'u';
    }
    return answer;
}
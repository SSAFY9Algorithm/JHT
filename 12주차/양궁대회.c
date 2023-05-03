#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>

// info_len은 배열 info의 길이입니다.
int* solution(int n, int info[], size_t info_len) {
    // return 값은 malloc 등 동적 할당을 사용해주세요. 할당 길이는 상황에 맞게 변경해주세요.
    int* answer = (int*)malloc(1 * sizeof(int));
    answer[0] = -1;
    int dif = 0;
    int lowidx = 0;
    for(int i = 1 ; i < (1 << 10) ; i++){
        int leftArrow = n;
        int Rscore = 0;
        int Ascore = 0;
        int* temp = (int*)malloc(11 * sizeof(int));
        for(int j = 0 ; j < 10 ; j++){
            if ((i& (1 << j)) != 0 && info[j] + 1 <= leftArrow){
                temp[j] = info[j] + 1;
                leftArrow -= temp[j];
                Rscore += 10 - j;
            }else if (info[j] != 0){
                Ascore += 10 - j;
                temp[j] = 0;
            }else{
                temp[j] = 0;
            }
        }
        temp[10] = leftArrow;
        if (Rscore - Ascore > dif){
            dif = Rscore - Ascore;
            free(answer);
            answer = temp;
        }else if (dif != 0 && Rscore - Ascore == dif){
            int check = 0;
            for(int jht = 10 ; jht >= 0 ; jht--){
                if (temp[jht] < answer[jht]){
                    break;
                }else if (temp[jht] > answer[jht]){
                    check = 1;
                    break;
                }
            }
            if (check == 1){
                free(answer);
                answer = temp;
            }
        }else{
            free(temp);
        }
    }
    return answer;
}
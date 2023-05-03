#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>

int EC, UC;
int** usersInfo;
int* emoInfo;
int* answer;
void Per(int idx, int * arr){
    if (idx == EC){
        int plusTemp = 0;
        int priceTemp = 0;
        for(int i = 0 ; i < UC ; i++){
			int sum = 0;
            for(int j = 0 ; j < EC ; j++){
                if (usersInfo[i][0] <= arr[j])
                    sum += emoInfo[j] * (100 - arr[j]) / 100;
            }
			if (sum < usersInfo[i][1])
				priceTemp += sum;
			else
				plusTemp++;
        }
        if (answer[0] < plusTemp){
            answer[0] = plusTemp;
            answer[1] = priceTemp;
        }else if (answer[0] == plusTemp && answer[1] < priceTemp)
            answer[1] = priceTemp;
        return;
    }
    for(int i = 4 ; i > 0 ; i--){
        arr[idx] = i*10;
        Per(idx+1, arr);
    }
    return;
}

// users_rows는 2차원 배열 users의 행 길이, users_cols는 2차원 배열 users의 열 길이입니다.
// emoticons_len은 배열 emoticons의 길이입니다.
int* solution(int** users, size_t users_rows, size_t users_cols, int emoticons[], size_t emoticons_len) {
    // return 값은 malloc 등 동적 할당을 사용해주세요. 할당 길이는 상황에 맞게 변경해주세요.
    answer= (int*)malloc(2 * sizeof (int));
	answer[0] = -1;
    answer[1] = -1;
    EC = emoticons_len;
    UC = users_rows;
    usersInfo = users;
    emoInfo = (int*)emoticons;
    int sale[emoticons_len];
    Per(0,sale);
    return answer;
}
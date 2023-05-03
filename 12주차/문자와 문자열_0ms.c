#include <stdio.h>

int main() {
    char str[1000];
    int i;
    scanf("%s", str);
    scanf("%d", &i);
    printf("%c", str[i-1]);
    return 0;
}
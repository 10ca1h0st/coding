#include<stdio.h>

int main(int argc,char *argv[]){
    printf("hello world\n");
    if(argc > 1){
        printf(argv[1]);
        printf("\n");
    }
    char *a = "ni hao";
    printf(a);
    int b = 10;
    printf("\n");
    printf("%d",b);
    printf("\n");
    return 0;
}

#include <stdio.h>

char encrypt(char m,int a,int b);

int main(int argc,char* argv[]){
    char m;
    int a,b;
    printf("input two parameters(such as 1,2):");
    scanf("%d,%d",&a,&b);
    setbuf(stdin, NULL);
    printf("the parameters is:%d,%d\n",a,b);
    printf("input the plain(a~zA~Z):");
    //fflush(stdin);linux下不起作用
    scanf("%c",&m);
    setbuf(stdin, NULL);
    printf("the plain is:%c\n",m);
    char c = encrypt(m,a,b);
    printf("the secert is:%c\n",c);
    return 0;
}

char encrypt(char m,int a,int b){
    char c;
    if(m>=0x41 && m<=0x5A){
        c = a*(m-0x41)+b;
        c = c%26;
    }
    else if(m>=0x61 && m<=0x7A){
        c = a*(m-0x61)+b;
        c = c%26;
    }
    /**
    char c = a*m+b;
    c = c%26;
    */
    if(m>=0x41 && m<=0x5A){
        c = 0x41+c;
    }
    else if(m>=0x61 && m<=0x7A){
        c = 0x61+c;
    }
    return (char)c;
}
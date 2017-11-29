1:1.o 2.o
	gcc -o 1 1.o 2.o

1.o:1.c
	gcc -g -c 1.c

2.o:2.c 2.h
	gcc -g -c 2.c

.PHONY:clean
clean:
	rm 1 1.o 2.o 
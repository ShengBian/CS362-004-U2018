CFLAGS = -Wall -pedantic -Wextra -lm

all: testme
	./testme
	
testme: testme.c
	gcc -o testme testme.c $(CFLAGS)
	
clean:
	rm -rf testme

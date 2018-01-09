;Systems and Networks Assessed Exercise

;The program takes in:
;	- An integer value n, assume n > 0.
;	- An array X of length n.
;It will calculate:
; 	- Possum: Sum of positive values in array X.
; 	- Negcount: The count of negative numbers in array.
;	- oddcount: The count of positive odd numbers in the array.

;Psuedo-HLL program:
;	n=12;
;	X = {3, -6, 27, 101, 50, 0, -20, -21, 19, 6, 4, -10};
;	for( i=0 ; i<n ; i++ )
;		if (X[i] >= 0)
;			possum+=X[i]
;			if(X[i] % 2 != 0)
;				oddcount++
;			else 
;				negcount++

; Register usage
;	R1 = constant 1
;	R2 = n
;	R3 = i
;	R4 = X[i]
;	R5 = possum
;	R6 = oddcount
;	R7 = negcount
;	R8 = check ( i < n )
;	R9 = check ( X[i] < 0 ) Check negative
;	R10= check ( X[i] == ODD ) := Bitwise AND
;	R11= check ( X[i] == ODD ) := Boolean

; Initialise

	LEA	R1,1[R0]	; R1 = constant 1
	LOAD	R2,n[R0]	; R2 = n
	LEA	R3,0[R0]	; R3 = i = 0
	LOAD	R4,X[R0]	; R4 = X[0]
	LOAD	R5,possum[R0]	; R5 = possum = 0
	LOAD	R6,ocount[R0]	; R6 = oddcount = 0
	LOAD	R7,ncount[R0]	; R7 = negcount = 0

;Top of FOR loop needs to check to remain in loop

floop	CMPLT	R8,R3,R2	;R8 = ( i < n )
	JUMPF	R8,end[R0]	;if i>n goto end

;IF (X[i] >= 0) 

ifpos	LOAD	R4,X[R3]	;R4 = X[i]
	CMPLT	R9,R4,R0	;R9 = ( X[i] < 0 )
	JUMPT	R9,else[R0]	;jump to else if negative

;then possum += X[i]

	ADD	R5,R5,R4	;R5 = possum+X[i]

;IF ( X[i] == ODD )


	AND	R10,R4,R1	;R10 = bitwise AND on X[i] and constant 1
	CMPEQ	R11,R10,R1	;R11 = (R10 == 1)
	JUMPF	R11,incr[R0]	;if X[i] even goto bottom of loop

;then increment oddcount

	ADD	R6,R6,R1	;oddcount++
	JUMP	incr[R0]	;goto bottom of loop, always skip else from here

;ELSE (on first IF) incremenent negcount

else	ADD	R7,R7,R1	;negcount++ 

;no goto bottom needed after negcount++ as else is last operation before bottom

;Bottom of loop, increment i and goto top of loop

incr	ADD	R3,R3,R1	;i++
	JUMP	floop[R0]	;goto top of for loop

;Exit from the for loop
end	STORE	R5,possum[R0]	;possum = R5
	STORE	R6,ocount[R0]	;oddcount = R6
	STORE	R7,ncount[R0]	;negcount = R7
	TRAP	R0,R0,R0	;terminate program

;Set Data values
n	DATA	12	;n = 12
X	DATA	3;	;X[0]  = 3
	DATA	-6	;X[1]  = -6
	DATA	27	;X[2]  = 27
	DATA	101	;X[3]  = 101
	DATA	50	;X[4]  = 50
	DATA	0	;X[5]  = 0
	DATA	-20	;X[6]  = -20
	DATA	-21	;X[7]  = -21
	DATA	19	;X[8]  = 19
	DATA	6	;X[9]  = 6
	DATA	4	;X[10] =4
	DATA	-10	;X[11] =-10
possum	DATA	0	;initialise sum of positives to zero
ocount	DATA	0	;initialise oddcount to zero
ncount	DATA	0	;initialise negcount to zero

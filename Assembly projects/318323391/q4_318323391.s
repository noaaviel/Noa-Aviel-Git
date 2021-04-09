# Write your ID here: 318323391

# Computer Architecture 
#Question 4(25%)    	 
	  	
################# Data segment #####################
.data
array:	 .byte  -12,15,15,14,98,0,3,8
array1: .byte '=','1','2'
str:      .space 8
################# Code segment #########################

.text	
.globl main
main:	
	la $a1,array
	li $t0,0 #counter for loop
	la $a3,array1
	la $a2,str
loop:
	lb $t1,0($a1) # loading to t1 the num that is in the index t0
	lb $t2,1($a1)
	sub $t3,$t1,$t2 #$t3=t1-t2
	beqz $t3,insert_even 
	andi $t3,1 #check if even or odd
	beqz $t3,insert_two
	bnez $t3,insert_one
	
insert_even:
	lb $t1,0($a3)
	sb $t1,str($t0)
	jal counter_plusplus
	beq $t0,7,exit
	j loop
insert_one:
	lb $t1,1($a3)
	sb $t1,str($t0)
	jal counter_plusplus
	beq $t0,7,exit
	j loop
insert_two:
	lb $t1,2($a3)
	sb $t1,str($t0)
	jal counter_plusplus
	beq $t0,7,exit
	j loop
counter_plusplus:
	addi $t0,$t0,1
	addi $a1,$a1,1
	addi $a2,$a2,1
	jr $ra
exit:
	la $a0 ,str
	li $v0 ,4
	syscall
	li $v0,10
	syscall

	

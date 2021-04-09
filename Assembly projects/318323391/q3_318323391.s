# Write your ID here: 318323391

# Computer Architecture 
#Question 3(25%)       	 
# Input: array of 8 integer (word) sign number 
# Output: 1)save to array1 the opposite number 	(neg-->pos pos-->neg) 
# if the number divide by 4 than save the opposite divide by 4 number
#print (from left to right) the even posotive numbers of array1  	
################# Data segment #####################
.data
array: .word -4,300,-600,-750,1270,-200,800,900
array1: .word 0:8  #8 word for save
msg1:	.asciiz "\n The positive even numbers of array1:\n "
msg2: 	.asciiz " "
################# Code segment #####################
.text
.globl main
main:	# main program entry
#answer	 
################################################### 
	li $t2,-1
	li $t0,0
	la $a1,array # in $a1 there are the array
	la $a2,array1
	li $t4,4
	la $a0,msg1
	li $v0,4
	syscall	
loop:
	addi $t0,$t0,1
	lw $t1,0($a1) # loading to t1 the num that is in the index t0
	mult $t1,$t2 #$t1=$t1*(-1)
	mflo $t1
	move $t3,$t1 #t3=t1
	andi $t3,3 #cheack if divided by 4 ==>  modulus 4
	beq $t3 , 0 ,div4 # if he is divide by 4!
	sw $t1 ,0($a2)
	addi $a1,$a1,4
	addi $a2,$a2,4
	beq $t0,8,mission_2
	j loop 	
div4:
	div $t1,$t4 
	mflo $t1
	sw $t1 ,0($a2)
	addi $a1,$a1,4
	addi $a2,$a2,4
	beq $t0,8,mission_2
	j loop
mission_2:
	li $t0,0 #index counter
	la $a2,array1 #loading array to index 0
for_loop_for_printing_positives:
	
	lw $t1,0($a2)
	move $t4,$t1
	jal check_pos
	addi $t0,$t0,1
	addi $a2,$a2,4
	beq $t0,8,exit
	j for_loop_for_printing_positives
print:
	addi $t0,$t0,1
	addi $a2,$a2,4
	move $a0 ,$t1
	li $v0,1
	syscall
	la $a0,msg2
	li $v0,4
	syscall
	beq $t0,8,exit
	j for_loop_for_printing_positives	
# end of program
exit:	
	li $v0,10
	syscall
check_pos:
	#andi $t4,0x10000000 # check if positive
	bgt $t4,0, check_even
	#beq $t4,0,check_even
	jr $ra
check_even:
	move $t4,$t1
	andi $t4,1 #check if even == > modulus 2
	beq $t4,0,print
	jr $ra
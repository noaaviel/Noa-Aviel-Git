#Bar Mizrahi : 316080365
#Noa Aviel: 318323391 <3
.data

str: .space 33
char: .byte ' '
msg1: .asciiz "\nPlease enter a string with max length of 32 charactes:\n"
msg2: .asciiz "\n The max sequence of a-z in the string is:\n"
msg3: .asciiz "\n Please enter a char to look for in the string:\n"
msg4: .asciiz "\nPlease enter X (1-9): \n"
msg5: .asciiz "\nThe string after reduction in reverse is:\n"
.text

.globl main
	
main:
#part a:
	li $v0,4 
	la $a0,msg1
	syscall

	#getting string from user
	li $a1, 33
	la $a0,str
	li $v0 ,8
	syscall	

	la $a0, msg2
	li $v0,4
	syscall
	
	la $a1,str
	li $a0,0 #str counter was t0
	li $a2,1 #seq counter--> correct (MAX) counter was t3
	li $v0,0 #seq counter --> comperator was t4


	jal count_abc
	
	move $a0,$v0
	li $v0,1
	syscall

#part b:
	la $a0, msg3
	li $v0,4
	syscall
	
	li $a0,0 #loop counter 
	li $v0,12 
	syscall
	move $t0,$v0 #$t0 = user's char input 
	li $a2,0 #char counter 
	la $a1,str

	jal count_char
	
	move $a0,$a2
	li $v0,1
	syscall

#part c:
	la $a0, msg4
	li $v0,4
	syscall
	
	li $v0,5 #read int from user
	syscall
	move $a1,$v0 #a1 contains integer from user - X

	la $a0,str #a0 =str from user
	#insert ' ' , delete spacing 
	
	li $a2,0 #counter
	
	jal delete
	
	li $v0,4
	la $a0, str
	syscall

#part d:
	li $v0,4
	la $a0, msg5
	syscall
	
	la $a2,str #a2 =str
	li $a1,32 #a1 counter reverse
	add $a2,$a2,$a1 #go to end of str

	li $v0,11
	jal reverse
	
	li $v0,10 #exit program
        syscall
#end of main

count_abc:
	addi $a0,$a0,1
	lb $t1,0($a1)
	lb $t2,1($a1)

#check if in range a-z
	blt $t1,97,cut_count
	bgt $t1,122,cut_count
	blt $t2,97,cut_count
	bgt $t2,122,cut_count
	
	sub $t1,$t2,$t1
	beq $t1,1,counter_up
	j cut_count

cut_count:
	beq $a0,33,exit
	addi $a1,$a1,1
	bgt $a2,$v0,change_counter
	j count_abc

change_counter:
	move $v0,$a2
	li $a2,1
	j count_abc	

counter_up:
	beq $a0,33,exit
	addi $a2,$a2,1	
	addi $a1,$a1,1
	beq $a0,33,exit
	j count_abc

#part b
count_char:
	#$t0 = user's char input $a0 loop counter $a1 str $a2 char counter
	addi $a0,$a0,1 #loop counter ++
	lb $t1,0($a1) #load byte of str to t1

	beq $t1,$t0,counter_up_char #if user input == char->++char counter
	beq $a0,33,exit
	addi $a1,$a1,1 #str++

	j count_char

counter_up_char:
	beq $a0,33,exit #counter end of str
	addi $a2,$a2,1 #char counter ++
	addi $a1,$a1,1 #str ++
	j count_char


#part c:
delete: #a1-> int from user  a0 -> str a2-> loop counter
	#check if in range
	bgt $a1,9,exit
	blt $a1,1,exit
	
insert_spaces:
	add $a0,$a0,$a1  #set a0 to first index of x mult

	addi $a0,$a0,-1 #fix index because we start at index 0--> x-1
	addi $a2,$a2,1 #counter ++

	lb $t0,char #loading ' ' into t0 
	sb  $t0,($a0)#loading ' ' into string at index X or mult by X
	addi $a0,$a0,1

	beq $a2,33,spaced_string #end of string --> exit
	
	j insert_spaces
	#here we have a string with spaces 
	
spaced_string: #moving str to new_str 
	la $a0,str #moving cursor of str to start of spaced string a0 was s0
	li $a2,0 #restarting counter to 0 

	lb $s1,($a0)   #set first char from str to $s1
	li $s3, 0   #space counter

compare:
	#t0= ' '
	#is char == space?
	beq $a2,33,exit
	beq $s1, $t0, space #if s1 is a space move on
	bne $s1, $t0, save  #if s1 is a character save that in the stack

save:
	#save the new string
	sub $s4, $a0, $s3 #str-spacecounter
	sb $s1,($s4) #store byte in s1 to s4
	j step

space: 
	addi $s3, $s3, 1 #add 1 if space

step:
	addi $a2,$a2,1
	addi $a0,$a0, 1 #increment first string array str++
	lb $s1,($a0) #load incremented value load str++
	j compare
	#in $s4 --> pure string without spaces

#part d:
reverse:
	#a2 =str #a1 counter reverse	
	lb  $a0,0($a2) #load last byte

	syscall
	
	addi $a2,$a2,-1 #str -- 
	addi $a1,$a1,-1 #loop counter --
	beq $a1,-1,exit #out
	j reverse
	
exit:
	jr $ra

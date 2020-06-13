.data #for data

promp: .asciiz "Enter Your Name "
name: .space 20
message: .asciiz"\n Hello " 


.text #for instructions 

#promp user to enter their name 
li $v0, 4 
la $a0, promp
syscall

#get the users name 
li $v0, 8
la $a0, name
li $a1, 20
syscall
move $t0, $v0

#prints message first
li $v0, 4
la $a0, message
syscall

#print name
li $v0, 4
la $a0, name
syscall
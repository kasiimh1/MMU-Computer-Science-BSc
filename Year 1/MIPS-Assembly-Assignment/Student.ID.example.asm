.data #for data

promp: .asciiz "Enter Your Student ID "
message: .asciiz"Your Student ID Number Is :  " 

.text #for instructions 


#promp user to enter their ID 
li $v0, 4 
la $a0, promp
syscall

#get the users ID number 
li $v0, 5
syscall

move $t0, $v0

li $v0, 4
la $a0, message
syscall

# print ID number
li $v0, 1
move $a0, $t0
syscall

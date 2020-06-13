.data #for data

promp: .asciiz "\n Enter Your Student ID "
message: .asciiz"\n Your Student ID Number Is :  " 
time: .asciiz "\n start time:"
.text #for instructions 

.text
li $v0, 4
la $a0, promp
syscall

li $v0, 5
syscall

move $t0, $v0

add $t1, $zero, $t0

mult $t0, $t1
li $v0, 1
mflo $t4
move $a0, $t4
syscall




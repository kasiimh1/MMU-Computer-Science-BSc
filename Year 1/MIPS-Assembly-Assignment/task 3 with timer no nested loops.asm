.data #for data

promp: .asciiz "\n Enter Your Student ID "
message: .asciiz"\n Your answer is : " 
time: .asciiz "\n Task completed in "
milliseconds: .asciiz " milliseconds! "
seconds: .asciiz " seconds! "
.text #for instructions 

### goes here 
li $v0, 4
la $a0, promp
syscall

#add here so we start timer after input 
li $v0,30        
syscall
move $t5, $a0
syscall

li $v0, 5
syscall

move $t0, $v0
add $t1, $zero, $t0
mult $t0, $t1

addi $v0, $zero, 4  # print_string syscall
la $a0, message   # load address string that will print the number to a new line
syscall #let system call the specified value in $v0

li $v0, 1
mflo $t4
move $a0, $t4
syscall
## end here##

#add here so we start timer after input 
li $v0,30        
syscall
move $t6, $a0
syscall

sub $t3, $t6, $t5

#new line
li $v0,4  # print_string syscall
la $a0, time  # load address string that will print the number to a new line
syscall #let system call the specified value in $v0

li $v0, 1
move $a0, $t3 
syscall

li $v0, 4  # print_string syscall
la $a0, milliseconds   # load address string that will print the number to a new line
syscall #let system call the specified value in $v0

addi $t7, $zero, 1000
div $t2, $t3, $t7

#new line
li $v0,4  # print_string syscall
la $a0, time  # load address string that will print the number to a new line
syscall #let system call the specified value in $v0

li $v0, 1 
move $a0, $t2 
syscall

li $v0, 4  # print_string syscall
la $a0, seconds   # load address string that will print the number to a new line
syscall #let system call the specified value in $v0

#end the program by loading the exit service code
li $v0, 10
syscall #let system call the specified value in $v0

.data #for data

spaceit: .asciiz "\n "

time: .asciiz "\n Task completed in "
milliseconds: .asciiz " milliseconds! "
.text #for instructions 

#add here so we start timer after input 
li $v0,30        
syscall
move $t5, $a0
syscall

### goes here 



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

#end the program by loading the exit service code
li $v0, 10
syscall #let system call the specified value in $v0

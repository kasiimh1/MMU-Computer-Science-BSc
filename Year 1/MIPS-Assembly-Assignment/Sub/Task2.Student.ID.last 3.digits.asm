.data #for data

promp: .asciiz "Enter the last 3 digits of your Student ID number: "
message: .asciiz"\n Your answer is: " 
spaceit: .asciiz "\n - "
.text #for instructions 

#promp user to enter their ID 
li $v0, 4 
la $a0, promp
syscall #let system call the specified value in $v0

#get the users ID number 
li $v0, 5
syscall #let system call the specified value in $v0
#move the inputted number to a temp register
move $t0, $v0
li $v0, 4
la $a0, message
syscall #let system call the specified value in $v0

# add 1 to the inputted number
add $t1, $t0, 1
# divide the number by 2
div $t2, $t1, 2

# print ID number
li $v0, 1
move $a0, $t2
syscall #let system call the specified value in $v0

addi $t6,$zero, 5 #set loop to 6 times 

loop: #looping the printing of the number 
addi $v0, $zero, 4  # print_string syscall
la $a0, spaceit   # load address string that will print the number to a new line
syscall #let system call the specified value in $v0

li $v0, 1
move $a0, $t2
syscall #let system call the specified value in $v0

addi $t6, $t6, -1 #minus one for everytime the loop is run

bgez $t6, loop  #loop if the condition is met 

#end the program by loading the exit service code
li $v0, 10
syscall #let system call the specified value in $v0

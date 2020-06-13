.data #items stored for use later

promp: .asciiz "\n Enter Your Name " #tell user to enter their name
promp1: .asciiz "\n Enter Your Student ID " #tell user to enter ID
name: .space 10 #reserve a set number of bytes
message: .asciiz"\n Hello " #greeting + user's name
message1: .asciiz" \n Your Name Is - " #print users name
message2: .asciiz" \n Your Student ID Is - " #print users ID 

.text #for instructions

#promp user to enter their name
li $v0, 4 #load the service call (print promp string to tell user to input their name)
la $a0, promp #load address in arg0 with the contents of promp defined in .data when we run syscall
syscall #get the system to execute the above service call

#get the users name
li $v0, 8 #load the service call (read users name string) this reads what the user just inputted for their name
la $a0, name #load address in arg0 with the contents of name defined in .data when we run syscall
li $a1, 11 # sets character limit that can be inputted to 10 characters for the inputted name
syscall #get the system to execute the above service call

#print message
li $v0, 4 #load the service call (print greeting message string)
la $a0, message #load address in arg0 with the contents of message defined in .data when we run syscall
syscall #get the system to execute the above service call

#print name
li $v0, 4 #load the service call (print users name string)
la $a0, name #reserve allocated bytes for printing the users name in arg0
syscall #get the system to execute the above service call

#promp user to enter their ID
li $v0, 4 #load the service call (print promp1 to tell the user to enter their ID number)
la $a0, promp1 #load address in arg0 with the contents of promp1 defined in .data when we run syscall
syscall #get the system to execute the above service call

#get the users ID number
li $v0, 5 #load the service call (read int that user just inputted)
syscall #get the system to execute the above service call
move $t0, $v0 #moves what's in $v0 to $t0

#print message
li $v0, 4 #load the service call (print users name message string)
la $a0, message1 #load address in arg0 with the contents of message1 defined in .data when we run syscall
syscall #get the system to execute the above service call

#print name
li $v0, 4 #load the service call (print users name string)
la $a0, name #load address in arg0 with the contents of name defined in .data when we run syscall
syscall #get the system to execute the above service call

#print message
li $v0, 4 #load the service call (print users ID message string)
la $a0, message2 #load address in arg0 with the contents of message2 defined in .data when we run syscall
syscall #get the system to execute the above service call

# print ID number
li $v0, 1 #load the service call (print users ID as int)
move $a0, $t0 #moves the stored int ID number to arg0 to print when we run syscall
syscall #get the system to execute the above service call

#end the program by loading the exit service code
li $v0, 10 #load service call to terminate the program
syscall #get the system to execute the above service call

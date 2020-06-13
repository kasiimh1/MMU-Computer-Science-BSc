.data #for data

promp: .asciiz "\nEnter Your Student ID "
message: .asciiz"\n\nYour computed value is : "
time: .asciiz "\n \nTask completed in "
milliseconds: .asciiz " milliseconds! "
seconds: .asciiz " seconds! "
txtsetup: .asciiz "\nSetup has been run! \n Getting ready to run the loops (:"
loops: .asciiz "\n \nLoops ran - "
.text #for instructions go here

li $v0, 4
la $a0, promp
syscall

li $v0, 5
syscall

move $t0, $v0 #this is the number that we will times and replace with the number that we just multiplied within the same temp register

move $t7, $v0 #move inputted for use later when outputting the result

#start timer after input
li $v0,30
syscall
move $t5, $a0
syscall

#setup
addi $t6, $zero, 4 # multiplied number bigger number here results in a slower time
mult $t0,$t6
mflo $t0
mflo $t1

li $v0, 4
la $a0, txtsetup
syscall

	loop1:
			addi $t6, $zero, 4 # multiplied number bigger number here results in a slower time
			addi $t1, $t1, -1 #minus one for everytime the loop is run
			bgez $t1, loop1  #loop if the condition is met
			#needs to multi after otherwise the number just increases everytime the loop is called again
			mult $t0,$t6
			mflo $t0
			mflo $t1

		loop2:
				addi $t6, $zero, 4 # multiplied number bigger number here results in a slower time
				addi $t1, $t1, -1 #minus one for everytime the loop is run
				bgez $t1, loop2  #loop if the condition is met
				#needs to multi after otherwise the number just increases everytime the loop is called again
				mult $t0,$t6
				mflo $t0
				mflo $t1

			loop3:
					addi $t6, $zero, 4 # multiplied number bigger number here results in a slower time
					addi $t1, $t1, -1 #minus one for everytime the loop is run
					bgez $t1, loop3  #loop if the condition is met
					#needs to multi after otherwise the number just increases everytime the loop is called again
					mult $t0,$t6
					mflo $t0
					mflo $t1

				loop4:
						addi $t6, $zero, 4 # multiplied number bigger number here results in a slower time
						addi $t1, $t1, -1 #minus one for everytime the loop is run
						bgez $t1, loop4  #loop if the condition is met
						#needs to multi after otherwise the number just increases everytime the loop is called again
						mult $t0,$t6
						mflo $t0
						mflo $t1

					loop5:
							addi $t6, $zero, 4 # multiplied number bigger number here results in a slower time
							addi $t1, $t1, -1 #minus one for everytime the loop is run
							bgez $t1, loop5  #loop if the condition is met
							#needs to multi after otherwise the number just increases everytime the loop is called again
							mult $t0,$t6
							mflo $t0
							mflo $t1

						loop6:
								addi $t6, $zero, 4 # multiplied number bigger number here results in a slower time
								addi $t1, $t1, -1 #minus one for everytime the loop is run
								bgez $t1, loop6  #loop if the condition is met
								#needs to multi after otherwise the number just increases everytime the loop is called again
								mult $t0,$t6
								mflo $t0
								mflo $t1

							loop7:
									addi $t6, $zero, 4 # multiplied number bigger number here results in a slower time
									addi $t1, $t1, -1 #minus one for everytime the loop is run
									bgez $t1, loop7  #loop if the condition is met
									#needs to multi after otherwise the number just increases everytime the loop is called again
									mult $t0,$t6
									mflo $t0
									mflo $t1
								
								nSquareInput:
										mult $t7,$t7
										mflo $t7
										j end

end:

#stop timer here as calculations are done running!
li $v0,30
syscall
move $t6, $a0
syscall

sub $t3, $t6, $t5

addi $v0, $zero, 4  # print_string syscall
la $a0, message   # load address string that will print the number to a new line
syscall #let system call the specified value in $v0

li $v0, 1
#mflo $t7
move $a0, $t7
syscall
## end here##

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

addi $t7, $zero, 1000 #divide by 1000 to convert milliseconds -> seconds
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

#print loops done
li $v0, 4  # print_string syscall
la $a0, loops   # load address string that will print the number to a new line
syscall #let system call the specified value in $v0

#print loops int
li $v0, 1
move $a0, $t0
syscall 


#end the program by loading the exit service code
li $v0, 10
syscall #let system call the specified value in $v0

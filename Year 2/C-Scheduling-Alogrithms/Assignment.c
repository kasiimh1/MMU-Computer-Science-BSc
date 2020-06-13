#include <stdio.h>  // import standard io header file
#include <stdlib.h> //import standard lib header file

#define n 6 // define n to be used for the array's size / length

int i, j, temp, pos, option, qt; //i, j, temp, position and option, quantum time are all set as type int

float awt, atat; //average wait time, average turn around time

int ct[n], tat[n], wt[n], rt[n]; //completed time, turn around time, wait time, remaining time

int pid[n];                        //set pid size to that of n
int at[6] = {3,1,2,0,6,8}; //arrived time
int bt[6] = {4,5,20,25,14,6}; //burst time

/* 
--Data set from lab FCFS
    int at[n] = {0, 1, 2, 3, 5}; //arrived time
    int bt[n] = {4, 3, 1, 2, 5}; //burst time

--Data set from lab SJF
    int at[n] = {1, 2, 3, 4, 5}; //arrived time
    int bt[n] = {7, 5, 1, 2, 10}; //burst time

--Data set 1 from assignment brief
    int at[6] = {0,1,2,3,4,6}; //arrived time
    int bt[6] = {3,6,8,25,5,20}; //burst time

--Data set 2 from assignment brief
    int at[6] = {0,1,2,3,4,6}; //arrived time
    int bt[6] = {25,20,3,8,6,5}; //burst time

--Data set 3 from assignment brief
    int at[6] = {3,1,2,0,6,8}; //arrived time
    int bt[6] = {4,5,20,25,14,6}; //burst time
*/

void sort()
{
    for (i = 0; i < n; i++)
        pid[i] = i + 1; //pid starts at 1 and last value is n + 1

    /* we need to do some sort of sorting method on the burst time
    i will use the one from my C# portfolio but had converted it to C code        
    the following code sorts the arrival times and burst times depending on the case in use*/

    /* this sorting algorithm takes a parameter (arrival time or burst time)
       then compares them in pairs if the ajacent pair is smaller it swaps it,
       so then the sorted set of values go from smallest to largest */

    switch (option) // use the same user inputted option to call the right sorting method
    {
    case 1:
    {
        /* sort the arrival times */
        for (i = 0; i < n; i++)
        {
            pos = i;
            for (j = i + 1; j < n; j++)
            {
                /* if the ajacent pair is smaller it swaps it */
                if (at[j] < at[pos])
                    pos = j;
            }

            /* swap the arrival times */
            temp = at[i];
            at[i] = at[pos];
            at[pos] = temp;

            /* swap the burst times */
            temp = bt[i];
            bt[i] = bt[pos];
            bt[pos] = temp;
        }
        break;
    }
    case 2:
    {
        /* sort the burst times */
        for (i = 1; i < n; i++)
        {
            pos = i;
            for (j = i + 1; j < n; j++)
            {
                /* if the ajacent pair is smaller it swaps it */
                if (bt[j] < bt[pos])
                    pos = j;
            }

            /* swap the arrival times */
            temp = at[i];
            at[i] = at[pos];
            at[pos] = temp;

            /* swap the burst times */
            temp = bt[i];
            bt[i] = bt[pos];
            bt[pos] = temp;

            /* swap the pid times */
            temp = pid[i];
            pid[i] = pid[pos];
            pid[pos] = temp;
        }
        break;
    }
    }
}

void calculate()
{
    for (i = 0; i < n; i++)
    {
        pid[i] = i + 1; //pid starts at 1 and last value is n + 1
        
        /* set the wait time of first item in the array to 0 */
        wt[0] = 0;

        /* set the completed time for first item in the array, because we cannot do ct[i-1] */
        if (i == 0)
            ct[0] = bt[i] + at[i];

        /* we use the completed time of i - 1 so here it starts with the first
           item in the array and += the burst time to the current value of ct[i] */
        if (i > 0)
        {
            ct[i] = ct[i - 1] + bt[i];
        }

        /* compute the turn around time and wait time */
        tat[i] = ct[i] - at[i];
        wt[i] = tat[i] - bt[i];

        /* set wait time to 0 if it's less than 0, since there's no negative wait time, it just would be 0 */
        if (wt[i] < 0)
        {
            wt[i] = 0;
        }

        /* compute the average turn around time and average wait time */
        awt += wt[i];
        atat += tat[i];
    }

    /* calculate the average turn around time and average wait time by dividing by the amount of process we defined */
    atat /= n;
    awt /= n;
}
/* method is incomplete, but a start */
void roundRobin()
{
    wt[0] = 0; //first items wait time is 0
    atat = tat[0] = bt[0];
    int btt = bt[0]; //to store total burst time sum

    for (int i = 0; i < n; i++)
    {
        if (bt[i] > qt && bt[i] > 0)
        {
            wt[i] = btt - at[i];
            btt += bt[i];
            awt += wt[i];
            tat[i] = wt[i] + bt[i];
            atat += tat[i];
        }

        if (bt[i] <= qt)
        {
            bt[i] = 0;
        }

        else
            bt[i] -= qt;
        /* we need to keep iterating over this for loop until every bt[i] = 0; */
    }
    atat /= n;
    awt /= n;
}

void display()
{
    printf("\tPID\tAT\tBT\tCT\tTAT\tWT\n");
    for (i = 0; i < n; i++)
    {
        printf("[*]\t%3d\t%3d\t%3d\t%3d\t%3d\t%3d\n", pid[i], at[i], bt[i], ct[i], tat[i], wt[i]);
    }
    printf("\nAverage Turn Around Time: %f\nAverage Wait Time: %f", atat, awt);
}

void menu()
{
    printf("\nScheduling Algorithms 2CWK40\nCreated by Kasim Hussain 15078165\n");
    printf("\n[1] - First Come First Served (FCFS) Scheduler");
    printf("\n[2] - Shortest Job First (SJF) Scheduler");
    printf("\n[3] - Round Robin (RR) Scheduler");
    printf("\n[4] - Quit\n");
    printf("\nWhich option do you want to run? > ");
    scanf("%i", &option);

    if (option == 1)
    {
        printf("\nRunning First Come First Served (FCFS) Scheduler\n");
        sort(); // run the correct sorting method that matches option
        calculate();  // call first come first serve method
        display();    //display the results table
    }
    if (option == 2)
    {
        printf("\nRunning Shortest Job First (SJF) Scheduler\n");
        sort(); // run the correct sorting method that matches option
        calculate();  // call shortest job first method
        display();    //display the results table
    }
    if (option == 3)
    {
        printf("\nRunning Round Robin (RR) Scheduler\n");
        printf("\nEnter a Time Quantum > ");
        scanf("%i", &qt);                                               //let the user input the desired quantum time
        printf("\nUsing Time Quantum of %i that user specified\n", qt); // print out qt that user provided
        roundRobin();
        display();
    }
    if (option == 4)
    {
        printf("\nExiting the program\n");
        exit(0); //exit the program
    }
    if (option > 4 || option < 1)
    {
        printf("\nYou didn't enter a valid option");
    }
}

int main()
{
    menu(); //load the menu screen
    printf("\n");
    return 0;
}

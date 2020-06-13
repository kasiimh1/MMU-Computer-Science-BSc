//
//  main.cpp
//  Week19 Portfolio
//
//  Created by Kasim Hussain on 06/03/2019.
//  Copyright © 2019 Kasim Hussain. All rights reserved.
//

#include <iostream>

void substitute(char c1, char c2, char *ptr)
{
    for (int i = 0; i < *ptr; i++)
    {
        if (*ptr == c1)
        {
            //set c2 to x
            *ptr = c2;
        }
        ptr++;
    }
}

int main()
{
    char myArray[] = "label";
    char *ptr;
    ptr = &myArray[0];
    
    char ch1 = 'a';
    char ch2 = 'x';
    //print array to check current contents
    std::cout << myArray << std::endl;
    //call method to change value a in string
    substitute(ch1, ch2, myArray);
    //print array to check if contents has updated  when found 'a' and replaced with 'x'
    std::cout << myArray << std::endl;
}

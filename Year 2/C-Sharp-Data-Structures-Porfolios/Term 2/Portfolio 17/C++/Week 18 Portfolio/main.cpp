//
//  main.cpp
//  Week 18 Portfolio
//
//  Created by Kasim Hussain on 21/02/2019.
//  Copyright © 2019 Kasim Hussain. All rights reserved.
//

#include <iostream>

using namespace std;

int main() {
    
    int input = 0;
    float num1 = 0;
    float num2 = 1;
    float output = num1 + num2;
    
    cout << "Set x to print out the first x Fibonacci numbers in the sequence ";
    cin >> input;
    
    for (int i = 1; i < input; i++)
    {
        cout << output << ", ";
        output = num1 + num2;
        num1 = num2;
        num2 = output;
    }
    
    input = 0;
    num1 = 0;
    num2 = 1;
    output = num1 + num2;
    
    cout << "\n\nPrint Fibonacci numbers less than ";
    cin >> input;
 
    cout << "Printing Fibonacci numbers that are less than " << input << endl;
    
    for (int i = 1; i < input; i++)
    {
        while (output < input)
        {
          cout << output << ", ";
          output = num1 + num2;
          num1 = num2;
          num2 = output;
        }
        if (output > input)
        {
            break;
        }
    }
  
    cout << "\n\n";
}

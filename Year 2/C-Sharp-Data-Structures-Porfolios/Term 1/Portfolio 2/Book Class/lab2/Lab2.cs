using System;

namespace lab2
{
    class Lab2
    {
        static void Main(string[] args)
        {
            Fantasy[] books = new Fantasy[6];  //declare an array of Book

            books[0] = new Fantasy("Harry Potter and the Cursed Child")
            {
                AuthorName = ("J. K. Rowling"),
                AuthorAge = 53
            };

            books[1] = new Horror("The Creeping")
            {
                AuthorName = ("Alexandra SIROWY")
            };

            //1 other book
            books[2] = new Fantasy("A Song of Ice and Fire")
            {
                AuthorName = ("George RR Martin"),
                AuthorAge = 70
            };

            //1 other Horror with default author
            books[3] = new Horror("Hell House");

            //self created class Romantic         
            books[4] = new Romantic("Outlander")
            {
                AuthorAge = 66
            };

            //self created class Comedy 
            books[5] = new Comedy("Three Men in a Boat")
            {
                AuthorAge = 21
            };

            for (int i = 0; i < 6; i++)
                Console.WriteLine("\n{0} ", books[i].GetSummary);
            Console.WriteLine("\n\nProgram created by Kasim Hussain!");
            Console.ReadKey();
        }
    }
 }

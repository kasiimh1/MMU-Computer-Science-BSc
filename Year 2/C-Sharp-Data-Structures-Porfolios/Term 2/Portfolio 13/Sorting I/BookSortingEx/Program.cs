
using System;
using System.Collections.Generic;
using System.Text;

namespace BookSortingEx
{
    class Program
    {
        static void swap<T>(ref T x, ref T y)
        {
           //swapcount++;
            T temp = x;
            x = y;
            y = temp;
        }

        // Complete the code of the Selection Sort to sort an array of Book
        // To complete this code you need first to complete the CompareTo method in the class Book

        static void SelectionSort(Book[] library)
        {
            for (int i = 0; i < library.Length - 1; i++)
            {
                int smallest = i;
                for (int j = i + 1; j < library.Length; j++)
                {
                    if (library[j].CompareTo(library[smallest]) < 0)
                    {
                        smallest = j;
                    }
                }
                    swap(ref library[i], ref library[smallest]);               
            }
        }

        static void Main(string[] args)
        {
            ///string[] array1 = { "Fred", "Zoe", "Angela", "Umbrella", "Ben" };
            string[] titles = {"Writing Solid Code",
                "Objects First","Programming Gems",
                "Head First Java","The C Programming Language",
                "Mythical Man Month","The Art of Programming",
                "Coding Complete","Design Patterns", 
                "ZZ"};
            string[] authors ={ "Maguire", "Kolling", "Bentley", "Sierra", "Richie", "Brooks", "Knuth", "McConnal", "Gamma", "Weiss" };
            string[] isbns = { "948343", "849328493", "38948932", "394834342", "983492389", "84928334", "4839455", "21331322", "348923948", "43893284", "9483294", "9823943" };

            Book[] library = new Book[10];            

            for (int i = 0; i < library.Length; i++)
            {
                library[i] = new Book(isbns[i], titles[i], authors[i]);                
            }

            //week 14
            // Call selection sort to sort the books in the library
            SelectionSort(library);
            Console.WriteLine("Week14");
            foreach (Book item in library)
            {
                Console.WriteLine(" {0} ", item);
            }            
            // Display all the books in the library to check they are correctly sorted (you can use foreach)
            Console.ReadKey();            
        }
    }
}

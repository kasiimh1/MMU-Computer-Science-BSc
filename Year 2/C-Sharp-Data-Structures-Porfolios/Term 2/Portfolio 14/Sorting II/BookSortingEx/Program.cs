
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
        
        //quickSortDD2gen that can sort an array of any IComparable objects. 
        static void quickSortDD2<T>(T[] items, int left, int right) where T : IComparable<T>
        {
            int i, j;
            i = left;
            j = right;
            T pivot = items[left];

            while (i <= j)
            {
                for (; (items[i].CompareTo(pivot) < 0) && (i < right); i++) ;
                for (; (pivot.CompareTo(items[j]) < 0) && (j > left); j--) ;

                if (i <= j)
                    swap(ref items[i++], ref items[j--]);
            }

            if (left < j) quickSortDD2(items, left, j);
            if (i < right) quickSortDD2(items, i, right);
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

            //week 15
            Console.WriteLine("Week15");
            quickSortDD2(library, 0, library.Length -1);
            foreach (Book item in library)
            {
                Console.WriteLine(" {0} ", item);
            }


            // Display all the books in the library to check they are correctly sorted (you can use foreach)
            Console.ReadKey();            
        }
    }
}

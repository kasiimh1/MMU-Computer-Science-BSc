using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Dynamic_Data_Structures
{
    class Program
    {
        static void Main(string[] args)
        {
            LinkList testList = new LinkList();
            Console.WriteLine("Adding the following items");
            testList.AddItem(5);
            testList.AddItem(15);
            testList.AddItem(20);
            testList.AddItem(25);
            testList.DisplayItems();
            Console.WriteLine("\nThe count of items in the list is: " + testList.NumberOfItems());
            testList.IsPresentItem(15);
            Console.WriteLine("\nRemoving 5 from the list");
            testList.RemoveItem(5);
            Console.WriteLine("\nChecking if 5 deleted successfully");
            testList.DisplayItems();

            Console.ReadKey();
        }
    }
}

using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace GenericLinkedLists
{
    class Program
    {
        static void Main(string[] args)
        {
            //new list
            Console.WriteLine("==================");
            Console.WriteLine("LIST 1");
            Console.WriteLine("==================");
            LinkListGen<int> testList = new LinkListGen<int>();
            Console.WriteLine("Adding the following items");
            //add items
            testList.AddItem(5);
            testList.AddItem(15);
            testList.AddItem(20);
            testList.AddItem(25);
            //display items
            Console.WriteLine(testList.DisplayList());
            //count the amount of items in the list
            Console.WriteLine("The count of items in the list is: " + testList.NumberOfItems());
            //check if 15 is present
            Console.WriteLine("\nIs 15 present? " + testList.IsPresentItem(15));
            //removing 5 from the list
            Console.WriteLine("\nRemoving 5");
            testList.RemoveItem(5);
            //check if 5 has been removed successfully
            Console.WriteLine("\nIs 5 still present within the list? " + testList.IsPresentItem(5));
            //add 30 to the end of the list
            testList.AppendItem(30);
            Console.WriteLine("\nUpdated list items");
            //display the list again to check if 55 has been added
            Console.WriteLine(testList.DisplayList());

            //second new list
            Console.WriteLine("\n\n==================");
            Console.WriteLine("LIST 2");
            Console.WriteLine("==================");
            LinkListGen<int> newTestList = new LinkListGen<int>();
            //add the following to the end of the list
            newTestList.AppendItem(50);
            newTestList.AppendItem(45);
            newTestList.AppendItem(40);
            newTestList.AppendItem(35);
            //display the newly added items 
            Console.WriteLine("New Appended items");
            Console.WriteLine(newTestList.DisplayList());
            //third new list      
            Console.WriteLine("\n\n==================");
            Console.WriteLine("LIST 3");
            Console.WriteLine("==================");
            LinkListGen<int> nextTestList = new LinkListGen<int>();
            //add the following to the end of the list
            nextTestList.AppendItem(55);
            nextTestList.AppendItem(60);
            //concat both lists
            Console.WriteLine("Concating the second list");
            nextTestList.Concat(newTestList);
            Console.WriteLine(nextTestList.DisplayList());

            //fourth new list 
            Console.WriteLine("\n\n==================");
            Console.WriteLine("LIST 4");
            Console.WriteLine("==================");
            LinkListGen<int> testtList = new LinkListGen<int>();
            testtList.AddItem(5);
            testtList.AddItem(4);
            testtList.AddItem(3);
            testtList.AddItem(2);
            testtList.AddItem(1);
            testtList.AppendItem(10);
            Console.WriteLine("item 7 inserted: " + testtList.DisplayList());
            testtList.InsertInOrder(7);
            Console.WriteLine("item 6 inserted in order: " + testtList.DisplayList());

            Console.WriteLine("\n\n==================");
            Console.WriteLine("LIST 5");
            Console.WriteLine("==================");
            LinkListGen<int> newtestList = new LinkListGen<int>();
            newtestList.AddItem(15);
            newtestList.AddItem(20);
            newtestList.AddItem(8);
            newtestList.AddItem(6);
            newtestList.AddItem(55);
            Console.WriteLine("List 5 without order sorting: " + newtestList.DisplayList());
            newtestList.Sort();
            Console.WriteLine("List with order sorting: " + newtestList.DisplayList());
            Console.ReadKey();
            Console.ReadKey();
        }
    }
}
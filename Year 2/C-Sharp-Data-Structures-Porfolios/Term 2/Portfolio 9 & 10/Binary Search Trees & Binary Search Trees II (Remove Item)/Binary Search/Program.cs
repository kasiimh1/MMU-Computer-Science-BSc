using BinaryTree;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Binary_Search
{
    class Program
    {
        static void Main(string[] args)
        {
          BSTree<int> Tree = new BSTree<int>();
            Tree.InsertItem(20);
            Tree.InsertItem(10);
            Tree.InsertItem(30);
            Tree.InsertItem(25);
            Tree.InsertItem(22);
            Tree.InsertItem(35);
            Tree.InsertItem(38);
            Tree.InsertItem(40);
            //week 10 
            Console.WriteLine("The height of tree is " + Tree.Height());
            Console.WriteLine("Tree count is: " + Tree.Count());
            Console.WriteLine("Does it contain 9: " + Tree.Contains(9));
            //week 11
            Tree.RemoveItem(20);

            BinTree<int> myTree = new BinTree<int>();
            string buffer = "";
            Tree.PreOrder(ref buffer);
            Console.WriteLine(buffer);
            buffer = "";
            Console.ReadKey();

        }
    }
}

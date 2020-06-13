using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using BinaryTree;

namespace Binary_Search
{
    class Program
    {
        static void Main(string[] args)
        {
          AVLTree<int> Tree = new AVLTree<int>();
            Tree.InsertItem(30);
            Tree.InsertItem(40);
            Tree.InsertItem(10);
            Tree.InsertItem(35);
            Tree.InsertItem(50);
            Tree.InsertItem(45);
            Tree.InsertItem(60);
            Tree.InsertItem(16);
            Tree.InsertItem(7);
            Tree.InsertItem(70);

            Console.WriteLine("Tree count is: " + Tree.Count());
            Console.WriteLine("Tree Hight is: " + Tree.Height());

            Tree.RemoveItem(40);
            Tree.RemoveItem(50);

            string buffer = "";
            Tree.PreOrder(ref buffer);
            Console.WriteLine("items in PreOrder {0}", buffer);           
            Console.ReadKey();
        }
    }
}

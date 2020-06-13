using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace BinaryTree
{
    class Program
    {
        static void Main(string[] args)
        {
            Console.WriteLine("Adding items to the tree\n");
            Node<int> tree = new Node<int>(6);
            tree.Left = new Node<int>(2);
            tree.Left.Right = new Node<int>(5);
            tree.Left.Right.Data = 3;
            tree.Right = new Node<int>(8);

            BinTree<int> myTree = new BinTree<int>(tree);
            string buffer = "";

            Console.WriteLine("\nInOrder");
            myTree.InOrder(ref buffer);
            Console.WriteLine(buffer);
            buffer = "";

            Console.WriteLine("\nPreOrder");
            myTree.PreOrder(ref buffer);
            Console.WriteLine(buffer);
            buffer = "";

            Console.WriteLine("\nPostOrder");
            myTree.PostOrder(ref buffer);
            Console.WriteLine(buffer);
            buffer = "";

            Console.ReadKey();
        }
    }
}

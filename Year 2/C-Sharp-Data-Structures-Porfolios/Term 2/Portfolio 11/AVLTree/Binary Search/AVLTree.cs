using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using BinaryTree;
using Binary_Search;

namespace Binary_Search
{
    class AVLTree<T> : BSTree<T> where T : IComparable
    {
        public AVLTree()
        {
            root = null;
        } 
                
        public new void InsertItem(T item)
        {
            insertItem(item, ref root);
        }

        private void insertItem(T item, ref Node<T> tree)
        {
            if (tree == null)            
                tree = new Node<T>(item);            

            else if (item.CompareTo(tree.Data) < 0)            
                insertItem(item, ref tree.Left);
            
            else if (item.CompareTo(tree.Data) > 0)
                insertItem(item, ref tree.Right);            

            tree.BalanceFactor = height(ref tree.Left) - height(ref tree.Right);

            if (tree.BalanceFactor <= -2)            
                rotateLeft(ref tree);            

            if (tree.BalanceFactor >= 2)           
                rotateRight(ref tree);
        }
        private void rotateLeft(ref Node<T> tree)
        {
            if (tree.Right.BalanceFactor > 0)  //double rotate
                rotateRight(ref tree.Right);
                    
            Node<T> oldRoot = tree.Right;
            tree.Right = oldRoot.Left;
            oldRoot.Left = tree;
            tree = oldRoot;

            tree.BalanceFactor = height(ref tree.Left) - height(ref tree.Right);
        }

        private void rotateRight(ref Node<T> tree)
        {
            if (tree.Left.BalanceFactor < 0)  //double rotate
                rotateLeft(ref tree.Left);
            
            Node<T> oldRoot = tree.Left;
            tree.Left = oldRoot.Right;
            oldRoot.Right = tree;
            tree = oldRoot;

            tree.BalanceFactor = height(ref tree.Left) - height(ref tree.Right);
        }

        private void updateBalance(ref Node<T> tree)
        {
            tree.BalanceFactor = height(ref tree.Left) - height(ref tree.Right);
            Console.WriteLine("[Update-Balance] Current Balance Factor = " + tree.BalanceFactor);

            if (tree.BalanceFactor <= -2)
                rotateLeft(ref tree);

            if (tree.BalanceFactor >= 2)
                rotateRight(ref tree);        
        }

        private void checkBalance(ref Node<T> tree)
        {
            tree.BalanceFactor = height(ref tree.Left) - height(ref tree.Right);
            Console.WriteLine("[Check-Balance] Current Balance Factor = " + tree.BalanceFactor);
        }

        // new code to remove item, check for update on balance factor than, rebalance if needed.
        public new void RemoveItem(T item) //covered in lecture 16
        {
            if (root != null && Contains(item))
            {
                removeItem(item, ref root);
            }
        }

        /*
            checkBalance(ref tree);
        */

        private void removeItem(T item, ref Node<T> tree)
        {
            tree.BalanceFactor = height(ref tree.Left) - height(ref tree.Right);

            if (item.CompareTo(tree.Data) < 0)
            {
                removeItem(item, ref tree.Left);
            }
            if (item.CompareTo(tree.Data) > 0)
            {
                removeItem(item, ref tree.Right);
            }
            // found the item
            else if (item.CompareTo(tree.Data) == 0)
            {
                if (tree.Left == null)
                {
                    tree = tree.Right;                    
                }
                else if (tree.Right == null)
                {
                    tree = tree.Left;                    
                }
                else
                {
                    T newRoot = leastItem(tree.Right);
                    tree.Data = newRoot;
                    removeItem(newRoot, ref tree.Right);
                    updateBalance(ref tree);
                    checkBalance(ref tree);
                }
            }
        }

        // find and retrive leftMost item in the tree
        private T leastItem(Node<T> tree)
        {
            if (tree.Left == null)            
                return tree.Data;
            
            else            
                return leastItem(tree.Left);            
        }
    }
}
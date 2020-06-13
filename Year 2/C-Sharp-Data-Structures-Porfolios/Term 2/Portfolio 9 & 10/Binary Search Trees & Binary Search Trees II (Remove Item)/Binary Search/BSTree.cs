using BinaryTree;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Binary_Search
{
    class BSTree<T> : BinTree<T> where T : IComparable
    {
        public BSTree()
        {
            root = null;
        }

        public void InsertItem(T item)
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
        }

        public int Height()
        {
            return height(ref root);
        }

        public int height(ref Node<T> tree)
        //Return the max level of the tree
        {
            if (tree == null)
            {
                return 0;
            }

            int leftHeight = height(ref tree.Left);
            int rightHeight = height(ref tree.Right);

            if (leftHeight > rightHeight)
            {
                return leftHeight + 1;
            }
            else
            {
                return rightHeight + 1;
            }
        }

        public int Count()
        //Return the number of nodes in the tree
        {
            if (root == null)
            {
                return 0;
            }
            return checkCount(root);
        }
        public int checkCount(Node<T> tree)
        {
            int count = 0;

            if (tree == null)
            {
                return 0;
            }
            if (tree.Left != null)
            {
                count += 1 + checkCount(tree.Left);
            }
            if (tree.Right != null)
            {
                count += 1 + checkCount(tree.Right);
            }
            return count;
        }

        public Boolean Contains(T item)
        //Return true if the item is contained in the BSTree, false 	  //otherwise.
        {
            if (root == null)
            {
                return false;
            }
            return itemPresent(item, root);
        }
        public Boolean itemPresent(T item, Node<T> tree)
        {
            if (tree == null)
            {
                return false;
            }
            else if (tree.Data.CompareTo(item) == 0)
            {
                return true;
            }
            if (item.CompareTo(tree.Data) < 0)
            {
                return itemPresent(item, tree.Left);
            }
            if (item.CompareTo(tree.Data) > 0)
            {
                return itemPresent(item, tree.Right);
            }
            else return false;
        }

        public void RemoveItem(T item) //covered in lecture 16
        {
            if (root != null && Contains(item))
            {
                removeItem(item, ref root);
            }
        }

        private void removeItem(T item, ref Node<T> tree)
        {
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
                }
            }
        }
        // find and retrive leftMost item in the tree
        private T leastItem(Node<T> tree)
        {
            if (tree.Left == null)
            {
                return tree.Data;
            }
            else
            {
                return leastItem(tree.Left);
            }
        }
    }
}
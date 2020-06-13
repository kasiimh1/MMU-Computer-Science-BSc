using Companies_Trading_Data;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace BinaryTree
{
    class BinTree<Company> where Company : IComparable
    {
        protected Node<Company> root;
        public BinTree()  //creates an empty tree
        {
            root = null;
        }

        public BinTree(Node<Company> node)  //creates a tree with node as the root
        {
            root = node;
        }

        public void InOrder(ref List<Company> buffer)
        {
            inOrder(root, ref buffer);
        }

        private void inOrder(Node<Company> tree, ref List<Company> buffer)
        {
            if (tree != null)
            {
                inOrder(tree.Left, ref buffer);
                buffer.Add(tree.Data);
                inOrder(tree.Right, ref buffer);
            }
        }

        public void PostOrder(ref string buffer)
        {
            postOrder(root, ref buffer);
        }

        private void postOrder(Node<Company> tree, ref string buffer)
        {
            if (tree != null)
            {
                postOrder(tree.Left, ref buffer);
                postOrder(tree.Right, ref buffer);
                buffer += tree.Data.ToString() + ", ";
            }
        }

        public void PreOrder(ref string buffer)
        {
            preOrder(root, ref buffer);
        }

        private void preOrder(Node<Company> tree, ref string buffer)
        {
            if (tree != null)
            {
                buffer += tree.Data.ToString() + ", ";
                preOrder(tree.Left, ref buffer);
                preOrder(tree.Right, ref buffer);
            }
        }
    }
}

using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Graphs_I_and_II
{
    public class Graph<T> where T : IComparable
    {
        private LinkedList<GraphNode<T>> nodes;

        public Graph()
        {
            nodes = new LinkedList<GraphNode<T>>();
        }
        
        public bool IsEmptyGraph()
        {
            return nodes.Count == 0;
        }

        public bool ContainsGraph(GraphNode<T> node)
        {
            foreach (GraphNode<T> n in nodes)
            {
                if (n.ID.CompareTo(node.ID) == 0)
                { return true; }
            }
            return false;
        }

        public bool IsAdjacent(GraphNode<T> from, GraphNode<T> to)
        {
            foreach (GraphNode<T> n in nodes)
            {
                if (n.ID.CompareTo(from.ID) == 0)
                {
                    if (from.GetAdjList().Contains(to.ID))
                        return true;
                }
            }
            return false;
        }

        public void AddNode(T id)
        {
            GraphNode<T> n = new GraphNode<T>(id);
            nodes.AddFirst(n);
        }

        public GraphNode<T> GetNodeByID(T id)
        {
            foreach (GraphNode<T> n in nodes)
            {
                if (id.CompareTo(n.ID) == 0)
                { return n; }
            }
            return null;
        }

        // Add a directed edge between the node with id "from" and the node with id “to” 
        public void AddEdge(T from, T to)
        {           
            GraphNode<T> fNode = new GraphNode<T>(from);
            GraphNode<T> tNode = new GraphNode<T>(to);
            foreach (GraphNode<T> n in nodes)
            {
                if (n.ID.CompareTo(fNode.ID) == 0 && !IsAdjacent(fNode, tNode))
                { n.AddEdge(tNode); }
            }
        }

        //Perform a DFS traversal starting at the node with id “startID”
        //leaving a list of visited id’s in the visited list. 

        public void DepthFirstTraverse(T startID, ref List<T> visited)
        {
            LinkedList<T> adj;
            Stack<T> toVisit = new Stack<T>();

            GraphNode<T> current = new GraphNode<T>(startID);

            toVisit.Push(startID);

            while (toVisit.Count != 0)
            {
                //Hint: get current node to the list of visited nodes and add its adjacent nodes (only those not already visited) to toVist 
                current = GetNodeByID(toVisit.Peek());
                toVisit.Pop();
                visited.Add(current.ID);
                adj = current.GetAdjList();

                foreach (T item in adj)
                {
                    if (!toVisit.Contains(item) && !visited.Contains(item))
                    {
                        toVisit.Push(item);
                    }
                }
            }
        }

        //Perform a BFS traversal starting at the node with id “startID”
        //leaving a list of visited id’s in the visited list. 

        public void BreadthFirstTraverse(T startID, ref List<T> visited)
        {
            LinkedList<T> adj;
            Queue<T> toVisit = new Queue<T>();
            GraphNode<T> current = new GraphNode<T>(startID);

            toVisit.Enqueue(startID);

            while (toVisit.Count != 0)
            {
                //Hint: get current node to the list of visited nodes         and add its adjacent nodes (only those not already visited) to toVisit
                current = GetNodeByID(toVisit.Peek());
                toVisit.Dequeue();
                visited.Add(current.ID);
                adj = current.GetAdjList();
                foreach (T item in adj)
                {
                    if (!toVisit.Contains(item) && !visited.Contains(item))
                    {
                        toVisit.Enqueue(item);
                    }
                }
            }
        }
    }//end of class
}

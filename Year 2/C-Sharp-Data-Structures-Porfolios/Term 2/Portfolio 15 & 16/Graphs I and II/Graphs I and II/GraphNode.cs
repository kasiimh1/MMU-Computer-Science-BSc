using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Graphs_I_and_II
{
    public class GraphNode<T>
    {
        private T id; 
        private LinkedList<T> adjList; 

        public GraphNode(T id)
        {
            this.id = id;
            adjList = new LinkedList<T>();
        }

        public T ID
        {
            set { id = value; }
            get { return id; }
        }

        public void AddEdge(GraphNode<T> to)
        {
            adjList.AddFirst(to.ID);
        }

        public LinkedList<T> GetAdjList()
        {
            return adjList;
        }
    }
}

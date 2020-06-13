using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Graphs_I_and_II
{
    class Program
    {
        static void Main(string[] args)
        {

            GraphNode<char> current;  //current location
            GraphNode<char> to;
            Graph<char> myGraph = new Graph<char>();
            List<char> seen = new List<char>();

            myGraph.AddNode('A');
            myGraph.AddNode('B');
            myGraph.AddNode('C');
            myGraph.AddNode('D');
            myGraph.AddNode('E');

            myGraph.AddEdge('A', 'B');
            myGraph.AddEdge('A', 'C');
            myGraph.AddEdge('B', 'D');
            myGraph.AddEdge('B', 'E');

            current = myGraph.GetNodeByID('A');
            to = myGraph.GetNodeByID('B');

            Console.WriteLine("Is myGraph empty? Answer: {0}", myGraph.IsEmptyGraph());
            Console.WriteLine("Is myGraph contain {0}? Answer: {1}", current.ID, myGraph.ContainsGraph(current));
            Console.WriteLine("Is node {0} and {1} adjacent? Answer: {2}",
                  current.ID, to.ID, myGraph.IsAdjacent(current, to));
            Console.WriteLine("Is node {0} and {1} adjacent? Answer: {2}",
                  to.ID, current.ID, myGraph.IsAdjacent(to, current));

            myGraph.DepthFirstTraverse('A', ref seen);

            Console.Write("\nDepth First: ");
            for (int i = 0; i < seen.Count(); i++)
            { Console.Write(seen.ElementAt(i) + " "); }
            seen.Clear();
            myGraph.BreadthFirstTraverse('A', ref seen);
            Console.Write("\nBreadth First: ");

            for (int i = 0; i < seen.Count(); i++)
            { Console.Write(seen.ElementAt(i) + " "); /*A, B, C, D, E */ }            
            Console.ReadKey();
        }
    }
}

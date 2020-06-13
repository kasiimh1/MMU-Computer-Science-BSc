using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Dynamic_Data_Structures
{
    class Link
    {        
        private int data;
        private Link next;

        public Link(int item) //constructor with an item
        {
            data = item;
            next = null;
        }

        public Link(int item, Link list) //constructor with item and list
        {
            data = item;
            next = list;
        }

        public int Data //property for data
        {
            set { this.data = value; }
            get { return this.data; }
        }

        public Link Next //property for next
        {
            set { this.next = value; }
            get { return this.next; }
        }
    }
}

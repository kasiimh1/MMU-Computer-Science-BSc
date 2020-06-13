using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Dynamic_Data_Structures
{
    class LinkList
    {
        private Link list = null; //default value – empty list

        public void AddItem(int item) //add item to front of list
        {
            list = new Link(item, list);
        }

        public string DisplayItems() //write items to string and return
        {
            Link temp = list;
            string buffer = "";
            while (temp != null) // move one link and add head to the buffer
            {
                Console.WriteLine(temp.Data);
                temp = temp.Next;
            }
            return buffer;
        }

        public int NumberOfItems() // returns number of items in list
        {
            Link temp = list;
            int count = 0;
            while (temp != null) // move one link and add 1 to count
            {
                count++;
                temp = temp.Next;
            }
            return count;
        }

        public bool IsPresentItem(int item)
        {
            Link temp = list;
            while (temp != null)
            {
                if (temp.Data == item)
                {
                    Console.WriteLine("\nItem " + item + " is present");
                    return true;
                }
                temp = temp.Next;
            }
            Console.WriteLine("\nItem " + item + " isn't present");
            return false;
        }

        public void RemoveItem(int item)
        {
            Link temp = list;
            Link previous = null;

            while (temp != null)
            {
                if (temp.Data == item)
                {
                    if (previous != null)
                    {
                        previous.Next = temp.Next;
                        temp = temp.Next;
                    }
                    else
                    {
                        previous = temp;
                        temp = temp.Next;
                        list = temp;
                    }
                }
                else
                {
                    previous = temp;
                    temp = temp.Next;
                }
            }
        }
    }
}

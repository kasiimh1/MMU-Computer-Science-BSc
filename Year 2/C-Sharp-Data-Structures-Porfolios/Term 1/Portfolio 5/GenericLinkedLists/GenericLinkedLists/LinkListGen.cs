using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace GenericLinkedLists
{
    class LinkListGen<T> where T : IComparable
    {
        private LinkGen<T> list;
        public LinkListGen()
        {
        }

        public void AddItem(T item)
        {
            list = new LinkGen<T>(item, list);
        }

        public string DisplayList() //write items to string and return
        {
            LinkGen<T> temp = list;
            string buffer = "";
            while (temp != null) // move one link and add head to the buffer
            {
                buffer = buffer + temp.Data + ", ";
                temp = temp.Next;
            }
            return buffer;
        }

        public int NumberOfItems() // returns number of items in list
        {
            LinkGen<T> temp = list;
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
            LinkGen<T> temp = list;
            while (temp != null)
            {
                if (temp.Data.CompareTo(item) == 0)
                {
                    Console.WriteLine("\nItem " + item + " is present");
                    return true;
                }
                temp = temp.Next;
            }
            Console.WriteLine("\nItem " + item + " isn't present");
            return false;
        }

        public void AppendItem(T item)
        {
            LinkGen<T> temp = list;
            if (temp == null) // if list is empty
            {
                list = new LinkGen<T>(item);
            }
            else
            {
                while (temp.Next != null)
                {
                    temp = temp.Next;
                }
                temp.Next = new LinkGen<T>(item);
            }
        }

        public void RemoveItem(T item)
        {
            LinkGen<T> temp = list;
            LinkListGen<T> newList = new LinkListGen<T>();

            while (temp != null)
            {
                if (item.CompareTo(temp.Data) != 0)
                {
                    newList.AppendItem(temp.Data);
                }
                temp = temp.Next;
            }
            list = newList.list;
        }

        public void Concat(LinkListGen<T> list2)
        {
            LinkGen<T> temp = list;
            LinkListGen<T> newList = new LinkListGen<T>();

            while (temp != null)
            {
                newList.AddItem(temp.Data);
                temp = temp.Next;
            }
            temp = list2.list;
            while (temp != null)
            {
                newList.AppendItem(temp.Data);
                temp = temp.Next;
            }
            list = newList.list;
        }

        public void Copy(LinkListGen<T> list2)
        {
            LinkListGen<T> newList = new LinkListGen<T>();
            newList.Concat(list2);
            list = newList.list;
        }

        public void InsertInOrder(T item)
        {
            LinkGen<T> temp = list;
            LinkListGen<T> newList = new LinkListGen<T>();
            Boolean check = false;

            if (list == null)
                AddItem(item);
            else
            {
                while (temp != null)
                {
                    if (item.CompareTo(temp.Data) < 0)
                    {
                        if (!check)
                        {
                            newList.AppendItem(item);
                            check = true;
                        }
                    }
                    newList.AppendItem(temp.Data);
                    temp = temp.Next;
                }

                if (!check)
                {
                    newList.AppendItem(item);
                }
                list = newList.list;
            }
        }

        public void Sort()
        {
            LinkGen<T> temp = list;
            LinkListGen<T> newList = new LinkListGen<T>();
            while (temp != null)
            {
                newList.InsertInOrder(temp.Data);
                temp = temp.Next;
            }
            list = newList.list;
        }
    }
}
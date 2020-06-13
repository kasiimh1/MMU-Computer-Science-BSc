using System;
using System.Collections.Generic;
using System.Text;

namespace lab2
{
    class Person : IComparable
    {
        private string name;
        private int age;
        //constructor with one argument        
        public Person(string name)
        {
            this.name = name;
            //default age is 16
            age = 16;
        }
        //property for name            
        public string Name
        {
            get { return name; }
            set { name = value; }
        }
        public int Age
        {
            get { return age; }
            set { age = value; }
        }
        public int CompareTo(Object obj) //implementation of CompareTo
        {                   // for IComparable
            Person other = (Person)obj;
            return Name.CompareTo(other.Name); //uses Name for comparison        
        }
    }
}
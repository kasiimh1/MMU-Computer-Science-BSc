using System;
using System.Collections.Generic;
using System.Text;

namespace lab2
{
    class Fantasy
    {
        private string title;
        private Person author;

        public Fantasy(string title)
        {
            this.title = title;
            author = new Person(title);
        }        
        public string AuthorName
        {
            get { return author.Name; }
            set { author.Name = value; }
        }           
        public string Title
        {
            get { return title; }
            set { title = value; }
        }  
        public int AuthorAge
        { 
            get { return author.Age;  }
            set { author.Age = value; }
        }    
        public string GetSummary
        {
            get {
                string print =  "Book Genre: " + this.GetType().Name + "\nThe Book Title is: " + Title +
                                "\nThe Book was written by: " + AuthorName + "\n"+ AuthorName + " is "
                                + AuthorAge + " years old";
                return print;
                }
        }
    }
}
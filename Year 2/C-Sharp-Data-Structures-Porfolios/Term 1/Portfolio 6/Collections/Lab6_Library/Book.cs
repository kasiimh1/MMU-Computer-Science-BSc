using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Lab6_Library
{
    class Book
    {
        private string isbn;
        private string title;
        private Boolean onloan = false;

        public Book(string isbn, string title)
        {
            this.isbn = isbn;
            this.title = title;
        }
        public string ISBN
        {
            get { return isbn; }
            set { isbn = value; }
        }
        public string Title
        {
            get { return title; }
            set { isbn = title; }
        }
        public Boolean Onloan
        {
            get { return onloan; }
            set { onloan = value; }
        }
    }
}
using System;
using System.Collections.Generic;
using System.Text;

namespace lab2
{
    class Horror : Fantasy
    {
        public Horror(string title) : base(title)
        {
            base.AuthorName = ("Stephan King");
        }
    }
}

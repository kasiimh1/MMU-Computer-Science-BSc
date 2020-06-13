using System;
using System.Collections.Generic;
using System.Text;

namespace lab2
{
    class Comedy : Fantasy
    {
        public Comedy(string title) : base(title)
        {
            base.AuthorName = ("Jerome K Jerome");
        }
    }
}

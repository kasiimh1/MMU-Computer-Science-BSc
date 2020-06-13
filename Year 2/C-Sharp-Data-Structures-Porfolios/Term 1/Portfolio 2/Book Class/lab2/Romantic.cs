using System;
using System.Collections.Generic;
using System.Text;

namespace lab2
{
    class Romantic : Fantasy
        {
            public Romantic(string title) : base(title)
            {
                base.AuthorName = ("Diana Gabaldon");
            }
        }
    }
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Recursion
{
    class program
    {
        static void Main(string[] args)
        {
            Console.WriteLine("\n" + multiply(10,3));
            Console.WriteLine("\n" + power(5, 2));
            Console.WriteLine("\n" + dec(dec(10)));
            Console.WriteLine("\n{0}", inc(inc(inc(inc(2)))));
            Console.WriteLine("\n" + inc(dec(inc(inc(2)))));
            Console.ReadLine();
        }

        static int multiply(int x, int y)
        {

            if (y == 1)
            {
                return x; 
            }
            else
            {
                return x + multiply(x, dec(y));
            }
        }

        static int power(int x, int y)
        {
            if (y == 1)
            {
                return x;
            }
            else
            {
                return x * power(x, dec(y));
            }
        }

        static int inc(int x)
        {
            return x + 1;
        }

        static int dec(int x)
        {
            return x - 1;
        }
    }
}

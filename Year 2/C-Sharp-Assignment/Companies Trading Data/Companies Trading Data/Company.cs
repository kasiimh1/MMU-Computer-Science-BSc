using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Companies_Trading_Data
{
    class Company : IComparable
    {
        private string companyName;
        private int netincome;
        private int operatingincome;
        private int totalassets;
        private int numberemployees;
        private LinkedList<string> buyers;

        public Company()
        {

        }

        public string CompanyName
        {
            get { return companyName; }
            set { companyName = value; }
        }

        public int NetIncome
        {
            get { return netincome; }
            set { netincome = value; }
        }

        public int OperatingIncome
        {
            get { return operatingincome; }
            set { operatingincome = value; }
        }

        public int TotalAssets
        {
            get { return totalassets; }
            set { totalassets = value; }
        }

        public int NumberEmployees
        {
            get { return numberemployees; }
            set { numberemployees = value; }
        }

        public LinkedList<string> Buyers
        {
            get { return buyers; }
            set { buyers = value; }
        }

        public int CompareTo(Object obj) //implementation of CompareTo
        {   // for IComparable
            Company other = (Company)obj;
            return CompanyName.CompareTo(other.CompanyName); //uses CompanyName for comparison        
        }

        public override string ToString()
        {
            return companyName;
        }
    }
}
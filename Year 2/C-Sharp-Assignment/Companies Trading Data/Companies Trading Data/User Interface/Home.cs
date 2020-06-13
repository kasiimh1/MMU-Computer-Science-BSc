using System;
using System.Windows.Forms;
using System.IO;
using Binary_Search;
using System.Collections.Generic;

namespace Companies_Trading_Data
{
    public partial class Home : Form
    {
        public Home()
        {
            InitializeComponent();
            //set placeholder values on launch
            placeholderValues();
        }

        AVLTree<Company> CompanyAVLTree = new AVLTree<Company>(); //new AVLTree that is of type company        
        List<Company> buffer = new List<Company>(); //list to print out compaines to the list box
        Company comp = new Company(); // new company

        static string[] headers = new string[6]; //column header
        const int MAX_LINES_FILE = 50000; //max lines
        string[] AllLines = new string[MAX_LINES_FILE];

        // Displays an OpenFileDialog so the user can select a file to be opened  
        OpenFileDialog openFile = new OpenFileDialog();

        /* METHODS */

        public void loadFile()
        {
            /* reset program when browsing for a new file to open within the app */
            resetProgram();

            AllLines = File.ReadAllLines(openFile.FileName);

            /* lock the user from opening another file while there's already one in memory */
            if (AllLines != null)
                openFileToolStripMenuItem.Enabled = false;

            foreach (string line in AllLines)
            {
                if (line.StartsWith("Company")) //found first line - headers 
                    headers = line.Split(',');
                else
                {
                    string[] columns = line.Split(',');

                    /*
                      this needs to happen before we add the data to tempCompany 
                      as we need to insert the buyers along with the rest of the data
                    */

                    LinkedList<string> temp = new LinkedList<string>(); //new linkedlist to store the buyers

                    string[] buyers = columns[5].Split(';', '[', ']');
                    foreach (string partner in buyers)
                    {
                        if (partner != "")
                        {
                            //add partners to the linked list
                            temp.AddFirst(partner);
                        }
                    }

                    Company tempCompany = new Company //each new line will be it's own node in the AVLTree, fetched from the csv file
                    {
                        CompanyName = columns[0],
                        NetIncome = Convert.ToInt32(columns[1]),
                        OperatingIncome = Convert.ToInt32(columns[2]),
                        NumberEmployees = Convert.ToInt32(columns[3]),
                        TotalAssets = Convert.ToInt32(columns[4]),
                        Buyers = temp
                    };

                    //add each company name to the list view
                    companiesListBox.Items.Add(tempCompany.CompanyName);
                    //insert each company w/ data to the AVL tree
                    CompanyAVLTree.InsertItem(tempCompany);
                }
            }
            updateCountHeight();
        }

        public void placeholderValues()
        {
            /* set values upon application launch, application reset and any other time this func is called */
            companyNameTextBox.Text = "Select a Company";
            netIncomeTextBox.Text = "Select a Company";
            operatingIncomeTextBox.Text = "Select a Company";
            totalAssetsTextBox.Text = "Select a Company";
            employeesTextBox.Text = "Select a Company";
            newBuyerTextBox.Text = "Select a Company";
            companySearchTerm.Text = "";
        }

        public void updateCountHeight()
        {
            /* Update Tree Height and Count */
            treeHeight.Text = ("AVL Tree Height = " + CompanyAVLTree.Height());
            treeCount.Text = ("AVL Tree Count = " + CompanyAVLTree.Count());
        }

        public void removeRecord()
        {
            if (companiesListBox.SelectedItem == null)
            {            }
            else
            {
                /* find the company to remove */
                Company remove = new Company();
                companyNameTextBox.Text = companiesListBox.SelectedItem.ToString();
                remove.CompanyName = companyNameTextBox.Text;
                /* then remove it from the listbox */
                companiesListBox.Items.Remove(remove.CompanyName);

                /* fill with placeholder values */
                placeholderValues();
                /* remove the company from the AVLTree */
                CompanyAVLTree.RemoveItem(remove);
                /* remove from the ordered list */
                buffer.Remove(remove);
                /* finally update the tree count and height */
                updateCountHeight();
            }
        }

        public void resetProgram()
        {
            CompanyAVLTree.InOrder(ref buffer);
            /* set placeholder values */
            placeholderValues();

            /* iterate over the ordered list and remove each item in the AVLTree until done */
            foreach (Company comp in buffer)
            {
                CompanyAVLTree.RemoveItem(comp);
            }
            /* clear the ordered list */
            buffer.Clear();
            /* finally update the tree count and height */
            updateCountHeight();
        }

        public void sortInOrder()
        {
            List<Company> buffer = new List<Company>(); //list to print out compaines to the list box
            CompanyAVLTree.InOrder(ref buffer);
            /* clear current contents of the list box */
            companiesListBox.Items.Clear();

            /* iterate over the ordered list and add each item in to the list box until done */
            foreach (Company comp in buffer)
            {
                companiesListBox.Items.Add(comp.CompanyName);
            }
        }

        /* removes a record if it's present in the tree, updates the list view and calls the update tree height and count function */
        private void removeRecordButton_Click(object sender, EventArgs e)
        {
            removeRecord();
        }

        public void nullValue()
        {
            DialogResult errorDialog = MessageBox.Show(
              "No Item was selected from the list, click an item to display records\n\n"
              , "[Error] Null Value Found", MessageBoxButtons.OK, MessageBoxIcon.Information);
        }

        private void companiesListBox_SelectedIndexChanged_1(object sender, EventArgs e)
        {
            if (companiesListBox.SelectedItem == null)
            {            }
            else
            {
                comp = CompanyAVLTree.Search(companiesListBox.SelectedItem.ToString());

                newBuyerTextBox.Text = ""; //clear text when item is selected

                companyNameTextBox.Text = comp.CompanyName.ToString();
                netIncomeTextBox.Text = comp.NetIncome.ToString();
                operatingIncomeTextBox.Text = comp.OperatingIncome.ToString();
                totalAssetsTextBox.Text = comp.TotalAssets.ToString();
                employeesTextBox.Text = comp.NumberEmployees.ToString();

                LinkedList<string> temps = comp.Buyers;
                foreach (string partner in temps)
                {
                    newBuyerTextBox.Text += partner + ",";
                }
            }
        }

        private void sortButton_Click(object sender, EventArgs e)
        {
            sortInOrder();
        }

        private void updateRecordButton_Click(object sender, EventArgs e)
        {
            LinkedList<string> temp = new LinkedList<string>(); //new linkedlist to store the buyers
            if (companiesListBox.SelectedItem == null)
            {
                DialogResult errorDialog = MessageBox.Show(
               "No Item was selected from the list, click a company to modify it's record\n\n"
               , "[Error] Null Value Found", MessageBoxButtons.OK, MessageBoxIcon.Information);
            }
            else
            {
                /* get current values before we remove the record, than reinsert it */
                String compName = companyNameTextBox.Text;
                Int32 net = Convert.ToInt32(netIncomeTextBox.Text);
                Int32 operate = Convert.ToInt32(operatingIncomeTextBox.Text);
                Int32 employee = Convert.ToInt32(employeesTextBox.Text);
                Int32 assets = Convert.ToInt32(totalAssetsTextBox.Text);

                string[] buyers = newBuyerTextBox.Text.Split(',');

                foreach (string buy in buyers)
                {
                    temp.AddLast(buy);
                }

                removeRecord();

                Company insert = new Company
                {
                    CompanyName = compName,
                    NetIncome = net,
                    OperatingIncome = operate,
                    NumberEmployees = employee,
                    TotalAssets = assets,
                    Buyers = temp
                };

                CompanyAVLTree.InsertItem(insert);
                /* it adds the newly updated item to the bottom of the list box 
                 * ie the end of the linked list,
                 * if you sort in order it will go back to the top as intended
                 */
                companiesListBox.Items.Add(insert.CompanyName);
                updateCountHeight();
            }
        }
        
        private void SearchTermButton_Click(object sender, EventArgs e)
        {
            if (!partialKeySearch.Checked && !tradingPartnerSearch.Checked)
            {
                DialogResult errorDialog = MessageBox.Show(
                "Please select a search parameter before continuing\n\n"
                , "[Error] No Search Option Selected", MessageBoxButtons.OK, MessageBoxIcon.Information);
            }

            List<Company> buffer = new List<Company>(); //list to print out compaines to the list box
            String search = companySearchTerm.Text;
            CompanyAVLTree.InOrder(ref buffer);

            /* 
             * search for company based on partial keyword search
             * display all matching results 
             */

            if (partialKeySearch.Checked == true)
            {
                compainesListLabel.Text = "Company List";

                companiesListBox.Items.Clear();
                foreach (Company comp in buffer)
                {
                    if (comp.CompanyName.ToUpper().StartsWith(search.ToUpper()))
                    {
                        companiesListBox.Items.Add(comp.CompanyName);
                    }
                    if (!comp.CompanyName.ToUpper().StartsWith(search.ToUpper()))
                    {
                        companiesListBox.Items.Remove(comp.CompanyName);
                    }
                }

                if (search == "")
                {
                    companiesListBox.Items.Clear();
                    foreach (Company comp in buffer)
                    {
                        companiesListBox.Items.Add(comp.CompanyName);
                    }
                }
            }

            /* Displays all Trading Partners for a Company that is searched */
            if (tradingPartnerSearch.Checked == true)
            {
                compainesListLabel.Text = "Trading Partners List";

                /* convert search parameter to all caps */
                search = search.ToUpper();

                companiesListBox.Items.Clear();
                foreach (Company comp in buffer)
                {
                    if (comp.Buyers.Contains(search))
                    {
                        companiesListBox.Items.Add(comp.CompanyName);
                    }
                    if (!comp.Buyers.Contains(search))
                    {
                        companiesListBox.Items.Remove(comp.CompanyName);
                    }
                }
            }
        }

        private void howToUseHELPToolStripMenuItem_Click(object sender, EventArgs e)
        {
            DialogResult helpDialog = MessageBox.Show(
               "How to use this program \n\n" +
               "[*] Browse for a file or start adding data within the boxes provided \n\n" +
               "[*] Add Company - Fill in the required boxes for the companies data and click Add Record \n\n" +
               "[*] Update Company - Fill in the required boxes that you wish to update for that company and click Update Record \n\n" +
               "[*] Remove Company - Select the company you wish to remove from the list of Companies and click Remove Record \n\n" +
               "[*] Update Buyer - To update a new buyer you must select the company from the list, add to existing buyers or remove buyers, seperated by a comma without a space (AMX,ASD) \n\n" +
               "[*] Sort AVL Tree - Sorts the Company list alphabetically, it's also run on insertion, deletion and opening of a file \n\n" +
               "[*] Partial Keyword Search - Enter the first letter of the compaines you wish to display \n\n" +
               "[*] Trading Partner Search - Enter the name of the company who's trading partners you wish to display \n\n" +
               "[*] Reset Program - resets the AVL Tree's state, removing all entires present \n\n"
               , "Help Information", MessageBoxButtons.OK, MessageBoxIcon.Information);
        }

        private void resetProgramToolStripMenuItem_Click(object sender, EventArgs e)
        {
            openFileToolStripMenuItem.Enabled = true;
            resetProgram();
        }

        private void openFileToolStripMenuItem_Click(object sender, EventArgs e)
        {
            openFile.Title = "Select a CSV File"; //set the dialog box title
            openFile.InitialDirectory = @"C:\"; //set initial path to load 
            openFile.Filter = "CSV|*.csv"; //filter the files we want to be imported into this program

            if (openFile.ShowDialog() == DialogResult.OK)
            {
                loadFile();
            }
        }
    }
}
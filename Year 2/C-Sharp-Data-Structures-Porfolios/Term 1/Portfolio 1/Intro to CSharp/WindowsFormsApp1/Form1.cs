using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace WindowsFormsApp1
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
        }

        private void checkBox1_CheckedChanged(object sender, EventArgs e)
        {
            if (checkBox1.Checked == true)
            {
                messageTextBox.ForeColor = Color.Red;
                checkBox1.Text = "Change Text Colour to BLACK";
            }
            else
            {
                messageTextBox.ForeColor = Color.Black;
                checkBox1.Text = "Change Text Colour to RED";
            }
        }
      
    }
}

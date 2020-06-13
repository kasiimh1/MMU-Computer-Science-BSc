using System;
using System.Collections;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace Lab6_Library
{
    public partial class Form1 : Form
    {
        Dictionary<string, Book> library = new Dictionary<string, Book>();

        public Form1()
        {
            InitializeComponent();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            if (radioButton4.Checked == true)
            {
                Book book1 = new Book(textBox1.Text, textBox2.Text);
                library[book1.ISBN] = book1;
                textBox2.Text = "";
                label7.Text = Convert.ToString(library.Count);
                textBox4.Text = "";
                if (textBox3.Text == "Y" || textBox3.Text == "y")
                    library[book1.ISBN].Onloan = true;
                if (textBox3.Text == "N" || textBox3.Text == "n")
                    library[book1.ISBN].Onloan = false;
                foreach (KeyValuePair<string, Book> b in library)
                    textBox4.Text += (b.Key + " : " + b.Value.Title + " : " + library[b.Key].Onloan + Environment.NewLine);
            }

            if (radioButton3.Checked == true)
            {
                library.Remove(textBox1.Text);
                textBox4.Text = "";
                textBox1.Text = "";
                foreach (KeyValuePair<string, Book> b in library)
                    textBox4.Text += (b.Key + " : " + b.Value.Title + " : " + library[b.Key].Onloan + Environment.NewLine);
            }

            if (radioButton5.Checked == true)
            {
                if (textBox3.Text == "Y" || textBox3.Text == "y")
                    library[textBox1.Text].Onloan = true;
                if (textBox3.Text == "N" || textBox3.Text == "n")
                    library[textBox1.Text].Onloan = false;
                textBox4.Text = "";
                foreach (KeyValuePair<string, Book> b in library)
                    textBox4.Text += (b.Key + " : " + b.Value.Title + " : " + library[b.Key].Onloan + Environment.NewLine);
            }

            if (radioButton1.Checked == true && radioButton2.Checked == false)
            {
                String result = library[textBox1.Text].ISBN + " : " + library[textBox1.Text].Title + " : " + library[textBox1.Text].Onloan;
                        {
                            textBox4.Text = "";
                            textBox4.Text += result;
                        }
            }
            if (radioButton2.Checked == true && radioButton1.Checked == false)
            {
                String compare = textBox1.Text;
                {
                    foreach (KeyValuePair <string, Book> b in library)
                        if (b.Key == compare | b.Value.Title.Contains(compare))
                        {
                            textBox4.Text = "";
                            textBox4.Text += (b.Key + " : " + b.Value.Title + " : " + library[b.Key].Onloan + Environment.NewLine);
                        }
                }                
            }
        }
        private void Form1_Load(object sender, EventArgs e)
        {

        }
    }
}
namespace Companies_Trading_Data
{
    partial class Home
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.pageSetupDialog1 = new System.Windows.Forms.PageSetupDialog();
            this.treeHeight = new System.Windows.Forms.Label();
            this.treeCount = new System.Windows.Forms.Label();
            this.companiesListBox = new System.Windows.Forms.ListBox();
            this.updateRecordButton = new System.Windows.Forms.Button();
            this.removeRecordButton = new System.Windows.Forms.Button();
            this.netIncomeTextBox = new System.Windows.Forms.TextBox();
            this.operatingIncomeTextBox = new System.Windows.Forms.TextBox();
            this.employeesTextBox = new System.Windows.Forms.TextBox();
            this.totalAssetsTextBox = new System.Windows.Forms.TextBox();
            this.companyNameTextBox = new System.Windows.Forms.TextBox();
            this.companyNameLabel = new System.Windows.Forms.Label();
            this.netIncomeLabel = new System.Windows.Forms.Label();
            this.operatingIncomeLabel = new System.Windows.Forms.Label();
            this.totalAssetsLabel = new System.Windows.Forms.Label();
            this.label5 = new System.Windows.Forms.Label();
            this.CompanyLabel = new System.Windows.Forms.Label();
            this.sortButton = new System.Windows.Forms.Button();
            this.newBuyersLabel = new System.Windows.Forms.Label();
            this.newBuyerTextBox = new System.Windows.Forms.TextBox();
            this.compainesListLabel = new System.Windows.Forms.Label();
            this.companySearchTerm = new System.Windows.Forms.TextBox();
            this.label1 = new System.Windows.Forms.Label();
            this.SearchTermButton = new System.Windows.Forms.Button();
            this.tradingPartnerSearch = new System.Windows.Forms.RadioButton();
            this.partialKeySearch = new System.Windows.Forms.RadioButton();
            this.menuStrip1 = new System.Windows.Forms.MenuStrip();
            this.toolStripMenuItem1 = new System.Windows.Forms.ToolStripMenuItem();
            this.openFileToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.helpToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.resetProgramToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.howToUseHELPToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.menuStrip1.SuspendLayout();
            this.SuspendLayout();
            // 
            // treeHeight
            // 
            this.treeHeight.AutoSize = true;
            this.treeHeight.Font = new System.Drawing.Font("Marlett", 10F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.treeHeight.Location = new System.Drawing.Point(292, 549);
            this.treeHeight.Name = "treeHeight";
            this.treeHeight.Size = new System.Drawing.Size(133, 16);
            this.treeHeight.TabIndex = 16;
            this.treeHeight.Text = "AVL Tree Height = 0";
            // 
            // treeCount
            // 
            this.treeCount.AutoSize = true;
            this.treeCount.Font = new System.Drawing.Font("Marlett", 10F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.treeCount.Location = new System.Drawing.Point(64, 549);
            this.treeCount.Name = "treeCount";
            this.treeCount.Size = new System.Drawing.Size(131, 16);
            this.treeCount.TabIndex = 17;
            this.treeCount.Text = "AVL Tree Count = 0";
            // 
            // companiesListBox
            // 
            this.companiesListBox.FormattingEnabled = true;
            this.companiesListBox.Location = new System.Drawing.Point(274, 157);
            this.companiesListBox.Name = "companiesListBox";
            this.companiesListBox.Size = new System.Drawing.Size(197, 368);
            this.companiesListBox.TabIndex = 56;
            this.companiesListBox.SelectedIndexChanged += new System.EventHandler(this.companiesListBox_SelectedIndexChanged_1);
            // 
            // updateRecordButton
            // 
            this.updateRecordButton.ForeColor = System.Drawing.SystemColors.ActiveCaptionText;
            this.updateRecordButton.Location = new System.Drawing.Point(153, 469);
            this.updateRecordButton.Name = "updateRecordButton";
            this.updateRecordButton.Size = new System.Drawing.Size(100, 23);
            this.updateRecordButton.TabIndex = 57;
            this.updateRecordButton.Text = "Update Record";
            this.updateRecordButton.UseVisualStyleBackColor = true;
            this.updateRecordButton.Click += new System.EventHandler(this.updateRecordButton_Click);
            // 
            // removeRecordButton
            // 
            this.removeRecordButton.ForeColor = System.Drawing.SystemColors.ActiveCaptionText;
            this.removeRecordButton.Location = new System.Drawing.Point(44, 469);
            this.removeRecordButton.Name = "removeRecordButton";
            this.removeRecordButton.Size = new System.Drawing.Size(103, 23);
            this.removeRecordButton.TabIndex = 58;
            this.removeRecordButton.Text = "Remove Record";
            this.removeRecordButton.UseVisualStyleBackColor = true;
            this.removeRecordButton.Click += new System.EventHandler(this.removeRecordButton_Click);
            // 
            // netIncomeTextBox
            // 
            this.netIncomeTextBox.Location = new System.Drawing.Point(117, 185);
            this.netIncomeTextBox.Name = "netIncomeTextBox";
            this.netIncomeTextBox.Size = new System.Drawing.Size(136, 20);
            this.netIncomeTextBox.TabIndex = 2;
            // 
            // operatingIncomeTextBox
            // 
            this.operatingIncomeTextBox.Location = new System.Drawing.Point(117, 211);
            this.operatingIncomeTextBox.Name = "operatingIncomeTextBox";
            this.operatingIncomeTextBox.Size = new System.Drawing.Size(136, 20);
            this.operatingIncomeTextBox.TabIndex = 3;
            // 
            // employeesTextBox
            // 
            this.employeesTextBox.Location = new System.Drawing.Point(117, 263);
            this.employeesTextBox.Name = "employeesTextBox";
            this.employeesTextBox.Size = new System.Drawing.Size(136, 20);
            this.employeesTextBox.TabIndex = 5;
            // 
            // totalAssetsTextBox
            // 
            this.totalAssetsTextBox.Location = new System.Drawing.Point(117, 237);
            this.totalAssetsTextBox.Name = "totalAssetsTextBox";
            this.totalAssetsTextBox.Size = new System.Drawing.Size(136, 20);
            this.totalAssetsTextBox.TabIndex = 4;
            // 
            // companyNameTextBox
            // 
            this.companyNameTextBox.Location = new System.Drawing.Point(117, 159);
            this.companyNameTextBox.Multiline = true;
            this.companyNameTextBox.Name = "companyNameTextBox";
            this.companyNameTextBox.Size = new System.Drawing.Size(136, 20);
            this.companyNameTextBox.TabIndex = 1;
            // 
            // companyNameLabel
            // 
            this.companyNameLabel.AutoSize = true;
            this.companyNameLabel.Location = new System.Drawing.Point(21, 162);
            this.companyNameLabel.Name = "companyNameLabel";
            this.companyNameLabel.Size = new System.Drawing.Size(82, 13);
            this.companyNameLabel.TabIndex = 64;
            this.companyNameLabel.Text = "Company Name";
            // 
            // netIncomeLabel
            // 
            this.netIncomeLabel.AutoSize = true;
            this.netIncomeLabel.Location = new System.Drawing.Point(36, 190);
            this.netIncomeLabel.Name = "netIncomeLabel";
            this.netIncomeLabel.Size = new System.Drawing.Size(67, 13);
            this.netIncomeLabel.TabIndex = 65;
            this.netIncomeLabel.Text = "NET Income";
            // 
            // operatingIncomeLabel
            // 
            this.operatingIncomeLabel.AutoSize = true;
            this.operatingIncomeLabel.Location = new System.Drawing.Point(12, 216);
            this.operatingIncomeLabel.Name = "operatingIncomeLabel";
            this.operatingIncomeLabel.Size = new System.Drawing.Size(91, 13);
            this.operatingIncomeLabel.TabIndex = 66;
            this.operatingIncomeLabel.Text = "Operating Income";
            // 
            // totalAssetsLabel
            // 
            this.totalAssetsLabel.AutoSize = true;
            this.totalAssetsLabel.Location = new System.Drawing.Point(38, 242);
            this.totalAssetsLabel.Name = "totalAssetsLabel";
            this.totalAssetsLabel.Size = new System.Drawing.Size(65, 13);
            this.totalAssetsLabel.TabIndex = 67;
            this.totalAssetsLabel.Text = "Total Assets";
            // 
            // label5
            // 
            this.label5.AutoSize = true;
            this.label5.Location = new System.Drawing.Point(45, 268);
            this.label5.Name = "label5";
            this.label5.Size = new System.Drawing.Size(58, 13);
            this.label5.TabIndex = 68;
            this.label5.Text = "Employees";
            // 
            // CompanyLabel
            // 
            this.CompanyLabel.AutoSize = true;
            this.CompanyLabel.Location = new System.Drawing.Point(148, 138);
            this.CompanyLabel.Name = "CompanyLabel";
            this.CompanyLabel.Size = new System.Drawing.Size(51, 13);
            this.CompanyLabel.TabIndex = 69;
            this.CompanyLabel.Text = "Company";
            this.CompanyLabel.TextAlign = System.Drawing.ContentAlignment.MiddleCenter;
            // 
            // sortButton
            // 
            this.sortButton.ForeColor = System.Drawing.SystemColors.ActiveCaptionText;
            this.sortButton.Location = new System.Drawing.Point(108, 498);
            this.sortButton.Name = "sortButton";
            this.sortButton.Size = new System.Drawing.Size(87, 23);
            this.sortButton.TabIndex = 74;
            this.sortButton.Text = "Sort Records";
            this.sortButton.UseVisualStyleBackColor = true;
            this.sortButton.Click += new System.EventHandler(this.sortButton_Click);
            // 
            // newBuyersLabel
            // 
            this.newBuyersLabel.AutoSize = true;
            this.newBuyersLabel.Location = new System.Drawing.Point(121, 295);
            this.newBuyersLabel.Name = "newBuyersLabel";
            this.newBuyersLabel.Size = new System.Drawing.Size(39, 13);
            this.newBuyersLabel.TabIndex = 79;
            this.newBuyersLabel.Text = "Buyers";
            // 
            // newBuyerTextBox
            // 
            this.newBuyerTextBox.Location = new System.Drawing.Point(44, 316);
            this.newBuyerTextBox.Multiline = true;
            this.newBuyerTextBox.Name = "newBuyerTextBox";
            this.newBuyerTextBox.Size = new System.Drawing.Size(209, 133);
            this.newBuyerTextBox.TabIndex = 6;
            // 
            // compainesListLabel
            // 
            this.compainesListLabel.AutoSize = true;
            this.compainesListLabel.Location = new System.Drawing.Point(332, 138);
            this.compainesListLabel.Name = "compainesListLabel";
            this.compainesListLabel.Size = new System.Drawing.Size(78, 13);
            this.compainesListLabel.TabIndex = 80;
            this.compainesListLabel.Text = "Companies List";
            // 
            // companySearchTerm
            // 
            this.companySearchTerm.Location = new System.Drawing.Point(180, 48);
            this.companySearchTerm.Name = "companySearchTerm";
            this.companySearchTerm.Size = new System.Drawing.Size(164, 20);
            this.companySearchTerm.TabIndex = 81;
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(68, 51);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(106, 13);
            this.label1.TabIndex = 82;
            this.label1.Text = "Search For Company";
            // 
            // SearchTermButton
            // 
            this.SearchTermButton.ForeColor = System.Drawing.SystemColors.ActiveCaptionText;
            this.SearchTermButton.Location = new System.Drawing.Point(350, 48);
            this.SearchTermButton.Name = "SearchTermButton";
            this.SearchTermButton.Size = new System.Drawing.Size(75, 20);
            this.SearchTermButton.TabIndex = 83;
            this.SearchTermButton.Text = "Search ";
            this.SearchTermButton.UseVisualStyleBackColor = true;
            this.SearchTermButton.Click += new System.EventHandler(this.SearchTermButton_Click);
            // 
            // tradingPartnerSearch
            // 
            this.tradingPartnerSearch.AutoSize = true;
            this.tradingPartnerSearch.Location = new System.Drawing.Point(249, 90);
            this.tradingPartnerSearch.Name = "tradingPartnerSearch";
            this.tradingPartnerSearch.Size = new System.Drawing.Size(140, 17);
            this.tradingPartnerSearch.TabIndex = 84;
            this.tradingPartnerSearch.TabStop = true;
            this.tradingPartnerSearch.Text = "Trading Partners Search";
            this.tradingPartnerSearch.UseVisualStyleBackColor = true;
            // 
            // partialKeySearch
            // 
            this.partialKeySearch.AutoSize = true;
            this.partialKeySearch.Location = new System.Drawing.Point(108, 90);
            this.partialKeySearch.Name = "partialKeySearch";
            this.partialKeySearch.Size = new System.Drawing.Size(135, 17);
            this.partialKeySearch.TabIndex = 85;
            this.partialKeySearch.TabStop = true;
            this.partialKeySearch.Text = "Partial Keyword Search";
            this.partialKeySearch.UseVisualStyleBackColor = true;
            // 
            // menuStrip1
            // 
            this.menuStrip1.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.toolStripMenuItem1,
            this.helpToolStripMenuItem});
            this.menuStrip1.Location = new System.Drawing.Point(0, 0);
            this.menuStrip1.Name = "menuStrip1";
            this.menuStrip1.Size = new System.Drawing.Size(493, 24);
            this.menuStrip1.TabIndex = 86;
            this.menuStrip1.Text = "menuStrip1";
            // 
            // toolStripMenuItem1
            // 
            this.toolStripMenuItem1.DropDownItems.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.openFileToolStripMenuItem});
            this.toolStripMenuItem1.ForeColor = System.Drawing.SystemColors.ActiveCaptionText;
            this.toolStripMenuItem1.Name = "toolStripMenuItem1";
            this.toolStripMenuItem1.Size = new System.Drawing.Size(37, 20);
            this.toolStripMenuItem1.Text = "File";
            // 
            // openFileToolStripMenuItem
            // 
            this.openFileToolStripMenuItem.Name = "openFileToolStripMenuItem";
            this.openFileToolStripMenuItem.Size = new System.Drawing.Size(124, 22);
            this.openFileToolStripMenuItem.Text = "Open File";
            this.openFileToolStripMenuItem.Click += new System.EventHandler(this.openFileToolStripMenuItem_Click);
            // 
            // helpToolStripMenuItem
            // 
            this.helpToolStripMenuItem.DropDownItems.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.resetProgramToolStripMenuItem,
            this.howToUseHELPToolStripMenuItem});
            this.helpToolStripMenuItem.ForeColor = System.Drawing.SystemColors.ActiveCaptionText;
            this.helpToolStripMenuItem.Name = "helpToolStripMenuItem";
            this.helpToolStripMenuItem.Size = new System.Drawing.Size(44, 20);
            this.helpToolStripMenuItem.Text = "Help";
            // 
            // resetProgramToolStripMenuItem
            // 
            this.resetProgramToolStripMenuItem.Name = "resetProgramToolStripMenuItem";
            this.resetProgramToolStripMenuItem.Size = new System.Drawing.Size(175, 22);
            this.resetProgramToolStripMenuItem.Text = "Reset Program";
            this.resetProgramToolStripMenuItem.Click += new System.EventHandler(this.resetProgramToolStripMenuItem_Click);
            // 
            // howToUseHELPToolStripMenuItem
            // 
            this.howToUseHELPToolStripMenuItem.Name = "howToUseHELPToolStripMenuItem";
            this.howToUseHELPToolStripMenuItem.Size = new System.Drawing.Size(175, 22);
            this.howToUseHELPToolStripMenuItem.Text = "How To Use (HELP)";
            this.howToUseHELPToolStripMenuItem.Click += new System.EventHandler(this.howToUseHELPToolStripMenuItem_Click);
            // 
            // Home
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(0)))), ((int)(((byte)(0)))), ((int)(((byte)(64)))));
            this.BackgroundImageLayout = System.Windows.Forms.ImageLayout.Center;
            this.ClientSize = new System.Drawing.Size(493, 586);
            this.Controls.Add(this.partialKeySearch);
            this.Controls.Add(this.tradingPartnerSearch);
            this.Controls.Add(this.SearchTermButton);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.companySearchTerm);
            this.Controls.Add(this.compainesListLabel);
            this.Controls.Add(this.newBuyerTextBox);
            this.Controls.Add(this.newBuyersLabel);
            this.Controls.Add(this.sortButton);
            this.Controls.Add(this.CompanyLabel);
            this.Controls.Add(this.label5);
            this.Controls.Add(this.totalAssetsLabel);
            this.Controls.Add(this.operatingIncomeLabel);
            this.Controls.Add(this.netIncomeLabel);
            this.Controls.Add(this.companyNameLabel);
            this.Controls.Add(this.companyNameTextBox);
            this.Controls.Add(this.totalAssetsTextBox);
            this.Controls.Add(this.employeesTextBox);
            this.Controls.Add(this.operatingIncomeTextBox);
            this.Controls.Add(this.netIncomeTextBox);
            this.Controls.Add(this.removeRecordButton);
            this.Controls.Add(this.updateRecordButton);
            this.Controls.Add(this.companiesListBox);
            this.Controls.Add(this.treeCount);
            this.Controls.Add(this.treeHeight);
            this.Controls.Add(this.menuStrip1);
            this.ForeColor = System.Drawing.SystemColors.ButtonFace;
            this.MainMenuStrip = this.menuStrip1;
            this.Name = "Home";
            this.Text = "Company Trading Data by Kasim Hussain - 15078165";
            this.menuStrip1.ResumeLayout(false);
            this.menuStrip1.PerformLayout();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion
        private System.Windows.Forms.PageSetupDialog pageSetupDialog1;
        private System.Windows.Forms.Label treeHeight;
        private System.Windows.Forms.Label treeCount;
        private System.Windows.Forms.ListBox companiesListBox;
        private System.Windows.Forms.Button updateRecordButton;
        private System.Windows.Forms.Button removeRecordButton;
        private System.Windows.Forms.TextBox netIncomeTextBox;
        private System.Windows.Forms.TextBox operatingIncomeTextBox;
        private System.Windows.Forms.TextBox employeesTextBox;
        private System.Windows.Forms.TextBox totalAssetsTextBox;
        private System.Windows.Forms.TextBox companyNameTextBox;
        private System.Windows.Forms.Label companyNameLabel;
        private System.Windows.Forms.Label netIncomeLabel;
        private System.Windows.Forms.Label operatingIncomeLabel;
        private System.Windows.Forms.Label totalAssetsLabel;
        private System.Windows.Forms.Label label5;
        private System.Windows.Forms.Label CompanyLabel;
        private System.Windows.Forms.Button sortButton;
        private System.Windows.Forms.Label newBuyersLabel;
        private System.Windows.Forms.TextBox newBuyerTextBox;
        private System.Windows.Forms.Label compainesListLabel;
        private System.Windows.Forms.TextBox companySearchTerm;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Button SearchTermButton;
        private System.Windows.Forms.RadioButton tradingPartnerSearch;
        private System.Windows.Forms.RadioButton partialKeySearch;
        private System.Windows.Forms.MenuStrip menuStrip1;
        private System.Windows.Forms.ToolStripMenuItem toolStripMenuItem1;
        private System.Windows.Forms.ToolStripMenuItem openFileToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem helpToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem resetProgramToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem howToUseHELPToolStripMenuItem;
    }
}


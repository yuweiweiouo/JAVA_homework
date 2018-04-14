package ece155b;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.net.MalformedURLException;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import ece155b.data.*;
import ece155b.xml.XMLParser;

public class ProjectApplet extends JApplet implements ActionListener{
	private UserData userData;

	private Container mainPage;
	private JTextField companyName_textbox;
	private JTextField contactInfo_textbox;
	private JTextArea address_textbox;
	private JTable itemSold_table;
	private JTable itemNeeded_table;
	private DefaultTableModel itemSoldModel;
	private DefaultTableModel itemNeededModel;
	private JButton btnSaveInformation;
	private JButton btnLoadInformation;
	private JButton btnAddSoldRow;
	private JButton btnAddNeededRow;
	private JButton btnRemoveSoldRow;
	private JButton btnRemoveNeededRow;
	/**
	 * Initialize the contents of the frame.
	 */
	public void init() {
		mainPage = getContentPane();
		mainPage.setLayout(new FlowLayout());	
		mainPage.setBounds(100, 100, 1200, 800);

		makeGUI();

		userData = new UserData();

	}

	public void makeGUI(){

		JLabel lblNewLabel = new JLabel("Company Information");
		lblNewLabel.setBounds(40, 16, 156, 20);

		JLabel lblCompanyName = new JLabel("Company Name : ");
		lblCompanyName.setBounds(40, 52, 129, 20);

		JLabel lblNewLabel_1 = new JLabel("Contact Info : ");
		lblNewLabel_1.setBounds(40, 88, 103, 20);

		JLabel lblAddress = new JLabel("Address :");
		lblAddress.setBounds(40, 124, 68, 20);
		mainPage.setLayout(null);
		mainPage.add(lblNewLabel);
		mainPage.add(lblCompanyName);
		mainPage.add(lblNewLabel_1);
		mainPage.add(lblAddress);

		companyName_textbox = new JTextField();
		companyName_textbox.setBounds(189, 49, 261, 23);
		companyName_textbox.setColumns(10);
		mainPage.add(companyName_textbox);

		contactInfo_textbox = new JTextField();
		contactInfo_textbox.setColumns(10);
		contactInfo_textbox.setBounds(189, 86, 261, 23);
		mainPage.add(contactInfo_textbox);

		address_textbox = new JTextArea();
		address_textbox.setColumns(10);
		address_textbox.setBounds(189, 124, 261, 50);
		address_textbox.setBorder(BorderFactory.createLineBorder(Color.gray));
		address_textbox.setLineWrap(true);
		mainPage.add(address_textbox);

		JLabel lblNewLabel_2 = new JLabel("Items sold to customer");
		lblNewLabel_2.setBounds(15, 235, 269, 29);
		mainPage.add(lblNewLabel_2);

		itemSoldModel = new DefaultTableModel(
				new Object[][]{
					{"name","0","0"}}, 
				new Object[]{"Name","Price","Available"});
		itemSold_table = new JTable(itemSoldModel);
		itemSold_table.setBackground(Color.white);
		JScrollPane scrollPane1 = new JScrollPane(itemSold_table);
		scrollPane1.setBounds(25, 276, 443, 203);
		mainPage.add(scrollPane1);		

		JLabel lblNewLabel_3 = new JLabel("Items needed from providers  ");
		lblNewLabel_3.setBounds(582, 239, 238, 20);
		mainPage.add(lblNewLabel_3);

		btnRemoveSoldRow = new JButton("Delete selected row");
		btnRemoveSoldRow.setBounds(266, 495, 184, 29);
		btnRemoveSoldRow.addActionListener(this);
		mainPage.add(btnRemoveSoldRow);

		btnAddNeededRow = new JButton("Add a row");
		btnAddNeededRow.setBounds(633, 495, 115, 29);
		btnAddNeededRow.addActionListener(this);
		mainPage.add(btnAddNeededRow);

		itemNeededModel = new DefaultTableModel(
				new Object[][]{
					{"name","0"}}, 
				new Object[]{"Name","Required"});
		itemNeeded_table = new JTable(itemNeededModel);
		itemNeeded_table.setBackground(Color.white);
		JScrollPane scrollPane2 = new JScrollPane(itemNeeded_table);
		scrollPane2.setBounds(582, 276, 521, 203);
		mainPage.add(scrollPane2);	

		btnRemoveNeededRow = new JButton("Delete selected row");
		btnRemoveNeededRow.setBounds(839, 495, 196, 29);
		btnRemoveNeededRow.addActionListener(this);
		mainPage.add(btnRemoveNeededRow);

		btnAddSoldRow = new JButton("Add a row"); 		
		btnAddSoldRow.setBounds(55, 495, 115, 29);
		btnAddSoldRow.addActionListener(this);
		mainPage.add(btnAddSoldRow);

		btnSaveInformation = new JButton("Save Information");
		btnSaveInformation.setBounds(40, 607, 174, 29);
		btnSaveInformation.addActionListener(this);
		mainPage.add(btnSaveInformation);

		btnLoadInformation = new JButton("Load Information");
		btnLoadInformation.setBounds(266, 607, 174, 29);
		btnLoadInformation.addActionListener(this);
		mainPage.add(btnLoadInformation);
	}

	public void actionPerformed(ActionEvent e){		
		if(e.getSource() == btnAddSoldRow){
			btnAddSoldRow_Click();			
		}else if(e.getSource() == btnRemoveSoldRow){
			btnRemoveSoldRow_Click();	
		}else if(e.getSource() == btnAddNeededRow){
			btnAddNeededRow_Click();	
		}else if(e.getSource() == btnRemoveNeededRow){
			btnRemoveNeededRow_Click();	
		}else if(e.getSource() == btnSaveInformation){
			btnSaveInformation_Click();	
		}else if(e.getSource() == btnLoadInformation){
			btnLoadInformation_Click();	
		}
	}

	private void btnAddSoldRow_Click(){
		itemSoldModel.addRow(new Object[]{"","",""});
	}

	private void btnRemoveSoldRow_Click(){
		int[] selectedRows = itemSold_table.getSelectedRows();
		for(int i=selectedRows.length-1;i>=0;i--){
			itemSoldModel.removeRow(selectedRows[i]);	
		}
	}

	private void btnAddNeededRow_Click(){
		itemNeededModel.addRow(new Object[]{"","",""});
	}

	private void btnRemoveNeededRow_Click(){
		int[] selectedRows = itemNeeded_table.getSelectedRows();
		for(int i=selectedRows.length-1;i>=0;i--){
			itemNeededModel.removeRow(selectedRows[i]);	
		}
	}

	private void btnSaveInformation_Click()
	{
		try{
			// - - - - - - - - - - - - - - - - - - - - -
			//save distributor value
			userData.distributor.name = companyName_textbox.getText();
			userData.distributor.contact = contactInfo_textbox.getText();
			userData.distributor.address = address_textbox.getText();
			userData.distributor.clearAllIteams();

			for(Object o:itemSoldModel.getDataVector()){
				Vector v = (Vector) o;			
				SellSupply ss = new SellSupply();
				ss.supply.name = v.get(0).toString();
				ss.supply.price = Double.parseDouble(v.get(1).toString());
				ss.amountAvailable = Integer.parseInt(v.get(2).toString());
				userData.distributor.addSellItem(ss);			
			}

			for(Object o:itemNeededModel.getDataVector()){
				Vector v = (Vector) o;
				NeedSupply ns = new NeedSupply();
				ns.supply.name = v.get(0).toString();
				ns.amountNeeded = Integer.parseInt(v.get(1).toString());
				userData.distributor.addNeedItem(ns);			
			}
			System.out.print(userData.distributor.toString());

			// - - - - - - - - - - - - - - - - - - - - -
			//save process
			String toSaveFileName = JOptionPane.showInputDialog(mainPage, "幫檔案取個名字吧\n(預設為:\"hello.xml\")");
			if(toSaveFileName == null){
				return;
			}else if(toSaveFileName.isEmpty()){
				toSaveFileName = "hello";
			}

			System.out.println(toSaveFileName);
			if(new File(UserData.fileDir + toSaveFileName + ".xml").exists())
			{
				String[] options={"蓋掉它","還是算了"};				
				int opt = JOptionPane.showOptionDialog(mainPage, 
						"有一樣的檔案了，要不要覆蓋，要嗎要嗎?", 
						"訊息", 
						JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE,
						null,
						options,
						"蓋掉它");
				if(opt == JOptionPane.NO_OPTION){
					return;
				}
			}
			userData.toXmlFile(toSaveFileName + ".xml");
		} catch(NumberFormatException e){
			JOptionPane.showMessageDialog(mainPage, "你看看是不是表格有空白 (◔ д◔)\n不然就是該打數字 你打成文字");
		} catch(Exception e){
			JOptionPane.showMessageDialog(mainPage, e);		
		}
	}	

	private void btnLoadInformation_Click(){	
		try{
			System.out.println("load file button clicked");	
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setCurrentDirectory(new File(UserData.fileDir));
			int returnValue = fileChooser.showOpenDialog(mainPage);
			if (returnValue == JFileChooser.APPROVE_OPTION) 
			{ 
				File selectedFile = fileChooser.getSelectedFile();
				userData.readXmlFile(selectedFile.getAbsolutePath());
				System.out.println(selectedFile.getName());
			} 
			// - - - - - - - - - - - - - - - - - - - - -
			//set company's info
			companyName_textbox.setText(userData.distributor.name);
			contactInfo_textbox.setText(userData.distributor.contact);
			address_textbox.setText(userData.distributor.address);
			// - - - - - - - - - - - - - - - - - - - - -
			//clean and add SellSupply to table
			Vector<SellSupply> sellitems = userData.distributor.getSellItems();

			for(int row=itemSoldModel.getRowCount()-1;row>=0;row--){
				itemSoldModel.removeRow(row);
			}

			sellitems.forEach(si -> {			
				itemSoldModel.addRow(new Object[]{
						si.supply.name, 
						si.supply.price, 
						si.amountAvailable
				});
			});
			// - - - - - - - - - - - - - - - - - - - - -
			//clean and add NeedSupply to table
			Vector<NeedSupply> needitems = userData.distributor.getNeedItems();


			for(int row=itemNeededModel.getRowCount()-1;row>=0;row--){
				itemNeededModel.removeRow(row);
			}

			needitems.forEach(ni -> {			
				itemNeededModel.addRow(new Object[]{
						ni.supply.name, 
						ni.amountNeeded
				});
			});	
		} catch(Exception e){
			JOptionPane.showMessageDialog(mainPage, e);
		}
	}


	public void destroy() { 
		//destructor that writes the doctor object to file before closing
		System.out.println("...closing");
		//toXmlFile(...);
	}	
}



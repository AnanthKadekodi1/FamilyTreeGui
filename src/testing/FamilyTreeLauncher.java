/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testing;

/**
 * Name: FamilyTreeLauncher 
 * Date: 15/5/18
 * File: FamilyTreeLauncher.java
 * 
 * Purpose: An object class that stores primitive values pertaining to a FamilyTreeLauncher class,
 * the values it can store are: Strings relating to family member details (first name, surname and life description) and address (street number, street name, suburb and postcode).
 * Gender is stored as gender
 * Mother, father and spouse are stored as family member variables 
 * This is a graphical user interface (GUI) program for recording information about a family tree. The program can be started in viewing or editing mode.
 * Viewing mode: The GUI shows the details of one person at a time 
 * Editing mode: The user can choose to start a new empty family tree or edit an existing one
 * 
 *
 * 
 * Inputs are: 
 * Name: String
 * Surname: String
 * Maiden Name: String
 * Gender: gender
 * Life Description: String
 * Street number: String
 * Street Name: String
 * Suburb: String
 * Postcode: String
 * Can also load existing family tree created and edit it
 *
 * Output: The GUI stores the values and creates a family tree as the values are inputted. 
 * The family tree can be saved or a new family tree can be created 
 * The family member can also be deleted
 *  @author Ananth Kadekodi <32920719> - External - Tutor: Ferdous Sohel
 */


import java.awt.Component;
import java.awt.EventQueue;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.JTextField;
import javax.swing.JComboBox;

/**
 * Main method of the program. 
 * Purpose: Create the GUI (button, labels, TextField) and default mutable tree for saving the family members and family tree creation
 * @author Ananth
 */
public class FamilyTreeLauncher {

        // Variables
	private JFrame frame;
	private JLabel lblNewLabel ;
	private JButton loadTreeButton,saveTreeButton,createTreeButton,btnAddRelative,btnEdit,btnDeleteMember;
	private TreeImpl tree;
	DefaultMutableTreeNode root ;
	JTree treeView;
	private JTextField txtName;
	private JTextField txtSurName;
	private JTextField txtMaiden;
	private JTextField txtGender;
	private JTextField txtLife;
	private JTextField txtStreetNumber;
	private JTextField txtStreetName;
	private JTextField txtSuburb;
	private JTextField txtPostCode;
	private JLabel lblRelation;
	private JComboBox comboRelation;
	private JLabel lblFather, lblMother,lblSpouse,lblChildren;
	private static String currentNodeSelected;

private	FamilyMember[] familyMember;
private Address[] memberAddress;
private int index;
        // Get value for current selected node
	public String getCurrentNodeSelected() {
		return currentNodeSelected;
	}

        // Set the value for current node selected
	public void setCurrentNodeSelected(String currentNodeSelected) {
		this.currentNodeSelected = currentNodeSelected;
	}


        /**
         * The main function ensures that a new family tree is created
         * The try-catch method ensure that exceptions are handled well
         * @param args 
         */
	public static void main(String[] args) 
        {
		EventQueue.invokeLater(new Runnable() 
                {
			public void run() {
				try {
					FamilyTreeLauncher window = new FamilyTreeLauncher();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
         * Function to create a dummy family tree where all values are NULLL
         */
	public FamilyTreeLauncher() 
        {

		FamilyMember dummy = new FamilyMember("",null,null,"","", new Address("","","",""));
		tree = new TreeImpl(dummy);
		initialize();
		createNewTree();
	}

        private void next()
        {
            index++;

            if( index > 4 )
                index = 0;
        }
        /**
         * Function to create the GUI of the family tree application
         */
	private void initialize() 
        {
                index = 0;
                familyMember = new FamilyMember[5];
                memberAddress = new Address[5];
                
                // Hardcode values of the individual family members for testing purposes
                familyMember[1] = new FamilyMember("S - ", "Smith", "Smith", "Enjoyed being a farmer throughout life", "male", null);
		memberAddress[1] = new Address("101", "Bedford Rd", "Ardross", "6153");
                familyMember[0] = new FamilyMember(" B - ", "Smith", "Jones", "Wanted to be an actress", "female", null);
		memberAddress[0] = new Address("101", "Bedford Rd", "Ardross", "6153");
                familyMember[3] = new FamilyMember("E - ", "Smith", "Davies", "Working as a doctor", "female", null);
		memberAddress[3] = new Address("64", "Engler Av", "Murdoch", "6154");
                familyMember[2] = new FamilyMember("K - ", "Smith", "Smith", "Drives a mercedes", "male", null);
		memberAddress[2] = new Address("54", "Ard Street", "South Perth", "6151");
                familyMember[4] = new FamilyMember("M - ", "Hooper", "Hooper", "Olympic gold medalist of 100m", "male", null);
		memberAddress[4] = new Address("103", "Esplanade", "Perth", "6000");

                // Create heading for the program title
		frame = new JFrame();
		frame.setBounds(100, 100, 701, 620);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Family Tree");
		frame.getContentPane().setLayout(null);
		
                // Create the 'Welcome to the Family Tree Application' heading
		lblNewLabel = new JLabel("Welcome to the Family Tree Application");
		lblNewLabel.setFont(lblNewLabel.getFont().deriveFont((float)17.0));
		lblNewLabel.setBounds(34, 6, 400, 30);
		frame.getContentPane().add(lblNewLabel);
		
                // Load Tree
		loadTreeButton = new JButton("Load Tree");
		loadTreeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			
				//choose serialized file and load it
                                // Add relative to existing family member
				JFileChooser c = new JFileChooser();
				if(c.showOpenDialog(null)==JFileChooser.APPROVE_OPTION)
				{
					tree=tree.loadTreeFromFile(c.getSelectedFile().getPath());
				    treeView.setModel(loadTree());
					enableTextBoxes(false);
					btnAddRelative.setText("Add Relative");
					btnEdit.setVisible(true);
					btnDeleteMember.setVisible(true);
				}
			}
		});
		loadTreeButton.setBounds(20, 36, 110, 23);
		frame.getContentPane().add(loadTreeButton);
		
                // Save Tree button GUI 
		saveTreeButton = new JButton("Save Tree");
		saveTreeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//savetree
				String filename = JOptionPane.showInputDialog("Enter file name");
				tree.persistTree(filename);
			}
		});
		saveTreeButton.setBounds(140, 36, 110, 23);
		frame.getContentPane().add(saveTreeButton);
		
                // Create tree button GUI 
		createTreeButton = new JButton("Create Tree");
		createTreeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				createNewTree();
				}
		});
		createTreeButton.setBounds(270, 36, 110, 23);
		frame.getContentPane().add(createTreeButton);
		
		treeView = new JTree(loadTree());
		treeView.setBounds(10, 70, 259, 501);
		frame.getContentPane().add(treeView);
		
                // Label - Name
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(279, 92, 86, 14);
		frame.getContentPane().add(lblName);
		
		txtName = new JTextField();
		txtName.setBounds(372, 89, 86, 20);
		frame.getContentPane().add(txtName);
		txtName.setColumns(10);
		
                // Label  -Surname
		JLabel lblSurname = new JLabel("Surname");
		lblSurname.setBounds(279, 120, 86, 14);
		frame.getContentPane().add(lblSurname);
		
		txtSurName = new JTextField();
		txtSurName.setColumns(10);
		txtSurName.setName("this is surname");
		txtSurName.setBounds(372, 117, 86, 20);
		frame.getContentPane().add(txtSurName);
		
                // Label - Maiden Name
		JLabel lblGender = new JLabel("Maiden Name");
		lblGender.setBounds(279, 148, 86, 14);
		frame.getContentPane().add(lblGender);
		
		txtMaiden = new JTextField();
		txtMaiden.setColumns(10);
		txtMaiden.setBounds(372, 145, 86, 20);
		frame.getContentPane().add(txtMaiden);
		
                // Label - Gender
		JLabel lblGender_1 = new JLabel("Gender");
		lblGender_1.setBounds(279, 178, 86, 14);
		frame.getContentPane().add(lblGender_1);
		
		txtGender = new JTextField();
		txtGender.setColumns(10);
		txtGender.setBounds(372, 175, 86, 20);
		frame.getContentPane().add(txtGender);
		
                
                // Label - Life Description
		JLabel lblLifeDescription = new JLabel("Life Description");
		lblLifeDescription.setBounds(279, 206, 86, 14);
		frame.getContentPane().add(lblLifeDescription);
		
		txtLife = new JTextField();
		txtLife.setColumns(10);
		txtLife.setBounds(372, 203, 175, 31);
		frame.getContentPane().add(txtLife);
		
                // Label - Street Number
		JLabel lblStreetNumber = new JLabel("Street Number");
		lblStreetNumber.setBounds(279, 267, 86, 14);
		frame.getContentPane().add(lblStreetNumber);
		
		txtStreetNumber = new JTextField();
		txtStreetNumber.setColumns(10);
		txtStreetNumber.setBounds(372, 264, 86, 20);
		frame.getContentPane().add(txtStreetNumber);
		
                // Label  Street Name
		JLabel lblStreetName = new JLabel("Street Name");
		lblStreetName.setBounds(279, 295, 86, 14);
		frame.getContentPane().add(lblStreetName);
		
		txtStreetName = new JTextField();
		txtStreetName.setColumns(10);
		txtStreetName.setBounds(372, 292, 86, 20);
		frame.getContentPane().add(txtStreetName);
		
                // Label - Suburb
		JLabel lblSuburb = new JLabel("Suburb");
		lblSuburb.setBounds(279, 323, 86, 14);
		frame.getContentPane().add(lblSuburb);
		
		txtSuburb = new JTextField();
		txtSuburb.setColumns(10);
		txtSuburb.setBounds(372, 320, 86, 20);
		frame.getContentPane().add(txtSuburb);
		
                // Label - Postcode
		JLabel lblPostcode = new JLabel("Postcode");
		lblPostcode.setBounds(279, 351, 86, 14);
		frame.getContentPane().add(lblPostcode);
		
		txtPostCode = new JTextField();
		txtPostCode.setColumns(10);
		txtPostCode.setBounds(372, 348, 86, 20);
		frame.getContentPane().add(txtPostCode);
		
                // Label for the drop down buttons - father, mother, spouse and children 
		lblFather = new JLabel("");
		lblFather.setBounds(279, 413, 86, 14);
		frame.getContentPane().add(lblFather);
		
	
		lblMother = new JLabel("");
		lblMother.setBounds(279, 441, 86, 14);
		frame.getContentPane().add(lblMother);
		
		
		lblSpouse = new JLabel("");
		lblSpouse.setBounds(279, 469, 86, 14);
		frame.getContentPane().add(lblSpouse);
		
		
		lblChildren = new JLabel("");
		lblChildren.setBounds(279, 498, 86, 14);
		frame.getContentPane().add(lblChildren);
		
		// Label - edit button
		btnEdit = new JButton("Edit Details");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
                            // Edit the details of family member
				if(btnEdit.getText().equals("Edit Details"))
				{
					enableTextBoxes(true);
					btnEdit.setText("Update");
					btnDeleteMember.setVisible(false);
				}
				else
				{
					//Get current node and update the tree
					
					FamilyMember memberExisting = tree.getDetailsForMember(FamilyTreeLauncher.currentNodeSelected);
					FamilyMember updatedMember = new FamilyMember(txtName.getText(), txtSurName.getText(), txtMaiden.getText(), txtLife.getText(), txtGender.getText(), null);
					Address updatedMemberAddress = new Address(txtStreetNumber.getText(),txtStreetName.getText(),txtSuburb.getText(),txtPostCode.getText());
					updatedMember.setAddress(updatedMemberAddress);
					
					
				
					tree.updateMember(FamilyTreeLauncher.currentNodeSelected,updatedMember);
					
					
					enableTextBoxes(false);
					btnEdit.setText("Edit Details");
					btnDeleteMember.setVisible(true);
				}
					
			}
		});
		btnEdit.setBounds(294, 537, 110, 23);
		frame.getContentPane().add(btnEdit);
		
                // Label - add relative
		btnAddRelative = new JButton("Add Relative");
		btnAddRelative.addActionListener(new ActionListener() {
                    // Add relative action respnse
			public void actionPerformed(ActionEvent arg0) {
				if(btnAddRelative.getText().equals("Add Relative"))
				{
                                        // Relative values updated
					turnOnRelativeFields(true);
					btnEdit.setVisible(false);
					btnDeleteMember.setVisible(false);
					btnAddRelative.setText("Update");
				}
                                
                                // Addition of root to family tree
                                // Custom values implemented for Tesitng purposes
				else if(btnAddRelative.getText().equals("ADD ROOT"))
				{
                                    txtName.setText("Karl");
                                    txtSurName.setText("Smith");
                                    txtMaiden.setText("Smith");
                                    txtGender.setText("male");
                                    txtLife.setText("Descendent of captain cook");
                                    txtStreetNumber.setText("100");
                                    txtStreetName.setText("Mill pt road");
                                    txtSuburb.setText("South Perth");
                                    txtPostCode.setText("6151");
					FamilyMember rootMember = new FamilyMember("name - ROOT", "Smith", "Maiden name", "Life Description", "Male", null);
					Address rootMemberAddress = new Address("100", "Neverland", "Suburb", "10000");

//					
					rootMember.setAddress(rootMemberAddress);
					
                                      
					currentNodeSelected=rootMember.getFirstName();
					
					tree = new TreeImpl(rootMember);
					treeView.setModel(loadTree());
					
					enableTextBoxes(false);
					btnAddRelative.setText("Add Relative");
					btnEdit.setVisible(true);
					btnDeleteMember.setVisible(true);
					
				}
				else
				{
					//Get current node, add to Family Member and add relative to the current node
					
					System.out.println("FamilyTreeLauncher.currentNodeSelected=" + FamilyTreeLauncher.currentNodeSelected);
					
					FamilyMember memberExisting = tree.getDetailsForMember(FamilyTreeLauncher.currentNodeSelected);
					
					//Hardcode custom values to crosscheck the working of the program
                                    String s = (String)comboRelation.getSelectedItem();
                                    txtName.setText(familyMember[index].getFirstName() + s);
                                    txtSurName.setText(familyMember[index].getSurName());
                                    txtMaiden.setText(familyMember[index].getSurNameAfterMarriage());
                                    txtGender.setText(familyMember[index].getGender());
                                    txtLife.setText(familyMember[index].getLife());
                                    txtStreetNumber.setText(memberAddress[index].getStreetNumber());
                                    txtStreetName.setText(memberAddress[index].getStreetName());
                                    txtSuburb.setText(memberAddress[index].getSuburb());
                                    txtPostCode.setText(memberAddress[index].getPostalCode());
                                    FamilyMember relative = new FamilyMember(familyMember[index].getFirstName() + s, familyMember[index].getSurName(), familyMember[index].getSurNameAfterMarriage(), familyMember[index].getLife(), familyMember[index].getGender(), null );
                                    Address relativeAddress = new Address(memberAddress[index].getStreetNumber(), memberAddress[index].getStreetName(), memberAddress[index].getSuburb(), memberAddress[index].getPostalCode());
                                    next();

//					
					relative.setAddress(relativeAddress);
					
					
						
					
					int relationIndex = comboRelation.getSelectedIndex();
					
                                        // Switch case statement to set the relevant family member (father, mother, spouse and children)
					switch(relationIndex)
					{
						case 0: memberExisting.setFather(relative); break;
						case 1: memberExisting.setMother(relative); break;
						case 2: memberExisting.setSpouse(relative); break;
						case 3: memberExisting.addChild(relative); break;
					}
					
					tree.getAllMembers().add(relative);
					
					// Add relative button function
					turnOnRelativeFields(false);
					btnEdit.setVisible(true);
					btnDeleteMember.setVisible(true);
					btnAddRelative.setText("Add Relative");
					
					
					treeView.setModel(loadTree());
				}
			}
		});
		btnAddRelative.setBounds(417, 537, 110, 23);
		frame.getContentPane().add(btnAddRelative);
		
                // Label for the relation 
		lblRelation = new JLabel("Relation");
		lblRelation.setBounds(279, 426, 86, 14);
		frame.getContentPane().add(lblRelation);
		
                // Relative drop down button
		comboRelation = new JComboBox();
		comboRelation.setBounds(372, 423, 98, 20);
		comboRelation.addItem("Father");
		comboRelation.addItem("Mother");
		comboRelation.addItem("Spouse");
		comboRelation.addItem("Children");
		comboRelation.setSelectedIndex(0);
		frame.getContentPane().add(comboRelation);
		
                // Delete memeber action
		btnDeleteMember = new JButton("Delete Member");
		btnDeleteMember.addActionListener(new ActionListener()
                {
			public void actionPerformed(ActionEvent arg0) 
                        {
				tree.removeMember(FamilyTreeLauncher.currentNodeSelected);
				//tree = new TreeImpl(root);
				treeView.setModel(loadTree());
			}
		});
		btnDeleteMember.setBounds(545, 537, 130, 23);
		frame.getContentPane().add(btnDeleteMember);
		
		treeView.addMouseListener(new MouseAdapter() 
                {
		      public void mouseClicked(MouseEvent me) 
                      {
		        nodeClicked(me);
		      }
		});
		
		enableTextBoxes(false);
		turnOnRelativeFields(false);
	}
	
        /**
         * Function to create a new tree
         */
	protected void createNewTree() 
        {
		turnOnRelativeFields(true);
		turnOnRelativeFields(false);
		enableTextBoxes(true);
		
		lblFather.setVisible(false); 
		lblMother.setVisible(false);
		lblSpouse.setVisible(false);
		lblChildren.setVisible(false);
		
		btnAddRelative.setText("ADD ROOT");
		btnEdit.setVisible(false);
		btnDeleteMember.setVisible(false);
		treeView.setModel(null);
		tree = new TreeImpl(null);
	}

        
        /**
         * Function to ensure that the relations are not visible at the start
         * @param flag 
         */
	void turnOnRelativeFields(boolean flag)
	{
		comboRelation.setVisible(flag);
		lblRelation.setVisible(flag);
		lblFather.setVisible(!flag); 
		lblMother.setVisible(!flag);
		lblSpouse.setVisible(!flag);
		lblChildren.setVisible(!flag);
		
		// IF- statement to ensure that all the values are empty
		if(flag)
		{
			txtName.setText("");
			txtSurName.setText("");
			txtMaiden.setText("");
			txtGender.setText("");
			txtLife.setText("");
			txtStreetNumber.setText("");
			txtStreetName.setText("");
			txtSuburb.setText("");
			txtPostCode.setText("");
		}
		
                // Set values to name, surname, maiden, gender etc
		txtName.setEnabled(flag);
		txtSurName.setEnabled(flag);
		txtMaiden.setEnabled(flag);
		txtGender.setEnabled(flag);
		txtLife.setEnabled(flag);
		txtStreetNumber.setEnabled(flag);
		txtStreetName.setEnabled(flag);
		txtSuburb.setEnabled(flag);
		txtPostCode.setEnabled(flag);
		
	}
	
	
	/**
         * Function when a particular node is selected
         * @param me 
         */
	void nodeClicked(MouseEvent me) 
        {
	    TreePath tp = treeView.getPathForLocation(me.getX(), me.getY());
	    
	    if (tp != null)
	    {
	    	this.setCurrentNodeSelected(tp.getLastPathComponent().toString());
	    	setTextFields(this.tree.getDetailsForMember(tp.getLastPathComponent().toString()));
	    	
	    }
	    
	}
	
        /**
         * Function to set the values of the text fields
         * @param member 
         */
	public void setTextFields(FamilyMember member)
	{
            if(member==null) return;	
            txtName.setText(member.getFirstName());
            txtSurName.setText(member.getSurName());
            txtMaiden.setText(member.getSurNameAfterMarriage());
            txtGender.setText(member.getGender());
            txtLife.setText(member.getLife());
            txtStreetNumber.setText(member.getAddress().getStreetNumber());
            txtStreetName.setText(member.getAddress().getStreetName());
            txtSuburb.setText(member.getAddress().getSuburb());
            txtPostCode.setText(member.getAddress().getPostalCode());
	}
	
	
        /**
         * Function to ensure that the text boxes are displayed 
         * @param flag 
         */
	public void enableTextBoxes(boolean flag)
	{
            int count=0;
            for(Component component : frame.getContentPane().getComponents())
            {
		if(component instanceof JTextField)
		{
                    component.setEnabled(flag);
		}
            }	
	}
	
        /**
         * Function to ensure that the default mutable tree is loaded 
         * @return 
         */
	public  DefaultTreeModel loadTree()
	{
            root = new DefaultMutableTreeNode(tree.getRoot().getFirstName());
            appendTree(root,tree.getRoot());
            return new DefaultTreeModel(root);
	}
        

        /**
         * Function to ensure that the nodes of the default mutable tree are loaded
         * @return 
         */
	public  DefaultMutableTreeNode loadTreeNode()
	{
            root = new DefaultMutableTreeNode(tree.getRoot().getFirstName());
            appendTree(root,tree.getRoot());
            return root;
	}
	
        /**
         * Function to ensure that the values are appended in the nodes of the default mutable tree
         * @param root2
         * @param member 
         */
	public void appendTree(DefaultMutableTreeNode root2, FamilyMember member)
	{
            // Add children to the node
            if(member.getChildren().size()>0)
            {
                DefaultMutableTreeNode children = new DefaultMutableTreeNode("children");
                root2.add(children);
                
                        for(FamilyMember i : member.getChildren())
			{
                            DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(i.getFirstName());
                            children.add(newNode);
                            appendTree(newNode,i);
			}
            }
		
            // Add spouse to the node
            if(member.getSpouse()!=null)
            {
                DefaultMutableTreeNode spouse = new DefaultMutableTreeNode("spouse");
                root2.add(spouse);
			
                DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(member.getSpouse().getFirstName());
		spouse.add(newNode);
		appendTree(newNode,member.getSpouse());
            }
		
            // Add father to the node
            if(member.getFather()!=null)
            {
                DefaultMutableTreeNode father = new DefaultMutableTreeNode("father");
		root2.add(father);	
                DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(member.getFather().getFirstName());
		father.add(newNode);
		appendTree(newNode,member.getFather());
            }
		
            // Add mother to the node
            if(member.getMother()!=null)
            {
                DefaultMutableTreeNode mother = new DefaultMutableTreeNode("mother");
		root2.add(mother);
			
		DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(member.getMother().getFirstName());
		mother.add(newNode);
		appendTree(newNode,member.getMother());
            }
	}
}

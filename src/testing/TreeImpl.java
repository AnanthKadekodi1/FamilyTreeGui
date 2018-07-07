/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testing;



import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
/**
 * This class is used to create the family tree. 
 * It contains functions of adding family members to nodes; ensuring that the tree exists, loading tree from a file;
 * updating members and deleting members from a tree
 * @author Ananth
 */
public class TreeImpl {

        // Variables
	private FamilyMember root=null;
        private List<FamilyMember> allMembers = new ArrayList<>();
        
        /////////////////////////////////////////////////////
        //////////////// Get section
        /////////////////////////////////////////////////////
        
        /**
         * Get all family members from the array list
         * @return 
         */
        public List<FamilyMember> getAllMembers() 
        {
		return allMembers;
	}
        
        /**
         * Get the root of the tree
         * @return 
         */
	public FamilyMember getRoot() 
        {
		return root;
	}

         /**
         * Get details for all family members
         * @param member
         * @return 
         */
	public FamilyMember getDetailsForMember(String member)
	{
		
		for(FamilyMember m : this.getAllMembers())
		{
			if(m.getFirstName().equals(member))
				return m;
		}
		
		return null;
	}
        ////////////////////////////////////////////////////////
        //////// Set section
        //////////////////////////////////////////////////////
        
        /**
         * Set root to family tree 
         * @param root 
         */
	public void setRoot(FamilyMember root) 
        {
		this.root = root;
	}

	

        /**
         * Add values of all members into the root of tree
         * @param allMembers 
         */
	public void setAllMembers(List<FamilyMember> allMembers) 
        {
		this.allMembers = allMembers;
		this.setRoot(this.allMembers.get(0));
	}

	
	/**
         * Function to add family members to tree root
         * @param root 
         */
	public TreeImpl(FamilyMember root)
	{
		this.root=root;
		addMembers(root);
	}
	
        /**
         * Adding members to tree nodes
         * @param node 
         */
	private void addMembers(FamilyMember node)
	{
		if(node==null) return;
		allMembers.add(node);
		addMembers(node.getFather());
		addMembers(node.getMother());
		addMembers(node.getSpouse());
		
		
		for(FamilyMember child : node.getChildren())
			addMembers(child);
		
	}
	
        /**
         * Add spouse to family tree
         * @param index
         * @param spouse 
         */
	public void addSpouse(int index, FamilyMember spouse)
	{
		for(FamilyMember member : allMembers)
		{
			if(member.getMemberID()==index)
			{
				member.setSpouse(spouse);
				allMembers.add(spouse);
				return;
			}
		}
	}
	
        
        /**
         * Add father to family tree
         * @param index
         * @param father 
         */
	public void addFather(int index, FamilyMember father)
	{
		for(FamilyMember member : allMembers)
		{
			if(member.getMemberID()==index)
			{
				member.setFather(father);
				allMembers.add(father);
				return;
			}
		}
	}
	
        /**
         * Add mother to family tree 
         * @param index
         * @param mother 
         */
	public void addMother(int index, FamilyMember mother)
	{
		for(FamilyMember member : allMembers)
		{
			if(member.getMemberID()==index)
			{
				member.setMother(mother);
				allMembers.add(mother);
				return;
			}
		}
	}
	
        
        /**
         * Add child to family tree
         * @param index
         * @param child 
         */
	public void addChild(int index, FamilyMember child)
	{
		for(FamilyMember member : allMembers)
		{
			if(member.getMemberID()==index)
			{
				member.setSpouse(child);
				allMembers.add(child);
				return;
			}
		}
	}
	
        
        /**
         * Read file and ensure that the tree exists
         * @param filename 
         */
	public void persistTree(String filename)
	{
            try
                {   
                    FileOutputStream file = new FileOutputStream(filename);
                    ObjectOutputStream out = new ObjectOutputStream(file);
                    out.writeObject(this.getAllMembers());
                    out.close();
                    file.close();
                }
         
            catch(IOException ex)
            {
                ex.printStackTrace();
            }
	}
	
	
        /**
         * Function to load the tree from file
         * @param filename
         * @return 
         */
	public TreeImpl loadTreeFromFile(String filename)
	{
	
            TreeImpl newTree = null ;
		try
                {   
                    FileInputStream file = new FileInputStream(filename);
                    ObjectInputStream in = new ObjectInputStream(file);
                    this.setAllMembers((List<FamilyMember>) in.readObject());

                    in.close();
                    file.close();
             
                    this.setRoot(this.getAllMembers().get(0));
            
                    newTree = new TreeImpl(this.getAllMembers().get(0));
                    newTree.setAllMembers(getAllMembers());
                }
         
                 catch(Exception ex) {}
		
		return newTree;
	}
	
       
        /**
         * Update the family member values
         * @param member
         * @param updatedMember 
         */
	public void updateMember(String member, FamilyMember updatedMember) 
	{
		
            for(int i=0;i<this.getAllMembers().size();i++)
            {
                if(this.getAllMembers().get(i).getFirstName().equals(member))
                {
                    this.getAllMembers().remove(i);
                    this.getAllMembers().add(i, updatedMember);
                    return;
                }
	
            }
		
	}

        /**
         * Function to remove member
         * @param member 
         */
	public void removeMember(String member) 
        {
            for(int i=0;i<this.getAllMembers().size();i++)
            {
                if(this.getAllMembers().get(i).getFirstName().equals(member))
                {
                    this.getAllMembers().remove(i);
                    continue;
                }
			
	
                if(this.getAllMembers().get(i).getMother()!=null && this.getAllMembers().get(i).getMother().getFirstName().equals(member))
                {
                    this.getAllMembers().get(i).setMother(null);
                    continue;
                }

                if(this.getAllMembers().get(i).getFather()!=null && this.getAllMembers().get(i).getFather().getFirstName().equals(member))
                {
                    this.getAllMembers().get(i).setFather(null);
                    continue;
                }
		
                if(this.getAllMembers().get(i).getSpouse()!=null && this.getAllMembers().get(i).getSpouse().getFirstName().equals(member))
                {
                    this.getAllMembers().get(i).setSpouse(null);
                    continue;
                }
			
                int childCount = this.getAllMembers().get(i).getChildren().size();
			
			
                for(int j=0;j<childCount;j++)
                {
                    if(this.getAllMembers().get(i).getChildren().get(j).getFirstName().equals(member))
                    {
                        this.getAllMembers().get(i).getChildren().remove(j);
                        break;
                    }

                }
	
            }
	}
}
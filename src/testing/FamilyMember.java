/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testing;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/**
 * Function: Family Member
 * Purpose of this function is to set the values/details of the family members 
 * Serializable has been used to store a copy of the objects and send it to another process (main) on this program. 
 * @author Ananth
 */
public class FamilyMember implements Serializable{
    
        // Variables
	private static int id=1;
	private String firstName, surName, surNameAfterMarriage, life;
	private String gender;
	private Address address;
	private FamilyMember mother, father, spouse;
	private int memberID;
        private List<FamilyMember> children, grandChildren;
	
        /**
         * Constructor function to set the default values
         * @param firstName
         * @param surName
         * @param surNameAfterMarriage
         * @param life
         * @param gender
         * @param address 
         */
        public FamilyMember(String firstName, String surName, String surNameAfterMarriage, String life, String gender,
                            Address address) 
        {
		super();
		this.firstName = firstName;
		this.surName = surName;
		this.surNameAfterMarriage = surNameAfterMarriage;
		this.life = life;
		this.gender = gender;
		this.address = address;
		this.memberID = this.getId();
		this.children = new ArrayList<>();
		this.incrementId();
	}

        /**
         * Function to ensure that the values of father, mother and children are null
         * @return 
         */
	public boolean hasSub()
	{
		return (this.getFather()!=null || this.getMother()!=null || this.getChildren().size()>0);
	}
	
        /**
         * Function to increment member ID 
         */
        public static void incrementId()
	{
		id++;
	}
	
        /**
         * Function to add child to family member node
         * @param child 
         */
	public void addChild(FamilyMember child)
        {
		this.children.add(child);
	}

        
        /**
         * Function to get a string object representatives of the values 
         */
	public String toString() 
        {
		String text =  "FM [firstName=" + firstName + ", surName=" + surName + ", surNameAfterMarriage=" + surNameAfterMarriage
				+ ", life=" + life + ", gender=" + gender + ", address=" + address + ", mother=" + mother.getFirstName() + ", father="
				+ father.getFirstName() + ", spouse=" + spouse.getFirstName() + ", memberID=" + memberID + ", children=";
		
		for(FamilyMember child : this.getChildren())
		{
			text = text + " " + child.firstName;
		}
		
		return text;
	}

        ///////////////////////////////////////////////////////
        /////Set Section
        //////////////////////////////////////////////////////
         
        /**
         * Function to set the member ID
         * @param memberID 
         */
	public void setMemberID(int memberID) 
        {
		this.memberID = memberID;
	}

        /**
         * Function to set the first name of family member
         * @param firstName 
         */
        public void setFirstName(String firstName) 
        {
		this.firstName = firstName;
	}

        /**
         * Function to set the surname
         * @param surName 
         */
        public void setSurName(String surName) 
        {
		this.surName = surName;
	}

        /**
         * Function to set surname after marriage
         * @param surNameAfterMarriage 
         */
        public void setSurNameAfterMarriage(String surNameAfterMarriage) 
        {
		this.surNameAfterMarriage = surNameAfterMarriage;
	}

        /**
         * Function to set the life value
         * @param life 
         */
        public void setLife(String life) 
        {
		this.life = life;
	}
        
        /**
         * Function to set gender
         * @param gender 
         */
        public void setGender(String gender) 
        {
		this.gender = gender;
	}
        
        /**
         * Function to set address 
         * @param address 
         */
        public void setAddress(Address address) 
        {
		this.address = address;
	}

        /**
         * Function to set mother 
         * @param mother 
         */
        public void setMother(FamilyMember mother) 
        {
		this.mother = mother;
	}

        /**
         * Function to set father
         * @param father 
         */
        public void setFather(FamilyMember father) 
        {
		this.father = father;
	}

        /**
         * Function to set spouse
         * @param spouse 
         */
        public void setSpouse(FamilyMember spouse) 
        {
		this.spouse = spouse;
	}

        /**
         * Function to set children
         * @param children 
         */
        public void setChildren(List<FamilyMember> children) 
        {
		this.children = children;
	}
        
        /**
         * Function to set grandChildren
         * @param grandChildren 
         */
        public void setGrandChildren(List<FamilyMember> grandChildren) 
        {
		this.grandChildren = grandChildren;
	}
        
        /////////////////////////////////////////////////////////
        //// Get Section
        ////////////////////////////////////////////////////////
        
        /**
         * Function to get the memberID
         * @return 
         */
	public int getMemberID() 
        {
		return memberID;
	}

	/**
         * Function to get the ID
         * @return 
         */
	public static int getId() 
        {
		return id;
	}

	/**
         * Function to get the first name
         * @return 
         */
	public String getFirstName() 
        {
		return firstName;
	}

	/**
         * Function to get surname
         * @return 
         */
	public String getSurName() {
		return surName;
	}

	/**
         * Function to get surname after marriage
         * @return 
         */
	public String getSurNameAfterMarriage() 
        {
		return surNameAfterMarriage;
	}

	/**
         * Function to get life
         * @return 
         */
	public String getLife() {
		return life;
	}

	/**
         * Function to get gender
         * @return 
         */
	public String getGender() {
		return gender;
	}

	/**
         * Function to get address
         * @return 
         */
	public Address getAddress() {
		return address;
	}

	/**
         * Function to get mother 
         * @return 
         */
	public FamilyMember getMother() {
		return mother;
	}

	/**
         * Function to get father
         * @return 
         */
	public FamilyMember getFather() {
		return father;
	}

	/**
         * Function to get spouse
         * @return 
         */
	public FamilyMember getSpouse() {
		return spouse;
	}

	/**
         * Function to get children
         * @return 
         */
	public List<FamilyMember> getChildren() {
		return children;
	}

	/**
         * Function to get grandchildren
         * @return 
         */
	public List<FamilyMember> getGrandChildren() {
		return grandChildren;
	}

	
	
}
// Set gender to either MALE or FEMALE

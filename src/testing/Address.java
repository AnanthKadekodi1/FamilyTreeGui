/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testing;



import java.io.Serializable;

/**
 * Function to set the address values of the family members
 * Serializable has been used to store a copy of the objects and send it to another process (main) on this program
 * @author Ananth
 */
public class Address implements Serializable{

    //Variables
    private String streetNumber, streetName, suburb, postalCode;
    
    /**
     * Constructor to set the default values of streetNumber, streerName, suburb and postalCode
     * @param streetNumber
     * @param streetName
     * @param suburb
     * @param postalCode 
     */
    public Address(String streetNumber, String streetName, String suburb, String postalCode) 
    {
        super();
        this.streetNumber = streetNumber;
        this.streetName = streetName;
        this.suburb = suburb;
        this.postalCode = postalCode;
    }

    ///////////////////////////////////////////////
    //// Set section
    //////////////////////////////////////////////
	

    /**
     * Function to set the value of street number
     * @param streetNumber 
     */
    public void setStreetNumber(String streetNumber) 
    {
        this.streetNumber = streetNumber;
    }
    
    /**
     * Function to set the value of street name
     * @param streetName 
     */
    public void setStreetName(String streetName) 
    {
        this.streetName = streetName;
    }
    
    /**
     * Function to set the value of suburb
     * @param suburb 
     */
    public void setSuburb(String suburb) 
    {
        this.suburb = suburb;
    }
    

    /**
     * Function to set the value of post code
     * @param postalCode 
     */
    public void setPostalCode(String postalCode) 
    {
        this.postalCode = postalCode;
    }

    
    ///////////////////////////////////////////////
    ////////// Get Section
    ///////////////////////////////////////////////
    
    /**
     * Function to get the street name
     * @return 
     */
    public String getStreetName() 
    {
        return streetName;
    }


    /**
     * Function to get the value of suburb
     * @return 
     */
    public String getSuburb() 
    {
        return suburb;
    }

   /**
    * Function to get postal code
    * @return 
    */
    public String getPostalCode() 
    {
        return postalCode;
    }
    
    /**
     * Function to get street number
     * @return 
     */
    public String getStreetNumber() 
    {
        return streetNumber;
    }

   
    /**
     * Function to convert all the variables as string 
     * @return 
     */
    public String toString() 
    {
		return "Address [streetNumber=" + streetNumber + "]";
    }
	
	
	
}
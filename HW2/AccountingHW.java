/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Chelsea Gallimore
 */
import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
public class AccountingHW {
    
public static void main(String[] args) throws IOException {

    Scanner keyboard = new Scanner(System.in);
    
    //Declare Array to store input files
    ArrayList<String> inFiles = new ArrayList<>();
    ArrayList<String> outFiles = new ArrayList<>();
    
    //Declare boolean to control loop
    boolean exit = false;
    while(exit == false) {
        //prompt the user for the input files
        System.out.print("Enter a input file name or \"E\" to continue: ");
        String inFile = keyboard.next();
        if(inFile.equals("E")) {
            exit = true;
        } else {
            inFiles.add(inFile);
            System.out.print("Enter the output file name: ");
            String outFile = keyboard.next();
            outFiles.add(outFile);
            
        }
    
    }

    try {
        for (String file : inFiles) {
            //Store variables per file
            double highPrice = 0.00, lowPrice = 0.00, total = 0.00, totalTax = 0.00;
            final double TAX = 0.008875;
            String startDate = "", endDate = "", highSKU = "", lowSKU = "";
             //open file
            File newFile = new File(file);
            Scanner inputFile = new Scanner(newFile);
            //match file name in inFile array with outFile array
            int index = inFiles.indexOf(file);
             //create PrintWriter object
            PrintWriter pw = new PrintWriter(outFiles.get(index));
          
            //read data from file
            while (inputFile.hasNext()) {
                //get the next line
                String line = inputFile.nextLine();
                //tokenize the line
                String[] tokens = line.split(",");
                if(startDate.equals("")) {
                    startDate = tokens[0];
                   
                } else {
                    endDate = tokens[0];
                }
           
                //get the price and convert it to a double
                double price = Double.parseDouble(tokens[2]);
                if(lowPrice == 0.00) {
                    lowPrice = price;
                }

                //get the discount and convert it to a double
                double discount = Double.parseDouble(tokens[3]);

                //compute total sum of items' price in file
                double itemCost = price - (price * (discount/100));

              
                total += itemCost;
                if(itemCost > highPrice) {
                    highPrice = itemCost;
                    highSKU = tokens[1];
                } else if(itemCost < lowPrice){
                   
                    lowSKU = tokens[1];
                    lowPrice = itemCost;
                }
            }
                  //compute tax
               double tax = total * TAX; 
               pw.println("Report from " + startDate + " to " + endDate);
               pw.printf("\nThe total is $%5.2f%n\n", total);
               pw.printf("The tax is $%5.2f%n\n", tax);
               pw.printf("The highest price item is #" + highSKU + " at $%5.2f%n\n", highPrice);
               pw.printf("The lowest price item is #" + lowSKU + " at $%5.2f%n", lowPrice);
           
            //close files
            inputFile.close();
            pw.close();
        }
        
    } catch (Exception e) { 
        System.out.println("An exception " + e + "occured.");
    }


}

    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.poepart2;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

  import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/**
 *
 * @author mphah
 */
public class POEPART2 {

    
 

    
 
 
 
public class POEPARTtwo {

  public static void main(String[] args) throws JSONException {
      
      System.out.println("Welcome to the Register Page");
        Scanner sc = new Scanner(System.in);
      
        System.out.println("Please Enter Your Username : ");
        String username = sc.nextLine();
        System.out.println("Please Enter Your Password");
        String Password = sc.nextLine();
        System.out.println("Enter a valid SA Number(+27)");
        String Phone = sc.nextLine();
        
        if(username.length() <= 5 && 
                username.contains("_")){
            System.out.println("True Message");
        }else{
            System.out.println("False Message");
        }
        
        
        
        int numMessagesSend = 0;
        Scanner input = new Scanner(System.in);
        Random random = new Random();
        JSONArray messagesArray = new JSONArray();

        System.out.println("Welcome to Quick Chat");
        System.out.println("How many messages do you want to enter");
        int NumMessages = input.nextInt();
        input.nextLine(); // clear the leftover newline

        for (int x = 0; x < NumMessages; x++) {

            System.out.println("=====Menu=====");
            System.out.println("Option 1) Send Message");
            System.out.println("Option 2) Disregard Message");
            System.out.println("Option 3) Store Message To Send Later");
            int Choice = input.nextInt();
            input.nextLine();

            if (Choice != 1 && Choice != 2 && Choice != 3) {
                System.out.println("Invalid input, please enter 1, 2, or 3");
                x--; // repeat this message
                continue;
            }

            if (Choice == 2) {
                System.out.println("Message disregarded");
                continue;
            }

           
            long maximum = 9999999999L;
            long minimum = 1000000000L;
            long MessageID = minimum + (long) (random.nextDouble() * (maximum - minimum));

            System.out.println("Enter The Recipient Number");
            String RNumber = input.nextLine();

            System.out.println("Enter your message");
            String MessageText = input.nextLine();

            if (MessageText.length() > 250) {
                System.out.println("Please enter a message of less than 250 characters");
                x--; 
                continue;
            }

            if (Choice == 1) {
                numMessagesSend++;
            }

            
            String firstTwoDigits = String.valueOf(MessageID).substring(0, 2);
            String[] Words = MessageText.split(" ");
            String firstword = Words[0];
            String lastword = Words[Words.length - 1];
            String Hash = (firstTwoDigits + ":" + numMessagesSend + ":" + firstword + lastword).toUpperCase();

            if (Choice == 1) {
                System.out.println("Message Sent");
                System.out.println("Message ID: " + MessageID);
                System.out.println("Message Hash: " + Hash);
                System.out.println("Recipient: " + RNumber);
                System.out.println("Message: " + MessageText);
            } else { 
                JSONObject messageObj = new JSONObject();
                messageObj.put("recipient", RNumber);
                messageObj.put("message", MessageText);
                messageObj.put("messageID", MessageID);
                messagesArray.put(messageObj);

                storeMessages("storedMessages.json", messagesArray);
                System.out.println("Message successfully stored");
            }
        }

        System.out.println("Total messages sent: " + numMessagesSend);
    }

    public static void storeMessages(String fileName, JSONArray array) throws JSONException {
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(array.toString(4));
        } catch (IOException e) {
            System.out.println("Could not store messages: " + e.getMessage());
        }
    }
}
    


    }


    


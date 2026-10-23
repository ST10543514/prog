/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.poepart2;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
/**
 *
 * @author mphah
 */
public class Message {
  
/**
 *
 * @author mphah
 */

  

  


   



    private String messageID;
    private int messageNumber;
    private String recipient;
    private String message;
    private String messageHash;
    private String status;

    private static int totalMessagesSent = 0;
    private static ArrayList<Message> sentMessages = new ArrayList<>();
    private static ArrayList<Message> storedMessages = new ArrayList<>();

    public Message(String messageID, int messageNumber, String recipient, String message) {
        this.messageID = messageID;
        this.messageNumber = messageNumber;
        this.recipient = recipient;
        this.message = message;
        this.messageHash = createMessageHash();
        this.status = "Created";
    }

    public boolean checkMessageID() {
        if (messageID != null && messageID.length() <= 10) {
            return true;
        } else {
            return false;
        }
    }

    public String checkRecipientCell() {
        if (recipient != null && recipient.startsWith("+") && recipient.length() <= 11) {
            return "Cell phone number successfully captured.";
        } else {
            return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        }
    }

    public String checkMessageLength() {
        if (message.length() <= 250) {
            return "Message ready to send.";
        } else {
            int excess = message.length() - 250;
            return "Message exceeds 250 characters by " + excess + "; please reduce the size.";
        }
    }

    public String createMessageHash() {
        String frstTwoDigits = messageID.substring(0, 2);
        int NumberOfMessage = messageNumber;
        String[] Words = message.trim().split(" ");
        String firstword = Words[0];
        String lastWord = Words[Words.length - 1];
        String Hash = frstTwoDigits + ":" + NumberOfMessage + ":" + firstword + lastWord;
        return Hash;
    }

    public String SentMessage(int Choice) {
        if (Choice == 1) {
            status = "Sent";
            sentMessages.add(this);
            totalMessagesSent++;
            return "Message successfully sent.";
        } else if (Choice == 2) {
            status = "Disregarded";
            return "Press 0 to delete the message.";
        } else if (Choice == 3) {
            status = "Stored";
            storedMessages.add(this);
            storeMessage();
            return "Message successfully stored.";
        } else {
            return "Invalid option.";
        }
    }

    public static String printMessages() {
        if (sentMessages.isEmpty()) {
            return "No messages have been sent.";
        }
        String output = "";
        for (int x = 0; x < sentMessages.size(); x++) {
            Message msg = sentMessages.get(x);
            output += "\nMessage ID: " + msg.messageID;
            output += "\nMessage Hash: " + msg.messageHash;
            output += "\nRecipient: " + msg.recipient;
            output += "\nMessage: " + msg.message;
            output += "\n----------------------------";
        }
        return output;
    }

    public static int returnTotalMessagess() {
        return totalMessagesSent;
    }

    public void storeMessage() {
        try {
            FileWriter writer = new FileWriter("messages.json", true);
            String RNumber = recipient;
            String Message = message;
            writer.write("{\"messageID\":\"" + messageID + "\",\"recipient\":\"" + RNumber + "\",\"message\":\"" + Message + "\",\"messageHash\":\"" + messageHash + "\"},\n");
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred while storing the message.");
        }
    }

    public static String generateMessageID() {
        Random random = new Random();
        long maximum = 9999999999L;
        long minimum = 1000000000L;
        long MessageID = random.nextLong(maximum - minimum) + minimum;
        String frstTwoDigits = String.valueOf(MessageID);
        return frstTwoDigits;
    }

    public String getMessageID() { return messageID; }
    public String getMessageHash() { return messageHash; }
    public String getRecipient() { return recipient; }
    public String getMessage() { return message; }
    public String getStatus() { return status; }
    public int getMessageNumber() { return messageNumber; }
}


 


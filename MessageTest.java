/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author mphah
 */
public class MessageTest {
    private Message msg;

    @BeforeEach
    public void setUp() {
      msg = Message;
    }

    
    @Test
    public void testMessageLengthSuccess() {
        String Message = "Hi Mike, can you join us for dinner tonight?";
        assertEquals("Message ready to send.", msg.checkMessageLength(Message));
    }

    @Test
    public void testMessageLengthExactly250() {
        String Message = "a".repeat(250);
        assertEquals("Message ready to send.", msg.checkMessageLength(Message));
    }

    @Test
    public void testMessageLengthFailure() {
        String Message = "a".repeat(260);
        assertEquals("Message exceeds 250 characters by 10, please reduce the size.",
                msg.checkMessageLength(Message));
    }

    
    @Test
    public void testRecipientCellSuccess() {
        String RNumber = "+27718693002";
        assertEquals("Cell phone number successfully captured.", msg.checkRecipientCell(RNumber));
    }

    @Test
    public void testRecipientCellFailure() {
        String RNumber = "08575975889";
        assertEquals("Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.",
                msg.checkRecipientCell(RNumber));
    }

    // ---------- Message hash ----------
    @Test
    public void testCreateMessageHash() {
        String MessageID = "0012345678";
        int NumberOfMessage = 0;
        String Message = "HI THANKS";

        String Hash = msg.createMessageHash(MessageID, NumberOfMessage, Message);
        assertEquals("00:0:HITHANKS", Hash);
    }

    @Test
    public void testMessageHashTestCase1() {
        String MessageID = "0012345678";
        int NumberOfMessage = 0;
        String Message = "Hi Mike, can you join us for dinner tonight?";

        String Hash = msg.createMessageHash(MessageID, NumberOfMessage, Message);
        assertEquals("00:0:HITONIGHT", Hash);
    }

    @Test
    public void testMessageHashesInLoop() {
        String[] ids = {"0012345678", "0112345678"};
        String[] texts = {"Hi Mike, can you join us for dinner tonight?",
                          "Hi Keegan, did you receive the payment?"};
        String[] expected = {"00:0:HITONIGHT", "01:1:HIPAYMENT"};

        for (int i = 0; i < texts.length; i++) {
            assertEquals(expected[i], msg.createMessageHash(ids[i], i, texts[i]));
        }
    }

   
    @Test
    public void testCheckMessageID() {
        String MessageID = msg.generateMessageID();
        assertTrue(msg.checkMessageID(MessageID));
        assertEquals(10, MessageID.length());
        assertEquals("Message ID generated: " + MessageID, msg.messageIDGenerated(MessageID));
    }

    @Test
    public void testMessageIDTooLong() {
        assertFalse(msg.checkMessageID("12345678901"));
    }

    
    @Test
    public void testSendMessage() {
        int Choice = 1;
        String result = msg.SentMessage(Choice);
        assertEquals("Message successfully sent.", result);
    }

    @Test
    public void testDisregardMessage() {
        int Choice = 2;
        String result = msg.SentMessage(Choice);
        assertEquals("Press 0 to delete the message.", result);
    }

    @Test
    public void testStoreMessage() {
        int Choice = 3;
        String result = msg.SentMessage(Choice);
        assertEquals("Message successfully stored.", result);
    }

    @Test
    public void testInvalidChoice() {
        String result = msg.SentMessage(4);
        assertEquals("Invalid input, please enter 1, 2, or 3", result);
    }

    
    @Test
    public void testReturnTotalMessages() {
        msg.SentMessage(1); 
        msg.SentMessage(2); 
        assertEquals(1, msg.returnTotalMessages());
        assertEquals("Number of messages sent: 1", msg.printMessage(msg.returnTotalMessages()));
    }
} 

    
     
   
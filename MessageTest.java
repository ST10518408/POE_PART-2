/* 

* Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license 

* Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template 

*/ 

package com.mycompany.message;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MessageTest {

    
    // PART 1 - LOGIN TESTS//
    

    @Test
    public void testUsernameCorrectlyFormatted() {
        Message.Login user = new Message.Login("Kyle", "Smith", "kyl_1", "Ch&&sec@ke99!", "+27838968976");
        assertTrue(user.checkUserName());
    }

    @Test
    public void testUsernameIncorrectlyFormatted() {
        Message.Login user = new Message.Login("Kyle", "Smith", "kyle!!!!!!!", "Ch&&sec@ke99!", "+27838968976");
        assertFalse(user.checkUserName());
    }

    @Test
    public void testPasswordMeetsComplexity() {
        Message.Login user = new Message.Login("Kyle", "Smith", "kyl_1", "Ch&&sec@ke99!", "+27838968976");
        assertTrue(user.checkPasswordComplexity());
    }

    @Test
    public void testPasswordDoesNotMeetComplexity() {
        Message.Login user = new Message.Login("Kyle", "Smith", "kyl_1", "password", "+27838968976");
        assertFalse(user.checkPasswordComplexity());
    }

    @Test
    public void testCellPhoneCorrectlyFormatted() {
        Message.Login user = new Message.Login("Kyle", "Smith", "kyl_1", "Ch&&sec@ke99!", "+27838968976");
        assertTrue(user.checkCellPhoneNumber());
    }

    @Test
    public void testCellPhoneIncorrectlyFormatted() {
        Message.Login user = new Message.Login("Kyle", "Smith", "kyl_1", "Ch&&sec@ke99!", "08966553");
        assertFalse(user.checkCellPhoneNumber());
    }

    @Test
    public void testLoginSuccessful() {
        Message.Login user = new Message.Login("Kyle", "Smith", "kyl_1", "Ch&&sec@ke99!", "+27838968976");
        assertTrue(user.loginUser("kyl_1", "Ch&&sec@ke99!"));
    }

    @Test
    public void testLoginFailed() {
        Message.Login user = new Message.Login("Kyle", "Smith", "kyl_1", "Ch&&sec@ke99!", "+27838968976");
        assertFalse(user.loginUser("kyl_1", "wrongpassword"));
    }

    @Test
    public void testReturnLoginStatusSuccess() {
        Message.Login user = new Message.Login("Kyle", "Smith", "kyl_1", "Ch&&sec@ke99!", "+27838968976");
        String status = user.returnLoginStatus("kyl_1", "Ch&&sec@ke99!");
        assertTrue(status.contains("it is great to see you again"));
    }

    @Test
    public void testReturnLoginStatusFail() {
        Message.Login user = new Message.Login("Kyle", "Smith", "kyl_1", "Ch&&sec@ke99!", "+27838968976");
        String status = user.returnLoginStatus("kyl_1", "wrongpassword");
        assertEquals("Username or password incorrect, please try again.", status);
    }

    
    // PART 2 - MESSAGE TESTS//
   

    @Test
    public void testMessageIDCreated() {
        Message.MessageData msg = new Message.MessageData(1, "+27718693002", "Hi Mike, can you join us for dinner tonight?");
        assertTrue(msg.checkMessageID());
    }

    @Test
    public void testRecipientCorrectlyFormatted() {
        Message.MessageData msg = new Message.MessageData(1, "+27718693002", "Hi Mike, can you join us for dinner tonight?");
        assertEquals("Cell phone number successfully captured.", msg.checkRecipientCell());
    }

    @Test
    public void testRecipientIncorrectlyFormatted() {
        Message.MessageData msg = new Message.MessageData(1, "08575975889", "Hi Keegan, did you receive the payment?");
        assertEquals(
            "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.",
            msg.checkRecipientCell()
        );
    }

    @Test
    public void testMessageWithinLimit() {
        Message.MessageData msg = new Message.MessageData(1, "+27718693002", "Hi Mike, can you join us for dinner tonight?");
        assertEquals("Message ready to send.", msg.checkMessageLength());
    }

    @Test
    public void testMessageExceedsLimit() {
        String longMessage = "A".repeat(260);
        Message.MessageData msg = new Message.MessageData(1, "+27718693002", longMessage);
        assertTrue(msg.checkMessageLength().contains("Message exceeds 250 characters by"));
    }

    @Test
    public void testMessageHashCorrect() {
        Message.MessageData msg = new Message.MessageData(1, "+27718693002", "Hi Mike, can you join us for dinner tonight?");
        String hash = msg.getMessageHash();
        assertTrue(hash.endsWith(":HITONIGHT"));
    }

    @Test
    public void testMessageSentSuccessfully() {
        Message.MessageData msg = new Message.MessageData(1, "+27718693002", "Hi Mike, can you join us for dinner tonight?");
        assertEquals("Message successfully sent.", msg.sentMessage(1));
    }

    @Test
    public void testMessageDisregarded() {
        Message.MessageData msg = new Message.MessageData(1, "+27718693002", "Hi Mike, can you join us for dinner tonight?");
        assertEquals("Press 0 to delete the message.", msg.sentMessage(2));
    }

    @Test
    public void testMessageStored() {
        Message.MessageData msg = new Message.MessageData(1, "+27718693002", "Hi Mike, can you join us for dinner tonight?");
        assertEquals("Message successfully stored.", msg.sentMessage(3));
    }

    @Test
    public void testReturnTotalMessages() {
        int before = Message.MessageData.returnTotalMessages();
        Message.MessageData msg1 = new Message.MessageData(1, "+27718693002", "Hi Mike, can you join us for dinner tonight?");
        msg1.sentMessage(1);
        Message.MessageData msg2 = new Message.MessageData(2, "+27838884567", "Hi Keegan, did you receive the payment?");
        msg2.sentMessage(1);
        int after = Message.MessageData.returnTotalMessages();
        assertTrue(after >= before + 2);
    }
}

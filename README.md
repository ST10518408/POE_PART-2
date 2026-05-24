# POE-PART-2 — QuickChat Sending Messages

## What is this?
This is Part 2 of the QuickChat app built in Java.
It lets a registered user log in and send messages to other people using their cell phone numbers.

## What the app does
- The user registers with a username, password, and cell number
- The user logs in with their username and password
- The user chooses how many messages to send
- For each message the user enters a recipient number and a message
- The user can then choose to **send**, **store**, or **disregard** each message
- Stored messages are saved to a JSON file
- The app keeps track of how many messages were sent

## Classes
**Login** — handles registration and login
- Checks that the username has an underscore and is 5 characters or less
- Checks that the password has 8+ characters, a capital, a number, and a special character
- Checks that the cell number has an international code (+27)

**MessageData** — handles each message
- Creates a unique 10-digit message ID
- Creates a message hash in the format: `XX:N:FIRSTWORDLASTWORD`
- Checks the recipient number is correctly formatted
- Checks the message is not longer than 250 characters
- Allows the user to send, store, or disregard the message

## How to run
1. Open the project in NetBeans or any Java IDE
2. Run `Message.java`
3. Follow the prompts in the console

## How to run the tests
1. Open `MessageTest.java`
2. Right-click and select **Test File**
3. All 12 tests should pass

## Files
| File | Description |
|---|---|
| `Message.java` | Main application with Login and MessageData classes |
| `MessageTest.java` | JUnit 5 unit tests for the MessageData class |

## Author
Student Number: ST10518407

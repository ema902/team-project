# Team Project

Please keep this up-to-date with information about your project throughout the term.

The readme should include information such as:
- a summary of what your application is all about
    - A study app that tracks assignments todo tasks. Our app provides a calming relaxing environment for studying by having study sessions and breaks. 
- a list of the user stories, along with who is responsible for each one
- information about the API(s) that your project uses
    - Inspirational Quotes: https://api-ninjas.com/api/quotes#v2-quotes 
  - RANDOM.ORG: random number generation https://api.random.org/dashboard
  - CountdownShare: countdown timer https://countdownshare.com/api/v1
  - Figma: Images https://developers.figma.com/docs/rest-api/
- screenshots or animations demonstrating current functionality

By keeping this README up-to-date,
your team will find it easier to prepare for the final presentation
at the end of the term.

# Project Name
## Team Information
### Team Members
- Ava
- Ema
- Grace
- Miray
- Sophia
---
## Project Description
### Domain
[Project description]
-

## User Stories (For each group member):

### User Story 1 - Ema:
As a student, I want to generate flashcards in a timely manner so that I could study for my classes 

### User Story 2 - Grace:
As a student, I want to start a pomodoro timer so that I can study without distractions and take a break in between.
As a student, I want to adjust my focus and break timer durations so that my study sessions match my preferred pace.
As a student, I want to cancel an active focus session or end a break early so that I can reset the timer if my plans change.
As a student, I want to switch between different screens in the app so that I can study during my focus session, and play a game during my break.

### User Story 3 - Ava:
As a student, I want to start a break timer so that I can take periodic breaks after studying. 
As a student playing the break game, I want to receive feedback on my submitted code so that I figure out the secret code. 

### User Story 4 - Miray:
As a student, I want to play a game during my break so that I can wind down and relax after my study session. 

### User Story 5 - Sophia:
As a student, I want to review my flashcards so I can remember the material. 

## Use Cases
### Use Case 1
Name: Start Timer

Primary Actor: Student 

Preconditions: 
- There is no focus or break timer currently running 
- The user is on the home screen 

Trigger: Clicking the “Start” button on the home screen

Success Outcome: The focus timer counts down to 0 and transitions to the break screen. The student can then start the break timer, which switches to the home screen when the timer finishes. 

Main Success Scenario:
1. The student clicks the “Start” button on the focus screen.
2. The system gets the student’s preferred focus and break duration from their settings. 
3. The system hides the start and adjust options, shows the cancel button, and begins counting down the focus time.
4. The system continuously updates the timer display with the remaining time.
5. When the focus timer finishes, the system switches to the break screen and displays the “Start” button.
6. The student clicks the “Start” button to begin the break.
7. The system hides the start button, shows the “End Break” button, and counts down the break time.
8. When the break timer finishes, the system returns to the focus home screen and makes the start and adjust options available again.

Extensions:
3a / 7a. The student decides to stop the session early.
- The Cancel Timer use case is triggered.

### Use Case 2 
Name: Generating flashcards

Primary Actor: Student

Preconditions: Student has text formatted correctly (with titled bolded/underlined and body for flashcard DIRECTLY underneath)

Trigger: Clicking the generate button

Main Success Scenario:
1. Student goes to flashcard page
2. Student copies and pastes text into textbox meant for where you insert flashcards
3. Student formats accordingly 
4. Student hits generate flashcards and program generates and shows flashcards to students 
5. Student now has access to flashcards as needed

    
Extensions:
If user enters no text, message will prompt them to enter text first 
Success outcome: Student can generate flashcards successfully

### Use Case 3 
Name: Evaluate Guess and Generate Feedback 

Primary Actor: Student

Preconditions: Student is playing CandleGame, Break Timer is still ongoing

Trigger: The student enters a guess and clicks the submit button.

Success Outcome: The student's guess is checked with secret code, candle feedback is generated for each guessed digit, game condition and guess are updated, and the candle feedback is displayed to student.

Main Success Scenario:
1. Student enters 5 digit guessed code in game and displayed in real time
2. Student submits the code via Submit button. (Invalid inputs are ignored and not written)
3. The system receives the guessed code.
4. The system compares the guessed code to secret code.
5. The system then generates candle colour feedback based on the placement and correctness of each digit (Unlit: Digit not in secret code, Yellow: Digit is in secret code but in wrong place, Orange: Digit is in secret code and in the right place)
6. The system records the guess and feedback.
7. The system checks game conditions (Win, Loss, Ongoing) and updates it.
8. The system uses feedback to display the candle colours, remaining attempts, and status to the student.

Extensions: Student guesses the secret code correctly
1. The system updates the game condition to WON 
2. The system displays a winning message and displays candle colours to the student.
3. The student can quit the game and return to break screen.

Student's guess is invalid 
1. The system detects that the guess has invalid characters.
2. The submit button is disabled until a valid guess is detected. 
### Use Case 4 
Name: Start the Candle Game 

Primary Actor: Student

Preconditions: Break Timer is on, student is in the right page

Trigger: Student clicks the button linked to the break page

Success Outcome: Game page is open, ready to work and take students input

Main Success Scenario:
1. Student clicks the button linked to the break page 
2. Program checks if Break Timer is on, if it is;
3. Game creates a secret code and a new game
4. The system opens the game window showing the candles, Break Timer, and the tries left.

Extensions:

- If Break Timer is not active then the program does not let users access the game.
- If Student clicks the quit button, program marks Game Condition as “QUIT” and student returns to the focus page
- If Student clicks the instructions button, the program shows instructions in a pop up screen.
- If Break Timer runs out before the end of the game, student gets back to the focus page 

### Use Case 5  Studying flashcards
Primary Actor: Student 
Preconditions: Flashcards are generated properly 
Trigger: Clicking the “Knew it” or “Still Learning” button 
Main Success Scenario:

1. Student is on the flashcard page after flashcards are generated
2. Student clicks “Knew It” or “Still Learning” button
3. If “Knew It” pressed, removes flashcard from deck and if “Still Learning” is pressed, then moves flashcard to the back of the deck
4. System displays a new flashcard for the user
5. Extensions: If all flashcards are done on Step 3, then prompts student to restart/exit

---
## Minimal Viable Product (MVP)
| Team Member | MVP Use Case |
|-------------|--------------|
| Grace | Use Case 1 |
| Ema | Use Case 2 |
| Ava | Use Case 3 |
| Miray | Use Case 4|
| Sophia | Use Case 5|
--

## Entities
### Entity 1
### Entity 2
(Add additional entities as needed.)
---
## API Usage
**API Name:**
**Documentation:** [Link]
**How it is used in the program:**
---
## Design Diagrams
### UML Class Diagram
### Sequence Diagrams
---
## How to Run
[Instructions for running the program.]
---
## Notes
[Any additional project information.]

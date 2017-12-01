////////////////////////////////////////////////////////////////////////////////
//
// Title:			Review Quiz
// Files:			ReviewQuiz.java
//					P1.jar
// Semester:		CS 302 Fall 2016
//
// Author:			Kendra Raczek
// Email:			raczek@wisc.edu
// CS Login:		raczek
// Lecturer's Name:	Gary Dahl
// Lab Section:		335
//
////////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Online Sources: https://www.tutorialspoint.com/index.htm 
// 				   used for various clarifications to explain the syntax of
//				   multiple methods
//
////////////////////////////////////////////////////////////////////////////////
import java.util.Scanner;

/*
 * Application that that runs user through a CS 302 review quiz, with options to
 * answer a fixed number of problems or a fixed number of correct answers, and 
 * to submit a user-created review problem.
 */
public class ReviewQuiz
{
	/*
	 * Program execution begins
	 */
	public static void main(String[] args)
	{
		Scanner scnr = new Scanner(System.in);

		System.out.println("Welcome to the CS302 Review Quiz!");
		System.out.println("=================================");
		System.out.println();
		
		// user's email address
		System.out.print("Please enter your email address: ");
		String userEmail = scnr.next(); // user's email
		
		// initial menu choice
		String menuChoice = "";
		// Menu Loop repeats until user enters "Q" or "q" to quit
		while(!menuChoice.equalsIgnoreCase("q")) {
			System.out.println();
			System.out.println("MAIN MENU");
			System.out.println("    [R]andom Sample Quiz (fixed number of problems)");
			System.out.println("    [P]roficiency Quiz (fixed number of correct answers)");
			System.out.println("    [S]ubmit a Problem");
			System.out.println("    [Q]uit");
			System.out.print("Enter your choice: ");
			menuChoice = scnr.next();
			
			switch (menuChoice) {
				// [R]andom Sample Quiz (fixed number of problems)
				case "R":
				case "r":
					// sections chosen to answer questions from for random sample quiz
					int sectionChoiceR = 0;
					while(sectionChoiceR >= -1) {
						System.out.println();
						System.out.println(P1.getSections());
						System.out.print("Choose another section to answer problems from (or -1 to stop): ");
						sectionChoiceR = scnr.nextInt();
						if(sectionChoiceR != -1) {
							P1.toggleSection(sectionChoiceR);
						}
						// section choice -1 means to finish choosing sections
						else if(sectionChoiceR == -1) {
							if(P1.questionsAvailable()) {
								break;
							}
							else if(!P1.questionsAvailable()) {
								continue;
							}
						}	
					}
					System.out.println();
					System.out.print("Enter the number of problems to review: ");
					// fixed number of problems to review
					int numQuestions = scnr.nextInt();
					
					for(int i = 0; i <= numQuestions -1; ++i) { // number of questions presented
						System.out.println();
						// question that is presented to user
						String userQuestion = P1.getQuestion();
						System.out.println(( i + 1 ) + ". " + userQuestion); // prints keeping track of the current question's number
						
						System.out.print("Enter your answer: ");
						// user's answer input
						String userAnswer = scnr.next();
						System.out.println();
						
						// correct answer to question
						char correctAnswerChar = P1.getAnswer();
						String correctAnswer = Character.toString(correctAnswerChar); // Convert answer from char => string
						
						// Answer is Correct
						if (userAnswer.equalsIgnoreCase(correctAnswer)) {
							System.out.println("That's correct!!!");
						}
						// Answer is Incorrect
						else {
							System.out.println("The correct answer is: " + correctAnswer);
						}
						System.out.println();
						
						// Difficulty Rating
						System.out.print("Please rate the difficulty of this problem (1-easy to 5-hard, or 0-report): ");
						// user's input difficulty rating
						int userRating = scnr.nextInt();
						P1.submitRating(userRating);
						
						// current question's average rating
						double averageRating = P1.getRating();
						System.out.println("The average rating for this question has been: " + averageRating);
						
						P1.gotoNextProblem();
					}
					break;
			
				// [P]roficiency Quiz (fixed number of correct answers)
				case "P":
				case "p":
					// sections chosen to answer questions from for proficiency quiz
					int sectionChoiceP = 0;
					while(sectionChoiceP >= -1) {
						System.out.println();
						System.out.println(P1.getSections());
						System.out.print("Choose another section to answer problems from (or -1 to stop): ");
						sectionChoiceP = scnr.nextInt();
						if(sectionChoiceP != -1) {
							P1.toggleSection(sectionChoiceP);
						}
						// section choice -1 means to finish choosing sections
						else if(sectionChoiceP == -1) {
							if(P1.questionsAvailable()) {
								break;
							}
							else if(!P1.questionsAvailable()) {
								continue;
							}
						}	
					}
					System.out.println();
					System.out.print("Enter the number of correct answers needed to stop: ");
					// number of correct answers needed to finish quiz
					int numCorrectAnswersNeed = scnr.nextInt();
					// current number of user's correct answers
					int numCorrectAnswers = 0;
					// keeps track of the current question's number
					int i = 1;
					
					while(numCorrectAnswers < numCorrectAnswersNeed) {
						System.out.println();
						// question that is presented to user
						String userQuestion = P1.getQuestion();
						System.out.println(i + ". " + userQuestion);
						
						System.out.print("Enter your answer: ");
						// user's answer input
						String userAnswer = scnr.next();
						System.out.println();

						// correct answer to question
						char correctAnswerChar = P1.getAnswer();
						String correctAnswer = Character.toString(correctAnswerChar); // Convert answer from char => string
						
						// Answer is Correct
						if (userAnswer.equalsIgnoreCase(correctAnswer)) {
							System.out.println("That's correct!!!");
							numCorrectAnswers = numCorrectAnswers + 1;
						}
						// Answer is Incorrect
						else {
							System.out.println("The correct answer is: " + correctAnswer);
						}
						System.out.println();
						
						// Difficulty Rating
						System.out.print("Please rate the difficulty of this problem (1-easy to 5-hard, or 0-report): ");
						// user's input difficulty rating
						int userRating = scnr.nextInt();
						P1.submitRating(userRating);
						
						// current question's average rating
						double averageRating = P1.getRating(); // Average difficulty rating
						System.out.println("The average rating for this question has been: " + averageRating);
						
						i = i + 1;
						P1.gotoNextProblem();
					}
					break;
		
				// [S]ubmit a Problem
				case "S":
				case "s":
					System.out.println();
					System.out.println("Enter a multiple choice question (including the answer options): ");
					System.out.println("Type END on a line by itself to finish question.");
					
					// user's multi line question to submit
					String userSubmitQuestion = "";
					// temporary current line of question
					String currentLine = scnr.nextLine();
					userSubmitQuestion += currentLine;
					
					while(scnr.hasNext()){
						currentLine = scnr.nextLine();	
						if(!currentLine.equals("END")){
							userSubmitQuestion += currentLine + "\n";
						}
						else{
							break;
						}
					}
					System.out.print("Enter the correct answer for this question (one letter): ");
					// the answer to the user's submit question
					char userSubmitAnswer = scnr.next().charAt(0);
					userSubmitAnswer = Character.toUpperCase(userSubmitAnswer);
					
					System.out.println(P1.getSections());
					System.out.print("Enter the section for this problem: ");
					// the section of the user's submit question
					int userSubmitSection = scnr.nextInt();
					System.out.println();
					
					System.out.print("Please proofread your problem and confirm you would like to submit it (Y/N): ");
					// user's confirmation to submit question
					char userSubmitConfirm = scnr.next().charAt(0); // User's input confirmation
					userSubmitConfirm = Character.toUpperCase(userSubmitConfirm);
					if (userSubmitConfirm == 'Y') {
						P1.submitProblem( userSubmitQuestion, userSubmitAnswer, userSubmitSection, userEmail );
						System.out.print("Thank you for submitting this new problem.");
						System.out.println();
					}
					else {
						System.out.print("Problem was NOT submitted.");
						System.out.println();
					}
					break;
				
				// [Q]uit
				case "Q":
				case "q":
					System.out.println();
					System.out.println("==========================================");
					System.out.println("Thank you for using the CS302 Review Quiz!");
					break;
					
				// Invalid Menu Choice
				default:
					System.out.println();
					System.out.println("Sorry, but that menu choice was not recognized.");
					break;
			} // switch
		} // while
		P1.done();
	}
}

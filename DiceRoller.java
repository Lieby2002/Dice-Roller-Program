/**
 * 
 * DiceRoller
 * Primary Author: Casey R. Lieby
 * Secondary Contributors: 
 * Date Created: June 14, 2024
 * Date Last Modified: July 19, 2024
 * Version MainUpdate.MinorUpdate.BugFix: 1.1.0
 * Accepts a string of numerical inputs corresponding to one or several dice rolls, 
 * generates a random number then outputs the result. 
 * 
 */
import java.util.*;
public class DiceRoller {
	
	private ArrayList<String> input; // Array List to hold parsed rolls
	private ArrayList<String> rolls; // Array List to enable
	// the displaying of the result of each die
	
	/**
	 * Constructor, allows the class to operate without needing every method to be static. 
	 */
	public DiceRoller() {
		rolls = new ArrayList<String>();
	}
	
	/**
	 * Test Method
	 * 
	 * input String[] args - Input from terminal, presently unused
	 * 
	 * output void = no output.
	 */
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		DiceRoller d = new DiceRoller();
		while(true) {
			System.out.println("Enter Dice Roll: ");
			String in = scan.nextLine();
			if(in.equals("DONE")) break;
			int roll = d.diceRoll(in);
			
			System.out.println("Rolls: " + d.rolls + "\nSum: " + roll + "\n");
			d.rolls.clear();
			d.input.clear();
		}
		scan.close();
	}
	
	/**
	 * Operating Method, takes input from test method and translates it into mathematical 
	 * operation. Uses the operation pattern of Roll Dice > Multiply > Divide > Remainder > 
	 * Addition > Subtraction  
	 * 
	 *  Input String roll - Input from test method
	 *  
	 *  Output int result - result of roll
	 */
	//@SuppressWarnings("removal")
	public int diceRoll(String roll) {
		
		// Parses roll into subsections to allow for future operations
		input = parseRequest(roll);
		//System.out.println(input.toString());
		// checks if the input was valid
		if(input.get(0) == "Invalid Input") {
			System.out.println(input.get(0));
			return -1;
		}
		
		// Separates the operators from the other items for ease of counting operation 
		// quantity
		ArrayList<String> operations = new ArrayList<String>();
		for(int i = 0 ; i < input.size() ; i++) {
			if(input.get(i).equals("d") || input.get(i).equals("+") || 
			   input.get(i).equals("-") || input.get(i).equals("/") || 
			   input.get(i).equals("*") || input.get(i).equals("%")) {
				operations.add(input.get(i));  
			}
		}
		
		//Performs Dice Rolls
		for (int i = 0 ; i < input.size() ;) {
			int dice = 0;
			if(input.get(i).equals("d")) {
				if (i == 0) {
					dice += roll(Integer.valueOf(1), Integer.valueOf(input.get(i+1))); 
					input.remove(i+1);
				} else if(input.get(i-1).charAt(0) >= '0' && input.get(i-1).charAt(0) <= '9'){
					dice += roll(Integer.valueOf(input.get(i-1)), Integer.valueOf(input.get(i+1)));
					input.remove(i+1);
					input.remove(i-1);
					i--;
					
				} else {
					dice += roll(Integer.valueOf(1), Integer.valueOf(input.get(i+1)));
					input.remove(i+1);
				}
				input.set(i, dice+"");
				//System.out.println(input);
			} else {
				i++;
				}
		}
		
		// Looks for Sections in need of multiplication then multiplies
		for (int i = 0 ; i < input.size() ;) {
			int mult = 0;
			if(input.get(i).equals("*")) {
				if (i == 0) {
					mult += multiply(Integer.valueOf(1), Integer.valueOf(input.get(i+1))); 
					input.remove(i+1);
				} else if(input.get(i-1).charAt(0) >= '0' && input.get(i-1).charAt(0) <= '9'){
					mult += multiply(Integer.valueOf(input.get(i-1)), Integer.valueOf(input.get(i+1)));
					input.remove(i+1);
					input.remove(i-1);
					i--;
					
				} else {
					mult += multiply(Integer.valueOf(1), Integer.valueOf(input.get(i+1)));
					input.remove(i+1);
				}
				input.set(i, mult+"");
				//System.out.println(input);
			} else {
				i++;
				}
		}
		
		// Looks for Sections in need of division then divides
		for (int i = 0 ; i < input.size() ;) {
			int div = 0;
			if(input.get(i).equals("/")) {
				if (i == 0) {
					div += divide(Integer.valueOf(1), Integer.valueOf(input.get(i+1))); 
					input.remove(i+1);
				} else if(input.get(i-1).charAt(0) >= '0' && input.get(i-1).charAt(0) <= '9'){
					div += divide(Integer.valueOf(input.get(i-1)), Integer.valueOf(input.get(i+1)));
					input.remove(i+1);
					input.remove(i-1);
					i--;
					
				} else {
					div += divide(Integer.valueOf(1), Integer.valueOf(input.get(i+1)));
					input.remove(i+1);
				}
				input.set(i, div+"");
				//System.out.println(input);
			} else {
				i++;
				}
		}
				
		// Looks for modula operations and performs them
		for (int i = 0 ; i < input.size() ;) {
			int mod = 0;
			if(input.get(i).equals("%")) {
				if (i == 0) {
					mod += remainder(Integer.valueOf(1), Integer.valueOf(input.get(i+1))); 
					input.remove(i+1);
				} else if(input.get(i-1).charAt(0) >= '0' && input.get(i-1).charAt(0) <= '9'){
					mod += remainder(Integer.valueOf(input.get(i-1)), Integer.valueOf(input.get(i+1)));
					input.remove(i+1);
					input.remove(i-1);
					i--;
					
				} else {
					mod += remainder(Integer.valueOf(1), Integer.valueOf(input.get(i+1)));
					input.remove(i+1);
				}
				input.set(i, mod+"");
				//System.out.println(input);
			} else {
				i++;
				}
		}
		
		// Performs Addition Operations
		for (int i = 0 ; i < input.size() ;) {
			int sum = 0;
			if(input.get(i).equals("+")) {
				if (i == 0) {
					sum += addition(Integer.valueOf(1), Integer.valueOf(input.get(i+1))); 
					input.remove(i+1);
				} else if(input.get(i-1).charAt(0) >= '0' && input.get(i-1).charAt(0) <= '9'){
					sum += addition(Integer.valueOf(input.get(i-1)), Integer.valueOf(input.get(i+1)));
					input.remove(i+1);
					input.remove(i-1);
					i--;
					
				} else {
					sum += addition(Integer.valueOf(1), Integer.valueOf(input.get(i+1)));
					input.remove(i+1);
				}
				input.set(i, sum+"");
				//System.out.println(input);
			} else {
				i++;
				}
		}
		
		// Performs Subtraction Operations
		for (int i = 0 ; i < input.size() ;) {
			int rem = 0;
			if(input.get(i).equals("-")) {
				if (i == 0) {
					rem += subtraction(Integer.valueOf(1), Integer.valueOf(input.get(i+1))); 
					input.remove(i+1);
				} else if(input.get(i-1).charAt(0) >= '0' && input.get(i-1).charAt(0) <= '9'){
					rem += subtraction(Integer.valueOf(input.get(i-1)), Integer.valueOf(input.get(i+1)));
					input.remove(i+1);
					input.remove(i-1);
					i--;
					
				} else {
					rem += subtraction(Integer.valueOf(1), Integer.valueOf(input.get(i+1)));
					input.remove(i+1);
				}
				input.set(i, rem+"");
				//System.out.println(input);
			} else {
				i++;
				}
		}
		
		Integer intResult = Integer.valueOf(input.get(0));
		int result = intResult.intValue();
		return result;
	}
	
	/**
	 * subtracts the entered numbers and finds the difference. 
	 * 
	 * input Integer x, Integer y - subtracted numbers
	 * 
	 * output int difference - difference of subtraction operation
	 */
	private int subtraction(Integer x, Integer y) {
		return x.intValue() - y.intValue();
	}

	/**
	 * adds the entered numbers and finds the sum. 
	 * 
	 * input Integer x, Integer y - added numbers
	 * 
	 * output int sum - sum of addition operation
	 */
	private int addition(Integer x, Integer y) {
		return x.intValue() + y.intValue();
	}

	/**
	 * divides the entered numbers and finds the remainder. 
	 * 
	 * input Integer x, Integer y - divided numbers
	 * 
	 * output int remainder - remainder of division operation
	 */
	private int remainder(Integer x, Integer y) {
		return x.intValue() % y.intValue();
	}

	/**
	 * divides the entered numbers. 
	 * 
	 * input Integer x, Integer y - divided numbers
	 * 
	 * output int quotient - quotient of division operation
	 */
	private int divide(Integer x, Integer y) {
		return x.intValue() / y.intValue();
	}

	/**
	 * Multiplies the entered numbers. 
	 * 
	 * input Integer x, Integer y - multiplied numbers
	 * 
	 * output int product - product of multiplication operation
	 */
	private int multiply(Integer x, Integer y) {
		return x.intValue() * y.intValue();
	}

	/**
	 * Parses String from Dice Roller into subsections to allow 
	 * other methods to perform their operations
	 * 
	 * input String roll - string to be parsed
	 * 
	 * output ArrayList<String> parsedString - Arraylist of numbers and operation relevant characters
	 */
	private ArrayList<String> parseRequest(String roll){
		ArrayList<String> parsedString = new ArrayList<String>();
		parsedString.add("");
		char[] pstr = roll.toCharArray();
		//System.out.println(pstr);
		int psIndex = 0;
		
		for(int i = 0; i < pstr.length ; i++) {
			if (pstr[i] >= '0' && pstr[i] <= '9') {
					parsedString.set(psIndex, parsedString.get(psIndex)+pstr[i]); 
			} else if(pstr[i] == 'd' || pstr[i] == '+' || pstr[i] == '-' || 
					  pstr[i] == '/' || pstr[i] == '*' || pstr[i] == '%'){
				parsedString.add(pstr[i]+"");
				psIndex +=2;
				parsedString.add("");
			} else if(pstr[i] == ' '){
			} else {
				parsedString.clear(); 
				parsedString.add("Invalid Input");
				break;
			}
		}
		/*/*/if (parsedString.get(0) == "") {parsedString.remove(0);}
		return parsedString;
	}
	/**
	 * Generates a series of random numbers based upon the number of dice and size of dice. Sums the 
	 * rolls and adds the individual values of each roll to the rolls Array List
	 * 
	 * input Integer x, Integer y - Number of dice and size of dice
	 * 
	 * outputs int sum - sum of all dice rolls
	 * 
	 * modifies rolls - adds the requested roll and the result of each individual die roll. 
	 */
	private int roll(Integer x, Integer y) {
		rolls.add(x + "d" + y);
		int sum = 0;
		for(int i = 0 ; i < x.intValue() ; i++) {
			int roll = (int)(Math.random()*y.intValue())+1;
			rolls.add(roll+"");
			sum += roll;
			//System.out.println(sum);
		}
		
		return sum;
	} 

}

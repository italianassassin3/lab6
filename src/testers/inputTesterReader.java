package testers;


import java.io.IOException;
import java.util.Scanner;

import inputHandler.inputReader;
import inputHandler.jobs;
import queue.ArrayQueue;
import queue.Queue;
import queue.SLLQueue;



public class inputTesterReader {

	public static void main(String[] args)  {
	inputReader test = new inputReader();

		
				Queue<Integer> QA1 = new ArrayQueue<>();
				Queue<Integer> QA = QA1;						

				Queue<Integer> QS1 = new SLLQueue<>();
				Queue<Integer> QS = QS1;						
				
				
				Queue<jobs> QS2 = new SLLQueue<>();
				 
				Queue<jobs> QA2 = new ArrayQueue<>();
				
				test.testQ(QA2);
					
				 	
				System.out.println();
				test.testQ(QS2);
				
				
			//	System.out.println("The result by applying SLLQueue" + "\n" );

				
				
				System.out.println();
				
				
				
//				Scanner decision = new Scanner(System.in);
//				
//				System.out.println("By which method do you want to test:" +"\n" + "1)Test an arrayQueue" + "\n"+"2) Test a SLLQueue");
//				
//				int choice = decision.nextInt();
//				
//				if(choice == 1){
//					
//					test.testQ(QA2);
//				}
//				else 
//					
//				test.testQ(QS2);
	
	
	}

	
	
}

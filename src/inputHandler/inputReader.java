package inputHandler;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import queue.Queue;
import queue.SLLQueue;
import testers.inputTesterReader;


public class inputReader<E> extends inputTesterReader{
	
public static <E> void testQ(Queue<jobs> s) {
		
		Queue<jobs> inputQueue = s;
		Queue<jobs> processingQueuue = new SLLQueue<>();
		
		ArrayList <jobs>terminatedJobs = new ArrayList<>(); 
		
		
		double  column1=0;
		double  column2=0;
		double TotalTime=0;
		int numberOfElements=0;
		double DepartureTime=0;
		double ArrivalTime=0;
		
	

		Scanner read = new Scanner(System.in);
		

		String csvFile= read.nextLine();
		
		File inputFile= new File(csvFile);
		
		try {
			Scanner input = new Scanner(inputFile);
			while( input.hasNext()){
				String data = input.nextLine();
				String queueContent[]=data.split(",");
				column1= Double.parseDouble(queueContent[0]);
				column2= Double.parseDouble(queueContent[1]);
				numberOfElements++;
				inputQueue.enqueue(new jobs(column1,column2));
			}
				
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		int t =0;		

		while (!inputQueue.isEmpty()|| !processingQueuue.isEmpty()){
		
			if (!processingQueuue.isEmpty()){
				jobs outputJobs = processingQueuue.dequeue();
				int serviceTime = (int) (outputJobs.getServiceTime()-1);
				outputJobs.setServiceTime(serviceTime);
				if ( serviceTime==0){
					outputJobs.setDepartureTime(t);
					terminatedJobs.add(outputJobs);
				}
				else{
					processingQueuue.enqueue(outputJobs);
				}
			}
			if(!inputQueue.isEmpty()&& t==inputQueue.first().getProcessID()){
				processingQueuue.enqueue(inputQueue.dequeue());
			}

			t++;
	
		}
		
		for (int i = 0; i < numberOfElements; i++) {
			DepartureTime= terminatedJobs.get(i).getdepartureTime();
			column1= DepartureTime-terminatedJobs.get(i).getProcessID();
			TotalTime+=column1;	
		}


			
		System.out.printf("Average Time in System is: " + "%.2f",TotalTime/numberOfElements);
	
}

}

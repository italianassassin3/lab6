import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class inputReader {

	public interface Queue<E> {
		   /** Accessor Method.
	      Returns the size of the current instance
	**/
	int size();

	/** Accessor Method.
		Returns true if the current instance is empty; false, if not.  
	**/
	boolean isEmpty();

	/** Accessor Method.
		Accesses element in the current instance of the queue.
		The affected element is the one that has been in the queue
	  for the longest time among all its current elements.
		Returns reference to the element being accessed.
		Returns null if queue is empty. 
	  (Note that we named it front() in lectures....)
	**/
	E first();

	/** Mutator Method.
		Adds a new element to the queue.  
	**/
	void enqueue(E element);

	/** Mutator Method.
		Similar to the first() method, but this time, the queue is
	  altered since the accessed element is also removed from
		the queue. Returns null if the queue is empty.  
	**/
	E dequeue();

		//just for testing
		void showReverse(); 
	}

	public static class Job {

		private double pid;
		
		int time =0;
		
		   private int arrivalTime;    // arrival time of this job
		   private int remainingTime;  // remaining service time for this job
		   private int departureTime;  // time when the service for this job is completed
		 
		   
		   public Job(int id, int at, int rt) { 
			pid = id; 
			arrivalTime = at; 
			remainingTime = rt; 
		   }

		
		public Job(double column1, double column2) {
			
			this.pid = column1;
			remainingTime = (int) column2;
		}

		public double getPid() {
			return pid;
		}

		public double getRemainingTime() {
			return remainingTime;
		}
		  public int getArrivalTime() {
				return arrivalTime;
			   }


		public void setRemainingTime(double serviceTime) {
			remainingTime = (int) serviceTime;
		}
		public void setDepartureTime(int t){
			time=t;
			departureTime = t;
		}
		public double  getdepartureTime(){
			departureTime = time;
			return time;
			
		}
		
		 /**
		    * Registers an update of serviced received by this job. 
		    * @param q the time of the service being registered. 
		    */
		   public void isServed(int q) { 
			if (q < 0) return;   // only register positive value of q
			   remainingTime -= q; 
		   }
			
		   /**
		    * Generates a string that describes this job; useful for printing
		    * information about the job.
		    */
		   public String toString() { 
			return "PID = " + pid + 
				 "  Arrival Time = " + arrivalTime +
				 "  Remaining Time = " + remainingTime +
				 "  Departure Time = " + departureTime; 				
		   }

		
	}
	
	public static class ArrayQueue<E> implements Queue<E> {
		
		private final static int INITCAP = 4; 
		private E[] elements; 
		private int first, size; 
		public ArrayQueue() { 
			elements = (E[]) new Object[INITCAP]; 
			first = 0; 
			size = 0; 
		}
		public int size() {
			return size;
		}

		public boolean isEmpty() {
			return size == 0;
		}

		public E first() {
			if (isEmpty()) return null; 
			return elements[first]; 
		}

		public E dequeue() {
			if (isEmpty()) return null;
			E etr = elements[first]; 
			elements[first] = null; 
			first = (first+1) % elements.length; 
			size--; 
			// Check if number of available positions in the array exceed 3/4
			// of its total length. If so, and if the current capacity is not
			// less than 2*INITCAP, shrink the internal array to 1/2 of its
			// current length (the capacity of the queue). 
			if (elements.length >= 2*INITCAP && size < elements.length/4)
				changeCapacity(elements.length/2); 
			return etr; 
		}

		
		
		

		public void enqueue(E e) {
			if (size == elements.length)   // check capacity, double it if needed
				changeCapacity(2*size); 
			elements[(first+size)%elements.length] = e; 
			size++; 
		}

		private void changeCapacity(int newCapacity) { 
			// PRE: newCapacity >= size
			E[] temp = (E[]) new Object[newCapacity]; 
			for (int i=0; i<size; i++) {
				int srcIndex = (first + i) % elements.length; 
				temp[i] = elements[srcIndex]; 
				elements[srcIndex] = null; 
			} 
			elements = temp; 
			first = 0; 
		}

		
		
		
		
		//JUST FOR TESTING
			@Override
			public void showReverse() { 
			    if (size == 0)
				   System.out.println("Queue is empty."); 
				else
				   recSR(first);
		    } 
		    private void recSR(int f) { 
				if (elements[f] != null) { 
				   recSR(f+1); 
				   System.out.println(elements[f]); 
			     } 
		    } 
	}

	
	public static  class SLLQueue<E> implements Queue<E> {

		private  class Node<E> {   // Inner class for nodes. 
			private E element; 
			private Node<E> next;
			public E getElement() {
				return element;
			}
			public Node<E> getNext() {
			
				return next;
			} 
			
		}	
		private Node<E> first, last;   // references to first and last node
		private int size; 
		
		public SLLQueue() {           // initializes instance as empty queue
			first = last = null; 
			size = 0; 
		}
		public int size() {
			return size;
		}
		public boolean isEmpty() {
			return size == 0;
		}
		public E first() {
			if (isEmpty()) return null;
			return first.getElement(); 
		}
		public E dequeue() {
			if (isEmpty()) return null;		
			E tmp = first.getElement();
				first = first.getNext();	
			size--;
			return tmp;
		}
		public void enqueue(E e) {
			if (size == 0) {
				first = last = new Node<>(); 
			first.element = e;}
			else { 
				Node<E> tmp = new Node<E>();
				tmp.element = e;
				last.next = tmp;
				last = tmp;
			}
			size++; 
		}


		//JUST FOR TESTING
		@Override
		public void showReverse() { 
		    if (size == 0)
			   System.out.println("Queue is empty."); 
			else
			   recSR(first);
	    } 
	    private void recSR(Node<E> f) { 
			if (f != null) { 
			   recSR(f.getNext()); 
			   System.out.println(f.getElement()); 
		     } 
	    } 

	}
	
	
	public static class ir<E> {
		
		public <E> void testQ(Queue<Job> s) {
				
				Queue<Job> inputQueue = s;
				Queue<Job> processingQueuue = new SLLQueue<>();
				
				ArrayList <Job>terminatedJobs = new ArrayList<>(); 
				
				
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
						inputQueue.enqueue(new Job(column1,column2));
					}
						
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}

				int t =0;		

				while (!inputQueue.isEmpty()|| !processingQueuue.isEmpty()){
				
					if (!processingQueuue.isEmpty()){
						Job outputJobs = processingQueuue.dequeue();
						int serviceTime = (int) (outputJobs.getRemainingTime()-1);
						outputJobs.setRemainingTime(serviceTime);
						if ( serviceTime==0){
							outputJobs.setDepartureTime(t);
							terminatedJobs.add(outputJobs);
						}
						else{
							processingQueuue.enqueue(outputJobs);
						}
					}
					if(!inputQueue.isEmpty()&& t==inputQueue.first().getPid()){
						processingQueuue.enqueue(inputQueue.dequeue());
					}

					t++;
			
				}
				
				for (int i = 0; i < numberOfElements; i++) {
					DepartureTime= terminatedJobs.get(i).getdepartureTime();
					column1= DepartureTime-terminatedJobs.get(i).getPid();
					TotalTime+=column1;	
				}


					
				System.out.printf("Average Time in System is: " + "%.2f",TotalTime/numberOfElements);
			
		}

		}
	
	
	public static void main(String[] args) {
		ir  test = new ir();


		
		Queue<Integer> QA1 = new ArrayQueue<>();
		Queue<Integer> QA = QA1;						

		Queue<Integer> QS1 = new SLLQueue<>();
		Queue<Integer> QS = QS1;						
		
		
		Queue<Job> QS2 = new SLLQueue<>();
		 
		Queue<Job> QA2 = new ArrayQueue<>();
		
		test.testQ(QA2);
			
		 	
		System.out.println();
		test.testQ(QS2);
		
		
	
		
		
		System.out.println();
		
		
		



}

	}



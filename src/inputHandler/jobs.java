package inputHandler;

public class jobs {

	private double processID;
	private double ServiceTime;
	int time =0;
	
	public jobs(double column1, double column2) {
		
		this.processID = column1;
		ServiceTime = column2;
	}

	public double getProcessID() {
		return processID;
	}

	public double getServiceTime() {
		return ServiceTime;
	}

	public void setServiceTime(double serviceTime) {
		ServiceTime = serviceTime;
	}
	public void setDepartureTime(int t){
		time=t;
	}
	public double  getdepartureTime(){
		return time;
	}
	
	
	
}

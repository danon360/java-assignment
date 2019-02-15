
public class node {

	int priority;
	long time;
	job value;
	
	public node() {
		priority=0;
		time=0;
		value=null;
	}
	public node(job val) {
		priority=val.getJobPriority();
		time=val.getTempTime();     //note that we're using temptime here.
		value=val;
	}
	public int compareTo(node param) {
		if(this.priority<param.priority) return 1; //if it has a higher priority (smaller no) then it returns 1
		if(this.priority>param.priority) return -1; ////if it has a lower priority (bigger no) then it returns -1
		if(this.priority==param.priority) {if(this.time<param.time)return 1; else return -1;}
		return 0;
	}
	public String toString() {
		 return this.value.toString();
	}
	
	
}

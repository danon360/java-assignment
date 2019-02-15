import java.util.ArrayList;
public class priorityQue {
	ArrayList<job> list ;
	public static int time =0;
	
public priorityQue() {
	list = new ArrayList<job>(10);
}
public priorityQue(int a) {
	list = new ArrayList<job>(a);
}

public boolean isEmpty() {
	return this.list.isEmpty();
}
public int size() {
	return this.list.size();
}
public job peek() {
	if (this.list.isEmpty()) return null;
	return this.list.get(0);
}
public int highestPriority() {
	if(this.list.isEmpty()) return -1;
	int highest =0;
	for(int i =1;i<this.list.size();i++) {
		if(this.list.get(highest).getJobPriority()>this.list.get(i).jobPriority) highest=i;
		if(this.list.get(highest).getJobPriority()==this.list.get(i).jobPriority) {
			if(this.list.get(highest).getTempTime()>this.list.get(i).tempTime) highest=i;
		}
	}
	
	return highest;
}
public job deleteMin() {
	job temp = this.list.get(this.highestPriority());
	this.list.remove(this.highestPriority());
	return temp;
}
public job execute() {
	time++;
	job temp =this.list.get(this.highestPriority());
	temp.setCurrentJobLength((temp.getCurrentJobLength()-1));
	if(temp.currentJobLength<1) {
		temp.setEndTime(time);
		temp.setWaitTime(temp.getEndTime() - temp.getEntryTime()-temp.getJobLength());
		this.list.remove(this.highestPriority());
		temp.toString();
		return temp;
	}else temp.setTempTime(time);
	temp.toString();
	return temp;
	
}
public void insert (job param) {
	time++;
	param.setTempTime(time);
	param.setEntryTime(time);
	this.list.add( param);
}
public void starvefun() {
	
	int lowest=this.size()-1;
	boolean changed = false;
	for(int i =0;i<this.size();i++) {
		job temp=this.list.get(i);   
		if((temp.getJobLength()==temp.getCurrentJobLength())&&this.list.get(lowest).entryTime>=this.list.get(i).entryTime) {lowest=i;changed = true;}
		//System.out.println(temp.jobName+" jl: "+temp.getJobLength()+" cjl: "+temp.getCurrentJobLength()+" bool: "+(temp.getJobLength()==temp.getCurrentJobLength())+ " "+(this.list.get(lowest).entryTime>=this.list.get(i).entryTime)+" s "+this.size()+" ls "+this.list.size());
	}
	//System.out.println("lowest is : "+ this.list.get(lowest).toString());
	//System.out.println("job length : "+this.list.get(lowest).getJobLength()+" current job len :  "+this.list.get(lowest).getCurrentJobLength()+" changed : "+changed+" first : "+this.list.get(0).jobName+" lowest: "+lowest);
	if(changed) {this.list.get(lowest).setJobPriority(1);}
	time++;
}// parent is k/2 child is 2k or 2k+1 for th right child
}

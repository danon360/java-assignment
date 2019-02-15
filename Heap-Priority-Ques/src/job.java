
public class job {
	String jobName;
	int jobLength;
	int currentJobLength;
	int jobPriority;
	int finalPriority;
	long entryTime;
	long waitTime;
	long endTime;
	long tempTime;
	public job() {
		jobName = null;
		currentJobLength=0;
		jobPriority = 0;
		finalPriority = 0;
		entryTime = 0;
		waitTime=0;
		endTime=0;
		tempTime =0;
	}
	public job(job j) {
		this.jobName = j.jobName;
		this.jobLength = j.jobLength;
		this.currentJobLength = j.currentJobLength;
		this.jobPriority = j.jobPriority;
		this.finalPriority = j.finalPriority;
		this.entryTime = 0;
		this.tempTime=0;
	}

	public job(String jobName, int jobLength, int jobPriority) {
		super();
		this.jobName = jobName;
		this.jobLength = jobLength;
		this.currentJobLength = jobLength;
		this.jobPriority = jobPriority;
		this.finalPriority = jobPriority;
		this.entryTime = 0;
		this.tempTime=0;
	}

	public long getTempTime() {
		return tempTime;
	}

	public void setTempTime(long tempTime) {
		this.tempTime = tempTime;
	}

	public int getJobLength() {
		return jobLength;
	}

	public void setJobLength(int jobLength) {
		this.jobLength = jobLength;
	}

	public int getCurrentJobLength() {
		return currentJobLength;
	}

	public void setCurrentJobLength(int currentJobLength) {
		this.currentJobLength = currentJobLength;
	}

	public int getJobPriority() {
		return jobPriority;
	}

	public void setJobPriority(int jobPriority) {
		this.jobPriority = jobPriority;
	}

	public int getFinalPriority() {
		return finalPriority;
	}

	public void setFinalPriority(int finalPriority) {
		this.finalPriority = finalPriority;
	}

	public long getEntryTime() {
		return entryTime;
	}

	public void setEntryTime(long entryTime) {
		this.entryTime = entryTime;
	}

	public long getWaitTime() {
		return waitTime;
	}

	public void setWaitTime(long waitTime) {
		this.waitTime = waitTime;
	}

	public long getEndTime() {
		return endTime;
	}

	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}
	
	public String toString() {
		return "Now executing "+this.jobName+". Job length: "+this.jobLength+" cycles; Current remaing length: "+this.currentJobLength+" cycles;"+ 
		"Initial priority: "+this.finalPriority+"; Current priority: "+this.jobPriority;
	}
}

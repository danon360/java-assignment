
public class PriorityQueueSimulatorTester {

	static int maxNumberOfJobs=10;
	public static void main(String[] args) {
		int randlength;
		int randpriority;
		int counter=0;
		int counter2=0;
		job[] jobsInputArray= new job[maxNumberOfJobs];
		job[] jobsInputArray2 = new job[maxNumberOfJobs];
		
		priorityQue pq = new priorityQue(maxNumberOfJobs);
		
		for(int i =0;i<jobsInputArray.length;i++) {
			randlength = (int)(Math.random()*70+1);
			randpriority = (int)(Math.random()*40+1);
			jobsInputArray[i]= new job("JOB_"+(i+1),randlength,randpriority);
			//System.out.println(jobsInputArray[i].toString());
		}
		for(int i =0;i<maxNumberOfJobs;i++) {
			jobsInputArray2[i] = new job(jobsInputArray[i]);
		}
		//System.out.println("//////////////////////////////");
		for(int i = 0;i<maxNumberOfJobs;i++) {
		pq.insert(jobsInputArray[i]);
		//System.out.println(pq.list.get(i).toString()+" entrytime: "+pq.list.get(i).getEntryTime());
		}
			
		while(!pq.isEmpty()) {
			if(counter==30) {
				pq.starvefun();
				counter=0;	
			}else {
				pq.execute();
				counter++;
			}
		}
		//heap
		heapPQ heap = new heapPQ();
		//filling heap
		for(int i =1;i<maxNumberOfJobs;i++) {
			heap.insert(new node(jobsInputArray2[i]));
		}
		heap.starvfun();
		System.out.println(heap.isEmpty());
		while(!heap.isEmpty()) {
			heap.execute();
			if(counter2==30) {
				heap.starvfun();
				counter2=0;
			}
		}
		
		System.out.println(heap.isEmpty());
				
				
				
	}
}

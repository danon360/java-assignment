import java.util.ArrayList;

public class heapPQ {
	ArrayList<node>list;
	static int time=0;
	public heapPQ() {
		list= new ArrayList();
		node node = new node();
	}
	public heapPQ(int len) {
		list = new ArrayList(len+1);
	}
	public int size() {
		return this.list.size();
	}
	protected int parent(int n) {
		return (n)/2;
	}
	protected int left(int n) {
		return 2*n;
	}
	protected int right(int n) {
		return 2*n+1;
	}
	protected boolean hasLeft(int n) {
		return this.size()>this.left(n);
	}
	protected boolean hasRight(int n) {
		return this.size()>this.right(n);
	}
	public void swap(int a, int b) {
		node temp = this.list.get(a);
		this.list.set(a, this.list.get(b));
		this.list.set(b, temp);
	}
	public void insert(node a){
		
		a.time=time;
		time++;
		if(this.list.size()==0) {list.add(0,null);list.add(1, a);}
		else list.add(a);
		//System.out.println(this.size());
		this.upheap(this.size()-1);
	}
	public boolean isEmpty() {
		if(this.list.get(1)==null)return true;
		else return false;
	}
	public void upheap(int z) {
		while(z>0) {
			//System.out.println(z);
			int p =parent(z);
			//System.out.println(p);
			if(p==0)break;
			if(this.list.get(z).compareTo(this.list.get(p))==-1) {break;}
			 if(this.list.get(z).compareTo(this.list.get(p))==1) {swap(z,p);}
			z=p;
		}
		
	}
	public void downheap(int n) {
		while(this.hasLeft(n)) {
			int leftIndex=this.left(n);
			int smallChildIndex= leftIndex;
			if(this.hasRight(n)) {
				int rightIndex=this.right(n);
				if((this.list.get(leftIndex).compareTo(this.list.get(rightIndex))==-1)) {
					smallChildIndex=rightIndex;
				}
			}
			if(this.list.get(smallChildIndex).compareTo(this.list.get(n))==-1) break;
			this.swap(n,smallChildIndex);
			n=smallChildIndex;
		}
	}
	public node remove(int n) {
		node temp =this.list.remove(n);
		this.downheap(n);
		return temp;
	}
	public void insertP(node n) {
		int priority =n.priority;
		int lastindex;
		for(int i=1;i<this.list.size();i++) {
			if(priority<this.list.get(i).priority) {
				this.insert(n);
			}
		}
	}
	public void execute() {
		time++;
		this.list.get(1).value.currentJobLength--;
		if(this.list.get(1).value.currentJobLength==0) {
			
		}else {
			this.insert(this.remove(1));
		}
	}
	public void starvfun() {
		int lowest=this.size()-1;
		boolean changed = false;
		for(int i =1;i<this.size();i++) {
			job temp=this.list.get(i).value;   
			if((temp.getJobLength()==temp.getCurrentJobLength())&&this.list.get(lowest).time>=this.list.get(i).time) {lowest=i;changed = true;}
			//System.out.println(temp.jobName+" jl: "+temp.getJobLength()+" cjl: "+temp.getCurrentJobLength()+" bool: "+(temp.getJobLength()==temp.getCurrentJobLength())+ " "+(this.list.get(lowest).entryTime>=this.list.get(i).entryTime)+" s "+this.size()+" ls "+this.list.size());
		}
		//System.out.println("lowest is : "+ this.list.get(lowest).toString());
		//System.out.println("job length : "+this.list.get(lowest).getJobLength()+" current job len :  "+this.list.get(lowest).getCurrentJobLength()+" changed : "+changed+" first : "+this.list.get(0).jobName+" lowest: "+lowest);
		if(changed) {this.list.get(lowest).value.setJobPriority(1);}
		time++;
	}
}

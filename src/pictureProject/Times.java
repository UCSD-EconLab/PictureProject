package pictureProject;

public class Times{

	private long[] times = new long[24];

	public Times(){}

	public void setTime(int index, long val){
		times[index] = val;
	}

	public long[] getTimes(){
		return times;
	}

	public long getTime(int index){
		return times[index];
	}

}
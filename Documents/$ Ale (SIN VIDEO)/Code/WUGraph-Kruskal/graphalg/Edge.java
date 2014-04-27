package graphalg;

public class Edge implements Comparable {

	protected Object v1;
	protected Object v2;
	protected int w;

	protected Edge(Object vertex1, Object vertex2, int weight){

		v1 = vertex1;
		v2 = vertex2;
		w = weight;

	}

	public int getWeight(){
		int s = w;
		return s;
	}

	public Object getVertex1(){
		return v1;
	}

	public Object getVertex2(){
		return v2;
	}

	public int compareTo(Object e){

		if(this.getWeight() == ((Edge)e).getWeight()){
			return 0;
		} else if (this.getWeight() < ((Edge)e).getWeight()){
			return -1;
		} else {
			return 1;
		}

	}

	/*public int compareExactlyTo(Object e){

		Edge edge = (Edge) e;


		if(this.getWeight() == ((Edge) edge).getWeight() && this.getVertex1() == edge.getVertex1() && this.getVertex2() == edge.getVertex2()){
			return 0;
		} else if (this.getWeight() < ((Edge)edge).getWeight()){
			return -1;
		} else {
			return 1;
		}

	}*/

	public int compareExactlyTo(Edge edge){

		if(this.getWeight() == edge.getWeight() && this.getVertex1() == edge.getVertex1() && this.getVertex2() == edge.getVertex2()){
			return 0;
		} else if (this.getWeight() < edge.getWeight()){
			return -1;
		} else {
			return 1;
		}

	}

	public String toString(){

		String s = "V1: " + getVertex1()+ " V2: " + getVertex2()+ " Weight: " + getWeight();
		return s;
	}

}
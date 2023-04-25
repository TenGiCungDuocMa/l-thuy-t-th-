package Graph2;

public class Edge implements Comparable<Edge> {
	private int start;
	private int end;
	private Double weight;

	/**
	 * @param start
	 * @param end
	 * @param weight
	 */
	public Edge(int start, int end, Double weight) {
		this.start = start;
		this.end = end;
		this.weight = weight;
	}

	@Override
	public int compareTo(Edge o) {
		if (this.weight < o.weight) {
			return -1;
		}
		if (this.weight > o.weight) {
			return 1;
		}
		return 0;
	}

	/**
	 * @return the start
	 */
	public int getStart() {
		return start;
	}

	/**
	 * @param start the start to set
	 */
	public void setStart(int start) {
		this.start = start;
	}

	/**
	 * @return the end
	 */
	public int getEnd() {
		return end;
	}

	/**
	 * @param end the end to set
	 */
	public void setEnd(int end) {
		this.end = end;
	}

	/**
	 * @return the weight
	 */
	public Double getWeight() {
		return weight;
	}

	/**
	 * @param weight the weight to set
	 */
	public void setWeight(Double weight) {
		this.weight = weight;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "(" + this.start + ", " + this.end + ", " + this.weight + ")";
	}

}
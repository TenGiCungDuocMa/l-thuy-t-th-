package Graph;

public class Test {

	public static void main(String[] args) {
		Graph gr = new UndirectedGraph(5);
		gr.addEdge(1, 0);
		gr.addEdge(0, 2);
		gr.addEdge(3, 0);
		gr.addEdge(0, 4);
		gr.addEdge(1, 2);
		gr.addEdge(1, 3);
		gr.addEdge(1, 4);
		gr.addEdge(2, 3);
		gr.addEdge(2, 4);
		gr.addEdge(3, 4);
		System.out.println(gr.numOfEdge());
		System.out.println(gr.chuTrinhEuler());
	}
}

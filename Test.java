package Graph2;

public class Test {
	public static void main(String[] args) {
		Graph u = new UnGraph(7);
		u.addEdge(0, 1, 8.0);
		u.addEdge(3, 4, 5.0);
		u.addEdge(1, 2, 4.0);
		u.addEdge(1, 3, 1.0);
		u.addEdge(2, 5, 3.0);
		u.addEdge(4, 6, 3.0);
		u.addEdge(5, 4, 6.0);
		u.addEdge(5, 6, 2.0);
//		FreeTree f = u.KRUSKAL();
//		f.printEdge();
//		f.printMatrix();
		u.dijsktra(0,2);
		
	}
}
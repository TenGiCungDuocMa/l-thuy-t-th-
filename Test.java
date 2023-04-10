package Graph;

public class Test {

	public static void main(String[] args) {
		Graph gr = new UndirectedGraph(8);
		gr.addEdge(1, 0);
		gr.addEdge(3, 0);
		gr.addEdge(0, 4);
		gr.addEdge(1, 5);
		gr.addEdge(1, 2);
		gr.addEdge(1, 3);
		gr.addEdge(1, 4);
		gr.addEdge(2, 7);
		gr.addEdge(5, 2);
		gr.addEdge(3, 4);
		gr.addEdge(3, 4);
		gr.addEdge(3, 4);
		gr.addEdge(3, 5);
		gr.addEdge(3, 7);
		gr.addEdge(5, 7);
		gr.addEdge(6, 7);
//		gr.spanningTreeDFS().printMatrix();
//		gr.spanningTreeBFS().printMatrix();
		
	
		Digraph gr1 = new Digraph(4);
		gr1.addEdge(0, 1);
		gr1.addEdge(3, 0);
		gr1.addEdge(2, 1);
		gr1.addEdge(1, 2);
		gr1.addEdge(1, 3);
		gr1.addEdge(2, 3);
		gr1.addEdge(3, 2);
//		System.out.println(gr1.lienThong());
//		System.out.println(gr1.isEulerGraph());
//		System.out.println(gr1.chuTrinhEuler());
//		System.out.println(gr1.nuaEulerKhong());
//		System.out.println(gr1.cacDinhKhongCanBang());
//		System.out.println(gr1.duongDiEuler());
		
		Tree tree = new Tree(9);
		tree.addEdge(0, 1);
		tree.addEdge(0, 2);
		tree.addEdge(1, 3);
		tree.addEdge(1, 4);
		tree.addEdge(4, 8);
		tree.addEdge(2, 5);
		tree.addEdge(2, 6);
		tree.addEdge(6, 7);
//		tree.printMatrix();
//		System.out.println(tree.isCompleteTree());
//		System.out.println(tree.isTree());
//		System.out.println(tree.eccentricityBFS(0));
//		System.out.println(tree.eccentricityDFS(0));
//		System.out.println(tree.radiusTree());
//		System.out.println(tree.centersTree());
//		System.out.println(tree.pathFromXtoY(0, 1));
		tree.printEdge();
		tree.switchToRootTree(0).printEdge();
	}
}
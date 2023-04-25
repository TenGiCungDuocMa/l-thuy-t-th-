package Graph2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class UnGraph extends Graph {

	public UnGraph(int n) {
		super(n);
	}

	public UnGraph(Double[][] m) {
		super(m);
	}

	@Override
	public void addEdge(int u, int v, Double weight) {
		if ((u < 0 || u > weightMatrix.length - 1)) {
			throw new RuntimeException("Đỉnh không hợp lệ!");
		}
		weightMatrix[u][v] = weight;
		weightMatrix[v][u] = weight;
	}

	@Override
	public void destroyEdge(int u, int v) {
		if ((u < 0 || u > weightMatrix.length - 1)) {
			throw new RuntimeException("Đỉnh không hợp lệ!");
		}
		if (weightMatrix[u][v] != Double.POSITIVE_INFINITY) {
			weightMatrix[u][v] = Double.POSITIVE_INFINITY;
			weightMatrix[v][u] = Double.POSITIVE_INFINITY;
		} else {
			throw new RuntimeException("Edge not found!");
		}

	}

	@Override
	public int deg(int v) {
		int res = 0;
		if (v < 0 && v > weightMatrix.length - 1) {
			throw new RuntimeException("Đỉnh không hợp lệ!");
		}
		for (int i = 0; i < weightMatrix.length; i++) {
			if (weightMatrix[v][i] != Double.POSITIVE_INFINITY) {
				res++;
			}
		}
		return res;
	}

	@Override
	public Double totalWeight() {
		Double res = 0.0;
		for (int i = 0; i < weightMatrix.length; i++) {
			for (int j = 0; j < i + 1; j++) {
				if (weightMatrix[i][j] != Double.POSITIVE_INFINITY) {
					res += weightMatrix[i][j];
				}
			}
		}
		return res;
	}

	@Override
	public boolean connected() {
		Queue<Integer> q = new LinkedList<Integer>();
		boolean[] visited = new boolean[weightMatrix.length];
		visited[0] = true;
		q.add(0);
		while (!q.isEmpty()) {
			Integer v = q.poll();
			ArrayList<Integer> dinhKe = adjacentVertices(v);
			for (Integer i : dinhKe) {
				if (visited[i] == false) {
					q.add(i);
					visited[i] = true;
				}
			}
		}
		for (int i = 0; i < visited.length; i++) {
			if (!visited[i]) {
				return false;
			}
		}
		return true;
	}

	@Override
	public void printEdge() {
		String res = "";
		for (int i = 0; i < weightMatrix.length; i++) {
			for (int j = 0; j <= i; j++) {
				if ((weightMatrix[i][j] != Double.POSITIVE_INFINITY)) {
					res += "( " + i + ", " + j + ", " + weightMatrix[i][j] + " ), ";
				}
			}
		}
		System.out.println(res);
	}
	public FreeTree KRUSKAL() {
		FreeTree t = null;
		if (connected()) {
			t = new FreeTree(weightMatrix.length);
			PriorityQueue<Edge> pqueue = new PriorityQueue<Edge>();
			for (int i = 0; i < weightMatrix.length; i++) {
				for (int j = 0; j < i + 1; j++) {
					if (weightMatrix[i][j] !=  Double.POSITIVE_INFINITY) {
						pqueue.add(new Edge(i, j, weightMatrix[i][j]));

					}
				}
			}
		
			int count = 0;
			while (count < weightMatrix.length -1) {
				Edge e = pqueue.poll();
				int x = e.getStart();
				int y = e.getEnd();
				Double w = e.getWeight();
				if (!t.fromXtoY(x, y)) {
					count++;
					t.addEdge(x, y, w);
				}
		}
		}
		return t;
	}

}
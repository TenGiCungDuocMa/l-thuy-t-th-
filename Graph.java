package Graph2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;

public abstract class Graph {
	protected Double[][] weightMatrix;

	public Graph(Double[][] m) {
		Double[][] matrix = new Double[m.length][m.length];
		for (int i = 0; i < m.length; i++) {
			for (int j = 0; j < m[i].length; j++) {
				matrix[i][j] = m[i][j];
			}
		}
		this.weightMatrix = matrix;
	}

	public Graph(int n) {
		this.weightMatrix = new Double[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				weightMatrix[i][j] = Double.POSITIVE_INFINITY;
			}
		}
	}

	public abstract int deg(int v);

	public abstract Double totalWeight();

	public abstract void addEdge(int u, int v, Double weight);

	public abstract void destroyEdge(int u, int v);

	public abstract boolean connected();

	public abstract void printEdge();

	public Double[][] getWeightMatrix() {
		return weightMatrix;
	}

	public void setWeightMatrix(Double[][] weightMatrix) {
		this.weightMatrix = weightMatrix;
	}

	public void printMatrix() {
		String s = "";
		for (int i = 0; i < weightMatrix.length; i++) {
			for (int j = 0; j < weightMatrix[i].length; j++) {
				if (weightMatrix[i][j] == Double.POSITIVE_INFINITY) {
					s += "~" + "\t";
				} else {
					s += weightMatrix[i][j] + "\t";
				}
			}
			s += "\n";
		}
		System.out.println(s);
	}

	public int numEdge() {
		int res = 0;
		int totalDeg = 0;
		for (int i = 0; i < weightMatrix.length; i++) {
			totalDeg += deg(i);
		}
		res = totalDeg / 2;
		return res;
	}

	public ArrayList<Integer> adjacentVertices(int v) {
		ArrayList<Integer> res = new ArrayList<Integer>();
		for (int i = 0; i < weightMatrix.length; i++) {
			if (weightMatrix[v][i] != Double.POSITIVE_INFINITY) {
				res.add(i);
			}
		}
		return res;
	}

	public boolean fromXtoY(int x, int y) {
		Stack<Integer> stack = new Stack<Integer>();
		boolean[] visited = new boolean[weightMatrix.length];
		stack.push(x);
		visited[x] = true;
		while (!stack.isEmpty()) {
			int count = 0;
			Integer v = stack.peek();
			if (v == y) {
				return true;
			}
			ArrayList<Integer> dinhKe = adjacentVertices(v);
			for (Integer i : dinhKe) {
				if (visited[i] == false) {
					v = i;
					stack.push(v);
					visited[i] = true;
					break;
				} else {
					count++;
				}

			}
			if (count == dinhKe.size()) {
				stack.pop();
			}

		}
		return false;
	}

	public List<Integer> pathFromXtoY(int x, int y) {
		List<Integer> result = new ArrayList<Integer>();
		if (fromXtoY(x, y)) {
			Stack<Integer> stack = new Stack<Integer>();
			boolean[] visited = new boolean[weightMatrix.length];
			stack.push(x);
			visited[x] = true;
			while (!stack.isEmpty()) {
				int count = 0;
				Integer v = stack.peek();
				if (v == y) {
					result.addAll(stack);
					break;
				}
				ArrayList<Integer> dinhKe = adjacentVertices(v);
				for (Integer i : dinhKe) {
					if (visited[i] == false) {
						v = i;
						stack.push(v);
						visited[i] = true;
						break;
					} else {
						count++;
					}

				}
				if (count == dinhKe.size()) {
					Integer num = stack.pop();
				} else {
					count = 0;
				}

			}
		} else {
			throw new RuntimeException("There is no path from " + x + " to " + y + " !");
		}
		return result;
	}

	public abstract FreeTree KRUSKAL();

	public void dijsktra(int s) {
		if (this.connected()) {
			int sizeMatrix = weightMatrix.length;
			List<Integer> R = new ArrayList<Integer>();
			for (int i = 0; i < sizeMatrix; i++) {
				R.add(i);
			}
			double[] L = new double[sizeMatrix];
			int[] P = new int[sizeMatrix];
			for (Integer r : R) {
				L[r] = Double.POSITIVE_INFINITY;
				P[r] = -1;
			}
			L[s] = 0;
			while (!R.isEmpty()) {
				Integer v = R.get(0);
				for (Integer r : R) {
					v = (L[v] > L[r]) ? r : v;
				}

				for (Integer i : adjacentVertices(v)) {
					if (L[i] > L[v] + weightMatrix[v][i]) {
						L[i] = L[v] + weightMatrix[v][i];
						P[i] = v;
					}
				}
				R.remove(v);
			}
			System.out.println(Arrays.toString(L));
			System.out.println(Arrays.toString(P));
		}
	}
	public void dijsktra(int s, int d) {
		if (this.connected()) {
			int sizeMatrix = weightMatrix.length;
			List<Integer> R = new ArrayList<Integer>();
			for (int i = 0; i < sizeMatrix; i++) {
				R.add(i);
			}
			double[] L = new double[sizeMatrix];
			int[] P = new int[sizeMatrix];
			for (Integer r : R) {
				L[r] = Double.POSITIVE_INFINITY;
				P[r] = -1;
			}
			L[s] = 0;
			a :while (!R.isEmpty()) {
				Integer v = R.get(0);
				for (Integer r : R) {
					v = (L[v] > L[r]) ? r : v;
				}
				if (v.equals(d)) {
					break a;
				}
				for (Integer i : adjacentVertices(v)) {
					if (L[i] > L[v] + weightMatrix[v][i]) {
						L[i] = L[v] + weightMatrix[v][i];
						P[i] = v;
					}
				}
				R.remove(v);
			}
			System.out.println(Arrays.toString(L));
			System.out.println(Arrays.toString(P));
		}
	}

}
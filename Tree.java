package Graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Tree extends UndirectedGraph {

	public Tree(int len) {
		super(len);
		// TODO Auto-generated constructor stub
	}

	public Tree(int[][] maTranKe) {
		super(maTranKe);

	}

	@Override
	public void addEdge(int u, int v) {
		if (!this.fromXtoY(u, v)) {
			if ((u < 0 && u > maTranKe.length - 1) || (v < 0 && v > maTranKe.length - 1)) {
				throw new RuntimeException("Đỉnh không hợp lệ!");
			}
			this.maTranKe[u][v]++;
			this.maTranKe[v][u]++;

		} else {
			throw new RuntimeException("Can't create edge!");
		}
	}

	public boolean isCompleteTree() {
		return (this.numOfEdge() == (this.maTranKe.length - 1));
	}

	// Tính độ lệch tâm của một đỉnh v duyệt theo BFS
	public int eccentricityBFS(int v) {
		int result = 0;
		int[] deviationOfVertices = new int[maTranKe.length];
		boolean[] visited = new boolean[maTranKe.length];
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(v);
		visited[v] = true;
		while (!queue.isEmpty()) {
			Integer i = queue.poll();
			List<Integer> cacDinhKe = cacDinhKe(i);
			for (Integer j : cacDinhKe) {
				if (!visited[j]) {
					deviationOfVertices[j] = deviationOfVertices[i] + 1;
					queue.add(j);
					visited[j] = true;
				}
			}

		}
		for (int i : deviationOfVertices) {
			result = (result > i) ? result : i;
		}
		return result;
	}

	// Tính độ lệch tâm của một đỉnh v duyệt theo DFS
	public int eccentricityDFS(int v) {
		int result = 0;
		int[] deviationOfVertices = new int[maTranKe.length];
		for (int i = 0; i < maTranKe.length; i++) {
			deviationOfVertices[i] = pathFromXtoY(v, i).size() - 1;
		}
		for (int i : deviationOfVertices) {
			result = (result > i) ? result : i;
		}
		return result;
	}

	// Tính bán kính cây
	public int radiusTree() {
		return eccentricityDFS(centersTree().get(0));
	}

	// Các tâm của cây
	public List<Integer> centersTree() {
		List<Integer> result = new ArrayList<Integer>(2);
		int min = 0;
		int[] deviationOfVertices = new int[maTranKe.length];
		for (int i = 0; i < maTranKe.length; i++) {
			deviationOfVertices[i] = eccentricityBFS(i);
		}
		for (int i : deviationOfVertices) {
			min = (min < i)?min:i;
		}
//		for (int i = 1; i < maTranKe.length; i++) {
//			min = (eccentricityDFS(min) < eccentricityDFS(i)) ? min : i;
//		}
		result.add(min);
		for (int i : deviationOfVertices) {
			if ((min == i) && (min != i)) {
				result.add(i);
			}
		}
//		for (int i = 0; i < maTranKe.length; i++) {
//			if ((eccentricityDFS(i) == eccentricityDFS(min)) && (i != min)) {
//				result.add(i);
//			}
//		}
		return result;
	}

	// Chuyển sang cây có gốc
	public RootTree switchToRootTree(int root) {
		int[][] matrix = new int[maTranKe.length][maTranKe.length];
		Queue<Integer> q = new LinkedList<Integer>();
		boolean[] visited = new boolean[maTranKe.length];
		visited[root] = true;
		q.add(root);
		while (!q.isEmpty()) {
			Integer v = q.poll();
			ArrayList<Integer> dinhKe = cacDinhKe(v);
			for (Integer i : dinhKe) {
				if (visited[i] == false) {
					matrix[v][i] = 1;
					q.add(i);
					visited[i] = true;
				}
			}
		}
		
		return new RootTree(matrix, root);
	}
}
package Graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;

public abstract class Graph {
	protected int[][] maTranKe;

	/**
	 * @param maTranKe
	 */
	public Graph(int len) {
		this.maTranKe = new int[len][len];
	}

	// thêm cạnh
	public abstract void addEdge(int u, int v);

	// xóa cạnh
	public abstract void removeEdge(int u, int v);

	// tính bậc của đỉnh v
	public abstract int deg(int v);

	// tính số cạnh
	public int numOfEdge() {
		int res = 0;
		for (int i = 0; i < maTranKe.length; i++) {
			res += this.deg(i);
		}
		return res / 2;
	}

	// in ma trận kề
	public void printMatrix() {
		for (int i = 0; i < this.maTranKe.length; i++) {
			for (int j = 0; j < maTranKe[i].length; j++) {
				System.out.print(maTranKe[i][j] + "\t");
			}
			System.out.println();
		}
	}

	// in danh sách cạnh
	public abstract void printEdge();

	// Kiểm tra một đồ thị có phải là đơn đồ thị hay không
	public boolean isSingleGraph() {
		for (int i = 0; i < maTranKe.length; i++) {
			for (int j = 0; j < maTranKe[i].length; j++) {
				if ((maTranKe[i][i] != 0) || ((maTranKe[i][j] > 1) && (i != j))) {
					return false;
				}
			}
		}
		return true;
	}

	// kiểm tra đồ thị có liên thông không
	public abstract boolean lienThong();

	// kiểm tra có đường đi từ đỉnh x tới đỉnh y hay không
	public boolean fromXtoY(int x, int y) {
		Stack<Integer> stack = new Stack<Integer>();
		boolean[] visited = new boolean[maTranKe.length];
		stack.push(x);
		visited[x] = true;
		while (!stack.isEmpty()) {
			int count = 0;
			int v = stack.peek();
			if (v == y) {
				System.out.println(stack);
				return true;
			}
			ArrayList<Integer> dinhKe = cacDinhKe(v);
			a: for (Integer i : dinhKe) {
				if (visited[i] == false) {
					v = i;
					stack.push(v);
					visited[i] = true;
					break a;
				} else {
					count++;
				}

			}
			if (count == dinhKe.size()) {
				int num = stack.pop();
			} else {
				count = 0;
			}

		}
		return false;
	}

	// danh sách các đỉnh kề với đỉnh v
	protected ArrayList<Integer> cacDinhKe(int v) {
		ArrayList<Integer> res = new ArrayList<Integer>();
		for (int i = 0; i < maTranKe.length; i++) {
			if (maTranKe[v][i] > 0) {
				res.add(i);
			}

		}
		return res;
	}

	// thuật toán duyệt đồ thị theo chiều rộng với đỉnh bắt đầu cho trước
	public void BFS(int s) {
		Queue<Integer> q = new LinkedList<Integer>();
		boolean[] visited = new boolean[maTranKe.length];
		visited[s] = true;
		q.add(s);
		while (!q.isEmpty()) {
			int v = q.poll();
			System.out.println(v);
			ArrayList<Integer> dinhKe = cacDinhKe(v);
			for (Integer i : dinhKe) {
				if (visited[i] == false) {
					q.add(i);
					visited[i] = true;
				}
			}
		}
	}

	// thuật toán duyệt đồ thị theo chiều sâu với đỉnh bắt đầu cho trước
	public void DFS(int s) {
		Stack<Integer> stack = new Stack<Integer>();
		boolean[] visited = new boolean[maTranKe.length];
		stack.push(s);
		visited[s] = true;
		String st = "";
		while (!stack.isEmpty()) {
			int count = 0;
			int v = stack.peek();
			ArrayList<Integer> dinhKe = cacDinhKe(v);
			a: for (Integer i : dinhKe) {
				if (visited[i] == false) {
					v = i;
					stack.push(v);
					visited[i] = true;
					break a;
				} else {
					count++;
				}

			}
			if (count == dinhKe.size()) {
				int num = stack.pop();
				st += num + "\n";

			} else {
				count = 0;
			}

		}
		StringBuilder out = new StringBuilder(st);
		System.out.println(out.reverse());
	}

	// đếm số lượng thành phần liên thông của đồ thị
	public int numConnected() {
		int res = 0;
		if (this.lienThong()) {
			return res = 1;
		} else {
			Queue<Integer> q = new LinkedList<Integer>();
			boolean[] visited = new boolean[maTranKe.length];
			for (int value = 0; value < visited.length; value++) {
				System.out.println(visited[value]);
				if (!visited[value]) {
					res += 1;
					visited[value] = true;
					q.add(value);
					while (!q.isEmpty()) {
						int v = q.poll();
						ArrayList<Integer> dinhKe = cacDinhKe(v);
						for (Integer i : dinhKe) {
							if (visited[i] == false) {
								q.add(i);
								visited[i] = true;
							}
						}
					}

				}
			}
		}
		return res;
	}

	/**
	 * @return the maTranKe
	 */
	public int[][] getMaTranKe() {
		return maTranKe;
	}

// 	// kiểm tra đồ thị có phải là con của một đồ thị khác hay không
// 	public boolean hasSubGraph(Graph graph) {
// 		int[][] matrix = graph.getMaTranKe();
// 		for (int i = 0; i < matrix.length; i++) {
// 			for (int j = 0; j < matrix[i].length; j++) {
// 				System.out.println(matrix[i][j] + ", " + maTranKe[i][j]);
// 				if ((matrix[i][j] > 0) && (maTranKe[i][j] < 0)) {
// 					return false;
// 				}
// 			}
// 		}
// 		return true;
// 	}

	public static void main(String[] args) {
		Graph gr = new UndirectedGraph(13);
		gr.addEdge(1, 0);
		gr.addEdge(0, 4);
		gr.addEdge(3, 0);
		gr.addEdge(1, 4);
		gr.addEdge(1, 3);
		gr.addEdge(1, 5);
		gr.addEdge(1, 2);
		gr.addEdge(2, 5);
		gr.addEdge(2, 7);
		gr.addEdge(3, 4);
		gr.addEdge(3, 4);
		gr.addEdge(3, 4);
		gr.addEdge(5, 3);
		gr.addEdge(7, 3);
		gr.addEdge(6, 7);
		gr.addEdge(8, 9);
		gr.addEdge(10, 9);
		gr.addEdge(10, 8);
		gr.addEdge(11, 12);
//		gr.printMatrix();
//		gr.BFS(0);
		System.out.println("----------------");
//		gr.DFS(0);
		// System.out.println(gr.fromXtoY(8, 10));
		// System.out.println("Co lien thong khong? " + gr.lienThong());
		// System.out.println(gr.cacDinhKe(4));
		// System.out.println(gr.numConnected());
		Graph gr1 = new UndirectedGraph(9);
		gr1.addEdge(1, 0);
		gr1.addEdge(0, 4);
		gr1.addEdge(3, 0);
		gr1.addEdge(1, 4);
		gr1.addEdge(1, 3);
		gr1.addEdge(1, 5);
		gr1.addEdge(1, 2);
		gr1.addEdge(2, 5);
		gr1.addEdge(2, 7);
		gr1.addEdge(3, 4);
		gr1.addEdge(3, 4);
		gr1.addEdge(3, 4);
		gr1.addEdge(5, 3);
		gr1.addEdge(7, 3);
		gr1.addEdge(6, 7);
		gr1.addEdge(7, 8);
//		gr1.printMatrix();                                                                                                             
		System.out.println(gr.hasSubGraph(gr1));
	}
}

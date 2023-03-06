package Graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
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

	public Graph(int[][] matrix) {
		this.maTranKe = matrix;
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

	// Kiểm tra đồ thị có cạnh x, y hay không
	public boolean hasEdge(int x, int y) {
		return this.maTranKe[x][y] > 0;
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

	// kiểm tra đồ thị có phải là khung của một đồ thị khác hay không
	public boolean khungDoThi(Graph graph) {
		int[][] matrix = graph.getMaTranKe();
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				if ((matrix[i][j] > maTranKe[i][j]) || (matrix.length != maTranKe.length)) {
					return false;
				}
			}
		}
		return true;
	}

	// Tìm chu trình Euler của đồ thị
	public abstract List<Integer> chuTrinhEuler();

	// Tìm đường đi Euler của đồ thị
	public abstract List<Integer> duongDiEuler();
}

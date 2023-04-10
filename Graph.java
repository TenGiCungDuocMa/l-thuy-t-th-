package Graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public abstract class Graph {
	protected int[][] maTranKe;

	/**
	 * @param maTranKe
	 */
	public Graph(int len) {
		this.maTranKe = new int[len][len];
	}

	public Graph(int[][] matrix) {
		this.maTranKe = new int[matrix.length][matrix.length];
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				maTranKe[i][j] = matrix[i][j];
			}
		}
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

	// Đường đi từ x tới y
	public List<Integer> pathFromXtoY(int x, int y) {
		List<Integer> result = new ArrayList<Integer>();
		if (fromXtoY(x, y)) {
			Stack<Integer> stack = new Stack<Integer>();
			boolean[] visited = new boolean[maTranKe.length];
			stack.push(x);
			visited[x] = true;
			while (!stack.isEmpty()) {
				int count = 0;
				Integer v = stack.peek();
				if (v == y) {
					// System.out.println(stack);
					result.addAll(stack);
					break;
				}
				ArrayList<Integer> dinhKe = cacDinhKe(v);
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

	// kiểm tra có đường đi từ đỉnh x tới đỉnh y hay không
	public boolean fromXtoY(int x, int y) {
		Stack<Integer> stack = new Stack<Integer>();
		boolean[] visited = new boolean[maTranKe.length];
		stack.push(x);
		visited[x] = true;
		while (!stack.isEmpty()) {
			int count = 0;
			Integer v = stack.peek();
			if (v == y) {
				// System.out.println(stack);
				return true;
			}
			ArrayList<Integer> dinhKe = cacDinhKe(v);
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
			Integer v = q.poll();
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
				Integer num = stack.pop();
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
						Integer v = q.poll();
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

	// Tìm cây khung của đồ thị sử dụng phương pháp duyệt theo BFS
	public Tree spanningTreeBFS() {
		int[][] adjacencyMatrix = new int[maTranKe.length][maTranKe.length];
		if (this.lienThong()) {
			Queue<Integer> queue = new LinkedList<Integer>();
			boolean[] visited = new boolean[maTranKe.length];
			visited[0] = true;
			queue.add(0);
			while ((!queue.isEmpty()) && (this.numOfEdge() != (this.maTranKe.length - 1))) {
				Integer v = queue.poll();
				ArrayList<Integer> dinhKe = cacDinhKe(v);
				for (Integer i : dinhKe) {
					if (visited[i] == false) {
						queue.add(i);
						adjacencyMatrix[v][i] = 1;
						visited[i] = true;
					}
				}
			}
		} else {
			throw new RuntimeException("Don't have spanning tree!");
		}
		return new Tree(adjacencyMatrix);
	}

	// Tìm cây khung của đồ thị sử dụng phương pháp duyệt theo DFS
	public Tree spanningTreeDFS() {
		int[][] adjacencyMatrix = new int[maTranKe.length][maTranKe.length];
		if (this.lienThong()) {
			Stack<Integer> stack = new Stack<Integer>();
			boolean[] visited = new boolean[maTranKe.length];
			stack.push(0);
			visited[0] = true;
			while ((this.numOfEdge() != (this.maTranKe.length - 1)) && !stack.isEmpty()) {
				int count = 0;
				Integer v = stack.peek();
				ArrayList<Integer> dinhKe = cacDinhKe(v);
				a: for (Integer i : dinhKe) {
					if (visited[i] == false) {
						adjacencyMatrix[v][i] = 1;
						v = i;
						stack.push(v);
						visited[i] = true;
						break a;
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
			throw new RuntimeException("Don't have spanning tree!");
		}
		return new Tree(adjacencyMatrix);
	}

	// Kiểm tra một đồ thị có phải là đồ thị Euler hay không
	public abstract boolean isEulerGraph();

	// Tìm chu trình Euler của đồ thị
	public abstract List<Integer> chuTrinhEuler(int start);

	// Tìm chu trình Hamilton của đồ thị (nếu có)
//	public abstract List<Integer> chutrinhHamilton();

	// Tìm đường đi Euler của đồ thị
	public abstract List<Integer> duongDiEuler();

	// Kiểm tra một đồ thị có phải là cây hay không
	public boolean isTree() {
		return (this.lienThong()) && (this.numOfEdge() == (this.maTranKe.length - 1));
	}

}
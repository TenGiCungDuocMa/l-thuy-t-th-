package Graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class UndirectedGraph extends Graph { // đồ thị vô hướng

	public UndirectedGraph(int len) {
		super(len);
	}

	public UndirectedGraph(int[][] maTranKe) {
		super(maTranKe);
	}

	@Override
	public void addEdge(int u, int v) {
		if ((u < 0 && u > maTranKe.length - 1) || (v < 0 && v > maTranKe.length - 1)) {
			throw new RuntimeException("Đỉnh không hợp lệ!");
		}
		this.maTranKe[u][v]++;
		this.maTranKe[v][u]++;

	}

	@Override
	public void removeEdge(int u, int v) {
		if ((u < 0 && u > maTranKe.length - 1) || (v < 0 && v > maTranKe.length - 1)) {
			throw new RuntimeException("Đỉnh không hợp lệ!");
		}
		if (this.maTranKe[u][v] > 0) {
			this.maTranKe[u][v]--;
			this.maTranKe[v][u]--;
		} else {
			throw new RuntimeException("Edge not found!");
		}
	}

	@Override
	public int deg(int v) {
		if (v < 0 && v > maTranKe.length - 1) {
			throw new RuntimeException("Đỉnh không hợp lệ!");
		}
		int res = 0;

		for (int i = 0; i < maTranKe.length; i++) {
			res += this.maTranKe[v][i];
		}
		return res;
	}

	public boolean coLuongPhan() {

		ArrayList<Integer> color1 = new ArrayList<Integer>();
		ArrayList<Integer> color2 = new ArrayList<Integer>();

		for (int i = 0; i < maTranKe.length; i++) {
			for (int j = 0; j < maTranKe[i].length; j++) {
				if (maTranKe[i][j] > 0) {
					if (color1.isEmpty() && color2.isEmpty()) {
						color1.add(i);
						color2.add(j);
					}
					if (color1.contains(i)) {
						color2.add(j);
					}
					if (color2.contains(i)) {
						color1.add(j);
					}
					if (color1.contains(j)) {
						color2.add(i);
					}
					if (color2.contains(j)) {
						color1.add(i);
					}
				}
			}
		}
		for (int i = 0; i < color1.size() - 1; i++) {
			if (maTranKe[color1.get(i)][color1.get(i + 1)] > 0) {
				return false;
			}
		}
		for (int i = 0; i < color2.size() - 1; i++) {
			if (maTranKe[color2.get(i)][color2.get(i + 1)] > 0) {
				return false;
			}
		}

		return true;
	}

	@Override
	public void printEdge() {
		String res = "";
		for (int i = 0; i < maTranKe.length; i++) {
			for (int j = 0; j <= i; j++) {
				if ((maTranKe[i][j] > 0) && (i != j)) {
					while (maTranKe[i][j] > 0) {
						res += "(" + j + "," + i + "), ";
						maTranKe[i][j]--;
					}
				}
				if ((maTranKe[i][j] > 0) && (i == j)) {
					res += "(" + j + "," + i + "), ";
				}

			}
		}
		System.out.println(res);
	}

	public boolean lienThong() {
		Queue<Integer> q = new LinkedList<Integer>();
		boolean[] visited = new boolean[maTranKe.length];
		visited[0] = true;
		q.add(0);
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
		for (int i = 0; i < visited.length; i++) {
			if (!visited[i]) {
				return false;
			}
		}
		return true;
	}

	@Override
	public List<Integer> chuTrinhEuler() {
		Graph H = new UndirectedGraph(this.getMaTranKe());
		List<Integer> C = new ArrayList<Integer>();
		Stack<Integer> stack = new Stack<Integer>();
		C.add(0);
		stack.add(0);
		while (!stack.isEmpty()) {
			int a = stack.pop();
			if (H.deg(a) > 0) {
				List<Integer> sub = new ArrayList<Integer>();
				sub.add(a);
				while (H.deg(a) > 0) {
					stack.push(a);
					b: for (int i = 0; i < H.maTranKe.length; i++) {
						if (H.hasEdge(a, i)) {
							sub.add(i);
							H.removeEdge(a, i);
							a = i;
							break b;
						}
					}
				}
				System.out.println(sub);
				c:for (int i = 0; i < C.size(); i++) {
					if (C.get(i).equals(a)) {
						C.remove(i);
						C.addAll(i, sub);
						break c;
					}
				}
			}
		}
		return C;
	}

	@Override
	public List<Integer> duongDiEuler() {
		
		return null;
	}

}

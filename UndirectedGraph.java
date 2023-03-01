package Graph;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class UndirectedGraph extends Graph { // đồ thị vô hướng

	public UndirectedGraph(int len) {
		super(len);
		// TODO Auto-generated constructor stub
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
		// System.out.println(Arrays.toString(visited));
		for (int i = 0; i < visited.length; i++) {
			if (!visited[i]) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		UndirectedGraph u = new UndirectedGraph(5);
		u.addEdge(0, 4);
		u.addEdge(1, 4);
		u.addEdge(2, 4);
		u.addEdge(3, 4);
		u.printMatrix();
		u.printEdge();
		boolean a = u.coLuongPhan();
		System.out.println(a);
		System.out.println("-----------------------------------------------");
		UndirectedGraph u1 = new UndirectedGraph(6);
		u1.addEdge(0, 1);
		u1.addEdge(0, 4);
		u1.addEdge(1, 2);
		u1.addEdge(2, 3);
		u1.addEdge(3, 0);
		u1.addEdge(5, 1);
		u1.addEdge(5, 2);
		u1.addEdge(4, 2);
		u1.printMatrix();
		u1.printEdge();
		boolean b = u1.coLuongPhan();
		System.out.println(b);
		System.out.println("-----------------------------------------------");
		UndirectedGraph u2 = new UndirectedGraph(5);
		u2.addEdge(0, 1);
		u2.addEdge(0, 3);
		u2.addEdge(0, 4);
		u2.addEdge(1, 2);
		u2.addEdge(2, 3);
		u2.addEdge(2, 4);
		u2.printMatrix();
		u2.printEdge();
		boolean c = u2.coLuongPhan();
		System.out.println(c);
	}
}

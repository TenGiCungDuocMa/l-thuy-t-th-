package Graph;

import java.util.List;

public class Digraph extends Graph { // đồ thị có hướng

	public Digraph(int len) {
		super(len);
		// TODO Auto-generated constructor stub
	}

	public Digraph(int[][] maTranKe) {
		super(maTranKe);
	}

	@Override
	public void addEdge(int u, int v) {
		if ((u < 0 && u > maTranKe.length - 1) || (v < 0 && v > maTranKe.length - 1)) {
			throw new RuntimeException("Đỉnh không hợp lệ!");
		}
		this.maTranKe[u][v]++;
	}

	@Override
	public void removeEdge(int u, int v) {
		// TODO Auto-generated method stub
		if ((u < 0 && u > maTranKe.length - 1) || (v < 0 && v > maTranKe.length - 1)) {
			throw new RuntimeException("Đỉnh không hợp lệ!");
		}
		if (this.maTranKe[u][v] > 0) {
			this.maTranKe[u][v]--;
		} else {
			throw new RuntimeException("Edge not found!");
		}

	}

	public int tongBacNgoai(int v) {
		if (v < 0 && v > maTranKe.length - 1) {
			throw new RuntimeException("Đỉnh không hợp lệ!");
		}
		int res = 0;
		for (int i = 0; i < maTranKe.length; i++) {
			res += this.maTranKe[v][i];
		}
		return res;
	}

	public int tongBacTrong(int v) {
		if (v < 0 && v > maTranKe.length - 1) {
			throw new RuntimeException("Đỉnh không hợp lệ!");
		}
		int res = 0;

		for (int i = 0; i < maTranKe.length; i++) {
			res += this.maTranKe[i][v];
		}
		return res;
	}

	@Override
	public int deg(int v) {
		if (v < 0 && v > maTranKe.length - 1) {
			throw new RuntimeException("Đỉnh không hợp lệ!");
		}
		int res = this.tongBacNgoai(v) + this.tongBacTrong(v);
		return res;
	}

	@Override
	public void printEdge() {
		String res = "";
		for (int i = 0; i < maTranKe.length; i++) {
			for (int j = 0; j < maTranKe[i].length; j++) {
				if ((maTranKe[i][j] > 0) && (i != j)) {
					while (maTranKe[i][j] > 0) {
						res += "(" + i + "," + j + "), ";
						maTranKe[i][j]--;
					}
				}
				if ((maTranKe[i][j] > 0) && (i == j)) {
					res += "(" + i + "," + j + "), ";
				}
			}
		}

		System.out.println(res);
	}

	@Override
	public boolean lienThong() {
		for (int i = 0; i < maTranKe.length; i++) {
			for (int j = 0; j < maTranKe[i].length; j++) {
				if (maTranKe[i][j] > 0) {
					maTranKe[j][i] = 1;
				}
			}
		}
		UndirectedGraph u = new UndirectedGraph(maTranKe.length);
		return u.lienThong();
	}

	@Override
	public List<Integer> chuTrinhEuler() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Integer> duongDiEuler() {
		// TODO Auto-generated method stub
		return null;
	}
}

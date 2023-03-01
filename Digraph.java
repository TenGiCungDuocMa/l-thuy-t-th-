package Graph;

public class Digraph extends Graph { // đồ thị có hướng

	public Digraph(int len) {
		super(len);
		// TODO Auto-generated constructor stub
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
				if(maTranKe[i][j] > 0) {
					maTranKe[j][i] = 1;
				}
			}
		}
		UndirectedGraph u = new UndirectedGraph(maTranKe.length);
		return u.lienThong();
	}
	public static void main(String[] args) {
		Digraph d = new Digraph(6);
		d.addEdge(0, 1);
		d.addEdge(1, 2);
		d.addEdge(0, 3);
		d.addEdge(3, 0);
		d.addEdge(4, 5);
		d.addEdge(5, 3);
		d.addEdge(5, 3);
		d.addEdge(5, 3);
		d.addEdge(5, 5);
//		d.printMatrix();
//		d.printEdge();
		d.BFS(0);
		d.DFS(0);
		System.out.println(d.lienThong());
	}

	
}

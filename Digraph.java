package Graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Digraph extends Graph { // đồ thị có hướng

	public Digraph(int len) {
		super(len);
		// TODO Auto-generated constructor stub
	}

	public Digraph(int[][] matrix) {
		super(matrix);
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

	// Kiểm tra một đồ thị có hướng có liên thông yếu không
	@Override
	public boolean lienThong() {
		int[][] result = new int[maTranKe.length][maTranKe.length];
		for (int i = 0; i < maTranKe.length; i++) {
			for (int j = 0; j < maTranKe[i].length; j++) {
				if (maTranKe[i][j] > 0) {
					result[i][j] = 1;
					result[j][i] = 1;
				}
			}
		}
		UndirectedGraph u = new UndirectedGraph(result);
		return u.lienThong();
	}

	@Override
	public List<Integer> chuTrinhEuler(int start) {
		Graph H = new Digraph(this.getMaTranKe());
		List<Integer> C = new ArrayList<Integer>();
		if (H.isEulerGraph()) {
			Stack<Integer> stack = new Stack<Integer>();
			stack.add(start);
			C.add(start);
			while (!stack.isEmpty()) {
				Integer a = stack.pop();
				if (H.deg(a) > 0) {
					List<Integer> sub = new ArrayList<Integer>();
					sub.add(a);
					while (H.deg(a) > 0) {
						stack.push(a);
						d: for (int i = 0; i < H.maTranKe.length; i++) {
							if (H.hasEdge(a, i)) {
								sub.add(i);
								H.removeEdge(a, i);
								a = i;
								break d;
							}
						}
					}
//					System.out.println(sub);
					int num = C.indexOf(a);
					C.remove(num);
					C.addAll(num, sub);
				}

			}
		} else {
			throw new RuntimeException("Không có chu trình Euler!");
		}
		return C;

	}

	@Override
	public List<Integer> duongDiEuler() {
		List<Integer> result = new ArrayList<Integer>();
		if (nuaEulerKhong()) {
			List<Integer> dinhKhongCB = cacDinhKhongCanBang();
			Integer x = null;
			Integer y = null;
			for (Integer i : dinhKhongCB) {
				if (tongBacNgoai(i) == (tongBacTrong(i) + 1)) {
					 x = i;
				} 
				if (tongBacNgoai(i) == (tongBacTrong(i) - 1)) {
					 y = i;
				} 
			}
			Digraph H = new Digraph(this.maTranKe);
			H.addEdge(y, x);
			List<Integer> C = H.chuTrinhEuler(x);
//			System.out.println(C);
			for (int i = 0; i < C.size() - 1; i++) {
				if ((C.get(i) == x && C.get(i + 1) == y) || (C.get(i) == y && C.get(i + 1) == x)) {
					List<Integer> c1 = C.subList(0, i + 1);
					List<Integer> c2 = C.subList(i + 1, C.size() - 1);
					result.addAll(c2);
					result.addAll(c1);
					break;
				}
			}
		} else {
			throw new RuntimeException("Don't have Euler's path");
		}
		return result;
	}

	public boolean nuaEulerKhong() {
		if ((!this.lienThong()) || (this.maTranKe.length < 2) || (cacDinhKhongCanBang().size() != 2)) {
			return false;
		}
		
		Integer x = null;
		Integer y = null;
		for (Integer i : cacDinhKhongCanBang()) {
			if (tongBacNgoai(i) == (tongBacTrong(i) + 1)) {
				 x = i;
			} 
			if (tongBacNgoai(i) == (tongBacTrong(i) - 1)) {
				 y = i;
			} 
		}
		if ( x.equals(null) || y.equals(null)) {
			return false;
		}
		
		return true;
	}

	public List<Integer> cacDinhKhongCanBang() {
		List<Integer> dinhKhongCanBang = new ArrayList<Integer>();
		for (int i = 0; i < maTranKe.length; i++) {
			if (tongBacNgoai(i) != tongBacTrong(i)) {
				dinhKhongCanBang.add(i);
			}
		}
		return dinhKhongCanBang;
	}

	@Override
	public boolean isEulerGraph() {
		if ((this.lienThong() == false) || (this.maTranKe.length < 2)) {
			return false;
		}
		for (int i = 0; i < maTranKe.length; i++) {
			if (this.tongBacNgoai(i) != this.tongBacTrong(i)) {
				return false;
			}
		}
		return true;
	}

}
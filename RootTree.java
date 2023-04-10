package Graph;

public class RootTree extends Digraph {
	private int root;

	public RootTree(int len, int root) {
		super(len);
		this.root = root;
		// TODO Auto-generated constructor stub
	}

	public RootTree(int[][] matrix, int root) {
		super(matrix);
		this.root = root;
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the root
	 */
	public int getRoot() {
		return root;
	}

	/**
	 * @param root the root to set
	 */
	public void setRoot(int root) {
		this.root = root;
	}

	@Override
	public void addEdge(int u, int v) {
		if (!this.fromXtoY(u, v)) {
			if ((u < 0 && u > maTranKe.length - 1) || (v < 0 && v > maTranKe.length - 1)) {
				throw new RuntimeException("Đỉnh không hợp lệ!");
			}
			this.maTranKe[u][v]++;
		} else {
			throw new RuntimeException("Can't create edge!!");
		}
	}

	public boolean isCompleteTree() {
		return (this.numOfEdge() == (this.maTranKe.length - 1));
	}

	// Chuyển sang cây tự do
	public Tree switchToTree() {
		return new Tree(this.maTranKe);
	}
}
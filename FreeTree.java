package Graph2;

public class FreeTree extends UnGraph {

	public FreeTree(int n) {
		super(n);
	}

	public FreeTree(Double[][] m) {
		super(m);
	}

	@Override
	public void addEdge(int u, int v, Double weight) {
		if (!this.fromXtoY(u, v)) {
			if ((u < 0 && u > weightMatrix.length - 1) || (v < 0 && v > weightMatrix.length - 1)) {
				throw new RuntimeException("Đỉnh không hợp lệ!");
			}
			weightMatrix[u][v] = weight;
			weightMatrix[v][u] = weight;
		} else {
			throw new RuntimeException("Can't create edge!");
		}
	}

	@Override
	public void destroyEdge(int u, int v) {
		if (this.fromXtoY(u, v)) {
			if ((u < 0 && u > weightMatrix.length - 1) || (v < 0 && v > weightMatrix.length - 1)) {
				throw new RuntimeException("Đỉnh không hợp lệ!");
			}
			weightMatrix[u][v] = Double.POSITIVE_INFINITY;
			weightMatrix[v][u] = Double.POSITIVE_INFINITY;
		}
	}
}
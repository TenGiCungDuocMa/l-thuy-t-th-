package Graph2;

public class DiGraph extends Graph {

	public DiGraph(int n) {
		super(n);
	}

	public DiGraph(Double[][] m) {
		super(m);
	}

	@Override
	public void addEdge(int u, int v, Double weight) {
		if ((u < 0 && u > weightMatrix.length - 1) || (v < 0 && v > weightMatrix.length - 1)) {
			throw new RuntimeException("Đỉnh không hợp lệ!");
		}
		weightMatrix[u][v] = weight;

	}

	@Override
	public void destroyEdge(int u, int v) {
		if ((u < 0 && u > weightMatrix.length - 1) || (v < 0 && v > weightMatrix.length - 1)) {
			throw new RuntimeException("Đỉnh không hợp lệ!");
		}
		if (weightMatrix[u][v] != Double.POSITIVE_INFINITY) {
			weightMatrix[u][v] = Double.POSITIVE_INFINITY;
		} else {
			throw new RuntimeException("Edge not found!");
		}

	}

	@Override
	public int deg(int v) {
		if (v < 0 && v > weightMatrix.length - 1) {
			throw new RuntimeException("Đỉnh không hợp lệ!");
		}
		return degIn(v) + degOut(v);
	}

	public int degOut(int v) {
		int res = 0;
		if (v < 0 && v > weightMatrix.length - 1) {
			throw new RuntimeException("Đỉnh không hợp lệ!");
		}

		for (int i = 0; i < weightMatrix.length; i++) {
			if (weightMatrix[v][i] != Double.POSITIVE_INFINITY) {
				res++;
			}
		}
		return res;
	}

	public int degIn(int v) {
		int res = 0;
		if (v < 0 && v > weightMatrix.length - 1) {
			throw new RuntimeException("Đỉnh không hợp lệ!");
		}

		for (int i = 0; i < weightMatrix.length; i++) {
			if (weightMatrix[i][v] != Double.POSITIVE_INFINITY) {
				res++;
			}
		}
		return res;
	}

	@Override
	public Double totalWeight() {
		Double res = 0.0;
		for (int i = 0; i < weightMatrix.length; i++) {
			for (int j = 0; j < weightMatrix[i].length; j++) {
				if (weightMatrix[i][j] != Double.POSITIVE_INFINITY) {
					res += weightMatrix[i][j];
				}
			}
		}
		return res;
	}

	@Override
	public boolean connected() {
		Double[][] result = new Double[weightMatrix.length][weightMatrix.length];
		for (int i = 0; i < weightMatrix.length; i++) {
			for (int j = 0; j < weightMatrix[i].length; j++) {
				if (weightMatrix[i][j] !=Double.POSITIVE_INFINITY) {
					result[i][j] = weightMatrix[i][j];
					result[j][i] = weightMatrix[j][i];
				} else {
					result[i][j] = Double.POSITIVE_INFINITY;
					result[j][i] = Double.POSITIVE_INFINITY;
				}
			}
		}
		UnGraph u = new UnGraph(result);
		return u.connected();
	}

	@Override
	public void printEdge() {
		String res = "";
		for (int i = 0; i < weightMatrix.length; i++) {
			for (int j = 0; j < weightMatrix[i].length; j++) {
				if ((weightMatrix[i][j] != Double.POSITIVE_INFINITY)) {
					res += "( " + i + ", " + j + ", " + weightMatrix[i][j] + " ), ";
				}
			}
		}
		System.out.println(res);

	}

	@Override
	public FreeTree KRUSKAL() {
		// TODO Auto-generated method stub
		return null;
	}

}
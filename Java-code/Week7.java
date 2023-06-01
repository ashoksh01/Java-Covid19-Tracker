package CW_Task1;

public class Week7 {

	public static int minEditDistance(int[] a, int[] b) {
		int x = a.length;
		int y = b.length;
		int[][] matrix = new int[x + 1][y + 1];

		for (int i = 0; i < x + 1; i++) {
			for (int j = 0; j < y + 1; j++) {

				if (i == 0 && j == 0) {
					matrix[i][j] = 0;
				}

				else if (i == 0) {
					matrix[i][j] = matrix[i][j - 1] + 1;
				}

				else if (j == 0) {
					matrix[i][j] = matrix[i - 1][j] + 1;
				}

				else {
					if (a[i - 1] == b[j - 1]) {
						matrix[i][j] = matrix[i - 1][j - 1];
					}

					else {
						matrix[i][j] = Math.min(matrix[i][j - 1], Math.min(matrix[i - 1][j - 1], matrix[i - 1][j])) + 1;
					}
				}
			}
		}
		return matrix[x][y];
	}

	public static void main(String[] args) {
		int[] list1 = { 1, 2, 3, 5 };
		int[] list2 = { 1, 4, 3 };

		System.out.println(minEditDistance(list1, list2));
	}
}
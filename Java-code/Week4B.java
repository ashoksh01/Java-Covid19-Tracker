package CW_Task1;

import java.util.ArrayList;

public class Week4B {
	int vertices;
	int[][] adjacency_matrix;

	Week4B(int v) {
		vertices = v;
		adjacency_matrix = new int[vertices][vertices];
	}

	public void addEdge(int source, int destination) {
		adjacency_matrix[source][destination] = 1;
	}

	public int solution(String restaurants) {
		int[] indegree = new int[vertices];
		for (int i = 0; i < vertices; i++) {
			for (int j = 0; j < vertices; j++) {
				if (adjacency_matrix[i][j] != 0) {
					indegree[j]++;
				}
			}
		}
		int n = restaurants.length();
		char[] restaurant = restaurants.toCharArray();
		int[][] map = new int[n][26];
		Week4_A Q = new Week4_A();
		for (int i = 0; i < vertices; i++) {
			if (indegree[i] == 0) {
				map[i][restaurant[i] - 'a'] = 1;
				Q.enqueue(i);
			}
		}
		int res = 0;
		while (!Q.isEmpty()) {
			int node = Q.dequeue();
			int max = getMax(map[node]);
			res = Math.max(res, max);
			for (int j = 0; j < vertices; j++) {
				if (adjacency_matrix[node][j] != 0) {
					for (int i = 0; i < 26; i++) {
						map[j][i] = Math.max(map[j][i], map[node][i] + (restaurant[j] - 'a' == i ? 1 : 0));
					}
					indegree[j]--;
					if (indegree[j] == 0) {
						Q.enqueue(j);
					}
				}
			}
		}
		return res;
	}

	public int getMax(int[] list) {
		int num = list[0];
		for (int i : list) {
			num = Math.max(i, num);
		}
		return num;
	}

	public static void main(String[] args) {
		Week4B q = new Week4B(5);
		q.addEdge(0, 1);
		q.addEdge(0, 2);
		q.addEdge(2, 3);
		q.addEdge(3, 4);
		System.out.println(q.solution("abaca"));

	}
}

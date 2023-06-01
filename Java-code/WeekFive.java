package CW1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class WeekFive {
	HashMap<Integer, Integer> map;

	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

	public TreeNode[] splitBST(TreeNode root, int V) {
		TreeNode dummySm = new TreeNode(0);
		TreeNode curSm = dummySm;
		TreeNode dummyLg = new TreeNode(0);
		TreeNode curLg = dummyLg;
		while (root != null) {
			if (root.val <= V) {
				curSm.right = root;
				curSm = root;
				root = root.right;
				curSm.right = null;
			} else {
				curLg.left = root;
				curLg = root;
				root = root.left;
				curLg.left = null;
			}
		}
		return new TreeNode[] { dummySm.right, dummyLg.left };
	}

	public void binaryTree() {
		TreeNode root = new TreeNode(40);
		root.left = new TreeNode(20);
		root.right = new TreeNode(50);
		root.left.left = new TreeNode(10);
		root.left.right = new TreeNode(35);
		root.right.right = new TreeNode(60);
		root.left.right.left = new TreeNode(25);
		root.right.right.left = new TreeNode(55);
		TreeNode arr[] = splitBST(root, 35);
		int[][] outputarr = printLevelOrder(arr[0]);
		printEdge(outputarr);
		int[][] edges = new int[outputarr.length][outputarr[0].length];
		for (int i = 0; i < outputarr.length; i++) {
			// i=rows
			for (int j = 0; j < outputarr[0].length; j++) {
				if (outputarr[i][j] != 0) {
					edges[i][j] = map.get(outputarr[i][j]);
				}
			}
		}
		printEdge(edges);
		int[] dist = new Solutions().sumOfDistancesInTree(map.size(), edges);
		System.out.println(Arrays.toString(dist));
	}

	public void printEdge(int[][] arr) {
		int rowslength = arr.length;
		int collength = arr[0].length;
		System.out.println("");
		System.out.println("printing edges");
		for (int i = 0; i < rowslength; i++) {
			for (int j = 0; j < collength; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println("");
		}
	}

	int edges[][] = new int[10][2];
	int crawl_count = 0;

	public int[][] printLevelOrder(TreeNode root) {
		int lngth = 0;
		map = new HashMap<>();
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		queue.add(root);
		while (!queue.isEmpty()) {
			TreeNode tempNode = queue.poll();
			map.put(tempNode.val, lngth);
			System.out.print(tempNode.val + " ");
			lngth++;
			/* Enqueue left child */
			if (tempNode.left != null) {
				queue.add(tempNode.left);
				edges[crawl_count][0] = tempNode.val;
				edges[crawl_count][1] = tempNode.left.val;
				crawl_count++;
			}
			/* Enqueue right child */
			if (tempNode.right != null) {
				queue.add(tempNode.right);
				edges[crawl_count][0] = tempNode.val;
				edges[crawl_count][1] = tempNode.right.val;
				crawl_count++;
			}
		}
		return edges;
	}

	public static void main(String[] args) {
		WeekFive sp = new WeekFive();
		sp.binaryTree();
	}
}

package CW_Task1;

import java.util.Comparator;
import java.util.PriorityQueue;

import CW_Task1.Node;

class Node {

	int val;
	Node next;

	Node(int val) {
		this.val = val;
		this.next = null;

	}
}

public class Week1 {

	public static void mergeLinkedList(Node lists[]) {

		PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {

			public int compare(Node a, Node b) {

				return b.val - a.val;
			}

		});

		Node p, head = new Node(0);
		p = head;

		for (int i = 0; i < lists.length; i++) {

			if (lists[i] != null) {

				pq.add(lists[i]);

			}
		}

		while (!pq.isEmpty()) {
			p.next = pq.poll();
			p = p.next;

			if (p.next != null)
				pq.add(p.next);
		}

		p = head.next;

		while (p.next != null) {
			System.out.print(p.val + " ");
			p = p.next;
		}

		System.out.print(p.val);
	}

	public static void main(String[] args) {
		int N = 2;

		Node a[] = new Node[N];

		a[0] = new Node(100);
		a[0].next = new Node(400);
		a[0].next.next = new Node(-1000);
		a[0].next.next.next = new Node(-500);

		a[1] = new Node(-300);
		a[1].next = new Node(2000);
		a[1].next.next = new Node(-500);

		mergeLinkedList(a);
	}
}
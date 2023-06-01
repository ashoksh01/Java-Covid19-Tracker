package CW_Task1;

import java.util.Arrays;
import java.util.Collections;
import java.util.ArrayList;
import java.util.Arrays;

public class Week6 {

	public static int[] mergeSort(int[] list) {
		int l_index = 0;
		int r_index = list.length - 1;
		if (l_index < r_index) {
			int m_index = (r_index) / 2;
			int[] a = Arrays.copyOfRange(list, l_index, m_index + 1);
			int[] b = Arrays.copyOfRange(list, m_index + 1, r_index + 1);

			System.out.println(Arrays.toString(a));
			System.out.println(Arrays.toString(b));

			mergeSort(a);
			mergeSort(b);
			int i;
			int j;
			int k;
			i = j = k = 0;

			while (i < a.length && j < b.length) {
				if (a[i] > b[j]) {
					list[k] = a[i];
					i++;
				} else {
					list[k] = b[j];
					j++;
				}
				k++;
			}

			while (i < a.length) {
				list[k] = a[i];
				i++;
				k++;
			}
			while (j < b.length) {
				list[k] = b[j];
				j++;
				k++;

			}

		}
		return list;

	}

	public static void main(String[] args) {
		int d = 3;
		int[] list = { 7, 10, 4, 3, 20, 15 };
		int[] merged = mergeSort(list);
		System.out.println(merged[d - 1]);
	}
}
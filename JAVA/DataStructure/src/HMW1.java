
public class HMW1 {

	public static void main(String[] args) {
		int[] noa = { 2, 1, 3, 5 };
		int[] res;
		res = minMax(noa, noa.length);
		for (int i = 0; i < res.length; i++) {
			System.out.println(res[i]);
		}
	}

	static int[] minMax(int[] a, int size) {
		// array to return
		int[] arrReturn = {4,5};
		int min, max;
		// if size==0 max = min
		if (size == 0) {
			return arrReturn;
		}
			if (a[size - 1] < a[size - 2] && a[size-1]<arrReturn[0]) {
				min = a[size - 1];
				arrReturn[0] = min;
				return minMax(a, size - 1);
			}
			if (a[size - 1] > a[size - 2]&&a[size-1]>arrReturn[1]) {
				max = a[size - 1];
				arrReturn[1] = max;
				return minMax(a, size - 1);
			}
			return arrReturn;
	}

}

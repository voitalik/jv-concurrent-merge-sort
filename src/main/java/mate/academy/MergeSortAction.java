package mate.academy;

import java.util.concurrent.RecursiveAction;

public class MergeSortAction extends RecursiveAction {

    private final int[] array;

    public MergeSortAction(int[] array) {
        this.array = array;
    }

    /**
     * The main computation performed by this task.
     */
    @Override
    protected void compute() {
        // base case
        if (array.length > 1) {
            int mid = array.length / 2;
            int[] left = new int[mid];
            int[] right = new int[array.length - mid];

            System.arraycopy(array, 0, left, 0, mid);
            System.arraycopy(array, mid, right, 0, array.length - mid);

            invokeAll(new MergeSortAction(left), new MergeSortAction(right));
            merge(array, left, right);
        }
    }

    private void merge(int[] array, int[] left, int[] right) {
        int leftLength = left.length;
        int rightLength = right.length;
        int i = 0;
        int j = 0;
        int k = 0;

        while (i < leftLength && j < rightLength) {
            if (left[i] <= right[j]) {
                array[k++] = left[i++];
            } else {
                array[k++] = right[j++];
            }
        }
        while (i < leftLength) {
            array[k++] = left[i++];
        }
        while (j < rightLength) {
            array[k++] = right[j++];
        }
    }
}

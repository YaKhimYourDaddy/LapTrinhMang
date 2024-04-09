public class Main {
    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5};

        // Passing the array to a function to modify it
        modifyArray(array);

        // Printing the modified array
        for (int num : array) {
            System.out.print(num + " ");
        }
    }

    // Function to modify the elements of the array
    public static void modifyArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] *= 2; // Doubling each element
        }
    }
}

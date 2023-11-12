public class DrawATriangle {
    public static void main(String[] args) {
        int SIZE = 5;
        int col = 0;
        int row = 0;

        while (row < SIZE) {
            while (col <= row) {
                System.out.print('*');
                col = col + 1;
            }
            System.out.println();

            col = 0;
            row = row + 1;
        }
    }
}
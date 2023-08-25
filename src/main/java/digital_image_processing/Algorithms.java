package digital_image_processing;

import java.io.FileWriter;
import java.io.IOException;

public class Algorithms {
    public void lbp(int[][] arr,int k) throws IOException {
        // getting rows and column
        int rows = arr.length;
        int cols = arr[0].length;
        // make lbp array
        int[][] lbpArr = new int[rows - 2][cols - 2];
        int m, n;
        // m for row n for column
        for (m = 0; m < rows - 2; m++) {
            for (n = 0; n < cols - 2; n++) {
                // final array save
                lbpArr[m][n] = tempLBP(m, n, arr);
            }
        }
        //Image ig = new Image();
        oneDarray(lbpArr,k);
        //ig.binaryImage(lbpArr,k,fileN);
    }

    // for collecting lbp value
    public int tempLBP(int m, int n, int[][] arr) {
        int x, sum;
        x = n;
        // temp 3x3 matrix
        int[][] temp = new int[3][3];
        for (int a = 0; a < temp.length; a++) {
            for (int b = 0; b < temp[0].length; b++) {
                temp[a][b] = arr[m][n];
                n++;
                if (n == (x + temp[0].length)) {
                    n = x;
                }
            }
            m++;
        }

        // for 3x3 array
        int[][] btemp = new int[3][3];
        for (int k = 0; k < temp.length; k++) {
            for (int l = 0; l < temp[0].length; l++) {
                if (temp[k][l] >= temp[1][1]) {
                    btemp[k][l] = 1;
                } else {
                    btemp[k][l] = 0;
                }
            }
        }
        sum = btemp[0][0] + btemp[0][1] * 2 + btemp[0][2] * 4 + btemp[1][2] * 8 + btemp[2][2] * 16 + btemp[2][1] * 32
                + btemp[2][0] * 64 + btemp[1][0] * 128;
        return sum;
    }

    // two D to one D array conversion
    public void oneDarray(int[][] arr,int filek) throws IOException {
        int row = arr.length;
        int column = arr[0].length;
        // covert 2d array to 1d array for minimizing effort
        int[] array1D = new int[row * column];
        int k = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                array1D[k] = arr[i][j];
                k++;
            }
        }
        searchIng(array1D,filek);
    }

    // searching and saving in histogram txt
    public void searchIng(int[] arr,int k) throws IOException {
        String histoPath = "data/testHisto.txt";
        int j, max = 255;

        for (int i = 0; i <= max; i++) {
            int count = 0;
            for (j = 1; j < arr.length; j++) {
                if (arr[j] == i) {
                    count++;
                }
            }
            // System.out.println(i+" "+count+" times");
            // saving in a file
            try (FileWriter fileWriter = new FileWriter(histoPath,true)) {
                if(i==0){
                    fileWriter.write(k+ " ");
                }else{
                    if(count!=0){
                        fileWriter.write(i + ":" + count + " ");
                    }
                }
                if(i==255){
                    fileWriter.write("\n");
                }
            } catch (IOException e) {
                System.out.println("Invalid Path");
            }
        }
    }
}


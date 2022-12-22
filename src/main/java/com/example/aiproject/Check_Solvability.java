package com.example.aiproject;

public class Check_Solvability {

/* To check if a n-puzzle is solvalbe, we need to verify if the number of inversions in the puzzle
is even.
 */

    public static int inv_count(int [] arr_mat){
        int count = 0;
        for (int i=0; i< arr_mat.length; i++){
            for(int j=i+1; j<arr_mat.length; j++){
                if (arr_mat[i] > 0 && arr_mat[j]>0 && arr_mat[i]>arr_mat[j]){
                    count++;
                }
            }
        }
        return count;
    }

// Take our mat as param and convert it to array to use the count method
    public static boolean Solvable (int [][] mat){
        int arr_mat[];
        arr_mat = new int[mat.length* mat.length];
        boolean isSolvable;
        int k=0;
        for (int i=0; i<mat.length; i++){
            for (int j=0; j< mat.length; j++){
                arr_mat[k++] = mat[i][j];
            }
        }

        int count = inv_count(arr_mat);

        if (count%2 == 0){
            isSolvable = true;
        }else {
            isSolvable = false;
        }
        return isSolvable;
    }
}

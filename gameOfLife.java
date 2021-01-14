package gameoflife;

import java.util.Scanner;

public class GameOfLIfe {
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
    // dimensions of field
        System.out.print("Lenght of x axis: ");
        int xAxis = sc.nextInt();   // can be changed to exact values
        System.out.print("Height of y axis: ");
        int yAxis = sc.nextInt();   // can be changed to exact values
        if (xAxis < 2 || yAxis < 2) {
            System.out.println("Minimal length of axis is 2");
            return;
        }
        System.out.println("");
    // max iteration count
        int zAxis = 200;
        
        // make empty prime array
        char[][] field = new char[yAxis][xAxis];
        
        // fill prime array with random values
        for (int y = 0; y < yAxis; y++) {
            for (int x = 0; x < xAxis; x++) {
                double temp = Math.random();
                if (temp < 0.24) {
                    field[y][x] = 'X';
                } else {
                    field[y][x] = '.';
                }                
            }
        }
        
        // make empty array for memory
        char[][][] memory = new char[zAxis][yAxis][xAxis];
        
        // insert prime array in to memory
        memory[0] = field;        
        
        // states change as soon as two identical arrays are found
        boolean isEqual = false;
        int firstEqual = 0;
        int lastEqual = 0;
        
    // main
        for (int z = 1; z <= zAxis; z++) {
            if (z == zAxis) {
                lastEqual = zAxis - 1;
                break;
            }
            
            for (int y = 0; y < yAxis; y++) {
                for (int x = 0; x < xAxis; x++) {
                    
                    // add counter of how many neighbours has item
                    int countNeigh = 0;
                    if (x > 0) {
                        if (memory[z - 1][y][x - 1] == 'X') countNeigh++;
                    }
                    if (x < xAxis - 1) {
                        if (memory[z - 1][y][x + 1] == 'X') countNeigh++;
                    }                     
                    if (y > 0) {
                        if (x > 0) {
                            if (memory[z - 1][y - 1][x - 1] == 'X') countNeigh++;
                        }
                        if (x < xAxis - 1) {
                           if (memory[z - 1][y - 1][x + 1] == 'X') countNeigh++; 
                        } 
                        if (memory[z - 1][y - 1][x] == 'X') countNeigh++;                        
                    }
                    if (y < yAxis - 1) {
                        if (x > 0) {
                            if (memory[z - 1][y + 1][x - 1] == 'X') countNeigh++;
                        }   
                        if (x < xAxis - 1) {
                            if (memory[z - 1][y + 1][x + 1] == 'X') countNeigh++;
                        }
                        if (memory[z - 1][y + 1][x] == 'X') countNeigh++;
                    }
                    
                    // changes characters depending on status
                    if (memory[z - 1][y][x] == 'X') {
                        if(countNeigh == 2 || countNeigh == 3) {
                            memory[z][y][x] = 'X';
                        } else {
                            memory[z][y][x] = '.';
                        }
                    }
                    if (memory[z - 1][y][x] == '.') {
                        if(countNeigh == 3) {
                            memory[z][y][x] = 'X';
                        } else {
                            memory[z][y][x] = '.';
                        }
                    }
                }
            }
            
            // checks if there are no identical arrays by comparing last generated array with every previously made array
            for (int zComp = z - 1; zComp >= 0; zComp--) {
                int check = 0;
                
                for (int y = 0; y < yAxis; y++) {
                    for (int x = 0; x < xAxis; x++) {
                        if (memory[zComp][y][x] == memory[z][y][x]) check++;
                    }
                }
                if (check == yAxis * xAxis) {
                    isEqual = true;
                    firstEqual = zComp;
                    lastEqual = z;
                    break;
                }
            }
            
            // breaks iterations
            if (isEqual) {
                break;
            }
            
        }
        
    // print
        for (int z = 0; z <= lastEqual; z++) {
            // if static does not print last copy
            if (isEqual && z == lastEqual && lastEqual - firstEqual == 1) break;

            // adjusts where to put name of iteration depending on xAxis
            for (int s = 0; s <= xAxis; s++) {
                System.out.print(" ");
            }
            System.out.print("Iteration nr: " + (z + 1));
            System.out.println("");
            
            for (int y = 0; y < yAxis; y++) {
                for (int x = 0; x < xAxis; x++) {
                    System.out.print(memory[z][y][x]);
                }
                System.out.println("");
            }
        }
        
        System.out.println("");
        System.out.println("");
        
        // rearranges chars
        for (int z = firstEqual; z <= lastEqual; z++) {
            for (int y = 0; y < yAxis; y++) {
                for (int x = 0; x < xAxis; x++) {
                    if (memory[z][y][x] == 'X') memory[z][y][x] = '+';
                    if (memory[z][y][x] == '.') memory[z][y][x] = '-';
                }
            }
        }
        
    // extra print
        if (isEqual) {
            
            // responsive design depending on field dimensions
            int xRMax = 5;
            int yRMax = 5;
            if (xAxis > 5) xRMax = 4;
            if (xAxis > 10) xRMax = 3;
            if (xAxis > 50) xRMax = 2;
            if (xAxis > 80) xRMax = 1;
            if (yAxis > 5) yRMax = 4;
            if (yAxis > 10) yRMax = 3;
            if (yAxis > 15) yRMax = 2;
            if (yAxis > 25) yRMax = 1;
            
        // if static prints last iteration in bigger size
            if (lastEqual - firstEqual == 1) {             
                for (int y = 0; y < yAxis; y++) {
                    for (int yR = 0; yR < yRMax; yR++) {
                        for (int x = 0; x < xAxis; x++) {
                            for (int xR = 0; xR < xRMax; xR++) {
                                System.out.print(memory[firstEqual][y][x]);
                            }
                        }
                        System.out.println("");
                    }
                }
                
                System.out.println("");
                System.out.println("Becomes static at iteration " + (firstEqual + 1));
                System.out.println("");
                
        //if loop prints loop twice in bigger size
            } else {
                for (int zR = 0; zR < 2; zR++) {
                    for (int z = firstEqual; z < lastEqual; z++) {
                        for (int y = 0; y < yAxis; y++) {
                            if (yRMax > 3) yRMax = 3;
                            if (yRMax == 1) yRMax = 2;
                            if (xAxis > 80 && yRMax > 2) yRMax -= 1;
                            for (int yR = 0; yR < yRMax - 1; yR++) {
                                for (int x = 0; x < xAxis; x++) {
                                    if (xAxis > 80 && xRMax > 1) xRMax -= 1;
                                    for (int xR = 0; xR < xRMax; xR++) {
                                        System.out.print(memory[z][y][x]);
                                    }
                                }
                                System.out.println("");
                            }
                        }
                        System.out.println("");
                    }
                }
                
                System.out.println("");
                System.out.println("Starts looping with pattern of " + (lastEqual - firstEqual) + " at interation nr " + (firstEqual + 1));
                System.out.println("");
            }
        
        // if still no loop or static after max iterations
        } else {
            System.out.println("");
            System.out.println("Could not find any loops with " + zAxis + " iterations");
            System.out.println("");
        }
       
        
        System.out.println("END");


        
    }
}
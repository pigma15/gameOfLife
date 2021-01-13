package gameoflife;
public class GameOfLIfe {
    
    public static void main(String[] args) {
        // dimensions of field
        int yAxis = 12;
        int xAxis = 39;
        // max iteration count
        int zAxis = 160;
        
        // make empty prime array
        char[][] field = new char[yAxis][xAxis];
        
        // fill prime array with random values
        for (int y = 0; y < yAxis; y++) {
            for (int x = 0; x < xAxis; x++) {
                double temp = Math.random();
                if (temp < 0.25) {
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
        
        // main
        for (int z = 1; z < zAxis; z++) {
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
        }
        
        // print
        for (int z = 0; z < zAxis; z++) {
            System.out.println("                      Iteration nr: " + (z + 1));
            for (int y = 0; y < yAxis; y++) {
                for (int x = 0; x < xAxis; x++) {
                    System.out.print(memory[z][y][x]);
                }
                System.out.println("");
            }
        }
        System.out.println("END");
        
        // finding loop
        int firstEqual = 0;
        int secondEqual = 0;
        for (int z = 0; z <= zAxis; z++) {
            
            // becomes true when loop is found
            boolean equal = false;
            if (z == 0) {
                System.out.println("-------------------------");
            }
            if (z == zAxis) {
                System.out.println("Could not find any loops");
            }
            for (int check = z + 1; check < zAxis; check++) {
                
                // checks for each character to match in arrays
                int checkCount = 0;
                    for (int y = 0; y < yAxis; y++) {
                        for (int x = 0; x < xAxis; x++) {
                            if (memory[z][y][x] == memory[check][y][x]) {
                                checkCount++;
                            }
                        }
                    }
                    if (checkCount == yAxis * xAxis) {   
                        firstEqual = z;
                        if (check - z == 1) {
                            System.out.println("Becomes static at iteration nr." + z);
                            secondEqual = z;
                        } else {
                            System.out.println("Loop of " + (check - 1 - z) + " starting at iteration " + (z));
                            secondEqual = check - 1;
                        }                    
                        equal = true;
                        if (equal) {
                            break;
                        }
                    }
            }
            if (equal) {
                break;
            }
        }
        System.out.println("");

        // end of rendering
        for (int z = firstEqual; z <= secondEqual; z++) {
            for (int y = 0; y < yAxis; y++) {
                for (int x = 0; x < xAxis; x++) {
                    if (memory[z][y][x] == 'X') memory[z][y][x] = '+';
                    if (memory[z][y][x] == '.') memory[z][y][x] = '-';
                }
            }
        }
        if (firstEqual == 0 && secondEqual == 0) {
            return;
        }
        if (secondEqual == firstEqual) {
            for(int y = 0; y < yAxis; y++) {
                for(int i = 0; i < 3; i++) {
                    System.out.println("");
                    for(int x = 0; x < xAxis; x++) {
                        for (int j = 0;  j < 4; j++) {
                            System.out.print(memory[firstEqual][y][x]);
                        }
                    }   
                }
            }
            System.out.println("");
            System.out.println("");
            System.out.println("Becomes static at iteration nr." + (firstEqual - 1));
        } else {
            for (int l = 0; l < 3; l++) {
                for (int z = firstEqual; z <= secondEqual; z++) {
                    System.out.println("");
                    for(int y = 0; y < yAxis; y++) {
                        for(int i = 0; i < 2; i++) {
                            System.out.println("");
                            for(int x = 0; x < xAxis; x++) {
                                for (int j = 0;  j < 3; j++) {
                                    System.out.print(memory[z][y][x]);
                                }
                            }   
                        }
                    }
                }
            }
            System.out.println("");
            System.out.println("");
            System.out.println("Loop of " + (secondEqual - firstEqual + 1) + " starting at iteration " + (firstEqual + 1));
        }

        
    }
}
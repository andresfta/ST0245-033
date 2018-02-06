public class Taller3
{    
    static void towerOfHannoi(int n){
        towerOfHannoi(n, 1, 2, 3);
    }

    private static void towerOfHannoi(int n, int beg, int aux, int end){
        if(n == 1){
            System.out.println(beg + " → " + end);
        }else{
            towerOfHannoi(n - 1, beg, end, aux);
            towerOfHannoi(1, beg, aux, end);
            towerOfHannoi(n - 1, aux, beg, end);
        }
    }

    static void combinations(String s){
        combinations("", s);
    }

    private static void combinations(String base, String s){
        if(s.length() == 0){
            System.out.println(base);
        }else{
            combinations(base + s.charAt(0), s.substring(1));
            combinations(base + s.charAt(0), s.substring(1));
        }
    }

    static void permutations(String s) {
        permutations("", s);
    }

    private static void permutations(String base, String s){
        if(s.length() == 0){
            System.out.println(base);
        }else{
            for(int i = 0; i < s.length(); ++i){
                permutations(base + s.charAt(i), s.substring(0,i) + s.substring(i + 1));
            }
        }        
    }

    static void permutation4(String a){
        permutations4(a, "", a.length(), a.length());
    }

    private static void permutations4(String s, String prefix, int size, int count){
        if(count == 1){
            for(int i = 0; i < size; i++){
                System.out.println(prefix + s.charAt(i));
            }
        }
        else{
            for(int i = 0; i < size; i++){
                permutations4(s, prefix + s.charAt(i), size, count - 1);
            }
        }
    }

    static void floodFill(int[][] matriz, int x, int y, int originalColor, int replacementColor){
        if(matriz[x][y] == originalColor){
            matriz[x][y] = replacementColor;
            if(x != 0)floodFill(matriz, x - 1, y, originalColor, replacementColor);
            if(y != 0)floodFill(matriz, x, y - 1, originalColor, replacementColor);
            if(x < matriz.length - 1)floodFill(matriz, x + 1, y, originalColor, replacementColor);
            if(y < matriz[0].length - 1)floodFill(matriz, x, y + 1, originalColor, replacementColor);
        }
    }

    static void main(String[] args){
        int[][] a = new int[10][10];
        a[0][0] = 2;
        a[1][0] = 2;
        a[0][1] = 2;
        a[1][1] = 2;    
        floodFill(a, 5, 5, 0, 1);
        for(int[] i: a){
            for(int j: i){System.out.print(j + " ");}
            System.out.println();
        }        
    }
}

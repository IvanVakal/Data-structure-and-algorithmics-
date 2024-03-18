//  Name: Ivan Vakal
//  Assignment number: 6
//  Assignment: Dynamic Programming—Edit Distance
//  File name: .cpp
//  Date last modified: November  21, 2022
//  Honor statement: I have neither given nor received any unauthorized help on this assignment. 
import java.util.Scanner;
import java.util.Stack;

public class Vakal_2 {
//	
	static Stack<String> stack = new Stack<String>();
/*Find minimum number operations to convert str1 to str2

 * */
	private static int min(int x, int y, int z) {
        if (x <= y && x <= z)
            return x;
        if (y <= x && y <= z)
            return y;
        else
            return z;
    }
	
/*tracing   
 * @param dp
 * @param word1 
 * @param word2 
 * @param m
 * @param n
 * */
	public static void Tracing(int[][] T, String word1, String word2, int m, int n) {
		int x = m, y = n;
		
    	while(x != 0 || y != 0) {
//    		Current cell
    		int cc = T[x][y];
//    		if first word empty using inserting
    		if(x - 1 < 0) {	
    			stack.push("/" + word2.charAt(y - 1));
    			y --;
    			continue;
    		}
//    		if second word empty using deleting
    		else if(y - 1 < 0) {
    			stack.push("-");
    			x --;
    			continue;
    		}
//    		Current cell LEFT
            int cc_L = T[x][y - 1];
//    		Current cell UP
            int cc_U = T[x - 1][ y];
//    		Current cell DOWN
            int cc_D = T[x - 1][ y - 1];
            
            if ((cc_D <= cc_L && cc_D <= cc_U) && (cc_D == cc - 1 || cc_D == cc))
            {
            	//Replace
                if (cc_D == cc - 1)
                {
                    stack.push("/" +  word2.charAt(y - 1) );
                    x--;
                    y--;
                }
//                Keep current position
                else
                {
                    stack.push("^");
                    x--;
                    y--;
                }
            }
//            Insert
            else if (cc_L <= cc_D && cc_L == cc - 1)
            {
                stack.push("+" + word2.charAt(y - 1) );
                y--;                   
            }
//            Delete
            else
            {
                stack.push("-");
                x--;                   
            }
    		
    	}
    }
	
	private static int editDistDP(String str1, String str2, int m, int n) {
        int dp[][] = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0) {//first word empty,
                    dp[i][j] = j;//use insertion n times.
                }
                else if (j == 0) {//second word empty,
                    dp[i][j] = i;//use deletion n times.
                }
                else if (str1.charAt(i - 1) == str2.charAt(j - 1)) {//if last chars are same, divide the problem.
                    dp[i][j] = dp[i - 1][j - 1];
                }
                else {
                	dp[i][j] = 1 + min(dp[i][j - 1], dp[i - 1][j], dp[i - 1][j - 1]);
                }
            }
        }
        Tracing(dp,str1,str2,m,n);//do backtracing for path.
		
		//return the distance.
        return dp[m][n];
}
	public static void  main (String[] args) {
		
		
		 
		Scanner sc= new Scanner(System.in); //System.in is a standard input stream   
		String word1 = sc.nextLine();//read the string word1 
		String  word2 = sc.nextLine(); //read the string word1 
		int distance = editDistDP(word1, word2, word1.length(), word2.length());//get words and find distance.
		System.out.print(distance+ ":"+ "  ");
		while(!stack.isEmpty()) {
			System.out.print(stack.pop());
       }
       
       		System.out.println("\n");
       		
       		String word3 = sc.nextLine();//read the string word1 
       		String  word4 = sc.nextLine(); 
       		int distance2 = editDistDP(word3, word4, word3.length(), word4.length());//get words and find distance.
       		System.out.print(distance2+ ":"+ "  ");
       		while(!stack.isEmpty()) {
       			System.out.print(stack.pop());
	       }
	       
	       System.out.println("\n");
	       
	       	String word5 = sc.nextLine();//read the string word1 
			String  word6 = sc.nextLine(); 
			int distance3 = editDistDP(word5, word6, word5.length(), word6.length());//get words and find distance.
		    System.out.print(distance3+ ":"+ "  ");
		    while(!stack.isEmpty()) {
		       System.out.print(stack.pop());
		       }
     
	}
}

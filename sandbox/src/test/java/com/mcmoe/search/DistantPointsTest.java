package com.mcmoe.search;

import static org.junit.Assert.assertEquals;

import org.junit.Ignore;
import org.junit.Test;

/**
Examples
0)      
   4
   2
   Returns: {17, 17 }
   The square stretches from (0,0) to (18,18). After you paint (1,1), the farthest point within the square is (17,17).
1) 
   4
   3
   Returns: {1, 17 }
   Now there are two candidates. Both upper-left and bottom-right points within the square
   are equally distant from the already painted two, so we choose the leftmost one.
2)       
   4
   5
   Returns: {9, 9 }
   After you paint all the corners, the best choice is the center of the square.
3) 
   3
   14
   Returns: {1, 3 }
4) 
   5
   1089
   Returns: {33, 32 }
*/
public class DistantPointsTest {
   /* getKth(n, k) */
   
   @Test
   public void testFirstPoint() {
      for(int n = 2; n < 11; ++n) {
         int[] result = DistantPoints.getKth(n, 1);
         assertEquals(1, result[0]);
         assertEquals(1, result[1]);
      }
   }

   @Test
   public void testSecondPoint() {
      int[] result = DistantPoints.getKth(4, 2);
      System.err.println("testSecondPoint: (" + result[0] + ", " + result[1] + ")");
      assertEquals(17, result[0]);
      assertEquals(17, result[1]);
   }
   
   @Test
   public void testThirdPoint() {
      int[] result = DistantPoints.getKth(4, 3);
      System.err.println("testThirdPoint: (" + result[0] + ", " + result[1] + ")");
      assertEquals(1, result[0]);
      assertEquals(17, result[1]);
   }
   
   @Test
   public void testFifthPoint() {
      int[] result = DistantPoints.getKth(4, 5);
      System.err.println("testFifthPoint: (" + result[0] + ", " + result[1] + ")");
      assertEquals(9, result[0]);
      assertEquals(9, result[1]);
   }
   
   @Test
   public void test3_14_Point() {
      int[] result = DistantPoints.getKth(3, 14);
      System.err.println("test3_14_Point: (" + result[0] + ", " + result[1] + ")");
      assertEquals(1, result[0]);
      assertEquals(3, result[1]);
   }
   
   @Test
   public void test5_1089_Point() {
      int[] result = DistantPoints.getKth(5, 1089);
      System.err.println("test5_1089_Point: (" + result[0] + ", " + result[1] + ")");
      assertEquals(33, result[0]);
      assertEquals(32, result[1]);
   }
}

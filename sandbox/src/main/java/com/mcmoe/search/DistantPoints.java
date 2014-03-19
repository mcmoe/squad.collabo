package com.mcmoe.search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
TopCoder problem "DistantPoints" used in TCO10 Semi 1 (Division I Level One)

Problem Statement
      There is a square on a plane with (0,0) as its lower left and (2^N+2, 2^N+2) as its upper right corner.
      It is known that K distinct points have to be painted.
      Each of them should be strictly inside the square and should have integral coordinates.
      The first point to be painted is always (1,1).
      For each of the next points, the one from which the Euclidean distance to the closest
      already painted point is maximum should be chosen.
      In case of ties, the leftmost point should be painted.
      If there are still several possibilities, the lowermost point is chosen. 
      Return int[] with exactly two elements, the X and Y coordinates of the K-th point to be painted.
 
Definition:
Class:   DistantPoints
Method:  getKth
Parameters: int, int 
Returns: int[]
Method signature: int[] getKth(int N, int K) (be sure your method is public)
 
Notes
-  The Euclidean distance between two points (x1,y1) and (x2,y2) is equal to the square root of (x1-x2)^2+(y1-y2)^2.
 
Constraints
-  N will be between 2 and 10, inclusive.
-  K will be between 1 and the amount of points inside the square with side length 2^N+2, inclusive.

 See @class DistantPointsTest @link Distan for Examples
*/



/**
 * @author MC Moe
 *
 */
public class DistantPoints {
   
   private static final Map<Integer, Matrix> matrices;
   
   static {
      matrices = new HashMap<Integer, Matrix>();
      
      for(Integer n = 2; n <= 10; ++n) {
         int length = (int) Math.pow(2, n) + 2;
         matrices.put(n, new Matrix(length));
      }
   }
   
   private DistantPoints() { }

   public static int[] getKth(int n, int k) {
      if(outOfBounds(n, k))
         return new int[] {-1, -1};
      
      return buildKth(n, k);
   }
   
   private static int[] buildKth(int n, int k) {
      Point kTh = matrices.get(n).k(k);
      return new int[] {kTh.x(), kTh.y()};
   }
   
   private static boolean outOfBounds(int n, int k) {
      int length = (int) Math.pow(2, n) + 2;
      if(n < 2 || n > 10 || 
         k < 1 || k > length * length) {
            return true;
         }
      return false;
   }
   
   private static class Point {
      
      private int x;
      private int y;
      
      public Point(int x, int y) {
         this.x = x;
         this.y = y;
      }
      
      public int x() {
         return x;
      }

      public int y() {
         return y;
      }
      
      @Override
      public int hashCode() {
         final int prime = 31;
         int result = 1;
         result = prime * result + x;
         result = prime * result + y;
         return result;
      }

      @Override
      public boolean equals(Object obj) {
         if (this == obj)
            return true;
         if (obj == null)
            return false;
         if (getClass() != obj.getClass())
            return false;
         Point other = (Point) obj;
         if (x != other.x)
            return false;
         if (y != other.y)
            return false;
         return true;
      }
      
      @Override
      public String toString() {
         return "(" + x + ", " + y + ")";
      }
   }
   
   private static class DistancePoint extends Point {

      private Double distance;
      private Point target;
      
      public DistancePoint(int x, int y, Point target) {
         super(x, y);
         this.target = target;
         this.distance = distance(target);
      }
      
      public Double getDistance() {
         return distance;
      }
      
      public Point getTargetPoint() {
         return target;
      }
      
      @Override
      public String toString() {
         return "[" +super.toString()+ " <- " + distance + " ->" + target.toString() + "]";
      }
      
      /**
       * Calculates Euclidean distance between two points:
       *    distance(p1, p2): square root of (x1-x2)^2+(y1-y2)^2
       * @param other the point to calculate distance to/from
       * @return the distance
       */
      private Double distance(Point other) {
         return Math.sqrt((Math.pow(x() - other.x(), 2) + Math.pow(y() - other.y(), 2)));
      }
   }
   
   private static class Matrix {
      private Point[][] points;
      private Map<Integer, Point> kayz;
      
      public Matrix(int length) {
         kayz = new HashMap<Integer, Point>();
         points = new Point[length][length];
         for(int i = 1; i < points.length; ++i) {
            for(int j = 1; j < points.length; ++j) {
               points[i][j] = new Point(i, j);
            }
         }
      }
      
      public Point k(int k) {
         if(!kayz.containsKey(k)) {
             kayz.put(k, farthest(k));
         }
         return kayz.get(k);
      }
      
      /**
       * computes farthest Point (kth) from 1 -> k-1 Point
       * @param k the kth Point to be painted
       * @return the farthest kth Point
       */
      private Point farthest(int k) {
         if(k == 1) {
            return new Point(1, 1);
         }
         Set<DistancePoint> minimals = new HashSet<DistancePoint>();
         Set<Point> subKayz = new HashSet<Point>();
         
         for(int i = 1; i < k; ++i) {
            subKayz.add(k(i));
         }
         
         for(int i = 1; i < points.length; ++i) {
            for(int j = 1; j < points.length; ++j) {
               if(kayz.containsValue(points[i][j])) { continue; }
               minimals.add(minimum(points[i][j], subKayz));
            }
         }
         return maximum(minimals);
      }
      
      private DistancePoint minimum(Point point, Set<Point> subKayz) {
         Double minimum = Double.MAX_VALUE;
         DistancePoint distancePoint = null;
         
         for(Point k : subKayz) {
            DistancePoint currentDistance = new DistancePoint(point.x(), point.y(), k);
            Double distance = currentDistance.getDistance();
            
            if(distance.compareTo(minimum) < 0) {
               minimum = distance;
               distancePoint = currentDistance;
            }   
         }
         return distancePoint;
      }
      
      private Point maximum(Set<DistancePoint> candidates) {
         DistancePoint maximum = candidates.iterator().next();
         for(DistancePoint c : candidates) {     
            if(c.getDistance().compareTo(maximum.getDistance()) > 0) {
               maximum = c;
            } else if(c.getDistance().compareTo(maximum.getDistance()) == 0) {
               if(c.x() < maximum.x()) {
                  maximum = c;                  
               } else if(c.x() == maximum.x() && c.y() < maximum.y()) {
                  maximum = c;
               }
            }
         }
         return new Point(maximum.x(), maximum.y());
      }
   }  
}





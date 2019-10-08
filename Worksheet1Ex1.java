/**
 * This program computes the area of a circle given the radius.
 * @author Weikai Jia
 * @version 2019-10-04
 */
public class Ex1{

  /**
   * This method's input is the radius of the cirlce and outputs the area.
   * @param radius The radius of the circle.
   * @return The area of the circle.
   */
  public static double areaCircle(double radius){
    return Math.PI*radius*radius;
  }

  public static void main(String args[]){
    double r1=0,r2=5,r3=10;
    System.out.println("The area of a cicle of radius "+r1 +" is: "+areaCircle(r1)+".");
    System.out.println("The area of a cicle of radius "+r2 +" is: "+areaCircle(r2)+".");
    System.out.println("The area of a cicle of radius "+r3 +" is: "+areaCircle(r3)+".");
  }
}

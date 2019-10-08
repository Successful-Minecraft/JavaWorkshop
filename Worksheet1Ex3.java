/**
 *
 * This program computes the angle between the minute hand and the hour hand of a clock
 * @author Weiaki Jia
 * @version 2019-10-04
 */
public class Ex3{

  /**
   * This medthod computes the angle between the minute hand and the hour hand.
   * @param hours The input hour value.
   * @param minutes The input minute value.
   * @param seconds The input second value.
   * @return The angle measured counterclockwise from hour to minute hand and it is rounded to an integer between 0 to 359.
   */
  public static int timeToAngle2(int hours, int minutes, double seconds){
    double hour=0,minute=0;
    //The increment of degree: 1 minute to 1 hour; 1 second to 1 minute; 1 second to 1 hour.
    double incl=30.0/60, inclmin=6.0/60, inclhour=30.0/3600;
    //Tht input hours and minute are restricted modulo 12 and 60 respectively.
    double angle=0;
    hour=(double) (hours%12);
    minute=(double) (minutes%60);
    /*theta1 is the angle between 12 and hour hand.
      theta2 is the angle between 12 and minute  hand.
      the degree increment originated from minute and second have been added to hour and minte properly.
    */
    //Calculate the difference angle with increment.
    double theta1=0,theta2=0;
    theta1=hour/12.0*360+minute*incl+seconds*inclhour;
    theta2=minute/60.0*360+seconds*inclmin;
    angle=theta1-theta2;
    //The return angle should be restricted modulo 360.
    return ((int) Math.round(angle)+360)%360;
  }


  /**
   * This medthod computes the angle between the minute hand and the hour hand.
   * It uses the timeToAngle2 and assign the second to 0.
   * @param hours The input hour value.
   * @param minutes The input minute value.
   * @return The angle measured counterclockwise from hour to minute hand and it is
   * rounded to an integer between 0 to 359.
   */
  public static int timeToAngle(int hours, int minutes){
      return timeToAngle2(hours, minutes,0);
  }    

  
  public static void main(String args[]){
    //Here is the tests.
    int h1=11,m1=49,h2=9,m2=0,h3=3,m3=0,h4=18,m4=0,h5=1,m5=0,h6=2,m6=30,h7=4,m7=41;
    int h8=0,m8=0,h9=13,m9=5;
    double s8=20,s9=27.272727272727;
    System.out.println("The time "+ h1+":"+m1+" has the angle "+timeToAngle(h1,m1)+ ".");
    System.out.println("The time "+ h2+":0"+m2+" has the angle "+timeToAngle(h2,m2)+ ".");
    System.out.println("The time "+ h3+":0"+m3+" has the angle "+timeToAngle(h3,m3)+ ".");
    System.out.println("The time "+ h4+":0"+m4+" has the angle "+timeToAngle(h4,m4)+ ".");
    System.out.println("The time "+ h5+":0"+m5+" has the angle "+timeToAngle(h5,m5)+ ".");
    System.out.println("The time "+ h6+":"+m6+" has the angle "+timeToAngle(h6,m6)+ ".");
    System.out.println("The time "+ h7+":"+m7+" has the angle "+timeToAngle(h7,m7)+ ".");

    System.out.println("The time "+ h8+":0"+m8+":"+s8+" has the angle "+timeToAngle2(h8,m8,s8)+ ".");
    System.out.println("The time "+ h9+":0"+m9+":"+s9+" has the angle "+timeToAngle2(h9,m9,s9)+ ".");

  }
}

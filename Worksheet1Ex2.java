/**
 * 
 * This program converts masses given in the imperial system into kilograms.
 * @author Weikai Jia
 * @version 2019-10-04 
 */
public class Ex2{
  public static void main(String args[]){
    double stone=11,pound=6;
    System.out.println("A person’s weight in kg corresponding to "+stone+" stones and "+ pound+" pounds is "+imperialToKg
      (0,0,0,11,6,0,0,0)+" kg");
  }

  /**
   * This method converts masses given in the imperial system into kilograms.
   * @param ton 1 ton equals 2240 pounds.
   * @param hundredweight 1 hundredweight equals 112 pounds.
   * @param quarter 1 quarter equals 28 pounds.
   * @param stone 1 stone equals 14 pounds.
   * @param ounce 1 ounce equals 1/16 pounds.
   * @param drachm 1 drachm equals 1/256 pounds.
   * @param grain 1 grain equals 1/7000 pounds.
   * @param pound 1 ton equals 1 pound.
   * @return the kilograms corresponding to the input weight.
   */
  public static double imperialToKg(double ton, double hundredweight,double quarter, double stone, double pound, double ounce, double drachm, double grain){
    double tempkilo=0;
    double pound2kilo = 0.45359237;
    tempkilo = (ton*2240+hundredweight *112 +quarter *28+stone*14+ounce*1/16+drachm *1/256+grain*1/7000+pound)*pound2kilo;
    return tempkilo;
  }
} 


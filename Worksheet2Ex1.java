/** Car is a class that has 4 field variables:
 *  make     :   String
 *  price    :   int
 *  maxSpeed :   int
 *  colour   :   String
 *
 *  @author   Weikai Jia
 *  @version  2019-10-08
 */
public class Car{
    private String make;

    private int price;

    private int maxSpeed;

    private String colour;


    /**
     * The constructor of the Car class.
     * @param make      The make of the car.
     * @param price     The price of the car.
     * @param maxSpeed  The maxSpeed of the car.
     * @param colour    The colour of the car.
     */
    public Car(String make,int price,int maxSpeed,String colour){
        this.make=make;
        this.price=price;
        this.maxSpeed=maxSpeed;
        this.colour=colour;
    }

    /**
     * This method sets the make.
     * @param make The make of the car.
     */
    public void setMake (String make){
        this.make=make;
    }

    /**
     * This method sets the price.
     * @param price The price of the acr.
     */
    public void setPrice (int price){
        this.price=price;
    }

    /**
     * This method sets the max speed.
     * @param maxSpeed The max speed of the car.
     */
    public void setMaxSpeed (int maxSpeed){
        this.maxSpeed=maxSpeed;
    }

    /**
     * This method sets the colour.
     * @param colour The collour of the car.
     */
    public void setColour (String colour){
        this.colour=colour;
    }

    /**
     * This method gets the make.
     * @return The make of the car.
     */
    public String getMake(){
        return this.make;
    }

    /**
     * This method gets the price.
     * @return The price of the car.
     */
    public int getPrice(){
        return this.price;
    }

    /**
     * This method gets the max speed.
     * @return The max speed of the car.
     */
    public int getMaxSpeed(){
        return this.maxSpeed;
    }

    /**
     * This method gets the colour of the car.
     * @return The colour of the car.
     */
    public String getColour() {
        return this.colour;
    }

    /**
     * This method returns the objects of the Car class in a user-friendly way.
     * @return The objects in the Car class.
     */
    public String toString(){
        return
                 "\nThe make of the car: "+this.getMake()+
                 ",\nthe price: "+this.getPrice()+
                 ",\nthe max speed: "+ this.getMaxSpeed()+
                 ",\nthe colour: "+this.getColour() + ".\n";
    }

}

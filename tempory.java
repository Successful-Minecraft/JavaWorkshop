import javafx.application.Application;

import java.awt.desktop.AppEvent;
import java.util.Arrays;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;

public class Waffle extends Application {
    private Expenditure[] expenditures;
    private int []        SecNum;
    private final int maximum = 12;
    private static int[][] grid;

    public Waffle(Expenditure [] expenditures){
        this.expenditures =expenditures;

    }

    public void SortWaffle(){
        Arrays.sort(this.expenditures, (Expenditure exp1, Expenditure exp2) ->
                exp2.getValue() - exp1.getValue());
    }

    public void CalculateSecnum (){
        int length=this.expenditures.length;
        SortWaffle();
        double sum=0;

        for(int i=0;i<length;++i)
            sum+=(double)this.expenditures[i].getValue();

        if( length<=this.maximum ){
            this.SecNum = new int[length];
            for(int j=0;j<length;++j)
                this.SecNum[j]= (int) Math.round(((double)this.expenditures[j].getValue()/sum*100));
        }

    }


    /**
     *  @param stage The window to be displayed.
     */
    @Override
    public void start(Stage stage) throws Exception {
        Group root = new Group();
        int OFFSET=10;
        int radius=5;
        for (int i = 0; i< grid.length; i++) {
            for (int j = 0; j< grid[i].length; j++) {
                Circle circle = new Circle(OFFSET+j*OFFSET,OFFSET+i*OFFSET, radius);
                if (grid[i][j] == 1) {
                    circle.setFill(Color.GREEN);
                } else if (grid[i][j] == 2) {
                    circle.setFill(Color.RED);
                }
                root.getChildren().add(circle);
            }
        }

        //Creating a scene graph, consisting of the circles.
        //The scene containing one scene graph
        int SCENE_WIDTH  = OFFSET + OFFSET * (grid[0].length + 1);
        int SCENE_HEIGHT = OFFSET + OFFSET * (grid.length + 2);
        Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);
        stage.setTitle("Dots");
        stage.setScene(scene);
        stage.show();


    }


    public static void main(String []args) {
        int[][] grid1 = {{2,2},
                {1,2,0,0,1},
                {2,1,0,1,2,1}};
        grid = grid1;
        launch(args);
        Expenditure [] expenditures = new Expenditure[]
                        {
                                new Expenditure( "Salaries", 11000 ),
                                new Expenditure( "Paper", 2000 ),
                                new Expenditure( "Rent", 5000 ),
                                new Expenditure( "Most popular books on Java etc.", 10000 ),
                                new Expenditure( "Heating", 3000 ),
                                new Expenditure( "Coffee/Tea", 7000 ),
                                new Expenditure( "Biscuits", 8000 ),
                                new Expenditure( "Travel", 18000 ),
                                new Expenditure( "Electricity", 1000 ),
                                new Expenditure( "Pencils", 3000 )
                        };


        Waffle w= new Waffle( expenditures );
        w.SortWaffle();
        w.CalculateSecnum();
        for(int i=0;i<w.expenditures.length;++i)
            System.out.println(w.SecNum[i]);

        
    }
}





////////Pie:////////
import javafx.application.Application;

import java.awt.*;
import java.awt.desktop.AppEvent;
import java.util.ArrayList;
import java.util.Arrays;

import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;


import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

import javax.lang.model.type.ArrayType;
import javax.lang.model.type.NullType;


public class Pie extends Application {
    public static Expenditure[]         expenditures;
    public static int []                SecNum;
    public static final int             maximum = 8;
    public static int[][]               grid;
    public static Color []              cnote;
    public static ArrayList<Line>       lines;
    public static ArrayList<Text>       texts;
    public static double []             theta;





    public static void SortPie(){
        System.out.println("SortPie");
        Arrays.sort(expenditures, (Expenditure exp1, Expenditure exp2) ->
                exp2.getValue() - exp1.getValue());
    }

    /*
    public static int SortStringWaffle(){
        Arrays.sort(expenditures, (Expenditure exp1, Expenditure exp2) ->
                exp2.getDescription().length()- exp1.getDescription().length());
        int r=expenditures[0].getDescription().length();
        SortPie();
        return r;


    }
    */


    public static void CalculateSecnum (){
        SortPie();
        theta= new double[expenditures.length];
        System.out.println("CalculateSecnum");
        double sum=0.0;
        for(int i=0;i<expenditures.length;++i) {
            sum += (double) expenditures[i].getValue();
            System.out.println( expenditures[i].getValue() );
            theta[i]=0.0;
        }




        for(int i=0;i<expenditures.length;++i) {
            theta[i] = (double) expenditures[i].getValue() / sum  * 2*Math.PI;
            System.out.println( theta[i]+" "+100*Math.cos(theta[i])+"  "+100*Math.sin(theta[i]));

        }


        int k=0,CoIndex=0;


    }


    public static void DrawLine(double r){
        double R=r;
        double sumtheta=0.0;

        lines.add(new Line(300, 300, 300+(int)R*Math.cos(0), 300+(int)R*Math.sin(0)  ));

        for (int i = 0; i< theta.length; i++) {
            sumtheta+=theta[i];
            lines.add(new Line(300, 300, 300+(int)R*Math.cos(sumtheta), 300-(int)R*Math.sin(sumtheta)  ));
        }
    }

    public static void AddText(Double r){
        double R=r;
        double sumtheta=0.0;



        for (int i = 0; i< theta.length; i++) {
            sumtheta+=theta[i];
            Text t=new Text(300+(int)R*Math.cos(sumtheta), 300-(int)R*Math.sin(sumtheta),expenditures[i].getDescription());
            t.setFont(Font.font(10));
            texts.add(t);
        }
    }





    /**
     *  @param stage The window to be displayed.
     */
    @Override
    public void start(Stage stage) throws Exception {

        Group root = new Group();

        for (int i = 0; i< lines.size(); i++) {
            root.getChildren().add(lines.get( i) );
        }
        for (int i = 0; i< texts.size(); i++) {
            root.getChildren().add(texts.get( i) );
        }

        Circle c= new Circle(300,300,100);
        c.setFill( Color.TRANSPARENT );
        c.setStroke(Color.BLACK);
        c.setStrokeWidth( 1 );

        root.getChildren().add(c);


        //Creating a scene graph, consisting of the circles.
        //The scene containing one scene graph
        int SCENE_WIDTH  = 1000;
        int SCENE_HEIGHT = 1000;
        Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);
        stage.setTitle("Dots");
        stage.setScene(scene);
        stage.show();


    }


    public static void main(String []args) {

        lines= new ArrayList<Line>();
        texts= new ArrayList<Text>();

        expenditures = new Expenditure[]
                {
                        new Expenditure( "Salaries", 11000 ),
                        new Expenditure( "Paper", 2000 ),
                        new Expenditure( "Rent", 5000 ),
                        new Expenditure( "Most popular books on Java etc.", 10000 ),
                        new Expenditure( "Heating", 3000 ),
                        new Expenditure( "Coffee/Tea", 7000 ),
                        new Expenditure( "Biscuits", 8000 ),
                        new Expenditure( "Travel", 18000 ),
                        new Expenditure( "Electricity", 1000 ),
                        new Expenditure( "Pencils", 3000 )
                };

        System.out.println("Waffle constructor");



        SortPie();
        CalculateSecnum();
        DrawLine(100.0);
        AddText( 150.0 );
        launch(args);


    }

}

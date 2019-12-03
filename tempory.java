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



import java.awt.datatransfer.MimeTypeParseException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class MiniInterpreter {
    public ArrayList<String>    lines;
    public ArrayList<Integer>   wholenumbers;
    public ArrayList<String>    names;
    public int                  index;

    public MiniInterpreter(){
        lines = new ArrayList < String >        ();
        wholenumbers = new ArrayList< Integer > ();
        names= new ArrayList<String>            ();
        index=0;
    }

    public boolean EvaluateEqualNum(){
        String s= this.lines.get(index);

        return(s.matches("[A-Z]\\s=\\s\\d+|[A-Z]\\s=\\s\\-\\d+" ));
    }

    public boolean EvaluateEqualLtr(){
        String s= this.lines.get(index);

        return(s.matches("[A-Z]\\s=\\s[A-Z]" ));
    }

    public boolean EvaluateEqualLtrAndNum(){
        String s= this.lines.get(index);

        return(s.matches(
                "[A-Z]\\s=\\s[A-Z]\\s\\+\\s\\d+[A-Z]\\s=\\s[A-Z]\\s\\+\\s\\-\\d+" ));
    }

    public boolean EvaluateEqualNumAndLtr(){
        String s= this.lines.get(index);

        return(s.matches(
                "[A-Z]\\s=\\s\\d+\\s\\+\\s[A-Z]|[A-Z]\\s=\\s\\-\\d+\\s\\+\\s[A-Z]" ));
    }
    public boolean EvaluateEqualNumAndNum(String s0){


        return(s0.matches(
                "[A-Z]\\s=\\s(\\d+|\\-\\d+)\\s\\+\\s(\\d+|\\-\\d+)"));
    }

    public boolean EvaluateOutput(){
        return (this.lines.get( index ).length()==0);
    }

    public void GetLtrToLtr(String s0){

        String s1="";s1+=s0.charAt(0);
        String s2="";s2+=s0.charAt(4);
        int i=0,index=0,num=0;
        for(i=0;i<this.names.size();++i){
            if(this.names.get(i).matches(s2)){
                index=i;
                num=this.wholenumbers.get(i);
            }
        }
        for(i=0;i<this.names.size();++i) {
            if (this.names.get( i ) .matches(s1)) {
                this.wholenumbers.set( i, num );
            }
        }
    }
    public void GetLtrToNum(String s0){

        String s1="";s1+=s0.charAt(0);
        String s2="";
        for(int j=4;j<s0.length();++j) s2+=s0.charAt(j);
        int staticnum= Integer.parseInt(s2);
        int i=0,index=0,num=0;

        boolean b=false;
        for(i=0;i<this.names.size();++i) {

            if (s1.matches( this.names.get(i) )) {
                this.wholenumbers.set( i, staticnum );
                b=true;
                System.out.println("Got it!");
            }
        }
        if(b==false){
            this.names.add(s1);
            this.wholenumbers.add(staticnum);
        }
    }

    public void GetLtrToLtrNum(String s0){

        String s1="";s1+=s0.charAt(0);
        String s2="";s2+=s0.charAt(4);
        String s3="";
        for(int j=8;j<s0.length();++j) s3+=s0.charAt(j);
        int staticnum= Integer.parseInt(s3);

        int i=0,index=0,num=0;

        boolean b=false;
        for(i=0;i<this.names.size();++i) {

            if (s2.matches( this.names.get(i) )) {
                num=this.wholenumbers.get(i);
            }
        }
        for(i=0;i<this.names.size();++i) {

            if (s1.matches( this.names.get(i) )) {
                this.wholenumbers.set(i,num+staticnum);
            }
        }
    }

    public void GetLtrToNumNum(String s0) {
        String s1="";s1 += s0.charAt(0);
        String stemp="";
        for(int i=4;i<s0.length();++i)
            for(int j=i;j<s0.length();++j){
                stemp +=s0.charAt(j);
                if(s0.matches( "\\-|\\-\\d+|\\d+")){

                }
            }

    }

    public void Evaluate(){
        int i=0,j=0,k=0;
        for(i=0;i<this.lines.size();++i){
            this.index=i;
            if(this.EvaluateEqualLtr())
            {
                GetLtrToLtr( this.lines.get(index) );
            }
            else if(this.EvaluateEqualNum()){

            }
        }
    }

    public void OutputAll(){
        System.out.println();
        for(int i=0;i<this.names.size();++i)
            System.out.println(this.names.get(i)+"  "+this.wholenumbers.get(i));
    }

    public boolean FindMatch(String s0){
        System.out.println(
                s0.matches("[A-Z]\\s\\=\\s"+
                        "([A-Z]|\\-\\d+|\\d+)"+
                        "\\s\\+\\s"+"" +
                        "([A-Z]|\\-\\d+|\\d+)")
                        || s0.matches( "[A-Z]\\s\\=\\s[A-Z]")
        );
        Boolean b=(s0.matches("[A-Z]\\s\\=\\s"+
                "([A-Z]|\\-\\d+|\\d+)"+
                "\\s\\+\\s"+"" +
                "([A-Z]|\\-\\d+|\\d+)")
                || s0.matches( "[A-Z]\\s\\=\\s[A-Z]"));
        return b;

    }

    public void OutputMatch(String s0) {
        if (this.FindMatch( s0 )) {
            String s1 = "";
            s1 += s0.charAt( 0 );
            System.out.print( s1 + " = " );
            String[] snote = new String[2];
            snote[0] = "";
            snote[1] = "";
            int[][] numnote = new int[2][2];
            numnote[0][0] = 0;
            numnote[0][1] = 0;
            numnote[1][0] = 0;
            numnote[1][1] = 0;
            String s = "";
            int strindex = 0, numindex = 0;

            for (int i = 4; i < s0.length(); ++i) {

                s += s0.charAt( i );
                String temp = "";
                temp += s0.charAt( i );
                if (s0.length()==5) { snote[0]+=s0.charAt(4);  }


                else if( temp.matches( "\\s" )) {
                    else if (s.matches( "\\+\\s" )) {
                        s = "";
                        for(int j=i+1;j<s0.length();++j)
                            s+=s0.charAt(j);
                        System.out.println("{"+s+ "}");
                        if (s.matches( "[A-Z]" )) {
                            String temp2 = "";
                            temp2 += s.charAt( 0 );
                            if (strindex == 0) {
                                snote[0] = temp2;
                                ++strindex;
                            } else if (strindex == 1) {
                                snote[1] = temp2;
                            }
                            s = "";

                        } else if (s.matches( "\\d+\\s|\\-\\d+" )) {
                            String temp2 = "";

                            int num = Integer.parseInt( s );
                            if (numindex == 0) {
                                numnote[0][0] = num;
                                numnote[0][1] = 1;
                                ++numindex;
                            } else if (numindex == 1) {
                                numnote[1][0] = num;
                                numnote[0][1] = 1;
                            }
                            s = "";
                        }
                    
                    }
                    else if (s.matches( "\\+\\s" )) {
                        s = "";
                        for(int j=i+1;j<s0.length();++j)
                            s+=s0.charAt(j);
                        System.out.println("{"+s+ "}");
                        if (s.matches( "[A-Z]" )) {
                            String temp2 = "";
                            temp2 += s.charAt( 0 );
                            if (strindex == 0) {
                                snote[0] = temp2;
                                ++strindex;
                            } else if (strindex == 1) {
                                snote[1] = temp2;
                            }
                            s = "";

                        } else if (s.matches( "\\d+\\s|\\-\\d+" )) {
                            String temp2 = "";

                            int num = Integer.parseInt( s );
                            if (numindex == 0) {
                                numnote[0][0] = num;
                                numnote[0][1] = 1;
                                ++numindex;
                            } else if (numindex == 1) {
                                numnote[1][0] = num;
                                numnote[0][1] = 1;
                            }
                            s = "";
                        }
                    }
                }


            }
            System.out.println("String: ");
            System.out.println(snote[0]+"  "+snote[1]);
            System.out.println("number: ");
            System.out.println(numnote[0][0]+"  "+numnote[1][0]);

        }

    }




    public static void main (String args[]){

        MiniInterpreter m= new MiniInterpreter();
        //Do some tests for the GetLtrToNum();

        m.GetLtrToNum( "A = 113" );
        m.OutputAll();

        m.GetLtrToNum( "A = 124" );
        m.OutputAll();

        m.GetLtrToNum( "A = -124" );

        m.OutputAll();
        m.GetLtrToNum( "B = 124" );

        m.OutputAll();
        m.GetLtrToNum( "A = 129" );

        m.OutputAll();
        m.GetLtrToNum( "C = 129" );

        m.OutputAll();
        m.GetLtrToNum( "C = 139" );

        m.OutputAll();
        m.GetLtrToLtr( "A = B" );

        m.OutputAll();
        m.GetLtrToLtr( "C = B" );

        m.OutputAll();
        m.GetLtrToLtrNum( "C = B + 123" );

        m.OutputAll();
        m.GetLtrToLtrNum( "C = B + -123" );

        m.OutputAll();
        System.out.println(m.EvaluateEqualNumAndNum("C = -23 + -445"));
        System.out.println(m.EvaluateEqualNumAndNum("C = -23 + A"));
        System.out.println(m.EvaluateEqualNumAndNum("C = 23 + -445"));
        System.out.println(m.EvaluateEqualNumAndNum("C = B + -445"));
        System.out.println(m.EvaluateEqualNumAndNum("C = 0313 + 245"));
        System.out.println("start the test");
        m.FindMatch( "C = B" );
        m.FindMatch( "C = B + D" );
        m.FindMatch( "C = B + -123" );
        m.FindMatch( "C = -203 + -123" );
        m.FindMatch( "C = H" );
        m.FindMatch( "C = -002020202 + C" );
        m.FindMatch( "C = -002020202 + CD" );
        m.FindMatch( "C = -002020202 + ." );
        m.FindMatch( "D=1234 + 1234455667" );
        System.out.println(Integer.parseInt("0010203"));
        System.out.println(Integer.parseInt("-0010203"));
        System.out.println(Integer.parseInt("00000010203"));
        System.out.println(Integer.parseInt("-00000"));
        System.out.println(Integer.parseInt("00000"));
        m.OutputMatch("A = B + C");
        m.OutputMatch("A = 1492 + -2039");
        m.OutputMatch("A = 0012 + -039");
        m.OutputMatch("A = -0012 + 039");


    }

}

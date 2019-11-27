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

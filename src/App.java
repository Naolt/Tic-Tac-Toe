import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
 

public class App extends Application {
    String inside = "X";
    String winner= " ";
    Text theWinner;
    int counter = 0;
    Stage stage;
    Scene youwonScene;
    Scene mainScene;
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;
        
        stage.setTitle("TicTacToe!");
       
        Button btn[] = new Button[9];
        VBox box = new VBox(2);
        HBox rows[] = new HBox[3];
        

        for(int j = 0; j < rows.length ; ++j){
            rows[j] = new HBox(2);
            rows[j].prefWidth(300);
            rows[j].setAlignment(Pos.CENTER);
        }
        for(int i = 0; i < btn.length;++i){
            btn[i]  = new Button(" ");
            btn[i].setPrefWidth(40);
            btn[i].setPrefHeight(40); 
        }
        rows[0].getChildren().addAll(btn[0],btn[1],btn[2]);
        rows[1].getChildren().addAll(btn[3],btn[4],btn[5]);
        rows[2].getChildren().addAll(btn[6],btn[7],btn[8]);
        
        box.getChildren().addAll(rows[0],rows[1],rows[2]);
        box.setBackground(new Background(new BackgroundFill(Color.GRAY,CornerRadii.EMPTY, Insets.EMPTY)));
        box.setPadding(new Insets(30));
        // btn.setOnAction(new EventHandler<ActionEvent>() {
 
        //     @Override
        //     public void handle(ActionEvent event) {
        //         System.out.println("Hello World!");
        //     }
        // });
        for(int i = 0 ; i < 9 ; ++i){
            Button b = btn[i];
            if(btn[i].getText() == " "){
                b.setOnAction((e)->{
                    if(b.getText() == " "){
                        change();
                        b.setText(inside);
                        counter++;
                        if(counter > 3){
                            checkWin(btn);
                        }
                    }
                    
                });
            }
        }
        
       BorderPane root = new BorderPane();
       Text gametitle = new Text("Tic Tac Toe");
       gametitle.setFont(new Font(20));
       gametitle.setFill(Color.WHITESMOKE);
       HBox playAgainBox = new HBox();
    //    Region rg = new Region();
       Button playAgain = new Button("Reset");
       playAgain.setOnAction((e) ->{
        reset(btn);
       });
       
       playAgain.setPrefHeight(20);
       playAgainBox.getChildren().addAll(playAgain);
       playAgainBox.setPrefWidth(300);
       playAgainBox.setAlignment(Pos.CENTER_RIGHT);
       playAgainBox.setPadding(new Insets(10));
        playAgainBox.setBackground(new Background(new BackgroundFill(Color.GRAY, CornerRadii.EMPTY, Insets.EMPTY)));
        

       HBox titleBox = new HBox();
        titleBox.getChildren().add(gametitle);
        titleBox.setAlignment(Pos.CENTER);
        titleBox.setPrefWidth(300);
        titleBox.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        titleBox.setPadding(new Insets(10));
        
        // for telling player 
        VBox player = new VBox(2);
        Text player1 = new Text("Player 1: X");
        Text player2 = new Text("Player 2: O");
        
        player1.setFont(new Font("Calibri",16));
        player2.setFont(new Font("Calibri",16));
        player1.setFill(Color.WHITE);
        player2.setFill(Color.WHITE);
        player.getChildren().addAll(player1,player2);
        player.setBackground(new Background(new BackgroundFill(Color.GRAY, CornerRadii.EMPTY, Insets.EMPTY)));
        player.setPadding(new Insets(5));


        VBox titleBoxAndPlayer = new VBox(10);
        titleBoxAndPlayer.getChildren().addAll(titleBox,player);
       titleBoxAndPlayer.setBackground(new Background(new BackgroundFill(Color.GRAY, CornerRadii.EMPTY, Insets.EMPTY)));
        root.setTop(titleBoxAndPlayer);
        root.setCenter(box);
        root.setBottom(playAgainBox);


        BorderPane youWonPane = new BorderPane();
         theWinner  = new Text();
         theWinner.setFont(new Font(20));
         theWinner.setFill(Color.RED);

        Button playGameAgain = new Button("Play Again");
        playGameAgain.setOnAction((e) -> {
            stage.setScene(mainScene);
            reset(btn);
        });
      
        youWonPane.setCenter(theWinner);
        youWonPane.setBottom(playGameAgain);
        youwonScene = new Scene(youWonPane,300,300);

        mainScene = new Scene(root,300,300);
        stage.setScene(mainScene);
        stage.show();


// second scene


    }
    public void change(){
        if(isEven(counter)){
            inside = "X";
        }else{
            inside = "O";
        }
    }
    public boolean isEven(int num){
        if(num % 2 == 0){
            return true;
        }
        return false;
    }
    public boolean isFilled(Button  btns[]){
        boolean filled = true;
        for(int i  = 0; i < btns.length; i++){
            if(btns[i].getText() == " "){
                filled = false;
            }
        }
    return filled;
    }
    public void checkWin(Button bts[]){
        String[][] box = {{bts[0].getText(),bts[1].getText(),bts[2].getText()},{bts[3].getText(),bts[4].getText(),bts[5].getText()},{bts[6].getText(),bts[7].getText(),bts[8].getText()}};

        // if(isFilled(bts)){

            if(checkInside(box[0][0],box[0][1],box[0][2]) || checkInside(box[1][0],box[1][1],box[1][2]) || 
               checkInside(box[2][0],box[2][1],box[2][2]) || checkInside(box[0][0],box[1][0],box[2][0]) ||
               checkInside(box[0][1],box[1][1],box[2][1]) || checkInside(box[0][2],box[1][2],box[2][2]) ||
               checkInside(box[0][0],box[1][1],box[2][2]) || checkInside(box[0][2],box[1][1],box[2][0])  ){

                stage.setScene(youwonScene);
            } 
        // }
    }
    public boolean checkInside(String x, String y,String z){
        boolean same = false;
       
        if(!(x.equals(" "))){
            if(x.equals(y) && x.equals(z)){
                same = true;
                winner = x;

                if(winner.equals("X")){
                    theWinner.setText("Player 1 Is The Winner");
                    
                }else{
                    theWinner.setText(" Player 2 Is The Winner");
                }
               
            }
        }
        return same;

    }
    public void reset(Button btns[]){
        for(int i = 0; i < 9; ++i){
            btns[i].setText(" ");
        }
        counter = 0;
        inside = "X";
    }
    
}

//package mostbasicjavafxmove;
import java.util.ArrayList;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import javafx.event.ActionEvent;
import javafx.scene.shape.Shape;
import javafx.event.Event;
import javafx.scene.Node;
import javafx.scene.paint.Paint;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.Media;

/**
 * For more information see:
 *
 */
//  http://stackoverflow.com/questions/21331519/how-to-get-smooth-animation-with-keypress-event-in-javafx
// * http://docs.oracle.com/javafx/2/scenegraph/jfxpub-scenegraph.htm
//* http://stackoverflow.com/questions/15013913/checking-collision-of-shapes-with-javafx
// * https://gamedevelopment.tutsplus.com/tutorials/introduction-to-javafx-for-game-development--cms-23835
public class Final_Game extends Application {

    ArrayList<Rectangle> badblockz = new ArrayList();
    ArrayList<String> input = new ArrayList<String>();
    ArrayList<Enemy> Enemies = new ArrayList();
        ArrayList<wall> Walls = new ArrayList();
    Rectangle rect;
    Rectangle box;
    Enemy ned, E1, E2, E3, E4, E5;
    Player sam;
    Coin gold;
    Blade sword;
    ImageView CV, CV2 ,CV3, start;
    Group root, root2;
    Scene Level;
    wall W1, W2, W3, W4,W5,W6,W7 ,L1,L2, L3, L4, L5, L6, L7,L8,L9,L10,L11,L12,L13,L14;
    transition level1;
    MediaPlayer beat;
    Media media;
    boolean starting = false;
   
    public static boolean isalive = true;
  

    @Override
    public void start(Stage primaryStage) {
   
         media = new Media("file:///home/cyberknight/NetBeansProjects/JavaFXApplication1/src/hello.wav");
         beat = new MediaPlayer(media);
         beat.play();
         
         
        root = new Group();
        Level = new Scene(root);
        primaryStage.setTitle("Beta v0.6");
        primaryStage.setScene(Level);

        Canvas canvas = new Canvas(1000, 800);

               start = new ImageView("file:src/Start.png");
     //   final ImageView SS = new ImageView(start);
        start.setFitHeight(canvas.getHeight());
           start.setFitHeight(canvas.getWidth());
       
    //    start.setFitWidth(600);
        CV2 = new ImageView("file:src/Background.png");
        CV2.setFitHeight(canvas.getHeight());
        CV2.setFitWidth(canvas.getWidth());
        
             CV3 = new ImageView("file:src/Level2.png");
        CV3.setFitHeight(canvas.getHeight());
        CV3.setFitWidth(canvas.getWidth());

        //Notice gc is not being used yet 
        GraphicsContext gc = canvas.getGraphicsContext2D();

        //notice we are creating shape objects 
        box = new Rectangle(400, 520, 170, 50);
        box.setFill(Color.PLUM);

        sam = new Player(470, 50, 9, 20);
        sam.setFill(Color.GOLD);
        
        level1 = new transition(400,-10,170,50);
        level1.setFill(Color.ALICEBLUE);
        
      W1 = new wall(-200,0,600,54);
      W1.setFill(Color.TRANSPARENT);
      W2 = new wall(-50,0,100,900);
         W2.setFill(Color.TRANSPARENT);
      W3 = new wall(950,-550,100,900);
         W3.setFill(Color.TRANSPARENT);
      W4 = new wall(20,750,1200,200);
         W4.setFill(Color.TRANSPARENT);
      W5 = new wall(950,450,100,900);
         W5.setFill(Color.TRANSPARENT);
      
      W6 = new wall(570,0,500,50);
         W6.setFill(Color.TRANSPARENT);
                      
        Walls.add(W1);
        Walls.add(W2);
                Walls.add(W3);
                    Walls.add(W4);
    Walls.add(W5);
     Walls.add(W6);
        //
        ned = new Enemy(500, 0, 500);
      ned.setFill(Color.AQUAMARINE);
   E1 = new Enemy(52, 234, 4);
        E2 = new Enemy(552, 50, 4);
        E3 = new Enemy(42, 23, 4);
        E4 = new Enemy(242, 63, 4);
       E5 = new Enemy(152, 23, 4);
        Enemies.add(ned);
        Enemies.add(E1);
        Enemies.add(E2);
        Enemies.add(E3);
       Enemies.add(E4);
       Enemies.add(E5);

        rect = new Rectangle(600, 20, 25, 25);
        rect.setFill(Color.BLUE);
        gold = new Coin(300, 500, 30, 50);

        sword = new Blade(23, 340, 14, 40);
        sword.setFill(Color.TRANSPARENT);
        ned.chase(sam);

        badblockz.add(rect);

        // root = new Group();
        root2 = new Group();

        // boolean isactive = true;
        //we have created an animation timer --- the class MUST be overwritten - look below 
        AnimationTimer timer = new MyTimer();

        Level.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {

                System.out.println("Your health x is " + sam.GetX() + "sams y" + sam.GetY());
                if (event.getCode() == KeyCode.ENTER) {
                    starting = true;
                    root.getChildren().remove(start);
                }
                if (event.getCode() == KeyCode.D) { // don't use toString here!!!
                    //
                    //     sam.MoveUp();
                  // sam.setX(sam.getX() + 10);
                    sam.setFill(Color.CADETBLUE);
                    sword.FollowR(sam);
                    checkBounds(sam);
                    Goldhit(sam);
                    EnemyHit(sam);
                    sam.ImageRight(sam);
                    Swordhit(ned);
                    Changelevel(sam);
                    sam.moveright(Walls);
                    
                                

                    ///  sam.MoveUp();
                } else if (event.getCode() == KeyCode.A) {
                 sam.moveleft(Walls);
                    sam.setFill(Color.RED);
                    checkBounds(sam);
                    sword.FollowL(sam);
                    Goldhit(sam);
                    EnemyHit(sam);
                    sam.ImageLeft(sam);
                    Swordhit(ned);
                    Changelevel(sam);
                  //  WallHit(sam);

                } else if (event.getCode() == KeyCode.W) {
                 sam.moveUp(Walls);
                    sam.setFill(Color.GREEN);
                    checkBounds(sam);
                    sword.FollowU(sam);
                    Goldhit(sam);

                    sam.ImageUp(sam);
                    Swordhit(ned);
                    Changelevel(sam);

                } else if (event.getCode() == KeyCode.S) {
                  sam.moveDown(Walls);
                    sam.setFill(Color.RED);
                    checkBounds(sam);
                    Goldhit(sam);
                    //  sword.FollowL(sam);
                    EnemyHit(sam);
                    sam.ImageDown(sam);
                    sword.FollowD(sam);
                    Changelevel(sam);
                    Changelevel(sam);

                } else if (event.getCode() == KeyCode.L) {
                    Level.setRoot(root2);

                }

            }
        });

        //     if ((sam.getX() == gold.getX()) && (sam.getY() == gold.getY())){
        //        System.out.println("MAJOR ");
        //   }
        Level.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.RIGHT || event.getCode() == KeyCode.LEFT) {
                    // rectangleVelocity.set(0);
                }
                class Coin extends Rectangle {

                    public Coin() {
                    }

                    public Coin(double width, double height) {
                        super(width, height);
                    }

                    public Coin(double width, double height, Paint fill) {
                        super(width, height, fill);
                    }

                    public Coin(double x, double y, double width, double height) {
                        super(x, y, width, height);
                    }

                }
            }

            class Coin extends Rectangle {

                public Coin() {
                }

                public Coin(double width, double height) {
                    super(width, height);
                }

                public Coin(double width, double height, Paint fill) {
                    super(width, height, fill);
                }

                public Coin(double x, double y, double width, double height) {
                    super(x, y, width, height);
                }

            }
        });

        //try disabling canvas --- notice the difference 
        root.getChildren().add(canvas);
  
           root.getChildren().add(sam);
        root.getChildren().add(CV2);

        root.getChildren().add(rect);
        //   root.getChildren().add(start);
     //   root.getChildren().add(box);

      //  root.getChildren().add(sam);

        // root.getChildren().add(ned);
        timer.start();
        primaryStage.show();
        root.getChildren().add(gold.CModel);

        gold.setFill(Color.GOLD);
        root.getChildren().add(gold);
        root.getChildren().add(sword);
        root.getChildren().add(sam.Model);
                  
        // root.getChildren().add(ned.EModel);
        root.getChildren().add(sword.SModel);
        for (Enemy e : Enemies) {
            root.getChildren().add(e);
            root.getChildren().add(e.EModel);
            
            //  root.getChildren().add(level1);
            EnemyHit(sam);
             

        //root2.getChildren().add(sam);
     
        }
          for (wall w : Walls) {
            root.getChildren().add(w);
          }
              root.getChildren().add(start);
    }

    ///  vvvvvvvvvvvv   MAIN vvvvvvvvvvv
    public static void main(String[] args) {

        launch(args);

    }

    //// ^^^^^^^^^^^  MAIN ^^^^^^^^^^^^^
    // we create our time here --- to animate 
    private class MyTimer extends AnimationTimer {

        boolean movedown = true;

        @Override
        public void handle(long now) {  // =================================================================================================================================
         //    ned.chase(sam);
         
                 
              for (wall w : Walls) {
           
            //sam.WallHit(Walls);
              }
//Changelevel(sam);
              if (starting == true){
            for (Enemy e : Enemies) {
                e.chase(sam);
                Swordhit(e);
                EnemyHit(sam);// ============================================================================ Change The method to Enemies instead of ned
            }
            }
            doHandle();

        
        }

        private void doHandle() {
            checkBounds(box);
            if (movedown && rect.getY() < 555) {
                rect.setY(rect.getY() + 10);
            }
            if (!movedown && rect.getY() > 1) {
                rect.setY(rect.getY() - 10);
            }
            if (rect.getY() > 555) {
                movedown = false;
            }
            if (rect.getY() < 1) {
                movedown = true;
            }
            if (sam.health < 1) {
                System.exit(0);
            }

            // stop();
            // System.out.println("Animation stopped");
        }

    }

    private void checkBounds(Rectangle box) {
        // checkBounds is called in two different locations --- it's really only necessary in the animation dohandle
        // experiment - check the differences

        boolean collisionDetected = false;

        // notice the difference in how an ArrayList iterates through items 
        for (Rectangle badblock : badblockz) {
            if (box.getBoundsInParent().intersects(badblock.getBoundsInParent())) {
                collisionDetected = true;
                badblock.setFill(Color.RED);
            } else {
                badblock.setFill(Color.BLUE);
            }
        }
        if (collisionDetected) {
            box.setFill(Color.PURPLE);
        } else {
            box.setFill(Color.BLACK);
        }

    }
    
  
    private void Changelevel(Player sam) {
        boolean nextlevel = false;

        if (sam.getBoundsInParent().intersects(level1.getBoundsInParent())) {
            nextlevel = true;

        }

        if (nextlevel) {
           
            try {
            
                Level.setRoot(root2);
                      root2.getChildren().add(CV3);
                     
                sam.setX(200);
                sam.setY(300);
                 L1 = new wall(640,330,230,50);
                    L1.setFill(Color.TRANSPARENT);
                   L2 = new wall(185,300,230,50);
                      L2.setFill(Color.TRANSPARENT);
                     L3 = new wall(765,110,230,50);
                        L3.setFill(Color.TRANSPARENT);
                       L4 = new wall(135,130,230,50);
                          L4.setFill(Color.TRANSPARENT);
                                  L5 = new wall(360,130,50,230);
                                     L5.setFill(Color.TRANSPARENT);
                                      L6 = new wall(760,130,50,230);
                                         L6.setFill(Color.TRANSPARENT);
                     L7 = new wall(650,375,50,230);
                        L7.setFill(Color.TRANSPARENT);
                       L8 = new wall(190,350,50,230);
                          L8.setFill(Color.TRANSPARENT);
                            L9 = new wall(350,500,50,230);
                               L9.setFill(Color.TRANSPARENT);
                             L10 = new wall(480,40,230,50);
                                L10.setFill(Color.TRANSPARENT);
                                                          L11 = new wall(330,70,230,50);
                                                             L11.setFill(Color.TRANSPARENT);
                Walls.add(L1);
                  Walls.add(L2);
                   Walls.add(L3);
                  Walls.add(L4);
                       Walls.add(L5);
                     Walls.add(L6);
                        Walls.add(L7);
                            Walls.add(L8);
                               Walls.add(L9);
                                Walls.add(L10);
                                 Walls.add(L11);
               
                    root2.getChildren().add(gold);
                      root2.getChildren().add(L1);
                        root2.getChildren().add(L2);
                              root2.getChildren().add(L3);
                               root2.getChildren().add(L4);
                                 root2.getChildren().add(L5);
                                    root2.getChildren().add(L6);
                                         root2.getChildren().add(L7);
                                                    root2.getChildren().add(L8);
                                                        root2.getChildren().add(L9);
                                                            root2.getChildren().add(L10);
                                                               root2.getChildren().add(L11);
        root2.getChildren().add(sword);
        root2.getChildren().add(sam.Model);
           //  root2.getChildren().add(sam);
           
             Enemies.removeAll(Enemies);
             
             
     
        
                System.out.println("Level switch");
                ;
                  
            } catch (Exception e) {
                System.out.println("This broke ");
            }

        }
    }

    private void Goldhit(Player sam) {
        boolean Goldhits = false;
        if (sam.getBoundsInParent().intersects(gold.getBoundsInParent())) {

            System.out.println("You Got Some Gold");
            Goldhits = true;
        }
        if (Goldhits) {
            sam.setFill(Color.TOMATO);
            gold.setFill(Color.TRANSPARENT);
            sam.score += 1;
            System.out.println("Your score is " + sam.score);
            gold.setX(300);

            //    sam.score = sam.score - 1;
        }
    }

    public void Isalive(boolean isalive) {

        if (sam.health < 1) {
            isalive = false;

        }
        if (isalive = false) {

        }

    }
 //private boolean WallHit(ArrayList<wall> a){
 //      boolean stop = false;
  //      for (wall w: a){
   //       if (sam.getBoundsInParent().intersects(w.getBoundsInParent())) {
   //            stop = true;
  //             System.out.println("HIT");
   //            sam.setX(sam.GetX() - 10); 
      
    private void Swordhit(Enemy ned) {
        boolean nedhit = false;
        if (sword.getBoundsInParent().intersects(ned.getBoundsInParent())) {
            System.out.println("You stabbed ned");
            nedhit = true;
        }
        if (nedhit) {
            //  sam.setFill(Color.TOMATO);
            ned.setFill(Color.TRANSPARENT);
            ned.health = ned.health - 20000;
            System.out.println("THIS IS NEDS HEALTH" + ned.health);

        }
    }

    private void EnemyHit(Player sam) {
        boolean Enemyhit = false;
        for (Enemy e : Enemies) {
            if (sam.getBoundsInParent().intersects(e.getBoundsInParent())) {
                System.out.println("Ouch you got hit by an enemy");

                Enemyhit = true;

            }
            if (Enemyhit) {
                sam.setFill(Color.DARKORCHID);
                sam.health = sam.health - 1;

            }
        }
    }
}

abstract class Items {

    int x;
    int y;

    public Items(int x, int y) {
        this.x = x;
        this.y = y;

    }

}

abstract class Humanoid extends Rectangle {

    double x;
    double y;
    int health;

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void moveup() {
        this.y = this.y - 1;
    }

    public void moveleft() {
        this.x = this.x - 1;
    }

    public void moveright() {
        this.x = this.x + 1;

    }

    public void movedown() {
        this.y = this.y + 1000;
    }
}

class Player extends Rectangle {

    Image CV = new Image("file:src/Down1.png");
    Image UP = new Image("file:src/Up1.png");
    Image RIGHT = new Image("file:src/Right1.png");
    Image LEFT = new Image("file:src/Left1.png");

    // int x = this.GetX();
    //  int y = this.GetY();
    public ImageView Model = new ImageView(CV);
    int health = 4;
    int score = 0;

    public Player(double x, double y) {
        super(x, y, 30, 30);
    }

    public Player(int health) {
        this.health = health;
    }

    public Player() {
        this.Model.setX(this.GetX());
        this.Model.setY(this.GetY());
        this.Model.setFitHeight(90);
        this.Model.setFitWidth(90);
    }
      private boolean WallHit(ArrayList<wall> b){
       boolean stop = false;
        for (wall w: b){
          if (this.getBoundsInParent().intersects(w.getBoundsInParent())) {
               stop = true;
               System.out.println("HIT");
               this.setX(this.GetX() - 10); 
          }
        }
          return stop ;
    }
     void moveright(ArrayList<wall> b){
                this.setX(this.GetX() + 10); 
  
    //   if (this.getBoundsInParent().intersects(b.getBoundsInParent())) {
        //   this.setX(this.GetX() - 10);
     //  }
          
         if (this.WallHit(b)){
               this.setX(this.GetX()-10);
           }
        
         
     
     }
      void moveleft(ArrayList<wall> b){
         this.setX(this.GetX() - 10); 
          if (this.WallHit(b)){
               this.setX(this.GetX()+30);
           }
        
     }
       void moveUp(ArrayList<wall> b){
         this.setY(this.GetY() - 10);
          if (this.WallHit(b)){
               this.setY(this.GetY()+10);
           }
        
     }
          void moveDown(ArrayList<wall> b){
         this.setY(this.GetY() + 10);
          if (this.WallHit(b)){
               this.setY(this.GetY()-10);
           }
        
     }


    //  public Player(double width, double height) {
    //    super(width, height);
    //   this.Model.setX(this.GetX());
    //     this.Model.setY(this.GetY());
    //    this.Model.setFitHeight(90);
    //   this.Model.setFitWidth(90);
    // }
    public Player(double width, double height, Paint fill) {
        super(width, height, fill);
    }

    public Player(double x, double y, double width, double height) {
        super(x, y, width, height);
    }

    public int GetX() {
        return (int) this.getX();
    }

    public int GetY() {
        return (int) this.getY();
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void ImageDown(Player sam) {

        Model.setImage(CV);
        this.Model.setX(this.GetX() - 10);
        this.Model.setY(this.GetY() - 10);
    }

    public void ImageUp(Player sam) {
        Model.setImage(UP);
        this.Model.setX(this.GetX() - 10);
        this.Model.setY(this.GetY() - 10);

    }

    public void ImageRight(Player sam) {
        Model.setImage(RIGHT);
        this.Model.setX(this.GetX() - 10);
        this.Model.setY(this.GetY() - 10);

    }

    public void ImageLeft(Player sam) {
        Model.setImage(LEFT);
        this.Model.setX(this.GetX() - 10);
        this.Model.setY(this.GetY() - 10);

    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

}

class Enemy extends Rectangle {

    Image ED = new Image("file:src/EDown.png");
    Image EU = new Image("file:src/EUp.png");
    Image ER = new Image("file:src/ERight.png");
    Image EL = new Image("file:src/ELeft.png");
    Image Edead = new Image("file:src/EDead.png");

    public ImageView EModel = new ImageView(ED);

    Rectangle myrectangle;
    int health;

    public Enemy(double x, double y, int hp) {
        super(x, y, 20, 20);

        health = hp;

        this.setFill(Color.VIOLET);

        this.health = hp;
        this.EModel.setX(this.getX());
        this.EModel.setY(this.GetY());
        this.EModel.setFitHeight(90);
        this.EModel.setFitWidth(90);

    }

    public void moveup() {

        this.setY(this.getY() - 0.000002);
    }

    public void moveleft() {

        this.setX(this.getX() - 0.0000002);
    }

    public void moveright() {
        this.setX(this.getX() + 0.00000002);

    }

    public void movedown() {
        this.setY(this.getY() + 0.0000002);
    }

    public void chase(Player p) {

        boolean Eisalive = true;
        if (this.health < 1) {
            Eisalive = false;
            EModel.setImage(Edead);
        }

        if (Eisalive) {
            if (p.GetX() < this.getX()) {
                this.moveleft();
                this.setX(this.getX() - 1);
                EModel.setImage(EL);
                this.EModel.setX(this.getX());
                this.EModel.setY(this.GetY());

            } else if (p.getX() > this.getX()) {
                this.setX(this.getX() + 1);
                EModel.setImage(ER);
                this.EModel.setX(this.getX() - 25);
                this.EModel.setY(this.GetY() - 25);

            }
            if (p.getY() < this.getY()) {
                this.moveup();
                this.setY(this.getY() - 1);
                EModel.setImage(EU);
                this.EModel.setX(this.getX() - 25);
                this.EModel.setY(this.GetY() - 25);
            }
            if (p.getY() > this.getY()) {
                this.setY(this.getY() + 1);
                this.movedown();
                EModel.setImage(ED);
                this.EModel.setX(this.getX() - 25);
                this.EModel.setY(this.GetY() - 25);
            }

        }
    }

    public int GetY() {
        return (int) this.getY();
    }

}

class Coin extends Rectangle {

    Image CD = new Image("file:src/Coin.png");

    public ImageView CModel = new ImageView(CD);

    public Coin() {
        this.CModel.setX(this.getX());
        this.CModel.setY(this.getY());
        this.CModel.setFitHeight(90);
        this.CModel.setFitWidth(90);
        CModel.setImage(CD);
    }

    public Coin(double width, double height) {
        super(width, height);
    }

    public Coin(double width, double height, Paint fill) {
        super(width, height, fill);
    }

    public Coin(double x, double y, double width, double height) {
        super(x, y, width, height);
    }

}

class Blade extends Rectangle {

    public boolean Good = false;
    // private Object event;

    Image BU = new Image("file:src/SwordUp.png");
    Image BD = new Image("file:src/SwordDown.png");

    public ImageView SModel = new ImageView(BU);

    public Blade() {
        this.SModel.setX(this.getX());
        this.SModel.setY(this.getY());
        this.SModel.setFitHeight(800);
        this.SModel.setFitWidth(90);
    }

    public Blade(double width, double height) {
        super(width, height);
    }

    public Blade(double width, double height, Paint fill) {
        super(width, height, fill);
    }

    public Blade(double x, double y, double width, double height) {
        super(x, y, width, height);
    }

    public void FollowR(Player sam) {
        this.setX(sam.GetX() + 15);
        this.setY(sam.GetY());
        SModel.setImage(BU);
        this.SModel.setX(this.getX() - 0);
        this.SModel.setY(this.getY() - 10);

    }

    public void FollowL(Player sam) {
        this.setX(sam.GetX() - 15);
        this.setY(sam.GetY());
        SModel.setImage(BU);
        this.SModel.setX(this.getX() - 0);
        this.SModel.setY(this.getY() - 10);

    }

    public void FollowU(Player sam) {
        this.setX(sam.GetX());
        this.setY(sam.GetY() - 25);
        SModel.setImage(BU);
        this.SModel.setX(this.getX() - 0);
        this.SModel.setY(this.getY() - 10);

    }

    public void FollowD(Player sam) {
        this.setX(sam.GetX() + 3);
        this.setY(sam.GetY() + 20);
        SModel.setImage(BD);
        this.SModel.setX(this.getX() - 7);
        this.SModel.setY(this.getY() + 10);

    }

    public void Attack() {

        while (Good) {
            //  this.getY() = this.getY() - 10;

            System.out.println("ITS ACTIVE");
        }

    }
}

class wall extends Rectangle{

    public wall() {
    }

    public wall(double width, double height) {
        super(width, height);
    }

    public wall(double width, double height, Paint fill) {
        super(width, height, fill);
    }

    public wall(double x, double y, double width, double height) {
        super(x, y, width, height);
    }
    
}

class transition extends Rectangle{

    public transition() {
    }

    public transition(double width, double height) {
        super(width, height);
    }

    public transition(double width, double height, Paint fill) {
        super(width, height, fill);
    }

    public transition(double x, double y, double width, double height) {
        super(x, y, width, height);
    }
    
} 

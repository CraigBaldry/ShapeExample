/**
 * 2D Shapes Demonstration
 * 2018
 */
package pkg2dshapes;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/**
 * @author Craig Baldry 
 * @version 1.00 30th August 2018
 */
public class ShapeController implements Initializable {
    int objectCount = 0;                //Count how many shapes made
    @FXML
    private Text count;                 //Displays the Count
    Color randomColor;                  //Random Colors for our shapes
    @FXML
    private Text status;                //Displays the x,y coordinates
    @FXML
    private Canvas myCanvas;            //The Canvas where we will draw shapes
    private GraphicsContext gc;         
    @FXML
    private Button btnCLear;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        gc = myCanvas.getGraphicsContext2D();      
        
        //Draws a shape at the location of the mouse pointer on click
        myCanvas.setOnMousePressed((MouseEvent event) -> {
            double x = event.getX();
            double y = event.getY();
            
            //If primary button is clicked make a circle
            if (event.isPrimaryButtonDown()) {
                drawCircle(gc, x, y);
                objectCount++;
                System.out.println("Circle");
                
            }
            
            //If secondary button is clicked draw a square
            if (event.isSecondaryButtonDown()) {
                drawRectangle(gc, x, y);
                objectCount++;
                System.out.println("Rectangle");
            }
            //This is how many shapes were created
            count.setText(String.valueOf(objectCount));
        });
        
        //When button is pressed clears the canvas
        btnCLear.setOnAction(
                (event) -> {
                    reset(myCanvas, Color.WHITE);
                    objectCount = 0;
                    count.setText(String.valueOf(objectCount));
                });
    }
    
    //Clears the canvas
    private void reset(Canvas canvas, Color color) {
        GraphicsContext graphicContext = canvas.getGraphicsContext2D();
        graphicContext.setFill(color);
        graphicContext.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }
    
    //Creates a random color for fill
    private Color randomColor() {
        Random rand = new Random();
        float r = rand.nextFloat();
        float g = rand.nextFloat();
        float b = rand.nextFloat();

        randomColor = new Color(r, g, b, 1);
        return randomColor;
    }
    
    //Displays the cordinates of x and y
    @FXML
    private void displayPostion(MouseEvent event) {
        status.setText("X = " + event.getX() + ". Y = " + event.getY() + ".");
    }
    
    //Draws a cirlce on the canvas
    private void drawCircle(GraphicsContext gc, double x, double y) {
        GraphicsContext gc1 = myCanvas.getGraphicsContext2D();
        gc1.setFill(randomColor());
        gc1.fillOval(x - 20, y - 20, 40, 40);
    }

    //draws a square on canvas
    private void drawRectangle(GraphicsContext gc, double x, double y) {
        GraphicsContext gc2 = myCanvas.getGraphicsContext2D();
        gc2.setFill(randomColor());
        gc2.fillRect(x - 20, y - 20, 40, 40);
    }
}

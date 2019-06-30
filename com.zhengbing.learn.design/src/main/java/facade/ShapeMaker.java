package facade;

/**
 * create by zhengbing on 2019-06-30.
 * email mydreambing@126.com
 * since version 1.0
 */
public class ShapeMaker {
    
    private Shape circle;
    private Shape rectangle;
    private Shape square;

    public ShapeMaker() {
        circle = new Circle();
        rectangle = new Rectangle();
        square = new Square();
    }

    public void drawCircle() {
        circle.draw();
    }

    public void drawRectangle() {
        rectangle.draw();
    }

    public void drawSquare() {
        square.draw();
    }
}

package facade;

/**
 * @author zhengbing
 * @date 2019-06-30.
 * @email mydreambing@126.com
 * @since version 1.0
 */
public class Main {
    public static void main(String[] args) {
        // 对于客户端来说，只用关心具体功能实现，具体业务如何复杂的实现不用去管，由外观模式自动隐藏起来
        ShapeMaker shapeMaker = new ShapeMaker();
        shapeMaker.drawCircle();
        shapeMaker.drawRectangle();
        shapeMaker.drawSquare();
    }
}


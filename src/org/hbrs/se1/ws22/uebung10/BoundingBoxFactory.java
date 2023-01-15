package org.hbrs.se1.ws22.uebung10;

public class BoundingBoxFactory {
    public static MyPrettyRectangle createBoundingBox(MyPrettyRectangle[] rectangles) {
        double x1 = 0.0;
        double y1 = 0.0;
        double x2 = 0.0;
        double y2 = 0.0;
        if(rectangles == null) {
            return null;
        } else if(rectangles.length == 0) {
            return new MyPrettyRectangle(x1, y1, x2, y2);
        } else {
            for (int i = 1; i < rectangles.length; i++) {
                if(rectangles[i].getX1() <= rectangles[i-1].getX1()) {
                        x1 = rectangles[i].getX1();
                }
                if(rectangles[i].getY1() <= rectangles[i-1].getY1()) {
                    y1 = rectangles[i].getY1();
                }
                if(rectangles[i].getX2() >= rectangles[i-1].getX2()) {
                    x2 = rectangles[i].getX2();
                }
                if(rectangles[i].getY2() <= rectangles[i-1].getY2()) {
                    y2 = rectangles[i].getY2();
                }
            }
        }
        return new MyPrettyRectangle(x1,y1,x2,y2);
    }
}

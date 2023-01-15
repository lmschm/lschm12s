package org.hbrs.se1.ws22.uebung10;

public class MyPrettyRectangle {

    private double x1;
    private double y1;
    private double x2;
    private double y2;

    public MyPrettyRectangle(double x1, double y1, double x2, double y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    public double getX1() {
        return this.x1;
    }

    public double getY1() {
        return this.y1;
    }

    public double getX2() {
        return this.x2;
    }

    public double getY2() {
        return this.y2;
    }

    public boolean contains(MyPrettyRectangle rectangle) {
        boolean erg = false;
        if((rectangle.getY1() >= this.getY1()) & (rectangle.getY2() <= this.getY2()) &
            (rectangle.getX1() >= this.getX1()) & (rectangle.getX2() <= this.getX2())) {
            erg = true;
        }
        return erg;
    }

    public MyPoint getCenter() {
        double x = (x1 + x2)/2;
        double y = (y1 + y2)/2;
        return new MyPoint(x,y);
    }

    public double getArea() {
        return ((this.getX2()-this.getX1()) * (this.getY2()-this.getY1()));
    }

    public double getPerimeter() {
        return ((2*(this.getX2()-this.getX1())) + (2*(this.getY2()-this.getY1())));
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) {
            return false;
        } else if(!(obj instanceof MyPrettyRectangle)) {
            return false;
        }
        MyPrettyRectangle myrect = (MyPrettyRectangle) obj;
        if((this.x1 == myrect.x1) & (this.y1 == myrect.y1) & (this.x2 == myrect.getX2()) &
                (this.y2 == myrect.getY2())) {
            return true;
        } else {
            return false;
        }

    }
}

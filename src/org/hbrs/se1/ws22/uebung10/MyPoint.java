package org.hbrs.se1.ws22.uebung10;

public class MyPoint {

    private double x;
    private double y;
    public MyPoint(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) {
            return false;
        } else if(!(obj instanceof MyPoint)) {
            return false;
        }
        MyPoint myPoint = (MyPoint) obj;
        if((this.x == myPoint.getX()) & (this.y == myPoint.getY())) {
            return true;
        } else {
            return false;
        }
    }
}

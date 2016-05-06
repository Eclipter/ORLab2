package entity;

import java.util.Objects;

/**
 * Created by USER on 21.04.2016.
 */
public class Node {

    private Point3D pointA;
    private Point3D pointB;
    private Point3D pointC;
    private Point3D pointD;
    private double key;

    public Node(Point3D pointA, Point3D pointB, Point3D pointC, Point3D pointD) {
        this.pointA = pointA;
        this.pointB = pointB;
        this.pointC = pointC;
        this.pointD = pointD;
        this.key = Double.MAX_VALUE;
    }

    public Point3D getPointA() {
        return pointA;
    }

    public void setPointA(Point3D pointA) {
        this.pointA = pointA;
    }

    public Point3D getPointB() {
        return pointB;
    }

    public void setPointB(Point3D pointB) {
        this.pointB = pointB;
    }

    public Point3D getPointC() {
        return pointC;
    }

    public void setPointC(Point3D pointC) {
        this.pointC = pointC;
    }

    public Point3D getPointD() {
        return pointD;
    }

    public void setPointD(Point3D pointD) {
        this.pointD = pointD;
    }

    public double getKey() {
        return key;
    }

    public void setKey(double key) {
        this.key = key;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Node)) return false;
        Node node = (Node) o;
        return Objects.equals(pointA, node.pointA) &&
                Objects.equals(pointB, node.pointB) &&
                Objects.equals(pointC, node.pointC) &&
                Objects.equals(pointD, node.pointD);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pointA, pointB, pointC, pointD);
    }
}

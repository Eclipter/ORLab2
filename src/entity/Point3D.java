package entity;

import java.util.Objects;

/**
 * Created by USER on 21.04.2016.
 */
public class Point3D {

    private double x;
    private double y;
    private double z;
    private double temp;

    public Point3D(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Point3D)) return false;
        Point3D point3D = (Point3D) o;
        return Double.compare(point3D.getX(), getX()) == 0 &&
                Double.compare(point3D.getY(), getY()) == 0 &&
                Double.compare(point3D.getZ(), getZ()) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getX(), getY(), getZ());
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }
}

package entity;

import java.util.List;

/**
 * Created by USER on 21.04.2016.
 */
public class Graph {

    private List<Point3D> pointList;
    private List<Node> nodeList;
    private double[][] matrix;

    public Graph(List<Point3D> pointList, List<Node> nodeList, double[][] matrix) {
        this.pointList = pointList;
        this.nodeList = nodeList;
        this.matrix = matrix;
    }

    public List<Point3D> getPointList() {
        return pointList;
    }

    public void setPointList(List<Point3D> pointList) {
        this.pointList = pointList;
    }

    public List<Node> getNodeList() {
        return nodeList;
    }

    public void setNodeList(List<Node> nodeList) {
        this.nodeList = nodeList;
    }

    public double[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(double[][] matrix) {
        this.matrix = matrix;
    }
}

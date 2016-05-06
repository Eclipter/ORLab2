package util;

import entity.Graph;
import entity.Node;
import entity.Point3D;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by USER on 21.04.2016.
 */
public class VTKParser {

    public Graph parse(String filename) throws IOException {
        List<Point3D> pointList = new ArrayList<>();
        List<Node> nodeList = new ArrayList<>();
        List<Double> tempList = new ArrayList<>();

        Path path = Paths.get(filename);
        List<String> lines = Files.readAllLines(path);

        for(int i = 0 ; i < lines.size(); i++) {
            if(lines.get(i).startsWith("POINTS")) {
                i++;
                while(!"".equals(lines.get(i))) {
                    String[] coords = lines.get(i).split(" ");
                    pointList.add(new Point3D(Double.parseDouble(coords[0]),
                            Double.parseDouble(coords[1]), Double.parseDouble(coords[2])));
                    i++;
                }
            }
            if(lines.get(i).startsWith("CELLS")) {
                i++;
                while(!"".equals(lines.get(i))) {
                    String[] tokens = lines.get(i).split(" ");
                    nodeList.add(new Node(pointList.get(Integer.parseInt(tokens[1])),
                            pointList.get(Integer.parseInt(tokens[2])),
                            pointList.get(Integer.parseInt(tokens[3])),
                            pointList.get(Integer.parseInt(tokens[4]))));
                    i++;
                }
            }
            if(lines.get(i).startsWith("LOOKUP_TABLE")) {
                i++;
                while(!"".equals(lines.get(i))) {
                    tempList.add(Double.parseDouble(lines.get(i)));
                    i++;
                }
            }
        }

        double[][] matrix = new double[nodeList.size()][nodeList.size()];

        for(int i = 0; i < nodeList.size(); i++) {
            for(int j = 0; j < nodeList.size(); j++) {
                matrix[i][j] = -1;
            }
        }

        for(int i = 0; i < nodeList.size(); i++) {
            List<Point3D> pointAList = new ArrayList<>();
            pointAList.add(nodeList.get(i).getPointA());
            pointAList.add(nodeList.get(i).getPointB());
            pointAList.add(nodeList.get(i).getPointC());
            pointAList.add(nodeList.get(i).getPointD());
            for(int j = 0; j < nodeList.size(); j++) {
                List<Point3D> pointBList = new ArrayList<>();
                pointBList.add(nodeList.get(j).getPointA());
                pointBList.add(nodeList.get(j).getPointB());
                pointBList.add(nodeList.get(j).getPointC());
                pointBList.add(nodeList.get(j).getPointD());

                List<Point3D> samePoints = pointBList.stream().filter(pointAList::contains).collect(Collectors.toList());
                if(samePoints.size() == 3) {
                    matrix[i][j] = (tempList.get(pointList.indexOf(samePoints.get(0)))
                            + tempList.get(pointList.indexOf(samePoints.get(1)))
                            + tempList.get(pointList.indexOf(samePoints.get(2)))) / 3;
                }
            }
        }
        return new Graph(pointList, nodeList, matrix);
    }


    public void printToEleFile(Graph graph) throws IOException {
        List<String> strings = new ArrayList<>();
        strings.add(graph.getNodeList().size() + " " + 4 + " " + 1);
        for(int i = 0; i < graph.getNodeList().size(); i++) {
            Node node = graph.getNodeList().get(i);
            strings.add(String.format("%d %d %d %d %d %d", i + 1, graph.getPointList().indexOf(node.getPointA()) + 1,
                    graph.getPointList().indexOf(node.getPointB()) + 1,
                    graph.getPointList().indexOf(node.getPointC()) + 1,
                    graph.getPointList().indexOf(node.getPointD()) + 1,
                    (int) node.getKey()));
        }
        Path path = Paths.get("lab2.ele");
        Files.write(path, strings);
    }

}
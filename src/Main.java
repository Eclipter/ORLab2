import entity.Graph;
import util.PrimImpl;
import util.VTKParser;

import java.io.IOException;

/**
 * Created by USER on 21.04.2016.
 */
public class Main {

    public static void main(String[] args) {
        VTKParser vtkParser = new VTKParser();

        try {
            Graph graph = vtkParser.parse("tn0.vtk");
            PrimImpl.findTree(graph, 12); //указать свой номер треугольника в качестве корня
            vtkParser.printToEleFile(graph);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package util;

import entity.Graph;
import entity.Node;

import java.util.*;

/**
 * Created by USER on 26.04.2016.
 */
public class PrimImpl {

    public static void findTree(Graph graph, int rootNumber) {
        int n = graph.getNodeList().size();
        int[] prev = new int [n];
        for(int i = 0; i < n; i++) {
            prev[i] = -1;
        }
        List<Node> priorityQueue = new LinkedList<>();
        graph.getNodeList().get(rootNumber).setKey(0);
        priorityQueue.addAll(graph.getNodeList());
        while (!priorityQueue.isEmpty()) {
            Node v = priorityQueue.stream().min(Comparator.comparing(Node::getKey)).get();
            priorityQueue.remove(v);
            int vInd = graph.getNodeList().indexOf(v);
            for(int j = 0; j < n; j++) {
                if(graph.getMatrix()[vInd][j] >= 0) {
                    Node u = graph.getNodeList().get(j);
                    if(priorityQueue.contains(u) && graph.getMatrix()[vInd][j] < u.getKey()) {
                        u.setKey(graph.getMatrix()[vInd][j]);
                        prev[j] = vInd;
                    }
                }
            }
        }

        double maxMark = 0;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(rootNumber);
        graph.getNodeList().get(rootNumber).setKey(0);
        while(!queue.isEmpty()) {
            Integer nodeIndex = queue.poll();
            for(int i = 0; i < prev.length; i++) {
                if(prev[i] == nodeIndex) {
                    graph.getNodeList().get(i).setKey(graph.getNodeList().get(nodeIndex).getKey() + 1);
                    queue.add(i);

                    if(graph.getNodeList().get(i).getKey() > maxMark) {
                        maxMark = graph.getNodeList().get(i).getKey();
                    }
                }
            }
        }

        final double finalMaxMark = maxMark;
        graph.getNodeList().forEach(node -> {
            if(node.getKey() < finalMaxMark / 4.0) {
                node.setKey(0);
            }
            else if(node.getKey() < finalMaxMark / 2.0) {
                node.setKey(1);
            }
            else if(node.getKey() < finalMaxMark * 0.75) {
                node.setKey(2);
            }
            else {
                node.setKey(3);
            }
        });
    }
}

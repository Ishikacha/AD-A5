package application;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.*;

import supportStuff.applicationSupport.Coordinate;

public class PathFinder implements PathFinder_I, Serializable {

    private final Maze maze;

    public PathFinder(final Maze maze) {
        this.maze = maze;
    }

    @Override
    public List<Coordinate> getShortestPath() {
        List<Coordinate> shortestPathBFS = breadthFirstSearch();
        if (shortestPathBFS != null) {
            return shortestPathBFS;
        }
        return depthFirstSearch();
    }

    private List<Coordinate> breadthFirstSearch() {
        Queue<List<Coordinate>> queue = new LinkedList<>();
        Set<Coordinate> visited = new HashSet<>();
        Coordinate start = maze.getStart();
        Coordinate destination = maze.getDestination();

        queue.add(new ArrayList<>(Arrays.asList(start)));

        while (!queue.isEmpty()) {
            List<Coordinate> path = queue.poll();
            Coordinate current = path.get(path.size() - 1);

            if (current.equals(destination)) {
                return path;
            }

            for (Coordinate neighbor : getValidNeighbors(current)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    List<Coordinate> newPath = new ArrayList<>(path);
                    newPath.add(neighbor);
                    queue.add(newPath);
                }
            }
            path.clear(); // Entfernen des Referenzpfads, um Speicher zu sparen
        }

        return null; // Kein Weg gefunden
    }

    private List<Coordinate> depthFirstSearch() {
        Stack<Coordinate> stack = new Stack<>();
        Set<Coordinate> visited = new HashSet<>();
        Coordinate start = maze.getStart();
        Coordinate destination = maze.getDestination();

        stack.push(start);

        while (!stack.isEmpty()) {
            Coordinate current = stack.pop();

            if (current.equals(destination)) {
                return getPathFromVisited(visited, start, destination);
            }

            if (!visited.contains(current)) {
                visited.add(current);

                for (Coordinate neighbor : getValidNeighbors(current)) {
                    if (!visited.contains(neighbor)) {
                        stack.push(neighbor);
                    }
                }
            }
        }

        return null;
    }

    private List<Coordinate> getValidNeighbors(Coordinate coordinate) {
        int[][] mazeField = maze.getMazeField();
        int x = coordinate.getX();
        int y = coordinate.getY();
        int numRows = mazeField.length;
        int numCols = mazeField[0].length;

        List<Coordinate> neighbors = new ArrayList<>();

        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // Oben, Unten, Links, Rechts

        for (int[] dir : directions) {
            int newX = x + dir[0];
            int newY = y + dir[1];

            if (newX >= 0 && newX < numRows && newY >= 0 && newY < numCols
                    && mazeField[newX][newY] != Integer.MIN_VALUE) {
                neighbors.add(new Coordinate(newX, newY));
            }
        }

        return neighbors;
    }

    private List<Coordinate> getPathFromVisited(Set<Coordinate> visited, Coordinate start, Coordinate destination) {
        List<Coordinate> path = new ArrayList<>();
        path.add(destination);
        Coordinate current = destination;

        while (!current.equals(start)) {
            for (Coordinate neighbor : visited) {
                if (neighbor.isNeighbor(current)) {
                    path.add(neighbor);
                    current = neighbor;
                    break;
                }
            }
        }

        Collections.reverse(path);
        return path;
    }
}
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

// Klasse zur Durchführung der Pfadsuche im Labyrinth
public class PathFinder implements PathFinder_I, Serializable {

    private final Maze maze; // Das Labyrinth, in dem der Pfad gesucht wird

    // Konstruktor, der das Labyrinth für die Pfadsuche initialisiert
    public PathFinder(final Maze maze) {
        this.maze = maze;
    }

    // Hauptmethode, um den kürzesten Pfad zu finden
    @Override
    public List<Coordinate> getShortestPath() {
        // Versuche zuerst Breitensuche (BFS)
        List<Coordinate> shortestPathBFS = breadthFirstSearch();
        if (shortestPathBFS != null) {
            return shortestPathBFS;
        }
        // Wenn BFS keinen Pfad findet, verwende Tiefensuche (DFS)
        return depthFirstSearch();
    }

    // Implementierung der Breitensuche (BFS)
    private List<Coordinate> breadthFirstSearch() {
        Queue<List<Coordinate>> queue = new LinkedList<>(); // Warteschlange für Pfade
        Set<Coordinate> visited = new HashSet<>(); // Bereits besuchte Koordinaten
        Coordinate start = maze.getStart();
        Coordinate destination = maze.getDestination();

        queue.add(new ArrayList<>(Arrays.asList(start))); // Füge Startkoordinate zum Pfad hinzu

        while (!queue.isEmpty()) {
            List<Coordinate> path = queue.poll(); // Entferne den ersten Pfad aus der Warteschlange
            Coordinate current = path.get(path.size() - 1); // Aktuelle Koordinate im Pfad

            if (current.equals(destination)) {
                return path; // Rückgabe des gefundenen Pfads, wenn das Ziel erreicht ist
            }

            for (Coordinate neighbor : getValidNeighbors(current)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    List<Coordinate> newPath = new ArrayList<>(path);
                    newPath.add(neighbor);
                    queue.add(newPath); // Füge neuen Pfad mit Nachbarkoordinate zur Warteschlange hinzu
                }
            }
            path.clear(); // Lösche den Referenzpfad, um Speicher zu sparen
        }

        return null; // Kein Pfad gefunden
    }

    // Implementierung der Tiefensuche (DFS)
    private List<Coordinate> depthFirstSearch() {
        Stack<Coordinate> stack = new Stack<>(); // Stapel für die Tiefensuche
        Set<Coordinate> visited = new HashSet<>(); // Bereits besuchte Koordinaten
        Coordinate start = maze.getStart();
        Coordinate destination = maze.getDestination();

        stack.push(start); // Startkoordinate zum Stapel hinzufügen

        while (!stack.isEmpty()) {
            Coordinate current = stack.pop(); // Entferne die oberste Koordinate vom Stapel

            if (current.equals(destination)) {
                return getPathFromVisited(visited, start, destination); // Finde den Pfad zurückverfolgend
            }

            if (!visited.contains(current)) {
                visited.add(current);

                for (Coordinate neighbor : getValidNeighbors(current)) {
                    if (!visited.contains(neighbor)) {
                        stack.push(neighbor); // Füge Nachbarkoordinaten zum Stapel hinzu
                    }
                }
            }
        }

        return null; // Kein Pfad gefunden
    }

    // Methode zur Ermittlung der gültigen Nachbarkoordinaten für eine gegebene Koordinate
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

        return neighbors; // Liste der gültigen Nachbarn zurückgeben
    }

    // Methode zur Erstellung des Pfads aus den bereits besuchten Koordinaten
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
        return path; // Rückgabe des Pfads von Start zu Ziel
    }
}
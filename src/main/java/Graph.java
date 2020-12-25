import java.util.*;

public class Graph {

    private Map<String, Integer> vertices = new HashMap<>();
    private ArrayList<Arc> arcs = new ArrayList<>();
    private ArrayList<Arc> newArcs;

    public class Arc {

        private String name;
        private int weight;
        private int begin;
        private int end;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Arc arc = (Arc) o;
            return weight == arc.weight &&
                    begin == arc.begin &&
                    end == arc.end &&
                    Objects.equals(name, arc.name);
        }

        @Override
        public int hashCode() {

            return Objects.hash(name, weight, begin, end);
        }

        @Override
        public String toString() {
            return "Arc{" +
                    "name='" + name + '\'' +
                    ", weight=" + weight +
                    ", begin=" + begin +
                    ", end=" + end +
                    '}';
        }

        public Arc(String n, int w, String beginVertexName, String endVertexName) {
            name = n;
            weight = w;
            begin = vertices.get(beginVertexName);
            end = vertices.get(endVertexName);
        }

        public Integer getArcBegin() {
            return this.begin;
        }

        public Integer getArcEnd() {
            return this.end;
        }

        public String getArcName() {
            return this.name;
        }

        public int getArcWeight() {
            return this.weight;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Graph graph = (Graph) o;
        return Objects.equals(vertices, graph.vertices) &&
                Objects.equals(arcs, graph.arcs) &&
                Objects.equals(newArcs, graph.newArcs);
    }

    @Override
    public int hashCode() {

        return Objects.hash(vertices, arcs, newArcs);
    }

    public Graph() {
        this.vertices.put("A", 0);
        this.vertices.put("B", 1);
        this.vertices.put("C", 2);
        this.vertices.put("D", 3);
        this.vertices.put("E", 4);

        Arc ab = new Arc("AB", 3, "A", "B");
        Arc bc = new Arc("BC", 3, "B", "C");
        Arc ae = new Arc("AE", 3, "A", "E");
        Arc ec = new Arc("EC", 3, "E", "C");
        Arc ca = new Arc("CA", 3, "C", "A");

        arcs.add(ab);
        arcs.add(bc);
        arcs.add(ae);
        arcs.add(ec);
        arcs.add(ca);
    }

    public Map<String, Integer> getVertices() {
        return vertices;
    }

    public List<Arc> getArcs() {
        return arcs;
    }

    public void addVertex(String name) {
        vertices.put(name, vertices.size());
    }

    public void addArc(String name, int weight, String beginVertex, String endVertex) {
        Arc a = new Arc(name, weight, beginVertex, endVertex);
        arcs.add(a);
    }

    public void deleteVertex(String vertexName) {
        int currentVertex = vertices.getOrDefault(vertexName, -1);
        for (int i = 0; i < arcs.size(); i++) {
            if (arcs.get(i).begin == currentVertex) arcs.remove(i);
            if (arcs.get(i).end == currentVertex) arcs.remove(i);
        }
        vertices.remove(vertexName);
    }

    public void deleteArc(String arcName) {
        for (int i = 0; i < arcs.size(); i++)
            if (arcs.get(i).name.equals(arcName)) arcs.remove(i);
    }

    public void changeVertexName(String oldName, String newName) {
        if (!vertices.containsKey(oldName)) throw new IllegalArgumentException("Вершины с таким именем не существует");
        vertices.put(newName, vertices.get(oldName));
        vertices.remove(oldName);
    }

    public void changeArcWeight(String arcName, int newWeight) {
        for (Arc arc : arcs)
            if (arc.name.equals(arcName)) {
                arc.weight = newWeight;
                break;
            }
    }

    public List<String> getArcOutList(String vertexName) {
        List<String> result = new ArrayList();
        for (Arc arc : arcs) if (vertices.get(vertexName) == arc.begin) result.add(arc.name);
        return result;
    }

    public List<String> getArcInList(String vertexName) {
        List<String> result = new ArrayList();
        for (Arc arc : arcs) if (vertices.get(vertexName) == arc.end) result.add(arc.name);
        return result;
    }
}

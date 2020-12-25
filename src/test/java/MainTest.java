import org.junit.jupiter.api.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    private static final Logger logger = LogManager.getLogger(MainTest.class);
    Graph g = new Graph();
    private int sizeTest = g.getVertices().size();

    @Test
    void exampleTest() {
        logger.info("Test started");
        assertEquals(10, 10);
        logger.info("Test finished");
    }

    @Test
    void addVertexTest() {
        logger.info("Test started");
        g.addVertex("H");
        assertEquals(sizeTest + 1, g.getVertices().size());
        logger.info("Test finished");
    }

    @Test
    void deleteVertexTest() {
        logger.info("Test started");
        g.deleteVertex("A");
        assertEquals(sizeTest - 1, g.getVertices().size());
        logger.info("Test finished");
    }

    @Test
    void addArcTest() {
        logger.info("Test started");
        int s = g.getArcs().size();
        g.addArc("BA", 25, "B", "A");
        assertEquals(s + 1, g.getArcs().size());
        logger.info("Test finished");
    }

    @Test
    void deleteArcTest() {
        logger.info("Test started");
        int s = g.getArcs().size();
        g.deleteArc("AB");
        assertEquals(s - 1, g.getArcs().size());
        logger.info("Test finished");
    }

    @Test
    void changeVertexNameTest() {
        logger.info("Test started");
        g.changeVertexName("E", "e");
        boolean q = !g.getVertices().containsKey("E") && g.getVertices().containsKey("e");
        assertTrue(q);
        assertThrows(IllegalArgumentException.class, () -> g.changeVertexName("L", "O"));
        logger.info("Test finished");
    }

    @Test
    void changeArcWeightTest() {
        logger.info("Test started");
        g.changeArcWeight("EC", 17);
        List<Graph.Arc> arcsTest = g.getArcs();
        int resultWeight = 0;
        for (int i = 0; i < arcsTest.size(); i++)
            if (arcsTest.get(i).getArcName().equals("EC")) resultWeight = arcsTest.get(i).getArcWeight();
        assertEquals(17, resultWeight);
        logger.info("Test finished");
    }

    @Test
    void getArcOutListTest() {
        logger.info("Test started");
        List<String> result = new ArrayList<>();
        result.add("AB");
        result.add("AE");
        assertEquals(result, g.getArcOutList("A"));
        logger.info("Test finished");
    }

    @Test
    void getArcInListTest() {
        logger.info("Test started");
        List<String> result = new ArrayList<>();
        result.add("BC");
        result.add("EC");
        assertEquals(result, g.getArcInList("C"));
        logger.info("Test finished");
    }
}

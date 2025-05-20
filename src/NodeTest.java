import org.junit.Test;

import static org.junit.Assert.*;

public class NodeTest {

    @Test
    public void testNode() {
        Node node1 = new Node();
        assertEquals("", node1.getTerm().getTerm());
        assertEquals(0, node1.getTerm().getWeight());
        assertEquals(0, node1.getPrefixes());
        assertEquals(0, node1.getWords());
        assertEquals(26, node1.getReferences().length);
        assertNull(node1.getReferences()[0]);

        Term term = new Term("car", 15);
        Node[] reference = new Node[26];
        reference[2] = new Node("cart", 8);
        node1.setTerm(term);
        node1.setPrefixes(3);
        node1.setWords(2);
        node1.setReferences(reference);
        assertEquals("car", node1.getTerm().getTerm());
        assertEquals(15, node1.getTerm().getWeight());
        assertEquals(3, node1.getPrefixes());
        assertEquals(2, node1.getWords());
        assertEquals(26, node1.getReferences().length);
        assertEquals("cart", node1.getReferences()[2].getTerm().getTerm());
        assertEquals(8, node1.getReferences()[2].getTerm().getWeight());
        assertNull(node1.getReferences()[0]);
    }
}
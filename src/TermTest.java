import org.junit.Before;

import java.util.ArrayList;
import java.util.*;

import static org.junit.Assert.*;

public class TermTest {

    List<ITerm> l;

    @Before
    public void setUp() throws Exception {
        l = new ArrayList<ITerm>();
        ITerm t1 = new Term("zebra", 12);
        ITerm t2 = new Term("zoo", 25);
        ITerm t3 = new Term("yellow", 30);
        ITerm t4 = new Term("zone", 18);
        t4.setTerm("zone");
        t4.setWeight(18);
        l.add(t1);
        l.add(t2);
        l.add(t3);
        l.add(t4);
    }

    @org.junit.Test
    public void compareTo() {
        Collections.sort(l, ITerm.byReverseWeightOrder());
        ITerm tt1 = l.get(0);
        assertEquals("yellow", tt1.getTerm());
        assertEquals(30, tt1.getWeight());
        ITerm tt2 = l.get(1);
        assertEquals("zoo", tt2.getTerm());
        assertEquals(25, tt2.getWeight());
        ITerm tt3 = l.get(2);
        assertEquals("zone", tt3.getTerm());
        assertEquals(18, tt3.getWeight());
        ITerm tt4 = l.get(3);
        assertEquals("zebra", tt4.getTerm());
        assertEquals(12, tt4.getWeight());

        Collections.sort(l, ITerm.byPrefixOrder(15));
        tt1 = l.get(0);
        assertEquals("yellow", tt1.getTerm());
        assertEquals(30, tt1.getWeight());
        tt2 = l.get(1);
        assertEquals("zebra", tt2.getTerm());
        assertEquals(12, tt2.getWeight());
        tt3 = l.get(2);
        assertEquals("zone", tt3.getTerm());
        assertEquals(18, tt3.getWeight());
        tt4 = l.get(3);
        assertEquals("zoo", tt4.getTerm());
        assertEquals(25, tt4.getWeight());

        Collections.sort(l, ITerm.byPrefixOrder(2));
        tt1 = l.get(0);
        assertEquals("yellow", tt1.getTerm());
        assertEquals(30, tt1.getWeight());
        tt2 = l.get(1);
        assertEquals("zebra", tt2.getTerm());
        assertEquals(12, tt2.getWeight());
        tt3 = l.get(2);
        assertEquals("zone", tt3.getTerm());
        assertEquals(18, tt3.getWeight());
        assertEquals("zoo", tt4.getTerm());
        assertEquals(25, tt4.getWeight());

        Collections.sort(l);
        tt1 = l.get(0);
        assertEquals("yellow", tt1.getTerm());
        assertEquals(30, tt1.getWeight());
        tt2 = l.get(1);
        assertEquals("zebra", tt2.getTerm());
        assertEquals(12, tt2.getWeight());
        tt3 = l.get(2);
        assertEquals("zone", tt3.getTerm());
        assertEquals(18, tt3.getWeight());
        tt4 = l.get(3);
        assertEquals("zoo", tt4.getTerm());
        assertEquals(25, tt4.getWeight());
    }

    @org.junit.Test
    public void testToString() {
        Collections.sort(l);
        assertEquals("30" + "\t" + "yellow", l.get(0).toString());
    }
}
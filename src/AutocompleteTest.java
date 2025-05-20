import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class AutocompleteTest {

    private Autocomplete autocomplete;
    private static final String TEST_FILENAME = "test_words.txt";

    @Before
    public void setUp() {
        autocomplete = new Autocomplete();
    }

    @Test
    public void testAddWordValid() {
        autocomplete.addWord("test", 100);
        assertEquals(1, autocomplete.countPrefixes("test"));
        assertEquals(1, autocomplete.countPrefixes("tes"));
        assertEquals(1, autocomplete.countPrefixes("te"));
        assertEquals(1, autocomplete.countPrefixes("t"));
    }

    @Test
    public void testAddWordInvalidCharacters() {
        autocomplete.addWord("test123", 100);
        assertEquals(0, autocomplete.countPrefixes("test"));

        autocomplete.addWord("test!", 100);
        assertEquals(0, autocomplete.countPrefixes("test"));
    }

    @Test
    public void testAddWordCaseInsensitive() {
        autocomplete.addWord("TEST", 100);
        assertEquals(1, autocomplete.countPrefixes("test"));
        assertEquals(1, autocomplete.countPrefixes("TEST"));
        assertEquals(1, autocomplete.countPrefixes("TesT"));
    }

    @Test
    public void testGetSubTrie() {
        autocomplete.addWord("apple", 100);
        autocomplete.addWord("appetite", 200);

        Node node = autocomplete.getSubTrie("app");
        assertNotNull(node);
        assertEquals(2, node.getPrefixes());

        node = autocomplete.getSubTrie("apple");
        assertNotNull(node);
        assertEquals(1, node.getPrefixes());
        assertEquals("apple", node.getTerm().getTerm());
    }

    @Test
    public void testCountPrefixes() {
        autocomplete.addWord("apple", 100);
        autocomplete.addWord("appetite", 200);
        autocomplete.addWord("application", 300);

        assertEquals(3, autocomplete.countPrefixes("app"));
        assertEquals(2, autocomplete.countPrefixes("appl"));
        assertEquals(1, autocomplete.countPrefixes("appet"));
        assertEquals(0, autocomplete.countPrefixes("banana"));
    }

    @Test
    public void testGetSuggestions() {
        autocomplete.addWord("apple", 100);
        autocomplete.addWord("appetite", 200);
        autocomplete.addWord("application", 300);
        autocomplete.addWord("banana", 50);

        List<ITerm> suggestions = autocomplete.getSuggestions("app");
        assertEquals(3, suggestions.size());
        assertEquals("application", suggestions.get(0).getTerm());
        assertEquals("appetite", suggestions.get(1).getTerm());
        assertEquals("apple", suggestions.get(2).getTerm());

        suggestions = autocomplete.getSuggestions("b");
        assertEquals(1, suggestions.size());
        assertEquals("banana", suggestions.get(0).getTerm());
    }

    @Test
    public void testBuildTrie() throws IOException {
        String content = "100\tapple\n200\tappetite\n300\tapplication\n50\tbanana\n";
        Path tempFile = Files.createTempFile("autocomplete_test", ".txt");
        Files.write(tempFile, content.getBytes());

        Node root = autocomplete.buildTrie(tempFile.toString(), 5);
        assertNotNull(root);

        assertEquals(4, autocomplete.countPrefixes(""));
        assertEquals(3, autocomplete.countPrefixes("app"));
        assertEquals(1, autocomplete.countPrefixes("ban"));

        Files.delete(tempFile);
    }

    @Test
    public void testEmptyPrefix() {
        autocomplete.addWord("apple", 100);
        autocomplete.addWord("banana", 200);

        assertEquals(2, autocomplete.countPrefixes(""));
        List<ITerm> suggestions = autocomplete.getSuggestions("");
        assertEquals(2, suggestions.size());
    }

    @Test
    public void testNullInput() {
        assertNull(autocomplete.getSubTrie(null));
        assertEquals(0, autocomplete.countPrefixes(null));
        assertEquals(0, autocomplete.getSuggestions(null).size());
    }
}
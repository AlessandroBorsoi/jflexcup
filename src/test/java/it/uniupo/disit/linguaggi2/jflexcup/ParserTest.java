package it.uniupo.disit.linguaggi2.jflexcup;

import it.uniupo.disit.linguaggi2.jflexcup.generated.Parser;
import it.uniupo.disit.linguaggi2.jflexcup.generated.Scanner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.StringReader;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ParserTest {

    private final OutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outContent));
    }

    @Test
    void parseNotDeclaredIdOnAssign() throws Exception {
        Scanner scanner = new Scanner(new StringReader("a = 5"));
        Parser parser = new Parser(scanner);
        parser.parse();
        assertEquals("id: 'a' never declared\n", outContent.toString());
    }

    @Test
    void parseNotDeclaredIdOnPrint() throws Exception {
        Scanner scanner = new Scanner(new StringReader("print foo"));
        Parser parser = new Parser(scanner);
        parser.parse();
        assertEquals("id: 'foo' never declared\n", outContent.toString());
    }

    @Test
    void parseInvalidTypesProgram() throws Exception {
        Scanner scanner = new Scanner(new StringReader("int b\n" +
                "int a\n" +
                "a = 5.2\n" +
                "b = a + 3.2\n" +
                "print b\n"));
        Parser parser = new Parser(scanner);
        parser.parse();
        assertEquals("type INT of 'a' not compatible with type FLOAT\n" +
                "type INT of 'b' not compatible with type FLOAT\n", outContent.toString());
    }

    @Test
    void parseNotConvertibleType() throws Exception {
        Scanner scanner = new Scanner(new StringReader("int foo\n" +
                "float bar\n" +
                "bar = 1.5\n" +
                "foo = bar"));
        Parser parser = new Parser(scanner);
        parser.parse();
        assertEquals("type INT of 'foo' not compatible with type FLOAT\n", outContent.toString());
    }

    @Test
    void parseValidTypesProgram() throws Exception {
        Scanner scanner = new Scanner(new StringReader("float b\n" +
                "int a\n" +
                "a = 5\n" +
                "b = a + 3.2\n" +
                "print b\n"));
        Parser parser = new Parser(scanner);
        parser.parse();
        assertEquals("", outContent.toString());
    }

}
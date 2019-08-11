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
    void parseNotDeclaredId() throws Exception {
        Scanner scanner = new Scanner(new StringReader("a = 5"));
        Parser parser = new Parser(scanner);
        parser.parse();
        assertEquals("id: 'a' never declared\n", outContent.toString());
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

    @Test
    void parseInvalidTypesProgram() throws Exception {
        Scanner scanner = new Scanner(new StringReader("int b\n" +
                "int a\n" +
                "a = 5.2\n" +
                "b = a + 3.2\n" +
                "print b\n"));
        Parser parser = new Parser(scanner);
        parser.parse();
        //assertEquals("type INT not compatible with FLOAT\n" +
          //      "type INT not compatible with FLOAT", outContent.toString());
    }
}
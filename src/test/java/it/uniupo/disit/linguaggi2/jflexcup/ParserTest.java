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


    void test() throws Exception {
        Scanner scanner = new Scanner(new StringReader("let i=10; 34+i; let j=i+5; j+i;"));
        Parser parser = new Parser(scanner);
        parser.parse();
        assertEquals(
                "i = 10\n" +
                        "44\n" +
                        "j = 15\n" +
                        "25\n", outContent.toString());
    }
}
package it.uniupo.disit.linguaggi2.jflexcup;

import it.uniupo.disit.linguaggi2.jflexcup.generated.Scanner;
import it.uniupo.disit.linguaggi2.jflexcup.generated.sym;
import java_cup.runtime.Symbol;
import org.junit.jupiter.api.Test;

import java.io.StringReader;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ScannerTest {

    @Test
    void scanSrc() throws Exception {
        Scanner scanner = new Scanner(new StringReader(
                "float b\n" +
                "int a\n" +
                "a = 5\n" +
                "b = a + 3.2\n" +
                "print b\n"));
        assertEquals(sym.FLOAT, scanner.next_token().sym);
        assertEquals(sym.ID, scanner.next_token().sym);
        assertEquals(sym.INT, scanner.next_token().sym);
        assertEquals(sym.ID, scanner.next_token().sym);
        assertEquals(sym.ID, scanner.next_token().sym);
        assertEquals(sym.ASSIGN, scanner.next_token().sym);
        assertEquals(sym.INUM, scanner.next_token().sym);
        assertEquals(sym.ID, scanner.next_token().sym);
        assertEquals(sym.ASSIGN, scanner.next_token().sym);
        assertEquals(sym.ID, scanner.next_token().sym);
        assertEquals(sym.PLUS, scanner.next_token().sym);
        assertEquals(sym.FNUM, scanner.next_token().sym);
        assertEquals(sym.PRINT, scanner.next_token().sym);
        assertEquals(sym.ID, scanner.next_token().sym);
    }

    @Test()
    void scanInvalidToken() {
        Scanner scanner = new Scanner(new StringReader("<"));
        assertThrows(Error.class, scanner::next_token);
    }
}

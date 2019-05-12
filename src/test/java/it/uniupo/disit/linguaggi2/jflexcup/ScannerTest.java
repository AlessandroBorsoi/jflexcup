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
    void scanMinusExpression() throws Exception {
        Scanner scanner = new Scanner(new StringReader("34 - minus;"));
        Symbol il = scanner.next_token();
        assertEquals(sym.INTEGER_LITERAL, il.sym);
        assertEquals(34, il.value);
        assertEquals(sym.MINUS, scanner.next_token().sym);
        Symbol id = scanner.next_token();
        assertEquals(sym.IDENTIFIER, id.sym);
        assertEquals("minus", id.value);
        assertEquals(sym.SEMICOLON, scanner.next_token().sym);
    }

    @Test
    void scanTimesExpression() throws Exception {
        Scanner scanner = new Scanner(new StringReader("34 * times;"));
        assertEquals(sym.INTEGER_LITERAL, scanner.next_token().sym);
        assertEquals(sym.TIMES, scanner.next_token().sym);
        assertEquals(sym.IDENTIFIER, scanner.next_token().sym);
        assertEquals(sym.SEMICOLON, scanner.next_token().sym);
    }

    @Test
    void scanModExpression() throws Exception {
        Scanner scanner = new Scanner(new StringReader("34 % mod;"));
        assertEquals(sym.INTEGER_LITERAL, scanner.next_token().sym);
        assertEquals(sym.MOD, scanner.next_token().sym);
        assertEquals(sym.IDENTIFIER, scanner.next_token().sym);
        assertEquals(sym.SEMICOLON, scanner.next_token().sym);
    }

    @Test
    void scanTowExpressions() throws Exception {
        Scanner scanner = new Scanner(new StringReader("i / 8; let j = 3;"));
        assertEquals(sym.IDENTIFIER, scanner.next_token().sym);
        assertEquals(sym.DIVIDE, scanner.next_token().sym);
        assertEquals(sym.INTEGER_LITERAL, scanner.next_token().sym);
        assertEquals(sym.SEMICOLON, scanner.next_token().sym);
        assertEquals(sym.LET, scanner.next_token().sym);
        assertEquals(sym.IDENTIFIER, scanner.next_token().sym);
        assertEquals(sym.EQ, scanner.next_token().sym);
        assertEquals(sym.INTEGER_LITERAL, scanner.next_token().sym);
        assertEquals(sym.SEMICOLON, scanner.next_token().sym);
    }

    @Test
    void scanSlideExample() throws Exception {
        Scanner scanner = new Scanner(new StringReader("let i=10; 34+i; let j=i+5; j+i;"));
        assertEquals(sym.LET, scanner.next_token().sym);
        assertEquals(sym.IDENTIFIER, scanner.next_token().sym);
        assertEquals(sym.EQ, scanner.next_token().sym);
        assertEquals(sym.INTEGER_LITERAL, scanner.next_token().sym);
        assertEquals(sym.SEMICOLON, scanner.next_token().sym);
        assertEquals(sym.INTEGER_LITERAL, scanner.next_token().sym);
        assertEquals(sym.PLUS, scanner.next_token().sym);
        assertEquals(sym.IDENTIFIER, scanner.next_token().sym);
        assertEquals(sym.SEMICOLON, scanner.next_token().sym);
        assertEquals(sym.LET, scanner.next_token().sym);
        assertEquals(sym.IDENTIFIER, scanner.next_token().sym);
        assertEquals(sym.EQ, scanner.next_token().sym);
        assertEquals(sym.IDENTIFIER, scanner.next_token().sym);
        assertEquals(sym.PLUS, scanner.next_token().sym);
        assertEquals(sym.INTEGER_LITERAL, scanner.next_token().sym);
        assertEquals(sym.SEMICOLON, scanner.next_token().sym);
        assertEquals(sym.IDENTIFIER, scanner.next_token().sym);
        assertEquals(sym.PLUS, scanner.next_token().sym);
        assertEquals(sym.IDENTIFIER, scanner.next_token().sym);
        assertEquals(sym.SEMICOLON, scanner.next_token().sym);
    }

    @Test()
    void scanInvalidToken() {
        Scanner scanner = new Scanner(new StringReader("<"));
        assertThrows(Error.class, scanner::next_token);
    }
}

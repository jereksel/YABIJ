package pl.jereksel.brainfuck;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class Tests {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
        System.setErr(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void noNullAllowed() {
        new BrainfuckInterpreter(null);
    }

    @Test(expected = BrainfuckException.class)
    public void wrongSymbol() {
        new BrainfuckInterpreter(">++>>++%+").interpret();
    }

    @Test
    public void adding() {
        new BrainfuckInterpreter("++++++++++[>+++++++>++++++++++>+++>+<<<<-]>++.>+.+++++++..+++.>++.<<+++++++++++++++.>.+++.------.--------.>+.>.")
                .interpret();
        assertEquals("Hello World!\n", outContent.toString());
    }

}



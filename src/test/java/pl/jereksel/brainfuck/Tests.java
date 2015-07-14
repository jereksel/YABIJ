package pl.jereksel.brainfuck;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

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
    public void helloWorld() {
        new BrainfuckInterpreter("++++++++++[>+++++++>++++++++++>+++>+<<<<-]>++.>+.+++++++..+++.>++.<<+++++++++++++++.>.+++.------.--------.>+.>.")
                .interpret();
        assertEquals("Hello World!\n", outContent.toString());
    }

    @Test
    //https://pl.wikipedia.org/wiki/Brainfuck#Dodawanie_2
    public void adding() {

        String program = "";

        //2 - 50 in ASCII
        for (int i = 1; i <= 50; i++) {
            program += "+";
        }

        program += ">++++++[<-------->-]";


        //3 - 51 in ASCII
        for (int i = 1; i <= 51; i++) {
            program += "+";
        }

        program += "[<+>-]<.";

        new BrainfuckInterpreter(program)
                .interpret();

        assertEquals("5", outContent.toString());
    }


    @Test
    //https://pl.wikipedia.org/wiki/Brainfuck#Mno.C5.BCenie
    public void multiplying() {

        String program = "";

        //2 - 50 in ASCII
        for (int i = 1; i <= 50; i++) {
            program += "+";
        }

        program += ">";

        //3 - 51 in ASCII
        for (int i = 1; i <= 51; i++) {
            program += "+";
        }


        program += ">++++++++[<------<------>>-]<<[>[>+>+<<-]>>[<<+>>-]<<<-]>>>++++++[<++++++++>-]<.";

        new BrainfuckInterpreter(program)
                .interpret();

        assertEquals("6", outContent.toString());

    }

}



package pl.jereksel.brainfuck;

public class Main {

    public static void main(String[] args)
            throws Exception {

        if (args.length == 0) {
            printUsage();
            System.exit(1);
        }

        String data = args[0];

        new BrainfuckInterpreter(data).interpret();

    }

    private static void printUsage() {
        System.out.println("USAGE: <file>");
    }

}

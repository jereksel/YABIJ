package pl.jereksel.brainfuck;

public class BrainfuckInterpreter {

    private String data;

    private int dataPosition = 0;

    private int arrayPosition = 0;

    private char[] array = new char[30000];

    public BrainfuckInterpreter(String data) {

        if (data == null) {
            throw new IllegalArgumentException("Program cannot be null");
        }

        this.data = data;
    }


    public void interpret() {

        while (dataPosition != data.length()) {

            switch (data.charAt(dataPosition)) {

                case '>':
                    arrayPosition++;
                    dataPosition++;
                    break;

                case '<':
                    arrayPosition--;
                    dataPosition++;
                    break;

                case '+':
                    array[arrayPosition]++;
                    dataPosition++;
                    break;

                case '-':
                    array[arrayPosition]--;
                    dataPosition++;
                    break;

                case '.':
                    System.out.print(array[arrayPosition]);
                    dataPosition++;
                    break;

                case '[':

                    if (array[arrayPosition] == 0) {

                        int temp = 0;

                        while (true) {

                            dataPosition++;

                            if (temp == 0 && data.charAt(dataPosition) == ']') {
                                dataPosition++;
                                break;
                            } else if (data.charAt(dataPosition) == ']') {
                                temp++;
                            } else if (data.charAt(dataPosition) == '[') {
                                temp--;
                            }


                        }

                    } else {
                        dataPosition++;
                    }


                    break;


                case ']':


                    int temp = 0;


                    while (true) {

                        dataPosition--;

                        if (temp == 0 && data.charAt(dataPosition) == '[') {
                            break;
                        } else if (data.charAt(dataPosition) == ']') {
                            temp++;
                        } else if (data.charAt(dataPosition) == '[') {
                            temp--;
                        }

                    }


                    break;

                default:
                    throw new BrainfuckException();

            }
        }

    }

}

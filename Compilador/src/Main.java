import LexicalAnalizer.fileReader;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        fileReader reader = new fileReader("test.s");
        Character c;
        while ((c = reader.readCharacter()) != null) {
            System.out.println(c);
        }
        reader.close();
    }
}
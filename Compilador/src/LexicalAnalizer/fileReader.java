package LexicalAnalizer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class fileReader {

    private BufferedReader reader;
    private int row;
    private int column;

    public fileReader(String nombreArchivo) throws IOException {
        this.reader = new BufferedReader(new FileReader(nombreArchivo));
        this.row = 1;
        this.column = 0;
    }

    public Character readCharacter() throws IOException {
        int c = reader.read();
        if (c == -1) {
            return null;
        }
        column++;
        if (c == '\n') {
            row++;
            column = 0;
        }
        return (char) c;
    }

    public void close() throws IOException {
        reader.close();
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
}

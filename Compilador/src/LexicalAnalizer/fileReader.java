package LexicalAnalizer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;

public class fileReader {

    private BufferedReader reader;
    private int row;
    private int column;
    private Deque<Character> buffer;

    public fileReader(String nombreArchivo) throws IOException {
        this.reader = new BufferedReader(new FileReader(nombreArchivo));
        this.row = 1;
        this.column = 1;
        this.buffer = new ArrayDeque<>();
    }

    public Character readCharacter() throws IOException {
        if (!buffer.isEmpty()) {
            Character c = buffer.pollFirst();
            updatePosition(c);
            return c;
        }

        int c = reader.read();
        if (c == -1) {
            return null;
        }
        char ch = (char) c;
        updatePosition(ch);
        return ch;
    }

    public Character peek() throws IOException {
        if (!buffer.isEmpty()) {
            return buffer.peekFirst();
        }

        reader.mark(1);
        int c = reader.read();
        reader.reset();

        if (c == -1) {
            return null;
        }
        return (char) c;
    }

    //implementada bajo recomendacion, pero se usa?
    public void unread(Character c) {
        if (c != null) {
            buffer.push(c);
        }
    }

    private void updatePosition(char c) {
        column++;
        if (c == '\n') {
            row++;
            column = 1;
        }
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

    // TODO: Agregar soporte para strings (lexema entre comillas)
}

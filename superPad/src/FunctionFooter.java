import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class FunctionFooter implements DocumentListener {
    GUI gui;
    FunctionFooter(GUI gui){
        this.gui = gui;
    }
    int readLine(){
        String textarea = gui.textArea.getText();
        String[] lines = textarea.split("\r\n|\r|\n");
        return lines.length;
    }
    int readWord(){
        String textarea = gui.textArea.getText();
        String trim = textarea.trim();
        if (trim.isEmpty())
            return 0;
        return trim.split("\\s+").length;
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        updateLog(e);
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        updateLog(e);
    }

    @Override
    public void changedUpdate(DocumentEvent e) {

    }
    public void updateLog(DocumentEvent e) {
        gui.countWord = readWord();
        gui.countLine = readLine();
        gui.fWords.setText("   Words: " + gui.countWord);
        gui.fLines.setText("Lines: " + gui.countLine);
        gui.fLength.setText("Length: " + gui.textArea.getText().length());
    }

}

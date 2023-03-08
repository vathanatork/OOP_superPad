import java.awt.*;

public class FunctionFormat {

    /**------------Variable------------*/
    GUI gui;
    Font arial, comicSansMs, timeNewRoman;
    String selectedFont;

    /**------------Constructor------------*/
    FunctionFormat(GUI gui){
        this.gui = gui;
    }

    /**------------Word wrap--------------*/
    public void WordWrap(){
        if(gui.wordWrapOn == false){
            gui.wordWrapOn = true;
            gui.textArea.setLineWrap(true);
            gui.textArea.setWrapStyleWord(true);
            gui.iWordWrap.setText("Word wrap:on");
        }
        else{
            gui.wordWrapOn = false;
            gui.textArea.setLineWrap(false);
            gui.textArea.setWrapStyleWord(false);
            gui.iWordWrap.setText("Word wrap:off");
        }
    }

    /**------------Font size--------------*/
    public void createFont(int fontSize){
        //gui.font = fontSize;
        arial = new Font("Arial",Font.PLAIN,fontSize);
        comicSansMs = new Font("Comic Sans Ms",Font.PLAIN,fontSize);
        timeNewRoman = new Font("Time New Roman",Font.PLAIN,fontSize);
        setFontFamily(selectedFont);
    }


    /**------------Font Family--------------*/
    public void setFontFamily(String family){
        selectedFont = family;
        switch (selectedFont){
            case "Arial":
                gui.textArea.setFont(arial);
                break;
            case "Comic Sans MS":
                gui.textArea.setFont(comicSansMs);
                break;
            case "Time New Roman":
                gui.textArea.setFont(timeNewRoman);
                break;
        }
    }
}

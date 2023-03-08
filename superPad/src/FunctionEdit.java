public class FunctionEdit {
    GUI gui;

    FunctionEdit(GUI gui){
        this.gui = gui;
    }

    public void undo(){
        gui.um.undo();
    }

    public void redo(){
        gui.um.redo();
    }

    public void copy(){gui.textArea.copy();}

    public void cut(){gui.textArea.cut();}

    public void paste(){gui.textArea.paste();}
}

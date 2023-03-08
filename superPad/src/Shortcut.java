import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Shortcut implements KeyListener {
    GUI gui;

    /** ------------Constructor----------*/
    Shortcut(GUI gui){
        this.gui = gui;
    }

    /**----------implement function shortcut------------*/

    @Override
    public void keyPressed(KeyEvent e) {
        /**--------------ctrl + s = save()---------------------*/
        if(e.isControlDown() && e.getKeyCode() == KeyEvent.VK_S){
            gui.file.save();
        }

        /**--------------ctrl + shift + s = saveAs()---------------------*/
        if(e.isShiftDown() && e.isControlDown() && e.getKeyCode() == KeyEvent.VK_S){
            gui.file.saveAs();
        }

        /**--------------ctrl + N = New()---------------------*/
        if(e.isControlDown() && e.getKeyCode() == KeyEvent.VK_N){
            gui.file.newFile();
        }

        /**--------------ctrl + o = open()---------------------*/
        if(e.isControlDown() && e.getKeyCode() == KeyEvent.VK_O){
            gui.file.open();
        }

        /**--------------Alt + f4 = exit()---------------------*/
        if(e.isAltDown() && e.getKeyCode() == KeyEvent.VK_F4){
            gui.file.exit();
        }

        /**--------------ctrl + w = word wrap()---------------------*/
        if(e.isControlDown() && e.getKeyCode() == KeyEvent.VK_W){
            gui.format.WordWrap();
        }

        /**--------------Ctrl + "+" = increase font ---------------------*/
        if(e.isControlDown() && e.getKeyCode() == KeyEvent.VK_EQUALS){
            gui.format.createFont(gui.font);
            gui.font = gui.font + 2;
        }

        /**--------------Ctrl + "-" = decrease font---------------------*/
        if(e.isControlDown() && e.getKeyCode() == KeyEvent.VK_UNDO){
            gui.format.createFont(gui.font);
            gui.font = gui.font - 2;
        }

        /**--------------Ctrl + 0 = default font---------------------*/
        if(e.isControlDown() && e.getKeyCode() == KeyEvent.VK_0){
            gui.format.createFont(16);
            gui.font = 16;
        }

        /**--------------Ctrl + z = undo()---------------------*/
        if(e.isControlDown() && e.getKeyCode() == KeyEvent.VK_Z){
            gui.edit.undo();
        }

        /**--------------Ctrl + shift + z = redo()---------------------*/
        if(e.isAltDown() && e.getKeyCode() == KeyEvent.VK_Z){
            gui.edit.redo();
        }


    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
}

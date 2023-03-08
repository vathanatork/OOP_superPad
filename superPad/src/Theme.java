import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import java.awt.*;

public class Theme {

    GUI gui;

    /**-------------Constructor-------------*/
    Theme (GUI gui){
        this.gui = gui;
    }

    /**-------------default----------------*/
    public void theme_default(){
        gui.menuBar.setBackground(new Color(47,46,48));

        for(int i=0;i<4;i++){
            gui.menuBar.getMenu(i).setForeground(Color.white);
            int count=gui.menuBar.getMenu(i).getItemCount();
            for(int j =0;j<count;j++){
                gui.menuBar.getMenu(i).getItem(j).setBackground(new Color(47,46,48));
                gui.menuBar.getMenu(i).getItem(j).setForeground(Color.white);
            }
        }

        int scount =gui.mFontSize.getItemCount();
        for(int i=0;i<scount;i++){
            gui.mFontSize.getItem(i).setBackground(new Color(47,46,48));
            gui.mFontSize.getItem(i).setForeground(Color.white);
        }

        int fcount =gui.mFamily.getItemCount();
        for(int i=0;i<fcount;i++){
            gui.mFamily.getItem(i).setBackground(new Color(47,46,48));
            gui.mFamily.getItem(i).setForeground(Color.white);
        }


        gui.window.getContentPane().setBackground(Color.white);
        gui.textArea.setBackground(Color.white);
        gui.textArea.setForeground(Color.black);
        gui.window.getRootPane().putClientProperty("JRootPane.titleBarBackground", Color.darkGray);
        gui.panel.setBackground(Color.darkGray);
        gui.menuBar.setBackground(new Color(0,18,51));
        setForceGroundColor();
    }

    /**-------------dark----------------*/
    public void theme_dark() {

        for(int i=0;i<4;i++){
            gui.menuBar.getMenu(i).setForeground(Color.white);
            int count=gui.menuBar.getMenu(i).getItemCount();
            for(int j =0;j<count;j++){
                gui.menuBar.getMenu(i).getItem(j).setBackground(new Color(0,18,51));
                gui.menuBar.getMenu(i).getItem(j).setForeground(Color.white);
            }
        }

        int scount =gui.mFontSize.getItemCount();
        for(int i=0;i<scount;i++){
            gui.mFontSize.getItem(i).setBackground(new Color(0,18,51));
            gui.mFontSize.getItem(i).setForeground(Color.white);
        }

        int fcount =gui.mFamily.getItemCount();
        for(int i=0;i<fcount;i++){
            gui.mFamily.getItem(i).setBackground(new Color(0,18,51));
            gui.mFamily.getItem(i).setForeground(Color.white);
        }
        
        gui.window.getContentPane().setBackground(new Color(51,51,51));
        gui.textArea.setBackground(new Color(51,51,51));
        gui.textArea.setForeground(Color.white);
        gui.window.getRootPane().putClientProperty("JRootPane.titleBarBackground", new Color(47,46,48));
        gui.panel.setBackground(new Color(47,46,48));
        gui.menuBar.setBackground(new Color(47,46,48));
        setForceGroundColor();

    }

    public void theme_darcula() {
        // nothing overwrite
        gui.window.getContentPane().setBackground(new Color(69, 73, 74));
        gui.textArea.setBackground(new Color(69, 73, 74));
        gui.textArea.setForeground(Color.white);
        gui.window.getRootPane().putClientProperty("JRootPane.titleBarBackground", new Color(60,63,65));
        gui.panel.setBackground(new Color(60,63,65));
        setForceGroundColor();
    }

    void setForceGroundColor(){
        gui.menuBar.setForeground(Color.white);
        gui.mFooter.setForeground(Color.white);
    }
}

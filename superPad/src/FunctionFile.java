import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

public class FunctionFile {

    /**----------------Variable-------------*/
    GUI gui ;
    String fileName,fileAddress;

    /**----------------Constructor---------*/
    FunctionFile(GUI gui){
        this.gui = gui;
    }


    /** ---------------new File------------ */
    public void newFile(){
        gui.textArea.setText("");
        gui.window.setTitle("New");
        fileName = null;
        fileAddress = null;
    }


    /** ---------------Open File----------- */
    public void open(){
        FileDialog fd = new FileDialog(gui.window,"Open",FileDialog.LOAD);
        fd.setVisible(true);

        /** --------Open window dialog---------------------*/
        /** --------get Fileaddress and FileName-----------*/
        if(fd.getFile()!=null){
            fileName = fd.getFile();
            fileAddress = fd.getDirectory();
            gui.window.setTitle(fileName);
        }


        /** ------read File content and append to textArea-------*/
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileAddress+fileName));
            gui.textArea.setText("");
            String line = null;
            while((line = br.readLine())!=null){
                gui.textArea.append(line+ "\n");
            }
            br.close();

        }catch (Exception e){
            System.out.println("File not found!");
        }
    }


    /** ---------------Save File----------- */
    public void save(){
        if(fileName==null){
            saveAs();
        }else{
            try{
                FileWriter fw = new FileWriter(fileAddress+fileName);
                fw.write(gui.textArea.getText());
                gui.window.setTitle(fileName);
                fw.close();
            }catch (Exception e){
                System.out.println("Something Wrong!");
            }
        }
    }



    /** ---------------Save AS File----------- */
    public void saveAs(){
        FileDialog fd = new FileDialog(gui.window,"Save",FileDialog.SAVE);
        fd.setVisible(true);

        if(fd.getFile()!=null){
            fileName = fd.getFile();
            fileAddress = fd.getDirectory();
            gui.window.setTitle("FileName");
        }

        try{
            FileWriter fw = new FileWriter(fileAddress+fileName);
            fw.write(gui.textArea.getText());
            fw.close();
        }catch (Exception e){
            System.out.println("Something Wrong!");
        }

    }


    /** ---------------Exit----------- */
    public void exit(){
        System.exit(0);
    }



}/** end class */

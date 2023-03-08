
import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import com.formdev.flatlaf.util.SystemInfo;

import javax.swing.*;
import javax.swing.event.DocumentListener;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.undo.UndoManager;
import java.awt.*;
import java.awt.event.*;

public class GUI implements ActionListener {
    /**-------------variable-------------*/
    static JFrame window;
    JPanel panel,panelFooter;
    int font = 16;
    /**-------------textArea-------------*/
    JTextArea textArea;
    private JScrollPane scrollBar;

    /**-------------Menu-------------*/
    JMenuBar menuBar;
    JMenu mFile,mEdit,mFormat,mTheme;

    /** ---------file menu-------*/
    JMenuItem iNew,iOpen,iSave,iSaveAs,iExit;

    /** ---------Edit menu-------*/
    JMenuItem iUndo,iRedo,iCopy,iCut,iPaste;

    /** ---Format---*/
    boolean wordWrapOn = false;
    JMenuItem iWordWrap,iFontArial,iFontTNR,iFontCSMS,iFontSize8,iFontSize12,iFontSize16,iFontSize20,iFontSize24,iFontSize28;
    JMenu mFamily,mFontSize;

    /**-----theme---------*/
    JMenuItem iLight,iDark,iDarcular;

    /**------------Footer-------------*/
    JMenuBar mFooter;
    JLabel fWords, fLines, fLength;
    int countLine=0, countWord = 0;

    /**------------Exit Panel-------------*/
    JPanel exitPanel;
    JButton dontSave,save,cancel;

    /** -------create object of function--------*/
    FunctionFile file = new FunctionFile(this);
    FunctionFormat format = new FunctionFormat(this);
    UndoManager um = new UndoManager();
    FunctionEdit edit = new FunctionEdit(this);
    Shortcut shortcut = new Shortcut(this);
    Theme theme = new Theme(this);
    FunctionFooter footer = new FunctionFooter(this);



    /**------------Constructor------------------*/
    GUI(){
        createWindow();
        createTextArea();
        createMenuBar();
        createFileMenu();
        createEditMenu();
        createFormatMenu();
        createThemeMenu();
        createFooter();
        //createExitPanel();
        format.setFontFamily("Time New Roman");
        format.createFont(font);
        format.WordWrap();
        theme.theme_darcula();
        window.setVisible(true);
    }


    /**------------Create Window------------------*/
    private void createWindow() {
        window = new JFrame("Super Pad");

        JFrame.setDefaultLookAndFeelDecorated(true);
        window.setSize(600,600);
        window.setLayout(new BorderLayout(15,0));
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        WindowListener listener = new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                Frame window = (Frame) evt.getSource();
                System.out.println("Closing = "+window.getTitle());
            }
        };

//		ImageIcon logo = new ImageIcon(getClass().getClassLoader().getResource("superpad.jpg"));
//		window.setIconImage(logo.getImage());
    }


    /**------------Create Text Area------------------*/
    private void createTextArea() {
        textArea = new JTextArea();
        textArea.setMargin(new Insets(5,10,5,10));
        textArea.addKeyListener(shortcut);
        textArea.getDocument().addDocumentListener(footer);
        /**------------redo and undo ------------------*/
        textArea.getDocument().addUndoableEditListener(new UndoableEditListener() {
            @Override
            public void undoableEditHappened(UndoableEditEvent e) {
                um.addEdit(e.getEdit());
            }
        });
        countWord = footer.readWord();
        countLine = footer.readLine();

        /**------------scroll bar------------------*/
        scrollBar = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollBar.setBorder(BorderFactory.createEmptyBorder());
        window.add(scrollBar,BorderLayout.CENTER);
    }


    /**------------Create Menu Bar------------------*/
    private void createMenuBar() {
        menuBar = new JMenuBar();
        panel = new JPanel();
        window.add(panel,BorderLayout.NORTH);
        panel.setLayout(new BorderLayout());
        panel.add(menuBar,BorderLayout.WEST);

        mFile = new JMenu("File");
        mEdit = new JMenu("Edit");
        mFormat = new JMenu("Format");
        mTheme = new JMenu("Theme");

        menuBar.add(mFile);
        menuBar.add(mEdit);
        menuBar.add(mFormat);
        menuBar.add(mTheme);
        //menuBar.setBackground(Color.gray);
    }

    /**------------create File Menu------------------*/
    void createFileMenu() {

        iNew = new JMenuItem("New             Ctr+N");
        iNew.addActionListener(this);
        iNew.setActionCommand("New");
        iOpen = new JMenuItem("Open           Ctr+O");
        iOpen.addActionListener(this);
        iOpen.setActionCommand("open");
        iSave = new JMenuItem("Save           Ctr+S");
        iSave.addActionListener(this);
        iSave.setActionCommand("save");
        iSaveAs = new JMenuItem("Save as");
        iSaveAs.addActionListener(this);
        iSaveAs.setActionCommand("saveAs");
        iExit = new JMenuItem("Exit");
        iExit.addActionListener(this);
        iExit.setActionCommand("exit");

        mFile.add(iNew);
        mFile.add(iOpen);
        mFile.add(iSave);
        mFile.add(iSaveAs);
        mFile.add(iExit);
    }


    /**------------Create Edit Menu------------------*/
    void createEditMenu() {
        iUndo = new JMenuItem("Undo");
        iUndo.addActionListener(this);
        iUndo.setActionCommand("undo");
        iRedo = new JMenuItem("Redo");
        iRedo.addActionListener(this);
        iRedo.setActionCommand("redo");
        iCopy = new JMenuItem("Copy");
        iCopy.addActionListener(this);
        iCopy.setActionCommand("copy");
        iCut = new JMenuItem("Cut");
        iCut.addActionListener(this);
        iCut.setActionCommand("cut");
        iPaste = new JMenuItem("Paste");
        iPaste.addActionListener(this);
        iPaste.setActionCommand("paste");

        mEdit.add(iUndo);
        mEdit.add(iRedo);
        mEdit.add(iCopy);
        mEdit.add(iCut);
        mEdit.add(iPaste);
    }


    /**------------Create Format Menu------------------*/
    void createFormatMenu() {
        /**------------word wrap menu item------------------*/
        iWordWrap = new JMenuItem("Word Wrap:off");
        iWordWrap.addActionListener(this);
        iWordWrap.setActionCommand("word wrap");

        /**------------font size , font family menu------------------*/
        mFontSize= new JMenu("Font Size");
        mFamily = new JMenu("Font Family");

        /**------------Font family item------------------*/
        iFontArial = new JMenuItem("Arial");
        iFontArial.addActionListener(this);
        iFontArial.setActionCommand("arial");
        iFontCSMS = new JMenuItem("Comic San MS");
        iFontCSMS.addActionListener(this);
        iFontCSMS.setActionCommand("csms");
        iFontTNR = new JMenuItem("Times New Roman");
        iFontTNR.addActionListener(this);
        iFontTNR.setActionCommand("tnr");

        /**------------Font size item------------------*/
        iFontSize8 = new JMenuItem("8");
        iFontSize8.addActionListener(this);
        iFontSize8.setActionCommand("8");
        iFontSize12 = new JMenuItem("12");
        iFontSize12.addActionListener(this);
        iFontSize12.setActionCommand("12");
        iFontSize16 = new JMenuItem("16");
        iFontSize16.addActionListener(this);
        iFontSize16.setActionCommand("16");
        iFontSize20 = new JMenuItem("20");
        iFontSize20.addActionListener(this);
        iFontSize20.setActionCommand("20");
        iFontSize24 = new JMenuItem("24");
        iFontSize24.addActionListener(this);
        iFontSize24.setActionCommand("24");
        iFontSize28 = new JMenuItem("28");
        iFontSize28.addActionListener(this);
        iFontSize28.setActionCommand("28");

        /**------------Add item to menu------------------*/
        mFormat.add(iWordWrap);
        mFormat.add(mFontSize);
        mFormat.add(mFamily);
        mFamily.add(iFontArial);
        mFamily.add(iFontCSMS);
        mFamily.add(iFontTNR);
        mFontSize.add(iFontSize8);
        mFontSize.add(iFontSize12);
        mFontSize.add(iFontSize16);
        mFontSize.add(iFontSize20);
        mFontSize.add(iFontSize24);
        mFontSize.add(iFontSize28);

    }


    /**------------Create Theme menu------------------*/
    void createThemeMenu() {
        iLight = new JMenuItem("LightTheme");
        iLight.addActionListener(this);
        iLight.setActionCommand("lightTheme");
        iDark = new JMenuItem("Dark");
        iDark.addActionListener(this);
        iDark.setActionCommand("darkTheme");
        iDarcular = new JMenuItem("darcula");
        iDarcular.addActionListener(this);
        iDarcular.setActionCommand("darcula");
        mTheme.add(iLight);
        mTheme.add(iDark);
        mTheme.add(iDarcular);
    }
    /**------------Create Footer------------------*/
    void createFooter() {
        panelFooter = new JPanel();
        mFooter = new JMenuBar();
        fWords = new JLabel("   Words: " + countWord);
        fLines = new JLabel("Lines: " + countLine);
        fLength = new JLabel("Length: " + textArea.getText().length());
        fWords.setForeground(Color.white);
        fLines.setForeground(Color.white);
        fLength.setForeground(Color.white);

        mFooter.setLayout(new GridLayout(1,3));
        mFooter.add(fWords);
        mFooter.add(fLines);
        mFooter.add(fLength);
        mFooter.setBorder(BorderFactory.createEmptyBorder());
        panelFooter.setLayout(new GridLayout(1,1));
        panelFooter.setPreferredSize(new Dimension(300,40));
        panelFooter.add(mFooter);
        panelFooter.setBorder(BorderFactory.createEmptyBorder());
        window.add(panelFooter, BorderLayout.SOUTH);
    }

    /**------------Create Exit Panel------------------*/
    void createExitPanel(){
        exitPanel = new JPanel();
        exitPanel.setPreferredSize(new Dimension(200,200));
        exitPanel.setBackground(Color.green);
        window.getContentPane().add(exitPanel);
    }


    /**------------Action listener------------------*/
    @Override
    public void actionPerformed(ActionEvent e) {

        String command = e.getActionCommand();

        switch (command){
            /**------------callback file function------------------*/
            case "New":file.newFile();break;
            case "open":file.open();break;
            case "saveAs":file.saveAs();break;
            case "save": file.save();break;
            case "exit": file.exit();

            /**------------callback edit function------------------*/
            case "undo":edit.undo();break;
            case "redo":edit.redo();break;
            case "cut" :edit.cut();break;
            case "copy":edit.copy();break;
            case "paste":edit.paste();break;

            /**------------callback format function------------------*/
            case "word wrap": format.WordWrap();break;
            case "arial":format.setFontFamily("Arial");break;
            case "csms" :format.setFontFamily("Comic Sans MS");break;
            case "tnr":format.setFontFamily("Time New Roman");break;
            case "8":format.createFont(8);break;
            case "12":format.createFont(12);break;
            case "16":format.createFont(16);break;
            case "20":format.createFont(20);break;
            case "24":format.createFont(24);break;
            case "28":format.createFont(28);break;

            /**---------------theme---------*/
            case "lightTheme": theme.theme_default();break;
            case "darkTheme": theme.theme_dark();break;
            case "darcula": theme.theme_darcula();break;

        }
    }
}


package notepad;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.filechooser.*;
import java.io.*;

public class Notepad extends JFrame implements ActionListener{
    JTextArea area;
    String copied_text;
        Notepad() {
          setTitle("Notepad");

          ImageIcon notepadIcon = new ImageIcon(ClassLoader.getSystemResource("notepad/icons/notepad.png"));
          Image icon = notepadIcon.getImage();
          setIconImage(icon);
        
          JMenuBar menubar = new JMenuBar();    //menu bar object
          menubar.setBackground(Color.WHITE);
          
          JMenu file = new JMenu("File");      // creating file menu object
          
          // file menu items
          JMenuItem newdoc= new JMenuItem("New");
          newdoc.addActionListener(this);
          newdoc.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
          newdoc.setFont(new Font ("AERIAL", Font.PLAIN, 14));
          
          JMenuItem open= new JMenuItem("Open");
          open.addActionListener(this);
          open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
          open.setFont(new Font ("AERIAL", Font.PLAIN, 14));
          
          JMenuItem save= new JMenuItem("Save");
          save.addActionListener(this);
          save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
          save.setFont(new Font ("AERIAL", Font.PLAIN, 14));
          
          JMenuItem print= new JMenuItem("Print");
          print.addActionListener(this);
          print.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
          print.setFont(new Font ("AERIAL", Font.PLAIN, 14));
          
          JMenuItem exit= new JMenuItem("Exit");
          exit.addActionListener(this);
          exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0));
          exit.setFont(new Font ("AERIAL", Font.PLAIN, 14));
          
          //adding file menu items to file menu
          file.add(newdoc);
          file.add(open);
          file.add(save);
          file.add(print);
          file.add(exit);
          
          menubar.add(file);  //adding file menu to menu bar
          
          
          JMenu edit = new JMenu("Edit");     // creating edit menu object
          
          // edit menu items
          JMenuItem cut= new JMenuItem("Cut");
          cut.addActionListener(this);
          cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
          cut.setFont(new Font ("AERIAL", Font.PLAIN, 14));
          
          JMenuItem copy= new JMenuItem("Copy");
          copy.addActionListener(this);
          copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
          copy.setFont(new Font ("AERIAL", Font.PLAIN, 14));
          
          JMenuItem paste= new JMenuItem("Paste");
          paste.addActionListener(this);
          paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK));
          paste.setFont(new Font ("AERIAL", Font.PLAIN, 14));
          
          JMenuItem selectall= new JMenuItem("Select all");
          selectall.addActionListener(this);
          selectall.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
          selectall.setFont(new Font ("AERIAL", Font.PLAIN, 14));
          
          //adding edit menu items to edit menu
          edit.add(cut);
          edit.add(copy);
          edit.add(paste);
          edit.add(selectall);
          
          menubar.add(edit);     //adding edit menu to menu bar
          
          
           JMenu help = new JMenu("Help");     // creating help menu object
          
          // help menu items
          JMenuItem about= new JMenuItem("About");
          about.addActionListener(this);
          about.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, ActionEvent.CTRL_MASK));
          about.setFont(new Font ("AERIAL", Font.PLAIN, 14));
          
           //adding help menu items to help menu
          help.add(about);
          
          menubar.add(help);     //adding help menu to menu bar
          
          setJMenuBar(menubar);   //adding menu bar to frame
          
          
          //creating a text area
          area = new JTextArea();
          area.setFont(new Font("SAN_SERIF", Font.PLAIN, 18));
          area.setLineWrap(true);
          area.setWrapStyleWord(true);
          
          JScrollPane pane = new JScrollPane(area);
          pane.setBorder(BorderFactory.createEmptyBorder());
          add(pane);

          setExtendedState(MAXIMIZED_BOTH);   //making frame max size
        
          setVisible(true);  //making frame visible
    }
        
    @Override
    public void actionPerformed(ActionEvent ae){
        if(ae.getActionCommand().equals("New")){   //new menu item actions
        area.setText("");
        }
        
        else if (ae.getActionCommand().equals("Open")) {    //open menu item actions
            JFileChooser chooser = new JFileChooser("D:/Java");
            chooser.setAcceptAllFileFilterUsed(false); 
            FileNameExtensionFilter restrict = new FileNameExtensionFilter("Only text files", "txt"); 
            chooser.addChoosableFileFilter(restrict);
    	            int result = chooser.showOpenDialog(this);
            if(result == JFileChooser.APPROVE_OPTION) {
                File file = chooser.getSelectedFile();				
                try{
                    System.out.println("Notepad");
                    FileReader reader = new FileReader(file);
                    BufferedReader br = new BufferedReader(reader);
                    area.read( br, null );
                    br.close();
                    area.requestFocus();
                }catch(Exception e){
                    System.out.print(e);
                }
            }
        }
            
             else if(ae.getActionCommand().equals("Save")){
            final JFileChooser SaveAs = new JFileChooser();
            SaveAs.setApproveButtonText("Save");
            int actionDialog = SaveAs.showOpenDialog(this);
            if (actionDialog != JFileChooser.APPROVE_OPTION) {
                return;
            }
            File fileName = new File(SaveAs.getSelectedFile() + ".txt");
            BufferedWriter outFile = null;
            try {
                outFile = new BufferedWriter(new FileWriter(fileName));
                area.write(outFile);
            } catch (Exception e) {
                e.printStackTrace();
            }
             }
            
             else if(ae.getActionCommand().equals("Print")){
                 try{
                 area.print();
             }catch(Exception e){}
             }
             
             else if(ae.getActionCommand().equals("Exit")){
                 System.exit(0);
             }
            
             else if(ae.getActionCommand().equals("Cut")){
                 copied_text = area.getSelectedText();
                 area.replaceRange("", area.getSelectionStart(), area.getSelectionEnd());
             }
             
             else if(ae.getActionCommand().equals("Copy")){
                 copied_text = area.getSelectedText();
             }
             else if(ae.getActionCommand().equals("Paste")){
                 area.insert(copied_text, area.getCaretPosition());
             }
             else if(ae.getActionCommand().equals("Select all")){
                 area.selectAll();
             }
             else if(ae.getActionCommand().equals("About")){
                 new About().setVisible(true);
             }  
}
    
    public static void main(String[] args) {
        new Notepad();
        
    }
    }
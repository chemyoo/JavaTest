import java.util.*;
import java.io.*;

import javax.swing.JFileChooser;

public class ReadFileUsingJFileChooser {

    /**
     * @param args
     * @throws Exception 
     */
    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
        JFileChooser jfc=new JFileChooser();
        if(jfc.showOpenDialog(null)==JFileChooser.APPROVE_OPTION){
            File file=jfc.getSelectedFile();
            Scanner input=new Scanner(file);
            while(input.hasNext()){
                System.out.println(input.nextLine());
            }
            input.close();
        }
        else
            System.out.println("No file is selected!");
    }

}
import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
public class ProjectX {
    public static void main(String[] args) throws Exception{
        // Check if source file has extension arxml
        if(!(args[0].matches("^[a-zA-Z0-9._ -]+\\.(arxml)$"))){
            throw new NotVaildAutosarFileException();
        }
        // Create a directory to source file
        File sourceFile = new File(args[0]);
        // Check if source files is empty
        if(sourceFile.length() == 0){
            throw new EmptyAutosarFileException();
        }
        /* Split Code into String Lines 
           Each Line is stored in ArrayList of Strings */
        ArrayList<String> splitedCode = new ArrayList<>();
        try(Scanner in = new Scanner(sourceFile)){
            while(in.hasNextLine()){
                splitedCode.add(in.nextLine());
            }
        }
        /* Create Array of Containers with 
           size = (number of lines - useless lines) / number of container lines */
        Container[] containers = new Container[(splitedCode.size() - 3)/4];
        /* Initialize this array by starting passing line number 3,4,5,6 to 
           first container object , passing the next 4 lines to the second 
           container object and same sequence to the rest of lines */
        int j = 0;
        for(int i = 2;i<splitedCode.size() - 1;i += 4){
            containers[j] = new Container(splitedCode.get(i), splitedCode.get(i + 1), splitedCode.get(i + 2), splitedCode.get(i + 3));
            j++;
        }
        // Sorting Array of Containers according to short name tag
        Arrays.sort(containers);
        // modify name of Source file 
        String modifiedName = args[0].substring(0, args[0].length() - 6);
        // Creating directory to Target File
        File targetFile = new File(modifiedName + "_mod.arxml");
        // Printing Sorted Code into Target File
        try(PrintWriter out = new PrintWriter(targetFile)){
        // Print First Line
        out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        // Print Second Line
        out.println("<AUTOSAR>");
        // Print Containers Lines
        for(int i = 0;i<containers.length;i++){
            out.println(containers[i].getOpenContainerTag());
            out.println(containers[i].getShortNameTag());
            out.println(containers[i].getLongNameTag());
            out.println(containers[i].getCloseContainerTag());
        }
        // Print Last Line
        out.println("</AUTOSAR>");
        }
    }
}

class Container implements Comparable<Container>{
    // Data Fields
    private String openContainerTag;
    private String shortNameTag;
    private String longNameTag;
    private String closeContainerTag;
    private char specialchar;

    // Constructor 
    public Container(String openContainerTag,String shortNameTag,String longNameTag,String closeContainerTag){
        this.openContainerTag = openContainerTag;
        this.shortNameTag = shortNameTag;
        this.longNameTag = longNameTag;
        this.closeContainerTag = closeContainerTag;
        // store last charcter which in short name tag in ContainerX 
        this.specialchar = shortNameTag.charAt((shortNameTag.indexOf("Container")) + 9);
    }
    
    // Getters
    public String getOpenContainerTag() {
        return openContainerTag;
    }

    public String getShortNameTag() {
        return shortNameTag;
    }

    public String getLongNameTag() {
        return longNameTag;
    }

    public String getCloseContainerTag() {
        return closeContainerTag;
    }
    
    @Override
    public int compareTo(Container o) {
        if(this.specialchar > o.specialchar)
            return 1;
        if(this.specialchar < o.specialchar)
            return -1;
        return 0;
    }
}
class NotVaildAutosarFileException extends Exception{
    public NotVaildAutosarFileException(){
        super("NotVaildAutosarFileException");
    }
}
class EmptyAutosarFileException extends RuntimeException{
    public EmptyAutosarFileException(){
        super("EmptyAutosarFileException");
    }
}
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The Node class represents a file or directory node in a file system.
 */
class Node {
    /** The name of the file or directory. */
    String name;

    /** The type of the node (0 for File, 1 for Directory). */
    int type;

    /** The size of the file in bytes. */
    long size;

    /** The last modified date of the file or directory. */
    String date;

    /**
     * Constructs a Node object based on the information from a given File object.
     *
     * @param file The File object representing the file or directory.
     */
    public Node(File file) {
        this.name = file.getName();
        this.type = file.isDirectory() ? 1 : 0;
        this.size = file.length();
        this.date = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(new Date(file.lastModified()));
    }

    /**
     * Returns a string representation of the Node object.
     *
     * @return A string containing information about the node.
     */
    @Override
    public String toString() {
        return "Name: " + name + ", Type: " + (type == 0 ? "File" : "Directory") + ", Size: " + size + " bytes, Date: " + date;
    }
}

/**
 * The Main class demonstrates the usage of the Node class by traversing a directory
 * and printing information about files and directories.
 */
public class Main {
    /**
     * The main method initializes a directory path, traverses the directory, and prints the total size.
     *
     * @param args Command-line arguments (not used in this program).
     */
    public static void main(String[] args) {
        File dir = new File("/home/nishitk/Documents");
        long totalSize = traverse(dir, " ");
        System.out.println("Total size: " + totalSize + " bytes");
    }

    /**
     * Recursively traverses a directory, printing information about files and directories.
     *
     * @param file   The current file or directory being processed.
     * @param indent The indentation for displaying the hierarchical structure.
     * @return The total size of files in the directory and its subdirectories.
     */
    public static long traverse(File file, String indent) {
        long totalSize = 0;
       // System.out.println("Processing: " + file.getAbsolutePath()); 
        if (file.isDirectory()) {
            System.out.println(indent + "Directory: " + file.getName());
            File[] files = file.listFiles();
            if (files != null) {
                for (File f : files) {
                    totalSize += traverse(f, indent + "  ");
                }
            }
        } else {
            Node node = new Node(file);
            System.out.println(indent + node);
            totalSize += node.size;
        }
        return totalSize;
    }
}

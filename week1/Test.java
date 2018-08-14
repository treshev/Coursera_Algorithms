import java.io.*;
import java.util.ArrayList;
import java.util.List;
public class Test {


    public static void main(String[] args) {

        String pathName = System.getProperty("user.dir") + "\\week1\\percolation-testing\\";
        System.out.println("pathName = " + pathName);

//        File[] fileList = getFileNameListFromDir(pathName);
        File file = new File("C:\\!SR\\Aleks\\Coursera\\Algorithm\\week1\\percolation-testing\\input1.txt");
        File[] fileList = new File[1];
        fileList[0] = file;

        for (File fileFromList : fileList) {
            if (fileProcessing(fileFromList)){
                System.out.println("File " + fileFromList.getName() + " was successfully proceeded");
            }
            else  System.out.println("File " + fileFromList.getName() + " IS FAILED!!!");
        }
    }
    private static boolean fileProcessing(File file) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String sCurrentLine;
            int nCount = Integer.parseInt(br.readLine());
            Percolation percolation = new Percolation(nCount);
            System.out.println("############# INIT #############");
            System.out.printf("isOpen(%s, %s) = %s \n", 1, 1, percolation.isOpen(1, 1));
            System.out.printf("percolation = %s \n", percolation.percolates());
            System.out.printf("numberOfOpenSites() = %s \n", percolation.numberOfOpenSites());
            System.out.printf("isfull(%s, %s) = %s \n", 1, 1, percolation.isFull(1, 1));
            System.out.println("############# END INIT #############\n\n");

            while ((sCurrentLine = br.readLine()) != null && sCurrentLine.length() > 0) {
                sCurrentLine = sCurrentLine.trim();
                String[] joinParams = sCurrentLine.split(" ");
                int row = Integer.parseInt(joinParams[0]);
                int col = Integer.parseInt(joinParams[1]);
                percolation.open(row, col);
                //isOpen(), percolates(), numberOfOpenSites(), and isFull()
                System.out.printf("isOpen(%s, %s) = %s \n", row, col, percolation.isOpen(row, col));
                System.out.printf("percolation = %s \n", percolation.percolates());
                System.out.printf("numberOfOpenSites() = %s \n", percolation.numberOfOpenSites());
                System.out.printf("isfull(%s, %s) = %s \n\n", row, col, percolation.isFull(row, col));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
    private static File[] getFileNameListFromDir(String pathName) {
        File folder = new File(pathName);
        return folder.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.toLowerCase().endsWith(".txt");
            }
        });
    }


}
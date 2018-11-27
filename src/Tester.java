import java.awt.*;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

import static java.lang.System.currentTimeMillis;

public class Tester {
    public static void main(String[] args) {
        // create RedBlackTree instance
        RedBlackTree redBlackTree = new RedBlackTree();
        double start = currentTimeMillis();
        String inputLine;

        // ********************************  create dictionary ********************************
        try {
            // read each line and add to the tree
            URL oracle = new URL("http://www.math.sjsu.edu/~foster/dictionary.txt");
            BufferedReader in = new BufferedReader(new InputStreamReader(oracle.openStream()));

            while ((inputLine = in.readLine()) != null) {
                redBlackTree.insert(inputLine);
            }
            in.close();

        } catch (IOException e) {
            System.out.println("MalformedURLException");
            e.printStackTrace();
        }

        double end = currentTimeMillis();
        double duration = end - start;
        System.out.println("The duration of creating the dictionary is " + duration);

        // ******************************** check with dictionary ********************************
        String st;
        String[] words;
        RedBlackTree.Node node;
        boolean isFound = false;
        double startLooking = currentTimeMillis();
        // check the word by calling lookup();
        File file = new File("/Users/sijiagao/IdeaProjects/sjsu.Gao.cs146.project3/src/myPoem");
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            while ((st = br.readLine()) != null) {
                words = st.split("\\s+");
//                words = {"aaaa", "aaaaa"};
                for (String s : words) {
                    node = redBlackTree.lookup(s);
                    // if node is not found, it is null
                    if (node == null) {
                        isFound = false;
                    } else
                        isFound = true;
                }
            }
        } catch (IOException e) {
            System.out.println("FileNotFoundException");
            e.printStackTrace();
        }

        double endLooking = currentTimeMillis();
        double durationLooking = endLooking - startLooking;
        if (isFound){
            System.out.println("The word is found");
        } else {
            System.out.println("The word is not found");
        }
        System.out.println("The duration to check the spell is " + durationLooking);
    }
}
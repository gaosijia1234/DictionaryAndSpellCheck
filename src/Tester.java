import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import java.io.*;

import static java.lang.System.currentTimeMillis;

public class Tester {
    public static void main(String[] args) {

        // create RedBlackTree instance
        RedBlackTree redBlackTree = new RedBlackTree();
        double start = currentTimeMillis();
        String inputLine;

        // ********************************  create dictionary ********************************
        long currentTime = System.currentTimeMillis();
        File file = new File("/Users/sijiagao/IdeaProjects/RedBlackTreeProject-master/dictionary.txt");

        try {
            // read each line and add to the tree
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                String string = scanner.nextLine();
                redBlackTree.insert(string);
            }
            long endingTime = System.currentTimeMillis();
            scanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("MalformedURLException");
            e.printStackTrace();
        }

        double end = currentTimeMillis();
        double duration = end - start;
        System.out.println("The duration of creating the dictionary is " + duration + " milliseconds.");

        // ******************************** check with dictionary ********************************
        String st;
        String[] words;
        RedBlackTree.Node node;
        boolean isFound = false;
        double startLooking = currentTimeMillis();
        // check the word by calling lookup();
        File file2 = new File("/Users/sijiagao/IdeaProjects/sjsu.Gao.cs146.project3/src/myPoem");
        try {
            BufferedReader br = new BufferedReader(new FileReader(file2));
            while ((st = br.readLine()) != null) {
                words = st.split("\\s+");
//                words = new String []{ "Is", "At", "All"};
                for (String s : words) {
                    if(redBlackTree.lookup(s)){
                        isFound = true;
                    }
                    else
                        isFound = false;
                }
            }
        } catch (IOException e) {
            System.out.println("FileNotFoundException");
            e.printStackTrace();
        }

        double endLooking = currentTimeMillis();
        double durationLooking = endLooking - startLooking;
        if (isFound) {
            System.out.println("The word is found");
        } else {
            System.out.println("The word is not found");
        }
        System.out.println("The duration to check the spell is " + durationLooking + " milliseconds.");
    }

    public static String makeString (RedBlackTree t) {
        class MyVisitor implements Visitor {
            String result = "";

            public void visit(RedBlackTree.Node n) {
                result = result + n.key;
            }
        }

        MyVisitor v = new MyVisitor();
        t.preOrderVisit(v);
        return v.result;
    }

    public static String makeStringDetails (RedBlackTree redBlackTree){
        class MyVisitor implements Visitor {
            String result = "";

            public void visit(RedBlackTree.Node n) {
                if (!(n.key).equals(""))
                    if (n.parent == null) {
                        result = result + "Color: " + n.color + ", Key:" + n.key + " Parent: " + "\n";
                    } else {
                        result = result + "Color: " + n.color + ", Key:" + n.key + " Parent: " + n.parent.key + "\n";
                    }

            }
        }
        MyVisitor v = new MyVisitor();
        redBlackTree.preOrderVisit(v);

        return v.result;
    }

}


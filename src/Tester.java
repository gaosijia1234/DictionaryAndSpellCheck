import java.awt.*;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

import static java.lang.System.currentTimeMillis;
import static java.lang.System.setOut;
import static org.junit.Assert.assertEquals;

public class Tester {
    public static void main(String[] args) {

//        RedBlackTree rbt = new RedBlackTree();
//        rbt.insert("D");
//        rbt.insert("B");
//        rbt.insert("A");
//        rbt.insert("C");
//        rbt.insert("F");
//        rbt.insert("E");
//        rbt.insert("H");
//        rbt.insert("G");
//        rbt.insert("I");
//        rbt.insert("J");
//
//        System.out.println(makeString(rbt));
//
//        String str = "Color: 1, Key:D Parent: \n" +
//                "Color: 1, Key:B Parent: D\n" +
//                "Color: 1, Key:A Parent: B\n" +
//                "Color: 1, Key:C Parent: B\n" +
//                "Color: 1, Key:F Parent: D\n" +
//                "Color: 1, Key:E Parent: F\n" +
//                "Color: 0, Key:H Parent: F\n" +
//                "Color: 1, Key:G Parent: H\n" +
//                "Color: 1, Key:I Parent: H\n" +
//                "Color: 0, Key:J Parent: I\n";
//
//        System.out.println(makeStringDetails(rbt));
//
//        boolean result = rbt.lookup("J");
//        if (result) {
//            System.out.println("found");
//        } else {
//            System.out.println("not found");
//        }

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
        System.out.println("The duration to check the spell is " + durationLooking);
    }

    public static String makeString (RedBlackTree t)
    {
        class MyVisitor implements RedBlackTree.Visitor {
            String result = "";

            public void visit(RedBlackTree.Node n) {
                result = result + n.key;
            }
        }
        ;
        MyVisitor v = new MyVisitor();
        t.preOrderVisit(v);
        return v.result;
    }

    public static String makeStringDetails (RedBlackTree redBlackTree){
        {
            class MyVisitor implements RedBlackTree.Visitor {
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
            ;
            MyVisitor v = new MyVisitor();
            redBlackTree.preOrderVisit(v);

            return v.result;
        }
    }

}


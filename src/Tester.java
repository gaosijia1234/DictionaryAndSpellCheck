import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import java.awt.*;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import static java.lang.System.currentTimeMillis;
import static java.lang.System.setOut;
import static org.junit.Assert.assertEquals;

public class Tester {
    public static void main(String[] args) {
        RedBlackTree rbt = new RedBlackTree();
        rbt.insert("D");
        rbt.insert("B");
        rbt.insert("A");
        rbt.insert("C");
        rbt.insert("F");
        rbt.insert("E");
        rbt.insert("H");
        rbt.insert("G");
        rbt.insert("I");
        rbt.insert("J");
//        rbt.insert("K");

        System.out.println("makeString(rbt)" + makeString(rbt));
        System.out.println("makeStringDetails(rbt)" + makeStringDetails(rbt));

        boolean result = rbt.lookup("J");
        if (result) {
            System.out.println("found");
        } else {
            System.out.println("not found");
        }

//        // create RedBlackTree instance
//        RedBlackTree redBlackTree = new RedBlackTree();
//        double start = currentTimeMillis();
//        String inputLine;
//
//        // ********************************  create dictionary ********************************
//        long currentTime = System.currentTimeMillis();
//        File file = new File("/Users/sijiagao/IdeaProjects/RedBlackTreeProject-master/dictionary.txt");
//
//        try {
//            // read each line and add to the tree
//
////            while ((inputLine = in.readLine()) != null) {
////                redBlackTree.insert(inputLine);
////            }
////            in.close();
//            Scanner scanner = new Scanner(file);
//            while (scanner.hasNext()) {
//                String string = scanner.nextLine();
//                redBlackTree.insert(string);
//            }
//            long endingTime = System.currentTimeMillis();
//            scanner.close();
//
//        } catch (FileNotFoundException e) {
//            System.out.println("MalformedURLException");
//            e.printStackTrace();
//        }
//
//        double end = currentTimeMillis();
//        double duration = end - start;
//        System.out.println("The duration of creating the dictionary is " + duration);
//
//        // ******************************** check with dictionary ********************************
//        String st;
//        String[] words;
//        RedBlackTree.Node node;
//        boolean isFound = false;
//        double startLooking = currentTimeMillis();
//        // check the word by calling lookup();
//        File file2 = new File("/Users/sijiagao/IdeaProjects/sjsu.Gao.cs146.project3/src/myPoem");
//        try {
//            BufferedReader br = new BufferedReader(new FileReader(file2));
//            while ((st = br.readLine()) != null) {
//                words = st.split("\\s+");
////                words = {"aaaa", "aaaaa"};
//                for (String s : words) {
//                    if(redBlackTree.lookup(s)){
//                        isFound = true;
//                    }
//                    else
//                        isFound = false;
//                }
//            }
//        } catch (IOException e) {
//            System.out.println("FileNotFoundException");
//            e.printStackTrace();
//        }
//
//        double endLooking = currentTimeMillis();
//        double durationLooking = endLooking - startLooking;
//        if (isFound) {
//            System.out.println("The word is found");
//        } else {
//            System.out.println("The word is not found");
//        }
//        System.out.println("The duration to check the spell is " + durationLooking);
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
//
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
//
//public class Tester {
//    public static void main(String[] args) {
//
//    File file = new File("/Users/sijiagao/IdeaProjects/sjsu.Gao.cs146.project3/src/dictionary.txt");
//    RedBlackTree dictionary = new RedBlackTree();
//    try {
//        Scanner scanner = new Scanner(file);
//        long currentTime = System.currentTimeMillis();
//        while (scanner.hasNext()) {
//            String string = scanner.nextLine();
//
//            System.out.println(string);
//            dictionary.insert(string);
//        }
//                System.out.println(makeStringDetails(dictionary));
//        long endingTime = System.currentTimeMillis();
//        scanner.close();
//        int totalTime = (int) (endingTime - currentTime);
//        System.out.println("The total time to insert is: " + totalTime + " ms");
//
//        currentTime = System.currentTimeMillis();
//        boolean searchResult = dictionary.lookup("aaaa");
//        endingTime = System.currentTimeMillis();
//        long totalTimeMs = (endingTime - currentTime);
//        if (searchResult) {
//            System.out.println("The time to look up a string in Dictionary " + totalTimeMs + " ms");
//            System.out.println("That's some fast search right there! :)");
//        } else {
//            System.out.println("Your word is not in our dictionary!");
//            System.out.println("Time for the unsuccessful search... " + totalTimeMs + " ms");
//        }
//        //the line below can be uncommented to check the preOrderVisit
//        //dictionary.preOrderVisit();
//    } catch (FileNotFoundException e) {
//        e.printStackTrace();
//    }
//
//}
//    public static String makeString (RedBlackTree t)
//    {
//        class MyVisitor implements RedBlackTree.Visitor {
//            String result = "";
//
//            public void visit(RedBlackTree.Node n) {
//                result = result + n.key;
//            }
//        }
//        ;
//        MyVisitor v = new MyVisitor();
//        t.preOrderVisit(v);
//        return v.result;
//    }
//    //
//    public static String makeStringDetails (RedBlackTree redBlackTree){
//        {
//            class MyVisitor implements RedBlackTree.Visitor {
//                String result = "";
//
//                public void visit(RedBlackTree.Node n) {
//                    if (!(n.key).equals(""))
//                        if (n.parent == null) {
//                            result = result + "Color: " + n.color + ", Key:" + n.key + " Parent: " + "\n";
//                        } else {
//                            result = result + "Color: " + n.color + ", Key:" + n.key + " Parent: " + n.parent.key + "\n";
//                        }
//
//                }
//            }
//            ;
//            MyVisitor v = new MyVisitor();
//            redBlackTree.preOrderVisit(v);
//
//            return v.result;
//        }
//    }
//}

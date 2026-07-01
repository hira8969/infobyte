package com.exam.repository;

import com.exam.model.Question;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class QuestionRepository {
    private final List<Question> questions = new ArrayList<>();

    public QuestionRepository() {
        seedQuestions();
    }

    public List<Question> findAll() {
        return new ArrayList<>(questions);
    }

    private void seedQuestions() {
        add(1, "What is JVM?", "Java Virtual Machine", "Java Vendor Machine", "Java Visual Model", "None", "A");
        add(2, "Which keyword is used to inherit a class in Java?", "implements", "extends", "inherits", "super", "B");
        add(3, "Which method is the entry point of a Java application?", "start()", "run()", "main()", "init()", "C");
        add(4, "Which collection does not allow duplicate elements?", "List", "Queue", "Set", "Map", "C");
        add(5, "Which package contains Swing classes?", "java.awt", "javax.swing", "java.swing", "javax.ui", "B");
        add(6, "Which access modifier is most restrictive?", "public", "protected", "private", "default", "C");
        add(7, "What is encapsulation?", "Hiding data with methods", "Running threads", "Compiling code", "Creating packages", "A");
        add(8, "Which exception is checked?", "NullPointerException", "IOException", "ArithmeticException", "ArrayIndexOutOfBoundsException", "B");
        add(9, "Which interface is used for button click events?", "Runnable", "Serializable", "ActionListener", "MouseWheelListener", "C");
        add(10, "Which layout manager supports card switching?", "GridLayout", "CardLayout", "FlowLayout", "BorderLayout", "B");
        add(11, "What does JDBC stand for?", "Java Database Connectivity", "Java Data Binary Code", "Joint Database Compiler", "Java Design Connector", "A");
        add(12, "Which keyword prevents inheritance?", "static", "final", "abstract", "volatile", "B");
        add(13, "Which class is immutable?", "StringBuilder", "StringBuffer", "String", "ArrayList", "C");
        add(14, "Which operator compares object references?", "=", "==", "equals", "!=", "B");
        add(15, "Which OOP concept allows method overloading?", "Polymorphism", "Abstraction", "Composition", "Association", "A");
        add(16, "Which component displays tabular data in Swing?", "JList", "JTable", "JTree", "JPanel", "B");
        add(17, "Which class creates a password input field?", "JTextField", "JPasswordField", "JLabel", "JTextArea", "B");
        add(18, "Which block always executes after try/catch?", "throw", "throws", "finally", "final", "C");
        add(19, "Which principle says a class should have one reason to change?", "OCP", "SRP", "DIP", "LSP", "B");
        add(20, "Which Java feature provides multiple threads of execution?", "Serialization", "Reflection", "Multithreading", "Generics", "C");
    }

    private void add(int id, String text, String a, String b, String c, String d, String correct) {
        Map<String, String> options = new LinkedHashMap<>();
        options.put("A", a);
        options.put("B", b);
        options.put("C", c);
        options.put("D", d);
        questions.add(new Question(id, text, options, correct));
    }
}

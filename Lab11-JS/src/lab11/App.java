package lab11;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class App extends JFrame {
    private JPanel mainPanel;
    private JPanel leftPanel;
    private JPanel rightPanel;
    private JTextArea inputTextArea;
    private JEditorPane outputTextArea;
    private JLabel inputLabel;
    private JLabel outputLabel;
    private JComboBox<String> comboScripts;
    private JButton formatButton;
    private JScrollPane inputScroll;
    private JScrollPane outputScroll;

    private ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
    private List<String> files = null;

    public App(String title) throws HeadlessException {
        super(title);

        files = getJSFiles();
        comboScripts.setModel(new DefaultComboBoxModel<>(files.toArray(new String[0])));
        outputTextArea.setEditable(false);
        outputTextArea.setContentType("text/html");
        outputTextArea.setMinimumSize(new Dimension(200,200));
        inputTextArea.setMinimumSize(new Dimension(200,200));
        inputScroll.setMinimumSize(new Dimension(200,200));
        outputScroll.setMinimumSize(new Dimension(200,200));
        rightPanel.setMaximumSize(new Dimension(300,600));

        try {
            engine.eval(new FileReader("moralizator.js"));
        } catch (ScriptException | FileNotFoundException e) {
            e.printStackTrace();
        }

        setResizable(false);
        setMinimumSize(new Dimension(600,600));
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        comboScripts.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                comboBoxAction();
            }
        });
        formatButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                formatAction();
            }
        });
    }

    private void formatAction() {
        if (Objects.requireNonNull(comboScripts.getSelectedItem()).toString().equals("None")){
            outputTextArea.setText(inputTextArea.getText());
            return;
            }

        outputTextArea.setText("");
        Invocable invocable = (Invocable) engine;

        Object result = null;
        try {
            result = invocable.invokeFunction("process", inputTextArea.getText());
        } catch (ScriptException | NoSuchMethodException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
        assert result != null;
        outputTextArea.setText(result.toString());
    }

    private void comboBoxAction() {
        String converterName = (String)comboScripts.getSelectedItem();

        assert converterName != null;
        if (converterName.equals("None")){
            return;
        }

        try {
            engine.eval(new FileReader(converterName));
        } catch (FileNotFoundException | ScriptException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private List<String> getJSFiles(){
        File dir = new File(".");
        File [] files = dir.listFiles((File dir1, String name1) -> name1.endsWith(".js"));
        LinkedList<String> result = new LinkedList<String>();
        assert files != null;
        result.add("None");
        for (File file : files) {
            result.push(file.getName());
        }

        return result;
    }

    public static void main(String[] args) {
        App service = new App("JS formatting");
    }

}

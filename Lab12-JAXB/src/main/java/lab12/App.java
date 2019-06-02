package lab12;

import lab12.offerPackage.OfferType;

import javax.swing.*;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class App extends JFrame {
    private JPanel mainPanel;
    private JPanel leftPanel;
    private JPanel rightPanel;
    private JTextArea inputTextArea;
    private JEditorPane outputTextArea;
    private JLabel inputLabel;
    private JLabel outputLabel;
    private JComboBox<String> comboStyles;
    private JComboBox<String> comboOffers;
    private JButton formatButton;
    private JScrollPane inputScroll;
    private JScrollPane outputScroll;
    private JButton editAddButton;

    private List<String> xmlFiles = null;
    private List<String> styleFiles = null;

    public App(String title) {
        super(title);

        xmlFiles = getXmlOffers();
        styleFiles = getStyles();
        comboOffers.setModel(new DefaultComboBoxModel<>(xmlFiles.toArray(new String[0])));
        comboStyles.setModel(new DefaultComboBoxModel<>(styleFiles.toArray(new String[0])));
        outputTextArea.setEditable(false);
        outputTextArea.setContentType("text/html");
        outputTextArea.setMinimumSize(new Dimension(200,200));
        inputTextArea.setMinimumSize(new Dimension(200,200));
        inputScroll.setMinimumSize(new Dimension(200,200));
        outputScroll.setMinimumSize(new Dimension(200,200));
        rightPanel.setMaximumSize(new Dimension(300,600));

        setResizable(false);
        setMinimumSize(new Dimension(600,600));
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        comboOffers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                comboOffersAction();
            }
        });
        formatButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                formatAction();
            }
        });
        comboStyles.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                comboStylesAction();
            }
        });
        editAddButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editAction();
            }
        });
    }

    private void editAction() {
        String converterName = (String)comboOffers.getSelectedItem();

        assert converterName != null;
        if (converterName.equals("None")){
            new OfferDialog(null);
        }else{
            OfferType offer;
            File file = new File("./dane/" + converterName);
            JAXBContext jaxbContext = null;
            try {
                jaxbContext = JAXBContext.newInstance(OfferType.class);
                Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
                offer = (OfferType) jaxbUnmarshaller.unmarshal(file);
                new OfferDialog(offer);
            } catch (JAXBException e) {
                e.printStackTrace();
            }
        }
    }

    private void comboStylesAction() {
        styleFiles = getStyles();
        comboStyles.setModel(new DefaultComboBoxModel<>(styleFiles.toArray(new String[0])));
        String converterName = (String)comboStyles.getSelectedItem();

        assert converterName != null;
        if (converterName.equals("None")){
            return;
        }
    }

    private void formatAction() {
        if (Objects.requireNonNull(comboOffers.getSelectedItem()).toString().equals("None")){
            outputTextArea.setText(inputTextArea.getText());
            return;
            }

        outputTextArea.setText("");

    }

    private void comboOffersAction() {
        xmlFiles = getXmlOffers();
        comboOffers.setModel(new DefaultComboBoxModel<>(xmlFiles.toArray(new String[0])));
        String converterName = (String)comboOffers.getSelectedItem();

        assert converterName != null;
        if (converterName.equals("None")){
            return;
        }

    }

    private List<String> getXmlOffers(){
        File dir = new File("./dane");
        File [] files = dir.listFiles((File dir1, String name1) -> name1.endsWith(".xml"));
        LinkedList<String> result = new LinkedList<String>();
        result.add("None");
        if (files != null) {
            for (File file : files) {
                result.push(file.getName());
            }
        }

        return result;
    }

    private List<String> getStyles(){
        File dir = new File("./transformacje");
        File [] files = dir.listFiles((File dir1, String name1) -> name1.endsWith(".xsl"));
        LinkedList<String> result = new LinkedList<String>();
        result.add("None");
        if (files != null) {
            for (File file : files) {
                result.push(file.getName());
            }
        }

        return result;
    }

    public static void main(String[] args) {
        App service = new App("Java TZ JAXB");
    }

}

package lab12;

import lab12.offerPackage.OfferType;

import javax.swing.*;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class App extends JFrame {
    private JPanel mainPanel;
    private JPanel leftPanel;
    private JPanel rightPanel;
    private JEditorPane outputTextArea;
    private JLabel outputLabel;
    private JComboBox<String> comboStyles;
    private JComboBox<String> comboOffers;
    private JButton formatButton;
    private JScrollPane outputScroll;
    private JButton editAddButton;
    private JButton refresh;

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
        refresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refreshFiles();
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

    private void refreshFiles(){
        styleFiles = getStyles();
        comboStyles.setModel(new DefaultComboBoxModel<>(styleFiles.toArray(new String[0])));
        xmlFiles = getXmlOffers();
        comboOffers.setModel(new DefaultComboBoxModel<>(xmlFiles.toArray(new String[0])));
    }

    private void comboStylesAction() {
        String converterName = (String)comboStyles.getSelectedItem();

        assert converterName != null;
        if (converterName.equals("None")){
            return;
        }
    }

    private void formatAction() {
        if (Objects.requireNonNull(comboOffers.getSelectedItem()).toString().equals("None") && Objects.requireNonNull(comboStyles.getSelectedItem()).toString().equals("None")){
            JOptionPane.showMessageDialog(null,"something is `None`");
            return;
            }
        StringWriter sw = new StringWriter();



        Source xml = new StreamSource(new File("./dane/" + (String)comboOffers.getSelectedItem()));
        Source xslt = new StreamSource("./transformacje/" + (String)comboStyles.getSelectedItem());

        try {

            FileWriter fw = new FileWriter("out.html");
            TransformerFactory tFactory = TransformerFactory.newInstance();
            Transformer trasform = tFactory.newTransformer(xslt);
            trasform.transform(xml, new StreamResult(sw));
            fw.write(sw.toString());
            fw.close();

            System.out
                    .println("out.html generated successfully");

        } catch (IOException | TransformerException | TransformerFactoryConfigurationError e) {
            e.printStackTrace();
        }

        try {
            outputTextArea.setText(new String(Files.readAllBytes(Paths.get("out.html"))));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void comboOffersAction() {
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

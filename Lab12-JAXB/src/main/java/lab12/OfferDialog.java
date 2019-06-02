package lab12;

import lab12.offerPackage.ObjectFactory;
import lab12.offerPackage.OfferType;

import javax.swing.*;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import java.awt.event.*;
import java.io.File;
import java.math.BigInteger;
import java.util.Date;

public class OfferDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField idTextField;
    private JTextField officeDataTextField;
    private JTextField priceTextField;
    private JTextField descriptionTextField;
    private JLabel id;
    private JLabel officeData;
    private JLabel price;
    private JLabel description;

    OfferType offer;

    public OfferDialog(OfferType offerType) {

        if (offerType==null){
            offer = new OfferType();
            try {
                offer.setEndDate(DatatypeFactory.newInstance().newXMLGregorianCalendar());
                offer.setStartDate(DatatypeFactory.newInstance().newXMLGregorianCalendar());
            } catch (DatatypeConfigurationException e) {
                e.printStackTrace();
            }
            offer.setId(new BigInteger("0"));
            offer.setPrice(1000);
            offer.setDescription("DSC");
            offer.setOfficeData("office data");
        }else {
            offer = offerType;
        }

        idTextField.setText(offer.getId().toString());
        officeDataTextField.setText(offer.getOfficeData());
        priceTextField.setText(Float.toString(offer.getPrice()));
        descriptionTextField.setText(offer.getDescription());

        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        pack();
        setVisible(true);
    }

    private void onOK() {

        offer.setId(BigInteger.valueOf(Integer.parseInt(idTextField.getText())));
        offer.setPrice(Float.parseFloat(priceTextField.getText()));
        offer.setDescription(descriptionTextField.getText());
        offer.setOfficeData(officeDataTextField.getText());
        try {
            offer.setEndDate(DatatypeFactory.newInstance().newXMLGregorianCalendar());
            offer.setStartDate(DatatypeFactory.newInstance().newXMLGregorianCalendar());
        } catch (DatatypeConfigurationException e) {
            e.printStackTrace();
        }

        JAXBContext jaxbContext = null;
        try {
            jaxbContext = JAXBContext.newInstance( OfferType.class );
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            jaxbMarshaller.setProperty( Marshaller.JAXB_FORMATTED_OUTPUT, true );

            jaxbMarshaller.marshal( offer, new File( "./dane/" + offer.getId() + ".xml" ) );
            jaxbMarshaller.marshal( offer, System.out );
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        dispose();
    }

    private void onCancel() {
        dispose();
    }
}

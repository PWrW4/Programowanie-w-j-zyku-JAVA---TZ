package lab09;

import javafx.stage.FileChooser;

import javax.crypto.NoSuchPaddingException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

public class App extends JFrame {
    private JButton encryptFileButton;
    private JButton decryptFileButton;
    private JPanel bottomPanel;
    private JPanel topPanel;
    private JPanel MainPanel;
    private JButton choseFileButton;
    private JLabel FilePath;
    private JButton generateKeysButton;
    private JButton chosePrivateKeyButton;
    private JButton chosePublicKeyButton;

    Encryptor enc;
    KeyGenerator kgen;
    File f = null;
    File publicKey=null;
    File privateKey=null;

    public App(String title) throws NoSuchPaddingException, NoSuchAlgorithmException, NoSuchProviderException {
        super(title);
        enc = new Encryptor();
        kgen = new KeyGenerator(1024);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(600,600));
        setContentPane(MainPanel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        choseFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                choseFile();
            }
        });
        decryptFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    decrypt();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        encryptFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    encrypt();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        generateKeysButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                kgen.createKeys();
            }
        });
        chosePrivateKeyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                chooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
                int returnVal = chooser.showOpenDialog(null);
                if(returnVal == JFileChooser.APPROVE_OPTION) {
                    System.out.println("You chose to open this file: " +
                            chooser.getSelectedFile().getName());
                    privateKey = chooser.getSelectedFile();
                }
            }
        });
        chosePublicKeyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                chooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
                int returnVal = chooser.showOpenDialog(null);
                if(returnVal == JFileChooser.APPROVE_OPTION) {
                    System.out.println("You chose to open this file: " +
                            chooser.getSelectedFile().getName());
                    publicKey = chooser.getSelectedFile();
                }
            }
        });
    }

    private void encrypt() throws IOException {
        try {
            enc.encryptFile(enc.getFileInBytes(f),f,enc.getPrivate(privateKey.getAbsolutePath()));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Encrypt error");
            e.printStackTrace();
        }
    }

    private void decrypt() throws IOException {
        try {
            enc.decryptFile(enc.getFileInBytes(f),f,enc.getPublic(publicKey.getAbsolutePath()));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Decrypt error");
            e.printStackTrace();
        }
    }

    private void choseFile() {
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
        int returnVal = chooser.showOpenDialog(null);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            System.out.println("You chose to open this file: " +
                    chooser.getSelectedFile().getName());
            f = chooser.getSelectedFile();
            FilePath.setText(f.getAbsolutePath());
        }
    }


    public static void main(String[] args) throws NoSuchPaddingException, NoSuchAlgorithmException, NoSuchProviderException {
        App app = new App("Cryptography");
    }
}

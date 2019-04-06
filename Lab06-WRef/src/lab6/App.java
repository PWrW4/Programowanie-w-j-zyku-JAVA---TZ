package lab6;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Objects;

public class App extends JFrame {

    public ArrayList<WeakReference<ImageIcon>> weakImages = new ArrayList<WeakReference<ImageIcon>>();

    

    final int numberOfPictures = 6;
    final int showedAtOnce = 2;
    private int currentIndex = 0;


    private JLabel jLabeOne;
    private JLabel jLabeTwo;

    private JPanel panelik;
    private JPanel buttonPanelik;
    private JPanel finalPanelik;

    private JButton upButton;
    private JButton downButton;

    public App(){
        initImages();

        finalPanelik = new JPanel();
        finalPanelik.setLayout(new BoxLayout(finalPanelik,BoxLayout.Y_AXIS));

        panelik = new JPanel();

        buttonPanelik = new JPanel();

        jLabeOne = new JLabel();
        jLabeTwo = new JLabel();

        upButton = new JButton("^^UP^^");
        downButton = new JButton("vvDOWNvv");


        if (weakImages.get(0).get()!=null){
            jLabeOne.setIcon(weakImages.get(0).get());
        }else{
            jLabeOne.setIcon(loadIcon("0.jpg"));
            weakImages.set(0,new WeakReference<ImageIcon>((ImageIcon) jLabeOne.getIcon()));
        }
        if (weakImages.get(1).get()!=null){
            jLabeTwo.setIcon(weakImages.get(1).get());
        }else{
            jLabeTwo.setIcon(loadIcon("1.jpg"));
            weakImages.set(1,new WeakReference<ImageIcon>((ImageIcon) jLabeTwo.getIcon()));
        }

        jLabeOne.setMinimumSize(new Dimension(500,250));
        jLabeTwo.setMinimumSize(new Dimension(500,250));

        upButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                upAction();
            }
        });

        downButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                downAction();
            }
        });

        buttonPanelik.add(upButton);
        buttonPanelik.add(downButton);

        panelik.add(jLabeOne);
        panelik.add(jLabeTwo);

        panelik.setMinimumSize(new Dimension(500,500));
        panelik.setPreferredSize(new Dimension(500,500));
        panelik.setMaximumSize(new Dimension(500,500));

        buttonPanelik.setMinimumSize(new Dimension(500,100));
        buttonPanelik.setPreferredSize(new Dimension(500,100));
        buttonPanelik.setMaximumSize(new Dimension(500,100));

        finalPanelik.setMinimumSize(new Dimension(600,700));
        finalPanelik.setPreferredSize(new Dimension(600,700));
        finalPanelik.setMaximumSize(new Dimension(600,700));

        setMinimumSize(new Dimension(600,700));
        setPreferredSize(new Dimension(600,700));
        setMaximumSize(new Dimension(600,700));

        finalPanelik.add(buttonPanelik);
        finalPanelik.add(panelik);

        add(finalPanelik);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    private void upAction() {
        if (currentIndex == 0){
            return;
        }
        currentIndex--;
        jLabeTwo.setIcon(jLabeOne.getIcon());
//        jLabeOne.setIcon(loadIcon(Integer.toString(currentIndex)+".jpg"));
        if (weakImages.get(currentIndex).get()!=null){
            System.out.println("UA from reference");
            jLabeOne.setIcon(weakImages.get(currentIndex).get());
        }else {
            System.out.println("UA loading");
            jLabeOne.setIcon(loadIcon(Integer.toString(currentIndex) +".jpg"));
            weakImages.set(currentIndex,new WeakReference<ImageIcon>((ImageIcon) jLabeOne.getIcon()));
        }
        System.out.println(Integer.toString(currentIndex)+" "+Integer.toString(currentIndex+1));
    }

    private void downAction() {
        if (currentIndex >= numberOfPictures-showedAtOnce){
            return;
        }
        currentIndex++;
        jLabeOne.setIcon(jLabeTwo.getIcon());
//        jLabeTwo.setIcon(loadIcon(Integer.toString(currentIndex+1)+".jpg"));
        if (weakImages.get(currentIndex+1).get()!=null){
            System.out.println("DA from reference");
            jLabeTwo.setIcon(weakImages.get(currentIndex+1).get());
        }else {
            System.out.println("DA loading");
            jLabeTwo.setIcon(loadIcon(Integer.toString(currentIndex+1) +".jpg"));
            weakImages.set(currentIndex+1,new WeakReference<ImageIcon>((ImageIcon) jLabeTwo.getIcon()));
        }
        System.out.println(Integer.toString(currentIndex)+" "+Integer.toString(currentIndex+1));
    }

    private ImageIcon loadIcon(String file){
        BufferedImage tmp = null;
        Image scalledImage;

        try {
            tmp = ImageIO.read(new File(file));
        } catch (IOException e) {
            e.printStackTrace();
        }

        scalledImage = Objects.requireNonNull(tmp).getScaledInstance(500,250,Image.SCALE_SMOOTH);

        return new ImageIcon(scalledImage);
    }

    private void initImages() {
        for (int i=0;i<numberOfPictures;i++){
            System.out.println("adding to array " + Integer.toString(i) + ".jpg");
            weakImages.add(new WeakReference<ImageIcon>(null));
        }

    }

    public static void main(String [] args)
    {
        new App();
    }
}

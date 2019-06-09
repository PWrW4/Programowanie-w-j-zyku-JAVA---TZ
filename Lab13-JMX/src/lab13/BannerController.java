package lab13;

import javax.management.*;
import javax.swing.*;
import java.lang.management.ManagementFactory;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicBoolean;

public class BannerController implements BannerControllerMBean, Runnable {

    private HashMap<Integer, Advertisement> hmap = new HashMap<Integer, Advertisement>();

    private int k = 0;
    private AtomicBoolean running = new AtomicBoolean();

    private JTextPane adField;
    private JLabel timerText;
    private JLabel idText;

    private Thread t;

    private ObjectName tmpObjectName = null;
    private MBeanServer server = null;

    public BannerController(JTextPane text, JLabel timer, JLabel id) {

        adField = text;
        timerText = timer;
        idText = id;

        try {
            tmpObjectName = new ObjectName("lab13.Advertisement:type=Controller,name=BannerController");
        } catch (MalformedObjectNameException e) {
            e.printStackTrace();
        }

        server = ManagementFactory.getPlatformMBeanServer();

        try {
            server.registerMBean(this, tmpObjectName);
        } catch (InstanceAlreadyExistsException | MBeanRegistrationException | NotCompliantMBeanException e) {
            e.printStackTrace();
        }
    }

    public HashMap<Integer, Advertisement> getHmap() {
        return hmap;
    }

    public void setHmap(HashMap<Integer, Advertisement> hmap) {
        this.hmap = hmap;
    }

    public void addAdvert(int time, String adv){
        Advertisement advert = new Advertisement();
        advert.setAddvTime(time);
        advert.setAddvString(adv);

        hmap.put(k,advert);


        try {
            tmpObjectName = new ObjectName("lab13.Advertisement:type=Advert,name=Advert_"+k);
        } catch (MalformedObjectNameException e) {
            e.printStackTrace();
        }

        try {
            server.registerMBean(advert, tmpObjectName);
        } catch (InstanceAlreadyExistsException | MBeanRegistrationException | NotCompliantMBeanException e) {
            e.printStackTrace();
        }
        k++;
    }

    @Override
    public void Start() {
        running.set(true);
        t = new Thread(this);
        t.start();
    }

    @Override
    public void Stop() {
        running.set(false);
    }

    @Override
    public void run() {
        while (running.get()){
            for (int key : hmap.keySet()) {
                if (!running.get()){
                    break;
                }
                timerText.setText(Integer.toString(hmap.get(key).getAddvTime()));
                adField.setText(hmap.get(key).getAddvString());
                idText.setText(Integer.toString(key));
                try {
                    Thread.sleep(hmap.get(key).getAddvTime());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        timerText.setText("");
        adField.setText("");
    }
}

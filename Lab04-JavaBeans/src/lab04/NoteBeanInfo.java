package lab04;

import ziarenka.TitlePositionEditor;

import javax.imageio.ImageIO;
import java.awt.*;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.beans.SimpleBeanInfo;
import java.io.IOException;
import java.util.Objects;

public class NoteBeanInfo extends SimpleBeanInfo {

    public Image getIcon(int iconType) {
        String name;
        if (iconType == BeanInfo.ICON_COLOR_16x16)
            name = "COLOR_16x16";
        else if (iconType == BeanInfo.ICON_COLOR_32x32)
            name = "COLOR_32x32";
        else
            return null;
        Image im = null;
        try {
            im = ImageIO.read(Objects.requireNonNull(NoteBeanInfo.class.getClassLoader().getResourceAsStream("note_" + name + ".png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return im;
    }

    public PropertyDescriptor[] getPropertyDescriptors() {
        try {

            return new PropertyDescriptor[] {
                    new PropertyDescriptor("noteTitle", Note.class),
                    new PropertyDescriptor("minX", Note.class),
                    new PropertyDescriptor("minY", Note.class),
            };
        } catch (IntrospectionException e) {
            e.printStackTrace();
            return null;
        }
    }
}

package lab04;

import ziarenka.TitlePositionEditor;

import javax.imageio.ImageIO;
import java.awt.*;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.beans.SimpleBeanInfo;
import java.io.IOException;

public class NoteBeanBeanInfo extends SimpleBeanInfo {

    public Image getIcon(int iconType) {
        String name = "";
        if (iconType == BeanInfo.ICON_COLOR_16x16)
            name = "COLOR_16x16";
        else if (iconType == BeanInfo.ICON_COLOR_32x32)
            name = "COLOR_32x32";
        else
            return null;
        Image im = null;
        try {
            im = ImageIO.read(NoteBeanBeanInfo.class.getClassLoader().getResourceAsStream("note_" + name + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return im;
    }

    public PropertyDescriptor[] getPropertyDescriptors() {
        try {
            PropertyDescriptor titlePositionDescriptor = new PropertyDescriptor("titlePosition", NoteBean.class);
            titlePositionDescriptor.setPropertyEditorClass(TitlePositionEditor.class);
//            PropertyDescriptor valuesDescriptor = new PropertyDescriptor("values", NoteBean.class);
//            valuesDescriptor.setPropertyEditorClass(DoubleArrayEditor.class);

            return new PropertyDescriptor[] {
                    new PropertyDescriptor("title", NoteBean.class), titlePositionDescriptor,
            };
        } catch (IntrospectionException e) {
            e.printStackTrace();
            return null;
        }
    }
}

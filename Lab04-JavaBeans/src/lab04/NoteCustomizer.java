package lab04;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.beans.*;

public class NoteCustomizer extends JPanel implements Customizer {

    private Note bean;
    private JTextField titleField;

    public NoteCustomizer(){

        setLayout(new BorderLayout());

        titleField = new JTextField();
        titleField.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent evt) {
                setTitle(titleField.getText());
            }

            public void insertUpdate(DocumentEvent evt) {
                setTitle(titleField.getText());
            }

            public void removeUpdate(DocumentEvent evt) {
                setTitle(titleField.getText());
            }
        });

        add(titleField, BorderLayout.NORTH);

    }

    public void setTitle(String newValue) {
        if (bean == null)
            return;
        String oldValue = bean.getNoteTitle();
        bean.setNoteTitle(newValue);
        firePropertyChange("title", oldValue, newValue);
    }

    @Override
    public void setObject(Object obj) {
        bean = (Note) obj;

        titleField.setText(bean.getNoteTitle());
    }
}

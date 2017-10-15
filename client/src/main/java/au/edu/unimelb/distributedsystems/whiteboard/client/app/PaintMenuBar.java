
package au.edu.unimelb.distributedsystems.whiteboard.client.app;

import au.edu.unimelb.distributedsystems.whiteboard.client.tools.FillerElement;
import au.edu.unimelb.distributedsystems.whiteboard.client.app.ClientLoginGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

public class PaintMenuBar extends JMenuBar 
{
    JMenu file;
        JMenuItem closeWhiteboard;
        JMenuItem newWhiteboard;
        JMenuItem openWhiteboard;
        JMenuItem saveWhiteboard; 
        JMenuItem saveAsWhiteboard;
    JMenu disconnect;
    JMenu clients;

    PaintMenuBar()
    {
        PaintMenuBar.ItemHandler itemHandler = new PaintMenuBar.ItemHandler();

        file   = new JMenu("File");
        clients = new JMenu("Clients");

        newWhiteboard = new JMenuItem("New");
        openWhiteboard = new JMenuItem("Open");
        saveWhiteboard = new JMenuItem("Save");
        saveAsWhiteboard = new JMenuItem("Save As");
        closeWhiteboard = new JMenuItem("Close");

        newWhiteboard.addActionListener(itemHandler);
        openWhiteboard.addActionListener(itemHandler);
        saveWhiteboard.addActionListener(itemHandler);
        saveAsWhiteboard.addActionListener(itemHandler);
        closeWhiteboard.addActionListener(itemHandler);

        file.add(newWhiteboard);
        file.add(openWhiteboard);
        file.add(saveWhiteboard);
        file.add(saveAsWhiteboard);
        file.addSeparator();
        file.add(closeWhiteboard);
        add(file);
        add(clients);
    }
    
    PaintMenuBar(boolean joint)
    {
        PaintMenuBar.ItemHandler itemHandler = new PaintMenuBar.ItemHandler();

        file   = new JMenu("File");
        disconnect = new JMenu("Disconnect");

        newWhiteboard = new JMenuItem("New");
        openWhiteboard = new JMenuItem("Open");
        saveWhiteboard = new JMenuItem("Save");
        saveAsWhiteboard = new JMenuItem("Save As");
        closeWhiteboard = new JMenuItem("Close");

        newWhiteboard.addActionListener(itemHandler);
        openWhiteboard.addActionListener(itemHandler);
        saveWhiteboard.addActionListener(itemHandler);
        saveAsWhiteboard.addActionListener(itemHandler);
        closeWhiteboard.addActionListener(itemHandler);

        file.add(newWhiteboard);
        file.add(openWhiteboard);
        file.add(saveWhiteboard);
        file.add(saveAsWhiteboard);
        file.addSeparator();
        file.add(closeWhiteboard);
        newWhiteboard.setEnabled(false);
        openWhiteboard.setEnabled(false);
        saveWhiteboard.setEnabled(false);
        saveAsWhiteboard.setEnabled(false);
        closeWhiteboard.setEnabled(false);
        add(file);
        add(disconnect);
    }

    private class ItemHandler implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent event)
        {
            if ( event.getSource() == closeWhiteboard)
            {
            	ClientLoginGUI.paint.dispose();
                System.exit(0); 
            }

            if ( event.getSource() == newWhiteboard )
            {
            	ClientLoginGUI.paint.drawPanel.elements = new ArrayList();
            	ClientLoginGUI.paint.drawPanel.elements.add(new FillerElement(Color.white));
            }

            if ( event.getSource() == saveWhiteboard )
            {
            	ClientLoginGUI.paint.saveFile();
            }
            
            if ( event.getSource() == saveAsWhiteboard )
            {
            	ClientLoginGUI.paint.saveAsFile();
            }

            if ( event.getSource() == openWhiteboard )
            {
                File fileName = ClientLoginGUI.paint.getFileName();
                ClientLoginGUI.paint.loadFile(fileName);
                ClientLoginGUI.paint.loadElementsFromFile();
                ClientLoginGUI.paint.closeFile();
            }
            ClientLoginGUI.paint.repaint();
            setFocusable(false);
        }
    }

}
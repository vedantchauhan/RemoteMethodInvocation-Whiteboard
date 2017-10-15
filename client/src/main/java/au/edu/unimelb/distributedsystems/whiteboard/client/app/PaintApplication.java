
package au.edu.unimelb.distributedsystems.whiteboard.client.app;

import au.edu.unimelb.distributedsystems.whiteboard.client.controls.PaintToolPanel;
import au.edu.unimelb.distributedsystems.whiteboard.client.controls.PencilToolPanel;
import au.edu.unimelb.distributedsystems.whiteboard.client.tools.Tool;
import au.edu.unimelb.distributedsystems.whiteboard.client.tools.draw.PaintElement;
import au.edu.unimelb.distributedsystems.whiteboard.client.app.ClientLoginGUI;
import au.edu.unimelb.distributedsystems.whiteboard.client.app.ColorPicker;
import au.edu.unimelb.distributedsystems.whiteboard.client.app.DrawPanel;
import au.edu.unimelb.distributedsystems.whiteboard.client.app.PaintMenuBar;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class PaintApplication extends JFrame
{
    public ObjectOutputStream output;
    public ObjectInputStream input;

    public DrawPanel drawPanel;
    protected PaintMenuBar menuBar; 
    protected ColorPicker colorPicker;
    public PaintToolPanel paintTools;
    private boolean joint;

    public PaintApplication()
    {
        setSize(1000, 800);
        setTitle("Whiteboard");
        
        drawPanel   = new DrawPanel();
        menuBar     = new PaintMenuBar();
        colorPicker = new ColorPicker();
        paintTools  = new PaintToolPanel(new PencilToolPanel(Tool.PENCIL, 1));
        
        setJMenuBar(menuBar);
        GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(paintTools, javax.swing.GroupLayout.DEFAULT_SIZE, 200, javax.swing.GroupLayout.DEFAULT_SIZE)
                    .addComponent(colorPicker, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(drawPanel)
        ));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                		.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                				.addComponent(drawPanel)
                				.addGroup(layout.createSequentialGroup()
                .addComponent(paintTools, javax.swing.GroupLayout.DEFAULT_SIZE, 640, javax.swing.GroupLayout.DEFAULT_SIZE) 
                				.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 100, Short.MAX_VALUE))
                .addComponent(colorPicker, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        synchronizeStartingColor();
    }
    
    public PaintApplication(boolean joint)
    {
    	this.joint = false;
        setSize(1000, 800);
        setTitle("Whiteboard");
        
        drawPanel   = new DrawPanel();
        menuBar     = new PaintMenuBar(false);
        colorPicker = new ColorPicker();
        paintTools  = new PaintToolPanel(new PencilToolPanel(Tool.PENCIL, 1));
        
        setJMenuBar(menuBar);
        GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(paintTools, javax.swing.GroupLayout.DEFAULT_SIZE, 200, javax.swing.GroupLayout.DEFAULT_SIZE)
                    .addComponent(colorPicker, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(drawPanel)
        ));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                		.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                				.addComponent(drawPanel)
                				.addGroup(layout.createSequentialGroup()
                .addComponent(paintTools, javax.swing.GroupLayout.DEFAULT_SIZE, 640, javax.swing.GroupLayout.DEFAULT_SIZE) 
                				.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 100, Short.MAX_VALUE))
                .addComponent(colorPicker, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        synchronizeStartingColor();
    }
    
    @Override
    public void addNotify() {
        super.addNotify();
        requestFocus();
    }
    
    public void synchronizeStartingColor()
    {
        colorPicker.currentClrPanel.setBackground(Color.black);
        colorPicker.color = colorPicker.currentClrPanel.getBackground();
        drawPanel.tool.setColor(colorPicker.currentClrPanel.getBackground());
        drawPanel.setBrushColor(colorPicker.color);
    }


    public File getFileName()
    {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setCurrentDirectory(new File("/Users/Dhruv/Desktop"));

        int result = fileChooser.showOpenDialog(this);

        File fileName = fileChooser.getSelectedFile();

        return fileName;
    }

    public void writeSketchToFile(File fileName)
    {
       try
        {
            for (int i=0; i<drawPanel.elements.size(); i++)
            {
                PaintElement elem = (PaintElement) drawPanel.elements.get(i);
                output.writeObject(elem);
            }
        }
        catch ( IOException exception )
        {
            System.err.println("Error writing to file.");
            return;
        }
    }

    public void loadElementsFromFile()
    {
        
        try
        {
            drawPanel.elements.clear();
            while(true)
            {
                drawPanel.elements.add(input.readObject());
            }
        }
        catch (IOException exception)
        {
            return;
        }
        catch (ClassNotFoundException classNotFoundException)
        {
            System.err.println("Unable to create object.");
        }
    }

    public void loadFile(File fileName)
    {
        try
        {
            input = new ObjectInputStream(new FileInputStream(fileName));
        }
        catch(IOException ioException)
        {
            System.err.println("Error loading file: "+fileName);
            return;
        }
    }

    public void openFile(File fileName)
    {
        try
        {
            output = new ObjectOutputStream(new FileOutputStream(fileName));
        }
        catch(IOException ioException)
        {
            System.err.println("Error loading file: "+fileName);
            return;
        }
    }

    public void saveFile()
    {
        try
        {
        	Component component = drawPanel;
            JFileChooser chooseDirec = new JFileChooser();
            chooseDirec.setFileSelectionMode(JFileChooser.FILES_ONLY);
            chooseDirec.showSaveDialog(ClientLoginGUI.paint);
            File file = chooseDirec.getSelectedFile();
            file = new File(file+".mypaint");
            Rectangle rect = component.getBounds();
          
                String format = "mypaint";
                BufferedImage captureImage =
                        new BufferedImage(rect.width, rect.height,
                                            BufferedImage.TYPE_INT_ARGB);
                component.paint(captureImage.getGraphics());
         
//                ImageIO.write(captureImage, format, file);
         
                System.out.printf("File %s was saved!", component.getName());
         
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));

            bufferedWriter.close();

            openFile(file);
            writeSketchToFile(file);
            closeFile();
        }
        catch (IOException exception)
        {
            System.err.println("Error saving to new file.");
        }
    }
    
    public void saveAsFile()
    {
        try
        {
        		Component component = drawPanel;
            JFileChooser chooseDirec = new JFileChooser();
            chooseDirec.setFileSelectionMode(JFileChooser.FILES_ONLY);
            chooseDirec.showSaveDialog(ClientLoginGUI.paint);
            File file = chooseDirec.getSelectedFile();
            file = new File(file+".png");
            Rectangle rect = component.getBounds();
          
                String format = "png";
                BufferedImage captureImage =
                        new BufferedImage(rect.width, rect.height,
                                            BufferedImage.TYPE_INT_ARGB);
                component.paint(captureImage.getGraphics());
         
                ImageIO.write(captureImage, format, file);
         
                System.out.printf("File %s was saved!", component.getName());
//         
//            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
//
//            bufferedWriter.close();
//
//            openFile(file);
//            writeSketchToFile(file);
//            closeFile();
        }
        catch (IOException exception)
        {
            System.err.println("Error saving to new file.");
        }   
    }

    public void closeFile()
    {
        try
        {
            if (output != null)
                output.close();
        }
        catch (IOException exception)
        {
            System.err.println("Error closing file");
            System.exit(1);
        }
    }
    

}

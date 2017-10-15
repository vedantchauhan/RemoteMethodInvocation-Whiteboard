
package au.edu.unimelb.distributedsystems.whiteboard.client.app;

import au.edu.unimelb.distributedsystems.whiteboard.client.tools.Tool;
import au.edu.unimelb.distributedsystems.whiteboard.client.utils.ColorPanel;
import au.edu.unimelb.distributedsystems.whiteboard.client.app.ClientLoginGUI;
import au.edu.unimelb.distributedsystems.whiteboard.client.app.ColorOptionBox;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class ColorPicker extends JPanel
{
    protected ColorOptionBox[] colorOptions; 
    protected ArrayList points;

    protected JPanel currentClrPanel;

    protected Color[] colors;

    protected Color color;
    
    public ColorPicker()
    {
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(92, 92));
        setLayout(new BorderLayout());

        colors = new Color[32];
        int count=0;
        for(int i=1; i<5; i++){
            for(int j=1; j<5; j++){
                for(int k=1; k<3; k++){
                    colors[count] = new Color(i*51, j*51, k*127);
                    count++;
                }
            }
        }
        
        color  = Color.black;
        
        currentClrPanel = new JPanel();
        currentClrPanel.setBackground(Color.BLACK);
        currentClrPanel.setPreferredSize(new Dimension(180,30));
        
        currentClrPanel.addMouseListener(
            new MouseAdapter()
            {
                @Override
                public void mousePressed(MouseEvent event)
                {
                    if (ClientLoginGUI.paint.drawPanel.getTool() != Tool.ERASER)
                    {
                        currentClrPanel.setBackground(
                                    JColorChooser.showDialog( ClientLoginGUI.paint, "Change Color",
                                    		ClientLoginGUI.paint.drawPanel.brushColor));
                        color = currentClrPanel.getBackground();
                        ClientLoginGUI.paint.drawPanel.tool.setColor(currentClrPanel.getBackground());
                        ClientLoginGUI.paint.drawPanel.setBrushColor(color);
                        
                    }
                }
            }
        );

        JPanel colorGrid = new JPanel();
        colorGrid.setBackground(Color.gray);
        colorGrid.setLayout(new GridLayout(2, 16, 6, 6));

        ColorPanel holder = new ColorPanel(Color.gray);
        holder.setLayout(new BorderLayout(6, 6));

        holder.add(currentClrPanel, BorderLayout.WEST);

        JPanel holder2 = new JPanel();
        holder2.setLayout(new BorderLayout());
        holder2.add(new ColorPanel(Color.gray), BorderLayout.WEST);
        holder2.add(new ColorPanel(Color.gray), BorderLayout.EAST);
        holder2.add(new ColorPanel(Color.gray), BorderLayout.SOUTH);
        holder2.add(new ColorPanel(Color.gray), BorderLayout.NORTH);

        holder2.add(holder, BorderLayout.CENTER);
        add(holder2, BorderLayout.CENTER);
    }

    public void deselectAll()
    {
        for(int i=0; i<colorOptions.length; i++)
        {
            colorOptions[i].selected = false;
        }
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
    }
}

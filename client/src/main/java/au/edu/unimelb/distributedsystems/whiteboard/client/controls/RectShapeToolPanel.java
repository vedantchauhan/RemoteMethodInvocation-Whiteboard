
package au.edu.unimelb.distributedsystems.whiteboard.client.controls;

import au.edu.unimelb.distributedsystems.whiteboard.client.app.ClientLoginGUI;
import au.edu.unimelb.distributedsystems.whiteboard.client.tools.StrokeStyle;
import au.edu.unimelb.distributedsystems.whiteboard.client.tools.Tool;
import au.edu.unimelb.distributedsystems.whiteboard.client.visuals.ToolOptionIcon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RectShapeToolPanel extends DragShapeToolPanel
{
    JButton fillButton  ;
    JButton unFillButton;

    public RectShapeToolPanel(Tool tool, int stroke)
    {
        super(tool, stroke);

        remove(buttonHolder);

        JPanel fillButtonPanel = new JPanel();
        fillButtonPanel.setLayout(new GridLayout(1, 2));
        fillButtonPanel.setBackground(Color.gray);
        fillButtonPanel.setPreferredSize(new Dimension(150, 50));

        fillButton = new JButton();
        unFillButton=new JButton();

        ButtonListener listener = new ButtonListener();

        fillButton.addActionListener(listener);
        unFillButton.addActionListener(listener);

        if (tool == Tool.RECTANGLE)
        {
            fillButton.add(new ToolOptionIcon(StrokeStyle.FILL_RECT));
            unFillButton.add(new ToolOptionIcon(StrokeStyle.OPEN_RECT));
        }
        if (tool == Tool.OVAL)
        {
            fillButton.add(new ToolOptionIcon(StrokeStyle.FILL_OVAL));
            unFillButton.add(new ToolOptionIcon(StrokeStyle.OPEN_OVAL));
        }
        if (tool == Tool.ROUND_RECT)
        {
            fillButton.add(new ToolOptionIcon(StrokeStyle.FILL_ROUND_RECT));
            unFillButton.add(new ToolOptionIcon(StrokeStyle.OPEN_ROUND_RECT));
        }

        fillButtonPanel.add(fillButton);
        fillButtonPanel.add(unFillButton);

        add(fillButtonPanel);

        buttonHolder.setPreferredSize(new Dimension(100, 90));
        
        revalidate();
        repaint();
    }

    private class ButtonListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent event)
        {
            if (event.getSource() == fillButton)
            {
                switch(tool)
                {
                    case RECTANGLE:
                    {
                        ClientLoginGUI.paint.drawPanel.tool.setStrokeStyle(StrokeStyle.FILL_RECT);
                        break;
                    }
                    case ROUND_RECT:
                    {
                        ClientLoginGUI.paint.drawPanel.tool.setStrokeStyle(StrokeStyle.FILL_ROUND_RECT);
                        break;
                    }
                    case OVAL:
                    {
                        ClientLoginGUI.paint.drawPanel.tool.setStrokeStyle(StrokeStyle.FILL_OVAL);
                        break;
                    }
                    case WRITER:
                    {
                        ClientLoginGUI.paint.drawPanel.tool.setStrokeStyle(StrokeStyle.FILL_RECT);
                        break;
                    }
                    
                }
            }
            if (event.getSource()==unFillButton)
            {
               switch(tool)
                {
                    case RECTANGLE:
                    {
                        ClientLoginGUI.paint.drawPanel.tool.setStrokeStyle(StrokeStyle.OPEN_RECT);
                        break;
                    }
                    case ROUND_RECT:
                    {
                        ClientLoginGUI.paint.drawPanel.tool.setStrokeStyle(StrokeStyle.OPEN_ROUND_RECT);
                        break;
                    }
                    case OVAL:
                    {
                        ClientLoginGUI.paint.drawPanel.tool.setStrokeStyle(StrokeStyle.OPEN_OVAL);
                        break;
                    }
                    case WRITER:
                    {
                        ClientLoginGUI.paint.drawPanel.tool.setStrokeStyle(StrokeStyle.OPEN_RECT);
                        break;
                    }
                }
            }
            repaint();
        }
    }
}

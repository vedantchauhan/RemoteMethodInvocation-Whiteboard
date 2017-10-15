
package au.edu.unimelb.distributedsystems.whiteboard.client.controls;

import au.edu.unimelb.distributedsystems.whiteboard.client.app.ClientLoginGUI;
import au.edu.unimelb.distributedsystems.whiteboard.client.tools.StrokeStyle;
import au.edu.unimelb.distributedsystems.whiteboard.client.tools.Tool;
import au.edu.unimelb.distributedsystems.whiteboard.client.visuals.StrokePanel;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class PencilToolPanel extends ToolOptionPanel
{

    public StrokeStyle[] strokeStyleList ={StrokeStyle.DOT_CIRC, StrokeStyle.DOT_RECT,
                                           StrokeStyle.LINE,     StrokeStyle.TRIANGLE,
                                           StrokeStyle.RIBBON };
    protected JSlider strokeSlider;

    protected JPanel showStrokePanel;

    protected StrokePanel showStroke;

    public PencilToolPanel(Tool tool, int stroke)
    {
        super(tool);

        setLayout(new FlowLayout());

        strokeSlider = new JSlider(SwingConstants.HORIZONTAL, 0, 20, 1);
        strokeSlider.setPaintTicks(false);
        strokeSlider.setMaximum(20);
        strokeSlider.setMinimum(1);
        strokeSlider.setMajorTickSpacing(1);

        strokeSlider.setValue(stroke);
        strokeSlider.revalidate();

        SlideChangeListener listener = new SlideChangeListener();
        strokeSlider.addChangeListener(listener);

        for(int i=0; i<3; i++)
        {
       //     styleIcons[i] = new ImageIcon(getClass().getResource("style"+(i+1)+".png"));
        }

        showStroke = new StrokePanel();
        showStrokePanel = new JPanel();
        showStrokePanel.setPreferredSize(new Dimension(62, 72));
        showStrokePanel.setLayout(new FlowLayout());
        showStrokePanel.add(showStroke, BorderLayout.CENTER);

        add(showStrokePanel);
        add(strokeSlider);
    }

    private class SlideChangeListener implements ChangeListener
    {
        @Override
        public void stateChanged(ChangeEvent event)
        {
            ClientLoginGUI.paint.drawPanel.tool.setStrokeWidth(strokeSlider.getValue());
            repaint();
        }
    }
}

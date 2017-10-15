
package au.edu.unimelb.distributedsystems.whiteboard.client.tools;

import au.edu.unimelb.distributedsystems.whiteboard.client.app.ClientLoginGUI;
import au.edu.unimelb.distributedsystems.whiteboard.client.tools.draw.PaintElement;

import java.awt.*;

public class FillerElement extends PaintElement
{
    public FillerElement(Color clr)
    {
        super(clr);
    }

    @Override
    public void update(Graphics g)
    {
        ClientLoginGUI.paint.drawPanel.setBackground(color);
        ClientLoginGUI.paint.drawPanel.backgroundColor = color;
        ClientLoginGUI.paint.drawPanel.repaint();
    }
}

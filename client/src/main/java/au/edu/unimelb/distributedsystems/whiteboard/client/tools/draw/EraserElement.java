
package au.edu.unimelb.distributedsystems.whiteboard.client.tools.draw;

import au.edu.unimelb.distributedsystems.whiteboard.client.app.ClientLoginGUI;
import au.edu.unimelb.distributedsystems.whiteboard.client.tools.StrokeStyle;

import java.awt.*;

public class EraserElement extends BrushElement
{
    public EraserElement(Point pt, Color clr, int dim, StrokeStyle style)
    {
        super(pt, clr, dim, style);
    }

    @Override
    public void update(Graphics g)
    {
        if (strokeStyle == StrokeStyle.DOT_CIRC)
        {
            g.setColor(ClientLoginGUI.paint.drawPanel.getBackground());
            g.fillOval(coors.x, coors.y, strokeWidth, strokeWidth);
        }
        if (strokeStyle != StrokeStyle.DOT_CIRC)
        {
            g.setColor(ClientLoginGUI.paint.drawPanel.getBackground());
            g.fillRect(coors.x, coors.y, strokeWidth, strokeWidth);
        }
    }
}


package au.edu.unimelb.distributedsystems.whiteboard.client.tools;

import au.edu.unimelb.distributedsystems.whiteboard.client.app.ClientLoginGUI;
import au.edu.unimelb.distributedsystems.whiteboard.client.tools.draw.BrushElement;

import java.awt.*;

public class FillerTool extends AbstractTool
{
    public FillerTool(Color clr, int stroke)
    {
        super(clr, stroke);
    }

    public void fillAllSides(Point point, Color color)
    {
        Robot robot;
        try
        {
            System.out.println(point);

            robot = new Robot();
            Color[] neswClrs = new Color[4];

            neswClrs[0] = robot.getPixelColor(point.x-1, point.y);
            neswClrs[1] = robot.getPixelColor(point.x+1, point.y);
            neswClrs[2] = robot.getPixelColor(point.x, point.y+1);
            neswClrs[3] = robot.getPixelColor(point.x, point.y-1);

            robot = null;

            if (neswClrs[0] != ClientLoginGUI.paint.drawPanel.getBackground())
            {
                robot.mouseMove(point.x-1, point.y);
                ClientLoginGUI.paint.drawPanel.elements.add(new BrushElement(ClientLoginGUI.paint.drawPanel.getMousePosition(), color, 1, StrokeStyle.DOT_RECT));
                System.out.println(ClientLoginGUI.paint.drawPanel.getMousePosition());
                ClientLoginGUI.paint.drawPanel.repaint();
                fillAllSides(new Point(point.x-1, point.y), color);
            }
            if (neswClrs[1] == ClientLoginGUI.paint.drawPanel.getBackground())
            {
                robot.mouseMove(point.x-1, point.y);
                ClientLoginGUI.paint.drawPanel.elements.add(new BrushElement(ClientLoginGUI.paint.drawPanel.getMousePosition(), color, 1, StrokeStyle.DOT_RECT));
                ClientLoginGUI.paint.drawPanel.repaint();
                fillAllSides(new Point(point.x+1, point.y), color);
            }
            if (neswClrs[2] == ClientLoginGUI.paint.drawPanel.getBackground())
            {
                robot.mouseMove(point.x-1, point.y);
                ClientLoginGUI.paint.drawPanel.elements.add(new BrushElement(ClientLoginGUI.paint.drawPanel.getMousePosition(), color, 1, StrokeStyle.DOT_RECT));
                ClientLoginGUI.paint.drawPanel.repaint();
                fillAllSides(new Point(point.x, point.y+1), color);
            }
            if (neswClrs[3] == ClientLoginGUI.paint.drawPanel.getBackground())
            {
                robot.mouseMove(point.x-1, point.y);
                ClientLoginGUI.paint.drawPanel.elements.add(new BrushElement(ClientLoginGUI.paint.drawPanel.getMousePosition(), color, 1, StrokeStyle.DOT_RECT));
                ClientLoginGUI.paint.drawPanel.repaint();
                fillAllSides(new Point(point.x, point.y-1), color);
            }
        }
        catch (AWTException exception)
        {
            exception.printStackTrace();
        }
    }
}

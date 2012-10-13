import java.awt.*;
import java.awt.geom.*;

/**
 *@author Kaiven Zhou kz2182
   A shape that manages its selection state.

   This class was taken from the original, from
   Horstmann's Object-oriented Design & Patterns (ISBN 0-471-74487-5)
*/
public abstract class SelectableShape implements SceneShape
{
   public void setSelected(boolean b)
   {
      selected = b;
   }

   public boolean isSelected()
   {
      return selected;
   }

   private boolean selected;
}

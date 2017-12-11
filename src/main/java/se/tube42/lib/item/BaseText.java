package se.tube42.lib.item;

import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.utils.*;

import se.tube42.lib.tweeny.*;
import se.tube42.lib.item.TextBox;
import se.tube42.lib.ks.*;
import se.tube42.lib.scene.*;

public class BaseText extends BaseItem
{
    public TextBox text;

    public BaseText(BitmapFont font)
    {
        this.text = new TextBox(font);
        this.text.setText("");

        setAlignment(0, 0);
        setMaxWidth(0);
    }

    public void setAlignment(float aw, float ah)
    {
        text.setAlignment(aw, ah);
        calcBounds();
    }

    public void setMaxWidth(int mw)
    {
        text.setMaxWidth(mw);
        calcBounds();
    }

    public void setText(String text)
    {
        this.text.setText(text);
        calcBounds();
    }


    public String getText()
    {
        return text.getText();
    }

    public BitmapFont getFont()
    {
        return text.getFont();
    }

    public void calcBounds()
    {
        w = text.getWidth();
        h = text.getHeight();
    }

    // --------------------------------------------

    public void draw(SpriteBatch sb)
    {
        final float a = getAlpha();
        text.getFont().setColor(cr, cg, cb, a);
        text.draw(sb, getX(), getY() );
    }



    public boolean hit(float x, float y)
    {
        // special hit function for text that uses alignments
        final float x0 = getX() + text.aw * w;
        final float y0 = getY() + text.ah * h;
        final float x1 = x0 + w;
        final float y1 = y0 + h;
        return x >= x0 && x <= x1 && y >= y0 && y <= y1;
    }

}

package se.tube42.lib.item;

import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.utils.*;

public class TextBox
{
    private BitmapFont font;
    private String text;
    private GlyphLayout gl;
    private int maxwidth;
    public float aw, ah;

    public TextBox(BitmapFont font)
    {
        this.font = font;
        this.gl = new GlyphLayout();
        this.maxwidth = 0;

        setAlignment(0, 0);
        setText("");
    }

    public void setAlignment(float aw, float ah)
    {
        this.aw = aw;
        this.ah = ah;
    }

    public void setMaxWidth(int mw)
    {
        this.maxwidth = mw;
        update();
    }

    public void setText(String text)
    {
        this.text = text;
        update();
    }

    public String getText()
    {
        return text;
    }

    public BitmapFont getFont()
    {
        return font;
    }

    public void setFont(BitmapFont font)
    {
        this.font = font;
        update();
    }

    private void update()
    {
        if(this.text == null || this.font == null)
            return;

        if(maxwidth > 0) {
            gl.setText(font, text, 0, text.length(), font.getColor(),
                maxwidth, Align.left, true, null);
        } else {
            gl.setText(font, text);
        }
    }

    public void draw(SpriteBatch sb, float x, float y)
    {
        if(this.text == null || this.font == null)
            return;

        // font.draw(sb, gl, x, y);


        x += aw * gl.width;
        y += ah * gl.height;


        if(maxwidth > 0) {
            font.draw(sb, text, x, y, maxwidth, Align.left, true);
        } else {
            font.draw(sb, text, x, y);
        }
    }

    public float getWidth()
    {
        return gl.width;
    }

    public float getHeight()
    {
        return gl.height;
    }
}


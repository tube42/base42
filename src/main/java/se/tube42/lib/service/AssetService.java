
package se.tube42.lib.service;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.graphics.g2d.freetype.*;

import java.util.*;

public class AssetService
{

    // -------------------------------------------------------------------
    // atlas

    public static Texture load(String filename, boolean filter)
    {
        final Texture texture = new Texture( Gdx.files.internal(filename));
        final Texture.TextureFilter tf = filter ?
              Texture.TextureFilter.Linear : Texture.TextureFilter.Nearest;
        texture.setFilter(tf, tf);
        return texture;
    }


    public static TextureRegion [] divide(Texture t, int cw, int ch)
    {
        final int tw = t.getWidth();
        final int th = t.getHeight();
        final int w = tw / cw;
        final int h = th / ch;

        if(w < 1 || h < 1) return null;

        final TextureRegion [] ret = new TextureRegion[cw * ch];

        for(int a = 0; a < ch; a++) {
            for(int b = 0; b < cw; b++) {
                TextureRegion t2 = new TextureRegion(t, b * w, a * h, w, h);
                ret[b + a * cw] = t2;
            }
        }

        for(int i = 0; i < ret.length; i++)
            correctRegionBorders(ret[i]);
        return ret;
    }


    public static void correctRegionBorders(TextureRegion tr)
    {
        final Texture t = tr.getTexture();

        if(t.getMinFilter() != Texture.TextureFilter.Linear)
            return;

        if(t.getWidth() < 2 || t.getHeight() < 2)
            return;

        final float hpw = 0.5f / t.getWidth();
        final float hph = 0.6f / t.getHeight();
        tr.setU ( tr.getU () + hpw);
        tr.setV ( tr.getV () + hph);
        tr.setU2( tr.getU2() - hpw);
        tr.setV2( tr.getV2() - hph);
    }

    public static ParticleEffectPool loadParticle(String dir, String name)
    {
        ParticleEffect effect = new ParticleEffect();
        effect.load(Gdx.files.internal(dir + name + ".p"), Gdx.files.internal(dir));
        return new ParticleEffectPool(effect, 0, 70);
    }

    public static NinePatch loadPatch(String filename, int l, int r, int t, int b)
    {
        final Texture te = new Texture(Gdx.files.internal(filename));
        // t.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        final TextureRegion tr = new TextureRegion(te);
        return new NinePatch(tr, l, r, t, b);
    }

    // -------------------------------------------------------------------
    // fonts

    public static BitmapFont [] createFonts(String filename,
              String charset, int... sizes)
    {
        final FreeTypeFontGenerator g = new FreeTypeFontGenerator(
                  Gdx.files.internal(filename));
        if(g == null) return null;

        BitmapFont [] ret = new BitmapFont[sizes.length];
        for(int i = 0; i < sizes.length; i++) {
            FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
            parameter.size = sizes[i];
            parameter.characters = charset;
            ret[i] = g.generateFont(parameter);
            ret[i].setUseIntegerPositions(true);
        }

        g.dispose(); // to avoid memory leaks!
        return ret;
    }
}

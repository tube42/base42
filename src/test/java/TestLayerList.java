package se.tube42.test.scene;

import com.badlogic.gdx.graphics.g2d.*;
import org.junit.*;

import se.tube42.lib.item.BaseItem;
import se.tube42.lib.scene.*;


public class TestLayerList
{
	@Test public void testAdd()
	{
		LayerList l = new LayerList();
		Layer i0 = new Layer();
		Layer i1 = new Layer();

		Assert.assertEquals("empty layer-list", l.getSize(), 0);

		Layer i2 = l.add(i0, i1);

		Assert.assertEquals("layer-list with 2 elements", l.getSize(), 2);
		Assert.assertEquals("layer-list element 0", l.get(0), i0);
		Assert.assertEquals("layer-list element 1", l.get(1), i1);
		Assert.assertEquals("layer-list add returns", i2, i1);
	}

	@Test public void testGrow()
	{
		LayerList l = new LayerList();
		Assert.assertEquals("empty layer-list before grow", l.getSize(), 0);

		// create enough items to force it to grow:
		final int n = 128;
		Layer [] items1 = new Layer[n];
		Layer [] items2 = new Layer[n];
		for(int i = 0; i < items1.length; i++)
			items1[i] = new Layer();
		for(int i = 0; i < items2.length; i++)
			items2[i] = new Layer();

		// add items1 all at once and items2 individualy
		l.add(items1);
		for(int i = 0; i < items2.length; i++)
			l.add(items2[i]);

		Assert.assertEquals("layer-list after grow", l.getSize(), 2 * n);

		for(int i = 0; i < items1.length; i++)
			Assert.assertEquals("layer-list grow items1 elements", l.get(i), items1[i]);

		for(int i = 0; i < items2.length; i++)
			Assert.assertEquals("layer-list grow items2 elements",
				l.get(i + items1.length), items2[i]);
	}
}
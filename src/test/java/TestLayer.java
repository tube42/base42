package se.tube42.test.scene;

import com.badlogic.gdx.graphics.g2d.*;
import org.junit.*;

import se.tube42.lib.item.BaseItem;
import se.tube42.lib.scene.*;

class DummyItem extends BaseItem {
	public void draw(SpriteBatch sb) { }
}

public class TestLayer
{
	@Test public void testAdd()
	{
		Layer l = new Layer();
		DummyItem i0 = new DummyItem();
		DummyItem i1 = new DummyItem();

		Assert.assertEquals("empty layer", l.getSize(), 0);

		Layer l2 = l.add(i0, i1);
		Assert.assertEquals("layer with 2 elements", l.getSize(), 2);
		Assert.assertEquals("layer element 0", l.get(0), i0);
		Assert.assertEquals("layer element 1", l.get(1), i1);
		Assert.assertEquals("layer add returns", l2, l);
	}

	@Test public void testGrow()
	{
		Layer l = new Layer();
		Assert.assertEquals("empty layer before grow", l.getSize(), 0);

		// create enough items to force it to grow:
		final int n = 128;
		DummyItem [] items1 = new DummyItem[n];
		DummyItem [] items2 = new DummyItem[n];
		for(int i = 0; i < items1.length; i++)
			items1[i] = new DummyItem();
		for(int i = 0; i < items2.length; i++)
			items2[i] = new DummyItem();

		// add items1 all at once and items2 individualy
		l.add(items1);
		for(int i = 0; i < items2.length; i++)
			l.add(items2[i]);

		Assert.assertEquals("layer after grow", l.getSize(), 2 * n);

		for(int i = 0; i < items1.length; i++)
			Assert.assertEquals("layer grow items1 elements", l.get(i), items1[i]);

		for(int i = 0; i < items2.length; i++)
			Assert.assertEquals("layer grow items2 elements",
				l.get(i + items1.length), items2[i]);
	}
}
package me.zhang.lab.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.Layout.Alignment;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import me.zhang.lab.utils.HardwareUtils;

/**
 * 自定义View
 * 
 * @author Aige
 * @since 2014/11/19
 */
public class StaticLayoutView extends View {
	private static final String TEXT = "This is used by widgets to control text layout. You should not need to use this class directly unless you are implementing your own widget or custom display object, or would be tempted to call Canvas.drawText() directly.";
	private TextPaint mTextPaint;// 文本的画笔
	private StaticLayout mStaticLayout;// 文本布局

	public StaticLayoutView(Context context) {
		this(context, null);
	}

	public StaticLayoutView(Context context, AttributeSet attrs) {
		super(context, attrs);

		// 初始化画笔
		initPaint(context);
	}

	/**
	 * 初始化画笔
	 */
	private void initPaint(Context context) {
		// 实例化画笔
		mTextPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
		mTextPaint.setTextSize(50);
		mTextPaint.setColor(Color.BLACK);
        int[] dimens = new int[2];
        HardwareUtils.getScreenDimens(context, dimens);
		mStaticLayout = new StaticLayout(TEXT, mTextPaint, dimens[0], Alignment.ALIGN_NORMAL, 1.0F, 0.0F, false);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		mStaticLayout.draw(canvas);
		canvas.restore();
	}
}

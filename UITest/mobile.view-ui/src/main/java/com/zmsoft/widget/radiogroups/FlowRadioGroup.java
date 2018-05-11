package com.zmsoft.widget.radiogroups;

import android.content.Context;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.View;
import android.widget.RadioGroup;

public class FlowRadioGroup extends RadioGroup {

	private int right;

	public FlowRadioGroup(Context context) {
		super(context);
	}

	public FlowRadioGroup(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int maxWidth = MeasureSpec.getSize(widthMeasureSpec);
		int childCount = getChildCount();
		int x = 0;
		int y = 0;
		int row = 0;

		for (int index = 0; index < childCount; index++) {
			final View child = getChildAt(index);
			if (child.getVisibility() != View.GONE) {
				child.measure(MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED);
				int width = child.getMeasuredWidth();
				int height = child.getMeasuredHeight();
				x += width;
				y = row * height + height;
				if (x > maxWidth) {
					x = width;
					row++;
					y = row * height + height;
				}
			}
		}
		setMeasuredDimension(maxWidth, y);
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		final int childCount = getChildCount();
		int maxWidth = r - l;
		int x = 0;
		int y = 0;
		int row = 0;
		for (int i = 0; i < childCount; i++) {
			final View child = this.getChildAt(i);
			if (child.getVisibility() != View.GONE) {
				int width = child.getMeasuredWidth();
				int height = child.getMeasuredHeight();
				x += width;
				y = row * height + height;
				if (x > maxWidth) {
					x = width;
					row++;
					y = row * height + height;
				}
				child.layout(x - width + 10, y - height, x + right, y);
			}
		}
	}

	/**
	 * RadioGroup.LayoutParams params = new
	 * RadioGroup.LayoutParams(RadioGroup.LayoutParams.WRAP_CONTENT,
	 * RadioGroup.LayoutParams.WRAP_CONTENT);
	 * 
	 * // 设置内外边距 params.leftMargin = 10; params.rightMargin = 10;
	 * rb.setLayoutParams(params); 设置不起作用，解决此引起的界面问题
	 * 
	 * @param right
	 */
	public void setRights(int right) {
		this.right = right;
	}

	// 不自动保存已经选择的RadioButton，解决每次选择radioButton之后关闭下次打开会自动选择的问题
	@Override
	protected void dispatchRestoreInstanceState(SparseArray<Parcelable> container) {
		// super.dispatchRestoreInstanceState(container);
	}

}

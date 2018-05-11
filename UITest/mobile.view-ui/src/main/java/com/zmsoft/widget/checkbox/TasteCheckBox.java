package com.zmsoft.widget.checkbox;

import android.content.Context;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.widget.CheckBox;

public class TasteCheckBox extends CheckBox {

	public TasteCheckBox(Context context) {
		super(context);
	}

	public TasteCheckBox(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	/*
	 * 不自动保存前面设置的值
	 */
	@Override
	protected void dispatchRestoreInstanceState(SparseArray<Parcelable> container) {
		// super.dispatchRestoreInstanceState(container);
	}
}

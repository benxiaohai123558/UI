package com.zmsoft.widget.edittext;

import android.content.Context;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.widget.EditText;

public class TasteEditText extends EditText {

	public TasteEditText(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public TasteEditText(Context context) {
		super(context);
	}

	/*
	 * 不自动保存前面设置的值
	 */
	@Override
	protected void dispatchRestoreInstanceState(SparseArray<Parcelable> container) {
		// super.dispatchRestoreInstanceState(container);
	}
}

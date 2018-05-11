package com.zmsoft.widget.pull;

import android.graphics.Typeface;
import android.graphics.drawable.Drawable;

import com.zmsoft.widget.pull.internal.LoadingLayoutNew;

import java.util.HashSet;

public class LoadingLayoutProxy implements ILoadingLayout {

	private final HashSet<LoadingLayoutNew> mLoadingLayouts;

	LoadingLayoutProxy() {
		mLoadingLayouts = new HashSet<>();
	}

	/**
	 * This allows you to add extra LoadingLayout instances to this proxy. This
	 * is only necessary if you keep your own instances, and want to have them
	 * included in any
	 * {@link PullToRefreshBaseNew#createLoadingLayoutProxy(boolean, boolean)
	 * createLoadingLayoutProxy(...)} calls.
	 * 
	 * @param layout - LoadingLayout to have included.
	 */
	public void addLayout(LoadingLayoutNew layout) {
		if (null != layout) {
			mLoadingLayouts.add(layout);
		}
	}

	@Override
	public void setLastUpdatedLabel(CharSequence label) {
		for (LoadingLayoutNew layout : mLoadingLayouts) {
			layout.setLastUpdatedLabel(label);
		}
	}

	@Override
	public void setLoadingDrawable(Drawable drawable) {
		for (LoadingLayoutNew layout : mLoadingLayouts) {
			layout.setLoadingDrawable(drawable);
		}
	}

	@Override
	public void setRefreshingLabel(CharSequence refreshingLabel) {
		for (LoadingLayoutNew layout : mLoadingLayouts) {
			layout.setRefreshingLabel(refreshingLabel);
		}
	}

	@Override
	public void setPullLabel(CharSequence label) {
		for (LoadingLayoutNew layout : mLoadingLayouts) {
			layout.setPullLabel(label);
		}
	}

	@Override
	public void setReleaseLabel(CharSequence label) {
		for (LoadingLayoutNew layout : mLoadingLayouts) {
			layout.setReleaseLabel(label);
		}
	}

	public void setTextTypeface(Typeface tf) {
		for (LoadingLayoutNew layout : mLoadingLayouts) {
			layout.setTextTypeface(tf);
		}
	}
}

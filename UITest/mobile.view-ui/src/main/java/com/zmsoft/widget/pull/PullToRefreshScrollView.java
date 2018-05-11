package com.zmsoft.widget.pull;

import android.content.Context;
import android.os.Parcelable;
import android.util.AttributeSet;

import com.zmsoft.widget.scrollview.MsgScrollView;

/**
 * 下拉刷新控件
 * @author jianguo
 *
 */
public class PullToRefreshScrollView extends PullToRefreshBase<MsgScrollView> {

	private final OnHeaderRefreshListener defaultOnRefreshListener = new OnHeaderRefreshListener() {

		@Override
		public void onRefresh() {
			onRefreshComplete();
		}

	};


	public PullToRefreshScrollView(Context context) {
		super(context);

		/**
		 * Added so that by default, Pull-to-Refresh refreshes the page
		 */
		setOnHeaderRefreshListener(defaultOnRefreshListener);
	}

	public PullToRefreshScrollView(Context context, int mode) {
		super(context, mode);

		/**
		 * Added so that by default, Pull-to-Refresh refreshes the page
		 */
		setOnHeaderRefreshListener(defaultOnRefreshListener);
	}

	public PullToRefreshScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);

		/**
		 * Added so that by default, Pull-to-Refresh refreshes the page
		 */
		setOnHeaderRefreshListener(defaultOnRefreshListener);
	}

	@Override
	protected MsgScrollView createRefreshableView(Context context, AttributeSet attrs) {
		MsgScrollView scrollView = new MsgScrollView(context, attrs);
		return scrollView;
	}

	@Override
	protected boolean isReadyForPullDown() {
		//Log.d("text", "mScrollView.getHeight()="+this.getHeight());
		return refreshableView.getScrollY() == 0;
	}

	@Override
	protected boolean isReadyForPullUp() {
		MsgScrollView view = getRefreshableView();
		int off=view.getScrollY()+view.getHeight()-view.getChildAt(0).getHeight();
		//Log.d("text", "view.getScrollY()="+view.getScrollY()+",view.getChildAt(0).getHeight()"+view.getChildAt(0).getHeight());
		return off == 0;
	}

	@Override
	protected void onRestoreInstanceState(Parcelable state) {
		try {
			super.onRestoreInstanceState(state);
		}catch (Exception e) {}
		state=null;
	}
}

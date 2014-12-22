package com.BibleQuote.ui.widget.listview.itemview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.BibleQuote.R;
import com.BibleQuote.ui.widget.listview.item.SubtitleItem;
import com.BibleQuote.ui.widget.listview.item.Item;

public class SubtitleItemView extends LinearLayout implements ItemView {

	private TextView mTextView;
	private TextView mSubtitleView;

	public SubtitleItemView(Context context) {
		this(context, null);
	}

	public SubtitleItemView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public void prepareItemView() {
		mTextView = (TextView) findViewById(R.id.text);
		mSubtitleView = (TextView) findViewById(R.id.subtitletext);
	}

	public void setObject(Item object) {
		final SubtitleItem item = (SubtitleItem) object;
		mTextView.setText(item.text);
		mSubtitleView.setText(item.subtitletext);
	}

}

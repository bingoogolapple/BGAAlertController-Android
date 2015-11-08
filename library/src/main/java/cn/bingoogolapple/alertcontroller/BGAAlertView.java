/**
 * Copyright 2015 bingoogolapple
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cn.bingoogolapple.alertcontroller;

import android.content.Context;
import android.graphics.Typeface;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * 作者:王浩 邮件:bingoogolapple@gmail.com
 * 创建时间:15/11/8 下午5:48
 * 描述:
 */
public class BGAAlertView extends LinearLayout {
    private BGAAlertController mAlertController;
    private TextView mTitleTv;
    private BGAActionItemView mCancelAiv;
    private boolean mIsFirstShow = true;

    private int mTitleColor;
    private int mMessageColor;
    private int mTitleTextSize;
    private int mMessageTextSize;

    public BGAAlertView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOrientation(VERTICAL);
        setBackgroundResource(R.drawable.ac_shape_bg);
        mTitleTv = new TextView(context);
        mTitleTv.setGravity(Gravity.CENTER);
        mTitleTv.setBackgroundResource(android.R.color.transparent);
        int padding = getResources().getDimensionPixelOffset(R.dimen.ac_gap);
        mTitleTv.setPadding(padding, padding, padding, padding);
        addView(mTitleTv, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));

        mTitleColor = getResources().getColor(R.color.ac_alert_title);
        mMessageColor = getResources().getColor(R.color.ac_alert_message);
        mTitleTextSize = getResources().getDimensionPixelOffset(R.dimen.ac_alert_text_size_title);
        mMessageTextSize = getResources().getDimensionPixelOffset(R.dimen.ac_alert_text_size_message);

        mTitleTv.setMinHeight(mTitleTextSize * 4);
    }

    public void setAlertController(BGAAlertController alertController) {
        mAlertController = alertController;
    }

    public void setTitle(String title, String message) {
        if (!TextUtils.isEmpty(title) && TextUtils.isEmpty(message)) {
            mTitleTv.setTextColor(mTitleColor);
            mTitleTv.setTextSize(TypedValue.COMPLEX_UNIT_PX, mTitleTextSize);
            mTitleTv.getPaint().setFakeBoldText(true);
            mTitleTv.setText(title);
        } else if (TextUtils.isEmpty(title) && !TextUtils.isEmpty(message)) {
            mTitleTv.setTextColor(mMessageColor);
            mTitleTv.setTextSize(TypedValue.COMPLEX_UNIT_PX, mMessageTextSize);
            mTitleTv.setText(message);
        } else {
            SpannableString titleSs = new SpannableString(title + "\n" + message);
            titleSs.setSpan(new ForegroundColorSpan(mTitleColor), 0, title.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            titleSs.setSpan(new AbsoluteSizeSpan(mTitleTextSize), 0, title.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            titleSs.setSpan(new StyleSpan(Typeface.BOLD), 0, title.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

            titleSs.setSpan(new ForegroundColorSpan(mMessageColor), title.length(), titleSs.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            titleSs.setSpan(new AbsoluteSizeSpan(mMessageTextSize), title.length(), titleSs.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            mTitleTv.setText(titleSs);
            mTitleTv.setLineSpacing(0.0f, 1.2f);
        }
    }

    public void addAction(BGAAlertAction alertAction) {
        BGAActionItemView actionItemView = new BGAActionItemView(getContext(), alertAction, mAlertController);

        if (alertAction.getStyle() == BGAAlertAction.AlertActionStyle.Cancel) {
            if (mCancelAiv == null) {
                mCancelAiv = actionItemView;
            } else {
                throw new RuntimeException("只能添加一个取消动作");
            }
        } else {
            addView(new BGAActionLineView(getContext()));
            addView(actionItemView);
        }
    }

    public void handleBackground() {
        if (mIsFirstShow) {
            mIsFirstShow = false;

            if (mCancelAiv != null) {
                addView(new BGAActionLineView(getContext()));
                addView(mCancelAiv);
            }

            if (getChildCount() >= 3) {
                for (int i = 2; i < getChildCount(); i++) {
                    View child = getChildAt(i);
                    if (child instanceof BGAActionItemView) {
                        if (i == getChildCount() - 1) {
                            child.setBackgroundResource(R.drawable.ac_selector_bottom);
                        } else {
                            child.setBackgroundResource(R.drawable.ac_selector_center);
                        }
                    }
                }
            }
        }
    }
}
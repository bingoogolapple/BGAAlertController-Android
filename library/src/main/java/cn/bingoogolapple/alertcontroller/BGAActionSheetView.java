/**
 * Copyright 2015 bingoogolapple
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
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
public class BGAActionSheetView extends LinearLayout {
    private BGAAlertController mAlertController;
    private LinearLayout mContanierLl;
    private TextView mTitleTv;
    private BGAActionItemView mCancelAiv;
    private boolean mIsFirstShow = true;

    private int mTitleColor;
    private int mMessageColor;
    private int mTitleTextSize;
    private int mMessageTextSize;

    public BGAActionSheetView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOrientation(VERTICAL);

        mTitleTv = new TextView(context);
        mTitleTv.setGravity(Gravity.CENTER);
        mTitleTv.setBackgroundResource(android.R.color.transparent);
        int padding = getResources().getDimensionPixelOffset(R.dimen.ac_gap);
        mTitleTv.setPadding(padding, padding, padding, padding);
        mTitleTv.setClickable(true);

        mContanierLl = new LinearLayout(getContext());
        mContanierLl.setOrientation(VERTICAL);
        mContanierLl.setBackgroundResource(R.drawable.ac_shape_bg);
        mContanierLl.addView(mTitleTv, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));

        addView(mContanierLl, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));

        mTitleColor = getResources().getColor(R.color.ac_action_sheet_title);
        mMessageColor = getResources().getColor(R.color.ac_action_sheet_message);
        mTitleTextSize = getResources().getDimensionPixelOffset(R.dimen.ac_action_sheet_text_size_title);
        mMessageTextSize = getResources().getDimensionPixelOffset(R.dimen.ac_action_sheet_text_size_message);

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
        } else if (!TextUtils.isEmpty(title) && !TextUtils.isEmpty(message)) {
            SpannableString titleSs = new SpannableString(title + "\n" + message);
            titleSs.setSpan(new ForegroundColorSpan(mTitleColor), 0, title.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            titleSs.setSpan(new AbsoluteSizeSpan(mTitleTextSize), 0, title.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            titleSs.setSpan(new StyleSpan(Typeface.BOLD), 0, title.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

            titleSs.setSpan(new ForegroundColorSpan(mMessageColor), title.length(), titleSs.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            titleSs.setSpan(new AbsoluteSizeSpan(mMessageTextSize), title.length(), titleSs.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            mTitleTv.setText(titleSs);
        } else {
            mTitleTv.setVisibility(GONE);
        }
    }

    public void addAction(BGAAlertAction alertAction) {
        BGAActionItemView actionItemView = new BGAActionItemView(getContext(), alertAction, mAlertController);

        if (alertAction.getStyle() == BGAAlertAction.AlertActionStyle.Cancel) {
            if (mCancelAiv == null) {
                mCancelAiv = actionItemView;
                mCancelAiv.setBackgroundResource(R.drawable.ac_selector_cancel);
                MarginLayoutParams params = (MarginLayoutParams) mCancelAiv.getLayoutParams();
                params.topMargin = getResources().getDimensionPixelOffset(R.dimen.ac_gap);
                addView(actionItemView);
            } else {
                throw new RuntimeException("只能添加一个取消动作");
            }
        } else {
            mContanierLl.addView(new BGAActionLineView(getContext()));
            mContanierLl.addView(actionItemView);
        }
    }

    public void handleBackground() {
        if (mIsFirstShow) {
            mIsFirstShow = false;

            if (mTitleTv.getVisibility() == VISIBLE) {
                if (mContanierLl.getChildCount() >= 3) {
                    for (int i = 2; i < mContanierLl.getChildCount(); i++) {
                        View child = mContanierLl.getChildAt(i);
                        if (child instanceof BGAActionItemView) {
                            if (i == mContanierLl.getChildCount() - 1) {
                                child.setBackgroundResource(R.drawable.ac_selector_bottom);
                            } else {
                                child.setBackgroundResource(R.drawable.ac_selector_center);
                            }
                        }
                    }
                }
            } else {
                if (mContanierLl.getChildCount() == 3) {
                    // 移除第一条分割线
                    mContanierLl.removeViewAt(1);

                    mContanierLl.getChildAt(1).setBackgroundResource(R.drawable.ac_selector_cancel);
                } else if (mContanierLl.getChildCount() > 3) {
                    // 移除第一条分割线
                    mContanierLl.removeViewAt(1);

                    for (int i = 1; i < mContanierLl.getChildCount(); i++) {
                        View child = mContanierLl.getChildAt(i);
                        if (child instanceof BGAActionItemView) {
                            if (i == 1) {
                                child.setBackgroundResource(R.drawable.ac_selector_top);
                            } else if (i == mContanierLl.getChildCount() - 1) {
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
}
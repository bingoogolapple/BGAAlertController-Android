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
import android.util.AttributeSet;
import android.view.Gravity;
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

    public BGAAlertView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOrientation(VERTICAL);
        setBackgroundResource(R.drawable.ac_shape_bg);
        mTitleTv = new TextView(context);
        mTitleTv.setGravity(Gravity.CENTER);
        mTitleTv.setBackgroundResource(android.R.color.transparent);
        mTitleTv.setClickable(true);
        int padding = getResources().getDimensionPixelOffset(R.dimen.ac_gap);
        mTitleTv.setPadding(padding * 2, padding, padding * 2, padding);
        addView(mTitleTv, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
    }

    public void setAlertController(BGAAlertController alertController) {
        mAlertController = alertController;
    }

    public void setTitle(CharSequence title, CharSequence message) {
        int titleColor = getResources().getColor(R.color.ac_alert_title);
        int messageColor = getResources().getColor(R.color.ac_alert_message);
        int titleTextSize = getResources().getDimensionPixelOffset(R.dimen.ac_alert_text_size_title);
        int messageTextSize = getResources().getDimensionPixelOffset(R.dimen.ac_alert_text_size_message);
        BGAAlertControllerHelper.setTitle(mTitleTv, titleColor, titleTextSize, messageColor, messageTextSize, title, message);
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

    public boolean showAble() {
        return mCancelAiv != null || getChildCount() > 1;
    }

    public void handleBackground() {
        if (mIsFirstShow) {
            mIsFirstShow = false;

            if (mCancelAiv != null) {
                addView(new BGAActionLineView(getContext()));
                addView(mCancelAiv);
            }

            BGAAlertControllerHelper.handleBackground(this);
        }
    }
}
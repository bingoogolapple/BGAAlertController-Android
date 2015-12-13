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

import android.graphics.Typeface;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * 作者:王浩 邮件:bingoogolapple@gmail.com
 * 创建时间:15/12/13 下午3:01
 * 描述:
 */
public class BGAAlertControllerHelper {

    private BGAAlertControllerHelper() {
    }

    public static void setTitle(TextView titleTv, int titleColor, int titleTextSize, int messageColor, int messageTextSize, CharSequence title, CharSequence message) {
        titleTv.setMinHeight(titleTextSize * 3);

        if (!TextUtils.isEmpty(title) && TextUtils.isEmpty(message)) {
            titleTv.setTextColor(titleColor);
            titleTv.setTextSize(TypedValue.COMPLEX_UNIT_PX, titleTextSize);
            titleTv.getPaint().setFakeBoldText(true);
            titleTv.setText(title);
        } else if (TextUtils.isEmpty(title) && !TextUtils.isEmpty(message)) {
            titleTv.setTextColor(messageColor);
            titleTv.setTextSize(TypedValue.COMPLEX_UNIT_PX, messageTextSize);
            titleTv.setText(message);
        } else if (!TextUtils.isEmpty(title) && !TextUtils.isEmpty(message)) {
            SpannableString titleSs = new SpannableString(title + "\n" + message);
            titleSs.setSpan(new ForegroundColorSpan(titleColor), 0, title.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            titleSs.setSpan(new AbsoluteSizeSpan(titleTextSize), 0, title.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            titleSs.setSpan(new StyleSpan(Typeface.BOLD), 0, title.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

            titleSs.setSpan(new ForegroundColorSpan(messageColor), title.length(), titleSs.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            titleSs.setSpan(new AbsoluteSizeSpan(messageTextSize), title.length(), titleSs.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            titleTv.setText(titleSs);
            titleTv.setLineSpacing(0.0f, 1.2f);
        } else {
            titleTv.setVisibility(View.GONE);
        }
    }

    public static void handleBackground(ViewGroup container) {
        if (container.getChildAt(0).getVisibility() == View.VISIBLE) {
            // 有标题的情况

            if (container.getChildCount() >= 3) {
                for (int i = 2; i < container.getChildCount(); i++) {
                    View child = container.getChildAt(i);
                    if (child instanceof BGAActionItemView) {
                        if (i == container.getChildCount() - 1) {
                            child.setBackgroundResource(R.drawable.ac_selector_bottom);
                        } else {
                            child.setBackgroundResource(R.drawable.ac_selector_center);
                        }
                    }
                }
            }
        } else {
            // 没有标题的情况

            if (container.getChildCount() == 3) {
                // 只有一个BGAActionItemView的情况

                // 移除第一条分割线
                container.removeViewAt(1);

                container.getChildAt(1).setBackgroundResource(R.drawable.ac_selector_cancel);
            } else if (container.getChildCount() > 3) {
                // 大于一个BGAActionItemView的情况

                // 移除第一条分割线
                container.removeViewAt(1);

                for (int i = 1; i < container.getChildCount(); i++) {
                    View child = container.getChildAt(i);
                    if (child instanceof BGAActionItemView) {
                        if (i == 1) {
                            child.setBackgroundResource(R.drawable.ac_selector_top);
                        } else if (i == container.getChildCount() - 1) {
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
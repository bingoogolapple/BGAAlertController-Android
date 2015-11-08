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
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * 作者:王浩 邮件:bingoogolapple@gmail.com
 * 创建时间:15/11/8 下午8:37
 * 描述:
 */
public class BGAActionItemView extends TextView implements View.OnClickListener {
    private BGAAlertAction mAlertAction;
    private BGAAlertController mAlertController;

    public BGAActionItemView(Context context, BGAAlertAction alertAction, BGAAlertController alertController) {
        super(context);
        mAlertAction = alertAction;
        mAlertController = alertController;
        int padding = getResources().getDimensionPixelOffset(R.dimen.ac_gap);
        setPadding(padding, padding, padding, padding);
        setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        if (alertAction != null) {
            if (alertAction.getStyle() == BGAAlertAction.AlertActionStyle.Default) {
                setTextColor(getResources().getColor(R.color.ac_item_text_default));
            } else if (alertAction.getStyle() == BGAAlertAction.AlertActionStyle.Cancel) {
                setTextColor(getResources().getColor(R.color.ac_item_text_default));
                getPaint().setFakeBoldText(true);
            } else if (alertAction.getStyle() == BGAAlertAction.AlertActionStyle.Destructive) {
                setTextColor(getResources().getColor(R.color.ac_item_text_destructive));
            }
        }
        setGravity(Gravity.CENTER);
        setClickable(true);
        setOnClickListener(this);
        setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimensionPixelOffset(R.dimen.ac_item_text_size));
        setText(alertAction.getTitle());
    }

    @Override
    public void onClick(View v) {
        mAlertController.dismiss();
        mAlertAction.onClick();
    }
}
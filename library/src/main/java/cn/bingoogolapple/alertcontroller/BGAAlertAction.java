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

/**
 * 作者:王浩 邮件:bingoogolapple@gmail.com
 * 创建时间:15/11/8 下午6:09
 * 描述:
 */
public class BGAAlertAction {
    private String mTitle;
    private AlertActionStyle mStyle;
    private Delegate mDelegate;

    public BGAAlertAction(String title, AlertActionStyle style, Delegate delegate) {
        mTitle = title;
        mStyle = style;
        mDelegate = delegate;
    }

    public String getTitle() {
        return mTitle;
    }

    public AlertActionStyle getStyle() {
        return mStyle;
    }

    public void onClick() {
        if (mDelegate != null) {
            mDelegate.onClick();
        }
    }

    public interface Delegate {
        void onClick();
    }

    public enum AlertActionStyle {
        Default, Cancel, Destructive
    }
}
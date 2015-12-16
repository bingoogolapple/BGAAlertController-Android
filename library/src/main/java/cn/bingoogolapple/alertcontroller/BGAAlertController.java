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

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;

/**
 * 作者:王浩 邮件:bingoogolapple@gmail.com
 * 创建时间:15/11/7 上午1:52
 * 描述:
 */
public class BGAAlertController extends Dialog implements View.OnClickListener, DialogInterface.OnShowListener {
    private AlertControllerStyle mPreferredStyle;
    private FrameLayout mRootViewFl;
    private BGAAlertView mAlertView;
    private BGAActionSheetView mActionSheetView;

    private boolean mIsDismissed = true;
    private Animation mAlphaEnterAnimation;
    private Animation mAlphaExitAnimation;
    private Animation mAlertEnterAnimation;
    private Animation mAlertExitAnimation;
    private Animation mActionSheetEnterAnimation;
    private Animation mActionSheetExitAnimation;

    private BGAAlertAction mCancelAlertAction;

    public BGAAlertController(Activity activity, int titleResId, int messageResId, AlertControllerStyle preferredStyle) {
        this(activity, titleResId == 0 ? null : activity.getString(titleResId), messageResId == 0 ? null : activity.getString(messageResId), preferredStyle);
    }

    public BGAAlertController(Activity activity, CharSequence title, CharSequence message, AlertControllerStyle preferredStyle) {
        super(activity, R.style.BGAAlertController);
        setContentView(R.layout.ac_alert_controller);
        getWindow().setWindowAnimations(R.style.BGAWindow);
        getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, getScreenHeight(activity) - getStatusBarHeight(activity));
        setOnShowListener(this);
        setCancelable(false);
        setCanceledOnTouchOutside(false);

        mPreferredStyle = preferredStyle;

        initRootView();
        initContentView(title, message);
        initAnim();
    }

    private void initRootView() {
        mRootViewFl = (FrameLayout) findViewById(R.id.fl_alert_controller_root);
        mRootViewFl.setOnClickListener(this);
        if (mPreferredStyle == AlertControllerStyle.Alert) {
            int padding = getContext().getResources().getDimensionPixelOffset(R.dimen.ac_gap) * 3;
            mRootViewFl.setPadding(padding, 0, padding, 0);
        }
    }

    private void initContentView(CharSequence title, CharSequence message) {
        if (mPreferredStyle == AlertControllerStyle.Alert) {
            mAlertView = (BGAAlertView) findViewById(R.id.alertView);
            mAlertView.setAlertController(this);
            mAlertView.setTitle(title, message);
            mAlertView.setVisibility(View.VISIBLE);
        } else {
            mActionSheetView = (BGAActionSheetView) findViewById(R.id.actionSheetView);
            mActionSheetView.setAlertController(this);
            mActionSheetView.setTitle(title, message);
            mActionSheetView.setVisibility(View.VISIBLE);
        }
    }

    private void initAnim() {
        mAlertEnterAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.ac_alert_enter);
        mAlertExitAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.ac_alert_exit);
        mAlertExitAnimation.setAnimationListener(new BGASimpelAnimationListener() {
            @Override
            public void onAnimationEnd(Animation animation) {
                mAlertView.post(mDismissRunnable);
            }
        });

        mActionSheetEnterAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.ac_action_sheet_enter);
        mActionSheetExitAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.ac_action_sheet_exit);
        mActionSheetExitAnimation.setAnimationListener(new BGASimpelAnimationListener() {
            @Override
            public void onAnimationEnd(Animation animation) {
                mActionSheetView.post(mDismissRunnable);
            }
        });

        mAlphaEnterAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.ac_alpha_enter);
        mAlphaExitAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.ac_alpha_exit);
        mAlphaExitAnimation.setAnimationListener(new BGASimpelAnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                if (mPreferredStyle == AlertControllerStyle.ActionSheet) {
                    mActionSheetView.startAnimation(mActionSheetExitAnimation);
                } else {
                    mAlertView.startAnimation(mAlertExitAnimation);
                }
            }
        });
    }

    // 解决 Attempting to destroy the window while drawing!
    private Runnable mDismissRunnable = new Runnable() {
        @Override
        public void run() {
            BGAAlertController.super.dismiss();
        }
    };

    @Override
    public void onShow(DialogInterface dialog) {
        if (mIsDismissed) {
            mIsDismissed = false;
            mRootViewFl.startAnimation(mAlphaEnterAnimation);
            if (mPreferredStyle == AlertControllerStyle.ActionSheet) {
                if (mActionSheetView.showAble()) {
                    mActionSheetView.handleBackground();
                    mActionSheetView.startAnimation(mActionSheetEnterAnimation);
                } else {
                    throw new RuntimeException("必须至少添加一个BGAActionItemView");
                }
            } else {
                if (mAlertView.showAble()) {
                    mAlertView.handleBackground();
                    mAlertView.startAnimation(mAlertEnterAnimation);
                } else {
                    throw new RuntimeException("必须至少添加一个BGAActionItemView");
                }
            }
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.fl_alert_controller_root) {
            onBackPressed();
        }
    }

    @Override
    public void cancel() {
        super.cancel();
        if (mCancelAlertAction != null) {
            mCancelAlertAction.onClick();
        }
    }

    @Override
    public void dismiss() {
        executeExitAnim();
    }

    private void executeExitAnim() {
        if (!mIsDismissed) {
            mIsDismissed = true;
            mRootViewFl.startAnimation(mAlphaExitAnimation);
        }
    }

    /**
     * 不管添加顺序怎样，AlertActionStyle.Cancel始终是在最底部的,AlertActionStyle.Default和AlertActionStyle.Destructive按添加的先后顺序显示
     *
     * @param alertAction
     */
    public void addAction(BGAAlertAction alertAction) {
        if (alertAction == null) {
            return;
        }

        if (alertAction.getTitleResId() != 0) {
            alertAction.setTitle(getContext().getString(alertAction.getTitleResId()));
        }

        if (mPreferredStyle == AlertControllerStyle.ActionSheet) {
            mActionSheetView.addAction(alertAction);
        } else {
            mAlertView.addAction(alertAction);
        }

        if (alertAction.getStyle() == BGAAlertAction.AlertActionStyle.Cancel) {
            setCancelable(true);
            setCanceledOnTouchOutside(true);
            mCancelAlertAction = alertAction;
        }
    }

    public enum AlertControllerStyle {
        ActionSheet, Alert
    }

    public static int getStatusBarHeight(Context context) {
        Resources resources = context.getResources();
        int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
        return resources.getDimensionPixelSize(resourceId);
    }

    public static int getScreenHeight(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(dm);
        return dm.heightPixels;
    }
}
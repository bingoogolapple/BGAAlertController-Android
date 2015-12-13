package cn.bingoogolapple.alertcontroller.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import cn.bingoogolapple.alertcontroller.BGAAlertAction;
import cn.bingoogolapple.alertcontroller.BGAAlertController;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showAlertViewDemo1(View v) {
        showAlertControllerDemo1(BGAAlertController.AlertControllerStyle.Alert);
    }

    public void showActionSheetDemo1(View v) {
        showAlertControllerDemo1(BGAAlertController.AlertControllerStyle.ActionSheet);
    }

    public void showAlertViewDemo2(View v) {
        showAlertControllerDemo2(BGAAlertController.AlertControllerStyle.Alert);
    }

    public void showActionSheetDemo2(View v) {
        showAlertControllerDemo2(BGAAlertController.AlertControllerStyle.ActionSheet);
    }

    public void showAlertViewDemo3(View v) {
        showAlertControllerDemo3(BGAAlertController.AlertControllerStyle.Alert);
    }

    public void showActionSheetDemo3(View v) {
        showAlertControllerDemo3(BGAAlertController.AlertControllerStyle.ActionSheet);
    }

    private void showAlertControllerDemo1(BGAAlertController.AlertControllerStyle preferredStyle) {
        BGAAlertController alertController = new BGAAlertController(this, R.string.title, R.string.content, preferredStyle);
        // 不管添加顺序怎样，AlertActionStyle.Cancel始终是在最底部的,AlertActionStyle.Default和AlertActionStyle.Destructive按添加的先后顺序显示
        alertController.addAction(new BGAAlertAction(R.string.cancel, BGAAlertAction.AlertActionStyle.Cancel, new BGAAlertAction.Delegate() {
            @Override
            public void onClick() {
                showToast("点击了取消");
            }
        }));
        alertController.addAction(new BGAAlertAction(R.string.confirm, BGAAlertAction.AlertActionStyle.Destructive, new BGAAlertAction.Delegate() {
            @Override
            public void onClick() {
                showToast("点击了确定");
            }
        }));
        alertController.setCancelable(true);
        alertController.show();
    }

    private void showAlertControllerDemo2(BGAAlertController.AlertControllerStyle preferredStyle) {
        BGAAlertController alertController = new BGAAlertController(this, 0, 0, preferredStyle);
        alertController.addAction(new BGAAlertAction(R.string.other1, BGAAlertAction.AlertActionStyle.Default, new BGAAlertAction.Delegate() {
            @Override
            public void onClick() {
                showToast("点击了其他1");
            }
        }));
        // 不管添加顺序怎样，AlertActionStyle.Cancel始终是在最底部的,AlertActionStyle.Default和AlertActionStyle.Destructive按添加的先后顺序显示
        alertController.addAction(new BGAAlertAction(R.string.cancel, BGAAlertAction.AlertActionStyle.Cancel, new BGAAlertAction.Delegate() {
            @Override
            public void onClick() {
                showToast("点击了取消");
            }
        }));
        alertController.addAction(new BGAAlertAction(R.string.other2, BGAAlertAction.AlertActionStyle.Default, new BGAAlertAction.Delegate() {
            @Override
            public void onClick() {
                showToast("点击了其他2");
            }
        }));
        alertController.addAction(new BGAAlertAction(R.string.confirm, BGAAlertAction.AlertActionStyle.Destructive, new BGAAlertAction.Delegate() {
            @Override
            public void onClick() {
                showToast("点击了确定");
            }
        }));
        alertController.setCancelable(true);
        alertController.show();
    }

    private void showAlertControllerDemo3(BGAAlertController.AlertControllerStyle preferredStyle) {
        BGAAlertController alertController = new BGAAlertController(this, 0, R.string.content, preferredStyle);
        // 不管添加顺序怎样，AlertActionStyle.Cancel始终是在最底部的,AlertActionStyle.Default和AlertActionStyle.Destructive按添加的先后顺序显示
        alertController.addAction(new BGAAlertAction(R.string.cancel, BGAAlertAction.AlertActionStyle.Cancel, new BGAAlertAction.Delegate() {
            @Override
            public void onClick() {
                showToast("点击了取消");
            }
        }));
        alertController.addAction(new BGAAlertAction(R.string.confirm, BGAAlertAction.AlertActionStyle.Destructive, new BGAAlertAction.Delegate() {
            @Override
            public void onClick() {
                showToast("点击了确定");
            }
        }));
        alertController.setCancelable(true);
        alertController.show();
    }

    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
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

    public void showAlertView(View v) {
        BGAAlertController alertController = new BGAAlertController(this, "我是标题", "我是很长很长很长很长很长很长很长很长很长很长很长很长的消息", BGAAlertController.AlertControllerStyle.Alert);
        // 不管添加顺序怎样，AlertActionStyle.Cancel始终是在最底部的,AlertActionStyle.Default和AlertActionStyle.Destructive按添加的先后顺序显示
        alertController.addAction(new BGAAlertAction("取消", BGAAlertAction.AlertActionStyle.Cancel, new BGAAlertAction.Delegate() {
            @Override
            public void onClick() {
                showToast("点击了取消");
            }
        }));
        alertController.addAction(new BGAAlertAction("其他1", BGAAlertAction.AlertActionStyle.Default, new BGAAlertAction.Delegate() {
            @Override
            public void onClick() {
                showToast("点击了其他1");
            }
        }));
        alertController.addAction(new BGAAlertAction("其他2", BGAAlertAction.AlertActionStyle.Default, new BGAAlertAction.Delegate() {
            @Override
            public void onClick() {
                showToast("点击了其他2");
            }
        }));
        alertController.addAction(new BGAAlertAction("确定", BGAAlertAction.AlertActionStyle.Destructive, new BGAAlertAction.Delegate() {
            @Override
            public void onClick() {
                showToast("点击了确定");
            }
        }));
        alertController.show();
    }

    public void showActionSheet(View v) {
        BGAAlertController alertController = new BGAAlertController(this, "我是标题", "我是很长很长很长很长很长很长很长很长很长很长很长很长的消息", BGAAlertController.AlertControllerStyle.ActionSheet);
        // 不管添加顺序怎样，AlertActionStyle.Cancel始终是在最底部的,AlertActionStyle.Default和AlertActionStyle.Destructive按添加的先后顺序显示
        alertController.addAction(new BGAAlertAction("取消", BGAAlertAction.AlertActionStyle.Cancel, new BGAAlertAction.Delegate() {
            @Override
            public void onClick() {
                showToast("点击了取消");
            }
        }));
        alertController.addAction(new BGAAlertAction("其他1", BGAAlertAction.AlertActionStyle.Default, new BGAAlertAction.Delegate() {
            @Override
            public void onClick() {
                showToast("点击了其他1");
            }
        }));
        alertController.addAction(new BGAAlertAction("其他2", BGAAlertAction.AlertActionStyle.Default, new BGAAlertAction.Delegate() {
            @Override
            public void onClick() {
                showToast("点击了其他2");
            }
        }));
        alertController.addAction(new BGAAlertAction("确定", BGAAlertAction.AlertActionStyle.Destructive, new BGAAlertAction.Delegate() {
            @Override
            public void onClick() {
                showToast("点击了确定");
            }
        }));
        alertController.show();
    }

    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
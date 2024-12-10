# :running:BGAAlertController-Android:running:

[![License](https://img.shields.io/badge/license-Apache%202-green.svg)](https://www.apache.org/licenses/LICENSE-2.0)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/cn.bingoogolapple/bga-alertcontroller/badge.svg)](https://maven-badges.herokuapp.com/maven-central/cn.bingoogolapple/bga-alertcontroller)

工作以来公司UI设计师出的Android效果图都是iOS风格的UIAlertView和UIActionSheet，新项目还是用原来那一套，不想重复造轮子，所以仿写了从iOS8开始支持的UIAlertController，统一UIAlertView和UIActionSheet的用法

### 效果图

![Demo](http://7xk9dj.com1.z0.glb.clouddn.com/alertcontroller/screenshots/alertcontroller3.gif)

### 基本使用

#### 1.添加Gradle依赖

``` groovy
dependencies {
    compile 'cn.bingoogolapple:bga-alertcontroller:latestVersion@aar'
}
```

#### 2.在java代码中使用BGAAlertController

``` Java
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
```

### 高级用法

> 1.如果您不满意默认的颜色，在自己项目的colors.xml中定义以下相应地颜色即可（不用全部定义，对不满意的值重新定义即可）

``` xml
<resources>
    <color name="ac_bg_translucent">#01000000</color>
    <color name="ac_bg_content">#F9F9F9</color>

    <color name="ac_item_text_default">#007AFF</color>
    <color name="ac_item_text_destructive">#FF3B30</color>
    <color name="ac_item_bg_pressed">#EBEBEB</color>

    <color name="ac_alert_title">#000000</color>
    <color name="ac_alert_message">#000000</color>

    <color name="ac_action_sheet_title">#4d4d4d</color>
    <color name="ac_action_sheet_message">#8f8f8f</color>
</resources>
```

> 2.如果您不满意默认的间距和字体大小，在自己项目的dimens.xml中定义以下相应的dimen（不用全部定义，对不满意的值重新定义即可）

``` xml
<resources>
    <dimen name="ac_radius">10dp</dimen>
    <dimen name="ac_gap">10dp</dimen>
    <dimen name="ac_line_height">1dp</dimen>
    <dimen name="ac_item_text_size">18sp</dimen>
    <dimen name="ac_action_sheet_text_size_title">14sp</dimen>
    <dimen name="ac_action_sheet_text_size_message">14sp</dimen>

    <dimen name="ac_alert_text_size_title">18sp</dimen>
    <dimen name="ac_alert_text_size_message">14sp</dimen>
</resources>
```

> 3.如果您不满意默认动画时间，在自己项目的integers.xml中定义以下相应的integer
``` xml
<resources>
    <integer name="ac_animation_duration">300</integer>
</resources>
```

## 作者联系方式

| 个人主页 | 邮箱 |
| ------------- | ------------ |
| <a  href="https://www.bingoogolapple.cn" target="_blank">bingoogolapple.cn</a>  | <a href="mailto:bingoogolapple@gmail.com" target="_blank">bingoogolapple@gmail.com</a> |

| 个人微信号 | 微信群 | 公众号 |
| ------------ | ------------ | ------------ |
| <img width="180" alt="个人微信号" src="https://github.com/bingoogolapple/bga-god-assistant-config/raw/main/images/BGAQrCode.png"> | <img width="180" alt="微信群" src="https://github.com/bingoogolapple/bga-god-assistant-config/raw/main/images/WeChatGroup1QrCode.jpg"> | <img width="180" alt="公众号" src="https://github.com/bingoogolapple/bga-god-assistant-config/raw/main/images/GongZhongHao.png"> |

| 个人 QQ 号 | QQ 群 |
| ------------ | ------------ |
| <img width="180" alt="个人 QQ 号" src="https://github.com/bingoogolapple/bga-god-assistant-config/raw/main/images/BGAQQQrCode.jpg"> | <img width="180" alt="QQ 群" src="https://github.com/bingoogolapple/bga-god-assistant-config/raw/main/images/QQGroup1QrCode.jpg"> |

## 打赏支持作者

如果您觉得 BGA 系列开源库或工具软件帮您节省了大量的开发时间，可以扫描下方的二维码打赏支持。您的支持将鼓励我继续创作，打赏后还可以加我微信免费开通一年 [上帝小助手浏览器扩展/插件开发平台](https://github.com/bingoogolapple/bga-god-assistant-config) 的会员服务

| 微信 | QQ | 支付宝 |
| ------------- | ------------- | ------------- |
| <img width="180" alt="微信" src="https://github.com/bingoogolapple/bga-god-assistant-config/raw/main/images/donate-wechat.jpg"> | <img width="180" alt="QQ" src="https://github.com/bingoogolapple/bga-god-assistant-config/raw/main/images/donate-qq.jpg"> | <img width="180" alt="支付宝" src="https://github.com/bingoogolapple/bga-god-assistant-config/raw/main/images/donate-alipay.jpg"> |

## 作者项目推荐

* 欢迎您使用我开发的第一个独立开发软件产品 [上帝小助手浏览器扩展/插件开发平台](https://github.com/bingoogolapple/bga-god-assistant-config)

## License

```
Copyright 2015 bingoogolapple

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```

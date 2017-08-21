# Android Modules
Android Modules contains simple and common utils class. It contains more common utils like text, toast, alert dialog etc,.

## Prerequisites
```
minSdkVersion 17
```
## Installation

Step 1. Add the JitPack repository to your build file
Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			maven { url 'https://jitpack.io' }
		}
	}

Step 2. Add the dependency

	dependencies {
	        compile 'com.github.thapovan-inc.android-modules:common-utils:v1.0.0'
            compile 'com.github.thapovan-inc.android-modules:custom-ui:v1.0.0'
	}
  
## How do I use?
Its realy very simple
```
ToastUtil.showToast(context, message);
```
```
TextUtil.cleanupString(string);
```
```
TextUtil.isValidString(string);
```
```
DialogUtil.showAlert(context, title, message);
```
```
DialogUtil.showAlertAndFinish(context, title, message);
```
```
DialogUtil.showYesDialogWithListener(Activity activity,
                                             String title,
                                             String message,
                                             String okBtn,
                                             DialogInterface.OnClickListener listener);
```
## Authors
Thapovan Android Team - [Thapovan Info Systems Inc](http://www.thapovan-inc.com/)

## License
This project is licensed under the MIT License.

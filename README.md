[![](https://jitpack.io/v/thapovan-inc/android-modules.svg)](https://jitpack.io/#thapovan-inc/android-modules)

# Android Modules
Android Modules contains simple and common utils class. It contains more common utils like text, toast, alert dialog etc,.

# Table of Contents

1. [Prerequisites](#prerequisites)
1. [Installation ](#installation)
1. [Moduels](#utils)
    1. [Common Utils](#common-utils)
    1. [Custom UI](#custom-ui)
    1. [Image Utils](#image-utils)
    1. [Social Utils](#social-utils)
1. [Authors](#authors)
1. [License](#license)

## Prerequisites
```
minSdkVersion 15
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
	        compile 'com.github.thapovan-inc.android-modules:common-utils:1.0.1'
            compile 'com.github.thapovan-inc.android-modules:custom-ui:1.0.1'
            compile 'com.github.thapovan-inc.android-modules:image-utils:1.0.1'
            compile 'com.github.thapovan-inc.android-modules:social-network:1.0.1'
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

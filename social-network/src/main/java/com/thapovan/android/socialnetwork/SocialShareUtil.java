package com.thapovan.android.socialnetwork;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.annotation.IntDef;
import android.support.v4.app.ShareCompat;
import android.util.Log;

import com.facebook.share.model.ShareHashtag;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SocialShareUtil {

    public static final String PACKAGE_FACEBOOK = "com.facebook.katana";
    public static final String PACKAGE_TWITTER  = "com.twitter.android";

    public static final int SOURCE_FACEBOOK = 1;
    public static final int SOURCE_TWITTER  = 2;
    public static final int SOURCE_SMS      = 3;
    public static final int SOURCE_EMAIL    = 4;

    private static final String TAG = "SocialShareUtil";

    private SocialShareUtil() {
    }

    @IntDef({SOURCE_FACEBOOK, SOURCE_TWITTER, SOURCE_SMS, SOURCE_EMAIL})
    public @interface ShareSource{}

    public static void share(Activity activity,String chooserTitle, String subject, String content, @ShareSource int source){
        share(activity,chooserTitle, subject, content, null, null, source);
    }

    public static void share(Activity activity,String chooserTitle, String subject, String content, String url, @ShareSource int source){
        share(activity,chooserTitle, subject, content, url, null, source);
    }

    public static void share(Activity activity,String chooserTitle, String subject, String content, String url, String hashTag, @ShareSource int source){

        switch (source){
            case SOURCE_FACEBOOK:
                try {
                    Uri shareUri = null;
                    if(url != null){
                        shareUri = Uri.parse(url);
                    }
                    ShareHashtag shareHashTag = null;
                    if(hashTag != null) {
                        shareHashTag = new ShareHashtag.Builder()
                                .setHashtag("#" + hashTag)
                                .build();
                    }
                    ShareLinkContent link = new ShareLinkContent.Builder()
                            .setContentUrl(shareUri)
                            .setQuote(content)
                            .setShareHashtag(shareHashTag)
                            .build();
                    ShareDialog shareDialog = new ShareDialog(activity);
                    shareDialog.show(link);
                } catch (Exception e){
                    Log.e(TAG, "share: ", e);
                }

                break;
            case SOURCE_TWITTER:

                String shareContent = content;
                if(hashTag != null){
                    shareContent = content + " #" + hashTag;
                }
                Intent twitterIntent = ShareCompat.IntentBuilder.from(activity)
                        .setChooserTitle(chooserTitle)
                        .setText(shareContent)
                        .setType("text/plain")
                        .getIntent().setPackage(PACKAGE_TWITTER);

                if(twitterIntent.resolveActivity(activity.getPackageManager())!=null){
                    activity.startActivity(twitterIntent);
                }else{
                    String sharerUrl = "https://twitter.com/intent/tweet?text=" + content;

                    if(url != null){
                        sharerUrl = sharerUrl + "&url=" + url;
                    }
                    if(hashTag != null){
                        sharerUrl = sharerUrl + "&hashtags=" +hashTag;
                    }

                    final Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(sharerUrl));
                    activity.startActivity(intent);
                }
                break;
            case SOURCE_SMS:
                ShareCompat.IntentBuilder.from(activity)
                        .setText(content)
                        .setType("text/plain")
                        .setChooserTitle(chooserTitle)
                        .startChooser();
                break;
            case SOURCE_EMAIL:
                ShareCompat.IntentBuilder.from(activity)
                        .setSubject(subject)
                        .setType("message/rfc822")
                        .setText(content)
                        .setEmailTo(new String[]{})
                        .setChooserTitle(chooserTitle)
                        .startChooser();
                break;
        }

    }

}

package com.thapovan.android.firebase;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.MainThread;
import android.support.annotation.StringRes;
import android.support.annotation.StyleRes;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.ErrorCodes;
import com.firebase.ui.auth.IdpResponse;
import com.google.android.gms.common.Scopes;
import com.google.firebase.auth.FirebaseAuth;
import com.thapovan.android.R;
import com.thapovan.android.app.AppActivity;
import com.thapovan.android.commonutils.log.L;
import com.thapovan.android.commonutils.toast.ToastUtil;
import com.thapovan.android.imageutils.ImageUtil;
import com.thapovan.android.socialnetwork.facebook.FacebookLogin;
import com.thapovan.android.socialnetwork.facebook.model.FacebookProfile;
import com.thapovan.android.socialnetwork.facebook.subscriber.FacebookEventSubscriber;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FirebaseActivity extends AppActivity {

    @BindView(R.id.root)
    View mRootView;

    FirebaseActivity mActivity;

    private static final int RC_SIGN_IN = 101;
    private static final String UNCHANGED_CONFIG_VALUE = "CHANGE-ME";
    private static final String FIREBASE_TOS_URL = "https://firebase.google.com/terms/";
    private static final String FIREBASE_PRIVACY_POLICY_URL = "https://firebase.google.com/terms/analytics/#7_privacy";

    public static void start(final Activity activity) {
        final Intent intent = new Intent(activity, FirebaseActivity.class);
        activity.startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mActivity = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase);
        ButterKnife.bind(mActivity);

        FirebaseAuth auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser() != null) {
            startSignedInActivity(null);
            finish();
            return;
        }
    }

    @OnClick(R.id.btnLogin)
    public void onBtnLogin() {
        L.i("ONNN $$$$");
        initFireBaseLogin();
    }

    private void initFireBaseLogin() {
        L.i("ONNN ####");
        startActivityForResult(
                AuthUI.getInstance().createSignInIntentBuilder()
                        .setTheme(getSelectedTheme())
                        .setLogo(getSelectedLogo())
                        .setAvailableProviders(getSelectedProviders())
                        .setTosUrl(getSelectedTosUrl())
                        .setPrivacyPolicyUrl(getSelectedPrivacyPolicyUrl())
                        .setIsSmartLockEnabled(true)
                        .setAllowNewEmailAccounts(true)
                        .build(),
                RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        L.i("ONNN 1");
        if (requestCode == RC_SIGN_IN) {
            L.i("ONNN 2");
            handleSignInResponse(resultCode, data);
            L.i("ONNN 3");
            return;
        }
        L.i("ONNN 4");
        showSnackbar(R.string.unknown_response);
    }

    @MainThread
    private void handleSignInResponse(int resultCode, Intent data) {
        IdpResponse response = IdpResponse.fromResultIntent(data);

        // Successfully signed in
        if (resultCode == RESULT_OK) {
            L.i("ONNN 5");
            startSignedInActivity(response);
            finish();
            return;
        } else {
            L.i("ONNN 6");
            // Sign in failed
            if (response == null) {
                // User pressed back button
                showSnackbar(R.string.sign_in_cancelled);
                return;
            }

            if (response.getErrorCode() == ErrorCodes.NO_NETWORK) {
                showSnackbar(R.string.no_internet_connection);
                return;
            }

            if (response.getErrorCode() == ErrorCodes.UNKNOWN_ERROR) {
                showSnackbar(R.string.unknown_error);
                return;
            }
        }
        L.i("ONNN 7");
        showSnackbar(R.string.unknown_sign_in_response);
    }


    private void startSignedInActivity(IdpResponse response) {
        L.i("ONNN 8");
        startActivity(
                SignedInActivity.createIntent(
                        this,
                        response,
                        new SignedInActivity.SignedInConfig(
                                getSelectedLogo(),
                                getSelectedTheme(),
                                getSelectedProviders(),
                                getSelectedTosUrl(),
                                false,
                                false)));
    }

    @MainThread
    @StyleRes
    private int getSelectedTheme() {
        return R.style.AppTheme;
//        return AuthUI.getDefaultTheme();
    }

    @MainThread
    @DrawableRes
    private int getSelectedLogo() {
        return R.drawable.ic_thapovan_logo;
//        return AuthUI.NO_LOGO;
    }

    @MainThread
    private String getSelectedTosUrl() {
        return FIREBASE_TOS_URL;
    }

    @MainThread
    private String getSelectedPrivacyPolicyUrl() {
        return FIREBASE_PRIVACY_POLICY_URL;
    }

    @MainThread
    private List<AuthUI.IdpConfig> getSelectedProviders() {
        List<AuthUI.IdpConfig> selectedProviders = new ArrayList<>();

        if (true) {
            selectedProviders.add(
                    new AuthUI.IdpConfig.Builder(AuthUI.GOOGLE_PROVIDER)
                            .setPermissions(getGooglePermissions())
                            .build());
        }

        if (!isFacebookMisconfigured()) {
            selectedProviders.add(
                    new AuthUI.IdpConfig.Builder(AuthUI.FACEBOOK_PROVIDER)
                            .setPermissions(getFacebookPermissions())
                            .build());
        }

        if (!isTwitterMisconfigured()) {
            selectedProviders.add(new AuthUI.IdpConfig.Builder(AuthUI.TWITTER_PROVIDER).build());
        }

        if (true) {
            selectedProviders.add(new AuthUI.IdpConfig.Builder(AuthUI.EMAIL_PROVIDER).build());
        }

        if (true) {
            selectedProviders.add(
                    new AuthUI.IdpConfig.Builder(AuthUI.PHONE_VERIFICATION_PROVIDER).build());
        }

        return selectedProviders;
    }


    @MainThread
    private boolean isGoogleMisconfigured() {
        return UNCHANGED_CONFIG_VALUE.equals(getString(R.string.default_web_client_id));
    }

    @MainThread
    private boolean isFacebookMisconfigured() {
        return UNCHANGED_CONFIG_VALUE.equals(getString(R.string.facebook_application_id));
    }

    @MainThread
    private boolean isTwitterMisconfigured() {
        List<String> twitterConfigs = Arrays.asList(
                getString(R.string.twitter_consumer_key),
                getString(R.string.twitter_consumer_secret)
        );

        return twitterConfigs.contains(UNCHANGED_CONFIG_VALUE);
    }

    @MainThread
    private List<String> getFacebookPermissions() {
        List<String> result = new ArrayList<>();
//        if (true) {
//            result.add("public_profile");
//        }
        if (true) {
            result.add("user_friends");
        }
        if (true) {
            result.add("user_photos");
        }
        return result;
    }

    @MainThread
    private List<String> getGooglePermissions() {
        List<String> result = new ArrayList<>();
        if (true) {
            result.add("https://www.googleapis.com/auth/youtube.readonly");
        }
        if (true) {
            result.add(Scopes.DRIVE_FILE);
        }
        return result;
    }
    @MainThread
    private void showSnackbar(@StringRes int errorMessageRes) {
        Snackbar.make(mRootView, errorMessageRes, Snackbar.LENGTH_LONG).show();
    }
}

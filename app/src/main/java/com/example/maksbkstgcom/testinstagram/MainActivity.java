package com.example.maksbkstgcom.testinstagram;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.instagram.instagramapi.engine.InstagramEngine;
import com.instagram.instagramapi.exceptions.InstagramException;
import com.instagram.instagramapi.interfaces.InstagramAPIResponseCallback;
import com.instagram.instagramapi.interfaces.InstagramLoginCallbackListener;
import com.instagram.instagramapi.objects.IGPagInfo;
import com.instagram.instagramapi.objects.IGSession;
import com.instagram.instagramapi.objects.IGTag;
import com.instagram.instagramapi.utils.InstagramKitLoginScope;
import com.instagram.instagramapi.widgets.InstagramLoginButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InstagramLoginButton instagramLoginButton = (InstagramLoginButton) findViewById(R.id.instagramLoginButton);

        String[] scopes = { InstagramKitLoginScope.BASIC, InstagramKitLoginScope.COMMENTS, InstagramKitLoginScope.PUBLIC_ACCESS };
        instagramLoginButton.setInstagramLoginCallback(instagramLoginCallbackListener);
        instagramLoginButton.setScopes(scopes);

    }

    InstagramLoginCallbackListener instagramLoginCallbackListener = new InstagramLoginCallbackListener() {
        @Override
        public void onSuccess(IGSession session) {

            InstagramEngine.getInstance(MainActivity.this).searchTagsWithName(new InstagramAPIResponseCallback<ArrayList<IGTag>>() {
                @Override
                public void onResponse(ArrayList<IGTag> responseObject, IGPagInfo pageInfo) {
                    Toast.makeText(MainActivity.this, "success", Toast.LENGTH_LONG).show();
                }

                @Override
                public void onFailure(InstagramException exception) {
                    Toast.makeText(MainActivity.this, "failure", Toast.LENGTH_LONG).show();
                }
            }, "kievme");

//            Toast.makeText(MainActivity.this, "Wow!!! User trusts you :) " + session.getAccessToken(),
//                    Toast.LENGTH_LONG).show();

        }

        @Override
        public void onCancel() {
            Toast.makeText(MainActivity.this, "Oh Crap!!! Canceled.",
                    Toast.LENGTH_LONG).show();

        }

        @Override
        public void onError(InstagramException error) {
            Toast.makeText(MainActivity.this, "User does not trust you :(\n " + error.getMessage(),
                    Toast.LENGTH_LONG).show();

        }
    };

}

package express.express.exercise.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.google.android.gms.ads.AdView;
import com.workout.exercise.R;
import express.express.exercise.fragment.fragmentLaunchActivity;
import express.express.exercise.util.adMobManager;

public class MainActivity extends AppCompatActivity {
    String appPackageName;
    private Context context;
    private AdView adView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        appPackageName = getPackageName();


        adView = (AdView) findViewById(R.id.adView);

        if (getString(R.string.ads_visibility).equals("yes")) {
            adMobManager a = new adMobManager();
            a.LoadBannerAdd(MainActivity.this, null);

        } else {
            adView.setVisibility(View.GONE);
        }

        context = this;
    }

    public void startExercise(View view) {
        startActivity(new Intent(this, ExerciseActivity.class));
    }

    public void myExercise(View view) {
        Intent i = new Intent(context, fragmentLaunchActivity.class);
        i.putExtra("TAG", "myExercise");
        startActivity(i);
    }

    public void rateOnclick(View view) {
        Intent rateIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName));
        if (rateIntent != null)
            startActivity(rateIntent);
    }

    public void shareOnClick(View view) {
        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, getString(R.string.sharing_text) + "\n" + "https://play.google.com/store/apps/details?id=" + appPackageName);
        startActivity(Intent.createChooser(sharingIntent, "Share using"));
    }

    public void moreOnClick(View view) {
        Intent moreIntent = new Intent(Intent.ACTION_VIEW).setData(Uri.parse("https://play.google.com/store/apps/developer?id=" + getString(R.string.more_apps)));
        startActivity(moreIntent);
    }

    public void exerciseOnClick(View view) {
        Intent i = new Intent(context, fragmentLaunchActivity.class);
        i.putExtra("TAG", "exercise");
        startActivity(i);
    }

    public void calculatorOnClick(View view) {
        Intent i = new Intent(context, fragmentLaunchActivity.class);
        i.putExtra("TAG", "bmi");
        startActivity(i);
    }

    public void settingOnClick(View view) {
        Intent i = new Intent(context, fragmentLaunchActivity.class);
        i.putExtra("TAG", "settings");
        startActivity(i);
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(context)
                .setTitle("Quit")
                .setMessage("Do you want to quit this app ?")
                .setPositiveButton("Rate US", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent rateIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName));
                        if (rateIntent != null)
                            startActivity(rateIntent);
                    }
                })
                .setNegativeButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                       finishAffinity();
                    }
                }).show();

    }
}

package com.example.kevin.slugcentral;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;

public class GEActivity extends AppCompatActivity {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    CheckBox CC,ER,IM,MF,SI,SR,TA,PEE,PEH,PET,PRE,PRC,PRS,C1,C2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ge);
        //save the states
        pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
        editor = pref.edit();
       // ref all the boxes
        CC = (CheckBox)findViewById(R.id.checkCC);
        ER = (CheckBox)findViewById(R.id.checkER);
        IM = (CheckBox)findViewById(R.id.checkIM);
        MF = (CheckBox)findViewById(R.id.checkMF);
        SI = (CheckBox)findViewById(R.id.checkSI);
        SR = (CheckBox)findViewById(R.id.checkSR);
        TA = (CheckBox)findViewById(R.id.checkTA);
        PEE = (CheckBox)findViewById(R.id.checkPEE);
        PEH = (CheckBox)findViewById(R.id.checkPEH);
        PET = (CheckBox)findViewById(R.id.checkPET);
        PRE = (CheckBox)findViewById(R.id.checkPRE);
        PRC = (CheckBox)findViewById(R.id.checkPRC);
        PRS = (CheckBox)findViewById(R.id.checkPRS);
        C1 = (CheckBox)findViewById(R.id.checkC1);
        C2 = (CheckBox)findViewById(R.id.checkC2);

        if (pref.getBoolean("checkCC", false)){ //false is default value
            CC.setChecked(true); //it was checked
        } else{
            CC.setChecked(false); //it was NOT checked
        }
        if (pref.getBoolean("checkER", false)){ //false is default value
            ER.setChecked(true); //it was checked
        } else{
            ER.setChecked(false); //it was NOT checked
        }
        if (pref.getBoolean("checkIM", false)){ //false is default value
            IM.setChecked(true); //it was checked
        } else{
            IM.setChecked(false); //it was NOT checked
        }
        if (pref.getBoolean("checkMF", false)){ //false is default value
            MF.setChecked(true); //it was checked
        } else{
            MF.setChecked(false); //it was NOT checked
        }
        if (pref.getBoolean("checkSI", false)){ //false is default value
            SI.setChecked(true); //it was checked
        } else{
            SI.setChecked(false); //it was NOT checked
        }
        if (pref.getBoolean("checkSR", false)){ //false is default value
            SR.setChecked(true); //it was checked
        } else{
            SR.setChecked(false); //it was NOT checked
        }
        if (pref.getBoolean("checkTA", false)){ //false is default value
            TA.setChecked(true); //it was checked
        } else{
            TA.setChecked(false); //it was NOT checked
        }
        if (pref.getBoolean("checkPEE", false)){ //false is default value
            PEE.setChecked(true); //it was checked
        } else{
            PEE.setChecked(false); //it was NOT checked
        }
        if (pref.getBoolean("checkPEH", false)){ //false is default value
            PEH.setChecked(true); //it was checked
        } else{
            PEH.setChecked(false); //it was NOT checked
        }
        if (pref.getBoolean("checkPET", false)){ //false is default value
            PET.setChecked(true); //it was checked
        } else{
            PET.setChecked(false); //it was NOT checked
        }
        if (pref.getBoolean("checkPRE", false)){ //false is default value
            PRE.setChecked(true); //it was checked
        } else{
            PRE.setChecked(false); //it was NOT checked
        }
        if (pref.getBoolean("checkPRC", false)){ //false is default value
            PRC.setChecked(true); //it was checked
        } else{
            PRC.setChecked(false); //it was NOT checked
        }
        if (pref.getBoolean("checkPRS", false)){ //false is default value
            PRS.setChecked(true); //it was checked
        } else{
            PRS.setChecked(false); //it was NOT checked
        }
        if (pref.getBoolean("checkC1", false)){ //false is default value
            C1.setChecked(true); //it was checked
        } else{
            C1.setChecked(false); //it was NOT checked
        }
        if (pref.getBoolean("checkC2", false)){ //false is default value
            C2.setChecked(true); //it was checked
        } else{
            C2.setChecked(false); //it was NOT checked
        }
    }
    @Override
    public void onBackPressed() {
        final Context context = this;
        Intent i = new Intent(context, Directory.class);
        editor.putBoolean("checkCC", CC.isChecked());
        editor.putBoolean("checkER", ER.isChecked());
        editor.putBoolean("checkIM", IM.isChecked());
        editor.putBoolean("checkMF", MF.isChecked());
        editor.putBoolean("checkSI", SI.isChecked());
        editor.putBoolean("checkSR", SR.isChecked());
        editor.putBoolean("checkTA", TA.isChecked());
        editor.putBoolean("checkPEE", PEE.isChecked());
        editor.putBoolean("checkPEH", PEH.isChecked());
        editor.putBoolean("checkPET", PET.isChecked());
        editor.putBoolean("checkPRE", PRE.isChecked());
        editor.putBoolean("checkPRC", PRC.isChecked());
        editor.putBoolean("checkPRS", PRS.isChecked());
        editor.putBoolean("checkC1", C1.isChecked());
        editor.putBoolean("checkC2", C2.isChecked());
        editor.commit(); // commit changes
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
        super.onBackPressed();
    }

}

package appewtc.masterung.rmutsvcode.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import appewtc.masterung.rmutsvcode.GetAllData;
import appewtc.masterung.rmutsvcode.MyAlert;
import appewtc.masterung.rmutsvcode.R;
import appewtc.masterung.rmutsvcode.ServiceActivity;

/**
 * Created by masterUNG on 7/3/2017 AD.
 */

public class MainFragment extends Fragment {

    private Button button;
    private EditText userEditText, passwordEditText;
    private String userString, passwordString;

    public static MainFragment mainInstance() {
        MainFragment mainFragment = new MainFragment();
        Bundle bundle = new Bundle();
        mainFragment.setArguments(bundle);
        return mainFragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.main_fragment_layout, container, false);

        return view;
    }   // onCreateView

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //New Register Controller
        newRegisterController();

        //Login Controller
        loginController();


    }   // onActivityCreate

    private void loginController() {

        //Initial View
        button = (Button) getView().findViewById(R.id.btnLogin);
        userEditText = (EditText) getView().findViewById(R.id.edtUser);
        passwordEditText = (EditText) getView().findViewById(R.id.edtPassword);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                userString = userEditText.getText().toString().trim();
                passwordString = passwordEditText.getText().toString().trim();

                if (userString.equals("") || passwordString.equals("")) {
                    //Have Space
                    MyAlert myAlert = new MyAlert(getActivity());
                    myAlert.myDialog("มีช่องว่าง", "กรุณากรอกทุกช่อง ซิค่าาาาา");
                } else {
                    //No Space
                    checkUserAndPass();
                }

            }   // onClick
        });


    }

    private void checkUserAndPass() {

        String tag = "4JulyV2";
        String urlPHP = "http://androidthai.in.th/piw/getAllUserMaster.php";
        boolean b = true;

        String[] columnStrings1 = new String[]{"id", "Name", "User", "Password"};
        String[] strings = new String[columnStrings1.length];

        try {

            GetAllData getAllData = new GetAllData(getActivity());
            getAllData.execute(urlPHP);
            String strJSON = getAllData.get();
            Log.d(tag, "JSON ==> " + strJSON);

            JSONArray jsonArray = new JSONArray(strJSON);
            for (int i=0; i<jsonArray.length(); i+=1) {

                JSONObject jsonObject = jsonArray.getJSONObject(i);
                if (userString.equals(jsonObject.getString("User"))) {
                    b = false;
                    for (int i1=0; i1<columnStrings1.length;i1++) {
                        strings[i1] = jsonObject.getString(columnStrings1[i1]);
                        Log.d(tag, "strings[" + i1 + "] ==> " + strings[i1]);
                    }
                }

            }   // for

            if (b) {
                //User False
                MyAlert myAlert = new MyAlert(getActivity());
                myAlert.myDialog("User False", "No This User in my database");
            } else if (passwordString.equals(strings[3])) {
                //Password True
                Toast.makeText(getActivity(), "Welcome " + strings[1],
                        Toast.LENGTH_SHORT).show();

                //Intent to ServiceActivity
                Intent intent = new Intent(getActivity(), ServiceActivity.class);
                intent.putExtra("Login", strings);
                startActivity(intent);
                getActivity().finish();

            } else {
                //Password False
                MyAlert myAlert = new MyAlert(getActivity());
                myAlert.myDialog("Password False", "Please Try Again Password False");
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void newRegisterController() {
        TextView textView = (TextView) getView().findViewById(R.id.txtNewRegister);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.relContent, NewRegisterFragment.newInstance())
                        .commit();
            }
        });
    }
}   // Main Class

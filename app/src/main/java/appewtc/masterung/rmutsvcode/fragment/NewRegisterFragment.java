package appewtc.masterung.rmutsvcode.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import appewtc.masterung.rmutsvcode.MyAlert;
import appewtc.masterung.rmutsvcode.R;

/**
 * Created by masterUNG on 7/3/2017 AD.
 */

public class NewRegisterFragment extends Fragment{

    //Explicit
    private Button button;
    private EditText nameEditText, userEditText, passwordEditText;



    public static NewRegisterFragment newInstance() {
        NewRegisterFragment newRegisterFragment = new NewRegisterFragment();
        Bundle bundle = new Bundle();
        newRegisterFragment.setArguments(bundle);
        return newRegisterFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.new_register_fragment_layout, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //Back Controller
        backController();

        //New Register Controller
        newRegisterController();


    }   // onActivityCreate

    private void newRegisterController() {

        //Initial View
        button = (Button) getView().findViewById(R.id.btnNewRegister);
        nameEditText = (EditText) getView().findViewById(R.id.edtName);
        userEditText = (EditText) getView().findViewById(R.id.edtUser);
        passwordEditText = (EditText) getView().findViewById(R.id.edtPassword);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Get Value From Edit Text
                String strName = nameEditText.getText().toString().trim();
                String strUser = userEditText.getText().toString().trim();
                String strPassword = passwordEditText.getText().toString().trim();

                //Check Space
                if (strName.length() == 0 || strUser.length() == 0 || strPassword.length() == 0) {
                    //Have Space
                    Log.d("4JulyV1", "Have Space");
                    MyAlert myAlert = new MyAlert(getActivity());
                    myAlert.myDialog("Have Space", "Please Fill All Every Blank");

                } else {
                    //No Space
                    Log.d("4JulyV1", "No Space");

                }


            }   // onClick
        });




    }

    private void backController() {
        ImageView imageView = (ImageView) getView().findViewById(R.id.btnBack);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.relContent, MainFragment.mainInstance())
                        .commit();

            }
        });
    }

}   // Main Class

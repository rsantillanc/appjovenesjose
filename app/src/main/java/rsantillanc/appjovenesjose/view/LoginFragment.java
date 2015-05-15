package rsantillanc.appjovenesjose.view;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.larvalabs.svgandroid.SVG;
import com.larvalabs.svgandroid.SVGParser;

import rsantillanc.appjovenesjose.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link LoginFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class LoginFragment extends Fragment {

    private ImageView ivLogo;

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_login, container, false);
        initViews(v);
        loadSVGImage();
        return v;
    }

    public void loadSVGImage(){
        SVG mSVG = SVGParser.getSVGFromResource(getResources(),R.raw.android);
        Drawable drawable = mSVG.createPictureDrawable();
        ivLogo.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        ivLogo.setImageDrawable(drawable);
    }

    public void initViews(View v){
        ivLogo = (ImageView)v.findViewById(R.id.iv_logo);
    }

    public interface OnFragmentInteractionListener {
        public void onFragmentInteraction(Uri uri);
    }

}

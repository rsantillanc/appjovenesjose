package rsantillanc.appjovenesjose.view;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import rsantillanc.appjovenesjose.R;
import rsantillanc.appjovenesjose.adapter.NavDrawerAdapter;
import rsantillanc.appjovenesjose.moldel.NavDrawerModel;

/**
 * Created by RenzoD on 29/04/2015.
 */
public class DrawerFragment extends Fragment {

    private final static String TAG = DrawerFragment.class.getSimpleName();

    private RecyclerView recView;
    private ActionBarDrawerToggle draTog;
    private LayoutInflater layIn;
    private DrawerLayout draLay;
    private NavDrawerAdapter navDraAdp;
    private View contView;
    private static String[] titles = null;
    private static int[] icons = {R.drawable.ic_user
                                ,R.drawable.ic_event
                                ,R.drawable.ic_news
                                ,R.drawable.ic_location
                                ,R.drawable.ic_about};

    private FragmentDrawerListener drawerListener;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // drawer labels
        titles = getActivity().getResources().getStringArray(R.array.nav_drawer_labels);
    }

    public void setDrawerListener(FragmentDrawerListener listener) {
        this.drawerListener = listener;
    }

    public static List<NavDrawerModel> getItems() {
        List<NavDrawerModel> data = new ArrayList<>();

        // preparing navigation drawer items
        for (int i = 0; i < titles.length; i++) {
            NavDrawerModel navItem = new NavDrawerModel();
            navItem.setTitle(titles[i]);
            navItem.setIcon(icons[i]);
            data.add(navItem);
        }

        return data;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View layView = inflater.inflate(R.layout.fragment_nav_drawer,container,false);
        recView = (RecyclerView)layView.findViewById(R.id.drawerList);

        navDraAdp = new NavDrawerAdapter(getItems(),getActivity());
        recView.setAdapter(navDraAdp);
        recView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(),recView,new ClickListener() {

            @Override
            public void onClick(View view, int position) {
                drawerListener.onDrawerItemSelected(view, position);
                draLay.closeDrawer(contView);
            }

            @Override
            public void onLongClick(View view, int position) {
                Toast.makeText(getActivity(),"LongClick",Toast.LENGTH_LONG).show();
            }
        }));

        return layView;
    }


    public void setUp(int fragmentId, DrawerLayout drawerLayout, final Toolbar toolbar) {
        contView = getActivity().findViewById(fragmentId);
        draLay = drawerLayout;
        draTog = new ActionBarDrawerToggle(getActivity(), drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                toolbar.setAlpha(1 - slideOffset / 2);
            }
        };

        draLay.setDrawerListener(draTog);
        draLay.post(new Runnable() {
            @Override
            public void run() {
                draTog.syncState();
            }
        });

    }

    public interface FragmentDrawerListener {
        public void onDrawerItemSelected(View view, int position);
    }

        public static interface ClickListener {
            public void onClick(View view, int position);
            public void onLongClick(View view, int position);
        }


    static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        private ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final ClickListener clickListener) {
            this.clickListener = clickListener;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && clickListener != null) {
                        clickListener.onLongClick(child, recyclerView.getChildPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
                clickListener.onClick(child, rv.getChildPosition(child));
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {

        }
    }

    }



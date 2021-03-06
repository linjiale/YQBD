package com.yqbd.yqbd.usergroupFragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.ListView;
import com.yqbd.yqbd.R;
import com.yqbd.yqbd.activities.group.CancelJoinActivity;
import com.yqbd.yqbd.beans.GroupInfoBean;
import com.yqbd.yqbd.usergroupFragments.adapters.GroupListAdapter;

import java.util.ArrayList;
import java.util.List;

public class GroupAppliedFragment extends Fragment {
    private OnFragmentInteractionListener mListener;

    private ArrayList<GroupInfoBean> groupsApplied = new ArrayList<>();

    private ListView groupAppliedListView;

    public GroupAppliedFragment() {
        // Required empty public constructor
    }


    public static GroupAppliedFragment newInstance(List<GroupInfoBean> groupsApplied) {
        GroupAppliedFragment fragment = new GroupAppliedFragment();
        Bundle args = new Bundle();
        ArrayList<GroupInfoBean> list = new ArrayList<>();
        list.addAll(groupsApplied);
        args.putParcelableArrayList("groupsApplied",list);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //接收数据
        if (getArguments() != null) {
            groupsApplied = getArguments().getParcelableArrayList("groupsApplied");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_group_applied, container, false);
        groupAppliedListView = (ListView) view.findViewById(R.id.list_group_applied);
        groupAppliedListView.setAdapter(new GroupListAdapter(getActivity(), R.layout.item_group_simple, groupsApplied));
        groupAppliedListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //点击跳转到退出小组申请页面
                Intent intent = new Intent(getContext(), CancelJoinActivity.class);
                intent.putExtra("groupId",groupsApplied.get(position).getGroupId());
                startActivity(intent);
            }
        });

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}

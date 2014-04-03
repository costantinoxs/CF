package it.cdpaf.computerfamily.fragment;

import org.json.JSONArray;
import it.cdpaf.computerfamily.helper.*;
import org.json.JSONException;
import org.json.JSONObject;

import it.cdpaf.computerfamily.R;


import it.cdpaf.computerfamily.entity.ListProduct;
import it.cdpaf.computerfamily.helper.Const;
import it.cdpaf.computerfamily.helper.HttpConnection;
import it.cdpaf.computerfamily.helper.ListProductSearchAdapter;

import android.app.AlertDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

/**
 * A dummy fragment representing a section of the app, but that simply
 * displays dummy text.
 */
public class AdvancedSearch extends Fragment {
	/**
	 * The fragment argument representing the section number for this
	 * fragment.
	 */
	private ListProduct list;	
	private Handler handler;
	private Dialogs dialogs;
	private int range=20;
	private int offset=0;
	
	public static final String ARG_SECTION_NUMBER = "section_number";
	
	public AdvancedSearch() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_advanced_search,
				container, false);
		
		TextView tvSearch = (TextView) rootView.findViewById(R.id.tvSearch);
		final EditText etSearch = (EditText) rootView.findViewById(R.id.etGenericSearch);
		Button btnSearch = (Button) rootView.findViewById(R.id.btnSearch);
		final ListView listView =(ListView) rootView.findViewById(R.id.list);
		
		View footerView = ((LayoutInflater)getActivity()
				.getSystemService(getActivity().LAYOUT_INFLATER_SERVICE))
				.inflate(R.layout.listfooter, null, false);
        listView.addFooterView(footerView);
		
		dialogs = new Dialogs();
		
		handler = new Handler() {
            @Override
            public void handleMessage(Message mess) {
            	int res = mess.arg1;
            	if(res==1){
            		ListProductSearchAdapter adapter = 
            				new ListProductSearchAdapter(getActivity(),
            				R.layout.product_choice_list_item, list);
            		listView.setAdapter(adapter);
            	}
            	if(res==0){
            		AlertDialog dialog=dialogs.ProductNotFount(getActivity());
            		dialog.show();
            	}
            }
		};
		
		footerView.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
            	System.gc();
            	offset=offset+20;
            	SearchData task = new SearchData();
				task.execute(etSearch.getText().toString());
            }

        });
		
		btnSearch.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				offset=0;
            	SearchData task = new SearchData();
				task.execute(etSearch.getText().toString());
			}
		});
		
		return rootView;
	}
	
	public class SearchData extends AsyncTask<String, Void, Void> {
		
		@Override
		protected void onPreExecute() {
		};

		@Override
		protected void onPostExecute(Void result) {
		}

		@Override
		protected Void doInBackground(String... params) {
			String strToSearch = params[0];
			JSONObject json = new JSONObject();
			try {
				HttpConnection connection = new HttpConnection();
				
				json.put("search", strToSearch);
				json.put("offset", offset);
				json.put("range", range);
				
				
				JSONArray array = connection.connectForCataalog("info_download_cf", json,Const.CONNECTION_TIMEOUT,Const.SOCKET_TIMEOUT);
				
				JSONObject jObj = (JSONObject) array.get(0);
				int res=Integer.parseInt(jObj.getString("result"));
				
				if(res==1){
					list=new ListProduct(array);
					Message message = handler.obtainMessage(1, res, 0);
					handler.sendMessage(message);
				}else{
					Message message = handler.obtainMessage(1, res, 0);
					handler.sendMessage(message);
				}
					
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				

			return null;
		};
	}
}

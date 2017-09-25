package co.blastlab.drinkingtime.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import co.blastlab.drinkingtime.R;

public class OrderFragment extends Fragment {

	boolean orderPlaced;

	ProgressBar orderProgressBar;

	LinearLayout orderView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.f_order, container, false);

		orderProgressBar = (ProgressBar) view.findViewById(R.id.orderProgress);
		orderView = (LinearLayout) view.findViewById(R.id.orderView);
		View orderButton = view.findViewById(R.id.orderButton);
		orderButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				orderClick();
			}
		});

		return view;
	}

	private void orderClick() {
		if (orderPlaced) {
			Toast.makeText(getActivity(), getString(R.string.order_in_progress), Toast.LENGTH_SHORT).show();
			return;
		}
		orderPlaced = true;
		orderProgressBar.setVisibility(View.VISIBLE);
		orderAsyncMock();
		Snackbar.make(orderView, getString(R.string.order_placed), Snackbar.LENGTH_LONG).show();
	}

	void orderAsyncMock() {
		new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
			@Override
			public void run() {
				orderPlaced = false;
				orderProgressBar.setVisibility(View.INVISIBLE);
				Snackbar.make(orderView, getString(R.string.order_finished), Snackbar.LENGTH_LONG).show();
			}
		}, 4500L);
	}
}
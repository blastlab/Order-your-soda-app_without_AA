package co.blastlab.drinkingtime.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import co.blastlab.drinkingtime.R;
import co.blastlab.drinkingtime.widget.DividerItemDecoration;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.view.LineChartView;

/*
	Temporary usage of graph library
 */
public class HistoryFragment extends Fragment {

	private List<String> orderList = new ArrayList<>();

	static final int MIN_PLOT_VAL = 0;
	static final int MAX_PLOT_VAL = 4;

	protected LineChartView chart;

	protected RecyclerView recyclerView;

	protected OrderAdapter adapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.f_history, container, false);

		chart = (LineChartView) view.findViewById(R.id.timeChart);
		recyclerView = (RecyclerView) view.findViewById(R.id.listview);

		setupChart();

		return view;
	}

	void setupChart() {
		// setting plot with fake data
		Random r = new Random();
		List<PointValue> values = new ArrayList<>();
		for (int i = 0; i <= 8; i++) {
			values.add(new PointValue(
				r.nextInt(MAX_PLOT_VAL - MIN_PLOT_VAL + 1) + MIN_PLOT_VAL,
				r.nextInt(MAX_PLOT_VAL - MIN_PLOT_VAL + 1) + MIN_PLOT_VAL
			));
		}

		//In most cased you can call data model methods in builder-pattern-like manner.
		Line line = new Line(values)
			.setColor(Color.BLUE)
			.setCubic(false)
			.setStrokeWidth(2);

		List<Line> lines = new ArrayList<>();
		lines.add(line);

		Axis axisY = new Axis().setHasLines(true);

		LineChartData data = new LineChartData();
		data.setAxisYLeft(axisY);
		data.setLines(lines);

		chart.setLineChartData(data);
		chart.setInteractive(false);

		// setting list with fake data
		for (int i = 0; i <= 8; i++) {
			orderList.add("Order " + i);
		}
		final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());

		adapter = new OrderAdapter(orderList);

		recyclerView.setLayoutManager(layoutManager);
		recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
		recyclerView.setHasFixedSize(true);
		recyclerView.setAdapter(adapter);

		// TODO: load data in background task

		adapter.notifyDataSetChanged();
	}

	class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {

		private List<String> orderList;

		class OrderViewHolder extends RecyclerView.ViewHolder {

			ImageView icon;

			TextView secondLine;

			TextView firstLine;

			OrderViewHolder(View view) {
				super(view);
				icon = (ImageView) view.findViewById(R.id.icon);
				secondLine = (TextView) view.findViewById(R.id.secondLine);
				firstLine = (TextView) view.findViewById(R.id.firstLine);
			}
		}

		OrderAdapter(List<String> moviesList) {
			this.orderList = moviesList;
		}

		@Override
		public OrderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
			View itemView = LayoutInflater.from(parent.getContext())
				.inflate(R.layout.i_order, parent, false);

			return new OrderViewHolder(itemView);
		}

		@Override
		public void onBindViewHolder(OrderViewHolder holder, int position) {
			String order = orderList.get(position);

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			String currentDateAndTime = sdf.format(new Date());

			holder.firstLine.setText(order);
			holder.secondLine.setText(currentDateAndTime);
		}

		@Override
		public int getItemCount() {
			return orderList.size();
		}
	}
}

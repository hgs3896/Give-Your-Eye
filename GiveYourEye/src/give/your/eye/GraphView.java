package give.your.eye;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Join;
import android.util.TypedValue;
import android.view.View;

public class GraphView extends View {

	private float size_width = dip(220);
	private float size_height = dip(220);
	private final float margin = dip(50);
	private final float graph_size_width = size_width - 2 * margin;
	private final float graph_size_height = size_height - 2 * margin;
	public final int MAX_WIDTH = (int) graph_size_width;
	public final int MAX_HEIGHT = (int) graph_size_height;

	FileOpenHelper f;

	private Paint linePaint;
	private Paint pointPaint;
	private Paint dotPaint;
	private Canvas graph;

	public GraphView(Context context) {
		super(context);
		f = new FileOpenHelper(context);

	}

	public GraphView(Context context, int dip_x, int dip_y) {
		super(context);
		size_width = dip(dip_x);
		size_height = dip(dip_y);
		f = new FileOpenHelper(context);

	}

	public float dip(int dip) {
		final float f = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
				dip, getResources().getDisplayMetrics());
		return f;
	}

	public void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		graph = canvas;

		// Holy Paint
		linePaint = new Paint();
		pointPaint = new Paint();
		dotPaint = new Paint();

		linePaint.setAntiAlias(true);
		linePaint.setColor(Color.WHITE);
		linePaint.setStrokeWidth(dip(2));
		linePaint.setStrokeJoin(Join.ROUND);

		pointPaint.setAntiAlias(true);
		pointPaint.setColor(Color.WHITE);
		pointPaint.setStrokeWidth(dip(5));
		pointPaint.setStrokeCap(Cap.ROUND);

		dotPaint.setAntiAlias(true);
		dotPaint.setColor(Color.RED);
		dotPaint.setStrokeWidth(dip(3));
		dotPaint.setStrokeCap(Cap.ROUND);

		// x axis
		drawGraph(0, 0, 0, (int) graph_size_height, linePaint);
		// y axis
		drawGraph(0, 0, (int) graph_size_width, 0, linePaint);

		// Border
		drawGraph(-20, -20, -20, MAX_HEIGHT + 20, linePaint);
		drawGraph(-20, MAX_HEIGHT + 20, MAX_WIDTH + 20, MAX_HEIGHT + 20,
				linePaint);
		drawGraph(MAX_WIDTH + 20, MAX_HEIGHT + 20, MAX_WIDTH + 20, -20,
				linePaint);
		drawGraph(MAX_WIDTH + 20, -20, -20, -20, linePaint);

		// arrow
		drawGraph(0, (int) graph_size_height, 10, (int) graph_size_height - 10,
				linePaint);
		drawGraph(0, (int) graph_size_height, -10,
				(int) graph_size_height - 10, linePaint);
		drawGraph((int) graph_size_width, 0, (int) graph_size_width - 10, -10,
				linePaint);
		drawGraph((int) graph_size_width, 0, (int) graph_size_width - 10, 10,
				linePaint);

		// dots
		drawPoint(0, 0);

		int[] arr = f.open();
		drawIntGraph(arr, Color.RED);

	}

	public void drawDotGraph(int p_x, int p_y, int q_x, int q_y, Paint p) {

		float point1_x = dip(p_x) + margin;
		float point1_y = size_height - dip(p_y) + margin;
		float point2_x = dip(q_x) + margin;
		float point2_y = size_height - dip(q_y) + margin;

		graph.drawLine(point1_x, point1_y, point2_x, point2_y, p);
		graph.drawPoint(point1_x, point1_y, pointPaint);
		graph.drawPoint(point2_x, point2_y, pointPaint);

	}

	public void drawGraph(int p_x, int p_y, int q_x, int q_y, Paint p) {

		float point1_x = dip(p_x) + margin;
		float point1_y = size_height - dip(p_y) + margin;
		float point2_x = dip(q_x) + margin;
		float point2_y = size_height - dip(q_y) + margin;

		graph.drawLine(point1_x, point1_y, point2_x, point2_y, p);

	}

	public void drawIntGraph(int[] arr, int col) {

		Paint p = new Paint();
		p.setAntiAlias(true);
		p.setColor(col);
		p.setStrokeWidth(dip(3));
		p.setStrokeCap(Cap.ROUND);

		for (int i = 0; i < arr.length - 1; i++) {
			drawDotGraph(8 * i, arr[i] * 2, 8 * (i + 1), arr[i + 1] * 2, p);
			drawDescription("" + i, i);
		}
	}

	public void drawPoint(int p_x, int p_y) {

		float point1_x = dip(p_x) + margin;
		float point1_y = size_height - dip(p_y) + margin;

		graph.drawPoint(point1_x, point1_y, pointPaint);
	}

	public void drawDot(int p_x, int p_y) {

		float point1_x = dip(p_x) + margin;
		float point1_y = size_height - dip(p_y) + margin;

		graph.drawPoint(point1_x, point1_y, dotPaint);
	}

	public void drawDescription(String txt, int column) {
		Paint p = new Paint();
		p.setAntiAlias(true);
		p.setTextSize(dip(10));
		p.setColor(Color.WHITE);
		p.setTextAlign(Paint.Align.CENTER);

		float point1_x = dip(8 * column) + margin;
		float point1_y = size_height - dip(-12) + margin;

		graph.drawText(txt, point1_x - dip(3), point1_y, p);
	}
}

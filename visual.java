package CW_Task2;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.JFrame;
import org.apache.commons.collections15.Transformer;

import cern.colt.Arrays;
import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.graph.DirectedSparseGraph;
import edu.uci.ics.jung.visualization.VisualizationImageServer;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.renderers.Renderer.VertexLabel.Position;

public class visual {
	DirectedSparseGraph<String, String> g;

	public void getVisual(ArrayList<String[]> data) {

		g = new DirectedSparseGraph<String, String>();

		for (String[] i : data) {
			System.out.println("0th: " + i[0]);
			g.addEdge(i[0], i[1], i[2]);
		}

		float dash[] = { 10.0f };
		final Stroke edgeStroke = new BasicStroke(1.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, dash,
				0.0f);
		Transformer<String, Stroke> edgeStrokeTransformer = new Transformer<String, Stroke>() {
			public Stroke transform(String s) {

				return edgeStroke;
			}

		};

		VisualizationImageServer<String, String> vs = new VisualizationImageServer<String, String>(
				new CircleLayout<>(g), new Dimension(600, 600));
		vs.getRenderContext().setEdgeStrokeTransformer(edgeStrokeTransformer);
		vs.getRenderContext().setVertexLabelTransformer(new ToStringLabeller<String>());
		vs.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller<String>());
		vs.getRenderer().getVertexLabelRenderer().setPosition(Position.N);

		JFrame frame = new JFrame();
		frame.getContentPane().add(vs);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);

	}
}
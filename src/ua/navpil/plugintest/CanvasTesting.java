package ua.navpil.plugintest;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class CanvasTesting {
	public static void main(String [] args) {
		Display display = new Display();
		
		Shell shell = new Shell(display);
		shell.setSize(300, 200);

		shell.setLayout(new FillLayout());
		
		Canvas canvas = new Canvas(shell, SWT.NONE);
		
		canvas.addPaintListener(new PaintListener() {
			
			@Override
			public void paintControl(PaintEvent e) {
				e.gc.drawText("Trying out canvas", 0, 0);
				e.gc.drawOval(50, 50, 80, 90);
			}
		});

		shell.open();
		
		while(!shell.isDisposed()) {
			if(!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
	
}

package ua.navpil.plugintest;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class PersonalDetailsApplication {
	public static void main(String [] args) {
		Display display = new Display();
		Shell shell = new Shell(display);

		shell.setLayout(new FillLayout());
		
		new PersonalDetailsDialog(shell, SWT.NONE);

		shell.open();
		
		while(!shell.isDisposed()) {
			if(!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
}

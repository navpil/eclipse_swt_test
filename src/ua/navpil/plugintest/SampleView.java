package ua.navpil.plugintest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.part.ViewPart;

public class SampleView extends ViewPart {
	Label label;
	Button button;

	public void createPartControl(Composite parent) {
		parent.setLayout(new RowLayout(SWT.VERTICAL));

		label = new Label(parent, SWT.BORDER);
		label.setText("Result will be here...");

		button = new Button(parent, SWT.PUSH);
		button.setText("Click me");

		parent.pack();

		ExecutorService executor = Executors.newSingleThreadExecutor();

		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent event) {
				Display display = Display.getCurrent();
				executor.execute(() -> {
					String text = callUrl("http://www.google.com");
					display.syncExec(() -> {
						label.setText(text);
						parent.pack();
					});
				});
			}
		});
	}

	public void setFocus() {
	}

	private String callUrl(String urlPath) {
		String text;
		try {
			URL url = new URL(urlPath);
			InputStream in = url.openStream();
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(in));

			String line;
			StringBuilder out = new StringBuilder("");
			while ((line = reader.readLine()) != null) {
				out.append(line);
			}
			text = "I called " + urlPath + " and got result of length "
					+ out.toString().length();
		} catch (IOException e) {
			text = "Something went wrong";
		}
		return text;
	}
}

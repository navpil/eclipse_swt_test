package ua.navpil.plugintest;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class PersonalDetailsDialog extends Composite{
	
	private Label nameLabel;
	private Text name;
	private Label sexLabel;
	private Combo sex;
	private Button ok;
	private Button cancel;
	private Button reset;

	public PersonalDetailsDialog(Composite composite, int style) {
		super(composite, style);
		
		setLayout(new GridLayout(3, false));
		
		setupInputFields();
		setupControlButtons();
		
		reset();
	}

	private void setupControlButtons() {
		ok = new Button(this, SWT.PUSH);
		ok.setText("OK");
		ok.addSelectionListener(new OkSelectionListener());
		
		cancel = new Button(this, SWT.PUSH);
		cancel.setText("Cancel");
		cancel.addSelectionListener(new CancelSelectionListener());
		
		reset = new Button(this, SWT.PUSH);
		reset.setText("Reset");
		reset.addSelectionListener(new ResetSelectionListener());
		
	}

	private void setupInputFields() {
		nameLabel = new Label(this, SWT.BORDER);
		nameLabel.setText("Name: ");
		
		name = new Text(this, SWT.BORDER);
		GridData nameGd = new GridData();
		nameGd.horizontalSpan = 2;
		name.setLayoutData(nameGd);
		
		sexLabel = new Label(this, SWT.BORDER);
		sexLabel.setText("Sex: ");

		GridData sexGd = new GridData();
		sexGd.horizontalSpan = 2;
		sex = new Combo(this, SWT.NONE);
		sex.setItems(new String[] {
			"MALE",
			"FEMALE"
		});
		sex.setLayoutData(sexGd);
	}
	
	private void reset() {
		name.setText("");
		sex.select(0);
	}

	private final class CancelSelectionListener extends SelectionAdapter {
		@Override
		public void widgetSelected(SelectionEvent event) {
			getParent().dispose();
		}
	}

	private final class ResetSelectionListener extends SelectionAdapter {
		@Override
		public void widgetSelected(SelectionEvent event) {
			reset();
		}
	}

	private final class OkSelectionListener extends SelectionAdapter {
		@Override
		public void widgetSelected(SelectionEvent event) {
			System.out.println("User has selected name " + name.getText() + " and sex " + sex.getText());
			getParent().dispose();
		}
	}

}

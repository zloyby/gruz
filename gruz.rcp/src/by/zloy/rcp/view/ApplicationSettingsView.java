package by.zloy.rcp.view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.part.ViewPart;

public class ApplicationSettingsView extends ViewPart {

	public static final String ID = "by.zloy.rcp.view.ApplicationSettingsView.ID";
	
	@Override
	public void createPartControl(Composite parent) {
		
		GridData gdFolderValue = new GridData();
		gdFolderValue.widthHint = 190;
		gdFolderValue.minimumWidth = 250;
		
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		layout.marginWidth = 2;
		layout.marginHeight = 2;
		layout.horizontalSpacing = 6;
		layout.verticalSpacing = 6;
		parent.setLayout(layout);
		
		Label saveItems = new Label(parent, SWT.NONE); 
		saveItems.setText("Save items to DB:");
		saveItems.setLayoutData(gdFolderValue);
		
		Button saveItemsValue = new Button(parent, SWT.CHECK);
		saveItemsValue.setSelection(true);
		saveItemsValue.setLayoutData(gdFolderValue);
	}

	@Override
	public void setFocus() {
	}

}

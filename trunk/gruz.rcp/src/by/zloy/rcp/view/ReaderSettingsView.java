package by.zloy.rcp.view;

import java.util.Calendar;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.part.ViewPart;

import by.zloy.rcp.jpa.JpaProvider;

public class ReaderSettingsView extends ViewPart {

	public static final String ID = "by.zloy.rcp.view.ReaderSettingsView.ID";

	@Override
	public void createPartControl(Composite parent) {
		
		GridData gdFolderValue = new GridData();
		gdFolderValue.widthHint = 190;
		
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		layout.marginWidth = 2;
		layout.marginHeight = 2;
		layout.horizontalSpacing = 6;
		layout.verticalSpacing = 6;
		parent.setLayout(layout);
		
		//1
		Label folder = new Label(parent, SWT.NONE); 
		folder.setText("Google reader folder:");
		
		final Text folderValue = new Text(parent, SWT.SINGLE | SWT.BORDER);
		folderValue.setMessage("kindle");
		folderValue.setEditable(true);
		folderValue.setLayoutData(gdFolderValue);
		
		//2
		Label maxNews = new Label(parent, SWT.NONE); 
		maxNews.setText("Maximum news to get:");
		
		final Spinner maxNewsValue = new Spinner(parent, SWT.BORDER);
		maxNewsValue.setValues(500, 1, 1000, 0, 10, 100);
		maxNewsValue.setLayoutData(gdFolderValue);
		
		//3
		Label makeUnread = new Label(parent, SWT.NONE); 
		makeUnread.setText("Make news as unread:");
		
		final Button makeUnreadValue = new Button(parent, SWT.CHECK);
		makeUnreadValue.setSelection(true);
		makeUnreadValue.setLayoutData(gdFolderValue);
		
		//4
		Label toMail = new Label(parent, SWT.NONE); 
		toMail.setText("Email of your kindle account:");
		
		final Text toMailValue = new Text(parent, SWT.SINGLE | SWT.BORDER);
		toMailValue.setMessage("replace_to_your_email@kindle.com");
		toMailValue.setEditable(true);
		toMailValue.setLayoutData(gdFolderValue);
		toMailValue.addVerifyListener(new VerifyListener() {
			private static final long serialVersionUID = -7535446110260951533L;

			@Override
			public void verifyText(VerifyEvent e) {
				//if(!e.text.contains("@")){
					//e.doit = false;
				//}
			}
		});
		
		//5
		Label time = new Label(parent, SWT.NONE); 
		time.setText("Time for checking:");
		
		final DateTime timeValue = new DateTime(parent, SWT.TIME | SWT.BORDER);
		timeValue.setLayoutData(gdFolderValue);
		
		//6
		Label none = new Label(parent, SWT.NONE); 
		none.setText("");
		
		Button save = new Button(parent, SWT.PUSH);
		save.setText("Save");
		save.addSelectionListener(new SelectionAdapter() {
			private static final long serialVersionUID = 3837449124523788708L;

			@Override
			public void widgetSelected(SelectionEvent e) {
				JpaProvider jpaProvider = new JpaProvider();
				
				Calendar cal = Calendar.getInstance();
				cal.set(Calendar.YEAR, timeValue.getYear());
				cal.set(Calendar.MONTH, timeValue.getMonth());
				cal.set(Calendar.DAY_OF_MONTH, timeValue.getDay());
				cal.set(Calendar.HOUR_OF_DAY, timeValue.getHours());
				cal.set(Calendar.MINUTE, timeValue.getMinutes());
				cal.set(Calendar.SECOND, timeValue.getSeconds());
				
				jpaProvider.saveRecord(cal, folderValue.getText(), maxNewsValue.getText(), 
						makeUnreadValue.getSelection(), toMailValue.getText());
			}
		});
		save.setLayoutData(gdFolderValue);
	}

	@Override
	public void setFocus() {
	}
}

package by.zloy.rcp;

import org.eclipse.rwt.lifecycle.IEntryPoint;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.application.WorkbenchAdvisor;

import by.zloy.rcp.ApplicationWorkbenchAdvisor;
import by.zloy.rcp.auth.LoginDialog;

public class RapApplication implements IEntryPoint {

	public static final String PLUGIN_ID = "by.zloy.rcp.rap";
	
	@Override
	public int createUI() {
		Display display = PlatformUI.createDisplay();
		LoginDialog loginDialog = new LoginDialog(new Shell());
		loginDialog.open();
		WorkbenchAdvisor advisor = new ApplicationWorkbenchAdvisor();
		return PlatformUI.createAndRunWorkbench(display, advisor);
	}
}

package by.zloy.rcp;

import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;

/**
 * This class controls all aspects of the application's execution
 */
public class Application extends RapApplication implements IApplication {

	public static final String PLUGIN_ID = "by.zloy.rcp";
	
	public Object start(IApplicationContext context) throws Exception {
		return createUI();
	}

	@Override
	public void stop() {
		// nothing
	}
}

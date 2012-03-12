package by.zloy.rcp;

import org.eclipse.jface.action.MenuManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ShellAdapter;
import org.eclipse.swt.events.ShellEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tray;
import org.eclipse.swt.widgets.TrayItem;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import by.zloy.rcp.image.Images;

public class ApplicationWorkbenchWindowAdvisor extends WorkbenchWindowAdvisor {

	private TrayItem trayItem;
	private Image trayImage;
	private ApplicationActionBarAdvisor actionBarAdvisor;
	
	public ApplicationWorkbenchWindowAdvisor(IWorkbenchWindowConfigurer configurer) {
		super(configurer);
	}

	public ActionBarAdvisor createActionBarAdvisor(IActionBarConfigurer configurer) {
		actionBarAdvisor = new ApplicationActionBarAdvisor(configurer); 
		return actionBarAdvisor;
	}

	public void preWindowOpen() {
		IWorkbenchWindowConfigurer configurer = getWindowConfigurer();
		configurer.setInitialSize(new Point(440, 500));
		configurer.setShowStatusLine(false);
		configurer.setShowCoolBar(false);
		configurer.setShowMenuBar(true);
		configurer.setTitle("Reader2Kindle");
	}

	public void postWindowOpen() {
		final IWorkbenchWindow window = getWindowConfigurer().getWindow();

		Rectangle displayBounds = Display.getCurrent().getBounds();
		Point size = window.getShell().getSize();
		int x = (displayBounds.width - size.x) / 2;
		int y = (displayBounds.height - size.y) / 2;
		window.getShell().setLocation(x, y);
	
		trayItem = initTaskItem(window);
		if (trayItem != null) {
			hookPopupMenu(window);
			hookMinimize(window);
		}
	}

	private void hookMinimize(final IWorkbenchWindow window) {
		window.getShell().addShellListener(new ShellAdapter() {
			private static final long serialVersionUID = 3282695574968858170L;

			@SuppressWarnings("unused")
			public void shellIconified(ShellEvent e) {
				window.getShell().setVisible(false);
			}
		});
		
		trayItem.addListener(SWT.DefaultSelection, new Listener() {
			private static final long serialVersionUID = -8569023049697668945L;

			public void handleEvent(Event event) {
				Shell shell = window.getShell();
				if (!shell.isVisible()) {
					shell.setVisible(true);
					window.getShell().setMinimized(false);
				}
			}
		});
	}

	private void hookPopupMenu(final IWorkbenchWindow window) {
		trayItem.addListener(SWT.MenuDetect, new Listener() {
			private static final long serialVersionUID = 6353265904643379704L;

			public void handleEvent(Event event) {
				MenuManager trayMenu = new MenuManager();
				Menu menu = trayMenu.createContextMenu(window.getShell());
				actionBarAdvisor.fillTrayItem(trayMenu);
				menu.setVisible(true);
			}
		});
	}

	private TrayItem initTaskItem(IWorkbenchWindow window) {
		final Tray tray = window.getShell().getDisplay().getSystemTray();
		if (tray == null) { return null; }
		TrayItem trayItem = new TrayItem(tray, SWT.NONE);
		trayImage = AbstractUIPlugin.imageDescriptorFromPlugin(Application.PLUGIN_ID, Images.RSS_LOGO).createImage();
		trayItem.setImage(trayImage);
		trayItem.setToolTipText("Reader2Kindle");
		return trayItem;
	}

	public void dispose() {
		super.dispose();
		if (trayImage != null) {
			trayImage.dispose();
			trayItem.dispose();
		}
	}
}

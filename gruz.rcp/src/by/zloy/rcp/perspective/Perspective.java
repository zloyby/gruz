package by.zloy.rcp.perspective;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

import by.zloy.rcp.view.ApplicationSettingsView;
import by.zloy.rcp.view.LogView;
import by.zloy.rcp.view.ReaderSettingsView;

public class Perspective implements IPerspectiveFactory {

	private static final String TOP_FOLDER = "by.zloy.top.folder";
	private static final String BOTTOM_FOLDER = "by.zloy.bottom.folder";

	public void createInitialLayout(IPageLayout layout) {
		layout.setEditorAreaVisible(false);
		
		IFolderLayout topLayout = layout.createFolder(TOP_FOLDER, IPageLayout.TOP, 0.5f, layout.getEditorArea());
		topLayout.addView(LogView.ID);
		topLayout.addView(ApplicationSettingsView.ID);
		
		IFolderLayout bottomLayout = layout.createFolder(BOTTOM_FOLDER, IPageLayout.BOTTOM, 0.5f, layout.getEditorArea());
		bottomLayout.addView(ReaderSettingsView.ID);
	}
}

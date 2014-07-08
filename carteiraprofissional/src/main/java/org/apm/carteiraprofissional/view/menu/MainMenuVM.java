package org.apm.carteiraprofissional.view.menu;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Path;
import org.zkoss.zk.ui.event.MouseEvent;

public class MainMenuVM {

	private String search;

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	private final static Logger logger = LoggerFactory
			.getLogger(MainMenuVM.class);

	private static String EXECUTIONS_PAGE = "registarRequisicao";
	private static String SPEC_SUBMIT_PAGE = "specsubmit";

	private static Map<String, String> pageNamesMap = new HashMap<String, String>();
	static {
		pageNamesMap.put(EXECUTIONS_PAGE, "index.zul");
		pageNamesMap.put(SPEC_SUBMIT_PAGE, "/pages/specsubmit.zul");
	}
	private Map<String, Component> componentPages = new HashMap<String, Component>();
	private String currentPageName = EXECUTIONS_PAGE;

	public void onClickNavigation(MouseEvent event) {
		String pageParam = event.getTarget().getId();
		logger.debug("onClickNavigation pageParam: {}", pageParam);
		// try to get the current component page from the map, and turn it off
		Component currentComponentPage = componentPages.get(currentPageName);
		if (currentComponentPage != null)
			currentComponentPage.setVisible(false);
		// set the current page to one passed on via id on button/link
		currentPageName = pageParam;
		// try to pull out the one we are supposed to navigate to
		currentComponentPage = componentPages.get(currentPageName);
		if (currentComponentPage == null) {
			Component comp = Path.getComponent("/mainPage/contentSection");
			currentComponentPage = Executions.createComponents(
					pageNamesMap.get(currentPageName), comp, null);
			componentPages.put(currentPageName, currentComponentPage);
		}
		currentComponentPage.setVisible(true);
	}

}

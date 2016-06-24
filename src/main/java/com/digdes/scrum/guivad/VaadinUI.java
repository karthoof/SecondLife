package com.digdes.scrum.guivad;

import com.digdes.scrum.dao.CustomerRepository;
import com.digdes.scrum.dao.ProjectDao;
import com.digdes.scrum.model.entity.Project;
import com.vaadin.event.FieldEvents;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import com.digdes.scrum.model.entity.Customer;
import com.vaadin.annotations.Theme;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
//Friday
@SpringUI(path="/createproject")
@Theme("valo")
public class VaadinUI extends UI {

	private final ProjectDao projectD; //private final ProjectDao projectD;

	private final CustomerEditor editor;

	private final Grid grid;

	private final TextField filter;

	private final Button addNewBtn;

	@Autowired
	public VaadinUI(ProjectDao projectD, CustomerEditor editor) { { //public VaadinUI(ProjectDao projectD, CustomerEditor editor) {
		this.projectD = projectD; //this.projectD = projectD;
		this.editor = editor;
		this.grid = new Grid();
		this.filter = new TextField();
		this.addNewBtn = new Button("Новый проект", FontAwesome.PLUS);
	}

	@Override
	protected void init(VaadinRequest request) {
		// build layout
		HorizontalLayout actions = new HorizontalLayout(filter, addNewBtn);
		VerticalLayout mainLayout = new VerticalLayout(actions, grid, editor);
		setContent(mainLayout);

		// Configure layouts and components
		actions.setSpacing(true);
		mainLayout.setMargin(true);
		mainLayout.setSpacing(true);

		grid.setHeight(300, Unit.PIXELS);
		grid.setColumns("id", "Называние", "Описание");

		filter.setInputPrompt("Поиск по названию");

		// Hook logic to components

		// Replace listing with filtered content when user changes filter
		filter.addTextChangeListener(e -> listProject(e.getText()));

		// Connect selected Customer to editor or hide if none is selected
		grid.addSelectionListener(e -> {
			if (e.getSelected().isEmpty()) {
				editor.setVisible(false);
			}
			else {
				editor.editProject((Project) grid.getSelectedRow());//editor.editProject((Project) grid.getSelectedRow());
			}
		});

		// Instantiate and edit new Customer the new button is clicked
			addNewBtn.addClickListener(e -> editor.editProject(new Project("", "")));//addNewBtn.addClickListener(e -> editor.editCustomer(new Project("", "")));

		// Listen changes made by the editor, refresh data from backend
		editor.setChangeHandler(() -> {
			editor.setVisible(false);
			listProject(filter.getValue());
		});

		// Initialize listing
		listProject(null);
	}

	// tag::listCustomers[]
	private void listProject(String text) {//	private void listProject(String text) {
		if (StringUtils.isEmpty(text)) {
			grid.setContainerDataSource(
					new BeanItemContainer(Project.class, projectD.findAll()));//new BeanItemContainer(Project.class, projectD.findAll()));
		}
		else {
			grid.setContainerDataSource(new BeanItemContainer(Project.class, //grid.setContainerDataSource(new BeanItemContainer(Project.class,
					projectD.findByName(text))); //	projectD.findByNameStartsWithIgnoreCase(text)));
		}
	}
	// end::listCustomers[]

}

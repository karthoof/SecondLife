package com.digdes.scrum.guivad;

import com.digdes.scrum.dao.CustomerRepository;
import com.digdes.scrum.model.entity.Customer;

import com.digdes.scrum.dao.ProjectDao;
import com.digdes.scrum.model.entity.Project;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.event.ShortcutAction;
import com.vaadin.server.FontAwesome;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

/**
 * A simple example to introduce building forms. As your real application is
 * probably much more complicated than this example, you could re-use this form in
 * multiple places. This example component is only used in VaadinUI.
 * <p>
 * In a real world application you'll most likely using a common super class for all your
 * forms - less code, better UX. See e.g. AbstractForm in Virin
 * (https://vaadin.com/addon/viritin).
 */
@SpringComponent
@UIScope
public class CustomerEditor extends VerticalLayout {//ProjectDao

	private final ProjectDao projectDao; //ProjectDao projectDao

	/**
	 * The currently edited customer
	 */
	private Project project; // Project project

	/* Fields to edit properties in Customer entity */
	TextField name = new TextField("Название"); //TextField name = new TextField("Название");
	TextField description = new TextField("Описание"); //TextField description = new TextField("Описание");

	/* Action buttons */
	Button save = new Button("Сохранить", FontAwesome.SAVE);
	Button cancel = new Button("Отмена");
	Button delete = new Button("Удалить", FontAwesome.TRASH_O);
	CssLayout actions = new CssLayout(save, cancel, delete);

	@Autowired
	public CustomerEditor(ProjectDao projectDao) { //ProjectDao projectDao
		this.projectDao = projectDao;					// this.projectDao = projectDao;

		addComponents(name, description, actions); //addComponents(name, description, actions);

		// Configure and style components
		setSpacing(true);
		actions.setStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);
		save.setStyleName(ValoTheme.BUTTON_PRIMARY);
		save.setClickShortcut(ShortcutAction.KeyCode.ENTER);
		//Передача кнопке объекта Листенер
		// wire action buttons to save, delete and reset
		Button.ClickListener listener = clickEvent -> projectDao.save(project);//Button.ClickListener listener = clickEvent -> repository.save(project);
		save.addClickListener(listener);
		delete.addClickListener(e -> projectDao.delete(project));	//delete.addClickListener(e -> projectDao.delete(project))
		cancel.addClickListener(e -> editCustomer(project)); //cancel.addClickListener(e -> editCustomer(project));
		setVisible(false);
	}

	public interface ChangeHandler {

		void onChange();
	}

	public final void editProject(Project p) { //public final void editProject(Project p)
		final boolean persisted = p.getId() != null; //	final boolean persisted = p.getId() != null;
		if (persisted) {
			// Find fresh entity for editing
			project = projectDao.findOne(p.getId());//	project = projectDao.findOne(p.getId());
		}
		else {
			project = p;//	project = p;

		}
		cancel.setVisible(persisted);

		// Bind customer properties to similarly named fields
		// Could also use annotation or "manual binding" or programmatically
		// moving values from fields to entities before saving
		BeanFieldGroup.bindFieldsUnbuffered(project, this); //	BeanFieldGroup.bindFieldsUnbuffered(project, this);

		setVisible(true);

		// A hack to ensure the whole form is visible
		save.focus();
		// Select all text in firstName field automatically
		name.selectAll(); //name.selectAll();
	}

	public void setChangeHandler(ChangeHandler h) {
		// ChangeHandler is notified when either save or delete
		// is clicked
		save.addClickListener(e -> h.onChange());
		delete.addClickListener(e -> h.onChange());
	}

}

package com.example.vaadindemo;

import com.example.vaadindemo.service.CapitalLeterValidator;
import com.example.vaadindemo.domain.Book;
import com.example.vaadindemo.service.ISBNValidator;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.ui.*;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.validator.DoubleRangeValidator;
import com.vaadin.data.validator.IntegerRangeValidator;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Table;
import com.vaadin.ui.UI;


@Title("Księgarnia")
@Theme("valo")
public class VaadinApp extends UI {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Override
    protected void init(VaadinRequest request) {

        final HorizontalLayout mainLayout = new HorizontalLayout();

// ========================================================================
// Tabele
// ========================================================================
        final BeanItemContainer<Book> beanContainer = new BeanItemContainer<Book>(
                Book.class);
   
        beanContainer.addBean(new Book("W pustyni i w puszczy", "Marian Opania","2342342342344", 234, 26));
        
        final Table tabela = new Table();
        tabela.setContainerDataSource(beanContainer);
        tabela.setSelectable(true);
        tabela.setImmediate(true);

// ========================================================================
// form
// ========================================================================
        Book ksiazka = new Book();
        BeanItem<Book> woot = new BeanItem<Book>(ksiazka);
        final FieldGroup fieldGroup = new FieldGroup();
        fieldGroup.setBuffered(true);
        fieldGroup.setItemDataSource(woot);

// ========================================================================
// Walidatory 
// ========================================================================
        FormLayout tableLayout = new FormLayout();

        tableLayout.setImmediate(true);
        tableLayout.setSpacing(true);
        tableLayout.setMargin(true);

        final Field<?> titleField = fieldGroup.buildAndBind("Tytuł Książki", "title");
        titleField.addValidator(new CapitalLeterValidator());
        titleField.addValidator(new StringLengthValidator("Zła długośc", 3, 50, false));
        titleField.setRequired(true);
        

        final Field<?> authorField = fieldGroup.buildAndBind("Autor", "author");
        authorField.addValidator(new CapitalLeterValidator());
        authorField.addValidator(new StringLengthValidator("Zła długośc", 3, 50, false));
        authorField.setRequired(true);
        
        final Field<?> ISBNField = fieldGroup.buildAndBind("Numer ISBN", "ISBN");
        ISBNField.addValidator(new StringLengthValidator("Błędna wartość", 13, 13, false));
        ISBNField.addValidator(new ISBNValidator());
        ISBNField.setRequired(true);
        
        final Field<?> pagesNumber = fieldGroup.buildAndBind("Ilość stron", "pagesNumber");
        pagesNumber.addValidator(new IntegerRangeValidator("Błędna ilość stron", 1, 2000));
        pagesNumber.setRequired(true);
        

        final Field<?> priceField = fieldGroup.buildAndBind("Cena książki", "price");
        priceField.addValidator(new DoubleRangeValidator("Błędna cena książki", 1.00, 1000.00));
        priceField.setRequired(true);
        

        tableLayout.addComponent(titleField);
        tableLayout.addComponent(authorField);
        tableLayout.addComponent(ISBNField);
        tableLayout.addComponent(pagesNumber);
        tableLayout.addComponent(priceField);
// ========================================================================
// Przyciski
// ========================================================================

        HorizontalLayout buttons = new HorizontalLayout();

        final Button btnDodaj = new Button("Dodaj");
        final Button btnUsun = new Button("Usuń");
        final Button btnEdytuj = new Button("Edytuj");
        buttons.addComponent(btnDodaj);
        buttons.addComponent(btnEdytuj);
        buttons.addComponent(btnUsun);
// ========================================================================
// Layout
// ========================================================================
        mainLayout.addComponent(tabela);
        mainLayout.addComponent(tableLayout);
        mainLayout.addComponent(buttons);
        tableLayout.addComponent(buttons);

        tabela.addValueChangeListener(new ValueChangeListener() {
            /**
             *
             */
            private static final long serialVersionUID = 1L;

            @Override
            public void valueChange(ValueChangeEvent event) {
                if (tabela.getValue() != null) {
                    Book temp = (Book) tabela.getValue();
                    BeanItem<Book> bike = new BeanItem<Book>(temp);
                    fieldGroup.setItemDataSource(bike);

                }
            }
        });
        btnDodaj.addClickListener(new ClickListener() {
            /**
             *
             */

            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(ClickEvent event) {

                Book toot = ((BeanItem<Book>) fieldGroup.getItemDataSource()).getBean();
                /*
                 * Walidatory pól
                 */
                if (titleField.isValid() && authorField.isValid() &&
                       ISBNField.isValid() && pagesNumber.isValid() && 
                        priceField.isValid()) {

                    beanContainer.addBean(new Book(toot.getTitle(), toot.getAuthor(), toot.getISBN(), 
                            toot.getPagesNumber(), toot.getPrice()));
                    Notification.show("Dodano nową książkę!");
                } else {
                    Notification.show("Błąd. Popraw dane i spróbuj ponownie");
                }
            }
        });
        btnUsun.addClickListener(new ClickListener() {
            /**
             *
             */
            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(ClickEvent event) {
                beanContainer.removeItem(tabela.getValue());
            }
        });
        btnEdytuj.addClickListener(new ClickListener() {
            /**
             *
             */
            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(ClickEvent event) {
                try {
                    fieldGroup.commit();
                    tabela.refreshRowCache();
                } catch (CommitException e) {
                    e.printStackTrace();
                }
            }
        });
        //setContent(mainContainer);
        setContent(mainLayout);
    }
}

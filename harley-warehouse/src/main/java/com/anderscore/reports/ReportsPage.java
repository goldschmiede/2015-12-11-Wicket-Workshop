package com.anderscore.reports;

import com.anderscore.authenticate.AuthenticatedPage;
import com.anderscore.homepage.HomePage;
import de.agilecoders.wicket.core.markup.html.bootstrap.navigation.ajax.BootstrapAjaxPagingNavigator;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.ListDataProvider;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by pmoebius on 07.12.2015.
 */
public class ReportsPage extends AuthenticatedPage {
    public ReportsPage(PageParameters parameters) {
        super(parameters);

        List<String[]> countries = loadCountriesFromCsv();
        ListDataProvider<String[]> listDataProvider = new ListDataProvider<String[]>(countries);

        DataView<String[]> dataView = new DataView<String[]>("rows", listDataProvider) {

            @Override
            protected void populateItem(Item<String[]> item) {
                String[] countriesArr = item.getModelObject();
                RepeatingView repeatingView = new RepeatingView("dataRow");

                for (int i = 0; i < countriesArr.length; i++){
                    repeatingView.add(new Label(repeatingView.newChildId(), countriesArr[i]));
                }
                item.add(repeatingView);
            }
        };

        dataView.setItemsPerPage(15);

        add(dataView);
        add(new BootstrapAjaxPagingNavigator("pagingNavigator", dataView));
    }

    private List<String[]> loadCountriesFromCsv() {
        InputStream countriesStream = ReportsPage.class.getResourceAsStream("countries.csv");
        Scanner scanner = new Scanner(countriesStream);
        List<String[]> countries = new ArrayList<String[]>();

        while(scanner.hasNext()){
            String curLine = scanner.nextLine();
            String[] countryData = curLine.split(";");

            countries.add(countryData);
        }

        scanner.close();

        return countries;
    }
}
package de.klschlitzohr.mepp.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class Section {

    private String section;
    private List<Title> titles;

    public Section(String section) {
        this.section = section;
        this.titles = new ArrayList<>();
    }

    public void addTitle(Title title) {
        this.titles.add(title);
    }

}

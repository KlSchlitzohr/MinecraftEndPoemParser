package de.klschlitzohr.mepp.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class Title {

    private String title;
    private List<String> names;

    public Title(String title) {
        this.title = title;
        this.names = new ArrayList<>();
    }

    public void addName(String name) {
        this.names.add(name);
    }

}

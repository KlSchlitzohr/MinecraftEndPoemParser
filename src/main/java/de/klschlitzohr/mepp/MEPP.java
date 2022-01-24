package de.klschlitzohr.mepp;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.klschlitzohr.mepp.model.Section;
import de.klschlitzohr.mepp.model.Title;
import lombok.SneakyThrows;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class MEPP {

    private final List<Section> sections;
    private final ObjectMapper objectMapper;

    public static void main(String[] args) {
        MEPP mepp = new MEPP();
        mepp.parse("input.txt");
    }

    public MEPP() {
        this.sections = new ArrayList<>();
        this.objectMapper = new ObjectMapper();
    }

    @SneakyThrows
    public void parse(String fileString) {
        File file = new File(fileString);
        if (file.exists()) {
            Stream<String> filestream = null;
            try {
                filestream = Files.lines(Paths.get(file.getAbsolutePath(), new String[0]), StandardCharsets.UTF_8);
            } catch (IOException e) {
                e.printStackTrace();
            }
            ArrayList<String> fileline = new ArrayList<String>();
            filestream.forEach(fileline::add);
            Section section = null;
            Title title = null;
            for (String line : fileline) {
                if (line.startsWith("#")) {
                    section = new Section(line.substring(1));
                    sections.add(section);
                } else if (line.startsWith("*")) {
                    title = new Title(line.substring(1));
                    section.addTitle(title);
                } else if (line.startsWith("-")) {
                    if (title != null) {
                        title.addName(line.substring(1));
                    }
                }
            }
        } else {
            System.out.println("Please put a input.txt file in the same directory as the jar file!");
        }
        objectMapper.writeValue(new File("output.json"), sections);
    }
}

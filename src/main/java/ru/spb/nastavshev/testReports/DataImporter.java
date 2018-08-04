package ru.spb.nastavshev.testReports;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class DataImporter {

    private static final String COMMA = ";";
    private final DataRepository dataRepository;
    private Function<String, UserData> mapToItem = (line) -> {
        String[] p = line.split(COMMA);// a CSV has comma separated lines
        UserData userData = new UserData(p[0], p[1], p[2], p[3], p[4], p[5], p[6], p[7], p[8], p[9], p[10], p[11]);
        return userData;
    };

    public DataImporter(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    public void importCsv(MultipartFile file) throws IOException {

        String filename = StringUtils.cleanPath(file.getOriginalFilename());

        dataRepository.deleteAll();

        try (InputStream inputStream = file.getInputStream()) {

            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

            final List<UserData> list = br.lines().skip(1).map(mapToItem).collect(Collectors.toList());

            dataRepository.saveAll(list);

            br.close();

        }

        return;
    }
}

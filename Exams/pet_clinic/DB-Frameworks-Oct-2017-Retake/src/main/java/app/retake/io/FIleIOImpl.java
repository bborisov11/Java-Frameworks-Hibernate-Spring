package app.retake.io;

import app.retake.io.api.FileIO;
import org.springframework.stereotype.Component;

import java.io.*;
@Component
public class FIleIOImpl implements FileIO {

    @Override
    public String read(String file) throws IOException {
        StringBuilder builder = new StringBuilder();
        try(
        InputStream stream = getClass().getResourceAsStream(file);
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        ) {
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
        }
        return builder.toString();
    }

    @Override
    public void write(String fileContent, String file) throws IOException {
            //String path = System.getProperty("user.dir") + "/src/main/resources" + file;

            try (
                    OutputStream outputStream = new FileOutputStream(file);
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
                    ) {
                bufferedWriter.write(fileContent);
            }
    }
}

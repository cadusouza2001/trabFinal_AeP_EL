package persistence;

import java.io.*;

public class FileRepository {

    File file;
    FileWriter repoWriter;
    BufferedReader repoReader;

    public FileRepository() throws IOException {
        this.file = new File("./repository.txt");
        if (!this.file.exists()) {
            this.file.createNewFile();
        }
        this.repoWriter = new FileWriter(file, true);
        this.repoReader = new BufferedReader(new FileReader(file));
    }

    public FileRepository(String pathname) throws IOException {
        this.file = new File(pathname);
        if (!this.file.exists()) {
            this.file.createNewFile();
        }
        this.repoWriter = new FileWriter(file, true);
        this.repoReader = new BufferedReader(new FileReader(file));
    }

    public void saveToRepo(String line) throws IOException {
        this.repoWriter.write(line);
        this.repoWriter.close();
    }

    public String readLineFromRepo() throws IOException {
        return this.repoReader.readLine();
    }

    public File getFile() {
        return file;
    }

    public FileWriter getRepoWriter() {
        return repoWriter;
    }

    public BufferedReader getRepoReader() {
        return repoReader;
    }
}

package persistence;

import java.io.*;

public class FileRepository {

    File repo;
    FileWriter repoWriter;
    BufferedReader repoReader;

    public FileRepository() throws IOException {
        this.repo = new File("./repository.txt");
        if (!this.repo.exists()) {
            this.repo.createNewFile();
        }
        this.repoWriter = new FileWriter(repo, true);
        this.repoReader = new BufferedReader(new FileReader(repo));
    }

    public void saveToRepo(String line) throws IOException {
        this.repoWriter.write(line);
        this.repoWriter.close();
    }

    public String readLineFromRepo() throws IOException {
        return this.repoReader.readLine();
    }

}

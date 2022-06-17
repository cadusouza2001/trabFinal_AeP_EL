package persistence;

import controller.PersonController;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;
import persistence.FileRepository;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class FileRepositoryTest {

    FileRepository repo;

@Before
public void before() throws Exception {
    repo = new FileRepository("./test.txt");
} 

@After
public void after() throws Exception {
    repo.getRepoReader().close();
    repo.getRepoWriter().close();
    repo.getFile().delete();
} 

/** 
* 
* Method: saveToRepo(String line) 
* 
*/ 
@Test
public void testSaveAndReadFromRepo() throws Exception {
    repo.saveToRepo(Integer.toString(4+4));
    Assert.assertEquals("8",repo.readLineFromRepo());
}

} 

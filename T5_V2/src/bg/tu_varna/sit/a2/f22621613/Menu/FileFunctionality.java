package bg.tu_varna.sit.a2.f22621613.Menu;

import java.util.List;
import java.util.Set;

public interface FileFunctionality {
    void openFile(String filename,Menu menu);
    List<String> findRules(String content);
    Set<Character> extractCharacters(String content, String tag);
    void closeFile();
    void saveFile();
    void saveFileAs(String filename);
    void help();
    void exit();
}

import java.util.ArrayList;
import java.util.List;

public class Group {
    private List<Character> characters;
    private String groupId;

    public Group(String groupId) {
        this.characters = new ArrayList<>();
        this.groupId = groupId;
    }

    public void addCharacter(Character character) {
        characters.add(character);
    }

    public List<Character> getCharacters() {
        return characters;
    }

    public List<Character> getRestCharacters() {
        // StringBuilder restCharacters = new StringBuilder();
        // for (Character character : characters) {
        //     restCharacters.append(character.LABEL);
        // }
        // return restCharacters.toString();
        return characters;
    }

    public boolean isDefeated() {
        // A group is defeated if it has no characters left
        return characters.isEmpty();
    }

    public String getGroupId() {
        return groupId;
    }
}

package dtos;

import entities.Hobby;
import entities.Phone;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class HobbyDTO implements Serializable {
    private String name;
    private final String wikiLink;
    private final String category;
    private final String type;

    public HobbyDTO(Hobby hobby) {
        this.name = hobby.getName();
        this.wikiLink = getWikiLink();
        this.category = getCategory();
        this.type = getType();

    }

    public void setName(String name) {
        this.name = name;
    }
    public static List<HobbyDTO> toList(List<Hobby> hobbies) {
        return hobbies.stream().map(HobbyDTO::new).collect(Collectors.toList());
    }


    public String getName() {
        return name;
    }

    public String getWikiLink() {
        return wikiLink;
    }

    public String getCategory() {
        return category;
    }

    public String getType() {
        return type;
    }

    public Hobby getEntity(){
        Hobby h= new Hobby(this.name, this.wikiLink, this.category, this.type);
        if(name != null)
            h.setName(this.name);
        return h;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HobbyDTO entity = (HobbyDTO) o;
        return Objects.equals(this.name, entity.name) &&
                Objects.equals(this.wikiLink, entity.wikiLink) &&
                Objects.equals(this.category, entity.category) &&
                Objects.equals(this.type, entity.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, wikiLink, category, type);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "name = " + name + ", " +
                "wikiLink = " + wikiLink + ", " +
                "category = " + category + ", " +
                "type = " + type + ")";
    }
}
